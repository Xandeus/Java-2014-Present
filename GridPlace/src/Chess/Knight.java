package chess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Knight extends GamePiece {
	public Knight(int x, int y, boolean isWhite, int posX, int posY) {
		super(x, y, isWhite, posX, posY);
		// TODO Auto-generated constructor stub
		try {
			if (isWhite())
				image = ImageIO.read(new File(
						"C:\\Users\\Alex\\Dropbox\\Programming\\JavaAsus\\GridPlace\\src\\resources\\whiteKnight.png"));
			else
				image = ImageIO.read(new File(
						"C:\\Users\\Alex\\Dropbox\\Programming\\JavaAsus\\GridPlace\\src\\resources\\blackKnight.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImage(image);
		setName("Knight");
	}

	ArrayList<GamePiece> validMoves;

	@Override
	public boolean isMoveValid(GamePiece desiredMove, GamePiece[][] pieces) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<GamePiece> findValidMoves(GamePiece[][] pieces) {
		// TODO Auto-generated method stub
		validMoves = new ArrayList<GamePiece>();
		int posX = this.getPosX();
		int posY = this.getPosY();
		
		return validMoves;
	}
}
