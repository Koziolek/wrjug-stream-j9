package pl.koziolekweb.wjug;

import io.vavr.Function1;

import java.math.BigInteger;
import java.util.stream.Stream;

public class GeneratorExampleJ9 {

    public static void main(String[] args) {
        Stream.iterate(1, n -> eulerElement(n).isProbablePrime(10000), n -> n + 1)
                .forEach(n -> System.out.printf("%s is prime %b%n",
                        eulerElement(n),
                        eulerElement(n).isProbablePrime(10000)));
    }

    // dodatkowe czary! Nie patrzeć patrzałkami!
    static Function1<Integer, BigInteger> euler = ((Function1<Integer, BigInteger>) x -> {
        BigInteger i = BigInteger.valueOf(x);
        return i.pow(2).subtract(i).add(new BigInteger("41"));
    }).memoized();

    static BigInteger eulerElement(int n) {
        return euler.apply(n);
    }
}
