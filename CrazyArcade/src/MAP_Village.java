import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JFrame;

import javazoom.jl.player.Player;

public class MAP_Village extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MAP_Village(){ //Patriots ������
		this.setTitle("Village"); //â ����
		this.setSize(796, 817);//â ũ�� -> Screen�� 780,780���� �׷���
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(new Screen(2));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
		
		bgplay();
	}
	private void bgplay() { // ��� ���� (������ ������ ���� ���� ����)
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Resources/patriotsmap.mp3");//���� ���� �ٲ�ߵ�
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
}