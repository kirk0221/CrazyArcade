import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	
	private String MAP_PANGLAND = "PangLand";
	private String MAP_VILLAGE = "Village";
	private String MAP_COOKIE = "Cookie";
	private JButton[] mapButtons; //맵 관리용 멤버필드
	
	public MainFrame(){ //MainFrame 생성자
		this.setTitle("CrazyArcade"); //창 제목
		this.setSize(800, 800); //창 크기               // 창 크기 수정
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		
		this.add(centerPanel(), BorderLayout.CENTER); //창 가운데로 들어갈 패널 추가
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setVisible(true); //창 보이게 하기
	}
	
	
	private JPanel centerPanel() { //창 가운데 들어갈 패널(맵 선택)
		JPanel centerPanel = new JPanel(); //return할 패널 생성
		mapButtons = new JButton[3]; //맵 총 개수 지정
		JButton PangLand = new JButton(MAP_PANGLAND); //팡랜드(맵0)
		JButton Village = new JButton(MAP_VILLAGE); //빌리지(맵1)
		JButton Cookie = new JButton(MAP_COOKIE); //쿠키(맵2)
		//mapButtons에 각 map 추가
		mapButtons[0] = PangLand;
		mapButtons[1] = Village;
		mapButtons[2] = Cookie;
		
		for (int i = 0; i<mapButtons.length; i++) {
			mapButtons[i].addActionListener(this); //버튼 눌렀을때 맵 선택되도록
			centerPanel.add(mapButtons[i]); //맵 버튼 패널에 추가
		}
		return centerPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(MAP_PANGLAND)) { //팡랜드(맵0) 선택시
			new PangLand(); //팡랜드 창 생성
			this.setVisible(false); //현재 창 숨기기
		}else if (e.getActionCommand().equals(MAP_VILLAGE)) { //빌리지(맵1) 선택시
			new Village(); //빌리지 창 생성
			this.setVisible(false); //헌재 창 숨기기
		}else if (e.getActionCommand().equals(MAP_COOKIE)) { //쿠키(맵2) 선택시
			new Cookie(); //쿠키 창 생성
			this.setVisible(false); //헌재 창 숨기기
		}
	}
}
