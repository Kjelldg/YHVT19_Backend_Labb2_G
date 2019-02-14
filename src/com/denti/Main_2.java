package com.denti;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main_2 {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://host:8080/context/rest/method");

		// JsonArray response =
		// target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);

		// File file = new File("./UserMessage.xml");

		try {

			String output;
			String xml_Output = "";

			URL url = new URL("http://localhost:8080/YHVT19_Backend_Labb2_G/rest/UserService/viewusers");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
			}

			InputStreamReader in = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(in);

			while ((output = br.readLine()) != null) {

				// System.out.println(output);
				xml_Output = output;
			}

			connection.disconnect();

			System.out.println("Output: " + output);
			System.out.println("XML Output: " + xml_Output);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			Document document;

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(xml_Output);

			NodeList nodeList = document.getElementsByTagName("users");

			for (int i = 0; i < nodeList.getLength(); i++) {

				System.out.println("Name: " + nodeList.item(i).getAttributes().getNamedItem("user").getNodeValue());
				System.out
						.println("Professsion: " + nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue());

				System.out.println("User: " + document.getElementsByTagName("name").item(i).getTextContent());
				System.out.println("---------------------------------");

			}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}

	}

}
