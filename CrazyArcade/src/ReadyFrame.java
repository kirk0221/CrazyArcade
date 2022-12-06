import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReadyFrame extends JFrame implements MouseListener {
	private JButton[] mapButtons;
	private JButton[] characterButtons;
	private Image starteBackground = new ImageIcon("Resources/ready2.png").getImage();//처음 배경 넣기
	private Image bazziBackground = new ImageIcon("Resources/bazzi.png").getImage();//배찌 배경 넣기
	private Image uniBackground = new ImageIcon("Resources/uni.png").getImage();//우니 배경 넣기
	private Image dizniackground = new ImageIcon("Resources/dizni.png").getImage();//디지니 배경 넣기
	static int plyaernumber=0; //몇번 플레이어인지 구분하기위한 변수
	static int chrnumber=0;  //캐릭터그릴때 필요한 변수 디지니 =1, 배찌 = 2, 우니 =3
	static int p1chnumber=0; //p1의 캐릭터
	static int p2chnumger=0; //p2의 캐릭터
	int count=0;
	public ReadyFrame(){ //MainFrame 생성자
		this.setTitle("MapChoice"); //창 제목
		this.setSize(805, 610); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		this.addMouseListener(this);
	}

	public void paint(Graphics g) {
		if (count ==0) {
		    g.drawImage(starteBackground,0,0,null); //처음 배경 넣기 한번만 그려져야됨
		    count=1;
		}
		if (plyaernumber==1 && chrnumber==1) { //첫번째 캐릭터 창에 디즈니 사진 띄우기
			g.drawImage(dizniackground,35,100,null);
			p1chnumber=1;
		}
		else if (plyaernumber==1 && chrnumber==2) { //첫번째 캐릭터 창에 배찌사진 띄우기
			g.drawImage(bazziBackground,35,100,null);
			p1chnumber=2;
		}
		else if (plyaernumber==1 && chrnumber==3) { //첫번째 캐릭터 창에 우니사진 띄우기
			g.drawImage(uniBackground,35,100,null);
			p1chnumber=3;
		}
		if (plyaernumber==2 && chrnumber==1) { //두번째 캐릭터 창에 디지니 사진 띄우기
			g.drawImage(dizniackground,250,100,null);
			p2chnumger=1;
		}
		else if (plyaernumber==2 && chrnumber==2) { //두번째 캐릭터 창에 배찌사진 띄우기
			g.drawImage(bazziBackground,250,100,null);
			p2chnumger=2;
		}
		else if (plyaernumber==2 && chrnumber==3) { //두번째 캐릭터 창에 우니사진 띄우기
			g.drawImage(uniBackground,250,100,null);
			p2chnumger=3;
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
			if (MapChoice.MapNumber==1) {
				new MAP_Cookie(); //쿠키맵 시작
			    ReadyFrame.this.setVisible(false); //현재 창 숨기기
			}
			else if (MapChoice.MapNumber==2) {
				new MAP_Patriots(); //해적맵 시작
				ReadyFrame.this.setVisible(false); //현재 창 숨기기
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
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle realbazzi = new Rectangle(580, 30, 100, 140); //배찌 선택하면 사진이 바뀜
		boolean realbazzicheck = realbazzi.contains(e.getPoint());
		if(realbazzicheck) {
			System.out.println("배찌!");
			chrnumber =2;
			repaint();
		}
		Rectangle realdizni = new Rectangle(440, 30, 100, 140); //디지니 선택하면 사진이 바뀜
		boolean realdiznicheck = realdizni.contains(e.getPoint());
		if(realdiznicheck) {
			System.out.println("디지니!");
			chrnumber =1;
			repaint();
		}
		Rectangle realuni = new Rectangle(690, 30, 100, 140); //우니 선택하면 사진이 바뀜
		boolean realunicheck = realuni.contains(e.getPoint());
		if(realunicheck) {
			System.out.println("우니!");
			chrnumber =3;
			repaint();
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