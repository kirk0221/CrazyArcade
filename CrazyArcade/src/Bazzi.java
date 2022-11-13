import java.awt.Image;
import javax.swing.ImageIcon;

public class Bazzi extends Character {

	private Image[] bazzi_state;
	private int state;//상태 번호
	private int X;
	private int Y;
	private int step;
	public int die;
	

	public Bazzi(Screen screen) {
		super(screen);
		// TODO Auto-generated constructor stub
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
		this.X = 400;//초기 X값
		this.Y = 300;//초기 Y값
		this.step = 5;//초기 이동 거리
		this.die = 0;//아직 죽지 않았음
	}
	
	public Image getImg() {//이미지를 스크린에 주기위한 함수
		return this.bazzi_state[state];
	}
	
	public int getX() {//X값을 스크린에 주기위한 함수
		return this.X;
	}
	
	public int getY() {//Y값을 스크린에 주기위한 함수
		return this.Y;
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

}
