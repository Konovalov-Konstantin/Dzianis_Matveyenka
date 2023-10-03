package org.example.java.generics.parametrizedclasses;

public class ParametrizedClass2 {
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Test", 10);
        System.out.println(pair.getValue1() + " : " + pair.getValue2());

        Pair<Integer, Double> pair2 = new Pair<>(20, 10.2);
        System.out.println(pair2.getValue1() + " : " + pair2.getValue2());
    }
}

class Pair<V1, V2> {
    private V1 value1;
    private V2 value2;

    public Pair(V1 value1, V2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public V1 getValue1() {
        return value1;
    }

    public V2 getValue2() {
        return value2;
    }
}
