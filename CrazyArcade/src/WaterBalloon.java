
public class WaterBalloon {
	int X;
	int Y;
	int bombSize;
	int playertype;
	
	public WaterBalloon(int playertype){
		this.bombSize = 1;
		this.playertype = playertype;
	}
	
	public void makeWaterBalloon(int x, int y) {
		this.X = x;
		this.Y = y;
		System.out.println("��ǳ�� X: "+this.X+"��ǳ�� Y: " + this.Y+"�÷��̾� "+playertype);
	}
	
}
