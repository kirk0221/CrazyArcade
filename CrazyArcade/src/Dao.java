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
	

	public Dao(Screen screen) {
		super(screen);
		// TODO Auto-generated constructor stub
		this.daoImg = dao2.getImage(); /*�⺻���� �ո��*/
		this.X = 400;//�ʱ� X��
		this.Y = 300;//�ʱ� Y��
		this.step = 5;//�ʱ� �̵� �Ÿ�
	}
	
	public Image getImg() {//�̹����� ��ũ���� �ֱ����� �Լ�
		return this.daoImg;
	}
	
	public int getX() {//X���� ��ũ���� �ֱ����� �Լ�
		return this.X;
	}
	
	public int getY() {//Y���� ��ũ���� �ֱ����� �Լ�
		return this.Y;
	}
	
	public void up() {//���� ����
		Y-=step;
		this.daoImg = dao3.getImage();
	}
	public void down() {//�Ʒ��� ����
		Y+=step;
		this.daoImg = dao2.getImage();
	}
	public void left() {//�������� ����
		X-=step;
		this.daoImg = dao4.getImage();
	}
	public void right() {//���������� ����
		X+=step;
		this.daoImg = dao1.getImage();
	}

}
