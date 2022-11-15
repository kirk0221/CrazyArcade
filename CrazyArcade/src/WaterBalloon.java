
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
		System.out.println("¹°Ç³¼± X: "+this.X+"¹°Ç³¼± Y: " + this.Y+"ÇÃ·¹ÀÌ¾î "+playertype);
	}
	
}
