package othello;

public class Cell {
	//なし(0)、黒(1)、白(2)
	private int status = 0;

	private int x, y;

	public Cell(int x, int y) {
		//0～7の範囲
		if(x > 0 || x < 7 && y > 0 || y < 7) {
			this.x = x;
			this.y = y;
		}
		return;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getStatus() {
		return this.status;
	}

	public boolean possibleMoveBlack() {
		//自分の状態がすでに「黒」もしくは「白」の場合falseを返す
		if(this.status != 0) {
			return false;
		//自分状態が「なし」の場合、自分の周囲八方に問い合わせをかける
		}else{
			//8方向のうち一つでもtrueが返ってきたら探索を打ち切り、trueを返却する
			for(int i = -1; i < 2; i++) {
				for(int j = -1;j < 2; j++ ) {
					if(i == 0 && j == 0) {
						continue;
					}
					if(x+i < 0 || x+i > 7 || y+j < 0 || y+j > 7) {
						continue;
					}
					//取得したCellの状態が「白」であればそのCellのcanFlipメソッドを、自分自身を引数として呼び出す。
					if(Board.getCell((x+i),(y+j)).status == 2) {
						if(Board.getCell((x+i),(y+j)).canFlip(this) == true) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}

	public boolean possibleMoveWhite() {
		//自分の状態がすでに「黒」もしくは「白」の場合falseを返す
		if(this.status != 0) {
			return false;
		//自分状態が「なし」の場合、自分の周囲八方に問い合わせをかける
		}else{
			//8方向のうち一つでもtrueが返ってきたら探索を打ち切り、trueを返却する
			for(int i = -1; i < 2; i++) {
				for(int j = -1;j < 2; j++ ) {
					if(i == 0 && j == 0) {
						continue;
					}
					if(x+i < 0 || x+i > 7 || y+j < 0 || y+j > 7) {
						continue;
					}
					//取得したCellの状態が「黒」であればそのCellのcanFlipメソッドを、自分自身を引数として呼び出す。
					if(Board.getCell((x+i),(y+j)).status == 1) {
						if(Board.getCell((x+i),(y+j)).canFlip(this) == true) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}

	boolean canFlip(Cell client) {
		int cliX = client.x;
		int cliY = client.y;
		if(client.x == this.x) {
			cliX = this.x;
		}
		if(client.x < this.x) {
			cliX = this.x+1;
		}
		if(client.x > this.x) {
			cliX = this.x-1;
		}
		if(client.y == this.y) {
			cliY = this.y;
		}
		if(client.y < this.y) {
			cliY = this.y+1;
		}
		if(client.y > this.y) {
			cliY = this.y-1;
		}
		if(this.status == 0) {
			return false;
		}
		//呼び出し元Cellの状態が「なし」である場合、自分自身を引数として次のCellのcanFlipを呼び出し、その返却値を返却する
		if(client.status == 0) {
			if(cliX < 0 || cliX > 7 || cliY < 0 || cliY > 7 ) {
				return false;
			}
			return Board.getCell(cliX,cliY).canFlip(this);
		}

		//呼び出し元Cellの状態と、自分自身の状態が異なる場合、trueを返却する

		if(client.status != this.status) {
			return true;
		}
		//呼び出し元Cellの状態と、自分自身の状態が一致する場合}
		if(client.status == this.status) {
			//次のCellが存在しない場合、falseを返却する{
			if(cliX < 0 || cliX > 7 || cliY < 0 || cliY > 7 ) {
				return false;
			//次のCellが存在する場合、次のCellのcanFlipを呼び出し、その返却値を返却する;
			}else {
				return Board.getCell(cliX,cliY).canFlip(this);
			}
		}
		return false;
	}

	public void onMove(boolean white) {
		//whilteがtrueの場合、自分の状態を「白」に変更する
		if(white) {
			//自分の隣接する8方向のCellのflip(this, true)をコールする
			for(int i = -1; i < 2; i++) {
				for(int j = -1;j < 2; j++ ) {
					if(i == 0 && j == 0) {
						continue;
					}
					if(x+i < 0 || x+i > 7 || y+j < 0 || y+j > 7) {
						continue;
					}
					if(Board.getCell((x+i),(y+j)).status == 1) {
						if(Board.getCell((x+i),(y+j)).canFlip(this) == true) {
							Board.getCell((x+i),(y+j)).flip(this,true);
						}
					}
				}
			}
			this.status = 2;
			
		//whilteがfalseの場合、自分の状態を「黒」に変更する
		}else{
			//自分の隣接する8方向のCellのflip(this, false)をコールする
			for(int i = -1; i < 2; i++) {
				for(int j = -1;j < 2; j++ ) {
					if(i == 0 && j == 0) {
						continue;
					}
					if(x+i < 0 || x+i > 7 || y+j < 0 || y+j > 7) {
						continue;
					}
					if(Board.getCell((x+i),(y+j)).status == 2) {
						if(Board.getCell((x+i),(y+j)).canFlip(this) == true) {
							Board.getCell((x+i),(y+j)).flip(this,false);
						}
					}
				}
			}
			this.status = 1;
		}
	}

	public void flip(Cell client, boolean white) {

		int cliX = client.x;
		int cliY = client.y;

		//whilteがtrueで自分が「白」の場合、何もせずに終了する
		if(white && this.status == 2) {
			return;
		//whilteがtrueで自分が「黒」の場合、自分の状態を「白」にする
		}else if(white && this.status == 1) {
			this.status = 2;

			//clientと自分自身の位置関係から方向を導き出し、次のCellを特定する
			if(client.x == this.x) {
				cliX = this.x;
			}
			if(client.x < this.x) {
				cliX = this.x+1;
			}
			if(client.x > this.x) {
				cliX = this.x-1;
			}
			if(client.y == this.y) {
				cliY = this.y;
			}
			if(client.y < this.y) {
				cliY = this.y+1;
			}
			if(client.y > this.y) {
				cliY = this.y-1;
			}
			//次のCellが存在しない場合、例外を発行する
			if(cliX < 0 || cliX > 7 || cliY < 0 || cliY > 7 ) {
				throw new RuntimeException();
			//次のCellが存在する場合、そのCellのflip(this, true)を呼び出す
			}else {
				Board.getCell(cliX,cliY).flip(this,true);
			}
		}
		//whilteがfalseで自分が「黒」の場合、何もせずに終了する
		if(!white && this.status == 1) {
			return;
		//whilteがfalseで自分が「白」の場合、自分の状態を「黒」にする
		}else if(!white && this.status == 2) {
			this.status = 1;

			//clientと自分自身の位置関係から方向を導き出し、次のCellを特定する
			if(client.x == this.x) {
				cliX = this.x;
			}
			if(client.x < this.x) {
				cliX = this.x+1;
			}
			if(client.x > this.x) {
				cliX = this.x-1;
			}
			if(client.y == this.y) {
				cliY = this.y;
			}
			if(client.y < this.y) {
				cliY = this.y+1;
			}
			if(client.y > this.y) {
				cliY = this.y-1;
			}
			//次のCellが存在しない場合、例外を発行する
			if(cliX < 0 || cliX > 7 || cliY < 0 || cliY > 7 ) {
				throw new RuntimeException();
			//次のCellが存在する場合、そのCellのflip(this, false)を呼び出す
			}else {
				Board.getCell(cliX,cliY).flip(this,false);
			}
		}
	}
	//強制的にセルの状態を設定する
	public void setStatus(int status) {
		this.status = status;
	}
}
