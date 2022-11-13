import java.awt.Image;
import javax.swing.ImageIcon;

public class Bazzi extends Character {

	private Image[] bazzi_state;
	private int state;//���� ��ȣ
	private int X;
	private int Y;
	private int step;
	public int die;
	

	public Bazzi(Screen screen) {
		super(screen);
		// TODO Auto-generated constructor stub
		this.bazzi_state = new Image[4];
		Image bazzi_front = new ImageIcon("Resources/bazzi_front.png").getImage();//���� ���� �̹���
		Image bazzi_back = new ImageIcon("Resources/bazzi_back.png").getImage();//���� �ĸ� �̹���
		Image bazzi_left = new ImageIcon("Resources/bazzi_left.png").getImage();//���� ������ �̹���
		Image bazzi_right = new ImageIcon("Resources/bazzi_right.png").getImage();//���� ������ �̹���
		this.bazzi_state[0] = bazzi_front;
		this.bazzi_state[1] = bazzi_back;
		this.bazzi_state[2] = bazzi_left;
		this.bazzi_state[3] = bazzi_right;
		this.state = 0;//�ʱ� �������� ��������
		this.X = 400;//�ʱ� X��
		this.Y = 300;//�ʱ� Y��
		this.step = 5;//�ʱ� �̵� �Ÿ�
		this.die = 0;//���� ���� �ʾ���
	}
	
	public Image getImg() {//�̹����� ��ũ���� �ֱ����� �Լ�
		return this.bazzi_state[state];
	}
	
	public int getX() {//X���� ��ũ���� �ֱ����� �Լ�
		return this.X;
	}
	
	public int getY() {//Y���� ��ũ���� �ֱ����� �Լ�
		return this.Y;
	}
	
	public void up() {//���� ����
		this.state  = 1;
		Y-=step;
	}
	public void down() {//�Ʒ��� ����
		this.state  = 0;
		Y+=step;
	}
	public void left() {//�������� ����
		this.state  = 2;
		X-=step;
	}
	public void right() {//���������� ����
		this.state  = 3;
		X+=step;
	}
	
	public void die() { //�׾��� ���
		this.die = 1;
	}

}
