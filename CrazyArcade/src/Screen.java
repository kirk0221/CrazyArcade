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
	
	public static int map_selection;//어떤 맵이 골라졌는지
	
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	public int[][] mapindexlist;
	
	private final int MAX_PLAYER = 2;//MAX 플레이어
	Character[] players; //플레이어 관리를 위한 배열

	
	int[] playerIndex_x; /*characterin에서 사용*/
	int[] playerIndex_y;
	
	int[] previous_Index_x; // 이전 인덱스 저장
	int[] previous_Index_y;
	
	
	private Image map_CookieBackground = new ImageIcon("Resources/mapCookie.png").getImage();//쿠키(맵0) 이미지
	private Image map_CookieBox1 = new ImageIcon("Resources/boxcookie1.png").getImage();
	private Image map_CookieBox2 = new ImageIcon("Resources/boxcookie2.png").getImage();
	private Image map_PatriotsBackground = new ImageIcon("Resources/mapPatriots.png").getImage();//해적(맵1) 이미지
	private Image map_PatriotsBox1 = new ImageIcon("Resources/box1.png").getImage();
	private Image map_PatriotsBox2 = new ImageIcon("Resources/box2.png").getImage();
	private Image map_PatriotsBox3 = new ImageIcon("Resources/box3.png").getImage();
	
	public Screen(int map) {
		this.map_selection = map; //생성자를 통해 어떤 맵 설정되었는지 받아오기 위함
		BoomJudge judge = new BoomJudge(map); //맵 배치 받아오기 위해서 사용
		players = new Character[MAX_PLAYER];
		playerIndex_x = new int[MAX_PLAYER];
		playerIndex_y = new int[MAX_PLAYER];
		previous_Index_x = new int[MAX_PLAYER];
		previous_Index_y = new int[MAX_PLAYER];
		Character player1 = new Bazzi(this,1); //플레이어1에 배찌 생성
		Character player2 = new Bazzi(this,2); //플레이어2에 배찌 생성
		players[0] = player1;
		players[1] = player2;
		addKeyListener(this);
		addComponentListener(this);
		for(int i =0; i<MAX_PLAYER; i++) {//이전 인덱스 저장 초기화
			previous_Index_x[i] = 0;
			previous_Index_y[i] = 0;
		}
		
		/* mapXlocaionlist와 mapYlocationlist는 맵의 각 타일들의 중심좌표의 x와 y값을 각각 저장*/
		/*for문을 이용하여 첫 타일은 (10,10)에서 시작해 x와 y 각각 60씩 증가하며 중심좌표들이 저장됨*/
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		int locationnum = 0;
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
				BoomJudge.die(); /*0.001초마다, die 함수를 호출하여
				캐릭터가 물풍선이 터지는 위치에 들어오는지를 실시간으로 판단함*/
			}
		},0, 1);
	}
	
	public void paint(Graphics g) {//스크린에 그리는 부분
		initBufferd();
		this.characterin();
		Dimension dim = getSize();
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		if(map_selection == 0) {//쿠키맵일때 배경
			bufferGraphics.drawImage(map_CookieBackground,0,0,this);
			for(int map_y=0; map_y<BoomJudge.map_size.length; map_y++) {
				for(int map_x=0; map_x<BoomJudge.map_size.length; map_x++) {
					if(BoomJudge.map_size[map_y][map_x] == 5) {
						bufferGraphics.drawImage(map_CookieBox1, mapXlocationlist[map_x], mapYlocationlist[map_y],this);
					}
				}
			}
		}else if(map_selection == 1) {//해적맵일때 배경
			bufferGraphics.drawImage(map_PatriotsBackground,0,0,this);
			for(int map_y=0; map_y<BoomJudge.map_size.length; map_y++) {
				for(int map_x=0; map_x<BoomJudge.map_size.length; map_x++) {
					if(BoomJudge.map_size[map_y][map_x] == 5) {
						bufferGraphics.drawImage(map_PatriotsBox1, mapXlocationlist[map_x], mapYlocationlist[map_y],this);
					}
				}
			}
		}
		for (int playertype = 0; playertype<MAX_PLAYER; playertype++) {
			for(int i=0;i<players[playertype].getballonListsize();i++) { /*물풍선의 링크드 리스트 사이즈 만큼 반복문 수행*/
				bufferGraphics.drawImage(players[playertype].getballoonImg(), mapXlocationlist[players[playertype].getballoonX(i)], mapYlocationlist[players[playertype].getballoonY(i)], this);
				/*물풍선 이미지를 그리되, 그리는 위치는 각 타일의 중앙이 되도록 함*/
			}
			
			for(int i=0;i<players[playertype].getboomballonListsize();i++) { /*터진 물풍선의 링크드 리스트 사이즈 만큼 반복문 수행*/
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
			bufferGraphics.drawImage(players[playertype].getImg(), players[playertype].getX(), players[playertype].getY(), this);//players 이미지 생성
			players[playertype].getPlayerIndex_x(playerIndex_x[playertype]); //x인덱스값 캐릭터에게 주기
			players[playertype].getPlayerIndex_y(playerIndex_y[playertype]); //y인덱스값 캐릭터에게 주기
		}
		g.drawImage(this.bufferedImage, 0, 0, this);
	}
	
	public void characterin() {//캐릭터가 현재 맵의 어느 배열위치에 있는지 확인
		/* mapXlocaitonlist의 13개의 중심 좌표값을 현재의 플레이어 X좌표와 비교하여 그 차이가 40보다 작으면 인덱스를 해당 중심좌표의 인덱스로 변경함*/
		for (int playertype=0; playertype<MAX_PLAYER; playertype++) {
			if(BoomJudge.map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] != 4) {
			BoomJudge.map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = 0;
			}
			BoomJudge.previous_map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = 0;/* 캐릭터에 대한 조작 이벤트가 발생시 map_size의 1 또는 2를 0으로 초기화*/
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getX()-mapXlocationlist[i])<40) || ((mapXlocationlist[i]-players[playertype].getX())<40)) {
					playerIndex_x[playertype] = i;
					previous_Index_x[playertype] = i;
				}
			}
			/* mapYlocaitonlist의 13개의 중심 좌표값을 현재의 플레이어 Y좌표와 비교하여 그 차이가 40보다 작으면 인덱스를 해당 중심좌표의 인덱스로 변경함*/
			for(int i=0; i<13;i++) {
				if((-(players[playertype].getY()-mapYlocationlist[i])<40) || ((mapYlocationlist[i]-players[playertype].getY())<40)) {
					playerIndex_y[playertype] = i;
					previous_Index_y[playertype] = i;
				}
			}
			BoomJudge.map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = playertype+1; /*캐릭터의 위치를 저장*/ //player1은 1로 player2는 2로 저장
			BoomJudge.previous_map_size[previous_Index_x[playertype]][previous_Index_y[playertype]] = 0;
			BoomJudge.previous_map_size[playerIndex_x[playertype]][playerIndex_y[playertype]] = playertype+1;
			/*물풍선을 놓고 마지막에 이동한 위치를 저장하고 물풍선이 존재하는 맵과 비교하기 위하여
			 * previous_map_size를 사용함
			 * 
			 */
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
		players[0].keyPressed(e);
		players[1].keyPressed(e);
		characterin(); // 버튼 누를때마다 캐릭터 위치 반영
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