import java.awt.Image;
import javax.swing.ImageIcon;

public class Dao extends Character {

	private Image daoImg;
	private int X;
	private int Y;
	private int step;
	private ImageIcon dao1 = new ImageIcon("Resources/dao1.png");
	private ImageIcon dao2 = new ImageIcon("Resources/dao2.png");
	private ImageIcon dao3 = new ImageIcon("Resources/dao3.png");
	private ImageIcon dao4 = new ImageIcon("Resources/dao4.png");
	

	public Dao(Screen screen, int x, int y) {
		super(screen);
		// TODO Auto-generated constructor stub
		this.daoImg = dao2.getImage(); /*기본값은 앞모습*/
		this.X = x;//초기 X값
		this.Y = y;//초기 Y값
		this.step = 5;//초기 이동 거리
	}
	
	public Image getImg() {//이미지를 스크린에 주기위한 함수
		return this.daoImg;
	}
	
	public int getX() {//X값을 스크린에 주기위한 함수
		return this.X;
	}
	
	public int getY() {//Y값을 스크린에 주기위한 함수
		return this.Y;
	}
	
	public void up() {//위로 가기
		Y-=step;
		this.daoImg = dao3.getImage();
	}
	public void down() {//아래로 가기
		Y+=step;
		this.daoImg = dao2.getImage();
	}
	public void left() {//왼쪽으로 가기
		X-=step;
		this.daoImg = dao4.getImage();
	}
	public void right() {//오른쪽으로 가기
		X+=step;
		this.daoImg = dao1.getImage();
	}

}
