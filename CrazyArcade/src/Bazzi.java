import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Bazzi extends Character implements KeyListener{

	private int X;
	private int Y;
	private int step;
	public int die;
	public int bombSize;
	public int playertype;
	WaterBalloon playerWaterBalloon;
	private Image[] bazzi_state;
	private int state;//상태 번호

	public Bazzi(Screen screen, int playertype) { /*플레이어 타입을 전달받아, 해당 타입에 따라 키에 대한 동작이 다르도록 함*/
		super(screen);
		// TODO Auto-generated constructor stub
		if(playertype == 1) {
			this.X = 400;//초기 X값
			this.Y = 300;//초기 Y값
		}else if(playertype == 2) {
			this.X = 300;//초기 X값
			this.Y = 400;//초기 Y값
		}
		this.step = 9;//초기 이동 거리
		this.die = 0;//아직 죽지 않았음
		this.bombSize = 1;//물줄기 크기 1
		this.playertype = playertype;
		playerWaterBalloon = new WaterBalloon(playertype); /* 물풍선 생성*/
		this.bazzi_state = new Image[4];
		Image bazzi_front = new ImageIcon("Resources/bazzi_front.png").getImage();//배찌 정면 이미지
		Image bazzi_back = new ImageIcon("Resources/bazzi_back.png").getImage();//배찌 후면 이미지
		Image bazzi_left = new ImageIcon("Resources/bazzi_left.png").getImage();//배찌 좌측면 이미지
		Image bazzi_right = new ImageIcon("Resources/bazzi_right.png").getImage();//배찌 우측면 이미지
		this.bazzi_state[0] = bazzi_front;
		this.bazzi_state[1] = bazzi_back;
		this.bazzi_state[2] = bazzi_left;
		this.bazzi_state[3] = bazzi_right;
		this.state = 0;//초기 정면으로 보고있음
	}
	
	public Image getImg() {//이미지를 스크린에 주기위한 함수
		return this.bazzi_state[state];
	}
	
	public Image getballoonImg() {//이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getImg();
	}
	
	public Image getcenterImg() {// 터진 물풍선 가운데 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getcenterImg();
	}
	
	public Image getleftImg() {// 터진 물풍선 왼쪽 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getleftImg();
	}
	
	public Image getrightImg() {// 터진 물풍선 오른쪽 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getrightImg();
	}
	
	public Image getupImg() {// 터진 물풍선 위 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getupImg();
	}
	public Image getdownImg() {// 터진 물풍선 아래 이미지를 스크린에 주기위한 함수
		return playerWaterBalloon.getdownImg();
	}
	
	public int getballoonX(int i) {//물풍선 X값을 스크린에 주기위한 함수
		return playerWaterBalloon.getX(i);
	}
	
	public int getballoonY(int i) {//물풍선 Y값을 스크린에 주기위한 함수
		return playerWaterBalloon.getY(i);
	}
	public int getboomballoonX(int i) {//터진 물풍선 X값을 스크린에 주기위한 함수
		return playerWaterBalloon.getboomX(i);
	}
	
	public int getboomballoonY(int i) {//터진 물풍선 Y값을 스크린에 주기위한 함수
		return playerWaterBalloon.getboomY(i);
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
	public int getboomballonListsize() {
		return playerWaterBalloon.boomballoonXList.size();
		/*터진 물풍선 객체의 링크드 리스트의 크기를 스크린에 전달하여, 반복문의 반복 휫수를 지정하기 위한 함수*/
	}
	
	public int getbombSize() {//물풍선 크기값을 스크린에 주기위한 함수
		return this.bombSize;
	}
	
	public void up() {//위로 가기
		this.state  = 1;
		Y-=step;
	}
	public void down() {//아래로 가기
		this.state  = 0;
		Y+=step;
	}
	public void left() {//왼쪽으로 가기
		this.state  = 2;
		X-=step;
	}
	public void right() {//오른쪽으로 가기
		this.state  = 3;
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
			case KeyEvent.VK_SPACE:
				playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize);//물풍선 놓기
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
			case KeyEvent.VK_SHIFT:
				playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize);//물풍선 놓기
				break;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}