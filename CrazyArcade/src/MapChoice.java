import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapChoice extends JFrame implements ActionListener{
	
	private String MAP_COOKIE = "Cookie";
	private String MAP_PATRIOTS = "Patriots";
	private String MAP_FOREST = "Forest";
	private JButton[] mapButtons; //�� ������ ����ʵ�
	public MapChoice(){ //MainFrame ������
		this.setTitle("MapChoice"); //â ����
		this.setSize(800, 600); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		this.add(centerPanel(), BorderLayout.NORTH); //â �Ʒ��� �� �г� �߰�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
	}
	
	private JPanel centerPanel() { //â ��� �� �г�(�� ����)
		JPanel centerPanel = new JPanel(); //return�� �г� ����
		
		mapButtons = new JButton[3]; //�� �� ���� ����
		JButton Cookie = new JButton(MAP_COOKIE); //��Ű(��0)
		JButton Patriots = new JButton(MAP_PATRIOTS); //����(��1)
		JButton Forest = new JButton(MAP_FOREST); //��(��2)
		
		Cookie.setPreferredSize(new Dimension(100,100)); //�η��� ��ư ũ�� 100,100 ����
		Patriots.setPreferredSize(new Dimension(100,100)); //������ ��ư ũ�� 100,100 ����
		Forest.setPreferredSize(new Dimension(100,100)); //������ ��ư ũ�� 100,100 ����
		
		Cookie.addActionListener(new ActionListener() {//��Ű(��0) ���ý�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MAP_Cookie(); //��Ű(��0) â ����
				MapChoice.this.setVisible(false); //���� â �����
			}
		});
		Patriots.addActionListener(new ActionListener() {//����(��1) ���ý�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MAP_Patriots(); //����(��1) â ����
				MapChoice.this.setVisible(false); //���� â �����
			}
		});
		Forest.addActionListener(new ActionListener() {//��(��2) ���ý�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MAP_FOREST(); //��(��2) â ����
				MapChoice.this.setVisible(false); //���� â �����
			}
		});
		
		//mapButtons�� �� map �߰�
		mapButtons[0] = Cookie;
		mapButtons[1] = Patriots;
		mapButtons[2] = Forest;
		for (int i = 0; i<mapButtons.length; i++) {
			mapButtons[i].addActionListener(this); //��ư �������� �� ���õǵ���
			centerPanel.add(mapButtons[i]); //�� ��ư �гο� �߰�
		}
		
		return centerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}