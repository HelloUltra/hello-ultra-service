package com.example.utility;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataParser {
	public static String test() {
		Document doc;
		String result = "";
		try {
			/*
			 * Authenticator.setDefault(new Authenticator() {
			 * 
			 * @Override protected PasswordAuthentication
			 * getPasswordAuthentication() { return new
			 * PasswordAuthentication(username, password.toCharArray()); } });
			 */

			// need http protocol
			doc = Jsoup.connect("http://www.naver.com/").get();

			// get page title
			result = doc.title() + "\n";

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				result += link.attr("href");
				result += link.text();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
