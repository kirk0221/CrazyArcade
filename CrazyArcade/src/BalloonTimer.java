import java.awt.Image;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class BalloonTimer {
	public static int count;
	Timer timer = new Timer();
	int remember_x;
	int remember_y;

	
	
	public BalloonTimer(int i) {
		count = i;
		timer.schedule(task,i);

	}
	public BalloonTimer() {
		timer.schedule(boom, 1000);
	}
	TimerTask task = new TimerTask() {
		@Override
	    public void run() {
			remember_x = WaterBalloon.balloonXList.get(0);
			remember_y = WaterBalloon.balloonYList.get(0);
	    	WaterBalloon.balloonXList.remove(0);
	    	WaterBalloon.balloonYList.remove(0);
	    	WaterBalloon.waterballoonmax += 1;;//��ǳ���� ������ ��ǳ�� ������ �ٽ� 1 �÷���
			WaterBalloon.boomballoonXList.add(remember_x); /*��ǳ�� x ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
			WaterBalloon.boomballoonYList.add(remember_y); /*��ǳ�� y ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
	    	new BalloonTimer();
	    }
	};
	
	TimerTask boom = new TimerTask() {
		@Override
	    public void run() {
	    	WaterBalloon.boomballoonXList.remove(0);
	    	WaterBalloon.boomballoonYList.remove(0);
	    }
	};
}