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
	
	public static int map_selection;//� ���� ���������
	
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	public int[][] mapindexlist;
	
	private final int MAX_PLAYER = 2;//MAX �÷��̾�
	Character[] players; //�÷��̾� ������ ���� �迭

	
	int[] playerIndex_x; /*characterin���� ���*/
	int[] playerIndex_y;
	
	int[] previous_Index_x; // ���� �ε��� ����
	int[] previous_Index_y;
	
	
	private Image map_CookieBackground = new ImageIcon("Resources/mapCookie.png").getImage();//��Ű(��0) �̹���
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//����(��1) �̹���
	
	public Screen(int map) {
		this.map_selection = map; //�����ڸ� ���� � �� �����Ǿ����� �޾ƿ��� ����
		players = new Character[MAX_PLAYER];
		playerIndex_x = new int[MAX_PLAYER];
		playerIndex_y = new int[MAX_PLAYER];
		previous_Index_x = new int[MAX_PLAYER];
		previous_Index_y = new int[MAX_PLAYER];
		Character player1 = new Bazzi(this,1); //�÷��̾�1�� ���� ����
		Character player2 = new Bazzi(this,2); //�÷��̾�2�� ���� ����
		players[0] = player1;
		players[1] = player2;
		addKeyListener(this);
		addComponentListener(this);
		for(int i =0; i<MAX_PLAYER; i++) {//���� �ε��� ���� �ʱ�ȭ
			previous_Index_x[i] = 0;
			previous_Index_y[i] = 0;
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
				BoomJudge.die(); /*0.001�ʸ���, die �Լ��� ȣ���Ͽ�
				ĳ���Ͱ� ��ǳ���� ������ ��ġ�� ���������� �ǽð����� �Ǵ���*/
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//��ũ���� �׸��� �κ�
		initBufferd();
		this.characterin();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		if(map_selection == 0) {//��Ű���϶� ���
			bufferGraphics.drawImage(map_CookieBackground,0,0,this);
		}else if(map_selection == 1) {//�������϶� ���
			bufferGraphics.drawImage(map_PatriotsBackground,0,0,this);
		}
		for (int playertype = 0; playertype<MAX_PLAYER; playertype++) {
			for(int i=0;i<players[playertype].getballonListsize();i++) { /*��ǳ���� ��ũ�� ����Ʈ ������ ��ŭ �ݺ��� ����*/
				bufferGraphics.drawImage(players[playertype].getballoonImg(), mapXlocationlist[players[playertype].getballoonX(i)], mapYlocationlist[players[playertype].getballoonY(i)], this);
				/*��ǳ�� �̹����� �׸���, �׸��� ��ġ�� �� Ÿ���� �߾��� �ǵ��� ��*/
			}
			
			for(int i=0;i<players[playertype].getboomballonListsize();i++) { /*���� ��ǳ���� ��ũ�� ����Ʈ ������ ��ŭ �ݺ��� ����*/
				bufferGraphics.drawImage(players[playertype].getcenterImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
				if(players[playertype].getboomballoonX(i)-players[playertype].getbombSize()>=0) {
				bufferGraphics.drawImage(players[playertype].getleftImg(),mapXlocationlist[players[playertype].getboomballoonX(i)-players[playertype].getbombSize()],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
				}
				if(players[playertype].getboomballoonX(i)+players[playertype].getbombSize()<=12) {
				bufferGraphics.drawImage(players[playertype].getrightImg(),mapXlocationlist[players[playertype].getboomballoonX(i)+players[playertype].getbombSize()],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
				}
				if(players[playertype].getboomballoonY(i)-players[playertype].getbombSize()>=0) {
				bufferGraphics.drawImage(players[playertype].getupImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)-players[playertype].getbombSize()], this);
				}
				if(players[playertype].getboomballoonY(i)+players[playertype].getbombSize()<=12) {
				bufferGraphics.drawImage(players[playertype].getdownImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)+players[playertype].getbombSize()], this);
				}
			}
		}

		for(int playertype=0; playertype<MAX_PLAYER; playertype++) {
			bufferGraphics.drawImage(players[playertype].getImg(), players[playertype].getX(), players[playertype].getY(), this);//players �̹��� ����
		}
		g.drawImage(this.bufferedImage, 0, 0, this);
	}
	
	public void characterin() {//ĳ���Ͱ� ���� ���� ��� �迭��ġ�� �ִ��� Ȯ��
		/* mapXlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� X��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
		for (int playertype=0; playertype<MAX_PLAYER; playertype++) {
			BoomJudge.map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = 0;
			BoomJudge.previous_map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = 0;/* ĳ���Ϳ� ���� ���� �̺�Ʈ�� �߻��� map_size�� 1 �Ǵ� 2�� 0���� �ʱ�ȭ*/
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getX()-mapXlocationlist[i])<40) && ((mapXlocationlist[i]-players[playertype].getX())<40)) {
					playerIndex_x[playertype] = i;
					previous_Index_x[playertype] = i;
				}
			}
			/* mapYlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� Y��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getY()-mapYlocationlist[i])<40) && ((mapYlocationlist[i]-players[playertype].getY())<40)) {
					playerIndex_y[playertype] = i;
					previous_Index_y[playertype] = i;
				}
			}
			BoomJudge.map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = playertype+1; /*ĳ������ ��ġ�� ����*/ //player1�� 1�� player2�� 2�� ����
			BoomJudge.previous_map_size[previous_Index_x[playertype]][previous_Index_y[playertype]] = 0;
			BoomJudge.previous_map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = playertype+1;
			/*��ǳ���� ���� �������� �̵��� ��ġ�� �����ϰ� ��ǳ���� �����ϴ� �ʰ� ���ϱ� ���Ͽ�
			 * previous_map_size�� �����
			 * 
			 */
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
		players[0].keyPressed(e);
		players[1].keyPressed(e);
		characterin(); // ��ư ���������� ĳ���� ��ġ �ݿ�
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