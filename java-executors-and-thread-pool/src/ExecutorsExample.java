import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rajeevkumarsingh on 09/05/17.
 */
public class ExecutorsExample {
    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Executor Service...");
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("Creating a Runnable...");
        Runnable runnable = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        System.out.println("Submit the task specified by the runnable to the executor service.");
        executorService.submit(runnable);

        System.out.println("Creating another Runnable...");
        Runnable runnable2 = () -> {
            System.out.println("Executing second task inside : " + Thread.currentThread().getName());
        };

        System.out.println("Submitting second runnable.");
        executorService.submit(runnable2);
    }
}
