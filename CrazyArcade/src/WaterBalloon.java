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
	
	static int a=0; /*물풍선 벽 하나만 깨지도록 하기위해 사용*/
	static int b=0;
	static int c=0;
	static int d=0;
	
	
	public int[] mapXlocationlist;
	public int[] mapYlocationlist;
	
	public int balloonXindex;
	public int balloonYindex;
	
	public int balloonplus; /* 물풍선 확장 아이템에서 사용*/
	
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
	/* 여러 개의 물풍선을 놓기 위해 링크드 리스트 사용*/
	
	Queue<Integer> boomballoonXqueue = new LinkedList<>();
	Queue<Integer> boomballoonYqueue = new LinkedList<>();
	LinkedList<Integer> boomballoonXList = new LinkedList<>();
	LinkedList<Integer> boomballoonYList = new LinkedList<>();
	
	private Make_Item_Index item;
	
	public WaterBalloon(int playertype){
		this.playertype = playertype;
		this.waterballoonmax = 1; //물풍선 기본 개수 1개
		this.waterballoonmax_plus = 0; //추가 물풍선 개수 0개
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
	
	public Image getImg() {// 물풍선 이미지를 스크린에 주기위한 함수
		return this.balloonImg;
	}
	
	public Image getcenterImg() {// 터진 물풍선 가운데 이미지를 스크린에 주기위한 함수
		return this.centerImg;
	}
	
	public Image getleftImg() {// 터진 물풍선 왼쪽 이미지를 스크린에 주기위한 함수
		return this.leftImg;
	}
	
	public Image getrightImg() {// 터진 물풍선 오른쪽 이미지를 스크린에 주기위한 함수
		return this.rightImg;
	}
	
	public Image getupImg() {// 터진 물풍선 위 이미지를 스크린에 주기위한 함수
		return this.upImg;
	}
	public Image getdownImg() {// 터진 물풍선 아래 이미지를 스크린에 주기위한 함수
		return this.downImg;
	}
	
	public int getX(int i) { /*물풍선의 x 값을 스크린에 전달하기 위한 함수, 
	i는 인덱스로 스크린에서 링크드 리스트의 어떤 값을 전달할지 지정받음.*/
		return balloonXList.get(i);
	}
	
	public int getY(int i) {
		return balloonYList.get(i);
	}
	
	public int getboomX(int i) { /*터진 물풍선의 x 값을 스크린에 전달하기 위한 함수, 
	i는 인덱스로 스크린에서 링크드 리스트의 어떤 값을 전달할지 지정받음.*/
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
			System.out.println("player1 물풍선 횟수 초과");
		}else if(playertype == 2 && waterballoonmax==0) {
			System.out.println("player2 물풍선 횟수 초과");
		}else {
			if (playertype == 1) {
				waterballoonmax -= 1;//물풍선이 설치되면 물풍선 최대개수를 1 줄여줌
				this.balloonplus = BoomJudge.character1_stream;
			}else if (playertype == 2) {
				waterballoonmax -= 1;//물풍선이 설치되면 물풍선 최대개수를 1 줄여줌
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
			balloonXqueue.add(balloonXindex); /*물풍선 x 좌표 인덱스를 저장하는 큐*/
			balloonYqueue.add(balloonYindex); /*물풍선 y 좌표 인덱스를 저장하는 큐*/
			balloonXList.add(balloonXindex); /*물풍선 x 좌표 인덱스를 저장하는 큐*/
			balloonYList.add(balloonYindex); /*물풍선 y 좌표 인덱스를 저장하는 링크드 리스트*/
			BoomJudge.map_size[balloonYindex][balloonXindex] = 3; /*3로 바꾸어 물풍선 놓기*/
			BoomJudge.die();
			/*내부적으로 이용하기 위해 3로 바꾸어줌*/
			TimerTask task = new TimerTask() {
				@Override
			    public void run() {
					int remember_x = balloonXqueue.peek();
					int remember_y = balloonYqueue.peek();
					
			    	balloonXList.remove(0);
			    	balloonYList.remove(0);
					boomballoonXqueue.add(balloonXqueue.poll()); /*물풍선 x 좌표 인덱스를 저장하는 큐*/
					boomballoonYqueue.add(balloonYqueue.poll()); /*물풍선 y 좌표 인덱스를 저장하는 큐*/
					boomballoonXList.add(remember_x); /*물풍선 x 좌표 인덱스를 저장하는 링크드 리스트*/
					boomballoonYList.add(remember_y); /*물풍선 y 좌표 인덱스를 저장하는 링크드 리스트*/
					
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
								System.out.println("벽");
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
								System.out.println("벽");
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
								System.out.println("벽");
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
								System.out.println("벽");
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
					//7번인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 7, 9);
					//8번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 8, 9);
					//10번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 10, 12);
					//11번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 11, 12);
					//13번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 13, 15);
					//14번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 14, 15);
					//16번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 16, 18);
					//17번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 17, 18);
					//19번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 19, 21);
					//20번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 20, 21);
					//22번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 22, 24);
					//23번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 23, 24);
					//25번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 25, 27);
					//26번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, balloonplus, 26, 27);
					
					//기존 인덱스
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