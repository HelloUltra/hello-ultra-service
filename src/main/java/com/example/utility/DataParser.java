package com.example.utility;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.model.Message;

public class DataParser extends Message {
	public static ArrayList hashTags = new ArrayList();
	public static String tagName;
	public static boolean hashTag;
	
	public static void hashTagParser() {
		System.setProperty("jsse.enableSNIExtension", "false") ; 
		Document doc;
		String result = "";
		try {
			// need http protocol
			doc = Jsoup.connect("https://slipp.net/").get();
			
			Elements links = doc.select("section.qna-tags ul li a.tag");
			for (Element link : links) {
			
				//result += link.text();
				String [] temp = link.text().split(" ");
				result = temp[0];	
				hashTags.add(result);
			
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
