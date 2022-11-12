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
	
	public int[][] map_size;//己 紫戚綜 竺舛聖 是廃 壕伸
	
	private Character player1; //巴傾戚嬢1 持失
	private Character player2; //巴傾戚嬢2 持失
	WaterBalloon player1WaterBalloon; //巴傾戚嬢1 弘燃識 持失
	WaterBalloon player2WaterBalloon; //巴傾戚嬢2 弘燃識 持失
	
	
	public Screen() {
		player1 = new Bazzi(this); //巴傾戚嬢1拭 陥神 持失
		player2 = new Bazzi(this); //巴傾戚嬢2拭 陥神 持失
		addKeyListener(this);
		addComponentListener(this);
		
		this.map_size = new int[13][13];//己 紫戚綜 13*13
		for(int i=0; i<13;i++) {//己 0生稽 段奄鉢
			for(int j=0; j<13; j++) {
				this.map_size[i][j] = 0;
			}
		}
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001段 爽奄稽 repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//什滴鍵拭 益軒澗 採歳
		initBufferd();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		bufferGraphics.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);//player1 戚耕走 持失
		bufferGraphics.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);//player2 戚耕走 持失
		g.drawImage(this.bufferedImage, 0, 0, this);
	}
	
	public void characterin() {//蝶遣斗亜 薄仙 己税 嬢汗 壕伸是帖拭 赤澗走 溌昔
		
	}
	
	public void update(Graphics g) {//穣汽戚闘 敗呪
		paint(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
//ししししけいし
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {//player1拭 企廃 崇送績
		case KeyEvent.VK_UP:
			if(player1.getY()>=0) {
				player1.up();
			}
			break;
		case KeyEvent.VK_DOWN:
			if(player1.getY()<=800) {
				player1.down();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(player1.getX()>=0) {
				player1.left();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(player1.getX()<=800) {
				player1.right();
			}
			break;
		case KeyEvent.VK_SHIFT:
			player1WaterBalloon = new WaterBalloon();
			player1WaterBalloon.makeWaterBalloon(player1.getX(), player1.getY());//弘燃識 兜奄
			break;
		}
		switch(e.getKeyCode()) {//player2拭 企廃 崇送績
		case KeyEvent.VK_W:
			if(player2.getY()>=0) {
				player2.up();
			}
			break;
		case KeyEvent.VK_S:
			if(player2.getY()<=800) {
				player2.down();
			}
			break;
		case KeyEvent.VK_A:
			if(player2.getX()>=0) {
				player2.left();
			}
			break;
		case KeyEvent.VK_D:
			if(player2.getX()<=800) {
				player2.right();
			}
			break;
		case KeyEvent.VK_SHIFT:
			player2WaterBalloon = new WaterBalloon();
			player2WaterBalloon.makeWaterBalloon(player2.getX(), player2.getY());//弘燃識 兜奄
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void initBufferd() {//獄遁 段奄鉢
		this.dim = getSize();
		this.bufferedImage = createImage(dim.width, dim.height);
		this.bufferGraphics = this.bufferedImage.getGraphics();
	}

	@Override
	public void componentResized(ComponentEvent e) {//但 滴奄亜 郊介凶 獄遁 段奄鉢
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
