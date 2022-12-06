import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Dizni extends Character implements KeyListener{

	private int X;
	private int Y;
	private int playerIndex_x;
	private int playerIndex_y;
	private int step;
	public int bombSize;
	public int playertype;
	WaterBalloon playerWaterBalloon;
	private Image[] bazzi_state;
	private int state;//���� ��ȣ

	public Dizni(Screen screen, int playertype) { /*�÷��̾� Ÿ���� ���޹޾�, �ش� Ÿ�Կ� ���� Ű�� ���� ������ �ٸ����� ��*/
		super(screen);
		// TODO Auto-generated constructor stub
		if(playertype == 1) {
			if (Screen.map_selection == 0) {//��Ű���϶�
				this.X = 0;//�ʱ� X��
				this.Y = 0;//�ʱ� Y��
			}else if (Screen.map_selection == 1) {//�������϶�
				this.X = 60;//�ʱ� X��
				this.Y = 60;//�ʱ� Y��
			}
		}else if(playertype == 2) {
			if (Screen.map_selection == 0) {
				this.X = 720;//�ʱ� X��
				this.Y = 720;//�ʱ� Y��
			}else if (Screen.map_selection == 1) {
				this.X = 660;//�ʱ� X��
				this.Y = 660;//�ʱ� Y��
			}
		}
		this.step = 5;//�ʱ� �̵� �Ÿ�
		this.bombSize = 1;//���ٱ� ũ�� 1
		this.playertype = playertype;
		playerWaterBalloon = new WaterBalloon(playertype); /* ��ǳ�� ����*/
		this.bazzi_state = new Image[4];
		Image dizini_front = new ImageIcon("Resources/Dizini_front.png").getImage();//������ ���� �̹���
		Image dizini_back = new ImageIcon("Resources/Dizini_back.png").getImage();//������ �ĸ� �̹���
		Image dizini_left = new ImageIcon("Resources/Dizini_left.png").getImage();//������ ������ �̹���
		Image dizini_right = new ImageIcon("Resources/Dizini_right.png").getImage();//������ ������ �̹���
		this.bazzi_state[0] = dizini_front;
		this.bazzi_state[1] = dizini_back;
		this.bazzi_state[2] = dizini_left;
		this.bazzi_state[3] = dizini_right;
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
	
	public void getPlayerIndex_x(int x) {//��ũ������ x�ε����� ��������
		this.playerIndex_x = x;
	}
	
	public void getPlayerIndex_y(int y) {//��ũ������ y�ε����� ��������
		this.playerIndex_y = y;
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
		if (playerIndex_y == 0) {//�ε��� 0�ϰ�� ����ó��
			Y-=step;
		}
		else if((BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 0) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 1) || (BoomJudge.map_size[playerIndex_y-1][playerIndex_x] == 2)) {//���� �̵���ġ �ε��� 0,1,2�� ��쿡�� �̵�����
			Y-=step;
		}
		else if((playerIndex_y)*60<this.getY()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			Y-=step;
		}
	}
	public void down() {//�Ʒ��� ����
		this.state  = 0;
		if (playerIndex_y == 12) {//�ε��� 12�ϰ�� ����ó��
			Y+=step;
		}
		else if((BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 0) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 1) || (BoomJudge.map_size[playerIndex_y+1][playerIndex_x] == 2)) {//���� �̵���ġ �ε��� 0,1,2�� ��쿡�� �̵�����
			Y+=step;
		}
		else if((playerIndex_y)*60>this.getY()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			Y+=step;
		}
	}
	public void left() {//�������� ����
		this.state  = 2;
		if (playerIndex_x == 0) {//�ε��� 0�ϰ�� ����ó��
			X-=step;
		}
		else if((BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 0) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 1) || (BoomJudge.map_size[playerIndex_y][playerIndex_x-1] == 2)) {//���� �̵���ġ �ε��� 0,1,2�� ��쿡�� �̵�����
			X-=step;
		}
		else if((playerIndex_x)*60<this.getX()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			X-=step;
		}
	}
	public void right() {//���������� ����
		this.state  = 3;
		if (playerIndex_x == 12) {//�ε��� 12�ϰ�� ����ó��
			X+=step;
		}
		else if((BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 0) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 1) || (BoomJudge.map_size[playerIndex_y][playerIndex_x+1] == 2)) {//���� �̵���ġ �ε��� 0,1,2�� ��쿡�� �̵�����
			X+=step;
		}
		else if((playerIndex_x)*60>this.getX()) {//�׷��� ĳ���Ͱ� ������ ��ĭ���� �ȳѾ���� ���� ���� ���� ���������� �̵�
			X+=step;
		}
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