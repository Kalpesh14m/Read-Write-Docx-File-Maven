package com.kalpesh.devil;

import java.io.File;

public class Constants {
	private Constants() {
	}

	private static final String FILE_NAME = "/helloMavenCentral.docx";
	static final File PATH = new File(System.getProperty("user.dir") + FILE_NAME);
}
