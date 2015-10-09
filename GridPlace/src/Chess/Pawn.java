package chess;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pawn extends GamePiece{
	int endVal = 3;
	boolean fMove = true;
	ArrayList<GamePiece> validMoves;
	//Image image;
	public Pawn(int x, int y, boolean isWhite, int posX, int posY) {
		super(x, y, isWhite, posX, posY);
		try {
			if(isWhite)
				image = ImageIO.read(new File("C:\\Users\\Alex\\Dropbox\\Programming\\JavaAsus\\GridPlace\\src\\resources\\whitePawn.png"));
			else
				image = ImageIO.read(new File("C:\\Users\\Alex\\Dropbox\\Programming\\JavaAsus\\GridPlace\\src\\resources\\blackPawn.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setName("pawn");
		setImage(image);
	}

	public boolean isMoveValid(GamePiece desiredMove, GamePiece[][] pieces) {
		// TODO Auto-generated method stub
		validMoves = findValidMoves(pieces);
		for (GamePiece p : validMoves) {
			System.out.println(p.getPosY() + " " + desiredMove.getPosX());
			if ((p.getPosX() == desiredMove.getPosX()) && (p.getPosY() == desiredMove.getPosY())) {
				fMove = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<GamePiece> findValidMoves(GamePiece[][] pieces) {
		validMoves = new ArrayList<GamePiece>();
		int posX = this.getPosX();
		int posY = this.getPosY();
		if (!fMove) {
			endVal = 2;
		}
		if (!isWhite()) {
			for (int y = posY + 1; y < posY + endVal; y++) {
				if (posX != 0 && !pieces[posX - 1][posY + 1].isWhite()
						&& !pieces[posX - 1][posY + 1].getName().equals("null")) {
					validMoves.add(pieces[posX - 1][posY + 1]);
				}if (posX != 7 && !pieces[posX + 1][posY + 1].isWhite()
						&& !pieces[posX + 1][posY + 1].getName().equals("null")) {
					validMoves.add(pieces[posX + 1][posY + 1]);
				}
				if (pieces[posX][y].getName().equals("null")) {
					validMoves.add(pieces[posX][y]);
				}
				else
					return validMoves;
			}
		} else
			for (int y = posY - 1; y > posY - endVal; y--) {
				// Moving forward
				if (posX != 0 && !pieces[posX - 1][posY - 1].isWhite()
						&& !pieces[posX - 1][posY - 1].getName().equals("null")) {
					validMoves.add(pieces[posX - 1][posY - 1]);
				}if (posX != 7 && !pieces[posX + 1][posY - 1].isWhite()
						&& !pieces[posX + 1][posY - 1].getName().equals("null")) {
					validMoves.add(pieces[posX + 1][posY - 1]);
				}
				if (pieces[posX][y].getName().equals("null")) {
					validMoves.add(pieces[posX][y]);
				}
				else
					return validMoves;
				 
			}
		return validMoves;
	}
}
