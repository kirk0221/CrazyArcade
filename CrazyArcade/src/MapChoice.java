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

public class MapChoice extends JFrame implements MouseListener{
	
	private String MAP_COOKIE = "Cookie";
	private String MAP_PATRIOTS = "Patriots";
	private JButton[] mapButtons; //맵 관리용 멤버필드
	static int MapNumber=0;
	private Image MapChoiceBackground = new ImageIcon("Resources/MapChoice.png").getImage();//처음 배경 넣기
	public MapChoice(){ //MainFrame 생성자
		this.setTitle("MapChoice"); //창 제목
		this.setSize(800, 610); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
		this.addMouseListener(this);
		
	}
	

	public void paint(Graphics g) {
		g.drawImage(MapChoiceBackground,0,0,null); //처음 배경 넣기
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle cookie = new Rectangle(0, 120, 400, 320); //쿠키맵선택
		boolean check = cookie.contains(e.getPoint());
		if(check) {
			MapNumber=1; //쿠키맵은 1번
			System.out.println("쿠키맵");
			MapChoice.this.setVisible(false); //현재 창 숨기기
		}
		Rectangle Patriots = new Rectangle(400, 120, 400, 320); //패트릿맵선택
		boolean check2 = Patriots.contains(e.getPoint());
		if(check2) {
			MapNumber=2; //해적맵은 2번
			System.out.println("해적맵");
			MapChoice.this.setVisible(false); //헌재 창 숨기기
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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