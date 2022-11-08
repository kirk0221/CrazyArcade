import java.awt.Image;

public abstract class Character {
	private Screen screen;
	private int X;
	private int Y;
	private int step;
	
	public Character(Screen screen) {//��ũ�� �ҷ�����
		this.screen = screen;
	}
	public Screen getScreen() {//��ũ�� �������� �Լ�
		return this.screen;
	}
	public abstract Image getImg();//�̹��� �������� �Լ�
	
	public int getX() {// X�� �������� �Լ�
		return this.X;
	}
	
	public int getY() {// Y�� �������� �Լ�
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
}
