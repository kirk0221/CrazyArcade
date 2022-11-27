import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class PangLand extends JFrame{
	public PangLand(){ //PangLand ������
		this.setTitle("PangLand"); //â ����
		this.setSize(800, 600); //â ũ��
		this.setResizable(false);//â ũ�� ����
		this.setLayout(new BorderLayout());///BorderLayout ����
		
		this.add(new Screen());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ����� ���μ������� ��� ����
		this.setLocationRelativeTo(null); //â ��� ��ġ
		this.setVisible(true); //â ���̰� �ϱ�
	}
		private void bgplay() {
			PangLand jlPlayer = null;
			try {
			FileInputStream fileInputStream = new
			FileInputStream("Resources/��-������Ʈ.wav");
			BufferedInputStream bufferedInputStream = new
			BufferedInputStream(fileInputStream);
			jlPlayer = new PangLand();
			} catch (Exception e) {
			System.out.println(e.getMessage());
			}
			final PangLand player = jlPlayer;
			new Thread() {
			public void run() {
			try {
			player.bgplay();
			} catch (Exception e) {
			System.out.println(e.getMessage());
			}
			}
			}.start();
	}
		private void playPunchSound(File file) {
			Clip clip = null;
			try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
}
		

		
