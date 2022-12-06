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
	private Image starteBackground = new ImageIcon("Resources/ready1.png").getImage();//ó�� ��� �ֱ�
	ImageIcon daoBackground = new ImageIcon("Resources/dao.png");//�ٿ� ��� �ֱ�
	ImageIcon bazziBackground = new ImageIcon("Resources/bazzi.png");//���� ��� �ֱ�
	ImageIcon uniBackground = new ImageIcon("Resources/uni.png");//��� ��� �ֱ�
	private int plyaernumber=0; //��� �÷��̾����� �����ϱ����� ����
	
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
	private JPanel mapPanel() { //�ʼ��� ��ư�� ���� �г�
		JPanel mapPanel = new JPanel();
		mapButtons = new JButton[1];
		
		return mapPanel;
	}
	private JPanel characterPanel() { //ĳ���� ������ �г�(�ٿ�, ����, ��� ����)
		JPanel characterPanel = new JPanel(); //return�� �г� ����
		characterButtons = new JButton[3]; //ĳ���ʹ� 3��
		JButton dao = new JButton(daoBackground); 
		JButton bazzi = new JButton(bazziBackground); 
		JButton uni = new JButton(uniBackground); 
		
		dao.setPreferredSize(new Dimension(160,50)); //dao ��ư ũ�� 160,50 ����
		bazzi.setPreferredSize(new Dimension(160,50)); //bazzi ��ư ũ�� 160,50 ����
		uni.setPreferredSize(new Dimension(160,50)); //uni ��ư ũ�� 160,50 ����
	
		characterButtons[0] = dao;
		characterButtons[1] = bazzi;
		characterButtons[1] = uni;
		
		for (int i = 0; i<characterButtons.length; i++) {
			characterPanel.add(characterButtons[i]); 
		}
		dao.addActionListener(new ActionListener() {  //�ٿ� ��������
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		bazzi.addActionListener(new ActionListener() {  //���� ��������
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		uni.addActionListener(new ActionListener() {  //��� ��������
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		return characterPanel;
	}
	
	
	public void paint(Graphics g) {
		g.drawImage(starteBackground,0,0,null); //ó�� ��� �ֱ�
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
			if (plyaernumber ==1) {
				//�÷��̾�1 ��������
			}
			else if(plyaernumber ==2) {
				//�÷��̾�2 ��������
			}
		}
		Rectangle realdizni = new Rectangle(440, 30, 100, 140); //������ �����ϸ� ������ �ٲ�
		boolean realdiznicheck = realdizni.contains(e.getPoint());
		if(realdiznicheck) {
			System.out.println("������!");
			if (plyaernumber ==1) {
				//�÷��̾�1 ��������
			}
			else if(plyaernumber ==2) {
				//�÷��̾�2 ��������
			}
		}
		Rectangle realuni = new Rectangle(690, 30, 100, 140); //��� �����ϸ� ������ �ٲ�
		boolean realunicheck = realuni.contains(e.getPoint());
		if(realunicheck) {
			System.out.println("���!");
			if (plyaernumber ==1) {
				//�÷��̾�1 ��������
			}
			else if(plyaernumber ==2) {
				//�÷��̾�2 ��������
			}
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