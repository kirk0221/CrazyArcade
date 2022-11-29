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
	private Image centerImg;
	private Image leftImg;
	private Image rightImg;
	private Image upImg;
	private Image downImg;
	
	static LinkedList<Integer> balloonXList = new LinkedList<>();
	static LinkedList<Integer> balloonYList = new LinkedList<>();
	/* ���� ���� ��ǳ���� ���� ���� ��ũ�� ����Ʈ ���*/
	
	static LinkedList<Integer> boomballoonXList = new LinkedList<>();
	static LinkedList<Integer> boomballoonYList = new LinkedList<>();
	
	public WaterBalloon(int balloon){
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
		this.centerImg = new ImageIcon("Resources/waterbomb_center.png").getImage();
		this.leftImg = new ImageIcon("Resources/waterbomb_width.png").getImage();
		this.rightImg = new ImageIcon("Resources/waterbomb_width2.png").getImage();
		this.upImg = new ImageIcon("Resources/waterbomb_height2.png").getImage();
		this.downImg = new ImageIcon("Resources/waterbomb_height.png").getImage();
	}
	
	public Image getImg() {// ��ǳ�� �̹����� ��ũ���� �ֱ����� �Լ�
		return this.balloonImg;
	}
	
	public Image getcenterImg() {// ���� ��ǳ�� ��� �̹����� ��ũ���� �ֱ����� �Լ�
		return this.centerImg;
	}
	
	public Image getleftImg() {// ���� ��ǳ�� ���� �̹����� ��ũ���� �ֱ����� �Լ�
		return this.leftImg;
	}
	
	public Image getrightImg() {// ���� ��ǳ�� ������ �̹����� ��ũ���� �ֱ����� �Լ�
		return this.rightImg;
	}
	
	public Image getupImg() {// ���� ��ǳ�� �� �̹����� ��ũ���� �ֱ����� �Լ�
		return this.upImg;
	}
	public Image getdownImg() {// ���� ��ǳ�� �Ʒ� �̹����� ��ũ���� �ֱ����� �Լ�
		return this.downImg;
	}
	
	public int getX(int i) { /*��ǳ���� x ���� ��ũ���� �����ϱ� ���� �Լ�, 
	i�� �ε����� ��ũ������ ��ũ�� ����Ʈ�� � ���� �������� ��������.*/
		return balloonXList.get(i);
	}
	
	public int getY(int i) {
		return balloonYList.get(i);
	}
	
	public int getboomX(int i) { /*���� ��ǳ���� x ���� ��ũ���� �����ϱ� ���� �Լ�, 
	i�� �ε����� ��ũ������ ��ũ�� ����Ʈ�� � ���� �������� ��������.*/
		return boomballoonXList.get(i);
	}
	
	public int getboomY(int i) {
		return boomballoonYList.get(i);
	}
	
	
	
	public void makeWaterBalloon(int x, int y, int bombSize) {
		this.bombSize = bombSize;
		if(waterballoonmax==0) {
			System.out.println("��ǳ�� Ƚ�� �ʰ�");
		}else {
			waterballoonmax -= 1;//��ǳ���� ��ġ�Ǹ� ��ǳ�� �ִ밳���� 1 �ٿ���
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
			Screen.map_size[balloonXindex][balloonYindex] = 3; /*3�� �ٲپ� ��ǳ�� ����*/
			/*���������� �̿��ϱ� ���� 3�� �ٲپ���*/
			BalloonTimer timer = new BalloonTimer(5000);//5�� �� ��ǳ�� ����
			for(int i = 0; i<boomballoonXList.size(); i++) {//���������� �̿��ϱ� ���� 4�� �ٲپ���
				Screen.map_size[boomballoonXList.get(i)][boomballoonYList.get(i)] = 4;
				if(boomballoonXList.get(i)+bombSize<=12) {
				Screen.map_size[boomballoonXList.get(i)+bombSize][boomballoonYList.get(i)] = 4;
				}
				if(boomballoonXList.get(i)-bombSize>=0) {
				Screen.map_size[boomballoonXList.get(i)-bombSize][boomballoonYList.get(i)] = 4;
				}
				if(boomballoonYList.get(i)+bombSize<=12) {
				Screen.map_size[boomballoonXList.get(i)][boomballoonYList.get(i)+bombSize] = 4;
				}
				if(boomballoonYList.get(i)-bombSize>=0) {
				Screen.map_size[boomballoonXList.get(i)][boomballoonYList.get(i)-bombSize] = 4;
				}
			}
		}
	}
}