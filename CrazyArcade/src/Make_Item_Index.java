public class Make_Item_Index {
	
	public Make_Item_Index() {
		
	}
	
	public void make_item_index(int y, int x, int bombSize, int balloonplus, int box_index, int item_index) {
		if((BoomJudge.previous_map_size[y][x] == box_index) && (BoomJudge.map_size[y][x] == 4)) {//��ǳ�� ���� �ڸ�
		BoomJudge.previous_map_size[y][x] =	item_index;
		BoomJudge.map_size[y][x] = item_index;
		}
		for(int plusbombsize = 0; plusbombsize<balloonplus+1; plusbombsize++) {
			if(y+bombSize+plusbombsize<=12) {
				if((BoomJudge.previous_map_size[y+bombSize+plusbombsize][x] == box_index) && (BoomJudge.map_size[y+bombSize+plusbombsize][x] == 4)) {//��ǳ�� ���� �ڸ� �Ʒ�
				BoomJudge.previous_map_size[y+bombSize+plusbombsize][x] = item_index;
				BoomJudge.map_size[y+bombSize+plusbombsize][x] = item_index;
				}
			}
			if(y-bombSize-plusbombsize>=0) {
				if((BoomJudge.previous_map_size[y-bombSize-plusbombsize][x] == box_index) && (BoomJudge.map_size[y-bombSize-plusbombsize][x] == 4)) {//��ǳ�� ���� �ڸ� ��
				BoomJudge.previous_map_size[y-bombSize-plusbombsize][x] = item_index;
				BoomJudge.map_size[y-bombSize-plusbombsize][x] = item_index;
				}
			}
			if(x+bombSize+plusbombsize<=12) {
				if((BoomJudge.previous_map_size[y][x+bombSize+plusbombsize] == box_index) && (BoomJudge.map_size[y][x+bombSize+plusbombsize] == 4)) {//��ǳ�� ���� �ڸ� ������
				BoomJudge.previous_map_size[y][x+bombSize+plusbombsize] = item_index;
				BoomJudge.map_size[y][x+bombSize+plusbombsize] = item_index;
				}
			}
			if(x-bombSize-plusbombsize>=0) {
				if((BoomJudge.previous_map_size[y][x-bombSize-plusbombsize] == box_index) && (BoomJudge.map_size[y][x-bombSize-plusbombsize] == 4)) {//��ǳ�� ���� �ڸ� ����
				BoomJudge.previous_map_size[y][x-bombSize-plusbombsize] = item_index;
				BoomJudge.map_size[y][x-bombSize-plusbombsize] = item_index;
				}
			}
		}
	}
}