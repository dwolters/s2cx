package de.upb.s2cx.helper;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public final class StringFunctions {

	private StringFunctions() {

	}

	public static String unquote(String s) {
		return s.substring(1, s.length() - 1);
	}

	public static String doubleQuote(String s) {
		return "\"" + s + "\"";
	}

	public static InputStream stringToInputStream(String s) {
		return new ByteArrayInputStream(s.getBytes());
	}

	public static void writeStringToFile(File file, String content) throws IOException {
		BufferedWriter writer = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static String getStringFromFile(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream(filename));
		StringBuilder sb = new StringBuilder();
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine());
			sb.append(System.getProperty("line.separator"));
		}
		scanner.close();
		return sb.toString();
	}
}
