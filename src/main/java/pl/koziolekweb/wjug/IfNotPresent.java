package pl.koziolekweb.wjug;

import java.util.Optional;

public class IfNotPresent {

    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();

        empty.ifPresent(System.out::println);
        //…
    }
}
