package pl.koziolekweb.wjug;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Optional;

public class UnionExampleJ9 {

    public static void main(String[] args) throws Exception {
        String dane = "<order><customizedProduct/></order>";
        Document document = loadXML(dane);

        Optional<? extends Serializable> product = getProduct(document);

        product.ifPresent(System.out::println);
    }

    private static <T extends Product & Serializable> Optional<T> getProduct(Document document) {
        return firstByName(document, "businessProduct")
                .map(n -> (T)new BusinessProduct())
                .or(
                        () -> firstByName(document, "personalProduct")
                                .map(n -> (T)new PersonalProduct())
                )
                .or(
                        () -> firstByName(document, "customizedProduct")
                                .map(n -> (T)new CustomizedProduct())
                );
    }

    private static Optional<Node> firstByName(Document document, String name) {
        return Optional.ofNullable(document.getElementsByTagName(name).item(0));
    }

    private static Document loadXML(String xml) throws Exception {
        DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
        DocumentBuilder bldr = fctr.newDocumentBuilder();
        InputSource insrc = new InputSource(new StringReader(xml));
        return bldr.parse(insrc);
    }
}


