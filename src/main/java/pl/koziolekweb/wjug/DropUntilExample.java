package pl.koziolekweb.wjug;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DropUntilExample {

    public static void main(String[] args) {
        Stream<String> stream = List.of("ala", "ma", "kota", "a", "kot", "ma", "mysz")
                .stream();
        DropUntil.dropUntil(stream, s -> s.length() > 1)
                .forEach(System.out::println);
    }
}

class DropUntil {

    static <T> Stream<T> dropUntil(Stream<T> in, Predicate<T> condition) {
        Spliterator<T> inSplit = in.spliterator();
        return StreamSupport.stream(
                new Spliterators.AbstractSpliterator<T>(inSplit.estimateSize(), 0) {
                    boolean dropped;

                    @Override
                    public boolean tryAdvance(Consumer<? super T> action) {
                        if (dropped)
                            return inSplit.tryAdvance(action);
                        do {
                        } while (!dropped && inSplit.tryAdvance(t -> {
                            if (!condition.test(t)) {
                                dropped = true;
                                action.accept(t);
                            }
                        }));
                        return dropped;
                    }

                    @Override
                    public void forEachRemaining(Consumer<? super T> action) {
                        while (!dropped)
                            if (!tryAdvance(action))
                                return;
                        inSplit.forEachRemaining(action);
                    }
                }
                , false
        );
    }
}