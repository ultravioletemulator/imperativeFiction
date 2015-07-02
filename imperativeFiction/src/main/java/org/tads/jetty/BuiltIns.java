package org.tads.jetty;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.regexp.RE;
//import org.apache.regexp.RESyntaxException;
import org.tads.gameRunner.core.TadsUtils;

public class BuiltIns {

	// note that this'll give the same value across different invocations
	// unless you call randomize(), which is as it should be
	private static Random _rand = new Random(0);
	// apparently we want to keep track of some arbitrary start point.
	private static Date _start_date = new Date();

	// Note: the arguments to this function are the literal ones passed in, 
	// but they're supposed to be passed by value. So they shouldn't be modified 
	// directly, they should be copied and the copy messed with
	public static TValue run(int type, TValue[] args, TValue[] current_args) throws ParseException, ReparseException, HaltTurnException, GameOverException {
		if (Jetty.get_debug_level() >= 3) {
			Jetty.out.print_error("Calling builtin=" + type + " argc=" + args.length, 3);
		}
		switch (type) {
		case 0: // say
		{
			if (!expect_args(type, args.length, 1, 2))
				return new TValue(TValue.NIL, 0);
			TValue arg = args[0];
			arg.must_be(TValue.NUMBER, TValue.SSTRING, TValue.NIL);
			if (arg.get_type() == TValue.NUMBER) {
				Jetty.out.print(Integer.toString(arg.get_number()));
			} else if (arg.get_type() == TValue.SSTRING) {
				Jetty.out.print(arg.get_string());
			} else {
				// for nil, just print nothing
				;
			}
			return new TValue(TValue.NIL, 0);
		}
		case 1: // car
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Vector lst = args[0].get_list();
			// Stephen Newton pointed out this should test the list size
			if (lst.size() > 0)
				return ((TValue) lst.elementAt(0)).do_clone();
			else
				return new TValue(TValue.NIL, 0);
		}
		case 2: // cdr
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			args[0].must_be(TValue.LIST);
			TValue tv = args[0].do_clone();
			Vector v = tv.get_list();
			if (v.size() > 0)
				v.removeElementAt(0);
			return tv;
		}
		case 3: // length
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			if (args[0].get_type() == TValue.SSTRING)
				return new TValue(TValue.NUMBER, args[0].get_string().length());
			else
				return new TValue(TValue.NUMBER, args[0].get_list().size());
		}
		case 4: // randomize
		{
			_rand = new Random();
			return new TValue(TValue.NIL, 0);
		}
		case 5: // rand
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			int n = args[0].get_number();
			if (n <= 0) {
				Jetty.out.print_error("rand() called with non-positive value: " + n, 1);
				n = 1;
			}
			int r = _rand.nextInt();
			return new TValue(TValue.NUMBER, (((r < 0 ? -r : r) % n) + 1));
		}
		case 6: // substr
		{
			if (!expect_args(type, args.length, 3))
				return new TValue(TValue.NIL, 0);
			String str = args[0].get_string();
			int offset = args[1].get_number() - 1;
			int len = args[2].get_number();
			if (offset >= str.length())
				return new TValue(TValue.SSTRING, "");
			if (offset + len > str.length())
				len = str.length() - offset;
			return new TValue(TValue.SSTRING, str.substring(offset, offset + len));
		}
		case 7: // cvtstr
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			args[0].must_be(TValue.NUMBER, TValue.TRUE, TValue.NIL);
			if (args[0].get_type() == TValue.NUMBER) {
				String str = Integer.toString(args[0].get_number());
				return new TValue(TValue.SSTRING, str);
			} else if (args[0].get_type() == TValue.TRUE)
				return new TValue(TValue.SSTRING, "true");
			else
				return new TValue(TValue.SSTRING, "nil");
		}
		case 8: // cvtnum
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			String str = args[0].get_string();
			if (str.equals("true"))
				return new TValue(TValue.TRUE, 0);
			else if (str.equals("nil"))
				return new TValue(TValue.NIL, 0);
			else {
				int val = 0;
				try {
					val = Integer.parseInt(str);
				} catch (NumberFormatException nfe) {
					val = 0;
				}
				return new TValue(TValue.NUMBER, val);
			}
		}
		case 9: // upper
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			String str = args[0].get_string();
			return new TValue(TValue.SSTRING, str.toUpperCase());
		}
		case 10: // lower
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			String str = args[0].get_string();
			return new TValue(TValue.SSTRING, str.toLowerCase());
		}
		case 11: // caps
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print("\\^");
			return new TValue(TValue.NIL, 0);
		}
		case 12: // find
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			args[0].must_be(TValue.LIST, TValue.SSTRING);
			if (args[0].get_type() == TValue.LIST) {
				Vector lst = args[0].get_list();
				for (int i = 0; i < lst.size(); i++)
					if (lst.elementAt(i).equals(args[1]))
						return new TValue(TValue.NUMBER, i + 1); // index from 1, of course
				return new TValue(TValue.NIL, 0);
			} else {
				String base = args[0].get_string();
				String sub = args[1].get_string();
				int idx = base.indexOf(sub);
				if (idx == -1)
					return new TValue(TValue.NIL, 0);
				else
					return new TValue(TValue.NUMBER, idx + 1);
			}
		}
		case 13: // getarg
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			int arg = args[0].get_number();
			if (arg > 0 && arg <= current_args.length)
				return current_args[arg - 1];
			else {
				Jetty.out.print_error("getarg out of range: " + arg, 1);
				return new TValue(TValue.NIL, 0);
			}
		}
		case 14: // datatype
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			return new TValue(TValue.NUMBER, args[0].get_type());
		}
		case 15: // setdaemon
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			int func = args[0].get_function();
			Jetty.state.add_daemon(func, -1, args[1], GameState.CONTINUOUS);
			return new TValue(TValue.NIL, 0);
		}
		case 16: // setfuse
		{
			if (!expect_args(type, args.length, 3))
				return new TValue(TValue.NIL, 0);
			int func = args[0].get_function();
			int time = args[1].get_number();
			if (time < 1)
				time = 1;
			Jetty.state.add_daemon(func, -1, args[2], time);
			return new TValue(TValue.NIL, 0);
		}
		case 17: // setversion
		{
			Jetty.out.print_error("This function is no longer supported.", 0);
			return new TValue(TValue.NIL, 0);
		}
		case 18: // notify
		{
			if (!expect_args(type, args.length, 3))
				return new TValue(TValue.NIL, 0);
			int obj = args[0].get_object();
			int prop = args[1].get_property();
			int time = args[2].get_number();
			Jetty.state.add_daemon(obj, prop, null, ((time == 0) ? GameState.CONTINUOUS : time));
			return new TValue(TValue.NIL, 0);
		}
		case 19: // unnotify
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			int obj = args[0].get_object();
			int prop = args[1].get_property();
			Jetty.state.remove_daemon(obj, prop, null);
			return new TValue(TValue.NIL, 0);
		}
		case 20: // yorn
		{
			String answer = Jetty.in.read_line().toLowerCase();
			int value = -1;
			if (answer.length() > 0 && answer.charAt(0) == 'y')
				value = 1;
			else if (answer.length() > 0 && answer.charAt(0) == 'n')
				value = 0;
			return new TValue(TValue.NUMBER, value);
		}
		case 21: // remfuse
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			int func = args[0].get_function();
			Jetty.state.remove_daemon(func, -1, args[1]);
			return new TValue(TValue.NIL, 0);
		}
		case 22: // remdaemon
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			int func = args[0].get_function();
			Jetty.state.remove_daemon(func, -1, args[1]);
			return new TValue(TValue.NIL, 0);
		}
		case 23: // incturn
		{
			if (!expect_args(type, args.length, 0, 1))
				return new TValue(TValue.NIL, 0);
			int amount = 1;
			if (args.length == 1)
				amount = args[0].get_number();
			for (int i = 0; i < amount; i++)
				Jetty.state.burn_fuses(i < amount - 1);
			return new TValue(TValue.NIL, 0);
		}
		case 24: // quit
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			throw new GameOverException();
		}
		case 25: // save
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.TRUE, 0);
			Jetty.out.print_error("save() is not implemented yet", 2);
			return new TValue(TValue.TRUE, 0);
		}
		case 26: // restore
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.TRUE, 0);
			Jetty.out.print_error("restore() is not implemented yet", 2);
			return new TValue(TValue.NUMBER, Constants.RESTORE_FILE_NOT_FOUND);
		}
		case 27: // logging
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.TRUE, 0);
			Jetty.out.print_error("logging() is not implemented yet", 2);
			return new TValue(TValue.TRUE, 0);
		}
		case 28: // input
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			return new TValue(TValue.SSTRING, Jetty.in.read_line());
		}
		case 29: // setit
		{
			if (!expect_args(type, args.length, 1, 2))
				return new TValue(TValue.NIL, 0);
			if (args.length == 1 && args[0].get_type() == TValue.LIST) {
				Vector lst = new Vector();
				Vector arglst = args[0].get_list();
				for (int i = 0; i < arglst.size(); i++) {
					int o = ((TValue) arglst.elementAt(i)).get_object();
					lst.addElement(Jetty.state.lookup_object(o));
				}
				TObject[] them = new TObject[lst.size()];
				lst.copyInto(them);
				Jetty.state.set_them(them);
				Jetty.state.set_it(Constants.PO_IT, null);
			} else {
				int pronoun = Constants.PO_IT;
				if (args.length == 2) {
					int p = args[1].get_number();
					if (p == 0)
						pronoun = Constants.PO_IT;
					else if (p == 1)
						pronoun = Constants.PO_HIM;
					else if (p == 2)
						pronoun = Constants.PO_HER;
					else
						Jetty.out.print_error("setit() called with bad arg: " + p, 1);
				}
				TObject obj;
				if (args[0].get_type() == TValue.NIL)
					obj = null;
				else
					obj = Jetty.state.lookup_object(args[0].get_object());
				Jetty.state.set_it(pronoun, obj);
			}
			return new TValue(TValue.NIL, 0);
		}
		case 30: // askfile
		{
			if (!expect_args(type, args.length, 1, 3, 4))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("askfile() is not implemented yet", 2);
			if (args.length == 4 && args[3].get_number() == Constants.ASKFILE_EXT_RESULT)
				return new TValue(TValue.NUMBER, Constants.ASKFILE_FAILURE);
			else
				return new TValue(TValue.NIL, 0);
		}
		case 31: // setscore
		{
			if (!expect_args(type, args.length, 1, 2))
				return new TValue(TValue.NIL, 0);
			if (args.length == 1) {
				args[0].must_be(TValue.SSTRING);
				Jetty.out.print_statusline(false, args[0].get_string());
			} else {
				String points = Integer.toString(args[0].get_number());
				String turns = Integer.toString(args[1].get_number());
				Jetty.out.print_statusline(false, points + "/" + turns);
			}
			return new TValue(TValue.NIL, 0);
		}
		case 32: // firstobj
		{
			if (!expect_args(type, args.length, 0, 1))
				return new TValue(TValue.NIL, 0);
			int cls = -1;
			if (args.length == 1)
				cls = args[0].get_object();
			// Hayden Pratt noted this had < instead of <=
			for (int i = 0; i <= Jetty.state.get_last_object(); i++) {
				TObject obj = Jetty.state.lookup_object(i, false);
				if (obj != null && obj.is_object() && !obj.is_class() && (cls == -1 || obj.inherits_from(cls)))
					return new TValue(TValue.OBJECT, i);
			}
			return new TValue(TValue.NIL, 0);
		}
		case 33: // nextobj
		{
			if (!expect_args(type, args.length, 1, 2))
				return new TValue(TValue.NIL, 0);
			int cls = -1;
			// Jim Nelson pointed out this should be 2, not 1.
			if (args.length == 2)
				cls = args[1].get_object();
			for (int i = args[0].get_object() + 1; i <= Jetty.state.get_last_object(); i++) {
				TObject obj = Jetty.state.lookup_object(i, false);
				if (obj != null && obj.is_object() && !obj.is_class() && (cls == -1 || obj.inherits_from(cls)))
					return new TValue(TValue.OBJECT, i);
			}
			return new TValue(TValue.NIL, 0);
		}
		case 34: // isclass
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			TObject obj = Jetty.state.lookup_object(args[0].get_object());
			boolean is = obj.inherits_from(args[1].get_object());
			return new TValue(is ? TValue.TRUE : TValue.NIL, 0);
		}
		case 35: // restart
		{
			if (!expect_args(type, args.length, 0, 2))
				return new TValue(TValue.NIL, 0);
			Jetty.state.do_restart();
			if (args.length == 2) {
				TObject func = Jetty.state.lookup_object(args[0].get_function());
				Jetty.runner.run(func.get_data(), TObject.arg_array(args[1]), func);
			}
			// and rerun the init function:
			TObject init = Jetty.state.lookup_required_object(RequiredObjects.INIT);
			Jetty.runner.run(init.get_data(), TObject.arg_array(), init);
			throw new HaltTurnException(ParserError.RESTARTING);
		}
		case 36: // debugTrace
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			if (args[0].get_number() == 1) {
				if (args[0].get_logical())
					Jetty.set_debug_level(2);
				else
					Jetty.set_debug_level(1);
			}
			return new TValue(TValue.NIL, 0);
		}
		case 37: // undo
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			boolean ret = Jetty.state.do_undo();
			return new TValue((ret ? TValue.TRUE : TValue.NIL), 0);
		}
		case 38: // defined
		{
			if (!expect_args(type, args.length, 2, 3))
				return new TValue(TValue.NIL, 0);
			TObject obj = Jetty.state.lookup_object(args[0].get_object());
			int pid = args[1].get_property();
			int t = args.length == 3 ? args[2].get_number() : Constants.DEFINED_ANY;
			if (t == Constants.DEFINED_ANY) {
				TValue v = obj.lookup_property(pid);
				return new TValue((v != null) ? TValue.TRUE : TValue.NIL, 0);
			} else if (t == Constants.DEFINED_DIRECTLY) {
				TValue v = obj.lookup_local_property(pid);
				return new TValue((v != null) ? TValue.TRUE : TValue.NIL, 0);
			} else if (t == Constants.DEFINED_INHERITS) {
				TValue v = obj.lookup_property(pid, true, false);
				return new TValue((v != null) ? TValue.TRUE : TValue.NIL, 0);
			} else if (t == Constants.DEFINED_GET_CLASS) {
				TObject[] src = new TObject[1];
				TValue v = obj.lookup_property(pid, true, true, src);
				if (v == null)
					return new TValue(TValue.NIL, 0);
				else
					return new TValue(TValue.OBJECT, src[0].get_id());
			} else {
				Jetty.out.print_error("Illegal value '" + t + "' to defined()", 1);
				return new TValue(TValue.NIL, 0);
			}
		}
		case 39: // proptype
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			TObject obj = Jetty.state.lookup_object(args[0].get_object());
			int pid = args[1].get_property();
			TValue v = obj.lookup_property(pid);
			if (v == null)
				return new TValue(TValue.NUMBER, TValue.NIL);
			else
				return new TValue(TValue.NUMBER, v.get_type());
		}
		case 40: // outhide
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			args[0].must_be(TValue.TRUE, TValue.NIL, TValue.NUMBER);
			if (args[0].get_type() == TValue.TRUE) // start hiding
			{
				int k = Jetty.out.hide_output();
				return new TValue(TValue.NUMBER, k);
			} else {
				int k = (args[0].get_type() == TValue.NIL) ? 0 : args[0].get_number();
				boolean was_output = Jetty.out.unhide_output(k);
				return new TValue(was_output ? TValue.TRUE : TValue.NIL, 0);
			}
		}
		case 41: // runfuses
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			Jetty.state.run_daemons_and_fuses(false, true);
			return new TValue(TValue.NIL, 0);
		}
		case 42: // rundaemons
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			Jetty.state.run_daemons_and_fuses(true, false);
			return new TValue(TValue.NIL, 0);
		}
		case 43: // gettime
		{
			if (!expect_args(type, args.length, 0, 1))
				return new TValue(TValue.NIL, 0);
			if (args.length == 1 && args[0].get_number() == Constants.GETTIME_TICKS) {
				long time = (new Date()).getTime() - _start_date.getTime();
				return new TValue(TValue.NUMBER, (int) time);
			} else {
				Calendar c = new GregorianCalendar();
				Vector info = new Vector();
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.YEAR)));
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.MONTH) + 1));
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.DATE)));
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.DAY_OF_WEEK)));
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.DAY_OF_YEAR)));
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.HOUR_OF_DAY)));
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.MINUTE)));
				info.addElement(new TValue(TValue.NUMBER, c.get(Calendar.SECOND)));
				info.addElement(new TValue(TValue.NUMBER, (int) (c.getTime().getTime() / 1000)));
				return new TValue(TValue.LIST, info);
			}
		}
		case 44: // getfuse
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			int obj, prop;
			if (args[0].get_type() == TValue.FUNCTION) {
				prop = -1;
				obj = args[0].get_function();
			} else {
				obj = args[0].get_object();
				prop = args[1].get_property();
			}
			int count = Jetty.state.lookup_fuse(obj, prop, args[1]);
			if (count != -1)
				return new TValue(TValue.NUMBER, count);
			else
				return new TValue(TValue.NIL, 0);
		}
		case 45: // intersect
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			Vector lst1 = args[0].get_list();
			Vector lst2 = args[1].get_list();
			Vector lst = new Vector();
			for (int i = 0; i < lst1.size(); i++)
				if (lst2.contains(lst1.elementAt(i)))
					lst.addElement(((TValue) lst1.elementAt(i)).do_clone());
			return new TValue(TValue.LIST, lst);
		}
		case 46: // inputkey
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			return new TValue(TValue.SSTRING, Jetty.in.read_key());
		}
		case 47: // objwords
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			int n = args[0].get_number();
			if (n == 1 || n == 2) {
				ObjectMatch om = Jetty.state.get_command_match(n);
				if (om == null)
					return new TValue(TValue.LIST, new Vector());
				Vector words = om.get_words();
				Vector words_list = new Vector(words.size());
				for (int i = 0; i < words.size(); i++)
					words_list.addElement(new TValue(TValue.SSTRING, ((VocabWord) words.elementAt(i)).get_word()));
				return new TValue(TValue.LIST, words_list);
			} else {
				Jetty.out.print_error("objwords() received bad arg: " + n, 1);
				return new TValue(TValue.NIL, 0);
			}
		}
		case 48: // addword
		{
			if (!expect_args(type, args.length, 3))
				return new TValue(TValue.NIL, 0);
			int obj = args[0].get_object();
			int prop = args[1].get_property();
			String word = args[2].get_string();
			String word2 = null;
			int x = word.indexOf(' ');
			if (x != -1) {
				word2 = word.substring(x + 1);
				word = word.substring(0, x);
			}
			Jetty.state.add_vocab(obj, prop, 0, word, word2);
			Jetty.state.save_addword_undo(obj, prop, args[2].get_string());
			return new TValue(TValue.NIL, 0);
		}
		case 49: // delword
		{
			if (!expect_args(type, args.length, 3))
				return new TValue(TValue.NIL, 0);
			int obj = args[0].get_object();
			int prop = args[1].get_property();
			String word = args[2].get_string();
			String word2 = null;
			int x = word.indexOf(' ');
			if (x != -1) {
				word2 = word.substring(x + 1);
				word = word.substring(0, x);
			}
			Jetty.state.del_vocab(obj, prop, word, word2);
			Jetty.state.save_delword_undo(obj, type, args[2].get_string());
			return new TValue(TValue.NIL, 0);
		}
		case 50: // getwords
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			TObject obj = Jetty.state.lookup_object(args[0].get_object());
			int prop = args[1].get_property();
			Vector words = new Vector();
			Vector types = new Vector();
			Vector word2s = new Vector();
			Vector vocab = obj.get_vocab();
			for (int i = 0; i < vocab.size(); i++) {
				VocabWord vw = (VocabWord) vocab.elementAt(i);
				vw.get_object(obj.get_id(), types, word2s);
				for (int j = 0; j < types.size(); j++) {
					if (((Integer) types.elementAt(j)).intValue() == prop) {
						String w2 = (String) word2s.elementAt(j);
						if (w2 == null)
							words.addElement(new TValue(TValue.SSTRING, vw.get_word()));
						else
							words.addElement(new TValue(TValue.SSTRING, vw.get_word() + " " + w2));
					}
				}
				types.removeAllElements();
				word2s.removeAllElements();
			}
			return new TValue(TValue.LIST, words);
		}
		case 51: // nocaps
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print("\\v");
			return new TValue(TValue.NIL, 0);
		}
		case 52: // skipturn
		{
			if (!expect_args(type, args.length, 0, 1))
				return new TValue(TValue.NIL, 0);
			int amount = 1;
			if (args.length == 1)
				amount = args[0].get_number();
			for (int i = 0; i < amount; i++)
				Jetty.state.burn_fuses(false);
			return new TValue(TValue.NIL, 0);
		}
		case 53: // clearscreen
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			Jetty.out.clear_screen();
			return new TValue(TValue.NIL, 0);
		}
		case 54: // firstsc
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			TObject obj = Jetty.state.lookup_object(args[0].get_object());
			int[] scs = obj.get_superclasses();
			if (scs.length > 0)
				return new TValue(TValue.OBJECT, scs[0]);
			else
				return new TValue(TValue.NIL, 0);
		}
		case 55: // verbinfo
		{
			if (!expect_args(type, args.length, 1, 2))
				return new TValue(TValue.NIL, 0);
			Vector info = new Vector();
			TObject verb = Jetty.state.lookup_object(args[0].get_object());
			TObject prep = null;
			if (args.length == 2)
				prep = Jetty.state.lookup_object(args[1].get_object());
			int[] template = null;
			try {
				template = Jetty.simulator.pick_template(verb, true, (prep != null), prep);
			} catch (HaltTurnException hte) {
				template = null; // and just ignore the error
			}
			if (args.length == 1) {
				if (template == null || template[3] == 0)
					info.addElement(new TValue(TValue.NIL, 0));
				else
					info.addElement(new TValue(TValue.PROPERTY, template[3]));
				if (template == null || template[4] == 0)
					info.addElement(new TValue(TValue.NIL, 0));
				else
					info.addElement(new TValue(TValue.PROPERTY, template[4]));
			} else {
				if (template == null || template[3] == 0)
					info.addElement(new TValue(TValue.NIL, 0));
				else
					info.addElement(new TValue(TValue.PROPERTY, template[3]));
				if (template == null || template[4] == 0)
					info.addElement(new TValue(TValue.NIL, 0));
				else
					info.addElement(new TValue(TValue.PROPERTY, template[4]));
				if (template == null || template[1] == 0)
					info.addElement(new TValue(TValue.NIL, 0));
				else
					info.addElement(new TValue(TValue.PROPERTY, template[1]));
				if (template == null || template[2] == 0)
					info.addElement(new TValue(TValue.NIL, 0));
				else
					info.addElement(new TValue(TValue.PROPERTY, template[4]));
				boolean dobj_first = (template != null) && ((template[5] & 0x01) != 0);
				info.addElement(new TValue(dobj_first ? TValue.TRUE : TValue.NIL, 0));
			}
			return new TValue(TValue.LIST, info);
		}
		case 56: // fopen
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("fopen() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 57: // fclose
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("fclose() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 58: // fwrite
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.TRUE, 0);
			Jetty.out.print_error("fwrite() is not implemented yet", 2);
			return new TValue(TValue.TRUE, 0);
		}
		case 59: // fread
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("fread() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 60: // fseek
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("fseek() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 61: // fseekeof
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("fseekeof() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 62: // ftell
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("ftell() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 63: // outcapture
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			args[0].must_be(TValue.TRUE, TValue.NUMBER);
			if (args[0].get_type() == TValue.TRUE) // start hiding
			{
				int k = Jetty.out.capture_output();
				return new TValue(TValue.NUMBER, k);
			} else {
				int k = args[0].get_number();
				return new TValue(TValue.SSTRING, Jetty.out.uncapture_output(k));
			}
		}
		case 64: // systemInfo
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			int code = args[0].get_number();
			// we do support sysinfo():
			if (code == 1) // __SYSINFO_SYSINFO
				return new TValue(TValue.TRUE, 0);
			// it's not really clear what version we support, but mrf.
			else if (code == 2) // __SYSINFO_VERSION
				return new TValue(TValue.SSTRING, "2.5.5");
			// I dunno, what OS are we?
			else if (code == 3) // __SYSINFO_OS_NAME
				return new TValue(TValue.SSTRING, "Java VM");
			// for all other queries (which are basically html-mode queries)
			// the answer is "no, we don't support it"
			else
				return new TValue(TValue.NUMBER, 0);
		}
		case 65: // morePrompt
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_more_prompt();
			return new TValue(TValue.NIL, 0);
		}
		case 66: // parserSetMe
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			args[0].must_be(TValue.OBJECT);
			if (args[0].get_object() != Jetty.state.get_parser_me().get_id()) {
				Jetty.state.save_setme_undo(Jetty.state.get_parser_me());
				TObject me = Jetty.state.lookup_object(args[0].get_object());
				Jetty.state.set_parser_me(me);
			}
			return new TValue(TValue.NIL, 0);
		}
		case 67: // parserGetMe
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			return new TValue(TValue.OBJECT, Jetty.state.get_parser_me().get_id());
		}
		case 68: // reSearch
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			String rex = args[0].get_string();
			String text = args[1].get_string();
			// Now, tads regexs are basically like POSIX, only with %s instead
			// of \s. Why? just to make my life harder, I think. So we need to
			// reverse this before feeding them in to the regexp package.
			StringBuffer rb = new StringBuffer(rex);
			for (int i = 0; i < rb.length(); i++) {
				if (rb.charAt(i) == '%') {
					rb.setCharAt(i, '\\');
					// advance pointer to what was being escaped so it'll be skipped
					i++;
					// annoying hack for different regexp syntaxes:
					if (rb.charAt(i) == '<' || rb.charAt(i) == '>')
						rb.setCharAt(i, 'b');
				} else if (rb.charAt(i) == '\\') {
					rb.insert(i, '\\');
					i++; // skip over the new /
				}
			}
			rex = rb.toString();
			//RE re = null;
			Pattern re = null;
			//			try {
			//re = new RE(rex);
			//			} catch (RESyntaxException rese) {
			//				Jetty.out.print_error("Bad regexp syntax: \"" + rex + "\"", 1);
			//				re = null;
			//			}
			re = Pattern.compile(rex);
			Matcher matcher = re.matcher(text);
			//			if (re == null || !re.match(text))
			//				return new TValue(TValue.NIL, 0);
			//			// create the list to be returned:
			//			Vector ret = new Vector(3);
			//			ret.addElement(new TValue(TValue.NUMBER, re.getParenStart(0)));
			//			ret.addElement(new TValue(TValue.NUMBER, re.getParenLength(0)));
			//			ret.addElement(new TValue(TValue.SSTRING, re.getParen(0)));
			Vector ret = new Vector(3);
			int i = 0;
			if (matcher.find()) {
				//				Vector ret = new Vector(3);
				//ret.addElement(new TValue(TValue.NUMBER, re.getParenStart(0)));
				ret.addElement(new TValue(TValue.NUMBER, matcher.start(0)));
				//				ret.addElement(new TValue(TValue.NUMBER, re.getParenLength(0)));
				ret.addElement(new TValue(TValue.NUMBER, matcher.end(0) - matcher.start(0)));
				//ret.addElement(new TValue(TValue.SSTRING, re.getParen(0)));
				ret.addElement(new TValue(TValue.SSTRING, matcher.group(0)));
				i++;
			} else {
				return new TValue(TValue.NIL, 0);
			}
			// and set up and store away all the submatch lists:
			//			if (re.getParenCount() > 0) {
			//				Vector[] groups = new Vector[re.getParenCount()];
			List<TValue> groups = new ArrayList<TValue>();
			while (matcher.find()) {
				//					for (int i = 0; i < groups.size(); i++) {
				//groups[i] = new Vector(3);
				//						groups[i].addElement(new TValue(TValue.NUMBER, re.getParenStart(i + 1)));
				groups.add(new TValue(TValue.NUMBER, matcher.start(i + 1)));
				//						groups[i].addElement(new TValue(TValue.NUMBER, re.getParenLength(i + 1)));
				groups.add(new TValue(TValue.NUMBER, matcher.end(i + 1) - matcher.start(i + 1)));
				//						groups[i].addElement(new TValue(TValue.SSTRING, re.getParen(i + 1)));
				groups.add(new TValue(TValue.SSTRING, matcher.group(i + 1)));
				//					}
				Jetty.state.set_rex_groups(TadsUtils.listToVectorList(groups));
				i++;
			}
			//			} else
			//				Jetty.state.set_rex_groups(null);
			return new TValue(TValue.LIST, ret);
		}
		case 69: // reGetGroup
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			int idx = args[0].get_number();
			Vector[] groups = Jetty.state.get_rex_groups();
			if (idx < 1 || idx > groups.length)
				return new TValue(TValue.NIL, 0);
			else
				return new TValue(TValue.LIST, groups[idx - 1]);
		}
		case 70: // inputevent
		{
			if (!expect_args(type, args.length, 0, 1))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("inputevent() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 71: // timeDelay
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			int t = args[0].get_number();
			try {
				Thread.sleep(t);
			} catch (InterruptedException ie) {
				; // uh, s'fine, I guess.
			}
			return new TValue(TValue.NIL, 0);
		}
		case 72: // setOutputFilter
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			args[0].must_be(TValue.NIL, TValue.FUNCTION);
			if (args[0].get_type() == TValue.FUNCTION) {
				TObject func = Jetty.state.lookup_object(args[0].get_function());
				Jetty.out.set_filter(func);
			} else {
				Jetty.out.set_filter(null);
			}
			return new TValue(TValue.NIL, 0);
		}
		case 73: // execCommand
		{
			if (!expect_args(type, args.length, 2, 3, 4, 5, 6))
				return new TValue(TValue.NIL, 0);
			TObject actor = Jetty.state.lookup_object(args[0].get_object());
			TObject verb = Jetty.state.lookup_object(args[1].get_object());
			TObject dobj = null;
			TObject prep = null;
			TObject iobj = null;
			int flags = 0;
			int[] template = null;
			if (args.length >= 3) {
				if (args[2].get_type() == TValue.OBJECT)
					dobj = Jetty.state.lookup_object(args[2].get_object());
				else if (args[2].get_type() == TValue.NIL)
					dobj = null;
				else {
					if (!expect_args(type, args.length, 3))
						return new TValue(TValue.NIL, 0);
					flags = args[2].get_number();
				}
			}
			if (args.length >= 4) {
				if (args[3].get_type() == TValue.OBJECT)
					prep = Jetty.state.lookup_object(args[3].get_object());
				else if (args[3].get_type() == TValue.NIL)
					prep = null;
				else {
					if (!expect_args(type, args.length, 4))
						return new TValue(TValue.NIL, 0);
					flags = args[3].get_number();
				}
			}
			if (args.length >= 5) {
				if (args[4].get_type() == TValue.OBJECT)
					iobj = Jetty.state.lookup_object(args[4].get_object());
				else if (args[4].get_type() == TValue.NIL)
					iobj = null;
				else {
					if (!expect_args(type, args.length, 5))
						return new TValue(TValue.NIL, 0);
					flags = args[4].get_number();
				}
			}
			if (args.length == 6)
				flags = args[5].get_number();
			try {
				template = Jetty.simulator.pick_template(verb, (dobj != null), (iobj != null), prep);
			} catch (HaltTurnException hte) {
				template = null; // and just ignore this, and we give the error below
			}
			if (template == null || (template[3] > 0 && dobj == null) || (template[1] > 0 && iobj == null))
				return new TValue(TValue.NUMBER, Constants.EC_INVAL_SYNTAX);
			int status;
			try {
				boolean do_valid_do = ((flags & Constants.EC_SKIP_VALIDDO) == 0);
				boolean do_valid_io = ((flags & Constants.EC_SKIP_VALIDIO) == 0);
				int hide = ((flags & Constants.EC_HIDE_SUCCESS) != 0) ? Jetty.out.hide_output() : -1;
				status = Jetty.simulator.do_execute(template, actor, verb, dobj, prep, iobj, true, do_valid_do, do_valid_io);
				if ((flags & Constants.EC_HIDE_SUCCESS) != 0)
					Jetty.out.unhide_output(hide);
			} catch (HaltTurnException hte) {
				if ((flags & Constants.EC_HIDE_ERROR) == 0)
					Jetty.perror.print(hte.get_errnum(), hte.get_errstr());
				status = hte.get_errnum();
			}
			return new TValue(TValue.NUMBER, status);
		}
		case 74: // parserGetObj
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			int which = args[0].get_number();
			if (which >= Constants.PO_ACTOR && which <= Constants.PO_IOBJ) {
				TObject t = Jetty.state.get_command_object(which);
				if (t == null)
					return new TValue(TValue.NIL, 0);
				else
					return new TValue(TValue.OBJECT, t.get_id());
			} else if (which >= Constants.PO_IT && which <= Constants.PO_HER) {
				TObject t = Jetty.state.lookup_it(which);
				if (t == null)
					return new TValue(TValue.NIL, 0);
				else
					return new TValue(TValue.OBJECT, t.get_id());
			} else if (which == Constants.PO_THEM) {
				TObject[] ts = Jetty.state.lookup_them();
				Vector v = new Vector(ts.length);
				for (int i = 0; i < ts.length; i++)
					v.addElement(ts[i]);
				return new TValue(TValue.LIST, v);
			} else {
				Jetty.out.print_error("Argument to parserGetObj out of range: " + which, 1);
				return new TValue(TValue.NIL, 0);
			}
		}
		case 75: // parseNounList
		{
			if (!expect_args(type, args.length, 6))
				return new TValue(TValue.NIL, 0);
			Vector words = args[0].get_list();
			Vector types = args[1].get_list();
			if (words.size() != types.size()) {
				Jetty.out.print_error("lists to parseNounList() must be the " + "same length", 1);
				return new TValue(TValue.NIL, 0);
			}
			Vector command = new Vector(words.size());
			for (int i = 0; i < words.size(); i++) {
				String word = ((TValue) words.elementAt(i)).get_string();
				command.addElement(Jetty.state.lookup_vocab(word));
			}
			int[] tarr = new int[types.size()];
			for (int i = 0; i < types.size(); i++)
				tarr[i] = ((TValue) types.elementAt(i)).get_number();
			// skip everything up to this point (but remember tads lists are
			// 1-indexed)
			int start_index = args[2].get_number() - 1;
			// complain if there's no objects that match all the vocab words:
			boolean complain_on_no_match = args[3].get_logical();
			// parse several ('and'-separated) noun phrases:
			boolean allow_multi = args[4].get_logical();
			// doing an actor check (forces complain and allow_multi to false;
			// also disallows "all", "both", "any", and quoted strings
			boolean actor_check = args[5].get_logical();
			try {
				ObjectMatch[] matches = Jetty.parser.parse_noun_phrase(command, start_index, null, tarr, allow_multi, complain_on_no_match, actor_check);
				Vector ret = new Vector();
				if (matches == null)
					ret.addElement(new TValue(TValue.NUMBER, start_index));
				else {
					for (int i = 0; i < matches.length; i++) {
						if (matches[i].num_matches() > 0) {
							Vector v = new Vector();
							int from = start_index;
							int to = start_index + matches[i].get_words().size();
							ObjectMatch[] excepts = matches[i].get_except();
							if (excepts != null) {
								to++; // for the "except"
								for (int j = 0; j < excepts.length; j++)
									to += excepts[j].get_size();
							}
							v.addElement(new TValue(TValue.NUMBER, from));
							v.addElement(new TValue(TValue.NUMBER, to));
							Vector objs = matches[i].get_matches();
							for (int j = 0; j < objs.size(); j++) {
								int id = ((TObject) objs.elementAt(j)).get_id();
								int flag = matches[i].get_match_flag(j);
								v.addElement(new TValue(TValue.OBJECT, id));
								v.addElement(new TValue(TValue.NUMBER, flag));
							}
							if (excepts != null) {
								for (int j = 0; j < excepts.length; j++) {
									Vector exc = excepts[j].get_matches();
									for (int k = 0; k < exc.size(); k++) {
										int id = ((TObject) exc.elementAt(k)).get_id();
										int flag = excepts[j].get_match_flag(k);
										v.addElement(new TValue(TValue.OBJECT, id));
										v.addElement(new TValue(TValue.NUMBER, flag | Constants.PRSFLG_EXCEPT));
									}
								}
							}
							ret.addElement(new TValue(TValue.LIST, v));
						}
						// increase the total counter by the size of this match
						// note that this size includes extraneous words like
						// 'and' which are not included in the sublist range numbers
						start_index += matches[i].get_size();
					}
					ret.insertElementAt(new TValue(TValue.NUMBER, start_index), 0);
				}
				return new TValue(TValue.LIST, ret);
			} catch (HaltTurnException hte) {
				Jetty.perror.print(hte.get_errnum(), hte.get_errstr());
				return new TValue(TValue.NIL, 0);
			}
		}
		case 76: // parserTokenize
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Vector v = Jetty.parser.tokenize_str(args[0].get_string());
			Vector words = new Vector(v.size());
			for (int i = 0; i < v.size(); i++) {
				String s = ((VocabWord) v.elementAt(i)).get_word();
				words.addElement(new TValue(TValue.SSTRING, s));
			}
			return new TValue(TValue.LIST, words);
		}
		case 77: // parserGetTokTypes
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Vector v = args[0].get_list();
			Vector ret = new Vector(v.size());
			for (int i = 0; i < v.size(); i++) {
				VocabWord vw = Jetty.state.lookup_vocab(((TValue) v.elementAt(i)).get_string());
				int flags = vw.get_flags();
				ret.addElement(new TValue(TValue.NUMBER, flags));
			}
			return new TValue(TValue.LIST, ret);
		}
		case 78: // parserDictLookup
		{
			if (!expect_args(type, args.length, 2))
				return new TValue(TValue.NIL, 0);
			Vector words = args[0].get_list();
			Vector types = args[1].get_list();
			if (words.size() != types.size()) {
				Jetty.out.print_error("lists to parserDictLookup() must be the " + "same length", 1);
				return new TValue(TValue.NIL, 0);
			}
			ObjectMatch om = new ObjectMatch(0);
			for (int i = 0; i < words.size(); i++) {
				String word = ((TValue) words.elementAt(i)).get_string();
				String word2 = null;
				int sp = word.indexOf(' ');
				if (sp != -1) {
					word2 = word.substring(sp + 1);
					word = word.substring(0, sp);
				}
				VocabWord vw = Jetty.state.lookup_vocab(word);
				// if no matches, then return an empty list:
				if (vw.is_unknown())
					return new TValue(TValue.LIST, new Vector());
				// otherwise, intersect with the appropriate types of obj:
				om.add_word(vw, word2, ((TValue) types.elementAt(i)).get_number());
			}
			Vector matches = om.get_matches();
			// convert this into a list of tvalues and return it:
			Vector ret = new Vector(matches.size());
			for (int i = 0; i < matches.size(); i++)
				ret.addElement(new TValue(TValue.OBJECT, ((TObject) matches.elementAt(i)).get_id()));
			return new TValue(TValue.LIST, ret);
		}
		case 79: // parserResolveObjects
		{
			if (!expect_args(type, args.length, 9))
				return new TValue(TValue.NIL, 0);
			TObject actor = Jetty.state.lookup_object(args[0].get_object());
			TObject verb = Jetty.state.lookup_object(args[1].get_object());
			TObject prep = null;
			if (args[2].get_type() == TValue.OBJECT)
				prep = Jetty.state.lookup_object(args[2].get_object());
			else
				args[2].must_be(TValue.NIL);
			TObject other = null;
			if (args[3].get_type() == TValue.OBJECT)
				other = Jetty.state.lookup_object(args[3].get_object());
			else
				args[3].must_be(TValue.NIL);
			int resolve_type = args[4].get_number();
			int prop = args[5].get_property();
			Vector match_words = args[6].get_list();
			Vector match_objs = args[7].get_list();
			ObjectMatch[] match = new ObjectMatch[1];
			match[0] = new ObjectMatch(0);
			int general_flags = 0;
			for (int i = 1; i < match_objs.size(); i++) {
				Vector v = ((TValue) match_objs.elementAt(i)).get_list();
				for (int j = 2; j < v.size() - 1; j += 2) {
					int obj = ((TValue) v.elementAt(j)).get_object();
					int flags = ((TValue) v.elementAt(j + 1)).get_number();
					// put some flags on the match-in-general thing
					general_flags |= (flags & (Constants.PRSFLG_ALL | Constants.PRSFLG_IT | Constants.PRSFLG_THEM | Constants.PRSFLG_COUNT | Constants.PRSFLG_PLURAL | Constants.PRSFLG_ANY | Constants.PRSFLG_HIM | Constants.PRSFLG_HER));
					// only keep some of the flags on the individual object
					flags &= (Constants.PRSFLG_ENDADJ | Constants.PRSFLG_TRUNC);
					match[0].add_object(Jetty.state.lookup_object(obj), flags);
				}
			}
			match[0].set_flags(general_flags);
			if (match[0].test_flags(Constants.PRSFLG_COUNT)) {
				String s = ((TValue) match_words.elementAt(0)).get_string();
				int c = 0;
				try {
					c = Integer.parseInt(s);
				} catch (NumberFormatException nfe) {
					Jetty.out.print_error("COUNT flag set in parserResolveObjects()," + " but first word not a number: '" + s + "'", 1);
					c = 1;
				}
				match[0].set_count(c);
				match_words.removeElementAt(0);
			}
			for (int i = 0; i < match_words.size(); i++) {
				String s = ((TValue) match_words.elementAt(i)).get_string();
				match[0].append_word(Jetty.state.lookup_vocab(s));
			}
			boolean silent = args[8].get_logical();
			int[] status = new int[1];
			// ok, all the parameters are done (pant, pant).
			// onto the actual call:
			Vector ret = new Vector();
			try {
				TObject[] objs = Jetty.simulator.do_disambig(resolve_type, match, prop, actor, verb, prep, other, silent, status, null);
				ret.addElement(new TValue(TValue.NUMBER, status[0]));
				for (int i = 0; i < objs.length; i++)
					ret.addElement(new TValue(TValue.OBJECT, objs[i].get_id()));
			} catch (HaltTurnException hte) {
				if (hte.get_errnum() < 0)
					throw hte;
				else
					ret.addElement(new TValue(TValue.NUMBER, hte.get_errnum()));
			} catch (ReparseException re) {
				ret.addElement(new TValue(TValue.NUMBER, ParserError.DISAMBIG_RETRY));
				String s = "";
				Vector v = re.get_tokens();
				for (int i = 0; i < v.size(); i++)
					s += (i == 0 ? "" : " ") + ((VocabWord) v.elementAt(i)).get_word();
				ret.addElement(new TValue(TValue.SSTRING, s));
			}
			return new TValue(TValue.LIST, ret);
		}
		case 80: // parserReplaceCommand
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			Vector toks = Jetty.parser.tokenize_str(args[0].get_string());
			throw new ReparseException(toks);
		}
		case 81: // exitobj
		{
			if (!expect_args(type, args.length, 0))
				return new TValue(TValue.NIL, 0);
			throw new ExitobjException();
		}
		case 82: // inputdialog
		{
			if (!expect_args(type, args.length, 5))
				return new TValue(TValue.NIL, 0);
			Jetty.out.print_error("inputdialog() is not implemented yet", 2);
			return new TValue(TValue.NIL, 0);
		}
		case 83: // resourceExists
		{
			if (!expect_args(type, args.length, 1))
				return new TValue(TValue.NIL, 0);
			return new TValue(TValue.NIL, 0);
		}
		default: {
			Jetty.out.print_error("Unknown built-in function: " + type, 1);
			throw new GameOverException();
		}
		}
	}

	private static String[] _names = { "say", "car", "cdr", "length", "randomize", "rand", "substr", "cvtstr", "cvtnum", "upper", "lower", "caps", "find", "getarg", "datatype", "setdaemon", "setfuse", "setversion", "notify", "unnotify", "yorn", "remfuse", "remdaemon", "incturn", "quit", "save", "restore", "logging", "input", "setit", "askfile", "setscore", "firstobj", "nextobj", "isclass", "restart", "debugTrace", "undo", "defined", "proptype", "outhide", "runfuses", "rundaemons", "gettime", "getfuse", "intersect", "inputkey", "objwords", "addword", "delword", "getwords", "nocaps", "skipturn", "clearscreen", "firstsc", "verbinfo", "fopen", "fclose", "fwrite", "fread", "fseek", "fseekeof", "ftell", "outcapture", "systemInfo", "morePrompt", "parserSetMe", "parserGetMe", "reSearch", "reGetGroup", "inputevent", "timeDelay", "setOutputFilter", "execCommand", "parserGetObj", "parseNounList", "parserTokenize", "parserGetTokTypes", "parserDictLookup", "parserResolveObjects", "parserReplaceCommand", "exitobj", "inputdialog", "resourceExists" };

	private static boolean expect_args(int type, int given, int cnt1) {
		return expect_args(type, given, cnt1, -1, -1, -1, -1);
	}

	private static boolean expect_args(int type, int given, int cnt1, int cnt2) {
		return expect_args(type, given, cnt1, cnt2, -1, -1, -1);
	}

	private static boolean expect_args(int type, int given, int cnt1, int cnt2, int cnt3) {
		return expect_args(type, given, cnt1, cnt2, cnt3, -1, -1);
	}

	private static boolean expect_args(int type, int given, int cnt1, int cnt2, int cnt3, int cnt4) {
		return expect_args(type, given, cnt1, cnt2, cnt3, cnt4, -1);
	}

	private static boolean expect_args(int type, int given, int cnt1, int cnt2, int cnt3, int cnt4, int cnt5) {
		if (given != cnt1 && given != cnt2 && given != cnt3 && given != cnt4 && given != cnt5) {
			Jetty.out.print_error("Bad number of args to " + _names[type] + "()", 1);
			return false;
		} else
			return true;
	}

	private BuiltIns() {
	} // shouldn't ever be called
}
