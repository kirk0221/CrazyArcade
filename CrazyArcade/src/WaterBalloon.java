import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;

public class WaterBalloon {
	int X;
	int Y;
	int bombSize;
	int balloontype;
	static int waterballoonmax;
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	
	public int balloonXindex;
	public int balloonYindex;
	
	private Image balloonImg;
	
	static LinkedList<Integer> balloonXList = new LinkedList<>();
	static LinkedList<Integer> balloonYList = new LinkedList<>();
	/* ���� ���� ��ǳ���� ���� ���� ��ũ�� ����Ʈ ���*/
	
	public WaterBalloon(int balloon){
		this.bombSize = 1;
		this.balloontype = balloon;
		this.waterballoonmax = 3; //��ǳ�� �ִ� ���� 3��
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		int locationnum = 10;
		for(int i=0; i<13;i++) {
				this.mapXlocationlist[i] = locationnum;
				this.mapYlocationlist[i] = locationnum;
				locationnum += 60;
		}
		
		this.balloonImg = new ImageIcon("Resources/waterbomb.png").getImage();
	}
	
	public Image getImg() {// ��ǳ�� �̹����� ��ũ���� �ֱ����� �Լ�
		return this.balloonImg;
	}
	
	public int getX(int i) { /*��ǳ���� x ���� ��ũ���� �����ϱ� ���� �Լ�, 
	i�� �ε����� ��ũ������ ��ũ�� ����Ʈ�� � ���� �������� ��������.*/
		return balloonXList.get(i);
	}
	
	public int getY(int i) {
		return balloonYList.get(i);
	}
	
	
	
	public void makeWaterBalloon(int x, int y) {
		if(waterballoonmax==0) {
			System.out.println("��ǳ�� Ƚ�� �ʰ�");
		}else {
			waterballoonmax -= 1;
			this.X = x;
			this.Y = y;
			for(int i=0; i<13;i++) {
				if((-(X-mapXlocationlist[i])<40) && ((mapXlocationlist[i]-X)<40)) {
					balloonXindex = i;
				}
			}
			for(int i=0; i<13;i++) {
				if((-(Y-mapYlocationlist[i])<40) && ((mapYlocationlist[i]-Y)<40)) {
					balloonYindex = i;
				}
			}
			balloonXList.add(balloonXindex); /*��ǳ�� x ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
			balloonYList.add(balloonYindex); /*��ǳ�� y ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
			Screen.map_size[balloonXindex][balloonYindex] = 2; /*2�� �ٲپ� ��ǳ�� ����*/
			/*���������� �̿��ϱ� ���� 2�� �ٲپ���*/
			BalloonTimer timer = new BalloonTimer(5000);
		}
	}
}