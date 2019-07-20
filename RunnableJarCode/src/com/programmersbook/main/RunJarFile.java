package com.programmersbook.main;

import java.io.IOException;

public class RunJarFile {

	public static void main(String[] args) throws IOException {
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c","Start","F:\\TheProgrammersBook\\July_20_2019\\RunnJar.bat");
		Process proces = processBuilder.start();
	}

}
