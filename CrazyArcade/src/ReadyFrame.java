import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReadyFrame extends JFrame implements MouseListener {
	private JButton[] mapButtons;
	private JButton[] characterButtons;
	private Image starteBackground = new ImageIcon("Resources/ready2.png").getImage();//ó�� ��� �ֱ�
	private Image bazziBackground = new ImageIcon("Resources/bazzi.png").getImage();//���� ��� �ֱ�
	private Image uniBackground = new ImageIcon("Resources/uni.png").getImage();//��� ��� �ֱ�
	private Image dizniackground = new ImageIcon("Resources/dizni.png").getImage();//����� ��� �ֱ�
	private Image FactoryBackground = new ImageIcon("Resources/Factorymap.png").getImage();//���丮 ��� �ֱ�
	private Image CookieBackground = new ImageIcon("Resources/Cookiemap.png").getImage();//��Ű ���
	private Image VilageBackground = new ImageIcon("Resources/Vilagemap.png").getImage();//��Ű ���
	private Image FactorylevelBackground = new ImageIcon("Resources/factorylevel.png").getImage();//���丮 ���̵� �ֱ�
	private Image CookielevelBackground = new ImageIcon("Resources/Cookielevel.png").getImage();//��Ű ���̵�
	private Image ReadyBackground = new ImageIcon("Resources/Readyy.png").getImage();//�غ� �׸�
	private Image NoreadyBackground = new ImageIcon("Resources/Noready.png").getImage();//�غ� ��� �׸�
	private Image VilagelevelBackground = new ImageIcon("Resources/Vilagelevel.png").getImage();//��Ű ���̵�
	
	static int plyaernumber=0; //��� �÷��̾����� �����ϱ����� ����
	static int chrnumber=0;  //ĳ���ͱ׸��� �ʿ��� ���� ����� =1, ���� = 2, ��� =3
	static int p1chnumber=0; //p1�� ĳ����
	static int p2chnumger=0; //p2�� ĳ����
	int count=0;
	private int p1ready=0;
	private int p2ready=0;
	private int p1chrcheck=0; //�÷��̾�1�� ĳ���͸� ������� üũ�ϴ� ����
	private int p2chrcheck=0; //�÷��̾�2�� ĳ���͸� ������� üũ�ϴ� ����
	
	public ReadyFrame(){ //MainFrame ������
		this.setTitle("MapChoice"); //â ����
		this.setSize(805, 610); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		this.addMouseListener(this);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {//0.001�� �ֱ�� repaint
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
			}
		},0, 1);
	}

	public void paint(Graphics g) {
		if (count ==0) {
		    g.drawImage(starteBackground,0,0,null); //ó�� ��� �ֱ� �ѹ��� �׷����ߵ�
		    count=1;
		}
		if (plyaernumber==1 && p1chnumber==1) { //ù��° ĳ���� â�� ����� ���� ����
			g.drawImage(dizniackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==2) { //ù��° ĳ���� â�� ������� ����
			g.drawImage(bazziBackground,35,100,null);
		}
		else if (plyaernumber==1 && p1chnumber==3) { //ù��° ĳ���� â�� ��ϻ��� ����
			g.drawImage(uniBackground,35,100,null);
		}
		if (plyaernumber==2 && p2chnumger==1) { //�ι�° ĳ���� â�� ����� ���� ����
			g.drawImage(dizniackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==2) { //�ι�° ĳ���� â�� ������� ����
			g.drawImage(bazziBackground,250,100,null);
		}
		else if (plyaernumber==2 && p2chnumger==3) { //�ι�° ĳ���� â�� ��ϻ��� ����
			g.drawImage(uniBackground,250,100,null);
		}
		if (MapChoice.MapNumber==2){  //������ ���� �������̶� �������̵� �׷���
			g.drawImage(FactoryBackground,440 ,200, null);
			g.drawImage(FactorylevelBackground, 625,190, null);
		}
		else if (MapChoice.MapNumber==1){ //��Ű�� ���� ��Ű���̶� ��Ű���̵� �׷���
			g.drawImage(CookieBackground,440 ,200, null);
			g.drawImage(CookielevelBackground,625 ,190, null);
		}
		else if (MapChoice.MapNumber==3){ //�������� ���� ���������̶� ���������̵� �׷���
			g.drawImage(VilageBackground,440 ,200, null);
			g.drawImage(VilagelevelBackground,625 ,190, null);
		}
		if (p1chrcheck==1 && p1ready == 1) {
			g.drawImage(ReadyBackground, 12 , 520, null);
		}
		else if (p1chrcheck==1 && p1ready == 0) {
			g.drawImage(NoreadyBackground, 12 , 520, null);
		}
		if (p2chrcheck==1 && p2ready == 1) {
			g.drawImage(ReadyBackground, 230 , 520, null);
		}
		else if (p2chrcheck==1 && p2ready == 0) {
			g.drawImage(NoreadyBackground, 228 , 520, null);
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
			if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==1) {
				if (p1ready ==1 && p2ready==1) {
					new MAP_Village(); //��Ű�� ����
					MainFrame.music.stop();
				    ReadyFrame.this.setVisible(false); //���� â �����
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==2) {
				if (p1ready ==1 && p2ready==1) {
					MainFrame.music.stop();
					new MAP_Patriots(); //������ ����
					ReadyFrame.this.setVisible(false); //���� â �����
				}
			}
			else if (p1chnumber !=0 && p2chnumger !=0 && MapChoice.MapNumber==3) {
				if (p1ready ==1 && p2ready==1) {
					MainFrame.music.stop();
					new MAP_Village(); //�������� ����
					ReadyFrame.this.setVisible(false); //���� â �����
				}
			}
			else if(MapChoice.MapNumber==0) {
				JOptionPane no = new JOptionPane();
				no.showMessageDialog(null, "���� ����ּ���.");
			}
			else if(p1ready ==0 || p2ready ==0) {
				JOptionPane nono = new JOptionPane();
				nono.showMessageDialog(null, "�غ� ���ּ���.");
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
		
		// pready�� 1���̸� �غ�ȰŰ� 0���̸� �غ� �ȵȰ���
		Rectangle ready1 = new Rectangle(5, 520, 200, 70); //1 �÷��̾� �غ�
		boolean ready1check = ready1.contains(e.getPoint());
		if(ready1check) {
			if (p1chrcheck == 1 && p1ready ==0) {
				p1ready =1; //�غ���°� �ƴҶ� �غ� ������ 1�� �÷��̾ ���𰡵�
				System.out.println("1�÷��̾� �غ�Ϸ�");
			}
			else if(p1ready == 1) {
				p1ready =0; //�غ������ �� �غ� ������ 1�� �÷��̾� ���� �����
				System.out.println("1�÷��̾� �غ����");
			}
		}
		Rectangle ready2 = new Rectangle(230, 520, 200, 70); //2 �÷��̾� �غ�
		boolean ready2check = ready2.contains(e.getPoint());
		if(ready2check) {
			if (p2chrcheck == 1 && p2ready ==0) {
				p2ready =1; //�غ���°� �ƴҶ� �غ� ������ 2�� �÷��̾ ���𰡵�
				System.out.println("2�÷��̾� �غ�Ϸ�");
			}
			else if(p2ready == 1) {
				p2ready =0; //�غ������ �� �غ� ������ 2�� �÷��̾� ���� �����
				System.out.println("2�÷��̾� �غ����");
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle realbazzi = new Rectangle(580, 30, 100, 140); //���� �����ϸ� ������ �ٲ�
		boolean realbazzicheck = realbazzi.contains(e.getPoint());
		if(realbazzicheck) {
			System.out.println("����!");
			if (plyaernumber==1) {
				p1chnumber=2;
				p1chrcheck=1;
			}
			else if(plyaernumber==2) {
				p2chnumger=2;
				p2chrcheck=1;
			}
		}
		Rectangle realdizni = new Rectangle(440, 30, 100, 140); //������ �����ϸ� ������ �ٲ�
		boolean realdiznicheck = realdizni.contains(e.getPoint());
		if(realdiznicheck) {
			System.out.println("������!");
			if (plyaernumber==1) {
				p1chnumber=1;
				p1chrcheck=1;
			}
			else if(plyaernumber==2) {
				p2chnumger=1;
				p2chrcheck=1;
			}
		}
		Rectangle realuni = new Rectangle(690, 30, 100, 140); //��� �����ϸ� ������ �ٲ�
		boolean realunicheck = realuni.contains(e.getPoint());
		if(realunicheck) {
			System.out.println("���!");
			if (plyaernumber==1) {
				p1chnumber=3;
				p1chrcheck=1;
			}
			else if(plyaernumber==2) {
				p2chnumger=3;
				p2chrcheck=1;
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