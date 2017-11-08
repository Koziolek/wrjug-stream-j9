package pl.koziolekweb.wjug;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Optional;

public class UnionExample {

    public static void main(String[] args) throws Exception {
        String dane = "<order><customizedProduct/></order>";
        Document document = loadXML(dane);

        Optional<Product> product = getProduct(document);

        product.ifPresent(System.out::println);
    }

    private static Optional<Product> getProduct(Document document) {
        Node node = firstByName(document, "businessProduct");
        if (node != null) {
            return Optional.of(new BusinessProduct());
        }
        node = firstByName(document, "personalProduct");
        if (node != null) {
            return Optional.of(new PersonalProduct());
        }
        node = firstByName(document, "customizedProduct");
        if (node != null) {
            return Optional.of(new CustomizedProduct());
        }
        return Optional.empty();
    }


    private static Node firstByName(Document document, String name) {
        return document.getElementsByTagName(name).item(0);
    }

    public static Document loadXML(String xml) throws Exception {
        DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
        DocumentBuilder bldr = fctr.newDocumentBuilder();
        InputSource insrc = new InputSource(new StringReader(xml));
        return bldr.parse(insrc);
    }
}


abstract class Product {
}

class BusinessProduct extends Product implements Serializable {
}

class PersonalProduct extends Product implements Serializable{
}

class CustomizedProduct extends Product implements Serializable{
}