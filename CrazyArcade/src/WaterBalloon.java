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
	
	static int a=0; /*��ǳ�� �� �ϳ��� �������� �ϱ����� ���*/
	static int b=0;
	static int c=0;
	static int d=0;
	
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	
	public int balloonXindex;
	public int balloonYindex;
	
	public int balloonplus; /* ��ǳ�� Ȯ�� �����ۿ��� ���*/
	
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
	
	private Make_Item_Index item;
	
	public WaterBalloon(int playertype){
		this.playertype = playertype;
		this.waterballoonmax = 1; //��ǳ�� �⺻ ���� 1��
		this.waterballoonmax_plus = 0; //�߰� ��ǳ�� ���� 0��
		this.mapXlocationlist = new int[13];
		this.mapYlocationlist = new int[13];
		this.item = new Make_Item_Index();
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
			this.waterballoonmax = 1 + waterballoonmax_plus;
		}
		this.waterballoonmax_plus = waterballoonmax_plus;
		if(playertype == 1 && waterballoonmax==0) {
			System.out.println("player1 ��ǳ�� Ƚ�� �ʰ�");
		}else if(playertype == 2 && waterballoonmax==0) {
			System.out.println("player2 ��ǳ�� Ƚ�� �ʰ�");
		}else {
			if (playertype == 1) {
				waterballoonmax -= 1;//��ǳ���� ��ġ�Ǹ� ��ǳ�� �ִ밳���� 1 �ٿ���
				this.balloonplus = BoomJudge.character1_stream;
			}else if (playertype == 2) {
				waterballoonmax -= 1;//��ǳ���� ��ġ�Ǹ� ��ǳ�� �ִ밳���� 1 �ٿ���
				this.balloonplus = BoomJudge.character2_stream;
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
					for(int plusbombsize = 0; plusbombsize<balloonplus+1; plusbombsize++) {
						if(boomballoonXqueue.peek()+bombSize+plusbombsize<=12) {
							if(a==0) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 4;
									if((BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 5 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==7 || 
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==10 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==13 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==16 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==19 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==22 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==25 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 26 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==6 || 
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==8 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==11 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==14 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==17 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==20 ||
											BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] ==23)) {
												a += 1;
								}
							}else {
								System.out.println("��");
							}
						}
						
						
						if(boomballoonXqueue.peek()-bombSize-plusbombsize>=0) {
							if(b==0) {
								BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 4;
								if(BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 5 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==7 || 
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==10 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==13 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==16 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==19 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==22 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==25 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 26 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==6 || 
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==8 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==11 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==14 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==17 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==20 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] ==23) {
											
											b += 1;
										}
							}else {
								System.out.println("��");
							}
						}
						
						
						if(boomballoonYqueue.peek()+bombSize+plusbombsize<=12) {
							if(c==0) {
								BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 4;
								if(BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 5 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==7 || 
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==10 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==13 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==16 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==19 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==22 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==25 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 26 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==6 || 
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==8 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==11 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==14 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==17 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==20 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] ==23) {
											c += 1;
										}
							}else {
								System.out.println("��");
							}
						}
						
						
						if(boomballoonYqueue.peek()-bombSize-plusbombsize>=0) {
							if(d==0) {
								BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 4;
								if(BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 5 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==7 || 
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==10 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==13 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==16 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==19 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==22 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==25 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==26 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==6 || 
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==8 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==11 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==14 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==17 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==20 ||
										BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] ==23) {
											d += 1;
										}
							}else {
								System.out.println("��");
							}
			
						}
					
					
					}
					
					waterballoonmax +=1;
			    }
			};
			
            TimerTask boomover = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//7���ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 7, 9);
					//8�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 8, 9);
					//10�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 10, 12);
					//11�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 11, 12);
					//13�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 13, 15);
					//14�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 14, 15);
					//16�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 16, 18);
					//17�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 17, 18);
					//19�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 19, 21);
					//20�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 20, 21);
					//22�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 22, 24);
					//23�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 23, 24);
					//25�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 25, 27);
					//26�� �ε���
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 26, 27);
					
					//���� �ε���
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					for(int plusbombsize = 0; plusbombsize<balloonplus+1; plusbombsize++) {
						if(c==0) {
							if(boomballoonYqueue.peek()+bombSize+plusbombsize<=12) {
								if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 9){
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 9;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 9;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 12) {
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 12;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 12;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 15) {
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 15;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 15;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 18) {
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 18;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 18;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 21) {
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 21;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 21;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 24) {
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 24;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 24;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] == 27) {
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 27;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 27;
								}else{
									BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 0;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+plusbombsize][boomballoonXqueue.peek()] = 0;
								}
							}
						}
						
						if(d==0) {
							if(boomballoonYqueue.peek()-bombSize-plusbombsize>=0) {
								if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 9) {
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 9;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 9;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 12) {
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 12;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 12;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 15) {
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 15;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 15;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 18) {
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 18;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 18;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 21) {
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 21;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 21;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 24) {
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 24;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 24;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] == 27) {
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 27;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 27;
								}else{
									BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 0;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-plusbombsize][boomballoonXqueue.peek()] = 0;
								}
							}
						}
						
						if(a==0) {
							if(boomballoonXqueue.peek()+bombSize+plusbombsize<=12) {
								if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 9) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 9;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 9;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 12) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 12;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 12;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 15) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 15;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 15;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 18) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 18;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 18;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 21) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 21;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 21;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 24) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 24;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 24;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] == 27) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 27;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 27;
								}else{
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 0;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+plusbombsize] = 0;
								}
							}
						}
						
						if(b==0) {
							if(boomballoonXqueue.peek()-bombSize-plusbombsize>=0) {
								if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 9) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 9;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 9;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 12) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 12;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 12;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 15) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 15;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 15;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 18) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 18;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 18;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 21) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 21;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 21;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 24) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 24;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 24;
								}else if(BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] == 27) {
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 27;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 27;
								}else{
									BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 0;
									BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-plusbombsize] = 0;
								}
							}
						}
					}
					boomballoonXqueue.remove();
					boomballoonYqueue.remove();
					boomballoonXList.remove(0);
					boomballoonYList.remove(0);
					a=0;
					b=0;
					c=0;
					d=0;
				}
			};
			
			
			
			Timer boom = new Timer();
			
			boom.schedule(task, 5000);
			boom.schedule(boomover, 6000);
			
		}
	}
}