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




/* branch and push 테스트를 위한 수정*/




public class Screen extends Canvas implements KeyListener, ComponentListener {
	private Image bufferedImage;
	private Graphics bufferGraphics;
	private Dimension dim;
	private Character player1; //플레이어1 생성
	private Character player2; //플레이어2 생성
	
	public Screen() {
		player1 = new Dao(this); //플레이어1에 다오 생성
		player2 = new Dao(this); //플레이어2에 다오 생성
		addKeyListener(this);
		addComponentListener(this);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001초 주기로 repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
		System.out.println("커밋 테스트");
		System.out.println("커밋 테스트");
		System.out.println("커밋 테스트");
		System.out.println("커밋 테스트2");
	}
	
	public void paint(Graphics g) {//스크린에 그리는 부분
		initBufferd();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		bufferGraphics.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);//player1 이미지 생성
		bufferGraphics.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);//player2 이미지 생성
		g.drawImage(this.bufferedImage, 0, 0, this);
	}
	
	public void update(Graphics g) {//업데이트 함수
		paint(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {//player1에 대한 움직임
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
		switch(e.getKeyCode()) {//player2에 대한 움직임
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
	
	private void initBufferd() {//버퍼 초기화
		this.dim = getSize();
		this.bufferedImage = createImage(dim.width, dim.height);
		this.bufferGraphics = this.bufferedImage.getGraphics();
	}

	@Override
	public void componentResized(ComponentEvent e) {//창 크기가 바뀔때 버퍼 초기화
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
