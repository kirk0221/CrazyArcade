import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MAP_Patriots extends JFrame{
	public MAP_Patriots(){ //Patriots ������
		this.setTitle("Patriots"); //â ����
		this.setSize(796, 817);//â ũ�� -> Screen�� 780,780���� �׷���
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(new Screen(1));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
	}
}
