package pl.koziolekweb.wjug;

import java.util.Optional;

public class IfNotPresentJ9 {

    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();

        empty.ifPresentOrElse(System.out::println, ()->System.out.println("(☞ ͡° ͜ʖ ͡°)☞"));

    }
}
