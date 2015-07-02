package org.tads.gameRunner.io;

import java.io.Console;
import java.io.IOException;

import org.tads.gameRunner.core.Ansi;
import org.tads.jetty.OutputFormatter;
import org.tads.jetty.PlatformIO;

/**
 * Created by developer on 7/1/15.
 */
public class ConsoleIO implements PlatformIO {

	public void clear_screen() {
		System.out.println("clear_screen");
		try {
			Runtime.getRuntime().exec("clear");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean more_prompt(String prompt) {
		System.out.println(prompt);
		char key = 0;
		try {
			key = (char) System.console().reader().read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (key == ' ');
	}

	public void print_error(String s) {
		//		System.out.println("print_error");
		System.err.println(s);
	}

	public void print_text(String s) {
		System.out.print(s);
		if (s != null && s.contains("\n"))
			System.out.print(Ansi.ANSI_RESET);
	}

	public String read_key() {
		String key = null;
		try {
			char keyInt = (char) System.in.read();
			key = "" + keyInt;
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("read_key");
		return key;
	}

	public String read_line() {
		//		System.out.println("read_line");
		String key = "";
		Console console = System.console();
		key = console.readLine();
		return key;
	}

	public void scroll_window() {
		//		System.out.println("scroll_window");
		System.out.println();
		//		System.out.println();
	}

	public void set_out(OutputFormatter out) {
		System.out.println("set_out " + out);
	}

	public void set_status_string(String s, boolean left) {
		System.out.println("set_status_string" + s + "left:" + left);
		System.out.print(Ansi.RED);
	}

	public void set_style(int style, boolean turn_on) {
		switch (style) {
		case PlatformIO.BOLD:
			System.console().printf(Ansi.BOLD);
			break;
		case PlatformIO.ITALIC:
			System.console().printf(Ansi.ITALIC);
			break;
		default:
			break;
		}
		System.out.println("set_style");
		System.out.print(Ansi.CYAN);
	}

	public int size_text(char c) {
		//		System.out.println("size_text" + c);
		return 1;
	}

	public int size_text(String s) {
		//		System.out.println("size_text");
		return s != null ? s.length() : 0;
	}
}
