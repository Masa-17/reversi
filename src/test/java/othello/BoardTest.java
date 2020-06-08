package othello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {


	@Test
	void getCellTest() {
		//	セル初期化
		Cell[][] cells = new Cell[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
		//	セル範囲外でnullが入ってるか確認
		assertEquals(null, Board.getCell(0,10));
		assertEquals(null, Board.getCell(-1,0));

		//	セル内のコマの位置が正しいか
		assertEquals(Board.getCell(1,0), Board.getCell(1,0));
	}

}
