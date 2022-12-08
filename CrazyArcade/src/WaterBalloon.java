import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class WaterBalloon {
	int X;
	int Y;
	int bombSize;
	int playertype;
	int waterballoonmax;
	int waterballoonmax_plus;
	
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
	
	Queue<Integer> balloonXqueue = new LinkedList<>();
	Queue<Integer> balloonYqueue = new LinkedList<>();
	LinkedList<Integer> balloonXList = new LinkedList<>();
	LinkedList<Integer> balloonYList = new LinkedList<>();
	/* ���� ���� ��ǳ���� ���� ���� ��ũ�� ����Ʈ ���*/
	
	Queue<Integer> boomballoonXqueue = new LinkedList<>();
	Queue<Integer> boomballoonYqueue = new LinkedList<>();
	LinkedList<Integer> boomballoonXList = new LinkedList<>();
	LinkedList<Integer> boomballoonYList = new LinkedList<>();
	
	public WaterBalloon(int playertype){
		this.playertype = playertype;
		this.waterballoonmax = 3; //��ǳ�� �ִ� ���� 3��
		this.waterballoonmax_plus = 0; //�߰� ��ǳ�� ���� 0��
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		int locationnum = 0;
		for(int i=0; i<13;i++) {
				this.mapXlocationlist[i] = locationnum;
				this.mapYlocationlist[i] = locationnum;
				locationnum += 60.45;
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
	
	public void makeWaterBalloon(int x, int y, int bombSize, int waterballoonmax_plus) {
		this.bombSize = bombSize;
		if (this.waterballoonmax_plus != waterballoonmax_plus) {
			this.waterballoonmax += 1;
		}
		this.waterballoonmax_plus = waterballoonmax_plus;
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
			balloonXqueue.add(balloonXindex); /*��ǳ�� x ��ǥ �ε����� �����ϴ� ť*/
			balloonYqueue.add(balloonYindex); /*��ǳ�� y ��ǥ �ε����� �����ϴ� ť*/
			balloonXList.add(balloonXindex); /*��ǳ�� x ��ǥ �ε����� �����ϴ� ť*/
			balloonYList.add(balloonYindex); /*��ǳ�� y ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
			BoomJudge.map_size[balloonYindex][balloonXindex] = 3; /*3�� �ٲپ� ��ǳ�� ����*/
			BoomJudge.die();
			/*���������� �̿��ϱ� ���� 3�� �ٲپ���*/
			
			TimerTask task = new TimerTask() {
				@Override
			    public void run() {
					int remember_x = balloonXqueue.peek();
					int remember_y = balloonYqueue.peek();
					
			    	balloonXList.remove(0);
			    	balloonYList.remove(0);
					boomballoonXqueue.add(balloonXqueue.poll()); /*��ǳ�� x ��ǥ �ε����� �����ϴ� ť*/
					boomballoonYqueue.add(balloonYqueue.poll()); /*��ǳ�� y ��ǥ �ε����� �����ϴ� ť*/
					boomballoonXList.add(remember_x); /*��ǳ�� x ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
					boomballoonYList.add(remember_y); /*��ǳ�� y ��ǥ �ε����� �����ϴ� ��ũ�� ����Ʈ*/
					
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 4;
					if(boomballoonXqueue.peek()+bombSize<=12) {
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 4;
					}
					if(boomballoonXqueue.peek()-bombSize>=0) {
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 4;
					}
					if(boomballoonYqueue.peek()+bombSize<=12) {
						BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 4;
					}
					if(boomballoonYqueue.peek()-bombSize>=0) {
						BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 4;
					}
					waterballoonmax +=1;
					/*�� �ε��� �׽�Ʈ��*/
					System.out.println("");
					System.out.println("----------���� ����-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.map_size[i][j]+"");
							}
						}
					System.out.println("");
					System.out.println("----------���� ����-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.previous_map_size[i][j]+"");
							}
						}
					System.out.println("");
					System.out.println(waterballoonmax);
					System.out.println("");
			    }
			};
			
			TimerTask boomover = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//7���ε���
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] == 7) && (BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] == 4)) {//��ǳ�� ���� �÷��ִ� ������
						BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 9;
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 7) && (BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 4)) {//��ǳ�� ���� �÷��ִ� ������
						BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
						BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 7) && (BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 4)) {//��ǳ�� ���� �÷��ִ� ������
						BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
						BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] == 7) && (BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 4)) {//��ǳ�� ���� �÷��ִ� ������
						BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] == 7) && (BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 4)) {//��ǳ�� ���� �÷��ִ� ������
						BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
					}
					//8�� �ε���
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 8) && (BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 4)) {//��ǳ�� ���� �÷��ִ� ������
					BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
					BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 8) && (BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 4)) {//��ǳ�� ���� �÷��ִ� ������
					BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
					BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 8) && (BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 4)) {//��ǳ�� ���� �÷��ִ� ������
					BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 8) && (BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 4)) {//��ǳ�� ���� �÷��ִ� ������
					BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
					}
					if((BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] == 8) && (BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] == 4)) {//��ǳ�� ���� �÷��ִ� ������
					BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 9;
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 9;
					}
					//���� �ε���
					if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] != 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] != 9)) {
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
						BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					}
					if(boomballoonYqueue.peek()+bombSize<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] != 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] != 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
						}
					}
					if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] != 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] != 9)) {
						if(boomballoonYqueue.peek()-bombSize>=0) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 0;
						}
					}
					if(boomballoonXqueue.peek()+bombSize<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] != 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] != 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
						}
					}
					if(boomballoonXqueue.peek()-bombSize>=0) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] != 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] != 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 0;
						}
					}
					boomballoonXqueue.remove();
					boomballoonYqueue.remove();
					boomballoonXList.remove(0);
					boomballoonYList.remove(0);
				}
			};
			Timer boom = new Timer();
			boom.schedule(task, 5000);
			boom.schedule(boomover, 6000);
		}
	}
}