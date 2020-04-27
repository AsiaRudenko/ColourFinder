import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Egor Ushakov 4 Feb 2020
 */
public class IJ_Debugger_Professional {
    /**
     * <div style="font-size: 120%;">Loose ends</div>
     * <ul>
     * <li>Caller filter</li>
     * <li>also with a pattern</li>
     * </ul>
     */
    public static class Warmup {
        public static void main(String[] args) {
            warmup();

            realWork();
        }

        private static void realWork() {
            for (int i = 0; i < 10; i++) {
                work();
            }
        }

        private static void warmup() {
            for (int i = 0; i < 10; i++) {
                work();
            }
        }

        private static void work() {
            // do something
            int a = 5;
        }
    }

    /**
     * <div style="font-size: 120%;">Loose ends</div>
     * <ul>
     * <li>Renderers for primitives and arrays (builtin and custom)</li>
     * </ul>
     */
    public static class Primitives {
        public static void main(String[] args) {
            int a = 100;
            System.out.println(a);
        }
    }

    /**
     * <div style="font-size: 120%;">Loose ends</div>
     * <ul>
     * <li>Builtin renderers for images, graphics objects</li>
     * </ul>
     */
    public static class Images {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                ImageIcon image = new ImageIcon("/Users/Egor.Ushakov/Downloads/jb.jpg");
                System.out.println("Size: width=" + image.getIconWidth() + " height=" + image.getIconHeight());

