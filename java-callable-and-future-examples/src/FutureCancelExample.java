import java.util.concurrent.*;

/**
 * Created by rajeevkumarsingh on 10/05/17.
 */
public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            // Perform some computation
            Thread.sleep(2000);
            return "Hello from Callable";
        };

        long startTime = System.nanoTime();
        Future<String> future = executorService.submit(callable);

        while(!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(200);
            double elapsedTimeInSec = (System.nanoTime() - startTime) / 1000000000.0;

            if (elapsedTimeInSec > 1) {
                // cancel future if the elapsed time is more than one second
                future.cancel(true);
            }
        }

        // Check if future is cancelled before retrieving the result
        if(!future.isCancelled()) {
            System.out.println("Task completed! Retrieving the result");
            // Future.get() blocks until the result is available
            String result = future.get();
            System.out.println(result);
        } else {
            System.out.println("Task was cancelled");
        }

        executorService.shutdown();
    }

}
