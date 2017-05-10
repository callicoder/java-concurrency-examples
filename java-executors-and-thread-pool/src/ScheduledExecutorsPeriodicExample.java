import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by rajeevkumarsingh on 10/05/17.
 */
public class ScheduledExecutorsPeriodicExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
          System.out.println("Executing Task At " + System.nanoTime());
        };

        System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
        scheduledExecutorService.scheduleAtFixedRate(task, 0,2, TimeUnit.SECONDS);

    }
}
