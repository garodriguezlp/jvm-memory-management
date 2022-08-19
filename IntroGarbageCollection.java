///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

@Command(name = "IntroGarbageCollection", mixinStandardHelpOptions = true, version = "IntroGarbageCollection 0.1",
        description = "IntroGarbageCollection made with jbang")
class IntroGarbageCollection implements Callable<Integer> {

    @Option(names = {"-n", "--number"},
            description = "number of iterations",
            required = true,
            defaultValue = "60")
    private int iterations;

    @Option(names = {"-p", "--pause"},
            description = "pause time",
            required = true,
            defaultValue = "2000")
    private int pause;

    public static void main(String... args) {
        int exitCode = new CommandLine(new IntroGarbageCollection()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {

        out.println("free memory at start: " + getFreeMemoryInMegabytes() + "M");

        for (int i = 0; i < iterations; i++) {

            // create a list of MyClass objects
            int randomNumber = (int) (Math.random() * (1_000_000 - 100_000) + 100_000);
            List<MyClass> myClasses = IntStream.range(0, randomNumber)
                    .boxed()
                    .map(n -> new MyClass())
                    .collect(toList());

            // print the free memory
            out.println("free memory after creating [" + randomNumber + "] objects: " + getFreeMemoryInMegabytes() + "M");

            // sleep for the pause time
            Thread.sleep(pause);
        }

        // Please, never do this in production code!
        System.gc();

        out.println("free memory after gc: " + getFreeMemoryInMegabytes() + "M");

        return 0;
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
    }
}
