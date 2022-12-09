import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReadyFrame extends JFrame implements MouseListener {
	private JButton[] mapButtons;
	private JButton[] characterButtons;
	private Image starteBackground = new ImageIcon("Resources/ready2.png").getImage();//처음 배경 넣기
	private Image bazziBackground = new ImageIcon("Resources/bazzi.png").getImage();//배찌 배경 넣기
	private Image uniBackground = new ImageIcon("Resources/uni.png").getImage();//우니 배경 넣기
	private Image dizniackground = new ImageIcon("Resources/dizni.png").getImage();//디즈니 배경 넣기
	private Image FactoryBackground = new ImageIcon("Resources/Factorymap.png").getImage();//팩토리 배경 넣기
	private Image CookieBackground = new ImageIcon("Resources/Cookiemap.png").getImage();//쿠키 배경
	private Image VilageBackground = new ImageIcon("Resources/Vilagemap.png").getImage();//쿠키 배경
	private Image FactorylevelBackground = new ImageIcon("Resources/factorylevel.png").getImage();//팩토리 난이도 넣기
	private Image CookielevelBackground = new ImageIcon("Resources/Cookielevel.png").getImage();//쿠키 난이도
	private Image ReadyBackground = new ImageIcon("Resources/Readyy.png").getImage();//준비 그림
	private Image NoreadyBackground = new ImageIcon("Resources/Noready.png").getImage();//준비 취소 그림
	private Image VilagelevelBackground = new ImageIcon("Resources/Vilagelevel.png").getImage();//쿠키 난이도
	
	static int plyaernumber=0; //몇번 플레이어인지 구분하기위한 변수
	static int chrnumber=0;  //캐릭터그릴때 필요한 변수 디즈니 =1, 배찌 = 2, 우니 =3
	static int p1chnumber=0; //p1의 캐릭터
	static int p2chnumger=0; //p2의 캐릭터
	int count=0;
	private int p1ready=0;
	private int p2ready=0;
	private int p1chrcheck=0; //플레이어1의 캐릭터를 골랐는지 체크하는 변수
	private int p2chrcheck=0; //플레이어2의 캐릭터를 골랐는지 체크하는 변수
	
	public ReadyFrame(){ //MainFrame 생성자
		this.setTitle("MapChoice"); //창 제목
		this.setSize(805, 610); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		this.addMouseListener(this);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001초 주기로 repaint
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}

	public void paint(Graphics g) {
		if (count ==0) {
		    g.drawImage(starteBackground,0,0,null); //처음 배경 넣기 한번만 그려져야됨
		    count=1;
		}
		if (plyaernumber==1 && p1chnumber==1) { //첫번째 캐릭터 창에 디즈니 사진 띄우기
			g.drawImage(dizniackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==2) { //첫번째 캐릭터 창에 배찌사진 띄우기
			g.drawImage(bazziBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==3) { //첫번째 캐릭터 창에 우니사진 띄우기
			g.drawImage(uniBackground,35,100,null);
		}
		if (plyaernumber==2 && p2chnumger==1) { //두번째 캐릭터 창에 디즈니 사진 띄우기
			g.drawImage(dizniackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==2) { //두번째 캐릭터 창에 배찌사진 띄우기
			g.drawImage(bazziBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==3) { //두번째 캐릭터 창에 우니사진 띄우기
			g.drawImage(uniBackground,250,100,null);
		}
		if (MapChoice.MapNumber==2){  //해적맵 고르면 해적맵이랑 해적난이도 그려줌
			g.drawImage(FactoryBackground,440 ,200, null);
			g.drawImage(FactorylevelBackground, 625,190, null);
		}
		else if (MapChoice.MapNumber==1){ //쿠키맵 고르면 쿠키맵이랑 쿠키난이도 그려줌
			g.drawImage(CookieBackground,440 ,200, null);
			g.drawImage(CookielevelBackground,625 ,190, null);
		}
		else if (MapChoice.MapNumber==3){ //빌리지맵 고르면 빌리지맵이랑 빌리지난이도 그려줌
			g.drawImage(VilageBackground,440 ,200, null);
			g.drawImage(VilagelevelBackground,625 ,190, null);
		}
		if (p1chrcheck==1 && p1ready == 1) {
			g.drawImage(ReadyBackground, 12 , 520, null);
		}
		else if (p1chrcheck==1 && p1ready == 0) {
			g.drawImage(NoreadyBackground, 12 , 520, null);
		}
		if (p2chrcheck==1 && p2ready == 1) {
			g.drawImage(ReadyBackground, 230 , 520, null);
		}
		else if (p2chrcheck==1 && p2ready == 0) {
			g.drawImage(NoreadyBackground, 228 , 520, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle map = new Rectangle(620, 320, 170, 100); //맵선택 누르면 맵초이스 화면뜸
		boolean mapcheck = map.contains(e.getPoint());
		if(mapcheck) {
			new MapChoice();
		}
		
		Rectangle start = new Rectangle(460, 450, 300, 240); // 시작 누르면 시작
		boolean startcheck = start.contains(e.getPoint());
		if(startcheck) {
			if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==1) {
				if (p1ready ==1 && p2ready==1) {
					new MAP_Village(); //쿠키맵 시작
					MainFrame.music.stop();
				    ReadyFrame.this.setVisible(false); //현재 창 숨기기
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==2) {
				if (p1ready ==1 && p2ready==1) {
					MainFrame.music.stop();
					new MAP_Patriots(); //해적맵 시작
					ReadyFrame.this.setVisible(false); //현재 창 숨기기
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==3) {
				if (p1ready ==1 && p2ready==1) {
					MainFrame.music.stop();
					new MAP_Village(); //빌리지맵 시작
					ReadyFrame.this.setVisible(false); //현재 창 숨기기
				}
			}
			else if(MapChoice.MapNumber==0) {
				JOptionPane no = new JOptionPane();
				no.showMessageDialog(null, "맵을 골라주세요.");
			}
			else if(p1ready ==0 || p2ready ==0) {
				JOptionPane nono = new JOptionPane();
				nono.showMessageDialog(null, "준비를 해주세요.");
			}
		}
		
		Rectangle player1 = new Rectangle(20, 100, 180, 330); //첫번째 캐릭터 선택
		boolean p1check = player1.contains(e.getPoint());
		if(p1check) {
			plyaernumber =1; //1번플레이어
			System.out.println("첫번째 캐릭터");
		}
		Rectangle player2 = new Rectangle(230, 100, 190, 330); //두번째 캐릭터 선택
		boolean p2check = player2.contains(e.getPoint());
		if(p2check) {
			plyaernumber =2; //2번플레이어
			System.out.println("두번째 캐릭터");
		}
		
		// pready가 1번이면 준비된거고 0번이면 준비가 안된거임
		Rectangle ready1 = new Rectangle(5, 520, 200, 70); //1 플레이어 준비
		boolean ready1check = ready1.contains(e.getPoint());
		if(ready1check) {
			if (p1chrcheck == 1 && p1ready ==0) {
				p1ready =1; //준비상태가 아닐때 준비를 누르면 1번 플레이어가 레디가됨
				System.out.println("1플레이어 준비완료");
			}
			else if(p1ready == 1) {
				p1ready =0; //준비상태일 때 준비를 누르면 1번 플레이어 레디가 사라짐
				System.out.println("1플레이어 준비취소");
			}
		}
		Rectangle ready2 = new Rectangle(230, 520, 200, 70); //2 플레이어 준비
		boolean ready2check = ready2.contains(e.getPoint());
		if(ready2check) {
			if (p2chrcheck == 1 && p2ready ==0) {
				p2ready =1; //준비상태가 아닐때 준비를 누르면 2번 플레이어가 레디가됨
				System.out.println("2플레이어 준비완료");
			}
			else if(p2ready == 1) {
				p2ready =0; //준비상태일 때 준비를 누르면 2번 플레이어 레디가 사라짐
				System.out.println("2플레이어 준비취소");
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle realbazzi = new Rectangle(580, 30, 100, 140); //배찌 선택하면 사진이 바뀜
		boolean realbazzicheck = realbazzi.contains(e.getPoint());
		if(realbazzicheck) {
			System.out.println("배찌!");
			if (plyaernumber==1) {
				p1chnumber=2;
				p1chrcheck=1;
			}
			else if(plyaernumber==2) {
				p2chnumger=2;
				p2chrcheck=1;
			}
		}
		Rectangle realdizni = new Rectangle(440, 30, 100, 140); //디지니 선택하면 사진이 바뀜
		boolean realdiznicheck = realdizni.contains(e.getPoint());
		if(realdiznicheck) {
			System.out.println("디지니!");
			if (plyaernumber==1) {
				p1chnumber=1;
				p1chrcheck=1;
			}
			else if(plyaernumber==2) {
				p2chnumger=1;
				p2chrcheck=1;
			}
		}
		Rectangle realuni = new Rectangle(690, 30, 100, 140); //우니 선택하면 사진이 바뀜
		boolean realunicheck = realuni.contains(e.getPoint());
		if(realunicheck) {
			System.out.println("우니!");
			if (plyaernumber==1) {
				p1chnumber=3;
				p1chrcheck=1;
			}
			else if(plyaernumber==2) {
				p2chnumger=3;
				p2chrcheck=1;
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}