import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Screen extends Canvas implements KeyListener, ComponentListener {
	private Image bufferedImage;
	private Graphics bufferGraphics;
	private Dimension dim;
	private Character player1; //�÷��̾�1 ����
	private Character player2; //�÷��̾�2 ����
	
	public Screen() {
		player1 = new Dao(this); //�÷��̾�1�� �ٿ� ����
		player2 = new Dao(this); //�÷��̾�2�� �ٿ� ����
		addKeyListener(this);
		addComponentListener(this);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001�� �ֱ�� repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//��ũ���� �׸��� �κ�
		initBufferd();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		bufferGraphics.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);//player1 �̹��� ����
		bufferGraphics.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);//player2 �̹��� ����
		g.drawImage(this.bufferedImage, 0, 0, this);
	}
	
	public void update(Graphics g) {//������Ʈ �Լ�
		paint(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {//player1�� ���� ������
		case KeyEvent.VK_UP:
			if(player1.getY()>=5) {
				player1.up();
			}
			break;
		case KeyEvent.VK_DOWN:
			if(player1.getY()<=520) {
				player1.down();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(player1.getX()>=5) {
				player1.left();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(player1.getX()<=740) {
				player1.right();
			}
			break;
		}
		switch(e.getKeyCode()) {//player2�� ���� ������
		case KeyEvent.VK_W:
			if(player2.getY()>=5) {
				player2.up();
			}
			break;
		case KeyEvent.VK_S:
			if(player2.getY()<=520) {
				player2.down();
			}
			break;
		case KeyEvent.VK_A:
			if(player2.getX()>=5) {
				player2.left();
			}
			break;
		case KeyEvent.VK_D:
			if(player2.getX()<=740) {
				player2.right();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void initBufferd() {//���� �ʱ�ȭ
		this.dim = getSize();
		this.bufferedImage = createImage(dim.width, dim.height);
		this.bufferGraphics = this.bufferedImage.getGraphics();
	}

	@Override
	public void componentResized(ComponentEvent e) {//â ũ�Ⱑ �ٲ� ���� �ʱ�ȭ
		// TODO Auto-generated method stub
		initBufferd();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
