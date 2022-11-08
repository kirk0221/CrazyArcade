import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	
	private String MAP_PANGLAND = "PangLand";
	private String MAP_VILLAGE = "Village";
	private JButton[] mapButtons; //�� ������ ����ʵ�
	
	public MainFrame(){ //MainFrame ������
		this.setTitle("CrazyArcade"); //â ����
		this.setSize(800, 600); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(centerPanel(), BorderLayout.CENTER); //â ����� �� �г� �߰�
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setVisible(true); //â ���̰� �ϱ�
	}
	
	
	private JPanel centerPanel() { //â ��� �� �г�(�� ����)
		JPanel centerPanel = new JPanel(); //return�� �г� ����
		mapButtons = new JButton[2]; //�� �� ���� ����
		JButton PangLand = new JButton(MAP_PANGLAND); //�η���(��0)
		JButton Village = new JButton(MAP_VILLAGE); //������(��1)
		//mapButtons�� �� map �߰�
		mapButtons[0] = PangLand;
		mapButtons[1] = Village;
		for (int i = 0; i<mapButtons.length; i++) {
			mapButtons[i].addActionListener(this); //��ư �������� �� ���õǵ���
			centerPanel.add(mapButtons[i]); //�� ��ư �гο� �߰�
		}
		return centerPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(MAP_PANGLAND)) { //�η���(��0) ���ý�
			new PangLand(); //�η��� â ����
			this.setVisible(false); //���� â �����
		}else if (e.getActionCommand().equals(MAP_VILLAGE)) { //������(��1) ���ý�
			new Village(); //������ â ����
			this.setVisible(false); //���� â �����
		}
	}
}
