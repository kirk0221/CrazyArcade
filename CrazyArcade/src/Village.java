import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Village extends JFrame{
	public Village(){ //PangLand ������
		this.setTitle("Village"); //â ����
		this.setSize(800, 600); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(new Screen());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
	}
}
