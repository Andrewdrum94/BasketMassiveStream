import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Shop {
    static Basket load(File fileJson, File fileTxt, Basket basket) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        String bool = doc.getDocumentElement()
                .getChildNodes()
                .item(1).getChildNodes().item(1).getTextContent();

        String format = doc.getDocumentElement().getChildNodes().item(1).getChildNodes().item(5).getTextContent();
        if (bool.equals("true")) {
            if (format.equals("json")) {
                if (fileJson.exists()) {
                    basket = Basket.loadFromJson();
                }
            } else {
                if (fileTxt.exists()) {
                    basket = Basket.loadFromTxtFile(fileTxt);
                }
            }
        }
        return basket;
    }

    static void save(File fileTxt, Basket basket) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        String bool = doc.getDocumentElement().getChildNodes().item(3).getChildNodes().item(1).getTextContent();
        String format = doc.getDocumentElement().getChildNodes().item(3).getChildNodes().item(5).getTextContent();
        if (bool.equals("true")) {
            if (format.equals("json")) {
                basket.saveJson();
            } else {
                basket.saveTxt(fileTxt);
            }
        }
    }

    static void log(ClientLog person1) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));

        String bool = doc.getDocumentElement().getChildNodes().item(5).getChildNodes().item(1).getTextContent();
        if (bool.equals("true")) {
            person1.exportAsCSV(person1.log);
        }
    }
}
