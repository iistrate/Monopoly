/*
 * Author: Jay Bingley
 */

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends GameObject {
	private int m_iplayermoney = 1500;
	private boolean m_binjail = false;
	
	
	protected boolean isM_binjail() {
		return m_binjail;
	}


	protected void setM_binjail(boolean b) {
		m_binjail = b;
	}


	protected int getM_iplayercash() {
		return m_iplayermoney;
	}


	protected void setM_iplayercash(int cash) {
		m_iplayermoney = m_iplayermoney + cash;
	}


	Player(int w, int h, int x, int y, int sX, int sY) {
		// initialize game objects members
		m_w = w;
		m_h = h;
		m_x = x;
		m_y = y;
		m_panel = new JPanel();
		// creates image
		m_image = new CustomImage("assets/sprites-player.png", w, h, sX, sY);
		// set panel size
		m_panel.setPreferredSize(new Dimension(w, h));
		// adds image to panel
		m_panel.add(new JLabel(new ImageIcon(m_image.getImage())));
		m_panel.setOpaque(false);
	}
}