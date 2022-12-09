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
	
	static VirtualKeyState c1 = new VirtualKeyState(); /*�����̵�����*/
    static VirtualKeyState c2 = new VirtualKeyState(); /* '' */
	
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
	private Image map_CookieBox1 = new ImageIcon("Resources/boxcookie1.png").getImage();
	private Image map_CookieBox2 = new ImageIcon("Resources/boxcookie2.png").getImage();
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//����(��1) �̹���
	private Image map_PatriotsBox1 = new ImageIcon("Resources/box1.png").getImage(); // �̰� �Ƹ� �Ⱦ��� �̹����� 2�� �Ȱ����� ��������
	private Image map_PatriotsBox2 = new ImageIcon("Resources/box2.png").getImage();
	private Image map_PatriotsBox3 = new ImageIcon("Resources/box3.png").getImage();
	
	private Image item_waterbomb = new ImageIcon("Resources/item_waterbombplus.png").getImage();
	private Image item_speed = new ImageIcon("Resources/item_Speed.png").getImage();
	private Image item_stream = new ImageIcon("Resources/item_waterstream.png").getImage();
	
	
	private Image boombmiddleup = new ImageIcon("Resources/waterbomb_link1.png").getImage();
	private Image boombmiddledown = new ImageIcon("Resources/waterbomb_link2.png").getImage();
	private Image boombmiddleleft = new ImageIcon("Resources/waterbomb_link3.png").getImage();
	private Image boombmiddleright = new ImageIcon("Resources/waterbomb_link4.png").getImage();
	public Screen(int map) {
		
	        
	        
		this.map_selection = map; //�����ڸ� ���� � �� �����Ǿ����� �޾ƿ��� ����
		BoomJudge judge = new BoomJudge(map); //�� ��ġ �޾ƿ��� ���ؼ� ���
		players = new Character[MAX_PLAYER];
		playerIndex_x = new int[MAX_PLAYER];
		playerIndex_y = new int[MAX_PLAYER];
		previous_Index_x = new int[MAX_PLAYER];
		previous_Index_y = new int[MAX_PLAYER];
		if (ReadyFrame.p1chnumber==1) {
			Character player1 = new Dizini(this,1); //�÷��̾�1�� ������ ����
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==2) {
			Character player1 = new Bazzi(this,1); //�÷��̾�1�� ���� ����
			players[0] = player1;
		}
		else if (ReadyFrame.p1chnumber==3) {
			Character player1 = new Uni(this,1); //�÷��̾�1�� ��� ����
			players[0] = player1;
		}
		if (ReadyFrame.p2chnumger==1) {
			Character player2 = new Dizini(this,2); //�÷��̾�2�� ������ ����
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==2) {
			Character player2 = new Bazzi(this,2); //�÷��̾�2�� ���� ����
			players[1] = player2;
		}
		else if (ReadyFrame.p2chnumger==3) {
			Character player2 = new Uni(this,2); //�÷��̾�2�� ��� ����
			players[1] = player2;
		}
		addKeyListener(this);
		addComponentListener(this);
		
		/* mapXlocaionlist�� mapYlocationlist�� ���� �� Ÿ�ϵ��� �߽���ǥ�� x�� y���� ���� ����*/
		/*for���� �̿��Ͽ� ù Ÿ���� (0,0)���� ������ x�� y ���� 60�� �����ϸ� �߽���ǥ���� �����*/
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		int locationnum = 0;
		for(int i=0; i<13;i++) {
				this.mapXlocationlist[i] = locationnum;
				this.mapYlocationlist[i] = locationnum;
				locationnum += 60.45;
		}
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001�� �ֱ�� repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
				BoomJudge.die(); /*0.001�ʸ���, die �Լ��� ȣ���Ͽ�
				ĳ���Ͱ� ��ǳ���� ������ ��ġ�� ���������� �ǽð����� �Ǵ���*/
				BoomJudge.item_check();
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
			for(int map_y=0; map_y<BoomJudge.map_size.length; map_y++) {
				for(int map_x=0; map_x<BoomJudge.map_size.length; map_x++) {
					if((BoomJudge.map_size[map_y][map_x] == 5) || (BoomJudge.map_size[map_y][map_x] == 7) || 
							(BoomJudge.map_size[map_y][map_x] == 10) || (BoomJudge.map_size[map_y][map_x] == 13)) {
						bufferGraphics.drawImage(map_CookieBox1, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//�� �ε����� �°� ��� �̹��� ����
					}
					if((BoomJudge.map_size[map_y][map_x] == 6) || (BoomJudge.map_size[map_y][map_x] == 8) || 
							(BoomJudge.map_size[map_y][map_x] == 11) || (BoomJudge.map_size[map_y][map_x] == 14)) {
						bufferGraphics.drawImage(map_CookieBox2, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//�� �ε����� �°� ��� �̹��� ����
					}
					if(BoomJudge.map_size[map_y][map_x] == 9) {
						bufferGraphics.drawImage(item_waterbomb, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//��ǳ�� �÷��ִ� ������ �̹��� ����
					}
					if(BoomJudge.map_size[map_y][map_x] == 12) {
						bufferGraphics.drawImage(item_speed, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//�ӵ����� ������ �̹��� ����
					}
					if(BoomJudge.map_size[map_y][map_x] == 15) {
						bufferGraphics.drawImage(item_stream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//��ǳ�� �������� ������ �̹��� ����
					}
				}
			}
		}else if(map_selection == 1) {//�������϶� ���
			bufferGraphics.drawImage(map_PatriotsBackground,0,0,this);
			for(int map_y=0; map_y<BoomJudge.map_size.length; map_y++) {
				for(int map_x=0; map_x<BoomJudge.map_size.length; map_x++) {
					if((BoomJudge.map_size[map_y][map_x] == 5) || (BoomJudge.map_size[map_y][map_x] == 7) || 
							(BoomJudge.map_size[map_y][map_x] == 10) || (BoomJudge.map_size[map_y][map_x] == 13)) {
						bufferGraphics.drawImage(map_PatriotsBox2, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//�� �ε����� �°� ��� �̹��� ����
					}
					if((BoomJudge.map_size[map_y][map_x] == 6) || (BoomJudge.map_size[map_y][map_x] == 8) || 
							(BoomJudge.map_size[map_y][map_x] == 11) || (BoomJudge.map_size[map_y][map_x] == 14)) {
						bufferGraphics.drawImage(map_PatriotsBox3, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//�� �ε����� �°� ��� �̹��� ����
					}
					if(BoomJudge.map_size[map_y][map_x] == 9) {
						bufferGraphics.drawImage(item_waterbomb, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//��ǳ�� �÷��ִ� ������ �̹��� ����
					}
					if(BoomJudge.map_size[map_y][map_x] == 12) {
						bufferGraphics.drawImage(item_speed, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//�ӵ����� ������ �̹��� ����
					}
					if(BoomJudge.map_size[map_y][map_x] == 15) {
						bufferGraphics.drawImage(item_stream, mapXlocationlist[map_x], mapYlocationlist[map_y],this);//��ǳ�� �������� ������ �̹��� ����
					}
				}
			}
		}
		for (int playertype = 0; playertype<MAX_PLAYER; playertype++) {
			for(int i=0;i<players[playertype].getballonListsize();i++) { /*��ǳ���� ��ũ�� ����Ʈ ������ ��ŭ �ݺ��� ����*/
				bufferGraphics.drawImage(players[playertype].getballoonImg(), mapXlocationlist[players[playertype].getballoonX(i)], mapYlocationlist[players[playertype].getballoonY(i)], this);
				/*��ǳ�� �̹����� �׸���, �׸��� ��ġ�� �� Ÿ���� �߾��� �ǵ��� ��*/
			}
			
			for(int i=0;i<players[playertype].getboomballonListsize();i++) { /*���� ��ǳ���� ��ũ�� ����Ʈ ������ ��ŭ �ݺ��� ����*/
			/*stream�� üũ�ϰ� Ȯ��� ��ǳ�� ������ �̹����� �׸���.*/
			if((BoomJudge.character1_stream!=0) || (BoomJudge.character2_stream!=0)) {
				bufferGraphics.drawImage(players[playertype].getcenterImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
					   if(players[playertype].getboomballoonX(i)-players[playertype].getbombSize()>=0) {
						bufferGraphics.drawImage(boombmiddleleft,mapXlocationlist[players[playertype].getboomballoonX(i)-players[playertype].getbombSize()],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
						}
						if(players[playertype].getboomballoonX(i)+players[playertype].getbombSize()<=12) {
						bufferGraphics.drawImage(boombmiddleright,mapXlocationlist[players[playertype].getboomballoonX(i)+players[playertype].getbombSize()],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
						}
						if(players[playertype].getboomballoonY(i)-players[playertype].getbombSize()>=0) {
						bufferGraphics.drawImage(boombmiddleup,mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)-players[playertype].getbombSize()], this);
						}
						if(players[playertype].getboomballoonY(i)+players[playertype].getbombSize()<=12) {
						bufferGraphics.drawImage(boombmiddledown,mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)+players[playertype].getbombSize()], this);
						}
						if(players[playertype].getboomballoonX(i)-players[playertype].getbombSize()-1>=0) {
							bufferGraphics.drawImage(players[playertype].getleftImg(),mapXlocationlist[players[playertype].getboomballoonX(i)-players[playertype].getbombSize()-1],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
						}
						if(players[playertype].getboomballoonX(i)+players[playertype].getbombSize()+1<=12) {
						bufferGraphics.drawImage(players[playertype].getrightImg(),mapXlocationlist[players[playertype].getboomballoonX(i)+players[playertype].getbombSize()+1],mapYlocationlist[players[playertype].getboomballoonY(i)], this);
						}
						if(players[playertype].getboomballoonY(i)-players[playertype].getbombSize()-1>=0) {
						bufferGraphics.drawImage(players[playertype].getupImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)-players[playertype].getbombSize()-1], this);
						}
						if(players[playertype].getboomballoonY(i)+players[playertype].getbombSize()+1<=12) {
						bufferGraphics.drawImage(players[playertype].getdownImg(),mapXlocationlist[players[playertype].getboomballoonX(i)],mapYlocationlist[players[playertype].getboomballoonY(i)+players[playertype].getbombSize()+1], this);
						}
			}else { /*�Ϲ����� ��ǳ�� �׸���*/
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
	}
		
		for(int playertype=0; playertype<MAX_PLAYER; playertype++) {
			bufferGraphics.drawImage(players[playertype].getImg(), players[playertype].getX(), players[playertype].getY(), this);//players �̹��� ����
			players[playertype].getPlayerIndex_x(playerIndex_x[playertype]); //x�ε����� ĳ���Ϳ��� �ֱ�
			players[playertype].getPlayerIndex_y(playerIndex_y[playertype]); //y�ε����� ĳ���Ϳ��� �ֱ�
		}
		g.drawImage(this.bufferedImage, 0, 0, this);
	}
	
	public void characterin() {//ĳ���Ͱ� ���� ���� ��� �迭��ġ�� �ִ��� Ȯ��
		/* mapXlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� X��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
		for (int playertype=0; playertype<MAX_PLAYER; playertype++) {
			if(BoomJudge.map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] != 4) {
			BoomJudge.map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = 0;
			}
			if((BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 9) &&(BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 15)){
				BoomJudge.previous_map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = 0;/* ĳ���Ϳ� ���� ���� �̺�Ʈ�� �߻��� map_size�� 1 �Ǵ� 2�� 0���� �ʱ�ȭ*/
			}
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getX()-mapXlocationlist[i])<40) || ((mapXlocationlist[i]-players[playertype].getX())<40)) {
					playerIndex_x[playertype] = i;
					previous_Index_x[playertype] = i;
				}
			}
			/* mapYlocaitonlist�� 13���� �߽� ��ǥ���� ������ �÷��̾� Y��ǥ�� ���Ͽ� �� ���̰� 40���� ������ �ε����� �ش� �߽���ǥ�� �ε����� ������*/
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getY()-mapYlocationlist[i])<40) || ((mapYlocationlist[i]-players[playertype].getY())<40)) {
					playerIndex_y[playertype] = i;
					previous_Index_y[playertype] = i;
				}
			}
			BoomJudge.map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = playertype+1; /*ĳ������ ��ġ�� ����*/ //player1�� 1�� player2�� 2�� ����
			if((BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 9)&&(BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] != 15)) {
				BoomJudge.previous_map_size[previous_Index_y[playertype]][previous_Index_x[playertype]] = 0;
				BoomJudge.previous_map_size[playerIndex_y[playertype]][playerIndex_x[playertype]] = playertype+1;
			}
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
	public void keyPressed(KeyEvent e) {/*�����̵�����*/
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			c1.setUp(true);
			break;
		case KeyEvent.VK_S:
			c1.setDown(true);
			break;
		case KeyEvent.VK_A:
			c1.setLeft(true);
			break;
		case KeyEvent.VK_D:
			c1.setRight(true);
			break;
		case KeyEvent.VK_UP:
			 c2.setUp(true);
			break;
		case KeyEvent.VK_DOWN:
			c2.setDown(true);
			break;
		case KeyEvent.VK_LEFT:
			c2.setLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			c2.setRight(true);
			break;
		}
        
        
		players[0].keyPressed(e);
		players[1].keyPressed(e);
		characterin(); // ��ư ���������� ĳ���� ��ġ �ݿ�
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) { /*�����̵�����*/
		// TODO Auto-generated method stub
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			c1.setUp(false);
			break;
		case KeyEvent.VK_S:
			c1.setDown(false);
			break;
		case KeyEvent.VK_A:
			c1.setLeft(false);
			break;
		case KeyEvent.VK_D:
			c1.setRight(false);
			break;
		case KeyEvent.VK_UP:
			 c2.setUp(false);
			break;
		case KeyEvent.VK_DOWN:
			c2.setDown(false);
			break;
		case KeyEvent.VK_LEFT:
			c2.setLeft(false);
			break;
		case KeyEvent.VK_RIGHT:
			c2.setRight(false);
			break;
		}
        repaint();
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