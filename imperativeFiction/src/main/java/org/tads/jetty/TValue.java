package org.tads.jetty;

import java.util.Vector;

public class TValue {

	public static final int NUMBER = 1;
	public static final int OBJECT = 2;
	public static final int SSTRING = 3;
	public static final int NIL = 5;
	public static final int CODE = 6;
	public static final int LIST = 7;
	public static final int TRUE = 8;
	public static final int DSTRING = 9;
	public static final int FUNCTION = 10;
	public static final int OLD_TEMPLATE = 11;
	public static final int PROPERTY = 13;
	public static final int DEMAND = 14;
	public static final int SYNONYM = 15;
	public static final int REDIR = 16;
	public static final int TEMPLATE = 17;

	public TValue(int type, byte[] value) {
		this(type, value, 0, value.length);
	}

	public TValue(int type, byte[] data, int start, int end) {
		_type = type;
		if (type == NUMBER) {
			_int_value = GameFileParser.read_32signed(data, start);
		} else if (type == OBJECT || type == FUNCTION || type == PROPERTY || type == SYNONYM || type == REDIR) {
			_int_value = GameFileParser.read_16(data, start);
		} else if (type == NIL || type == TRUE || type == DEMAND) {
			; // nothing to do
		} else if (type == SSTRING || type == DSTRING) {
			_str_value = new String(data, start, end - start);
		} else if (type == CODE) {
			_data_value = new byte[end - start];
			System.arraycopy(data, start, _data_value, 0, end - start);
		} else if (type == LIST) {
			_list_value = new Vector(5);
			while (start < end) {
				int ty = data[start++];
				TValue tv = null;
				if (ty == NUMBER) {
					tv = new TValue(ty, data, start, start + 4);
					start += 4;
				} else if (ty == OBJECT || ty == FUNCTION || ty == PROPERTY) {
					tv = new TValue(ty, data, start, start + 2);
					start += 2;
				} else if (ty == NIL || ty == TRUE) {
					tv = new TValue(ty, data, start, start);
				} else if (ty == SSTRING || ty == LIST) {
					int tsz = GameFileParser.read_16(data, start) - 2;
					start += 2;
					tv = new TValue(ty, data, start, start + tsz);
					start += tsz;
				} else {
					Jetty.out.print_error("Unknown type of list member: " + ty, 1);
					start = end;
				}
				_list_value.addElement(tv);
			}
		} else if (type == TEMPLATE || type == OLD_TEMPLATE) {
			int count = GameFileParser.read_8(data, start++);
			_list_value = new Vector(count);
			for (int i = 0; i < count; i++) {
				int[] t = new int[6];
				t[0] = GameFileParser.read_16signed(data, start + 0);
				if (t[0] == -1)
					t[0] = 0; // seems silly to have this -1 and others 0
				t[1] = GameFileParser.read_16(data, start + 2);
				t[2] = GameFileParser.read_16(data, start + 4);
				t[3] = GameFileParser.read_16(data, start + 6);
				t[4] = GameFileParser.read_16(data, start + 8);
				// Try and work old templates into the scheme, for Dan Greenlee
				if (type == OLD_TEMPLATE) {
					t[5] = 0;
					start += 10;
					_type = TEMPLATE;
				} else {
					t[5] = GameFileParser.read_8(data, start + 10);
					start += 16; // more space is reserved per template than is 
									// actually used
				}
				_list_value.addElement(t);
			}
		} else {
			Jetty.out.print_error("Unknown type in org.tads.jetty.TValue constructor: " + type, 1);
		}
	}

	public TValue(int type, int value) {
		_type = type;
		if (type == NUMBER || type == OBJECT || type == FUNCTION || type == PROPERTY) {
			_int_value = value;
		} else if (type == NIL || type == TRUE || type == DEMAND) {
			; // nothing to do
		} else {
			Jetty.out.print_error("Illegal type in this org.tads.jetty.TValue constructor: " + type, 1);
		}
	}

	public TValue(int type, String s) {
		_type = type;
		if (type == SSTRING) {
			_str_value = s;
		} else {
			Jetty.out.print_error("Illegal type in this org.tads.jetty.TValue constructor: " + type, 1);
		}
	}

	public TValue(int type, Vector lst) {
		_type = type;
		if (type == LIST) {
			_list_value = lst;
		} else {
			Jetty.out.print_error("Illegal type in this org.tads.jetty.TValue constructor: " + type, 1);
		}
	}

	// I am sure there is an excellent and proper reason why the standard
	// java clone method is protected, but I don't know it, and I need
	// a public clone method.
	public TValue do_clone() {
		TValue t = new TValue(NUMBER, 0);
		t.copy(this);
		return t;
	}

