package org.tads.gameRunner.engine;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by developer on 7/3/15.
 */
public abstract class Engine {

	private String name;
	private String mainClassName;
	private List<String> supportedFormats = new ArrayList<String>();

	public Engine() {
	}

	public Engine(String mainClassName, String name, List<String> supportedFormats) {
		this.mainClassName = mainClassName;
		this.name = name;
		this.supportedFormats = supportedFormats;
	}

	public String getMainClassName() {
		return mainClassName;
	}

	public void setMainClassName(String mainClassName) {
		this.mainClassName = mainClassName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSupportedFormats() {
		return supportedFormats;
	}

	public void setSupportedFormats(List<String> supportedFormats) {
		this.supportedFormats = supportedFormats;
	}

	public abstract void run(File file) throws IOException;

	@Override
	public String toString() {
		return "Engine{" + "mainClassName='" + mainClassName + '\'' + ", name='" + name + '\'' + ", supportedFormats=" + supportedFormats + '}';
	}
}
