package UI;

public class TimerUI implements Runnable {
	
    private Integer timeInterval;
    private Runnable runnable;

    public TimerUI(Integer timeInterval, Runnable runnable) {
    	this.runnable = runnable;
        this.timeInterval = timeInterval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.timeInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runnable.run();
        }
    }

}