	public void copy(TValue base) {
		if (base == this) {
			Jetty.out.print_error("copying onto self; not really doing it", 2);
			return;
		}
		_type = base._type;
		if (_type == NUMBER || _type == OBJECT || _type == FUNCTION || _type == PROPERTY || _type == SYNONYM || _type == REDIR) {
			_int_value = base._int_value;
		} else if (_type == NIL || _type == TRUE || _type == DEMAND) {
			; // nothing to do
		} else if (_type == SSTRING || _type == DSTRING) {
			_str_value = base._str_value;
		} else if (_type == CODE) {
			_data_value = base._data_value;
		} else if (_type == LIST) {
			_list_value = new Vector(base._list_value.size());
			for (int i = 0; i < base._list_value.size(); i++)
				_list_value.addElement(((TValue) base._list_value.elementAt(i)).do_clone());
		} else if (_type == TEMPLATE) {
			// templates are always read-only, so it's safe to do this, right?
			_list_value = base._list_value;
		} else {
			Jetty.out.print_error("Unknown type in org.tads.jetty.TValue copy(): " + _type, 1);
		}
	}

	public int get_type() {
		return _type;
	}

	public boolean must_be(int t1) {
		if (_type != t1) {
			Jetty.out.print_error("Error: wrong type -- expected " + type_names[t1] + ", found " + type_names[_type], 1);
			return false;
		} else
			return true;
	}

	public boolean must_be(int t1, int t2) {
		if (_type != t1 && _type != t2) {
			Jetty.out.print_error("Error: wrong type -- expected " + type_names[t1] + " or " + type_names[t2] + ", found " + type_names[_type], 1);
			return false;
		} else
			return true;
	}

	public boolean must_be(int t1, int t2, int t3) {
		if (_type != t1 && _type != t2 && _type != t3) {
			Jetty.out.print_error("Error: wrong type -- expected " + type_names[t1] + ", " + type_names[t2] + ", or " + type_names[t3] + ", found " + type_names[_type], 1);
			return false;
		} else
			return true;
	}

	public final static String[] type_names = { "UNUSED", "number", "object", "sstring", "UNUSED", "nil", "code", "list", "true", "dstring", "function", "???", "???", "property", "demand", "synonym", "redir", "template" };

	public int get_number() throws HaltTurnException {
		if (!must_be(NUMBER))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _int_value;
	}

	public boolean get_logical() // ie, true/nil
	{
		// apparently all values are valid for a logical test, and anything
		// non-nil and non-zero is true.
		if (!(_type == NUMBER || _type == TRUE || _type == NIL))
			return true;
		must_be(NUMBER, TRUE, NIL);
		if (_type == TRUE)
			return true;
		else if (_type == NIL)
			return false;
		else
			return (_int_value != 0 ? true : false);
	}

	public String get_string() throws HaltTurnException {
		if (!must_be(SSTRING))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _str_value;
	}

	public String get_dstring() throws HaltTurnException {
		if (!must_be(DSTRING))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _str_value;
	}

	public Vector get_list() throws HaltTurnException {
		if (!must_be(LIST))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _list_value;
	}

	public int get_object() throws HaltTurnException {
		if (!must_be(OBJECT))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _int_value;
	}

	public int get_function() throws HaltTurnException {
		if (!must_be(FUNCTION))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _int_value;
	}

	public int get_property() throws HaltTurnException {
		if (!must_be(PROPERTY))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _int_value;
	}

	public int get_redir() throws HaltTurnException {
		if (!must_be(REDIR))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _int_value;
	}

	public int get_synonym() throws HaltTurnException {
		if (!must_be(SYNONYM))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _int_value;
	}

	public byte[] get_code() throws HaltTurnException {
		if (!must_be(CODE))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _data_value;
	}

	public Vector get_template() throws HaltTurnException {
		if (!must_be(TEMPLATE))
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		return _list_value;
	}

	public int get_comparison(TValue other) throws HaltTurnException {
		if (this._type == other._type) {
			if (!must_be(NUMBER, SSTRING))
				throw new HaltTurnException(ParserError.TYPE_MISMATCH);
			if (_type == NUMBER) {
				return (this._int_value == other._int_value) ? 0 : ((this._int_value < other._int_value) ? -1 : 1);
			} else {
				int v = this._str_value.compareTo(other._str_value);
				return (v == 0) ? 0 : ((v < 0) ? -1 : 1);
			}
		} else {
			Jetty.out.print_error("Mismatched types in comparison: " + type_names[this._type] + " and " + type_names[other._type], 1);
			throw new HaltTurnException(ParserError.TYPE_MISMATCH);
		}
	}

	public boolean equals(Object o) {
		if (!(o instanceof TValue))
			return false;
		TValue other = (TValue) o;
		if (_type != other._type)
			return false;
		if (_type == NUMBER || _type == OBJECT || _type == FUNCTION || _type == PROPERTY || _type == SYNONYM || _type == REDIR) {
			return (_int_value == other._int_value);
		} else if (_type == NIL || _type == TRUE) {
			return true; // nothing else to check
		} else if (_type == SSTRING || _type == DSTRING) {
			return _str_value.equals(other._str_value);
		} else if (_type == LIST) {
			if (_list_value.size() != other._list_value.size())
				return false;
			for (int i = 0; i < _list_value.size(); i++) {
				if (!((TValue) _list_value.elementAt(i)).equals(other._list_value.elementAt(i)))
					return false;
			}
			return true;
		} else {
			return false;
		}
	}

	int _type;
	int _int_value;
	String _str_value;
	Vector _list_value;
	byte[] _data_value;
}
