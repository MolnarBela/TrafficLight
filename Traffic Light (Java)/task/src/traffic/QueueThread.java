package traffic;

public class QueueThread  extends Thread {
    private final int roads;
    private final int intervals;
    private boolean running = true;
    private String[] circularQueue;
    int front, rear;

    public QueueThread(int roads, int intervals) {
        this.setName("QueueThread");
        this.roads = roads;
        this.intervals = intervals;
        this.circularQueue = new String[roads];
        this.front = 0;
        this.rear = 0;
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
                System.out.println();
                printRoads();
                System.out.println();
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
    public void addRoad(String name) {
        int next = (rear + 1) % roads;
        if (rear == 0 && front == 0 && isEmpty()) {
                circularQueue[0] = name;
                System.out.println(name + " Added!");
        } else {
            if (front == next) {
                System.out.println("Queue is full");
            } else {
                circularQueue[next] = name;
                System.out.println(name + " Added!");
                rear = next;
            }
        }
    }
    public void deleteRoad() {
        if (isEmpty()) {
            System.out.println("queue is empty");
        } else {
            String name = circularQueue[front];
            circularQueue[front] = "";
            if (front != rear) {
                front = (front + 1) % roads;
            }
            System.out.println(name + " deleted!");
        }
    }
    private void printRoads() {
        int index = front;
        for (int i = 0; i < roads; i++) {
            if (null != circularQueue[index] && !circularQueue[index].isEmpty()) {
                System.out.println(circularQueue[index]);
            }
            index = (index + 1) % roads;
        }

    }

    private boolean isEmpty() {
        return front == rear && (circularQueue[front] == null || circularQueue[front].isEmpty());
    }

}
