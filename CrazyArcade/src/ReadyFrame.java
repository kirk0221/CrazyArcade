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
	private Image starteBackground = new ImageIcon("Resources/ready1.png").getImage();//처음 배경 넣기
	ImageIcon daoBackground = new ImageIcon("Resources/dao.png");//다오 배경 넣기
	ImageIcon bazziBackground = new ImageIcon("Resources/bazzi.png");//배찌 배경 넣기
	ImageIcon uniBackground = new ImageIcon("Resources/uni.png");//우니 배경 넣기
	private int plyaernumber=0; //몇번 플레이어인지 구분하기위한 변수
	
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
	private JPanel mapPanel() { //맵선택 버튼을 만들 패널
		JPanel mapPanel = new JPanel();
		mapButtons = new JButton[1];
		
		return mapPanel;
	}
	private JPanel characterPanel() { //캐릭터 선택할 패널(다우, 배찌, 우니 선택)
		JPanel characterPanel = new JPanel(); //return할 패널 생성
		characterButtons = new JButton[3]; //캐릭터는 3개
		JButton dao = new JButton(daoBackground); 
		JButton bazzi = new JButton(bazziBackground); 
		JButton uni = new JButton(uniBackground); 
		
		dao.setPreferredSize(new Dimension(160,50)); //dao 버튼 크기 160,50 설정
		bazzi.setPreferredSize(new Dimension(160,50)); //bazzi 버튼 크기 160,50 설정
		uni.setPreferredSize(new Dimension(160,50)); //uni 버튼 크기 160,50 설정
	
		characterButtons[0] = dao;
		characterButtons[1] = bazzi;
		characterButtons[1] = uni;
		
		for (int i = 0; i<characterButtons.length; i++) {
			characterPanel.add(characterButtons[i]); 
		}
		dao.addActionListener(new ActionListener() {  //다오 눌렀을때
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		bazzi.addActionListener(new ActionListener() {  //배찌 눌렀을때
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		uni.addActionListener(new ActionListener() {  //우니 눌렀을때
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		return characterPanel;
	}
	
	
	public void paint(Graphics g) {
		g.drawImage(starteBackground,0,0,null); //처음 배경 넣기
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
			if (plyaernumber ==1) {
				//플레이어1 사진변경
			}
			else if(plyaernumber ==2) {
				//플레이어2 사진변경
			}
		}
		Rectangle realdizni = new Rectangle(440, 30, 100, 140); //디지니 선택하면 사진이 바뀜
		boolean realdiznicheck = realdizni.contains(e.getPoint());
		if(realdiznicheck) {
			System.out.println("디지니!");
			if (plyaernumber ==1) {
				//플레이어1 사진변경
			}
			else if(plyaernumber ==2) {
				//플레이어2 사진변경
			}
		}
		Rectangle realuni = new Rectangle(690, 30, 100, 140); //우니 선택하면 사진이 바뀜
		boolean realunicheck = realuni.contains(e.getPoint());
		if(realunicheck) {
			System.out.println("우니!");
			if (plyaernumber ==1) {
				//플레이어1 사진변경
			}
			else if(plyaernumber ==2) {
				//플레이어2 사진변경
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