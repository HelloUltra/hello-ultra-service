package com.example.utility;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataParser {
	public static String test() {
		System.setProperty("jsse.enableSNIExtension", "false") ; 
		Document doc;
		String result = "";

		try {

			// need http protocol
			doc = Jsoup.connect("https://slipp.net/").get();
			
			Elements links = doc.select("section.qna-tags ul li a.tag");
			for (Element link : links) {
				result += link.text();
				String [] temp = result.split(" ");
				result = temp[0]+"\n";			
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		result = result.substring(1);
		System.out.println(result);
		return result;
	}

}
