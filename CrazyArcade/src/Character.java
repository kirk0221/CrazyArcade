import java.awt.Image;
import java.awt.event.KeyEvent;

public abstract class Character {
	private Screen screen;
	private int X;
	private int Y;
	private int playerIndex_x;
	private int playerIndex_y;
	private static int step;
	
	public Character(Screen screen) {//스크린 불러오기
		this.screen = screen;
	}
	public Screen getScreen() {//스크린 가져오는 함수
		return this.screen;
	}
	public abstract Image getImg();//이미지 가져오는 함수
	public abstract Image getballoonImg(); //물풍선 이미지 가져오기
	public abstract Image getcenterImg(); //물풍선 이미지 가져오기
	public abstract Image getleftImg(); //물풍선 이미지 가져오기
	public abstract Image getrightImg(); //물풍선 이미지 가져오기
	public abstract Image getupImg(); //물풍선 이미지 가져오기
	public abstract Image getdownImg(); //물풍선 이미지 가져오기
	
	public int getX() {// X값 가져오는 함수
		return this.X;
	}
	
	public int getY() {// Y값 가져오는 함수
		return this.Y;
	}
	
	public void up() {//위로 가기
		Y-=step;
	}
	public void down() {//아래로 가기
		Y+=step;
	}
	public void left() {//왼쪽으로 가기
		X-=step;
	}
	public void right() {//오른쪽으로 가기
		X+=step;
	}
	public abstract void keyPressed(KeyEvent e);
	protected abstract int getballoonY(int i);
	protected abstract int getballoonX(int i);
	protected abstract int getboomballoonY(int i);
	protected abstract int getboomballoonX(int i);
	protected abstract int getballonListsize();
	protected abstract int getboomballonListsize();
	protected abstract int getbombSize();
	protected abstract void getPlayerIndex_x(int x);
	protected abstract void getPlayerIndex_y(int y);
}