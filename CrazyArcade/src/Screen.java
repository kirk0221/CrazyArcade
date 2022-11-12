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
	
	public int map_selection;//어떤 맵이 골라졌는지
	public int[][] map_size;//맵 사이즈 설정을 위한 배열
	
	private Character player1; //플레이어1 생성
	private Character player2; //플레이어2 생성
	WaterBalloon player1WaterBalloon; //플레이어1 물풍선 생성
	WaterBalloon player2WaterBalloon; //플레이어2 물풍선 생성
	
	
	private Image map_CookieBackground = new ImageIcon("Resources/mapCookie.png").getImage();//쿠키(맵0) 이미지
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//해적(맵1) 이미지
	
	public Screen(int map) {
		this.map_selection = map; //생성자를 통해 어떤 맵 설정되었는지 받아오기 위함
		player1 = new Bazzi(this); //플레이어1에 다오 생성
		player2 = new Bazzi(this); //플레이어2에 다오 생성
		addKeyListener(this);
		addComponentListener(this);

		this.map_size = new int[13][13];//맵 사이즈 13*13
		for(int i=0; i<13;i++) {//맵 0으로 초기화
			for(int j=0; j<13; j++) {
				this.map_size[i][j] = 0;
			}
		}
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001초 주기로 repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//스크린에 그리는 부분
		initBufferd();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		if(map_selection == 0) {//쿠키맵일때 배경
			bufferGraphics.drawImage(map_CookieBackground,0,0,this);
		}else if(map_selection == 1) {//해적맵일때 배경
			bufferGraphics.drawImage(map_PatriotsBackground,0,0,this);
		}
		bufferGraphics.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);//player1 이미지 생성
		bufferGraphics.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);//player2 이미지 생성
		g.drawImage(this.bufferedImage, 0, 0, this);
		this.characterin();
	}
	
	public void characterin() {//캐릭터가 현재 맵의 어느 배열위치에 있는지 확인
		int p1_x=player1.getX()/50;//이거 나눠서 나오는 값이랑 그림에서 나오게 되는 좌표랑 같게 연동시켜줘야함(김준씨 부탁해요)
		int p1_y=player1.getY()/50;//이값도 바꿔야됨
		System.out.println("p1의 위치는 : ("+player1.getX()+","+player1.getY()+")"+"p1의 인덱스 위치는 : ("+p1_x+","+p1_y+")");
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
			if(player1.getY()>=0) {
				player1.up();
			}
			break;
		case KeyEvent.VK_DOWN:
			if(player1.getY()<=800) {// 창현아 여기 숫자 바꿔서 안나가게 딱 막아줘
				player1.down();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(player1.getX()>=0) {
				player1.left();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(player1.getX()<=800) {// 창현아 여기 숫자 바꿔서 안나가게 딱 막아줘
				player1.right();
			}
			break;
		case KeyEvent.VK_SHIFT:
			player1WaterBalloon = new WaterBalloon();
			player1WaterBalloon.makeWaterBalloon(player1.getX(), player1.getY());//물풍선 놓기
			break;
		}
		switch(e.getKeyCode()) {//player2에 대한 움직임
		case KeyEvent.VK_W:
			if(player2.getY()>=0) {
				player2.up();
			}
			break;
		case KeyEvent.VK_S:
			if(player2.getY()<=800) {// 창현아 여기 숫자 바꿔서 안나가게 딱 막아줘
				player2.down();
			}
			break;
		case KeyEvent.VK_A:
			if(player2.getX()>=0) {
				player2.left();
			}
			break;
		case KeyEvent.VK_D:
			if(player2.getX()<=800) {// 창현아 여기 숫자 바꿔서 안나가게 딱 막아줘
				player2.right();
			}
			break;
		case KeyEvent.VK_SHIFT:
			player2WaterBalloon = new WaterBalloon();
			player2WaterBalloon.makeWaterBalloon(player2.getX(), player2.getY());//물풍선 놓기
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
