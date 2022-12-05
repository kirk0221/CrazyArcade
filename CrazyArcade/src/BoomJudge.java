import javax.swing.JOptionPane;

public class BoomJudge {
	public static int[][] map_size = new int[13][13];//맵 사이즈 13*13
	/*물풍선에서 조작하기 위해 static으로 변경*/
	
	public static int[][] previous_map_size = new int[13][13];//맵 사이즈 13*13
	
	public BoomJudge() {
		if (Screen.map_selection == 0) {
			this.Map_Cookie_batch();
		}else if(Screen.map_selection == 1) {
			this.Map_Patriots_batch();
		}
	}
	
	public void Map_Cookie_batch() {//쿠키맵 기본 맵 구성
		this.map_size = new int[][] {{0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0}};
		this.previous_map_size = new int[][] {{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0}};
	}
	public void Map_Patriots_batch() {//해적맵 기본 맵 구성
		this.map_size = new int[][] {{0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0},
									 {0,0,0,0,0,0,0,0,0,0,0,0,0}};
		this.previous_map_size = new int[][] {{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0}};
	}
	public static void die() {
		for(int i=0;i<13;i++) {
			for(int j=0;j<13;j++) {
				if((previous_map_size[i][j] == 1) && (map_size[i][j] == 4)) {
					//JOptionPane.showMessageDialog(null, "플레이어 사망");
					//System.exit(0);
					System.out.print("사망");
				} /*물풍선이 터지기 직전의 플레이어의 좌표를 가지는 맵 previous_map_size와 
				물풍선이 터질때의 인덱스 정보를 가지는 map_size를 비교하여 동일한 위치에 1과 4 혹은
				2와 4가 존재하면 사망처리*/
				if((previous_map_size[i][j] == 2) && (map_size[i][j] == 4)) {
					//JOptionPane.showMessageDialog(null, "플레이어 사망");
					//System.exit(0);
					System.out.print("사망");
				}
			}
		}
		
	}
	
}