import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MAP_Cookie extends JFrame{
	
	public MAP_Cookie(){//Cookie ������
		this.setTitle("Cookie");//â ����
		this.setSize(796, 817);//â ũ�� -> Screen�� 780,780���� �׷���
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());//BorderLayout ����
		
		this.add(new Screen(0));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
	}

}
