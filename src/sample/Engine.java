package sample;


public class Engine {

    private OnAction onAction;
    private int fps = 15;
    private Thread updateThread;
    private Thread physicsThread;
    private Thread timeThread;
    public volatile boolean isStopped = true;

    private volatile long time = 0;

    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }

    /**
     * @param fps set fps and we convert it to millisecond
     */
    public void setFps(int fps) {
        this.fps = 1000 / fps;
    }

    private Thread loop(String name, final Runnable body, final long sleepMs) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!isStopped && !Thread.currentThread().isInterrupted()) {
                        body.run();
                        Thread.sleep(sleepMs);
                    }
                } catch (InterruptedException e) {
                    // stopped
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, name);
        t.setDaemon(true);
        t.start();
        return t;
    }

    public void start() {
        time = 0;
        isStopped = false;
        onAction.onInit();
        updateThread = loop("update", new Runnable() {
            @Override
            public void run() {
                onAction.onUpdate();
            }
        }, fps);
        physicsThread = loop("physics", new Runnable() {
            @Override
            public void run() {
                onAction.onPhysicsUpdate();
            }
        }, fps);
        timeThread = loop("time", new Runnable() {
            @Override
            public void run() {
                time++;
                onAction.onTime(time);
            }
        }, 1);
    }

    public void stop() {
        if (!isStopped) {
            isStopped = true;
            Thread current = Thread.currentThread();
            for (Thread t : new Thread[]{updateThread, physicsThread, timeThread}) {
                if (t != null && t != current) {
                    t.interrupt();
                }
            }
        }
    }


    public interface OnAction {
        void onUpdate();

        void onInit();

        void onPhysicsUpdate();

        void onTime(long time);
    }

}
