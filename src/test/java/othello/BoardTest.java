package othello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {


	@Test
	void getCellTest() {
		
		Cell[][] cells = new Cell[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
		
		assertEquals(null, Board.getCell(0,10));
		assertEquals(null, Board.getCell(-1,0));
		
		assertEquals(Board.getCell(1,0), Board.getCell(1,0));
	}

}
