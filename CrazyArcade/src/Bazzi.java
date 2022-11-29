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
	private int state;//���� ��ȣ

	public Bazzi(Screen screen, int playertype) { /*�÷��̾� Ÿ���� ���޹޾�, �ش� Ÿ�Կ� ���� Ű�� ���� ������ �ٸ����� ��*/
		super(screen);
		// TODO Auto-generated constructor stub
		if(playertype == 1) {
			this.X = 400;//�ʱ� X��
			this.Y = 300;//�ʱ� Y��
		}else if(playertype == 2) {
			this.X = 300;//�ʱ� X��
			this.Y = 400;//�ʱ� Y��
		}
		this.step = 9;//�ʱ� �̵� �Ÿ�
		this.die = 0;//���� ���� �ʾ���
		this.bombSize = 1;//���ٱ� ũ�� 1
		this.playertype = playertype;
		playerWaterBalloon = new WaterBalloon(playertype); /* ��ǳ�� ����*/
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
	}
	
	public Image getImg() {//�̹����� ��ũ���� �ֱ����� �Լ�
		return this.bazzi_state[state];
	}
	
	public Image getballoonImg() {//�̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getImg();
	}
	
	public Image getcenterImg() {// ���� ��ǳ�� ��� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getcenterImg();
	}
	
	public Image getleftImg() {// ���� ��ǳ�� ���� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getleftImg();
	}
	
	public Image getrightImg() {// ���� ��ǳ�� ������ �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getrightImg();
	}
	
	public Image getupImg() {// ���� ��ǳ�� �� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getupImg();
	}
	public Image getdownImg() {// ���� ��ǳ�� �Ʒ� �̹����� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getdownImg();
	}
	
	public int getballoonX(int i) {//��ǳ�� X���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getX(i);
	}
	
	public int getballoonY(int i) {//��ǳ�� Y���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getY(i);
	}
	public int getboomballoonX(int i) {//���� ��ǳ�� X���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getboomX(i);
	}
	
	public int getboomballoonY(int i) {//���� ��ǳ�� Y���� ��ũ���� �ֱ����� �Լ�
		return playerWaterBalloon.getboomY(i);
	}
	
	public int getX() {//X���� ��ũ���� �ֱ����� �Լ�
		return this.X;
	}
	
	public int getY() {//Y���� ��ũ���� �ֱ����� �Լ�
		return this.Y;
	}
	
	public int getballonListsize() {
		return playerWaterBalloon.balloonXList.size();
		/*��ǳ�� ��ü�� ��ũ�� ����Ʈ�� ũ�⸦ ��ũ���� �����Ͽ�, �ݺ����� �ݺ� �ܼ��� �����ϱ� ���� �Լ�*/
	}
	public int getboomballonListsize() {
		return playerWaterBalloon.boomballoonXList.size();
		/*���� ��ǳ�� ��ü�� ��ũ�� ����Ʈ�� ũ�⸦ ��ũ���� �����Ͽ�, �ݺ����� �ݺ� �ܼ��� �����ϱ� ���� �Լ�*/
	}
	
	public int getbombSize() {//��ǳ�� ũ�Ⱚ�� ��ũ���� �ֱ����� �Լ�
		return this.bombSize;
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(playertype == 1) {
			switch(e.getKeyCode()) {//player1�� ���� ������
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
				playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize);//��ǳ�� ����
				break;
			}
		}else if(playertype == 2) {
			switch(e.getKeyCode()) {//player2�� ���� ������
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
				playerWaterBalloon.makeWaterBalloon(this.getX(), this.getY(), this.bombSize);//��ǳ�� ����
				break;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}