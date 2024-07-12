import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Throughput {

    public static void main(String[] args) {
        // Number of threads and tasks
        int numberOfThreads = 100; // This can be adjusted as needed

        // Measure throughput for Platform Threads
        System.out.println("Platform Threads Test:");
        measureThroughput(Executors.newFixedThreadPool(numberOfThreads));

        // Measure throughput for Virtual Threads (Uncomment for Java versions supporting virtual threads)
        System.out.println("Virtual Threads Test:");
        measureThroughput(Executors.newVirtualThreadPerTaskExecutor());
    }

    private static void measureThroughput(ExecutorService executor) {
        AtomicInteger completedTasks = new AtomicInteger();
        Instant start = Instant.now();

        for (int i = 0; i < numberOfTasks; i++) {
            executor.execute(() -> {
                performTask();
                completedTasks.incrementAndGet();
            });
        }

        // Wait for all tasks to complete
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Waiting for completion
        }

        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("Completed " + completedTasks.get() + " tasks in " + duration + " ms.");
    }

    private static void performTask() {
        // Simulate a task (e.g., a simple computation)
        double result = 0;
        for (int i = 0; i < 1000; i++) {
            result += Math.sin(i);
        }
    }
}
