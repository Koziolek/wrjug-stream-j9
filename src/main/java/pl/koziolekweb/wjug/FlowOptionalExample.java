package pl.koziolekweb.wjug;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FlowOptionalExample {

    public static void main(String[] args) {
        List<String> slowa = List.of("ala", "ma", "kota", "a", "kot", "ma", "mysz");

        new ReverseFlow().reverseWord(slowa.stream())
                .forEach(System.out::println);

        Optional<String> slowo = Optional.ofNullable("Słowo");

        new ReverseFlow().reverseWord(
                slowo.map(Stream::of)
                        .orElse(Stream.empty())
        )
                .forEach(System.out::println);
    }

    static class ReverseFlow {

        public Stream<String> reverseWord(Stream<String> stream) {
            return stream.map(s -> new StringBuffer(s).reverse().toString());
        }
    }
}


