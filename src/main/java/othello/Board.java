package othello;

public class Board {

	private static Board board = new Board();

	private static final int BOARD_SIZE = 8;

	private Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];
	//配列の添字と同一のx値とy値を与えられて初期化
	static {
		for(int y = 0; y < BOARD_SIZE; ++y) {
			for(int x = 0; x < BOARD_SIZE; ++x) {
				board.cells[x][y] = new Cell(x,y);
			}
		}
	}
//
//	private Board() {
//
//	}
//
	public static Cell getCell(int x, int y) {
		//不正な引数を与えた場合、null値が返却される
		if(x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		return board.cells[x][y];
	}
}
