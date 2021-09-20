package dev.bahamut.tetris.display;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Canvas;

public class Display extends JFrame {

	private JPanel contentPane;
	JPanel menuPanel = new JPanel();
	JLabel numberLabel = new JLabel("0");
	JPanel nextPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JLabel lostLabel = new JLabel("You Lost!");
	Canvas canvas;
	Canvas nextCanvas;

	public Display() {
		setFont(new Font("Dialog", Font.PLAIN, 24));
		setTitle("Tetris");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 945);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		menuPanel.setPreferredSize(new Dimension(490, 0));
		menuPanel.setBounds(new Rectangle(0, 0, 300, 0));
		contentPane.add(menuPanel, BorderLayout.WEST);
		menuPanel.setLayout(null);
		
		JLabel pointsLabel = new JLabel("Points:");
		pointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pointsLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		pointsLabel.setBounds(120, 31, 88, 52);
		menuPanel.add(pointsLabel);
		
		numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numberLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		numberLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		numberLabel.setFont(new Font("Centaur", Font.PLAIN, 22));
		numberLabel.setBounds(64, 94, 144, 27);
		menuPanel.add(numberLabel);
		
		JLabel nextLabel = new JLabel("NEXT");
		nextLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		nextLabel.setBounds(64, 177, 119, 33);
		menuPanel.add(nextLabel);
		
		nextPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		nextPanel.setBounds(40, 218, 126, 107);
		menuPanel.add(nextPanel);
		
		lostLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		lostLabel.setBounds(40, 366, 126, 33);
		lostLabel.setVisible(false);
		menuPanel.add(lostLabel);
		gamePanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	
		gamePanel.setPreferredSize(new Dimension(640, 900));
		gamePanel.setSize(new Dimension(630, 900));
		contentPane.add(gamePanel, BorderLayout.EAST);
		
		canvas = new Canvas();
		canvas.setLocation(new Point(0,0));
		canvas.setSize(new Dimension(630, 882));
		canvas.setPreferredSize(new Dimension(630, 882));
		canvas.setMinimumSize(new Dimension(630, 882));
		canvas.setMaximumSize(new Dimension(630, 882));
		canvas.setFocusable(false);
		
		gamePanel.add(canvas);
		
		nextCanvas = new Canvas();
		nextCanvas.setLocation(new Point(0,0));
		nextCanvas.setSize(new Dimension(115, 92));
		nextCanvas.setPreferredSize(new Dimension(115, 92));
		nextCanvas.setMinimumSize(new Dimension(115, 92));
		nextCanvas.setMaximumSize(new Dimension(115, 92));
		nextCanvas.setFocusable(false);
		
		nextPanel.add(nextCanvas);
		
		
		
		this.setVisible(true);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public Canvas getNextCanvas() {
		return nextCanvas;
	}
	
	public JLabel getPoints() {
		return numberLabel;
	}
	
	public void lost() {
		lostLabel.setVisible(true);
	}
}
