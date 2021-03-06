package pl.koziolekweb.wjug;

import java.util.Optional;
import java.util.stream.Stream;

public class NullableStreamExample {
    public static void main(String[] args) {
        String s = null;
        Optional<String> slowo = Optional.ofNullable(s);
        System.out.println("----");
        slowo.map(Stream::of).orElseGet(Stream::empty).forEach(System.out::println);
        System.out.println("----");
        Stream.of(s).forEach(System.out::println);
    }
}
