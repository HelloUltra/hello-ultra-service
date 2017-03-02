package com.example.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Tag;


public class DataParser {
	public static ArrayList<Tag> hashTags = new ArrayList<Tag>();
	
	private static final Logger log = LoggerFactory.getLogger(DataParser.class);


	public static void hashTagParser() {
		System.setProperty("jsse.enableSNIExtension", "false");
		Document doc;
		try {
			// need http protocol
			doc = Jsoup.connect("https://slipp.net/").get();

			Elements links = doc.select("section.qna-tags ul li a.tag");
			for (Element link : links) {
				Tag tag = new Tag();
				String[] temp = link.text().split(" ");
				tag.setTagName(temp[0]);
				hashTags.add(tag);
					
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
