import javax.swing.JOptionPane;

public class BoomJudge {
	public static int[][] map_size = new int[13][13];//맵 사이즈 13*13
	/*물풍선에서 조작하기 위해 static으로 변경*/
	public static int character1_bombsizeup;
	public static int character2_bombsizeup;
	
	public static int[][] previous_map_size = new int[13][13];//맵 사이즈 13*13
	
	public BoomJudge(int map) {
		if (map == 0) {
			this.Map_Cookie_batch();
		}else if(map == 1) {
			this.Map_Patriots_batch();
		}
		character1_bombsizeup = 0;
		character2_bombsizeup = 0;
	}
	/*숫자별 인덱스 의미
	 * 0 : 아무것도 없는 그냥 길
	 * 1 : player1
	 * 2 : player2
	 * 3 : 터지지 않은 물풍선
	 * 4 : 터진 물풍선
	 * 5, 6 : 벽(인덱스별로 벽 색이 다름)
	 * 7, 8 : 아이템이 있는 벽(인덱스별로 벽 색이 다름)
	 * 9 : 물풍선 최대개수 추가 아이템
	 * 이후 인덱스 추가시 추가바람*/
	public void Map_Cookie_batch() {//쿠키맵 기본 맵 구성
		this.map_size = new int[][] {{ 1, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0 },
									 { 0, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 0 },
									 { 6, 6, 6, 0, 5, 5, 0, 5, 5, 0, 6, 6, 6 },
									 { 6, 6, 0, 0, 0, 5, 5, 5, 0, 0, 0, 6, 6 },
									 { 6, 6, 6, 0, 5, 5, 5, 5, 5, 0, 6, 6, 6 },
									 { 6, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 6 },
									 { 6, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 6 },
									 { 6, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 6 },
									 { 6, 6, 6, 0, 5, 5, 5, 5, 5, 0, 6, 6, 6 },
									 { 6, 6, 0, 0, 0, 5, 5, 5, 0, 0, 0, 6, 6 },
									 { 6, 6, 6, 0, 5, 5, 0, 5, 5, 0, 6, 6, 6 },
									 { 0, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 0 },
									 { 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 2 }};
		this.previous_map_size = new int[][] {{ 1, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0 },
											  { 0, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 0 },
											  { 6, 6, 6, 0, 5, 5, 0, 5, 5, 0, 6, 6, 6 },
											  { 6, 6, 0, 0, 0, 5, 5, 5, 0, 0, 0, 6, 6 },
											  { 6, 6, 6, 0, 5, 5, 5, 5, 5, 0, 6, 6, 6 },
											  { 6, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 6 },
											  { 6, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 6 },
											  { 6, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 6 },
											  { 6, 6, 6, 0, 5, 5, 5, 5, 5, 0, 6, 6, 6 },
											  { 6, 6, 0, 0, 0, 5, 5, 5, 0, 0, 0, 6, 6 },
											  { 6, 6, 6, 0, 5, 5, 0, 5, 5, 0, 6, 6, 6 },
											  { 0, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 0 },
											  { 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 2 }};
	}
	public void Map_Patriots_batch() {//해적맵 기본 맵 구성
		this.map_size = new int[][] {{ 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6 },
									 { 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 },
									 { 5, 0, 8, 0, 0, 5, 0, 5, 0, 0, 6, 0, 5 },
									 { 5, 0, 0, 6, 0, 0, 0, 0, 0, 6, 0, 0, 5 },
									 { 5, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 5 },
									 { 5, 0, 0, 0, 0, 6, 0, 6, 0, 0, 0, 0, 5 },
									 { 5, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 5 },
									 { 5, 0, 0, 0, 0, 6, 0, 6, 0, 0, 0, 0, 5 },
									 { 5, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 5 },
									 { 5, 0, 0, 6, 0, 0, 0, 0, 0, 6, 0, 0, 5 },
									 { 5, 0, 6, 0, 0, 5, 0, 5, 0, 0, 6, 0, 5 },
									 { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5 },
									 { 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6 }};
		this.previous_map_size = new int[][] {{ 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6 },
											  { 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 },
											  { 5, 0, 8, 0, 0, 5, 0, 5, 0, 0, 6, 0, 5 },
											  { 5, 0, 0, 6, 0, 0, 0, 0, 0, 6, 0, 0, 5 },
											  { 5, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 5 },
											  { 5, 0, 0, 0, 0, 6, 0, 6, 0, 0, 0, 0, 5 },
											  { 5, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 5 },
											  { 5, 0, 0, 0, 0, 6, 0, 6, 0, 0, 0, 0, 5 },
											  { 5, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 5 },
											  { 5, 0, 0, 6, 0, 0, 0, 0, 0, 6, 0, 0, 5 },
											  { 5, 0, 6, 0, 0, 5, 0, 5, 0, 0, 6, 0, 5 },
											  { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5 },
											  { 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6 }};
	}
	public static void die() {
		for(int map_y=0;map_y<13;map_y++) {
			for(int map_x=0;map_x<13;map_x++) {
				if((previous_map_size[map_y][map_x] == 1) && (map_size[map_y][map_x] == 4)) {
					JOptionPane.showMessageDialog(null, "플레이어 1 사망");
					System.exit(0);
				} /*물풍선이 터지기 직전의 플레이어의 좌표를 가지는 맵 previous_map_size와 
				물풍선이 터질때의 인덱스 정보를 가지는 map_size를 비교하여 동일한 위치에 1과 4 혹은
				2와 4가 존재하면 사망처리*/
				if((previous_map_size[map_y][map_x] == 2) && (map_size[map_y][map_x] == 4)) {
					JOptionPane.showMessageDialog(null, "플레이어 2 사망");
					System.exit(0);
				}
			}
		}
		
	}
	
	public static void item_check() { //추가 및 체크 함수
		for(int map_y=0;map_y<13;map_y++) {
			for(int map_x=0;map_x<13;map_x++) {
				if((previous_map_size[map_y][map_x] == 9) && (map_size[map_y][map_x] == 1)) {//아이템 득 경우
					previous_map_size[map_y][map_x] = 1;
					map_size[map_y][map_x] = 1;
					character1_bombsizeup+=1;
				}
				if((previous_map_size[map_y][map_x] == 9) && (map_size[map_y][map_x] == 2)) {//아이템 득 경우
					previous_map_size[map_y][map_x] = 2;
					map_size[map_y][map_x] = 2;
					character2_bombsizeup+=1;
				}
			}
		}
	}
	
}