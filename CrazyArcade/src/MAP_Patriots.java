import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MAP_Patriots extends JFrame{
	public MAP_Patriots(){ //PangLand ������
		this.setTitle("Village"); //â ����
		this.setSize(800, 800); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(new Screen());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
	}
}