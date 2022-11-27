import java.awt.Image;
import java.awt.event.KeyEvent;

public abstract class Character {
	private Screen screen;
	private static int X;
	private static int Y;
	private static int step;
	public int die;
	
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
	public void die() {
		die = 1;
	}
	public abstract void keyPressed(KeyEvent e);
	protected abstract int getballoonY(int i);
	protected abstract int getballoonX(int i);
	protected abstract int getboomballoonY(int i);
	protected abstract int getboomballoonX(int i);
	protected abstract int getballonListsize();
	protected abstract int getboomballonListsize();
	protected abstract int getbombSize();
}