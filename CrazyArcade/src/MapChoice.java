import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapChoice extends JFrame implements ActionListener{
	
	private String MAP_COOKIE = "Cookie";
	private String MAP_PATRIOTS = "Patriots";
	private JButton[] mapButtons; //맵 관리용 멤버필드
	private JButton[] loginButtons; //시작, 종료 관리용 멤버필드
	
	public MapChoice(){ //MainFrame 생성자
		this.setTitle("MapChoice"); //창 제목
		this.setSize(800, 600); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		this.add(centerPanel(), BorderLayout.NORTH); //창 아래로 들어갈 패널 추가
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기ccccc
	}
	
	private JPanel centerPanel() { //창 가운데 들어갈 패널(맵 선택)
		JPanel centerPanel = new JPanel(); //return할 패널 생성
		
		mapButtons = new JButton[2]; //맵 총 개수 지정
		JButton Cookie = new JButton(MAP_COOKIE); //쿠키(맵0)
		JButton Patriots = new JButton(MAP_PATRIOTS); //해적(맵1)
		
		Cookie.setPreferredSize(new Dimension(100,100)); //팡랜드 버튼 크기 100,100 설정
		Patriots.setPreferredSize(new Dimension(100,100)); //빌리지 버튼 크기 100,100 설정c
		
		//mapButtons에 각 map 추가
		mapButtons[0] = Cookie;
		mapButtons[1] = Patriots;
		for (int i = 0; i<mapButtons.length; i++) {
			mapButtons[i].addActionListener(this); //버튼 눌렀을때 맵 선택되도록
			centerPanel.add(mapButtons[i]); //맵 버튼 패널에 추가
		}
		return centerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(MAP_COOKIE)) { //쿠키(맵0) 선택시
			new MAP_Cookie(); //쿠키(맵0) 창 생성
			this.setVisible(false); //현재 창 숨기기
		}else if (e.getActionCommand().equals("Patriots")) { //해적(맵1) 선택시
			new MAP_Patriots(); //해적(맵1) 창 생성
			this.setVisible(false); //헌재 창 숨기기
		}
	}

}