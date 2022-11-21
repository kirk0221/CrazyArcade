import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapChoice extends JFrame implements ActionListener{
	
	private String MAP_COOKIE = "Cookie";
	private String MAP_PATRIOTS = "Patriots";
	private JButton[] mapButtons; //�� ������ ����ʵ�
	private JButton[] loginButtons; //����, ���� ������ ����ʵ�
	
	public MapChoice(){ //MainFrame ������
		this.setTitle("MapChoice"); //â ����
		this.setSize(800, 600); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		this.add(centerPanel(), BorderLayout.NORTH); //â �Ʒ��� �� �г� �߰�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�ccccc
	}
	
	private JPanel centerPanel() { //â ��� �� �г�(�� ����)
		JPanel centerPanel = new JPanel(); //return�� �г� ����
		
		mapButtons = new JButton[2]; //�� �� ���� ����
		JButton Cookie = new JButton(MAP_COOKIE); //��Ű(��0)
		JButton Patriots = new JButton(MAP_PATRIOTS); //����(��1)
		
		Cookie.setPreferredSize(new Dimension(100,100)); //�η��� ��ư ũ�� 100,100 ����
		Patriots.setPreferredSize(new Dimension(100,100)); //������ ��ư ũ�� 100,100 ����c
		
		//mapButtons�� �� map �߰�
		mapButtons[0] = Cookie;
		mapButtons[1] = Patriots;
		for (int i = 0; i<mapButtons.length; i++) {
			mapButtons[i].addActionListener(this); //��ư �������� �� ���õǵ���
			centerPanel.add(mapButtons[i]); //�� ��ư �гο� �߰�
		}
		return centerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(MAP_COOKIE)) { //��Ű(��0) ���ý�
			new MAP_Cookie(); //��Ű(��0) â ����
			this.setVisible(false); //���� â �����
		}else if (e.getActionCommand().equals("Patriots")) { //����(��1) ���ý�
			new MAP_Patriots(); //����(��1) â ����
			this.setVisible(false); //���� â �����
		}
	}

}