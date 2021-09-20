package dev.bahamut.tetris.input;

import java.awt.event.*;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean right, left, down, rotate;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];
		down = keys[KeyEvent.VK_S];
		rotate = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	
}
