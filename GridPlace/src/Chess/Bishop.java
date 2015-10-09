package chess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bishop extends GamePiece{
	ArrayList<GamePiece> validMoves;
	public Bishop(int x, int y, boolean isWhite, int posX, int posY) {
		super(x, y, isWhite, posX, posY);
		// TODO Auto-generated constructor stub
		try {
			if (isWhite())
				image = ImageIO.read(new File(
						"C:\\Users\\Alex\\Dropbox\\Programming\\JavaAsus\\GridPlace\\src\\resources\\whiteBishop.png"));
			else
				image = ImageIO.read(new File(
						"C:\\Users\\Alex\\Dropbox\\Programming\\JavaAsus\\GridPlace\\src\\resources\\blackBishop.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImage(image);
		setName("Bishop");
	}


	public boolean isMoveValid(GamePiece desiredMove, GamePiece[][] pieces) {
		validMoves = findValidMoves(pieces);
		for (GamePiece p : validMoves) {
			if ((p.getPosX() == desiredMove.getPosX()) && (p.getPosY() == desiredMove.getPosY())) {
				return true;
			}
		}
		return false;
	}

	
	public ArrayList<GamePiece> findValidMoves(GamePiece[][] pieces) {
		// TODO Auto-generated method stub
		int posX = this.getPosX();
		int posY = this.getPosY();
		validMoves = new ArrayList<GamePiece>();
		for (int x = posX - 1; x >= 0; x--) {
			if (pieces[x][posY +x].getName().equals("null")) {
				validMoves.add(pieces[x][posY +x]);
			} else if (pieces[x][posY +x].isWhite() != this.isWhite()) {
				validMoves.add(pieces[x][posY +x]);
				break;
			} else
				break;
		}

		for (int x = posX + 1; x <  Math.min(posY,posX); x++) {
			if (pieces[x][posY +x].getName().equals("null")) {
				validMoves.add(pieces[x][posY +x]);
			} else if (pieces[x][posY].isWhite() != this.isWhite()) {
				validMoves.add(pieces[x][posY +x]);
				break;
			} else
				break;
		}
		for (int x = posX - 1; x >= Math.min(posY,posX); x--) {
			System.out.println(x);
			if (pieces[x][posY +x].equals("null")) {
				validMoves.add(pieces[x][posY -x]);
			} else if (pieces[x][posY +x].isWhite() != this.isWhite()) {
				validMoves.add(pieces[x][posY -x]);
				break;
			} else
				break;
		}
		for (int x = posX + 1; x < Math.min(posY,posX); x++) {
			if (pieces[x][posY +x].getName().equals("null")) {
				validMoves.add(pieces[x][posY +x]);
			} else if (pieces[x][posY +x].isWhite() != this.isWhite()) {
				validMoves.add(pieces[x][posY +x]);
				break;
			} else
				break;
		}
		return validMoves;
	}
}
