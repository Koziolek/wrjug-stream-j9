package pl.koziolekweb.wjug;

import java.util.List;

public class TakeWhileExampleJ9 {

    public static void main(String[] args) {
        List.of("ala", "ma", "kota", "a", "kot", "ma", "mysz")
                .stream().takeWhile(s -> s.length() > 1)
                .forEach(System.out::println);
    }
}