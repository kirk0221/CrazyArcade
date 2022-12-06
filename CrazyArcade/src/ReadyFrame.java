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

public class ReadyFrame extends JFrame implements MouseListener {
	private JButton[] mapButtons;
	private JButton[] characterButtons;
	private Image starteBackground = new ImageIcon("Resources/ready2.png").getImage();//ó�� ��� �ֱ�
	private Image bazziBackground = new ImageIcon("Resources/bazzi.png").getImage();//���� ��� �ֱ�
	private Image uniBackground = new ImageIcon("Resources/uni.png").getImage();//��� ��� �ֱ�
	private Image dizniackground = new ImageIcon("Resources/dizni.png").getImage();//������ ��� �ֱ�
	static int plyaernumber=0; //��� �÷��̾����� �����ϱ����� ����
	static int chrnumber=0;  //ĳ���ͱ׸��� �ʿ��� ���� ������ =1, ���� = 2, ��� =3
	static int p1chnumber=0; //p1�� ĳ����
	static int p2chnumger=0; //p2�� ĳ����
	int count=0;
	public ReadyFrame(){ //MainFrame ������
		this.setTitle("MapChoice"); //â ����
		this.setSize(805, 610); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		this.addMouseListener(this);
	}

	public void paint(Graphics g) {
		if (count ==0) {
		    g.drawImage(starteBackground,0,0,null); //ó�� ��� �ֱ� �ѹ��� �׷����ߵ�
		    count=1;
		}
		if (plyaernumber==1 && chrnumber==1) { //ù��° ĳ���� â�� ����� ���� ����
			g.drawImage(dizniackground,35,100,null);
			p1chnumber=1;
		}
		else if (plyaernumber==1 && chrnumber==2) { //ù��° ĳ���� â�� ������� ����
			g.drawImage(bazziBackground,35,100,null);
			p1chnumber=2;
		}
		else if (plyaernumber==1 && chrnumber==3) { //ù��° ĳ���� â�� ��ϻ��� ����
			g.drawImage(uniBackground,35,100,null);
			p1chnumber=3;
		}
		if (plyaernumber==2 && chrnumber==1) { //�ι�° ĳ���� â�� ������ ���� ����
			g.drawImage(dizniackground,250,100,null);
			p2chnumger=1;
		}
		else if (plyaernumber==2 && chrnumber==2) { //�ι�° ĳ���� â�� ������� ����
			g.drawImage(bazziBackground,250,100,null);
			p2chnumger=2;
		}
		else if (plyaernumber==2 && chrnumber==3) { //�ι�° ĳ���� â�� ��ϻ��� ����
			g.drawImage(uniBackground,250,100,null);
			p2chnumger=3;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle map = new Rectangle(620, 320, 170, 100); //�ʼ��� ������ �����̽� ȭ���
		boolean mapcheck = map.contains(e.getPoint());
		if(mapcheck) {
			new MapChoice();
		}
		
		Rectangle start = new Rectangle(460, 450, 300, 240); // ���� ������ ����
		boolean startcheck = start.contains(e.getPoint());
		if(startcheck) {
			if (MapChoice.MapNumber==1) {
				new MAP_Cookie(); //��Ű�� ����
			    ReadyFrame.this.setVisible(false); //���� â �����
			}
			else if (MapChoice.MapNumber==2) {
				new MAP_Patriots(); //������ ����
				ReadyFrame.this.setVisible(false); //���� â �����
			}
		}
		
		Rectangle player1 = new Rectangle(20, 100, 180, 330); //ù��° ĳ���� ����
		boolean p1check = player1.contains(e.getPoint());
		if(p1check) {
			plyaernumber =1; //1���÷��̾�
			System.out.println("ù��° ĳ����");
		}
		Rectangle player2 = new Rectangle(230, 100, 190, 330); //�ι�° ĳ���� ����
		boolean p2check = player2.contains(e.getPoint());
		if(p2check) {
			plyaernumber =2; //2���÷��̾�
			System.out.println("�ι�° ĳ����");
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle realbazzi = new Rectangle(580, 30, 100, 140); //���� �����ϸ� ������ �ٲ�
		boolean realbazzicheck = realbazzi.contains(e.getPoint());
		if(realbazzicheck) {
			System.out.println("����!");
			chrnumber =2;
			repaint();
		}
		Rectangle realdizni = new Rectangle(440, 30, 100, 140); //������ �����ϸ� ������ �ٲ�
		boolean realdiznicheck = realdizni.contains(e.getPoint());
		if(realdiznicheck) {
			System.out.println("������!");
			chrnumber =1;
			repaint();
		}
		Rectangle realuni = new Rectangle(690, 30, 100, 140); //��� �����ϸ� ������ �ٲ�
		boolean realunicheck = realuni.contains(e.getPoint());
		if(realunicheck) {
			System.out.println("���!");
			chrnumber =3;
			repaint();
		}
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