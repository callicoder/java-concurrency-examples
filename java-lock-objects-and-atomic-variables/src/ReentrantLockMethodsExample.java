import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockMethodsCounter {
    private final ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    public int incrementAndGet() {
        // Check if the lock is currently acquired by any thread
        System.out.println("IsLocked : " + lock.isLocked());

        // Check if the lock is acquired by the current thread itself.
        System.out.println("IsHeldByCurrentThread : " + lock.isHeldByCurrentThread());

        // Try to acquire the lock
        boolean isAcquired;
        try {
            isAcquired = lock.tryLock(1, TimeUnit.SECONDS);
            System.out.println("Lock Acquired : " + isAcquired + "\n");
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        if(isAcquired) {
            try {
                Thread.sleep(2000);
                count = count + 1;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                lock.unlock();
            }
        }
        return count;
    }
}

public class ReentrantLockMethodsExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ReentrantLockMethodsCounter lockMethodsCounter = new ReentrantLockMethodsCounter();

        executorService.submit(() -> {
           System.out.println("IncrementCount (First Thread) : " +
                   lockMethodsCounter.incrementAndGet() + "\n");
        });

        executorService.submit(() -> {
            System.out.println("IncrementCount (Second Thread) : " +
                    lockMethodsCounter.incrementAndGet() + "\n");
        });

        executorService.shutdown();
    }
}
