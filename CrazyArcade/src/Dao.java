import java.awt.Image;
import javax.swing.ImageIcon;

public class Dao extends Character {

	private Image carImg;
	private int X;
	private int Y;
	private int step;
	

	public Dao(Screen screen) {
		super(screen);
		// TODO Auto-generated constructor stub
		this.carImg = new ImageIcon("Resources/car.png").getImage();
		this.X = 400;//초기 X값
		this.Y = 300;//초기 Y값
		this.step = 5;//초기 이동 거리
	}
	
	public Image getImg() {//이미지를 스크린에 주기위한 함수
		return this.carImg;
	}
	
	public int getX() {//X값을 스크린에 주기위한 함수
		return this.X;
	}
	
	public int getY() {//Y값을 스크린에 주기위한 함수
		return this.Y;
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

}
