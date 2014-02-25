import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends GameObject {
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