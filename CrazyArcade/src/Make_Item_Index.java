
public class Make_Item_Index {
	
	public Make_Item_Index() {
		
	}
	
	public void make_item_index(int y, int x, int bombSize, int box_index, int item_index) {
		if((BoomJudge.previous_map_size[y][x] == box_index) && (BoomJudge.map_size[y][x] == 4)) {//물풍선 놓인 자리
		BoomJudge.previous_map_size[y][x] =	item_index;
		BoomJudge.map_size[y][x] = item_index;
		}
		if(y+bombSize<=12) {
			if((BoomJudge.previous_map_size[y+bombSize][x] == box_index) && (BoomJudge.map_size[y-bombSize][x] == 4)) {//물풍선 놓인 자리 아래
			BoomJudge.previous_map_size[y+bombSize][x] = item_index;
			BoomJudge.map_size[y+bombSize][x] = item_index;
			}
		}
		if(y-bombSize>=0) {
			if((BoomJudge.previous_map_size[y-bombSize][x] == box_index) && (BoomJudge.map_size[y-bombSize][x] == 4)) {//물풍선 놓인 자리 위
			BoomJudge.previous_map_size[y-bombSize][x] = item_index;
			BoomJudge.map_size[y-bombSize][x] = item_index;
			}
		}
		if(x+bombSize<=12) {
			if((BoomJudge.previous_map_size[y][x+bombSize] == box_index) && (BoomJudge.map_size[y][x+bombSize] == 4)) {//물풍선 놓인 자리 오른쪽
			BoomJudge.previous_map_size[y][x+bombSize] = item_index;
			BoomJudge.map_size[y][x+bombSize] = item_index;
			}
		}
		if(x-bombSize>=0) {
			if((BoomJudge.previous_map_size[y][x-bombSize] == box_index) && (BoomJudge.map_size[y][x-bombSize] == 4)) {//물풍선 놓인 자리 왼쪽
			BoomJudge.previous_map_size[y][x-bombSize] = item_index;
			BoomJudge.map_size[y][x-bombSize] = item_index;
			}
		}
	}
}
