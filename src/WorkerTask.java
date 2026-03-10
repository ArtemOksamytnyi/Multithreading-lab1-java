import java.math.BigDecimal;

public class WorkerTask implements Runnable {

    private final int id;
    private final boolean[] stopFlags;
    private final Object consoleLock;

    public WorkerTask(int id, boolean[] stopFlags, Object consoleLock) {
        this.id = id;
        this.stopFlags = stopFlags;
        this.consoleLock = consoleLock;
    }

    @Override
    public void run() {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal current = BigDecimal.ZERO;
        long count = 0;
        BigDecimal step = BigDecimal.valueOf(id + 1);

        do {
            sum = sum.add(current);
            current = current.add(step);
            count++;
        } while (!stopFlags[id]);

        synchronized (consoleLock) {
            System.out.println(
                    "Потік " + (id + 1) +
                            ": сума = " + sum +
                            ", кількість елементів = " + count
            );
        }
    }
}