                JFrame frame = new JFrame("IconTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JButton label = new JButton("JB Icon");
                label.setIcon(image);
                frame.getContentPane().add(label);
                frame.pack();
                frame.setVisible(true);
            });
        }
    }

    /**
     * <div style="font-size: 120%;">Loose ends</div>
     * <ul>
     * <li>Breakpoints in lambdas</li>
     * <li>Step into lambdas</li>
     * </ul>
     */
    public static class Stream3 {
        public static void main(String[] args) {
            Object[] res = Stream.of(1, 2, 3, 4, 5, 6, 7, 8).filter(i -> i % 2 == 0).filter(i -> i > 3).peek(k -> {}).toArray();
            System.out.println(Arrays.toString(res));
        }
    }

    /**
     * <div style="font-size: 120%;">Loose ends</div>
     * <ul>
     * <li>Restore deleted breakpoint</li>
     * </ul>
     */
    public static class Restore {
        public static void main(String[] args) {
            System.out.println("Hello");
        }
    }

    /**
     * <div style="font-size: 120%;">Mark object</div>
     * <ul>
     * <li>To highlight in different contexts</li>
     * <li>To access anywhere with _DebugLabel</li>
     * </ul>
     */
    public static class Marks {
        public static void main(String[] args) {
            String x = "My precious";
            System.out.println(x);
        }
    }

    /**
     * <div style="font-size: 120%;">Memory view</div>
     * <ul>
     * <li>Find any object in the heap</li>
     * <li>Show referring objects</li>
     * <li>You can use mark object</li>
     * <li>Track new objects</li>
     * </ul>
     */
    public static class Memory {
        public static void main(String[] args) {
            Object x = new Memory();
            Object y = new Memory();
            Object z = new Memory();
            System.out.println(x);
        }
    }

    /**
     * <div style="font-size: 120%;">Pro settings</div>
     * <ul>
     * <li>Remove breakpoints with drag (or middle click)</li>
     * <li>Reduce tooltip delay</li>
     * <li>Show value tooltip on code selection (for keyboard lovers)</li>
     * <li>Disable hide null elements in arrays</li>
     * </ul>
     */
    static class Settings {
        public static void main(String[] args) {
            int abs = 5;
            List<Integer> list = Arrays.asList(null, 2, null, 3);
            System.out.println(abs + 5);
        }
    }

    /**
     * <div style="font-size: 120%;">Hotswap</div>
     * <ul>
     * <li>Change method bodies on the fly</li>
     * <li>Valid on the next method entry</li>
     * <li>Could be "forced" with drop frame</li>
     * </ul>
     */
    public static class Hotswap {
        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                foo(i);
            }
        }

        private static void foo(int i) {
            System.out.println(i+100);
        }
    }

    /**
     * <div style="font-size: 120%;">Hotswap</div>
     * <ul>
     * <li>Emulate breakpoint conditions</li>
     * </ul>
     */
    public static class Condition {
        public static void main(String[] args) {
            for (int i = 0; i < 1_000_000; i++) {
                foo(i);
            }
        }

        private static void foo(int i) {
            System.out.println(i+1000);
            if (i == 100_000) {
                int a = 5;
            }
        }
    }

    /**
     * <div style="font-size: 120%;">Attach to process</div>
     * <ul>
     * <li>Easily locate local processes</li>
     * <li>Read only</li>
     * <li>Attach from the toolwindow</li>
     * <li>Subprocesses debug with JAVA_TOOL_OPTIONS</li>
     * </ul>
     */
    public static class Subprocesses {
        public static void main(String[] args) throws IOException {
            Process process = Runtime.getRuntime().exec("/Users/Egor.Ushakov/Library/Java/JavaVirtualMachines/openjdk-13.0.2-2/bin/jdb");
            while (true) {
                System.in.read();
            }
        }
    }

    /**
     * <div style="font-size: 120%;">Debug decompiled code</div>
     * <ul>
     * <li>Useful when you do not have the source code</li>
     * <li>Please no hacking</li>
     * </ul>
     */
    int idea;
    /**
     * <div style="font-size: 120%;">Async stack traces</div>
     * <ul>
     * <li>Shows the flow of the async execution</li>
     * <li>Presets for standard executors</li>
     * </ul>
     */
    public static class Async {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            CompletableFuture.runAsync(Async::getLine).get();
        }

        private static void getLine() {
            try {
                CompletableFuture.runAsync(Async::getLine2).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        private static void getLine2() {
            System.out.println("Line2");
        }
    }

    /**
     * <div style="font-size: 120%;">Async stack traces</div>
     * <ul>
     * <li>Custom setup with annotations</li>
     * </ul>
     */
    /*public static class AsyncSchedulerExample {
        private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        public static void main(String[] args) throws InterruptedException {
            new Thread(() -> {
                try {
                    while (true) {
                        process(queue.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            schedule(1);
            schedule(2);
            schedule(3);
        }

*//*
        private static void schedule(@org.jetbrains.annotations.Async.Schedule Integer i) throws InterruptedException {
            System.out.println("Scheduling " + i);
            queue.put(i);
        }

        private static void process(@org.jetbrains.annotations.Async.Execute Integer i) {
            System.out.println("Processing " + i);
        }
*//*
    }
*/
    /**
     * <div style="font-size: 120%;">Stream debugger</div>
     * <ul>
     * <li>Understand what's happening inside the stream</li>
     * </ul>
     */
    public static class StreamDebuggerExample {
        private static IntStream factorize(int value) {
            List<Integer> factors = new ArrayList<>();
            for (int i = 2; i <= value; i++) {
                while (value % i == 0) {
                    factors.add(i);
                    value /= i;
                }
            }
            return factors.stream().mapToInt(Integer::intValue);
        }

        public static void main(String[] args) {
            int[] result = IntStream.of(10, 87, 97, 43, 121, 20)
                    .flatMap(StreamDebuggerExample::factorize)
                    .distinct()
                    .sorted()
                    .toArray();
            System.out.println(Arrays.toString(result));
        }
    }

    /**
     * <div style="font-size: 120%;">Overhead view</div>
     * <ul>
     * <li>Breakpoint conditions & log overhead</li>
     * <li>Renderers overhead</li>
     * <li>Watch return value overhead</li>
     * </ul>
     */
    public static class Overhead {
        public static void main(String[] args) {
            for (int i = 0; i < 1_000_000; i++) {
                if (args.hashCode() < 0) {
                    break;
                }
            }
        }
    }

    /**
     * <div style="font-size: 120%;">Class level watches</div>
     * <ul>
     * <li>Expression that is important for a specific type</li>
     * <li>For a field - use pin to top</li>
     * </ul>
     */
    public static class ClassLevelWatch {
        public static void main(String[] args) {
            String a = "abcde";
        }
    }

    /**
     * <div style="font-size: 120%;">You can not evaluate after pause</div>
     * <ul>
     * <li>But there's a workaround - do a simple step</li>
     * </ul>
     */
    public static class Pause {
        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1000);
            }
        }
    }

    /**
     * <div style="font-size: 120%;">Thank you</div>
     * <ul>
     * <li>Debug like a pro</li>
     * </ul>
     */
    int questions;

}