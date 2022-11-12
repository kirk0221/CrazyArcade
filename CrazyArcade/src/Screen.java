import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Screen extends Canvas implements KeyListener, ComponentListener {
	private Image bufferedImage;
	private Graphics bufferGraphics;
	private Dimension dim;
	
	public int map_selection;//� ���� ���������
	public int[][] map_size;//�� ������ ������ ���� �迭
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	public int[][] mapindexlist;
	
	private Character player1; //�÷��̾�1 ����
	private Character player2; //�÷��̾�2 ����
	WaterBalloon player1WaterBalloon; //�÷��̾�1 ��ǳ�� ����
	WaterBalloon player2WaterBalloon; //�÷��̾�2 ��ǳ�� ����
	
	int p1_x; /*characterin���� ���*/
	int p1_y;
	
	
	private Image map_CookieBackground = new ImageIcon("Resources/mapCookie.png").getImage();//��Ű(��0) �̹���
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//����(��1) �̹���
	
	public Screen(int map) {
		this.map_selection = map; //�����ڸ� ���� � �� �����Ǿ����� �޾ƿ��� ����
		player1 = new Bazzi(this); //�÷��̾�1�� �ٿ� ����
		player2 = new Bazzi(this); //�÷��̾�2�� �ٿ� ����
		addKeyListener(this);
		addComponentListener(this);

		this.map_size = new int[13][13];//�� ������ 13*13
		for(int i=0; i<13;i++) {//�� 0���� �ʱ�ȭ
			for(int j=0; j<13; j++) {
				this.map_size[i][j] = 0;
			}
		}
		
		/* mapXlocaionlist�� mapYlocationlist�� ���� �� Ÿ�ϵ��� �߽���ǥ�� x�� y���� ���� ����*/
		/*for���� �̿��Ͽ� ù Ÿ���� (10,10)���� ������ x�� y ���� 60�� �����ϸ� �߽���ǥ���� �����*/
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		int locationnum = 10;
		for(int i=0; i<13;i++) {
				this.mapXlocationlist[i] = locationnum;
				this.mapYlocationlist[i] = locationnum;
				locationnum += 60;
		}
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001�� �ֱ�� repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//��ũ���� �׸��� �κ�
		initBufferd();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		if(map_selection == 0) {//��Ű���϶� ���
			bufferGraphics.drawImage(map_CookieBackground,0,0,this);
		}else if(map_selection == 1) {//�������϶� ���
			bufferGraphics.drawImage(map_PatriotsBackground,0,0,this);
		}
		bufferGraphics.drawImage(player1.getImg(), player1.getX(), player1.getY(), this);//player1 �̹��� ����
		bufferGraphics.drawImage(player2.getImg(), player2.getX(), player2.getY(), this);//player2 �̹��� ����
		g.drawImage(this.bufferedImage, 0, 0, this);
		this.characterin();
	}
	
	public void characterin() {//ĳ���Ͱ� ���� ���� ��� �迭��ġ�� �ִ��� Ȯ��
		/* mapXlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� X��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
		for(int i=0; i<13;i++) {
			if((-(player1.getX()-mapXlocationlist[i])<40) && ((mapXlocationlist[i]-player1.getX())<40)) {
				p1_x = i;
			}
		}
		/* mapYlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� Y��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
		for(int i=0; i<13;i++) {
			if((-(player1.getY()-mapYlocationlist[i])<40) && ((mapYlocationlist[i]-player1.getY())<40)) {
				p1_y = i;
			}
		}

		System.out.println("p1�� ��ġ�� : ("+player1.getX()+","+player1.getY()+")"+"p1�� �ε��� ��ġ�� : ("+p1_x+","+p1_y+")");
	}
	
	public void update(Graphics g) {//������Ʈ �Լ�
		paint(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {//player1�� ���� ������
		case KeyEvent.VK_UP:
			if(player1.getY()>=0) {
				player1.up();
			}
			break;
		case KeyEvent.VK_DOWN:
			if(player1.getY()<=800) {// â���� ���� ���� �ٲ㼭 �ȳ����� �� ������
				player1.down();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(player1.getX()>=0) {
				player1.left();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(player1.getX()<=800) {// â���� ���� ���� �ٲ㼭 �ȳ����� �� ������
				player1.right();
			}
			break;
		case KeyEvent.VK_SHIFT:
			player1WaterBalloon = new WaterBalloon();
			player1WaterBalloon.makeWaterBalloon(player1.getX(), player1.getY());//��ǳ�� ����
			break;
		}
		switch(e.getKeyCode()) {//player2�� ���� ������
		case KeyEvent.VK_W:
			if(player2.getY()>=0) {
				player2.up();
			}
			break;
		case KeyEvent.VK_S:
			if(player2.getY()<=800) {// â���� ���� ���� �ٲ㼭 �ȳ����� �� ������
				player2.down();
			}
			break;
		case KeyEvent.VK_A:
			if(player2.getX()>=0) {
				player2.left();
			}
			break;
		case KeyEvent.VK_D:
			if(player2.getX()<=800) {// â���� ���� ���� �ٲ㼭 �ȳ����� �� ������
				player2.right();
			}
			break;
		case KeyEvent.VK_SHIFT:
			player2WaterBalloon = new WaterBalloon();
			player2WaterBalloon.makeWaterBalloon(player2.getX(), player2.getY());//��ǳ�� ����
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void initBufferd() {//���� �ʱ�ȭ
		this.dim = getSize();
		this.bufferedImage = createImage(dim.width, dim.height);
		this.bufferGraphics = this.bufferedImage.getGraphics();
	}

	@Override
	public void componentResized(ComponentEvent e) {//â ũ�Ⱑ �ٲ� ���� �ʱ�ȭ
		// TODO Auto-generated method stub
		initBufferd();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
