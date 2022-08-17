///usr/bin/env jbang "$0" "$@" ; exit $?

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

public class IntroGarbageCollection {

    public static void main(String... args) throws InterruptedException {

        out.println("free memory at start: " + getFreeMemoryInMegabytes() + "M");

        List<MyClass> myClasses = IntStream.range(0, 1_000_000).boxed()
                .map(i -> new MyClass())
                .collect(toList());

        out.println("free memory after creating 1.000.000 MyClass instances: " + getFreeMemoryInMegabytes() + "M");

        myClasses = new ArrayList<>();
        out.println("free memory after clearing myClasses: " + getFreeMemoryInMegabytes() + "M");

        out.println("sleeping for 5 seconds");
        Thread.sleep(5000);

        out.println("free memory after sleeping for 5 second: " + getFreeMemoryInMegabytes() + "M");

        myClasses = IntStream.range(0, 500_000).boxed()
                .map(i -> new MyClass())
                .collect(toList());

        out.println("free memory after creating 500.000 MyClass instances: " + getFreeMemoryInMegabytes() + "M");

        System.gc();

        out.println("free memory after gc: " + getFreeMemoryInMegabytes() + "M");
    }

    private static long getFreeMemoryInMegabytes() {
        return (Runtime.getRuntime().freeMemory() / 1024) / 1024;
    }

    public static class MyClass {
        long a;
        long aa;
        long aaa;
        long aaaa;
        long aaaaa;
        long aaaaaa;
        long aaaaaaa;
        long aaaaaaaa;
        long aaaaaaaaa;
        long aaaaaaaaaa;
    }

}
