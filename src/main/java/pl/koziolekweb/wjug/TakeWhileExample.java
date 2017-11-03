package pl.koziolekweb.wjug;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TakeWhileExample {

    public static void main(String[] args) {
        Stream<String> stream = List.of("ala", "ma", "kota", "a", "kot", "ma", "mysz")
                .stream();
        TakeWhile.takeWhile(stream, s -> s.length() > 1)
                .forEach(System.out::println);
    }
}

class TakeWhile {

    static <T> Stream<T> takeWhile(Stream<T> in, Predicate<T> condition) {
        Spliterator<T> inSplit = in.spliterator();
        return StreamSupport.stream(
                new Spliterators.AbstractSpliterator<T>(inSplit.estimateSize(), 0) {
                    private boolean isGoing = true;

                    @Override
                    public boolean tryAdvance(Consumer action) {
                        if (isGoing) {
                            boolean next = inSplit.tryAdvance(e -> {
                                if (condition.test(e))
                                    action.accept(e);
                                else
                                    isGoing = false;
                            });
                            return next && isGoing;
                        }
                        return false;
                    }
                }
                , false
        );
    }
}