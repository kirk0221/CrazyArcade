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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image bufferedImage;
	private Graphics bufferGraphics;
	private Dimension dim;
	
	public int map_selection;//� ���� ���������
	public static int[][] map_size;//�� ������ ������ ���� �迭
	/*��ǳ������ �����ϱ� ���� static���� ����*/
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	public int[][] mapindexlist;
	
	private final int MAX_PLAYER = 2;//MAX �÷��̾�
	Character[] players; //�÷��̾� ������ ���� �迭

	
	int[] playerIndex_x; /*characterin���� ���*/
	int[] playerIndex_y;
	
	
	private Image map_CookieBackground = new ImageIcon("Resources/mapCookie.png").getImage();//��Ű(��0) �̹���
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//����(��1) �̹���
	
	public Screen(int map) {
		this.map_selection = map; //�����ڸ� ���� � �� �����Ǿ����� �޾ƿ��� ����
		players = new Character[MAX_PLAYER];
		playerIndex_x = new int[MAX_PLAYER];
		playerIndex_y = new int[MAX_PLAYER];
		Character player1 = new Bazzi(this,1); //�÷��̾�1�� ���� ����
		Character player2 = new Bazzi(this,2); //�÷��̾�2�� ���� ����
		players[0] = player1;
		players[1] = player2;
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
		for(int i=0;i<players[0].getballonListsize();i++) { /*��ǳ���� ��ũ�� ����Ʈ ������ ��ŭ �ݺ��� ����*/
			bufferGraphics.drawImage(players[0].getballoonImg(), mapXlocationlist[players[0].getballoonX(i)], mapYlocationlist[players[0].getballoonY(i)], this);
			/*��ǳ�� �̹����� �׸���, �׸��� ��ġ�� �� Ÿ���� �߾��� �ǵ��� ��*/
		}
		
		for(int j=0;j<players[1].getballonListsize();j++) {
			bufferGraphics.drawImage(players[1].getballoonImg(), mapXlocationlist[players[1].getballoonX(j)], mapYlocationlist[players[1].getballoonY(j)], this);
		}

		for(int i=0; i<MAX_PLAYER; i++) {
			bufferGraphics.drawImage(players[i].getImg(), players[i].getX(), players[i].getY(), this);//players �̹��� ����
		}
		g.drawImage(this.bufferedImage, 0, 0, this);
		this.characterin();
	}
	
	public void characterin() {//ĳ���Ͱ� ���� ���� ��� �迭��ġ�� �ִ��� Ȯ��
		/* mapXlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� X��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
		for (int j=0; j<MAX_PLAYER; j++) {
			for(int i=0; i<13;i++) {
				if((-(players[j].getX()-mapXlocationlist[i])<40) && ((mapXlocationlist[i]-players[j].getX())<40)) {
					playerIndex_x[j] = i;
				}
			}
			/* mapYlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� Y��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
			for(int i=0; i<13;i++) {
				if((-(players[j].getY()-mapYlocationlist[i])<40) && ((mapYlocationlist[i]-players[j].getY())<40)) {
					playerIndex_y[j] = i;
				}
			}
			map_size[playerIndex_x[j]][playerIndex_y[j]] = 1; /*ĳ������ ��ġ�� ����*/
		}
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
		map_size[playerIndex_x[0]][playerIndex_y[0]] = 0;
		map_size[playerIndex_x[1]][playerIndex_y[1]] = 0; /* ĳ���Ϳ� ���� ���� �̺�Ʈ�� �߻��� map_size�� 1�� 0���� �ʱ�ȭ*/
		players[0].keyPressed(e);
		players[1].keyPressed(e);
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