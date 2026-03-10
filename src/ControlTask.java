public class ControlTask implements Runnable {

    private final boolean[] stopFlags;
    private final int[] timeDelay;
    private final Object consoleLock;

    public ControlTask(boolean[] stopFlags, int[] timeDelay, Object consoleLock) {
        this.stopFlags = stopFlags;
        this.timeDelay = timeDelay;
        this.consoleLock = consoleLock;
    }

    @Override
    public void run() {
        for (int i = 0; i < stopFlags.length; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    Thread.sleep(timeDelay[id]);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                stopFlags[id] = true;
            }).start();
        }
    }
}