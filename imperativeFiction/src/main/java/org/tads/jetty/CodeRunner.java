package org.tads.jetty;

import java.util.Stack;
import java.util.Vector;

public class CodeRunner {

	public CodeRunner() {
	}

	public TValue run(byte[] code, TValue[] args, TObject self) throws ParseException, ReparseException, HaltTurnException, GameOverException {
		return run(code, args, self, null);
	}

	// the prop_obj here is the object that actually contains the property 
	// with the code being executed, as opposed to the self object, which is
	// the, well, object which is executing the code
	//
	// also note we have a new copy-on-write policy for args; if the callee
	// wants to change them it should clone them, not edit them in-place.
	// therefore, don't clone them when passing them to a function.
	public TValue run(byte[] code, TValue[] args, TObject self, TObject prop_obj) throws ParseException, ReparseException, HaltTurnException, GameOverException {
		Stack stack = new Stack();
		TValue[] locals = null; // will be filled in by ENTER opcode which knows
								// how many locals there are.
		int p = 0;
		while (p < code.length) {
			int opcode = code[p++] & 0xFF;
			if (Jetty.get_debug_level() >= 4) {
				Jetty.out.print_error("opc=" + opcode, 4);
			}
			switch (opcode) {
			case 1: /* OPCPUSHNUM: push a constant numeric value */
			{
				TValue tv = new TValue(TValue.NUMBER, code, p, p + 4);
				p += 4;
				stack.push(tv);
			}
				break;
			case 2: /* OPCPUSHOBJ: push an object */
			{
				TValue tv = new TValue(TValue.OBJECT, code, p, p + 2);
				p += 2;
				stack.push(tv);
			}
				break;
			case 3: /* OPCNEG: unary negation */
			{
				stack.push(new TValue(TValue.NUMBER, -((TValue) stack.pop()).get_number()));
			}
				break;
			case 4: /* OPCNOT: logical negation */
			{
				if (!((TValue) stack.pop()).get_logical())
					stack.push(new TValue(TValue.TRUE, 0));
				else
					stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 5: /* OPCADD: addition/list concatenation */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				stack.push(do_add(arg1, arg2));
			}
				break;
			case 6: /* OPCSUB: subtraction/list difference */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				stack.push(do_sub(arg1, arg2));
			}
				break;
			case 7: /* OPCMUL: multiplication */
			{
				int val2 = ((TValue) stack.pop()).get_number();
				int val1 = ((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, (val1 * val2)));
			}
				break;
			case 8: /* OPCDIV: division */
			{
				int val2 = ((TValue) stack.pop()).get_number();
				int val1 = ((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, (val1 / val2)));
			}
				break;
			case 9: /* OPCAND: logical AND */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 10: /* OPCOR: logical OR */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 11: /* OPCEQ: equality */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.equals(arg2))
					stack.push(new TValue(TValue.TRUE, 0));
				else
					stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 12: /* OPCNE: inequality */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (!arg1.equals(arg2))
					stack.push(new TValue(TValue.TRUE, 0));
				else
					stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 13: /* OPCGT: greater than */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) > 0)
					stack.push(new TValue(TValue.TRUE, 0));
				else
					stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 14: /* OPCGE: greater or equal */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) >= 0)
					stack.push(new TValue(TValue.TRUE, 0));
				else
					stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 15: /* OPCLT: less than */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) < 0)
					stack.push(new TValue(TValue.TRUE, 0));
				else
					stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 16: /* OPCLE: less or equal */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) <= 0)
					stack.push(new TValue(TValue.TRUE, 0));
				else
					stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 17: /* OPCCALL: call a function */
			{
				int argc = GameFileParser.read_8(code, p++);
				int func = GameFileParser.read_16(code, p);
				p += 2;
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				TObject fobj = Jetty.state.lookup_object(func);
				if (fobj != null) {
					if (Jetty.get_debug_level() >= 3) {
						Jetty.out.print_error("Calling function: " + func, 3);
					}
					byte[] newcode = fobj.get_data();
					if (newcode != null)
						stack.push(run(newcode, arglist, fobj));
				} else
					Jetty.out.print_error("Error, no such function with id " + func, 1);
			}
				break;
			case 18: /* OPCGETP: get property */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = GameFileParser.read_16(code, p);
				p += 2;
				TObject obj = Jetty.state.lookup_object(((TValue) stack.pop()).get_object());
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(obj.eval_property(prop, arglist));
			}
				break;
			case 19: /* OPCGETPDATA: get a property, allowing only data values */
			{
				// debug-only, ignore
				p += 2;
			}
				break;
			case 20: /* OPCGETLCL: get a local variable's value */
			{
				int lcl = GameFileParser.read_16signed(code, p);
				if (lcl >= 0)
					stack.push(locals[lcl - 1]);
				else
					stack.push(args[(lcl * -1) - 1]);
				p += 2;
			}
				break;
			case 21: /* OPCPTRGETPDATA: get property via pointer; only allow data */
			{
				// debug-only, ignore
				;
			}
				break;
			case 22: /* OPCRETURN: return without a value */
			case 23: /* OPCRETVAL: return a value */
			{
				// nothing really to clean up here. just return, I guess.
				if (Jetty.get_debug_level() >= 3) {
					Jetty.out.print_error("returning", 3);
				}
				// the code claims that if the stack is empty when you're evaluating
				// RETVAL, you return nil. This seems bogus to me; I can't see
				// when you'd ever have an empty stack. But what the hey.
				if (opcode == 22 || stack.empty())
					return new TValue(TValue.NIL, 0);
				else
					return (TValue) stack.pop();
			}
			//	break;
			case 24: /* OPCENTER: enter a function */
			{
				// if we were using one large stack we'd put a marker (the baseptr)
				// in at this point to say "hey, when you're done with this function,
				// discard to here". but since we're using a new stack per
				// function this isn't necessary. 
				// we could load the passed-in args onto this stack, but I think
				// we'll just special-case accessing them in GETLCL.
				// we do, however, still have to set up the locals array:
				locals = new TValue[GameFileParser.read_16(code, p)];
				for (int i = 0; i < locals.length; i++)
					locals[i] = new TValue(TValue.NIL, 0);
				p += 2; // and bump this along
			}
				break;
			case 25: /* OPCDISCARD: discard top of stack */
			{
				stack.removeAllElements();
			}
				break;
			case 26: /* OPCJMP: unconditional jump */
			{
				p += GameFileParser.read_16signed(code, p);
			}
				break;
			case 27: /* OPCJF: jump if false */
			{
				if (!((TValue) stack.pop()).get_logical())
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 28: /* OPCPUSHSELF: push current object */
			{
				TValue tv = new TValue(TValue.OBJECT, self.get_id());
				stack.push(tv);
			}
				break;
			case 29: /* OPCSAY: implicit printout for doublequote strings */
			{
				int slen = GameFileParser.read_16(code, p) - 2;
				String s = new String(code, p + 2, slen);
				Jetty.out.print(s);
				p += 2 + slen;
				stack.push(new TValue(TValue.NIL, 0));
			}
				break;
			case 30: /* OPCBUILTIN: call a built-in function */
			{
				int argc = GameFileParser.read_8(code, p++);
				int builtin = GameFileParser.read_16(code, p);
				p += 2;
				TValue[] arglist = new TValue[argc];
				// note that we do *not* copy the arguments here, and hence
				// rely on the builtin functions to not modify the arguments
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(BuiltIns.run(builtin, arglist, args));
			}
				break;
			case 31: /* OPCPUSHSTR: push a string */
			{
				int len = GameFileParser.read_16(code, p) - 2;
				p += 2;
				TValue tv = new TValue(TValue.SSTRING, code, p, p + len);
				p += len;
				stack.push(tv);
			}
				break;
			case 32: /* OPCPUSHLST: push a list */
			{
				int len = GameFileParser.read_16(code, p) - 2;
				p += 2;
				TValue tv = new TValue(TValue.LIST, code, p, p + len);
				p += len;
				stack.push(tv);
			}
				break;
			case 33: /* OPCPUSHNIL: push the NIL value */
			{
				TValue tv = new TValue(TValue.NIL, 0);
				stack.push(tv);
			}
				break;
			case 34: /* OPCPUSHTRUE: push the TRUE value */
			{
				TValue tv = new TValue(TValue.TRUE, 0);
				stack.push(tv);
			}
				break;
			case 35: /* OPCPUSHFN: push the address of a function */
			{
				TValue tv = new TValue(TValue.FUNCTION, code, p, p + 2);
				p += 2;
				stack.push(tv);
			}
				break;
			case 36: /* OPCGETPSELFDATA: push property of self; only allow data */
			{
				// debug-only, ignore
				p += 2;
			}
				break;
			case 38: /* OPCPTRCALL: call function pointed to by top of stack */
			{
				int argc = GameFileParser.read_8(code, p++);
				int func = ((TValue) stack.pop()).get_function();
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				TObject fobj = Jetty.state.lookup_object(func);
				if (fobj != null) {
					if (Jetty.get_debug_level() >= 3) {
						Jetty.out.print_error("Calling function: " + func, 3);
					}
					byte[] newcode = fobj.get_data();
					if (newcode != null)
						stack.push(run(newcode, arglist, fobj));
				} else
					Jetty.out.print_error("Error, no such function with id " + func, 1);
			}
				break;
			case 39: /* OPCPTRINH: inherit pointer to property (stack=prop) */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = ((TValue) stack.pop()).get_property();
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(prop_obj.eval_property(self, prop, arglist, false));
			}
				break;
			case 40: /* OPCPTRGETP: get property by pointer (stack=obj,prop) */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = ((TValue) stack.pop()).get_property();
				TObject obj = Jetty.state.lookup_object(((TValue) stack.pop()).get_object());
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(obj.eval_property(prop, arglist));
			}
				break;
			case 41: /* OPCPASS: pass to inherited handler */
			{
				int prop = GameFileParser.read_16(code, p);
				p += 2;
				return prop_obj.eval_property(self, prop, args, false);
			}
			//	break;
			case 42: /* OPCEXIT: exit turn, but continue with fuses/daemons */
			{
				throw new ExitException();
			}
			case 43: /* OPCABORT: abort turn, skipping fuses/daemons */
			{
				throw new AbortException();
			}
			case 44: /* OPCASKDO: ask for a direct object */
			{
				throw new AskDobjException();
			}
			case 45: /* OPCASKIO: ask for indirect object and set preposition */
			{
				int iobj_num = GameFileParser.read_16(code, p);
				p += 2;
				TObject iobj = Jetty.state.lookup_object(iobj_num);
				if (iobj == null)
					Jetty.out.print_error("iobj=" + iobj_num + " not an object?", 1);
				else
					throw new AskIobjException(iobj);
			}
			case 46: /* OPCEXPINH: "inherited <superclass>.<property>" */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = GameFileParser.read_16(code, p);
				p += 2;
				int objid = GameFileParser.read_16(code, p);
				p += 2;
				TObject obj = Jetty.state.lookup_object(objid);
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(obj.eval_property(self, prop, arglist));
			}
				break;
			case 47: /* OPCEXPINHPTR: "inherited <superclass>.<prop-pointer>" */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = ((TValue) stack.pop()).get_property();
				int objid = GameFileParser.read_16(code, p);
				p += 2;
				TObject obj = Jetty.state.lookup_object(objid);
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(obj.eval_property(self, prop, arglist));
			}
				break;
			case 48: /* OPCCALLD: call function and discard value */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 49: /* OPCGETPD: evaluate property and discard any value */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 50: /* OPCBUILTIND: call built-in function and discard value */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 51: /* OPCJE: jump if equal */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.equals(arg2))
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 52: /* OPCJNE: jump if not equal */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (!arg1.equals(arg2))
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 53: /* OPCJGT: jump if greater than */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) > 0)
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 54: /* OPCJGE: jump if greater or equal */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) >= 0)
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 55: /* OPCJLT: jump if less than */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) < 0)
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 56: /* OPCJLE: jump if less or equal */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (arg1.get_comparison(arg2) <= 0)
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 57: /* OPCJNAND: jump if not AND */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (!(arg1.get_logical() && arg2.get_logical()))
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 58: /* OPCJNOR: jump if not OR */
			{
				TValue arg2 = (TValue) stack.pop();
				TValue arg1 = (TValue) stack.pop();
				if (!(arg1.get_logical() || arg2.get_logical()))
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 59: /* OPCJT: jump if true */
			{
				if (((TValue) stack.pop()).get_logical())
					p += GameFileParser.read_16signed(code, p);
				else
					p += 2;
			}
				break;
			case 60: /* OPCGETPSELF: get property of the 'self' object */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = GameFileParser.read_16(code, p);
				p += 2;
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(self.eval_property(prop, arglist));
			}
				break;
			case 61: /* OPCGETPSLFD: get property of 'self' and discard result */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 62: /* OPCGETPOBJ: get property of a given object */
			{
				int argc = GameFileParser.read_8(code, p++);
				int objid = GameFileParser.read_16(code, p);
				p += 2;
				int prop = GameFileParser.read_16(code, p);
				p += 2;
				TObject obj = Jetty.state.lookup_object(objid);
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(obj.eval_property(prop, arglist));
			}
				break;
			case 63: /* OPCGETPOBJD: get property of an object and discard result */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 64: /* OPCINDEX: get an indexed entry from a list */
			{
				int index = ((TValue) stack.pop()).get_number();
				Vector list = ((TValue) stack.pop()).get_list();
				if (index < 1 || index > list.size()) {
					Jetty.out.print_error("Error: indexing list out of bounds: " + index + " (list size=" + list.size() + ")", 1);
					stack.push(new TValue(TValue.NIL, 0));
				} else
					stack.push(list.elementAt(index - 1)); // index from 1, of course
			}
				break;
			case 67: /* OPCPUSHPN: push a property number */
			{
				TValue tv = new TValue(TValue.PROPERTY, code, p, p + 2);
				p += 2;
				stack.push(tv);
			}
				break;
			case 68: /* OPCJST: jump and save top-of-stack if true */
			{
				if (((TValue) stack.peek()).get_logical())
					p += GameFileParser.read_16signed(code, p);
				else {
					stack.pop();
					p += 2;
				}
			}
				break;
			case 69: /* OPCJSF: jump and save top-of-stack if false */
			{
				if (!((TValue) stack.peek()).get_logical())
					p += GameFileParser.read_16signed(code, p);
				else {
					stack.pop();
					p += 2;
				}
			}
				break;
			case 70: /* OPCJMPD: discard stack and then jump unconditionally */
			{
				Jetty.out.print_error("Opcode '" + opcode + "' no longer in use.", 1);
			}
				break;
			case 71: /* OPCINHERIT: inherit a property from superclass */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = GameFileParser.read_16(code, p);
				p += 2;
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(prop_obj.eval_property(self, prop, arglist, false));
			}
				break;
			case 72: /* OPCCALLEXT: call external function */
			{
				Jetty.out.print_error("External functions not supported", 1);
				p += 2;
			}
				break;
			case 73: /* OPCDBGRET: return to debugger (no stack frame leaving) */
			{
				// debug-only, ignore
				;
			}
				break;
			case 74: /* OPCCONS: construct list from top two stack elements */
			{
				// despite what that comment says (which comes from opc.h),
				// this apparently supports creating a list from the top N
				// elements, where N is an arg here
				int ncons = GameFileParser.read_16(code, p);
				p += 2;
				// construct a size-0 list:
				TValue lst = new TValue(TValue.LIST, code, p, p);
				Vector v = lst.get_list();
				// now pop stuff from the stack and add it on
				for (int i = 0; i < ncons; i++)
					v.addElement(stack.pop());
				stack.push(lst);
			}
				break;
			case 75: /* OPCSWITCH: switch statement */
			{
				// jump ahead to the switch table
				p += GameFileParser.read_16(code, p);
				TValue switch_value = (TValue) stack.pop();
				int num_cases = GameFileParser.read_16(code, p);
				p += 2;
				boolean jumped = false;
				for (int i = 0; i < num_cases; i++) {
					int case_type = GameFileParser.read_8(code, p++);
					TValue case_value = null;
					if (case_type == 1) // OPCPUSHNUM
					{
						case_value = new TValue(TValue.NUMBER, code, p, p + 4);
						p += 4;
					} else if (case_type == 31 || case_type == 32) // OPCPUSHSTR/LST
					{
						int ty = (case_type == 31) ? TValue.SSTRING : TValue.LIST;
						int tsz = GameFileParser.read_16(code, p) - 2;
						p += 2;
						case_value = new TValue(ty, code, p, p + tsz);
						p += tsz;
					}
					// OPCPUSHPN / OPCPUSHFN / OPCPUSHOBJ 
					else if (case_type == 67 || case_type == 35 || case_type == 2) {
						int ty = (case_type == 67) ? TValue.PROPERTY : ((case_type == 35) ? TValue.FUNCTION : TValue.OBJECT);
						case_value = new TValue(ty, code, p, p + 2);
						p += 2;
					} else if (case_type == 28) // OPCPUSHSELF
					{
						case_value = new TValue(TValue.OBJECT, self.get_id());
					} else if (case_type == 33 || case_type == 34) // OPCPUSHNIL/TRUE
					{
						int ty = (case_type == 33) ? TValue.NIL : TValue.TRUE;
						case_value = new TValue(ty, 0);
					} else {
						Jetty.out.print_error("Unknown case type: " + case_type, 1);
					}
					if (case_value.equals(switch_value)) {
						p += GameFileParser.read_16signed(code, p);
						jumped = true;
						break;
					} else
						p += 2; // skip jump value, since this case doesn't apply
				}
				// finally, if we finished without jumping anywhere, do the default
				if (!jumped)
					p += GameFileParser.read_16signed(code, p);
			}
				break;
			case 76: /* OPCARGC: get argument count */
			{
				stack.push(new TValue(TValue.NUMBER, args.length));
			}
				break;
			case 77: /* OPCCHKARGC: check actual arguments against formal count */
			{
				// get the count expected for this function
				int count = code[p++] & 0xFF;
				// to signal variable args (eg, a ... in the param list), 
				// 0x80 is added to the count. so we check for that here:
				if ((((count & 0x80) == 0) && count != args.length) || ((count & 0x7f) > args.length)) {
					Jetty.out.print_error("Wrong number of args passed to function: " + "received " + args.length + ", expected " + (count & 0x7f) + ((count & 0x80) == 0 ? "" : "+"), 1);
				}
			}
				break;
			case 78: /* OPCLINE: line record */
			{
				// debug-only, ignore
				p += GameFileParser.read_8(code, p);
			}
				break;
			case 79: /* OPCFRAME: local variable frame record */
			{
				// debug-only, ignore
				p += GameFileParser.read_16(code, p);
			}
				break;
			case 80: /* OPCBP: breakpoint - replaces an OPCLINE instruction */
			{
				// debug-only, ignore
				p += GameFileParser.read_8(code, p);
			}
				break;
			case 81: /* OPCGETDBLCL: get debugger local */
			{
				// debug-only, ignore
				p += 6;
			}
				break;
			case 82: /* OPCGETPPTRSELF: get property pointer from self */
			{
				int argc = GameFileParser.read_8(code, p++);
				int prop = ((TValue) stack.pop()).get_property();
				TValue[] arglist = new TValue[argc];
				for (int i = 0; i < arglist.length; i++)
					arglist[i] = (TValue) stack.pop();
				stack.push(self.eval_property(prop, arglist));
			}
				break;
			case 83: /* OPCMOD: modulo */
			{
				int val2 = ((TValue) stack.pop()).get_number();
				int val1 = ((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, (val1 % val2)));
			}
				break;
			case 84: /* OPCBAND: binary AND */
			{
				int val2 = ((TValue) stack.pop()).get_number();
				int val1 = ((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, (val1 & val2)));
			}
				break;
			case 85: /* OPCBOR: binary OR */
			{
				int val2 = ((TValue) stack.pop()).get_number();
				int val1 = ((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, (val1 | val2)));
			}
				break;
			case 86: /* OPCXOR: binary XOR */
			{
				if (((TValue) stack.peek()).get_type() == TValue.NUMBER) {
					int val2 = ((TValue) stack.pop()).get_number();
					int val1 = ((TValue) stack.pop()).get_number();
					stack.push(new TValue(TValue.NUMBER, (val1 ^ val2)));
				} else {
					boolean val2 = ((TValue) stack.pop()).get_logical();
					boolean val1 = ((TValue) stack.pop()).get_logical();
					if (val1 ^ val2)
						stack.push(new TValue(TValue.NIL, 0));
					else
						stack.push(new TValue(TValue.TRUE, 0));
				}
			}
				break;
			case 87: /* OPCBNOT: binary negation */
			{
				int val = ~((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, val));
			}
				break;
			case 88: /* OPCSHL: bit shift left */
			{
				int val2 = ((TValue) stack.pop()).get_number();
				int val1 = ((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, (val1 << val2)));
			}
				break;
			case 89: /* OPCSHR: bit shift right */
			{
				int val2 = ((TValue) stack.pop()).get_number();
				int val1 = ((TValue) stack.pop()).get_number();
				stack.push(new TValue(TValue.NUMBER, (val1 >> val2)));
			}
				break;
			case 90: /* OPCNEW: create new object */
			{
				int obj = ((TValue) stack.pop()).get_object();
				TObject tobj = Jetty.state.lookup_object(obj);
				if (tobj.is_dynamic()) {
					Jetty.out.print_error("Error: superclass for new must be non-dynamic", 1);
				} else {
					stack.push(Jetty.state.create_obj(tobj));
				}
			}
				break;
			case 91: /* OPCDELETE: delete object */
			{
				int obj = ((TValue) stack.pop()).get_object();
				TObject tobj = Jetty.state.lookup_object(obj);
				if (!tobj.is_dynamic()) {
					Jetty.out.print_error("Error: object for delete must be dynamic", 1);
				} else {
					Jetty.state.delete_obj(tobj);
				}
			}
				break;
			default: {
				if ((opcode & 0xc0) != 0) // it's an assignment operator
				{
					// we have to pull out the extended type (if any) first
					int ext = 0;
					if ((opcode & 0x1c) == 0x1c)
						ext = code[p++] & 0xFF;
					TValue dest = null;
					// careful, here: if we assign to a list, we push the list on the
					// stack. if not, we push the value assigned onto the stack.
					TValue return_list = null;
					int opdest = opcode & 0x03; /*
												 * OPCASIDEST_MASK: mask to get
												 * destination field
												 */
					if (opdest == 0x00) /* OPCASILCL: assign to a local */
					{
						int lcl = GameFileParser.read_16signed(code, p);
						p += 2;
						if (lcl >= 0)
							dest = locals[lcl - 1];
						else {
							args[(lcl * -1) - 1] = args[(lcl * -1) - 1].do_clone();
							dest = args[(lcl * -1) - 1];
						}
					} else if (opdest == 0x01) /*
												 * OPCASIPRP: assign to an
												 * object.property
												 */
					{
						int prop = GameFileParser.read_16(code, p);
						p += 2;
						TObject obj = Jetty.state.lookup_object(((TValue) stack.pop()).get_object());
						TValue[] arglist = new TValue[0];
						// if it's a direct assignment, don't evaluate the property,
						// otherwise do
						if ((opcode & 0x1c) == 0)
							dest = new TValue(TValue.NIL, 0);
						else
							dest = obj.eval_property(prop, arglist).do_clone();
						obj.set_property(prop, dest);
					} else if (opdest == 0x02) /*
												 * OPCASIIND: assign to an
												 * element of a list
												 */
					{
						int index = ((TValue) stack.pop()).get_number();
						return_list = (TValue) stack.pop();
						Vector list = return_list.get_list();
						if (index < 1 || index > list.size()) {
							Jetty.out.print_error("Error: indexing list out of bounds in assignment: " + index + " (list size=" + list.size() + ")", 1);
							stack.push(new TValue(TValue.NIL, 0));
							dest = new TValue(TValue.NIL, 0);
						} else
							dest = (TValue) list.elementAt(index - 1); // index from 1
					} else // 0x03:      /* OPCASIPRPPTR: assign property via pointer */
					{
						int prop = ((TValue) stack.pop()).get_property();
						TObject obj = Jetty.state.lookup_object(((TValue) stack.pop()).get_object());
						// if it's a direct assignment, don't evaluate the property,
						// otherwise do
						if ((opcode & 0x1c) == 0)
							dest = new TValue(TValue.NIL, 0);
						else
							dest = obj.eval_property(prop, TObject.arg_array());
						obj.set_property(prop, dest);
					}
					// value we push on the stack at the end; normally dest,
					// except for the post-increment/post-decrement operators
					TValue prev_value = null;
					int optype = opcode & 0x1c; /*
												 * OPCASITYP_MASK: mask to get
												 * assignment type field
												 */
					if (optype == 0x00) /* OPCASIDIR: direct assignment */
					{
						dest.copy((TValue) stack.pop());
					} else if (optype == 0x04) /* OPCASIADD: assign and add */
					{
						dest.copy(do_add(dest, (TValue) stack.pop()));
					} else if (optype == 0x08) /*
												 * OPCASISUB: assign and
												 * subtract
												 */
					{
						dest.copy(do_sub(dest, (TValue) stack.pop()));
					} else if (optype == 0x0c) /*
												 * OPCASIMUL: assign and
												 * multiply
												 */
					{
						int val = dest.get_number();
						val *= ((TValue) stack.pop()).get_number();
						dest.copy(new TValue(TValue.NUMBER, val));
					} else if (optype == 0x10) /* OPCASIDIV: assign and divide */
					{
						int val = dest.get_number();
						val /= ((TValue) stack.pop()).get_number();
						dest.copy(new TValue(TValue.NUMBER, val));
					} else if (optype == 0x14) /* OPCASIINC: increment */
					{
						if ((opcode & 0x20) == 0x00) // OPCASIPOST: post-increment
							prev_value = dest.do_clone();
						int val = dest.get_number() + 1;
						dest.copy(new TValue(TValue.NUMBER, val));
					} else if (optype == 0x18) /* OPCASIDEC: decrement */
					{
						if ((opcode & 0x20) == 0x00) // OPCASIPOST: post-increment
							prev_value = dest.do_clone();
						int val = dest.get_number() - 1;
						dest.copy(new TValue(TValue.NUMBER, val));
					} else // 0x1c:      /* OPCASIEXT: other - extension flag */
					{
						if (ext == 1) /* OPCASIMOD: modulo and assign */
						{
							int val = dest.get_number();
							val %= ((TValue) stack.pop()).get_number();
							dest.copy(new TValue(TValue.NUMBER, val));
						} else if (ext == 2) /* OPCASIBAND: binary AND and assign */
						{
							int val = dest.get_number();
							val &= ((TValue) stack.pop()).get_number();
							dest.copy(new TValue(TValue.NUMBER, val));
						} else if (ext == 3) /* OPCASIBOR: binary OR and assign */
						{
							int val = dest.get_number();
							val |= ((TValue) stack.pop()).get_number();
							dest.copy(new TValue(TValue.NUMBER, val));
						} else if (ext == 4) /* OPCASIXOR: binary XOR and assign */
						{
							if (dest.get_type() == TValue.NUMBER) {
								int val = dest.get_number();
								val %= ((TValue) stack.pop()).get_number();
								dest.copy(new TValue(TValue.NUMBER, val));
							} else {
								boolean val = dest.get_logical();
								val ^= dest.get_logical();
								if (val)
									dest.copy(new TValue(TValue.NIL, 0));
								else
									dest.copy(new TValue(TValue.TRUE, 0));
							}
						} else if (ext == 5) /* OPCASISHL: shift left and assign */
						{
							int val = dest.get_number();
							val <<= ((TValue) stack.pop()).get_number();
							dest.copy(new TValue(TValue.NUMBER, val));
						} else if (ext == 6) /* OPCASISHR: shift right and assign */
						{
							int val = dest.get_number();
							val >>= ((TValue) stack.pop()).get_number();
							dest.copy(new TValue(TValue.NUMBER, val));
						}
					}
					if (return_list != null)
						stack.push(return_list);
					else if (prev_value != null)
						stack.push(prev_value);
					else
						stack.push(dest);
				} else {
					Jetty.out.print_error("Unknown opcode: " + opcode + " p=" + p, 1);
					throw new GameOverException();
				}
			}
			}
		}
		return new TValue(TValue.NIL, 0);
	}

	private TValue do_add(TValue v1, TValue v2) throws HaltTurnException {
		v1.must_be(TValue.NUMBER, TValue.SSTRING, TValue.LIST);
		if (v1.get_type() == TValue.NUMBER) {
			int val = v1.get_number() + v2.get_number();
			return new TValue(TValue.NUMBER, val);
		} else if (v1.get_type() == TValue.SSTRING) {
			String s = v1.get_string() + v2.get_string();
			return new TValue(TValue.SSTRING, s);
		} else {
			Vector lst = v1.do_clone().get_list();
			if (v2.get_type() == TValue.LIST) {
				Vector lst2 = v2.get_list();
				for (int i = 0; i < lst2.size(); i++)
					lst.addElement(((TValue) lst2.elementAt(i)).do_clone());
			} else
				lst.addElement(v2.do_clone());
			return new TValue(TValue.LIST, lst);
		}
	}

	private TValue do_sub(TValue v1, TValue v2) throws HaltTurnException {
		v1.must_be(TValue.NUMBER, TValue.LIST);
		if (v1.get_type() == TValue.NUMBER) {
			int val = v1.get_number() - v2.get_number();
			return new TValue(TValue.NUMBER, val);
		} else {
			Vector lst = v1.do_clone().get_list();
			if (v2.get_type() == TValue.LIST) {
				Vector lst2 = v2.get_list();
				for (int i = 0; i < lst2.size(); i++)
					lst.removeElement(lst2.elementAt(i));
			} else
				lst.removeElement(v2);
			return new TValue(TValue.LIST, lst);
		}
	}
}
