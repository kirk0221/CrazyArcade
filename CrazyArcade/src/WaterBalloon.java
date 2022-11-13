import java.awt.Image;

import javax.swing.ImageIcon;

public class WaterBalloon {
	Image waterballoonImage;
	int X;
	int Y;
	int bombSize;
	
	public WaterBalloon(){
		this.bombSize = 1;
		this.waterballoonImage = new ImageIcon("Resources/waterbomb.png").getImage();//물풍선 이미지
	}
	
	public void makeWaterBalloon(int x, int y) {
		this.X = x;
		this.Y = y;
		
	}
	
	public Image getImg() {//이미지를 스크린에 주기위한 함수
		return this.waterballoonImage;
	}
}
