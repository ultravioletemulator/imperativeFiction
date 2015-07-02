package org.tads.jetty;/*
org.tads.jetty.Jetty, a tads 2 interpreter written in java. Goals are (roughly in order):
- clear
- correct
- cunning
- complete

In other words, the main point is to have an interpreter where people
can understand how it works and (ideally) add features to it and fix bugs
and use its code as a model to build other interpreters and stuff.
Of nearly-equal importance is correctness, used in the sense that if a
feature is included and claims to be an implementation to the standard, it 
should behave accurately. This is distinct from the fourth feature, 
completeness, which is much less important, which says that not only should 
the interpreter be correct in all the features it supports, it should 
support all the features in the spec (well, or what would be in the spec if 
there was one). As of writing this, for instance, this interpreter doesn't 
support external functions, html, etc, and probably won't support some other
features. Somewhere in this there's being cunning, by which I mean 
"efficient" but that doesn't start with "c". This interpreter is intended
to be fast enough to be usable on modern machines, and to be usable as a
demo on an applet. It probably won't be usable on slow machines or on
palmtops, but that's life. Someone else write a palmtop interpreter, 
please :)

These were the initial goals, anyway. In practice they more or less only
holds true for the "interpreter" part of org.tads.jetty.Jetty, as opposed to the "parser"
part. I found out to my distress there are rather a large number of parser
hooks and crotchets and thingamabobs and it was beyond me to implement them
all. So, frankly, it ends up mostly working but kind of spotty, and not even
fulfilling goal 2, let alone goal 4. But oh well.
 */

import java.io.IOException;
import java.io.InputStream;

public class Jetty {

	public static GameState state = null;
	public static OutputFormatter out = null;
	public static InputHandler in = null;
	public static Parser parser = null;
	public static Simulator simulator = null;
	public static CodeRunner runner = null;
	public static ParserError perror = null;

	public Jetty(PlatformIO io, InputStream file) {
		out = new OutputFormatter(io);
		in = new InputHandler(io);
		io.set_out(out);
		_file = file;
	}

	public boolean load() {
		try {
			GameFileParser gfp = new GameFileParser();
			state = gfp.read(_file);
			if (state == null)
				return false;
			state.init();
		} catch (IOException ioe) {
			Jetty.out.print_error("I/O error reading gamefile: " + ioe, 0);
			return false;
		} catch (HaltTurnException hte) {
			Jetty.out.print_error("Internal error reading gamefile: " + hte, 0);
			return false;
		}
		runner = new CodeRunner();
		perror = new ParserError();
		simulator = new Simulator();
		parser = new Parser();
		return true;
	}

	public void run() {
		try {
			TObject preinit = state.get_preinit();
			if (state.run_preinit() && preinit != null) {
				try {
					runner.run(preinit.get_data(), TObject.arg_array(), preinit);
				} catch (ParseException pe) {
					Jetty.out.print_error("Parse exception (" + pe + ") erroneously thrown in preinit()", 1);
				} catch (ReparseException pe) {
					Jetty.out.print_error("Reparse exception erroneously thrown in " + "preinit()", 1);
				}
			}
			TObject init = state.lookup_required_object(RequiredObjects.INIT);
			if (init == null) {
				Jetty.out.print_error("Error: no init function found", 0);
				throw new GameOverException();
			}
			try {
				runner.run(init.get_data(), TObject.arg_array(), init);
			} catch (ParseException pe) {
				Jetty.out.print_error("Parse exception (" + pe + ") erroneously thrown in init()", 1);
			} catch (ReparseException pe) {
				Jetty.out.print_error("Reparse exception erroneously thrown in init()", 1);
			}
			while (true) {
				parser.run_turn();
			}
		} catch (GameOverException goe) {
			System.out.println("\n[The game has ended.]");
		}
		// and make sure all the output gets dumped before we go bye-bye.
		Jetty.out.flush();
	}

	public static int get_debug_level() {
		return _debug_level;
	}

	public static void set_debug_level(int d) {
		_debug_level = d;
	}

	private InputStream _file;
	private static int _debug_level = 1;
}
