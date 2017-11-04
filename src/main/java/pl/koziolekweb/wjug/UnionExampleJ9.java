package pl.koziolekweb.wjug;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Optional;

public class UnionExampleJ9 {

    public static void main(String[] args) throws Exception {
        String dane = "<order><customizedProduct/></order>";
        Document document = loadXML(dane);

        Optional<Product> product = getProduct(document);

        product.ifPresent(System.out::println);
    }

    private static Optional<Product> getProduct(Document document) {
        return firstByName(document, "businessProduct")
                .<Product>map(n -> new BusinessProduct())
                .or(
                        () -> firstByName(document, "personalProduct")
                                .<Product>map(n -> new PersonalProduct())
                )
                .or(
                        () -> firstByName(document, "customizedProduct")
                                .map(n -> new CustomizedProduct())
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


