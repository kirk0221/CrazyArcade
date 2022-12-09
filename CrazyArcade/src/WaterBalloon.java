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
		this.waterballoonmax = 3; //물풍선 최대 개수 3개
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
			this.waterballoonmax += 1;
		}
		this.waterballoonmax_plus = waterballoonmax_plus;
		if(playertype == 1 && waterballoonmax==0) {
			System.out.println("player1 물풍선 횟수 초과");
		}else if(playertype == 2 && waterballoonmax==0) {
			System.out.println("player2 물풍선 횟수 초과");
		}else {
			if (playertype == 1) {
				waterballoonmax -= 1;//물풍선이 설치되면 물풍선 최대개수를 1 줄여줌
			}else if (playertype == 2) {
				waterballoonmax -= 1;//물풍선이 설치되면 물풍선 최대개수를 1 줄여줌
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
			    }
			};
			TimerTask task2 = new TimerTask() {
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
					
					
					
					if(boomballoonXqueue.peek()+bombSize<=12) {/*기존의 물풍선처럼 4를 놓고*/
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 4;
						if(boomballoonXqueue.peek()+bombSize+1<=12) { /*기존 물풍선보다 한칸 더가서 맵 안인지 체크하고 놓음*/
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 4;
						}
					}
					
					if(boomballoonXqueue.peek()-bombSize>=0) {
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 4;
						if(boomballoonXqueue.peek()-bombSize-1>=0) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 4;
						}
					}
					
					if(boomballoonYqueue.peek()+bombSize<=12) {
						BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 4;
						if(boomballoonYqueue.peek()+bombSize+1<=12) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 4;
						}
					}
					if(boomballoonYqueue.peek()-bombSize>=0) {
						BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 4;
						if(boomballoonYqueue.peek()-bombSize-1>=0) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 4;
						}
					}
					
					
					
					waterballoonmax +=1;
//					/*맵 인덱스 테스트용*/
//					System.out.println("");
//					System.out.println("----------지금 상태-----------");
//					for(int i=0;i<13;i++) {
//						System.out.println("");
//						for(int j=0;j<13;j++) {
//								System.out.print(BoomJudge.map_size[i][j]+" ");
//							}
//						}
//					System.out.println("");
//					System.out.println("----------이전 상태-----------");
//					for(int i=0;i<13;i++) {
//						System.out.println("");
//						for(int j=0;j<13;j++) {
//								System.out.print(BoomJudge.previous_map_size[i][j]+" ");
//							}
//						}
//					System.out.println("");
//					System.out.println(waterballoonmax);
//					System.out.println("");
			    }
			};
			
            TimerTask boomover = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//7번인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 7, 9);
					//8번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 8, 9);
					//10번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 10, 12);
					//11번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 11, 12);
					
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 13, 15);
					
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 14, 15);
					
					/*맵 인덱스 테스트용*/
					System.out.println("");
					System.out.println("----------지금 상태-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.map_size[i][j]+" ");
							}
						}
					System.out.println("");
					System.out.println("----------이전 상태-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.previous_map_size[i][j]+" ");
							}
						}
					System.out.println("");
					System.out.println(waterballoonmax);
					System.out.println("");
					
					//기존 인덱스
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					if(boomballoonYqueue.peek()+bombSize<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
						}
						else if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 15;
						}
						else{
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
						}
					}
					
		
					
					
					if(boomballoonYqueue.peek()-bombSize>=0) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
						}
					}
					
					

					
					if(boomballoonXqueue.peek()+bombSize<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
						}
					}
					
	
					
					if(boomballoonXqueue.peek()-bombSize>=0) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 15;
						}else{
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
			TimerTask boomover2 = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//7번인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 7, 9);
					//8번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 8, 9);
					//10번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 10, 12);
					//11번 인덱스
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 11, 12);
					
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 13, 15);
					
					item.make_item_index(boomballoonYqueue.peek(), boomballoonXqueue.peek(), bombSize, 14, 15);
					
					/*맵 인덱스 테스트용*/
					System.out.println("");
					System.out.println("----------지금 상태-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.map_size[i][j]+" ");
							}
						}
					System.out.println("");
					System.out.println("----------이전 상태-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.previous_map_size[i][j]+" ");
							}
						}
					System.out.println("");
					System.out.println(waterballoonmax);
					System.out.println("");
					
					//기존 인덱스
					BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
					if(boomballoonYqueue.peek()+bombSize<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 9;
						}
						else if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
						}
					}
					
					
					if(boomballoonYqueue.peek()+bombSize+1<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 9;
						}
						else if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize+1][boomballoonXqueue.peek()] = 0;
						}
					}
					
					
					if(boomballoonYqueue.peek()-bombSize>=0) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 0;
						}
					}
					
					
					if(boomballoonYqueue.peek()-bombSize-1>=0) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize-1][boomballoonXqueue.peek()] = 0;
						}
					}
					
					
					if(boomballoonXqueue.peek()+bombSize<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
						}
					}
					
					if(boomballoonXqueue.peek()+bombSize+1<=12) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize+1] = 0;
						}
					}
					
					if(boomballoonXqueue.peek()-bombSize>=0) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 0;
						}
					}
					
					if(boomballoonXqueue.peek()-bombSize-1>=0) {
						if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] == 9) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] == 9)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 9;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 9;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] == 12) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] == 12)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 12;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 12;
						}else if((BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] == 15) || (BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] == 15)) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 15;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 15;
						}else{
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize-1] = 0;
						}
					}
					boomballoonXqueue.remove();
					boomballoonYqueue.remove();
					boomballoonXList.remove(0);
					boomballoonYList.remove(0);
				}
			};
			
			
			TimerTask setzero = new TimerTask(){
				@Override
			    public void run() {
					balloonplus = 0;
					BoomJudge.character1_stream=0;
					BoomJudge.character2_stream=0;
					
				}
			};
			
			Timer boom = new Timer();
			
			/*stream을 체크하여 1 ~ 3 사이이면 task2(확장된 물풍선)를 수행함*/
			if((BoomJudge.character1_stream>0 && BoomJudge.character1_stream<4) || (BoomJudge.character2_stream>0 && BoomJudge.character2_stream<4)) {
				boom.schedule(task2, 5000);
				boom.schedule(boomover2, 6000);
				/*stream이 3이면 최대 이용가능 횟수 3에 도달한 것이므로 0으로 초기화*/
				if(BoomJudge.character1_stream == 3 || BoomJudge.character2_stream == 3) {
					boom.schedule(setzero, 6000);
				}else {
					/*stream이 1~3사이이면서 3이 아니면 1증가*/
					BoomJudge.character1_stream++;
					BoomJudge.character2_stream++;
				}
			}else { /*stream이 0이면 그냥 물풍선인 task 수행*/
				boom.schedule(task, 5000);
				boom.schedule(boomover, 6000);
			}
		}
	}
}