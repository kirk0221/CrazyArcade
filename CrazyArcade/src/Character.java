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
}
