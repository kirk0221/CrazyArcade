import java.awt.Image;

public abstract class Character {
	private Screen screen;
	private int X;
	private int Y;
	private int step;
	
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
}
