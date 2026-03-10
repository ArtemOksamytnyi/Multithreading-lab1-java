import java.util.Scanner;

public class Main {

    private static final int n = 12;
    private static final Object consoleLock = new Object();

    public static void main(String[] args) throws InterruptedException {

        boolean[] stopFlags = new boolean[n];
        int[] timeDelay = new int[n];
        Thread[] workers = new Thread[n];

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < n; i++) {
            System.out.print("Введіть час для виконання " + (i + 1) + " потоку (мс): ");
            timeDelay[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            workers[i] = new Thread(new WorkerTask(i, stopFlags, consoleLock));
            workers[i].start();
        }

        Thread controller = new Thread(
                new ControlTask(stopFlags, timeDelay, consoleLock)
        );
        controller.start();

        for (int i = 0; i < n; i++) {
            workers[i].join();
        }

    }
}