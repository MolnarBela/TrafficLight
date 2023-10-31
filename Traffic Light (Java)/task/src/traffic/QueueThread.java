package traffic;

public class QueueThread  extends Thread {
    private final int roads;
    private final int intervals;
    private boolean running = true;

    public QueueThread(int roads, int intervals) {
        this.roads = roads;
        this.intervals = intervals;
        this.setName("QueueThread");
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        int time = 0;
        while (running) {
            if (Traffic.getState().equals(SystemState.SYSTEM)) {
                Traffic.clearTerminal();
                System.out.printf("! %ds. have passed since system startup !\n", time);
                System.out.printf("! Number of roads: %d !\n", roads);
                System.out.printf("! Interval: %d !\n", intervals);
                System.out.printf("! Press \"Enter\" to open menu !%n");
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ignored) {
                System.out.printf("! QueueThread interrupted !%n");
            }
            time++;
        }
    }
}
