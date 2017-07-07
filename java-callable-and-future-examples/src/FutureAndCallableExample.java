import java.util.concurrent.*;

/**
 * Created by rajeevkumarsingh on 11/05/17.
 */
public class FutureAndCallableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            // Perform some computation
	    System.out.println("Entered Callable");
            Thread.sleep(2000);
            return "Hello from Callable";
        };

	System.out.println("Submitting Callable");	
        Future<String> future = executorService.submit(callable);
        // This line executes immediately
        System.out.println("Do something else while callable is getting executed");

        System.out.println("Retrieve the result of the future");
        // Future.get() blocks until the result is available
        String result = future.get();
        System.out.println(result);

        executorService.shutdown();
    }
}
