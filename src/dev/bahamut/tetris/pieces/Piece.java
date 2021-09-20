package dev.bahamut.tetris.pieces;

import dev.bahamut.tetris.coordinates.Coordinate;
import dev.bahamut.tetris.input.KeyManager;
import dev.bahamut.tetris.main.Game;
import dev.bahamut.tetris.main.RotationThread;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
import java.security.SecureRandom;

public class Piece {
	
	public static float DEFSPEED = 2.0f;
	
	public static int CELLDIM = 42;
	
	public static int RED = 1;
	public static int GREEN = 2;
	public static int BLUE = 3;
	public static int PURPLE = 4;
	
	private Coordinate[] cells;
	private Coordinate position;
	private int color;
	private int type;
	private int rotation;
	
	private SecureRandom random;
	
	private Game game;
	private KeyManager keyManager;
	
	private Coordinate[] stopCoordinates;
	
	private RotationThread rotationThread = new RotationThread(this);
	
	public Piece(Game game){		//Game is passed to get input from user
		this.game = game;
		cells = new Coordinate[4];
		random = new SecureRandom();
		type = random.nextInt(6);
		stopCoordinates = new Coordinate[4];
		generate();
	}
	
	public Coordinate[] getCells() {
		return cells;
	}
	
	public void setStopCoordinates(int index, Coordinate coordinate) {
		stopCoordinates[index] = coordinate;
	}

	public Coordinate[] getStopCoordinates() {
		return stopCoordinates;
	}
	
	public int getColor() {
		return color;
	}
	
	public int getType() {
		return type;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	
	public void generate() {
		
		position = new Coordinate((random.nextInt(10) + 3) * CELLDIM, - 2 * CELLDIM);
		
		rotation = random.nextInt(4);
		color = random.nextInt(4) + 1;

		if (type == 0) {		//If it is a straight piece
			
			if(rotation == 0) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[2] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() + 2 * CELLDIM, position.getY());
			}
			
			if(rotation == 1) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[3] = new Coordinate(position.getX(), position.getY() + 2 * CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[2] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() - 2 * CELLDIM, position.getY());
			}
			
