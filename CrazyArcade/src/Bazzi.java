import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Bazzi extends Character implements KeyListener{

	private Image bazziImg;
	private int X;
	private int Y;
	private int step;
	public int die;
	public int playertype;
	WaterBalloon playerWaterBalloon;
	

	public Bazzi(Screen screen, int playertype) { /*플레이어 타입을 전달받아, 해당 타입에 따라 키에 대한 동작이 다르도록 함*/
		super(screen);
		// TODO Auto-generated constructor stub
		this.bazziImg = new ImageIcon("Resources/bazzi_front.png").getImage();
		this.X = 400;//초기 X값
		this.Y = 300;//초기 Y값
		this.step = 5;//초기 이동 거리
		this.die = 0;//아직 죽지 않았음
		this.playertype = playertype;
		playerWaterBalloon = new WaterBalloon(playertype); /* 물풍선 생성*/
	}
	
	public Image getImg() {//이미지를 스크린에 주기위한 함수
		return this.bazziImg;
	}
	
	public Image getballoonImg() {//이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getImg();
	}
	
	public int getballoonX(int i) {//물풍선 X값을 스크린에 주기위한 함수
		return playerWaterBalloon.getX(i);
	}
	
	public int getballoonY(int i) {//물풍선 Y값을 스크린에 주기위한 함수
		return playerWaterBalloon.getY(i);
	}
	
	
	public int getX() {//X값을 스크린에 주기위한 함수
		return this.X;
	}
	
	public int getY() {//Y값을 스크린에 주기위한 함수
		return this.Y;
	}
	
	public int getballonListsize() {
		return playerWaterBalloon.balloonXList.size();
		/*물풍선 객체의 링크드 리스트의 크기를 스크린에 전달하여, 반복문의 반복 휫수를 지정하기 위한 함수*/
	}
	
	public void up() {//위로 가기
		Y-=step;
	}
	public void down() {//아래로 가기
		Y+=step;
	}
	public void left() {//왼쪽으로 가기
		X-=step;
	}
	public void right() {//오른쪽으로 가기
		X+=step;
	}
	
	public void die() { //죽었을 경우
		this.die = 1;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(playertype == 1) {
			switch(e.getKeyCode()) {//player1에 대한 움직임
			case KeyEvent.VK_UP:
				if(this.getY()>=0) {
					this.up();
				}
				break;
			case KeyEvent.VK_DOWN:
				if(this.getY()<=700) {
					this.down();
				}
				break;
			case KeyEvent.VK_LEFT:
				if(this.getX()>=0) {
					this.left();
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(this.getX()<=720) {
					this.right();
				}
				break;
			case KeyEvent.VK_SHIFT:
				playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY());//물풍선 놓기
				break;
			}
		}else if(playertype == 2) {
			switch(e.getKeyCode()) {//player2에 대한 움직임
			case KeyEvent.VK_W:
				if(this.getY()>=0) {
					this.up();
				}
				break;
			case KeyEvent.VK_S:
				if(this.getY()<=700) {
					this.down();
				}
				break;
			case KeyEvent.VK_A:
				if(this.getX()>=0) {
					this.left();
				}
				break;
			case KeyEvent.VK_D:
				if(this.getX()<=720) {
					this.right();
				}
				break;
			case KeyEvent.VK_SPACE:
				playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY());//물풍선 놓기
				break;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}