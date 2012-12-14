package com.saido.kumpulTweet.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


@SuppressWarnings("serial")
public class TarikTweet extends HttpServlet{
	private static final Logger logger = Logger.getLogger(TarikTweet.class.getCanonicalName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			fetchTweets();
	}
	
	private static void fetchTweets(){
	    try{// Start TRY Fetch
			String TwitterSearchQuery = "http://search.twitter.com/search.atom?q=to:triindonesia&rpp=100&include_entities=false&result_type=recent";
			//String testFile = "http://localhost/s/search.xml";
			URL sourceURL = new URL(TwitterSearchQuery);
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(new InputSource(sourceURL.openStream()));
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("entry");
			
			String[] tweetData = new String[5];
			
			for(int index = 0; index< nList.getLength(); index++){//Start parse
				Node nNode = nList.item(index);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						Element el = (Element) nNode;
						
						tweetData[0] = getTagValue("id",el).substring(23);
						tweetData[1] = getTagValue("name", el);
						tweetData[2] = getTagValue("published",el);
						tweetData[3] = getTagValue("title",el);
						tweetData[4] = getTagValue("twitter:source",el);
						
						Store.tweet(tweetData);
					}
				
			}//End parse
			
			
		}catch(Exception e){ //End Fetch
			String msg = "GAGAL FETCH!! " + e.getMessage();
			logger.log(Level.WARNING, msg);
		}//End Catch
	}//END fetchTweets()
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		return nValue.getNodeValue();
	}

}

