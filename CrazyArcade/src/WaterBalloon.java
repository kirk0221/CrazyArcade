import java.awt.Image;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class WaterBalloon {
	int X;
	int Y;
	int bombSize;
	int playertype;
	int waterballoonmax;
	
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
	
	public WaterBalloon(int playertype){
		this.playertype = playertype;
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
		if(playertype == 1 && waterballoonmax==0) {
			System.out.println("player1 ��ǳ�� Ƚ�� �ʰ�");
		}else if(playertype == 2 && waterballoonmax==0) {
			System.out.println("player2 ��ǳ�� Ƚ�� �ʰ�");
		}else {
			if (playertype == 1) {
				waterballoonmax -= 1;//��ǳ���� ��ġ�Ǹ� ��ǳ�� �ִ밳���� 1 �ٿ���
			}else if (playertype == 2) {
				waterballoonmax -= 1;//��ǳ���� ��ġ�Ǹ� ��ǳ�� �ִ밳���� 1 �ٿ���
			}
			this.X = x;
			this.Y = y;
			for(int i=0; i<13;i++) {
				if((-(X-mapXlocationlist[i])<40) || ((mapXlocationlist[i]-X)<40)) {
					balloonXindex = i;
				}
			}
			for(int i=0; i<13;i++) {
				if((-(Y-mapYlocationlist[i])<40) || ((mapYlocationlist[i]-Y)<40)) {
					balloonYindex = i;
				}
			}
			balloonXList.add(balloonXindex); /*��ǳ�� x ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
			balloonYList.add(balloonYindex); /*��ǳ�� y ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
			BoomJudge.map_size[balloonYindex][balloonXindex] = 3; /*3�� �ٲپ� ��ǳ�� ����*/
			BoomJudge.die();
			/*���������� �̿��ϱ� ���� 3�� �ٲپ���*/
			BalloonTimer timer = new BalloonTimer(5000);//5�� �� ��ǳ�� ����
			Timer boom = new Timer();
			TimerTask boomtask = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub

					for(int i = 0; i<boomballoonXList.size(); i++) {//���������� �̿��ϱ� ���� 4�� �ٲپ��� -> 4�� �ȹٲ�
						BoomJudge.map_size[balloonYindex][balloonXindex] = 4;
						if(boomballoonXList.get(i)+bombSize<=12) {
							BoomJudge.map_size[balloonYindex][balloonXindex+bombSize] = 4;
						}
						if(boomballoonXList.get(i)-bombSize>=0) {
							BoomJudge.map_size[balloonYindex][balloonXindex-bombSize] = 4;
						}
						if(boomballoonYList.get(i)+bombSize<=12) {
							BoomJudge.map_size[balloonYindex+bombSize][balloonXindex] = 4;
						}
						if(boomballoonYList.get(i)-bombSize>=0) {
							BoomJudge.map_size[balloonYindex-bombSize][balloonXindex] = 4;
						}
					}
					waterballoonmax +=1;
					BoomJudge.die();

					
					/*�� �ε��� �׽�Ʈ��*/
					System.out.println("");
					System.out.println("----------���� ����-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.map_size[j][i]+"");
							}
						}
					System.out.println("");
					System.out.println("----------���� ����-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.previous_map_size[j][i]+"");
							}
						}
					System.out.println("");
					}
					
				
				
			};
			TimerTask boomover = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int i = 0; i<boomballoonXList.size(); i++) {
						BoomJudge.map_size[boomballoonYList.get(i)][boomballoonXList.get(i)] = 0;
						BoomJudge.previous_map_size[boomballoonYList.get(i)][boomballoonXList.get(i)] = 0;
						if(boomballoonYList.get(i)+bombSize<=12) {
							BoomJudge.map_size[boomballoonYList.get(i)+bombSize][boomballoonXList.get(i)] = 0;
							BoomJudge.previous_map_size[boomballoonYList.get(i)+bombSize][boomballoonXList.get(i)] = 0;
						}
						if(boomballoonYList.get(i)-bombSize>=0) {
							BoomJudge.map_size[boomballoonYList.get(i)-bombSize][boomballoonXList.get(i)] = 0;
							BoomJudge.previous_map_size[boomballoonYList.get(i)-bombSize][boomballoonXList.get(i)] = 0;
						}
						if(boomballoonXList.get(i)+bombSize<=12) {
							BoomJudge.map_size[boomballoonYList.get(i)][boomballoonXList.get(i)+bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYList.get(i)][boomballoonXList.get(i)+bombSize] = 0;
						}
						if(boomballoonXList.get(i)-bombSize>=0) {
							BoomJudge.map_size[boomballoonYList.get(i)][boomballoonXList.get(i)-bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYList.get(i)][boomballoonXList.get(i)-bombSize] = 0;
						}
					}
					boomballoonXList.remove(0);
					boomballoonYList.remove(0);
				}
				
			};
			boom.schedule(boomtask, 5000);
			boom.schedule(boomover, 6000);
		}
	}
}