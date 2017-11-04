package pl.koziolekweb.wjug;

import java.math.BigInteger;
import java.util.stream.Stream;

public class GeneratorExample {

    public static void main(String[] args) {
        Stream.iterate(1, n -> n + 1)
                .limit(41)
                .map(GeneratorExample::eulerElement)
                .forEach(n -> System.out.printf("%s is prime %b%n", n, n.isProbablePrime(10000)));
    }

    static BigInteger eulerElement(int n){
        BigInteger i = BigInteger.valueOf(n);
        return i.pow(2).subtract(i).add(new BigInteger("41"));
    }
}
