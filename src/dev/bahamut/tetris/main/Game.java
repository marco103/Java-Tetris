package dev.bahamut.tetris.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.bahamut.tetris.coordinates.Coordinate;
import dev.bahamut.tetris.display.Display;
import dev.bahamut.tetris.input.KeyManager;
import dev.bahamut.tetris.pieces.*;
import java.lang.Math;

public class Game implements Runnable{
	
	private int[][] grid;
	private Display display;
	
	private Thread gameThread;
	
	private boolean running;
	private BufferStrategy bs;
	private BufferStrategy bsNext;
	private Graphics g;
	private Graphics gNext;
	
	private KeyManager keyManager;
	
	//Pieces
	private Piece currentPiece;
	private Piece nextPiece;
	
	int pts = 0;
	int ptsToGive = 0;
	
	public Game() {
		display = new Display();
		grid = new int[21][15];
		keyManager = new KeyManager();
		display.addKeyListener(keyManager);
		start();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public int[][] getGrid(){
		return grid;
	}
	
	public void gridRender(Graphics g) {
		
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x] != 0) {
					if(grid[y][x] == 1)
						g.setColor(Color.red);
					if(grid[y][x] == 2)
						g.setColor(Color.green);
					if(grid[y][x] == 3)
						g.setColor(Color.blue);
					if(grid[y][x] == 4)
						g.setColor(Color.pink);
					
					g.fillRect(x * Piece.CELLDIM, y * Piece.CELLDIM, Piece.CELLDIM, Piece.CELLDIM);
				}
			}
		}
		
	}
	
	public int[] findNearestX(float x) {
		
		int[] multipleValues = new int[21];	//Values that are multiple of Piece.CELLDIM;
		
		for(int i = 0; i < grid[0].length; i++) {
			multipleValues[i] = i * Piece.CELLDIM;
		}
		
		int[] mins = new int[2];
		int[] minpos = new int[2];
		mins[0] = Math.abs(multipleValues[0] - (int)x);	//first value is the minimum, the second is the minimum if the first value didn't exist
		mins[1] = Math.abs(multipleValues[0] - (int)x);
		minpos[0] = 0;
		minpos[1] = 0;
	
		for(int i = 0; i < multipleValues.length; i++) {
			
			if (Math.abs(multipleValues[i] - (int)x) < mins[0]) {
				mins[0] = Math.abs(multipleValues[i] - (int)x);
				minpos[0] = i;
			}
			
		}
		
		for (int i = 0; i < multipleValues.length; i++) {
			
			if (Math.abs(multipleValues[i] - (int)x) < mins[1] && Math.abs(multipleValues[i] - (int)x) != mins[0] ) {
				mins[1] = Math.abs(multipleValues[i] - (int)x);
				minpos[1] = i;
			}
		}
		
		minpos[0] = minpos[0] * Piece.CELLDIM;
		minpos[1] = minpos[1] * Piece.CELLDIM;
		
		return minpos;
		
	}
	
	public int[] findNearestY(float y) {
		
		int[] multipleValues = new int[grid.length];
		
		for(int i = 0; i < grid.length; i++) {
			multipleValues[i] = i * Piece.CELLDIM;
		}
		
		int[] mins = new int[2];
		int[] minpos = new int[2];
		mins[0] = Math.abs(multipleValues[0] - (int)y);
		mins[1] = Math.abs(multipleValues[0] - (int)y);
		minpos[0] = 0;
		minpos[1] = 0;
		
		for(int i = 0; i < grid.length; i++) {
			if(Math.abs(multipleValues[i] - (int)y) < mins[0]) {
				mins[0] = Math.abs(multipleValues[i] - (int)y);
				minpos[0] = i;
			}
		}
		
		for(int i = 0; i < grid.length; i++) {
			if(Math.abs(multipleValues[i] - (int)y) < mins[1] && Math.abs(multipleValues[i] - (int)y) != mins[0]) {
				mins[1] = Math.abs(multipleValues[i] - (int)y);
				minpos[1] = i;
			}
		}
		
		minpos[0] *= Piece.CELLDIM;
		minpos[1] *= Piece.CELLDIM;
		
		return minpos;
		
	}
	
	public boolean canDivideYBy(int number, Coordinate[] coordinates) {
		
		for(Coordinate coordinate : coordinates) {
			
			if (coordinate.getY() % number != 0)
				return false;
			
		}
		
		return true;
	}
	
	public boolean isColliding(Piece piece) {
		Coordinate[] cells = piece.getCells();
		
		int yControl, xControl;
		int[] nearestX, nearestY;
		
		
		for(int i = 0; i < cells.length; i++) {
			
			nearestY = findNearestY(cells[i].getY());
			
			if(cells[i].getX() % Piece.CELLDIM == 0) {
				
				xControl = (int)cells[i].getX() / Piece.CELLDIM;
				yControl = (nearestY[1]) / Piece.CELLDIM;
			
				if(yControl < 21 && yControl > 0 && grid[yControl][xControl] > 0 && nearestY[1]> cells[i].getY()) {
					
					for(int j = 0; j < cells.length; j++) {
						nearestY = findNearestY(cells[j].getY());
						piece.setStopCoordinates(j, new Coordinate(cells[j].getX(), nearestY[1] - Piece.CELLDIM));
					}
					
					return true;
				}
				
			}
			else {
				
				nearestX = findNearestX(cells[i].getX());
				
				yControl = (nearestY[1]) / Piece.CELLDIM;
				
				if(yControl < 21 && yControl > 0 && (grid[yControl][nearestX[0] / Piece.CELLDIM] > 0 || grid[yControl][nearestX[1] / Piece.CELLDIM] > 0) && nearestY[1] > cells[i].getY()) {
					
					if(grid[yControl][nearestX[0] / Piece.CELLDIM] > 0) {
						
						for(int j = 0; j < cells.length; j++) {
							nearestY = findNearestY(cells[j].getY());
							nearestX = findNearestX(cells[j].getX());
							piece.setStopCoordinates(j, new Coordinate(nearestX[0], nearestY[1] - Piece.CELLDIM));
						}
						
						return true;
					}
					else if(grid[yControl][nearestX[1] / Piece.CELLDIM] > 0) {
						
						for(int j = 0; j < cells.length; j++) {
							nearestY = findNearestY(cells[j].getY());
							nearestX = findNearestX(cells[j].getX());
							piece.setStopCoordinates(j, new Coordinate(nearestX[1], nearestY[1] - Piece.CELLDIM));
						}
						
						return true;
					}
				}
			}
			
		}
		
		//CONFRONTO COL FONDO
		for(int i = 0; i < cells.length; i++) {
			
			if(cells[i].getX() % Piece.CELLDIM == 0) {
				
				if (cells[i].getY() >= 882 - Piece.CELLDIM) {
					
					for(int j = 0; j < cells.length; j++) {
						piece.setStopCoordinates(j, new Coordinate(cells[j].getX(), cells[j].getY()));
					}
					
					return true;
				}
				
			}
			else {
				
				if (cells[i].getY() >= 882 - Piece.CELLDIM) {
					
					for(int j = 0; j < cells.length; j++) {
						nearestX = findNearestX(cells[j].getY());
						piece.setStopCoordinates(j, new Coordinate(cells[j].getX(), cells[j].getY()));
					}
					
					return true;
				}
			}		
		}
		
		return false;
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop() {
		
		display.lost();
		
		if (!running) {
			return;
		}
		
		running = false;
		
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void tick() {
		
		Coordinate[] cells;
		Coordinate[] stopCoordinates;
		
		keyManager.tick();
		
		if(!isColliding(currentPiece)) {
			currentPiece.tick();
		}
		else {
			
			cells = currentPiece.getCells();
			stopCoordinates = currentPiece.getStopCoordinates();
			
			for(int i = 0; i < cells.length; i++)
				cells[i] = stopCoordinates[i];
			
			for(int i = 0; i < cells.length; i++) {
				if(cells[i].getY() < 0) {
					running = false;
				}
			}
			
			
			for(int i = 0; i < cells.length; i++) {
				if(cells[i].getY() >= 0) 
					grid[(int)cells[i].getY() / Piece.CELLDIM][(int)cells[i].getX() / Piece.CELLDIM] = currentPiece.getColor();
			}
			
			
			for(int i = 0; i < grid.length; i++) {
				if(controlGridLine(i))
					ptsToGive += 100;
			}
			
			pts += ptsToGive;
			ptsToGive = 0;
			display.getPoints().setText(String.valueOf(pts));

			currentPiece = nextPiece;
			nextPiece = new Piece(this);
		}
		
	}
	
	
	
	public void init() {
		
		currentPiece = new Piece(this);
		nextPiece = new Piece(this);
		
	}
	
	public void render() {
		
		Canvas c = display.getCanvas();
		Canvas cNext = display.getNextCanvas();
		bs = c.getBufferStrategy();
		bsNext = cNext.getBufferStrategy();
		
		if (bs == null) {
			c.createBufferStrategy(2);
			return;
		}
		
		if (bsNext == null) {
			cNext.createBufferStrategy(2);
			return;
		}
		
		g = bs.getDrawGraphics();
		gNext = bsNext.getDrawGraphics();
		
		g.clearRect(0, 0, c.getWidth(), c.getHeight());
		gNext.clearRect(0, 0, cNext.getHeight(), cNext.getWidth());
		
		currentPiece.render(g);
		gridRender(g);
		
		bs.show();
		bsNext.show();
		g.dispose();
		gNext.dispose();
		
	}
	
	public boolean controlGridLine(int line) {
		for (int i = 0; i < grid[line].length; i++) {
			if(grid[line][i] == 0)
				return false;
		}
		
		for(int i = line; i >= 1; i--) {
			for(int j = 0; j < grid[i].length; j++)
				grid[i][j] = grid[i-1][j];
		}
		
		for (int i = 0; i < grid[line].length; i++) 
			grid[0][i] = 0;
		
		return true;
	}

	@Override
	public void run() {
		
		init();
		
		int fps = 60; //Game is at 60fps
		double timePerTick = 1000000000/fps;  //1000000000 is 1 seconds in nanoseconds
		
		long now;
		long lastTime = System.nanoTime();
		double delta = 0;
		
		while(running) {
			
			lastTime = System.nanoTime();
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
			
		}
		
		stop();
	}

	
}