			if(rotation == 3) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[3] = new Coordinate(position.getX(), position.getY() - 2 * CELLDIM);
			}
			
		}
		
		if(type == 1) {			//If it is a Z piece
			
			if(rotation == 0) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[2] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() - CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() , position.getY() - CELLDIM);
				cells[2] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() + CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[2] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() - CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() , position.getY() - CELLDIM);
				cells[2] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() + CELLDIM);
			}
			
		}
		
		if(type == 2) {			//If it is a J piece
			
			if(rotation == 0) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[3] = new Coordinate(position.getX() - CELLDIM, position.getY() + CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() + CELLDIM , position.getY());
				cells[2] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() - CELLDIM, position.getY() - CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() - CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() - CELLDIM , position.getY());
				cells[2] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() + CELLDIM);
			}
			
		}
		
		if(type == 3) {			//If it is a L piece
			
			if(rotation == 0) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() + CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() + CELLDIM , position.getY());
				cells[2] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() - CELLDIM, position.getY() + CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[3] = new Coordinate(position.getX() - CELLDIM, position.getY() - CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[2] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() - CELLDIM);
			}
			
		}
		
		if(type == 4) {			//If it is a square piece
			
			cells[0] = position;
			cells[1] = new Coordinate(position.getX() + CELLDIM, position.getY());
			cells[2] = new Coordinate(position.getX(), position.getY() - CELLDIM);
			cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY() - CELLDIM);
			
		}
		
		if(type == 5) {			//If it is a T piece
			
			if(rotation == 0) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[2] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX(), position.getY() - CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() , position.getY() - CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[3] = new Coordinate(position.getX() - CELLDIM, position.getY());
			}
			
			if(rotation == 2) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX() + CELLDIM, position.getY());
				cells[2] = new Coordinate(position.getX() - CELLDIM, position.getY());
				cells[3] = new Coordinate(position.getX(), position.getY() + CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = position;
				cells[1] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[3] = new Coordinate(position.getX() + CELLDIM, position.getY());
			}
			
		}
		
	}
	
	public void rotateLeft() {
		
		if(rotation > 0) {
			rotation -= 1;
		}
		else
			rotation = 3;
		
		if (type == 0) {		//If it is a straight piece
			
			if(rotation == 0) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() + 2 * CELLDIM, cells[0].getY());
			}
			
			if(rotation == 1) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(position.getX(), position.getY() - CELLDIM);
				cells[2] = new Coordinate(position.getX(), position.getY() + CELLDIM);
				cells[3] = new Coordinate(position.getX(), position.getY() + 2 * CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() - 2 * CELLDIM, cells[0].getY());
			}
			
			if(rotation == 3) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[3] = new Coordinate(cells[0].getX(), cells[0].getY() - 2 * CELLDIM);
			}
			
		}
		
		if(type == 1) {			//If it is a Z piece
			
			if(rotation == 0) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() - CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() , cells[0].getY() - CELLDIM);
				cells[2] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() + CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() - CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() , cells[0].getY() - CELLDIM);
				cells[2] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() + CELLDIM);
			}
			
		}
		
		if(type == 2) {			//If it is a J piece
			
			if(rotation == 0) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY() + CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() + CELLDIM , cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY() - CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() - CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() - CELLDIM , cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() + CELLDIM);
			}
			
		}
		
		if(type == 3) {			//If it is a L piece
			
			if(rotation == 0) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() + CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() + CELLDIM , cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY() + CELLDIM);
			}
			
			if(rotation == 2) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY() - CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() - CELLDIM);
			}
			
		}
		
		if(type == 4) {			//If it is a square piece
			
			cells[0] = cells[0];
			cells[1] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
			cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
			cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY() - CELLDIM);
			
		}
		
		if(type == 5) {			//If it is a T piece
			
			if(rotation == 0) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
			}
			
			if(rotation == 1) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() , cells[0].getY() - CELLDIM);
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
			}
			
			if(rotation == 2) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
				cells[2] = new Coordinate(cells[0].getX() - CELLDIM, cells[0].getY());
				cells[3] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
			}
			
			if(rotation == 3) {
				cells[0] = cells[0];
				cells[1] = new Coordinate(cells[0].getX(), cells[0].getY() + CELLDIM);
				cells[2] = new Coordinate(cells[0].getX(), cells[0].getY() - CELLDIM);
				cells[3] = new Coordinate(cells[0].getX() + CELLDIM, cells[0].getY());
			}
			
		}
		
		
	}
	
	public boolean isCollidingLeft() {
		
		int[][] grid = game.getGrid();
		int xControl;
		int yControl1, yControl2;
		int[] nearestX, nearestY;
		
		for(int i = 0; i < cells.length; i++) {
			
			nearestY = game.findNearestY(cells[i].getY());
			
			if (cells[i].getX() % CELLDIM == 0) {
				xControl = ((int)cells[i].getX() - CELLDIM) / CELLDIM;
				yControl1 = nearestY[1] / CELLDIM;
				yControl2 = nearestY[0] / CELLDIM;
				
				if(yControl1 < 21 && yControl2 < 21 && yControl1 > 0 && yControl2 > 0)
					if((grid[yControl1][xControl] > 0) || (grid[yControl2][xControl] > 0)) return true;
				
			}
			else {
				nearestX = game.findNearestX(cells[i].getX());
				xControl = (nearestX[1] - CELLDIM) / CELLDIM;
				yControl1 = nearestY[1] / CELLDIM;
				yControl2 = nearestY[0] / CELLDIM;
				
				if(yControl1 < 21 && yControl2 < 21 && yControl1 > 0 && yControl2 > 0 && xControl > 0 && xControl < 15)
					if((grid[yControl1][xControl] > 0) || (grid[yControl2][xControl] > 0)) return true;
			}
		}
		
		return false;
	}
	
	public boolean isCollidingRight() {
		
		int[][] grid = game.getGrid();
		int xControl;
		int yControl1, yControl2;
		int[] nearestX, nearestY;
		
		for(int i = 0; i < cells.length; i++) {
			
			nearestY = game.findNearestY(cells[i].getY());
			
			if (cells[i].getX() % CELLDIM == 0) {
				xControl = ((int)cells[i].getX() + CELLDIM) / CELLDIM;
				yControl1 = nearestY[1] / CELLDIM;
				yControl2 = nearestY[0] / CELLDIM;
				
				if(yControl1 < 21 && yControl2 < 21 && yControl1 > 0 && yControl2 > 0)
					if((grid[yControl1][xControl] > 0) || (grid[yControl2][xControl] > 0)) return true;
				
			}
			else {
				nearestX = game.findNearestX(cells[i].getX());
				
				xControl = (nearestX[1]) / CELLDIM;
				yControl1 = nearestY[1] / CELLDIM;
				yControl2 = nearestY[0] / CELLDIM;
				
				if(yControl1 < 21 && yControl2 < 21 && yControl1 > 0 && yControl2 > 0 && xControl > 0 && xControl < 15)
					if((grid[yControl1][xControl] > 0) || (grid[yControl2][xControl] > 0)) return true;
			}
		}
		
		return false;
	}
	
	public void fall() {
		
		//position.setY(position.getY() + DEFSPEED);
		
		for(int i = 0; i < cells.length; i++)
			cells[i].setY(cells[i].getY() + DEFSPEED);
		
	}
	
	public void fallFaster() {
		
		//position.setY(position.getY() + DEFSPEED);
		
		for(int i = 0; i < cells.length; i++)
			cells[i].setY(cells[i].getY() + 2 * DEFSPEED);
		
	}
	
	public void moveLeft() {
		
		for(int i = 0; i < cells.length; i++)
			cells[i].setX(cells[i].getX() - 3 * DEFSPEED);
		
	}
	
	public void moveRight() {
		
		for(int i = 0; i < cells.length; i++)
			cells[i].setX(cells[i].getX() + 3 * DEFSPEED);
		
	}
	
	public void tick() {
		
		keyManager = game.getKeyManager();
		boolean isCollidingLeft = false, isCollidingRight = false;
		
		for(int i = 0; i < cells.length; i++) {
			if(cells[i].getX() >= game.getDisplay().getCanvas().getWidth() - CELLDIM)
				isCollidingRight = true;
			if(cells[i].getX() <= 0)
				isCollidingLeft = true;
		}
		
		if(keyManager.down) {
			fallFaster();
		}
		else {
			fall();
		}
		if(keyManager.right && !isCollidingRight && !isCollidingRight())
			moveRight();
		if(keyManager.left && !isCollidingLeft && !isCollidingLeft() )
			moveLeft();
		
		if(keyManager.rotate && !rotationThread.isAlive()) {
			rotationThread = new RotationThread(this);
			rotationThread.start();
		}
		
	}
	
	public void render(Graphics g) {
		
		if(color == RED)
			g.setColor(Color.red);
		else if(color == GREEN)
			g.setColor(Color.green);
		else if(color == BLUE)
			g.setColor(Color.blue);
		else if(color == PURPLE)
			g.setColor(Color.pink);
		
		g.fillRect((int)cells[0].getX(), (int)cells[0].getY(), CELLDIM , CELLDIM);
		g.fillRect((int)cells[1].getX(), (int)cells[1].getY(), CELLDIM , CELLDIM);
		g.fillRect((int)cells[2].getX(), (int)cells[2].getY(), CELLDIM , CELLDIM);
		g.fillRect((int)cells[3].getX(), (int)cells[3].getY(), CELLDIM , CELLDIM);
		
	}

}
