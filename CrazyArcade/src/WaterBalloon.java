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
	/* 여러 개의 물풍선을 놓기 위해 링크드 리스트 사용*/
	
	public WaterBalloon(int balloon){
		this.bombSize = 1;
		this.balloontype = balloon;
		this.waterballoonmax = 3; //물풍선 최대 개수 3개
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
	
	public Image getImg() {// 물풍선 이미지를 스크린에 주기위한 함수
		return this.balloonImg;
	}
	
	public int getX(int i) { /*물풍선의 x 값을 스크린에 전달하기 위한 함수, 
	i는 인덱스로 스크린에서 링크드 리스트의 어떤 값을 전달할지 지정받음.*/
		return balloonXList.get(i);
	}
	
	public int getY(int i) {
		return balloonYList.get(i);
	}
	
	
	
	public void makeWaterBalloon(int x, int y) {
		if(waterballoonmax==0) {
			System.out.println("물풍선 횟수 초과");
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
			balloonXList.add(balloonXindex); /*물풍선 x 좌표 인덱스를 저장하는 링크드 리스트*/
			balloonYList.add(balloonYindex); /*물풍선 y 좌표 인덱스를 저장하는 링크드 리스트*/
			Screen.map_size[balloonXindex][balloonYindex] = 2; /*2로 바꾸어 물풍선 놓기*/
			/*내부적으로 이용하기 위해 2로 바꾸어줌*/
			BalloonTimer timer = new BalloonTimer(5000);
		}
	}
}