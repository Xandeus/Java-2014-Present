package chess;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pawn extends GamePiece {
	int endVal = 3;
	boolean fMove = true;
	ArrayList<GamePiece> validMoves;

	// Image image;
	public Pawn(int x, int y, boolean isWhite, int posX, int posY) {
		super(x, y, isWhite, posX, posY);
		try {
			if (isWhite)
				image = ImageIO.read(new File("res/whitePawn.png"));
			else
				image = ImageIO.read(new File("res/blackPawn.png"));
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
			if (p == desiredMove) {
				if ((this.getPosX() == desiredMove.getPosX()) || (!desiredMove.getName().equals("null"))) {
					return true;
				}
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
		if (!this.isWhite()) {
			for (int y = posY + 1; y < posY + endVal; y++) {
				if((posY+1) < 7){
					if (posX != 0) {
						validMoves.add(pieces[posX - 1][posY + 1]);
					}
					if (posX != 7) {
						validMoves.add(pieces[posX + 1][posY + 1]);
					}
					if (pieces[posX][y].getName().equals("null")) {
						validMoves.add(pieces[posX][y]);
					} else
						return validMoves;
				}
				
			}
		} else
			for (int y = posY - 1; y > posY - endVal; y--) {
				// Moving forward
				if((posY-1) > 0){
					if (posX != 0) {
						validMoves.add(pieces[posX - 1][posY - 1]);
					}
					if (posX != 7) {
						validMoves.add(pieces[posX + 1][posY - 1]);
					}
					if (pieces[posX][y].getName().equals("null")) {
						validMoves.add(pieces[posX][y]);
					} else
						return validMoves;
				}
				

			}
		return validMoves;
	}

	public ArrayList<GamePiece> findValidAttacks(GamePiece[][] pieces) {
		validMoves = new ArrayList<GamePiece>();
		int posX = this.getPosX();
		int posY = this.getPosY();
		if (!this.isWhite()) {
			if (posX != 0) {
				validMoves.add(pieces[posX - 1][posY + 1]);
			}
			if (posX != 7) {
				validMoves.add(pieces[posX + 1][posY + 1]);
			}
		} else {
			if (posX != 0) {
				validMoves.add(pieces[posX - 1][posY - 1]);
			}
			if (posX != 7) {
				validMoves.add(pieces[posX + 1][posY - 1]);
			}
		}

		return validMoves;
	}
	@Override
	public void movePiece(GamePiece pSelected, GamePiece[][] pieces,int width, int height,GamePiece desiredMove){
		GamePiece temp = new EmptySpace(pSelected.getPosX() * (width / 8),
				pSelected.getPosY() * (height / 8), pSelected.getPosX(), pSelected.getPosY());
		pieces[pSelected.getPosX()][pSelected.getPosY()] = temp;
		pSelected.setPosX(desiredMove.getPosX());
		pSelected.setPosY(desiredMove.getPosY());
		pieces[desiredMove.getPosX()][desiredMove.getPosY()] = pSelected;
		fMove = false;
	}
}
