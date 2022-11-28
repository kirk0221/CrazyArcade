import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;


import javazoom.jl.player.Player;

public class MainFrame extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MAP_COOKIE = "Cookie";
	private String MAP_PATRIOTS = "Patriots";
	private String MAP_FOREST = "Forest";
	private JButton[] mapButtons; //맵 관리용 멤버필드
	
	public MainFrame(){ //MainFrame 생성자
		this.setTitle("CrazyArcade"); //창 제목
		this.setSize(800, 600); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		
		this.add(centerPanel(), BorderLayout.CENTER); //창 가운데로 들어갈 패널 추가
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		
		bgplay(); // 배경 음악
		
	}
	
	private void bgplay() { // 배경 음악 (프기프 교수님 참조 파일 참고)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/gameReady.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        final Player player = jlPlayer;
        new Thread() {
            public void run() {
                try {
                	player.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
    }
	
	private JPanel centerPanel() { //창 가운데 들어갈 패널(맵 선택)
		JPanel centerPanel = new JPanel(); //return할 패널 생성
		
		mapButtons = new JButton[3]; //맵 총 개수 지정
		JButton Cookie = new JButton(MAP_COOKIE); //쿠키(맵0)
		JButton Patriots = new JButton(MAP_PATRIOTS); //해적(맵1)
		JButton Forest = new JButton(MAP_FOREST); //숲(맵2)
		
		Cookie.setPreferredSize(new Dimension(100,100)); //쿠키 버튼 크기 100,100 설정
		Patriots.setPreferredSize(new Dimension(100,100)); //해적 버튼 크기 100,100 설정
		Forest.setPreferredSize(new Dimension(100,100)); //숲 버튼 크기 100,100 설정
		
		//mapButtons에 각 map 추가
		mapButtons[0] = Cookie;
		mapButtons[1] = Patriots;
		mapButtons[2] = Forest;
		
		for (int i = 0; i<mapButtons.length; i++) {
			mapButtons[i].addActionListener(this); //버튼 눌렀을때 맵 선택되도록
			centerPanel.add(mapButtons[i]); //맵 버튼 패널에 추가
		}
		return centerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(MAP_COOKIE)) { //쿠키(맵0) 선택시
			new MAP_Cookie(); //쿠키(맵0) 창 생성
			this.setVisible(false); //현재 창 숨기기
		}else if (e.getActionCommand().equals(MAP_PATRIOTS)) { //해적(맵1) 선택시
			new MAP_Patriots(); //해적(맵1) 창 생성
			this.setVisible(false); //현재 창 숨기기
		}else if (e.getActionCommand().equals(MAP_FOREST)) { //숲(맵2) 선택시
			new MAP_FOREST(); // 숲(맵2) 창 생성
			this.setVisible(false); //현재 창 숨기기
		}
	}
}
