package othello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {
	Cell cell = new Cell(5, 2);
	Cell cellErr = new Cell(-1,10);

	//	コマの初期化
	public void init() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Board.getCell(i, j).setStatus(0);
			}
			//	セル座標に置かれているコマを白に設定
			Board.getCell(4, 3).setStatus(2);
			Board.getCell(3, 4).setStatus(2);
			//	セル座標に置かれているコマを黒に設定
			Board.getCell(3, 3).setStatus(1);
			Board.getCell(4, 4).setStatus(1);
		}
	}

	@Test
	void X座標の値取得() {
		//	コマが置かれてるX座標の値を取得
		assertEquals(5, cell.getX());
		//	セル外のX座標の値を取得
		assertEquals(-1, cellErr.getX());
	}
	@Test
	void Y座標の値取得() {
		//	コマが置かれてるX座標の値を取得
		assertEquals(2, cell.getY());
		//	セル外のY座標の値を取得
		assertEquals(10, cellErr.getY());
	}
	@Test
	void コマのステータスを取得() {
		//	コマの状態「なし」を取得
		assertEquals(0,cell.getStatus());
		//	置かれてるコマの状態を「黒」に設定
		cell.setStatus(1);
		//	置かれてるコマの状態「黒」を取得
		assertEquals(1,cell.getStatus());
		//	置かれてるコマの状態を「白」に設定
		cell.setStatus(2);
		//	置かれてるコマの状態「白」を取得
		assertEquals(2,cell.getStatus());
	}
	@Test
	void 黒のコマが置けるか確認() {
		//	コマの初期化
		init();
		//	「黒」のコマが置けなければfalseを返す
		assertEquals(false,Board.getCell(3, 3).possibleMoveBlack());
		//	「黒」のコマが置ければtrueを返す
		assertEquals(true,Board.getCell(5, 3).possibleMoveBlack());
		//	セル外ならfalseを返す
		assertEquals(false,Board.getCell(0, 7).possibleMoveBlack());
	}
	@Test
	void 白のコマが置けるか確認() {
		//	コマの初期化
		init();
		//	「白」のコマが置けなければfalseを返す
		assertEquals(false,Board.getCell(4, 3).possibleMoveWhite());
		//	「白」のコマが置ければtrueを返す
		assertEquals(true,Board.getCell(5, 4).possibleMoveWhite());
		//	セル外ならfalseを返す
		assertEquals(false,Board.getCell(0, 7).possibleMoveWhite());
	}
	@Test
	void コマが返せるか確認(){
		//	コマの初期化
		init();
		//	コマを置くとき、置かれてるコマが返せればtrueを返す(白)
		assertEquals(true,Board.getCell(3, 4).canFlip(Board.getCell(2, 4)));
		assertEquals(true,Board.getCell(3, 4).canFlip(Board.getCell(3, 5)));
		//	コマを置くとき、置かれてるコマが返せればtrueを返す(黒)
		assertEquals(true,Board.getCell(3, 3).canFlip(Board.getCell(2, 3)));
		assertEquals(true,Board.getCell(3, 3).canFlip(Board.getCell(3, 2)));

		//	コマを置くとき、置かれてるコマが返せなければfalseを返す
		Board.getCell(0, 7).setStatus(1);
		assertEquals(false,Board.getCell(0, 7).canFlip(Board.getCell(1, 6)));
		Board.getCell(1, 6).setStatus(1);
		assertEquals(false,Board.getCell(0, 7).canFlip(Board.getCell(1, 6)));
		assertEquals(false,Board.getCell(1, 6).canFlip(Board.getCell(0, 7)));

	}
	@Test
	void 隣接する8方向のセルを呼ぶ() {
		//	コマの初期化
		init();
		//	自分の状態が「黒」の場合、falseを返す
		Board.getCell(3, 5).onMove(false);
		//	自分の状態が「黒」か確認する
			assertEquals(1,Board.getCell(3, 5).getStatus());
		//	自分の状態が「白」の場合、trueを返す
		Board.getCell(2, 3).onMove(true);
		//	自分の状態が「白」か確認する
		assertEquals(2,Board.getCell(2, 3).getStatus());

		//	自分の状態が「黒」の場合、falseを返す(範囲外の場合、coutinueに入るか確認)
		Board.getCell(0, 7).onMove(false);
		//	自分の状態が「黒」か確認する
		assertEquals(1,Board.getCell(0, 7).getStatus());
		//	自分の状態が「白」の場合、trueを返す(範囲外の場合、coutinueに入るか確認)
		Board.getCell(0, 7).onMove(true);
		//	自分の状態が「白」か確認する
		assertEquals(2,Board.getCell(0, 7).getStatus());
	}
	@Test
	void 黒を白に返す(){
		//	コマの初期化
		init();
		//	置かれてる「白」コマが「黒」に返せればtrueを返す
		Board.getCell(4, 4).flip(Board.getCell(5, 3), true);
		Board.getCell(3, 3).flip(Board.getCell(3, 4), true);
		Board.getCell(3, 3).flip(Board.getCell(4, 4), true);
		Board.getCell(3, 3).flip(Board.getCell(4, 3), true);
		//	置かれてる「白」コマが「黒」に返せなければfalseを返す
		Board.getCell(3, 3).flip(Board.getCell(2, 2), false);

		//	例外を強制で出す
		Board.getCell(1, 6).setStatus(1);
		Board.getCell(0, 7).setStatus(1);
		assertThrows(RuntimeException.class, () -> Board.getCell(0, 7).flip(Board.getCell(1, 6), true));
	}
	@Test
	void 白を黒に返す() {
		//	コマの初期化
		init();
		//	置かれてる「白」コマが「黒」に返せればfalseを返す
		Board.getCell(3, 4).flip(Board.getCell(4, 4), false);
		Board.getCell(3, 4).flip(Board.getCell(3, 4), false);
		Board.getCell(4, 3).flip(Board.getCell(4, 4), false);
		Board.getCell(4, 3).flip(Board.getCell(3, 4), false);
		//	置かれてる「白」コマが「黒」に返せなければfalseを返す
		Board.getCell(4, 4).flip(Board.getCell(3, 4), true);
		//	例外を強制で出す
		Board.getCell(1, 6).setStatus(2);
		Board.getCell(0, 7).setStatus(2);
		assertThrows(RuntimeException.class, () -> Board.getCell(0, 7).flip(Board.getCell(1, 6), false));
	}


	@Test
	void ステータスを設定() {
		//	自分の状態を「黒」に設定する
		cell.setStatus(1);
		//	自分の状態が「黒」か確認
		assertEquals(1,cell.getStatus());
	}
}
