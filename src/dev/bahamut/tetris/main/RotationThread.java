package dev.bahamut.tetris.main;

import dev.bahamut.tetris.pieces.Piece;

public class RotationThread extends Thread {
	
	Piece pieceToRotate;
	
	public RotationThread(Piece pieceToRotate) {
		this.pieceToRotate = pieceToRotate;
	}

	@Override
	public void run() {
			
		pieceToRotate.rotateLeft();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
}
