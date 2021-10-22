package threatTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class RestXmlHttpClient {
    public void xml() throws IOException, ParserConfigurationException, SAXException {

        URL url = new URL("https://api.msrc.microsoft.com/cvrf/v2.0/updates");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set timeout as per needs
        connection.setConnectTimeout(20000);
        connection.setReadTimeout(20000);

        // Set DoOutput to true if you want to use URLConnection for output.
        // Default is false
        connection.setDoOutput(true);

        connection.setUseCaches(true);
        connection.setRequestMethod("GET");

        // Set Headers
        connection.setRequestProperty("Accept", "application/xml");
        connection.setRequestProperty("Content-Type", "application/xml");

        // Read XML
        InputStream inputStream = connection.getInputStream();
        byte[] res = new byte[2048];
        int i = 0;
        StringBuilder response = new StringBuilder();
        while ((i = inputStream.read(res)) != -1) {
            response.append(new String(res, 0, i));
        }
        inputStream.close();
        System.out.println(response.toString());
        String str = response.toString();
        String s1 = str.substring(str.indexOf(">")+1);
        s1.trim();
        String s2 = s1.substring(s1.indexOf(">")+1);
        System.out.println(s2);
        
       
        
        
     }

    public static Document parseXmlFromString(String xmlString)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
        org.w3c.dom.Document document = builder.parse(inputStream);
        return document;
    }
}