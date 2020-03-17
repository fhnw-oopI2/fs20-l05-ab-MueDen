package ch.fhnw.oop2.module05.streams.ab1;

import java.util.stream.Stream;

public class FibonacciAB1 {
    public static void main(String[] args) {
        Stream.iterate(new Tuple<Integer>(0 , 1), t -> new Tuple<Integer>(t.t2, t.t2 + t.t1 )).limit(30).map(t -> t.t1).forEach(System.out::println);
    }
}
