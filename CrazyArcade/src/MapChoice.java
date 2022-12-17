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
	private String MAP_VILLAGE = "Village";
	private JButton[] mapButtons; //�� ������ ����ʵ�
	private int num=0;
	static int MapNumber=0;
	private Image MapChoiceBackground = new ImageIcon(getClass().getClassLoader().getResource("MapChoice1.png")).getImage();//ó�� ��� �ֱ�
	
	
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
		if (num ==0) {
		   g.drawImage(MapChoiceBackground,0,0,null); //ó�� ��� �ֱ�
		   num=1;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle cookie = new Rectangle(0, 120, 270, 340); //��Ű�ʼ���
		boolean check = cookie.contains(e.getPoint());
		if(check) {
			MapNumber=1; //��Ű���� 1��
			System.out.println("��Ű��");
			MapChoice.this.setVisible(false); //���� â �����
		}
		Rectangle Patriots = new Rectangle(271, 120, 259, 340); //��Ʈ���ʼ���
		boolean check2 = Patriots.contains(e.getPoint());
		if(check2) {
			MapNumber=2; //�������� 2��
			System.out.println("������");
			MapChoice.this.setVisible(false); //���� â �����
		}
		Rectangle Vilage = new Rectangle(532, 120, 268, 340); //�������ʼ���
		boolean check3 = Vilage.contains(e.getPoint());
		if(check3) {
			MapNumber=3; //������ ���� 3��
			System.out.println("��������");
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