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
	private JButton[] mapButtons; //�� ������ ����ʵ�
	static int MapNumber=0;
	private Image MapChoiceBackground = new ImageIcon("Resources/MapChoice.png").getImage();//ó�� ��� �ֱ�
	public MapChoice(){ //MainFrame ������
		this.setTitle("MapChoice"); //â ����
		this.setSize(800, 610); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		this.addMouseListener(this);
		
	}
	

	public void paint(Graphics g) {
		g.drawImage(MapChoiceBackground,0,0,null); //ó�� ��� �ֱ�
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle cookie = new Rectangle(0, 120, 400, 320); //��Ű�ʼ���
		boolean check = cookie.contains(e.getPoint());
		if(check) {
			MapNumber=1; //��Ű���� 1��
			System.out.println("��Ű��");
			MapChoice.this.setVisible(false); //���� â �����
		}
		Rectangle Patriots = new Rectangle(400, 120, 400, 320); //��Ʈ���ʼ���
		boolean check2 = Patriots.contains(e.getPoint());
		if(check2) {
			MapNumber=2; //�������� 2��
			System.out.println("������");
			MapChoice.this.setVisible(false); //���� â �����
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