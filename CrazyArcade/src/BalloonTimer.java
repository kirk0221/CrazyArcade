import java.util.Timer;
import java.util.TimerTask;

public class BalloonTimer {
	public static int count;
	public int balloonXindex;
	public int balloonYindex;
	Timer timer = new Timer();
	
	
	public BalloonTimer(int i) {
		count = i;

		timer.schedule(task,i);
	}
	
	TimerTask task = new TimerTask() {
		@Override
	    public void run() {
			
	    	WaterBalloon.balloonXList.remove(0);
	    	WaterBalloon.balloonYList.remove(0);
	    }
	};
}