package othello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {
	Cell cell = new Cell(5, 2);
	Cell cellErr = new Cell(-1,10);

	public void init() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Board.getCell(i, j).setStatus(0);
			}
			Board.getCell(4, 3).setStatus(2);
			Board.getCell(3, 4).setStatus(2);
			Board.getCell(3, 3).setStatus(1);
			Board.getCell(4, 4).setStatus(1);
		}
	}


	@Test
	void getXTest() {
		assertEquals(5, cell.getX());
		assertEquals(-1, cellErr.getX());
	}
	@Test
	void getYTest() {
		assertEquals(2, cell.getY());
		assertEquals(10, cellErr.getY());
	}
	@Test
	void getStatusTest() {
		assertEquals(0,cell.getStatus());
		cell.setStatus(1);
		assertEquals(1,cell.getStatus());
		cell.setStatus(2);
		assertEquals(2,cell.getStatus());
	}
	@Test
	void possibleMoveBlackTest() {
		init();
		Board.getCell(1, 1).setStatus(1);
		assertEquals(false,Board.getCell(1, 1).possibleMoveBlack());
		Board.getCell(3, 2).setStatus(1);
		Board.getCell(2, 1).setStatus(2);
		assertEquals(true,Board.getCell(1, 0).possibleMoveBlack());
		Board.getCell(0, 7).setStatus(0);
		assertEquals(false,Board.getCell(0, 7).possibleMoveBlack());
	}
	@Test
	void possibleMoveWhiteTest() {
		init();
		Board.getCell(1, 1).setStatus(1);
		assertEquals(false,Board.getCell(1, 1).possibleMoveWhite());
		Board.getCell(3, 2).setStatus(2);
		Board.getCell(2, 1).setStatus(1);
		assertEquals(true,Board.getCell(1, 0).possibleMoveWhite());
		Board.getCell(0, 7).setStatus(0);
		assertEquals(false,Board.getCell(0, 7).possibleMoveWhite());
	}
	@Test
	void canFlipTest(){
		init();
		
		assertEquals(true,Board.getCell(3, 4).canFlip(Board.getCell(2, 4)));
		assertEquals(true,Board.getCell(3, 4).canFlip(Board.getCell(3, 5)));

		assertEquals(true,Board.getCell(3, 3).canFlip(Board.getCell(2, 3)));
		assertEquals(true,Board.getCell(3, 3).canFlip(Board.getCell(3, 2)));

		Board.getCell(0, 7).setStatus(1);
		assertEquals(false,Board.getCell(0, 7).canFlip(Board.getCell(1, 6)));
		Board.getCell(1, 6).setStatus(1);
		assertEquals(false,Board.getCell(0, 7).canFlip(Board.getCell(1, 6)));
		assertEquals(false,Board.getCell(1, 6).canFlip(Board.getCell(0, 7)));
		
	}
	@Test
	void onMoveTest() {
		init();
		Board.getCell(2, 3).onMove(true);
		Board.getCell(2, 3).onMove(false);
		assertEquals(1,Board.getCell(2, 3).getStatus());
		Board.getCell(2, 3).onMove(false);
		Board.getCell(2, 3).onMove(true);
		assertEquals(2,Board.getCell(2, 3).getStatus());

		Board.getCell(0, 7).onMove(false);
		assertEquals(1,Board.getCell(0, 7).getStatus());
		Board.getCell(0, 7).onMove(true);
		assertEquals(2,Board.getCell(0, 7).getStatus());
	}
	@Test
	void flipTest(){
		init();
		Board.getCell(4, 4).flip(Board.getCell(5, 3), true);
		Board.getCell(3, 3).flip(Board.getCell(3, 4), true);
		Board.getCell(3, 3).flip(Board.getCell(4, 4), true);
		Board.getCell(3, 3).flip(Board.getCell(4, 3), true);
		Board.getCell(3, 3).flip(Board.getCell(2, 2), false);
		
		Board.getCell(1, 6).setStatus(1);
		Board.getCell(0, 7).setStatus(1);
		assertThrows(RuntimeException.class, () -> Board.getCell(0, 7).flip(Board.getCell(1, 6), true));
		
		init();
		Board.getCell(3, 4).flip(Board.getCell(4, 4), false);
		Board.getCell(3, 4).flip(Board.getCell(3, 4), false);
		Board.getCell(4, 3).flip(Board.getCell(4, 4), false);
		Board.getCell(4, 3).flip(Board.getCell(3, 4), false);
		Board.getCell(3, 3).flip(Board.getCell(2, 2), true);
		
		Board.getCell(1, 6).setStatus(2);
		Board.getCell(0, 7).setStatus(2);
		assertThrows(RuntimeException.class, () -> Board.getCell(0, 7).flip(Board.getCell(1, 6), false));
		
	}

	@Test
	void setStatusTest() {
		cell.setStatus(1);
		assertEquals(1,cell.getStatus());
	}
}
