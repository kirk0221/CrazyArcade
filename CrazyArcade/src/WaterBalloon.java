import java.awt.Image;

import javax.swing.ImageIcon;

public class WaterBalloon {
	Image waterballoonImage;
	int X;
	int Y;
	int bombSize;
	
	public WaterBalloon(){
		this.bombSize = 1;
		this.waterballoonImage = new ImageIcon("Resources/bazzi_front.png").getImage();//��ǳ�� �̹���
	}
	
	public void makeWaterBalloon(int x, int y) {
		this.X = x;
		this.Y = y;
	}
}
