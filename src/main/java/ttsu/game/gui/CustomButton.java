package main.java.ttsu.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class CustomButton extends JComponent implements MouseListener {
	
	public String text = "";
	
	public final int arc;
		
	public Color borderColor = Color.BLACK;
	public Color mainColor = Color.WHITE;
	public Color textColor = Color.BLACK;
	
	private Font buttonFont = new Font("Segoe UI", Font.BOLD, 22);
	
	private boolean oneClick, clicked = false;
	
	public CustomButton(int x, int y, int width, int height) {
		super();
		setBounds(x, y, width, height);
		arc = Math.max(2, (int)(width*0.1));
		
		enableInputMethods(true);
		addMouseListener(this);
	}

	public CustomButton(String buttonText, int x, int y, int width, int height) {
		super();
		setBounds(x, y, width, height);
		arc = Math.max(2, (int)(width*0.1));
		text = buttonText;
		
		enableInputMethods(true);
		addMouseListener(this);
	}
	
	public boolean toggleOneClick() {
		oneClick = !oneClick;
		return oneClick;
	}
	
	public void unclick() {
		clicked = false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(borderColor);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
		g.setColor(mainColor);
		g.fillRoundRect(3, 3, getWidth()-6, getHeight()-6, arc, arc);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(textColor);
		g2.setFont(buttonFont);
		FontRenderContext frc = g2.getFontRenderContext( );
		Rectangle2D boundingBox = buttonFont.getStringBounds(text, frc);
		
		g2.drawString(text, (int)(getWidth()/2 - boundingBox.getWidth()/2), (int)(getHeight()/2 + boundingBox.getHeight()/3));
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mainColor = Color.LIGHT_GRAY;
		repaint();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mainColor = Color.WHITE;
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mainColor = Color.GRAY;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mainColor = Color.LIGHT_GRAY;
		repaint();

	}

}
