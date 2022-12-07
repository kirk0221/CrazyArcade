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
	/* 여러 개의 물풍선을 놓기 위해 링크드 리스트 사용*/
	
	Queue<Integer> boomballoonXqueue = new LinkedList<>();
	Queue<Integer> boomballoonYqueue = new LinkedList<>();
	LinkedList<Integer> boomballoonXList = new LinkedList<>();
	LinkedList<Integer> boomballoonYList = new LinkedList<>();
	
	public WaterBalloon(int playertype){
		this.playertype = playertype;
		this.waterballoonmax = 3; //물풍선 최대 개수 3개
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
	
	public void makeWaterBalloon(int x, int y, int bombSize) {
		this.bombSize = bombSize;
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
					
					System.out.println();
					System.out.println(balloonXqueue);
					System.out.println(balloonXList);
					System.out.println();
					
//			    	balloonXqueue.remove();
//			    	balloonYqueue.remove();
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
					BoomJudge.die();
					
					/*맵 인덱스 테스트용*/
					System.out.println("");
					System.out.println("----------지금 상태-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.map_size[i][j]+"");
							}
						}
					System.out.println("");
					System.out.println("----------이전 상태-----------");
					for(int i=0;i<13;i++) {
						System.out.println("");
						for(int j=0;j<13;j++) {
								System.out.print(BoomJudge.previous_map_size[i][j]+"");
							}
						}
					System.out.println("");
					
					System.out.println();
					System.out.println(balloonXqueue);
					System.out.println(balloonXList);
					System.out.println(boomballoonXqueue);
					System.out.println(boomballoonXList);
					System.out.println();
			    }
			};
			
			TimerTask boomover = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
						BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
						BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()] = 0;
						if(boomballoonYqueue.peek()+bombSize<=12) {
							BoomJudge.map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()+bombSize][boomballoonXqueue.peek()] = 0;
						}
						if(boomballoonYqueue.peek()-bombSize>=0) {
							BoomJudge.map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()-bombSize][boomballoonXqueue.peek()] = 0;
						}
						if(boomballoonXqueue.peek()+bombSize<=12) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()+bombSize] = 0;
						}
						if(boomballoonXqueue.peek()-bombSize>=0) {
							BoomJudge.map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 0;
							BoomJudge.previous_map_size[boomballoonYqueue.peek()][boomballoonXqueue.peek()-bombSize] = 0;
						}
					boomballoonXqueue.remove();
					boomballoonYqueue.remove();
					boomballoonXList.remove(0);
					boomballoonYList.remove(0);
					System.out.println();
					System.out.println(boomballoonXqueue);
					System.out.println(boomballoonXList);
					System.out.println();
				}
			};
			Timer boom = new Timer();
			boom.schedule(task, 5000);
			boom.schedule(boomover, 6000);
		}
	}
}