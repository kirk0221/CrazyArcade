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
	
	public int map_selection;//어떤 맵이 골라졌는지
	public static int[][] map_size;//맵 사이즈 설정을 위한 배열
	/*물풍선에서 조작하기 위해 static으로 변경*/
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	public int[][] mapindexlist;
	
	private final int MAX_PLAYER = 2;//MAX 플레이어
	Character[] players; //플레이어 관리를 위한 배열

	
	int[] playerIndex_x; /*characterin에서 사용*/
	int[] playerIndex_y;
	
	
	private Image map_CookieBackground = new ImageIcon("Resources/mapCookie.png").getImage();//쿠키(맵0) 이미지
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//해적(맵1) 이미지
	
	public Screen(int map) {
		this.map_selection = map; //생성자를 통해 어떤 맵 설정되었는지 받아오기 위함
		players = new Character[MAX_PLAYER];
		playerIndex_x = new int[MAX_PLAYER];
		playerIndex_y = new int[MAX_PLAYER];
		Character player1 = new Bazzi(this,1); //플레이어1에 배찌 생성
		Character player2 = new Bazzi(this,2); //플레이어2에 배찌 생성
		players[0] = player1;
		players[1] = player2;
		addKeyListener(this);
		addComponentListener(this);
		
		

		this.map_size = new int[13][13];//맵 사이즈 13*13
		for(int i=0; i<13;i++) {//맵 0으로 초기화
			for(int j=0; j<13; j++) {
				this.map_size[i][j] = 0;
			}
		}
		
		/* mapXlocaionlist와 mapYlocationlist는 맵의 각 타일들의 중심좌표의 x와 y값을 각각 저장*/
		/*for문을 이용하여 첫 타일은 (10,10)에서 시작해 x와 y 각각 60씩 증가하며 중심좌표들이 저장됨*/
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		int locationnum = 10;
		for(int i=0; i<13;i++) {
				this.mapXlocationlist[i] = locationnum;
				this.mapYlocationlist[i] = locationnum;
				locationnum += 60;
		}
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001초 주기로 repaint
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//스크린에 그리는 부분
		initBufferd();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		if(map_selection == 0) {//쿠키맵일때 배경
			bufferGraphics.drawImage(map_CookieBackground,0,0,this);
		}else if(map_selection == 1) {//해적맵일때 배경
			bufferGraphics.drawImage(map_PatriotsBackground,0,0,this);
		}
		for(int i=0;i<players[0].getballonListsize();i++) { /*물풍선의 링크드 리스트 사이즈 만큼 반복문 수행*/
			bufferGraphics.drawImage(players[0].getballoonImg(), mapXlocationlist[players[0].getballoonX(i)], mapYlocationlist[players[0].getballoonY(i)], this);
			/*물풍선 이미지를 그리되, 그리는 위치는 각 타일의 중앙이 되도록 함*/
		}
		
		for(int j=0;j<players[1].getballonListsize();j++) {
			bufferGraphics.drawImage(players[1].getballoonImg(), mapXlocationlist[players[1].getballoonX(j)], mapYlocationlist[players[1].getballoonY(j)], this);
		}

		for(int i=0; i<MAX_PLAYER; i++) {
			bufferGraphics.drawImage(players[i].getImg(), players[i].getX(), players[i].getY(), this);//players 이미지 생성
		}
		g.drawImage(this.bufferedImage, 0, 0, this);
		this.characterin();
	}
	
	public void characterin() {//캐릭터가 현재 맵의 어느 배열위치에 있는지 확인
		/* mapXlocaitonlist의 13개의 중심 좌표값을 현재의 플레이어 X좌표와 비교하여 그 차이가 40보다 작으면 인덱스를 해당 중심좌표의 인덱스로 변경함*/
		for (int j=0; j<MAX_PLAYER; j++) {
			for(int i=0; i<13;i++) {
				if((-(players[j].getX()-mapXlocationlist[i])<40) && ((mapXlocationlist[i]-players[j].getX())<40)) {
					playerIndex_x[j] = i;
				}
			}
			/* mapYlocaitonlist의 13개의 중심 좌표값을 현재의 플레이어 Y좌표와 비교하여 그 차이가 40보다 작으면 인덱스를 해당 중심좌표의 인덱스로 변경함*/
			for(int i=0; i<13;i++) {
				if((-(players[j].getY()-mapYlocationlist[i])<40) && ((mapYlocationlist[i]-players[j].getY())<40)) {
					playerIndex_y[j] = i;
				}
			}
			map_size[playerIndex_x[j]][playerIndex_y[j]] = 1; /*캐릭터의 위치를 저장*/
		}
	}
	
	public void update(Graphics g) {//업데이트 함수
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
		map_size[playerIndex_x[1]][playerIndex_y[1]] = 0; /* 캐릭터에 대한 조작 이벤트가 발생시 map_size의 1을 0으로 초기화*/
		players[0].keyPressed(e);
		players[1].keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void initBufferd() {//버퍼 초기화
		this.dim = getSize();
		this.bufferedImage = createImage(dim.width, dim.height);
		this.bufferGraphics = this.bufferedImage.getGraphics();
	}

	@Override
	public void componentResized(ComponentEvent e) {//창 크기가 바뀔때 버퍼 초기화
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