import java.awt.Image;
import java.awt.event.KeyEvent;

public abstract class Character {
	private Screen screen;
	private static int X;
	private static int Y;
	private static int step;
	public int die;
	
	public Character(Screen screen) {//��ũ�� �ҷ�����
		this.screen = screen;
	}
	public Screen getScreen() {//��ũ�� �������� �Լ�
		return this.screen;
	}
	public abstract Image getImg();//�̹��� �������� �Լ�
	public abstract Image getballoonImg(); //��ǳ�� �̹��� ��������
	public abstract Image getcenterImg(); //��ǳ�� �̹��� ��������
	public abstract Image getleftImg(); //��ǳ�� �̹��� ��������
	public abstract Image getrightImg(); //��ǳ�� �̹��� ��������
	public abstract Image getupImg(); //��ǳ�� �̹��� ��������
	public abstract Image getdownImg(); //��ǳ�� �̹��� ��������
	
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
	public void die() {
		die = 1;
	}
	public abstract void keyPressed(KeyEvent e);
	protected abstract int getballoonY(int i);
	protected abstract int getballoonX(int i);
	protected abstract int getboomballoonY(int i);
	protected abstract int getboomballoonX(int i);
	protected abstract int getballonListsize();
	protected abstract int getboomballonListsize();
	protected abstract int getbombSize();
}