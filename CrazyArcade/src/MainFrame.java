import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;


import javazoom.jl.player.Player;

public class MainFrame extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MAP_COOKIE = "Cookie";
	private String MAP_PATRIOTS = "Patriots";
	private String MAP_FOREST = "Forest";
	private JButton[] mapButtons; //�� ������ ����ʵ�
	
	public MainFrame(){ //MainFrame ������
		this.setTitle("CrazyArcade"); //â ����
		this.setSize(800, 600); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(centerPanel(), BorderLayout.CENTER); //â ����� �� �г� �߰�
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		
		bgplay(); // ��� ����
		
	}
	
	private void bgplay() { // ��� ���� (������ ������ ���� ���� ����)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/gameReady.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        final Player player = jlPlayer;
        new Thread() {
            public void run() {
                try {
                	player.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
    }
	
	private JPanel centerPanel() { //â ��� �� �г�(�� ����)
		JPanel centerPanel = new JPanel(); //return�� �г� ����
		
		mapButtons = new JButton[3]; //�� �� ���� ����
		JButton Cookie = new JButton(MAP_COOKIE); //��Ű(��0)
		JButton Patriots = new JButton(MAP_PATRIOTS); //����(��1)
		JButton Forest = new JButton(MAP_FOREST); //��(��2)
		
		Cookie.setPreferredSize(new Dimension(100,100)); //��Ű ��ư ũ�� 100,100 ����
		Patriots.setPreferredSize(new Dimension(100,100)); //���� ��ư ũ�� 100,100 ����
		Forest.setPreferredSize(new Dimension(100,100)); //�� ��ư ũ�� 100,100 ����
		
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
		if (e.getActionCommand().equals(MAP_COOKIE)) { //��Ű(��0) ���ý�
			new MAP_Cookie(); //��Ű(��0) â ����
			this.setVisible(false); //���� â �����
		}else if (e.getActionCommand().equals(MAP_PATRIOTS)) { //����(��1) ���ý�
			new MAP_Patriots(); //����(��1) â ����
			this.setVisible(false); //���� â �����
		}else if (e.getActionCommand().equals(MAP_FOREST)) { //��(��2) ���ý�
			new MAP_FOREST(); // ��(��2) â ����
			this.setVisible(false); //���� â �����
		}
	}
}
