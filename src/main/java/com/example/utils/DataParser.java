package com.example.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.model.Message;

public class DataParser {
	public static ArrayList<String> hashTags = new ArrayList<String>();
	public static String tagName;

	public static void hashTagParser() {
		System.setProperty("jsse.enableSNIExtension", "false");
		Document doc;
		try {
			// need http protocol
			doc = Jsoup.connect("https://slipp.net/").get();

			Elements links = doc.select("section.qna-tags ul li a.tag");
			for (Element link : links) {

				String[] temp = link.text().split(" ");
				hashTags.add(temp[0]);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
