/*
 * Tile as a game object; loads image to label and label to panel
 */
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Tile extends GameObject {
	Property m_property;
	Tile(int w, int h, int x, int y, int sX, int sY, boolean canBeBought) {
		//initialize game objects members
		m_w = w;
		m_h = h;
		m_x = x;
		m_y = y;
		m_panel = new JPanel();
		if (canBeBought) {
			if (y == 0) {
				if (x == 1) {
					m_property = new Property()
					.Available(true).Name("Strand").Value(220);
				}
				else if (x == 3) {
					m_property = new Property()
					.Available(true).Name("Fleet Street").Value(220);
				}
				else if (x == 4) {
					m_property = new Property()
					.Available(true).Name("Trafalguar Square").Value(240);
				}
				else if (x == 5) {
					m_property = new Property()
					.Available(true).Name("Fenchurch Street Station").Value(200);
				}
				else if (x == 6) {
					m_property = new Property()
					.Available(true).Name("Leicester Square").Value(260);
				}
				else if (x == 7) {
					m_property = new Property()
					.Available(true).Name("Coventry Street").Value(260);
				}
				else if (x == 8) {
					m_property = new Property()
					.Available(true).Name("Water Works").Value(150);
				}
				else if (x == 9) {
					m_property = new Property()
					.Available(true).Name("Picadilly").Value(280);
				}
			}
		}
		//creates image
		m_image = new CustomImage("assets/sprites-board.jpg", w, h, sX, sY);
		//set panel size
		m_panel.setPreferredSize(new Dimension(w, h));
		//adds image to panel
		ImageIcon img = new ImageIcon(m_image.getImage());
		JLabel label = new JLabel(img);
		img.getIconHeight();
		m_panel.add(label);
		m_panel.setOpaque(false);
	}
}