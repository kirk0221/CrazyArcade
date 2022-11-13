import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MAP_Patriots extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MAP_Patriots(){ //Patriots 생성자
		this.setTitle("Patriots"); //창 제목
		this.setSize(796, 817);//창 크기 -> Screen이 780,780으로 그려짐
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		
		this.add(new Screen(1));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
	}
}
