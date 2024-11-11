package dev.ak.interfce;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionalInterfaceExample {

    public Integer add(int a, int b) {
        return a+b;
    }

    public static void main(String[] args) {

        // Function<T, R> -> Takes argument T and return result with type R
        Function<Integer, Integer> function = (n) -> n * 10;
        function.apply(10);

        // BiFunction<T,U,R>  -> Takes 2 arguments T & U and return results with type R
        BiFunction<Integer, String, Map<Integer,String>> mapBiFunction = (n, str) -> {
            Map<Integer, String> map = new HashMap<>();
            map.put(n, str);
            return map;
        };

        Map<Integer, String> hello = mapBiFunction.apply(1, "hello");
        System.out.println(hello);
        IntFunction<Integer> intFunction = (n) -> Math.min(1,2);
        System.out.println(intFunction.apply(1));

        // Special type of Function 1. UnaryOperator  2. BinaryOperator

        //UnaryOperator<T> -> Accepts single argument type T and returns same type T
        UnaryOperator<Integer> integerUnaryOperator = (n) -> n * 10;
        System.out.println(integerUnaryOperator.apply(10));

        // BinaryOperator<T> -> Accepts two arguments type T and returns same type T
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        System.out.println(binaryOperator.apply(20,20));

        // Predicate<T>
        Predicate<String> stringPredicate = (str) -> str.startsWith("A");
        System.out.println(stringPredicate.test("Ajith"));

        BiPredicate<Integer,Integer> integerBiPredicate = Integer::equals;
        System.out.println(integerBiPredicate.test(1,1));

        // Consumer<T>
        Consumer<String> stringConsumer = (str) -> System.out.println(str);
        stringConsumer.accept("hello");

        BiConsumer<Integer, Integer> biConsumer = (a,b) -> System.out.println(a+b);
        biConsumer.accept(1,2);

        // Supplier<T>
        Supplier<LocalDate> dateSupplier = LocalDate::now;
        System.out.println(dateSupplier.get());

    }

}
