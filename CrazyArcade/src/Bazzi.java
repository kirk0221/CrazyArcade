import java.awt.Image;
import javax.swing.ImageIcon;

public class Bazzi extends Character {

	private Image carImg;
	private int X;
	private int Y;
	private int step;
	public int die;
	

	public Bazzi(Screen screen) {
		super(screen);
		// TODO Auto-generated constructor stub
		this.carImg = new ImageIcon("Resources/car.png").getImage();
		this.X = 400;//�ʱ� X��
		this.Y = 300;//�ʱ� Y��
		this.step = 5;//�ʱ� �̵� �Ÿ�
		this.die = 0;//���� ���� �ʾ���
	}//������
	
	public Image getImg() {//�̹����� ��ũ���� �ֱ����� �Լ�
		return this.carImg;
	}
	
	public int getX() {//X���� ��ũ���� �ֱ����� �Լ�
		return this.X;
	}
	
	public int getY() {//Y���� ��ũ���� �ֱ����� �Լ�
		return this.Y;
	}
	
	public void up() {//���� ����
		Y-=step;
	}
	public void down() {//�Ʒ��� ����
		Y+=step;
	}
	public void left() {//�������� ����
		X-=step;
	}
	public void right() {//���������� ����
		X+=step;
	}
	
	public void die() { //�׾��� ���
		this.die = 1;
	}

}
