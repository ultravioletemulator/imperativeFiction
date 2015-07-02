package org.tads.jetty;

public class InputHandler {

	public InputHandler(PlatformIO io) {
		_platform_io = io;
	}

	public String read_line() {
		Jetty.out.flush(); // flush output first
		Jetty.out.input_entered(); // and the next print will start at index 0
		String line = _platform_io.read_line();
		if (line != null)
			return line.trim();
		Jetty.out.print_error("Error with read_line()", 1);
		return "";
	}

	public String read_key() {
		Jetty.out.flush(); // flush output first
		String line = _platform_io.read_key();
		if (line != null)
			return line;
		Jetty.out.print_error("Error with read_key()", 1);
		return " ";
	}

	private PlatformIO _platform_io;
}
