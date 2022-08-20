///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

@Command(name = "IntroGarbageCollection", mixinStandardHelpOptions = true, version = "IntroGarbageCollection 0.1",
        description = "IntroGarbageCollection made with jbang")
class MemoryLeak implements Callable<Integer> {

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

    private List<MyClass> myClasses = new ArrayList<>();

    public static void main(String... args) {
        int exitCode = new CommandLine(new MemoryLeak()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {

        out.println("free memory at start: " + getFreeMemoryInMegabytes() + "M");

        for (int i = 0; i < iterations; i++) {

            if (!myClasses.isEmpty()) {
                int objectsToRemove = myClasses.size() / 4;
                out.println("removing " + objectsToRemove + " from list");
                myClasses.subList(0, objectsToRemove).clear();
            }

            int objectsToGenerate = (int) (Math.random() * (1_000_000 - 500_000) + 500_000);
            myClasses.addAll(IntStream.range(0, objectsToGenerate)
                    .boxed()
                    .map(MyClass::new)
                    .collect(toList()));

            // print the free memory
            out.println("free memory after creating [" + objectsToGenerate + "] objects: " + getFreeMemoryInMegabytes() + "M");

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
        private int id;
        private long a;
        private long aa;
        private long aaa;
        private long aaaa;
        private long aaaaa;
        private long aaaaaa;
        private long aaaaaaa;
        private long aaaaaaaa;

        public MyClass(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
