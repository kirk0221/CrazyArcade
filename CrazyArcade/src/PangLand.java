import java.awt.BorderLayout;

import javax.swing.JFrame;

public class PangLand extends JFrame{
	public PangLand(){ //PangLand 생성자
		this.setTitle("PangLand"); //창 제목
		this.setSize(800, 600); //창 크기
		this.setResizable(false);//창 크기 고정
		this.setLayout(new BorderLayout());///BorderLayout 설정
		
		this.add(new Screen());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 창 종료시 프로세스까지 모두 종료
		this.setLocationRelativeTo(null); //창 가운데 위치
		this.setVisible(true); //창 보이게 하기
	}
}
