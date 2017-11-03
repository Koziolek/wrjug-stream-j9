package pl.koziolekweb.wjug;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FlowOptionalExampleJ9 {

    public static void main(String[] args) {
        List<String> slowa = List.of("ala", "ma", "kota", "a", "kot", "ma", "mysz");

        new ReverseFlow().reverseWord(slowa.stream())
                .forEach(System.out::println);

        Optional<String> slowo = Optional.ofNullable("Słowo");

        new ReverseFlow().reverseWord(
                slowo.stream()
        )
                .forEach(System.out::println);
    }

    static class ReverseFlow {

        public Stream<String> reverseWord(Stream<String> stream) {
            return stream.map(s -> new StringBuffer(s).reverse().toString());
        }
    }
}


