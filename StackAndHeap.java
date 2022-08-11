///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 11

public class StackAndHeap {

    private int primitiveInTheHeap = 100;
    private String stringOnTheHeap = "hello";

    public String toString() {
        return "StackAndHeap{" +
                "primitiveInTheHeap=" + primitiveInTheHeap +
                ", stringOnTheHeap='" + stringOnTheHeap + '\'' +
                '}';
    }

    public static void main(String... args) {
        int localVariableInTheStack = 40;
        Integer boxedLocalVariableInTheHeap = 50;
        String localStringInTheHeap = "world";

        StackAndHeap stackAndHeap = new StackAndHeap();
        System.out.println(stackAndHeap);
    }
}
