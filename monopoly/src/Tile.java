/*
 * Tile as a game object; loads image to label and label to panel
 */
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Tile extends GameObject {
	Property m_property = new Property().Available(false);
	Tile(int w, int h, int x, int y, int sX, int sY, boolean canBeBought) {
		//initialize game objects members
		m_w = w;
		m_h = h;
		m_x = x;
		m_y = y;
		m_panel = new JPanel();
		if (canBeBought) {
			//top row
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
			} // end top row
			//bottom row
			else if (y == 10) {
				if (x == 1) {
					m_property = new Property()
					.Available(true).Name("PentonVille Road").Value(120);
				}
				else if (x == 2) {
					m_property = new Property()
					.Available(true).Name("Euston Road").Value(100);
				}
				else if (x == 4) {
					m_property = new Property()
					.Available(true).Name("The Angelislington").Value(100);
				}
				else if (x == 5) {
					m_property = new Property()
					.Available(true).Name("King Cross Station").Value(200);
				}
				else if (x == 7) {
					m_property = new Property()
					.Available(true).Name("White Chaper Road").Value(60);
				}
				else if (x == 9) {
					m_property = new Property()
					.Available(true).Name("Old Kent Road").Value(60);
				}
			} // end bottom row
			// left row
			else if (x == 0) {
				if (y == 1) {
					m_property = new Property()
					.Available(true).Name("Vine Street").Value(200);					
				}
				else if (y == 2) {
					m_property = new Property()
					.Available(true).Name("Malborough Street").Value(180);						
				}
				else if (y == 4) {
					m_property = new Property()
					.Available(true).Name("Bow Street").Value(180);						
				}
				else if (y == 5) {
					m_property = new Property()
					.Available(true).Name("Marylebone Station").Value(200);						
				}
				else if (y == 6) {
					m_property = new Property()
					.Available(true).Name("Northumr'land Street").Value(160);						
				}
				else if (y == 7) {
					m_property = new Property()
					.Available(true).Name("Whithall Street").Value(140);						
				}
				else if (y == 8) {
					m_property = new Property()
					.Available(true).Name("Electric Company").Value(150);						
				}
				else if (y == 9) {
					m_property = new Property()
					.Available(true).Name("Pall Mall").Value(140);						
				}
			} // end left row
			// right row
			else if (x == 10) {
				if (y == 1) {
					m_property = new Property()
					.Available(true).Name("Regent Street").Value(300);					
				}
				else if (y == 2) {
					m_property = new Property()
					.Available(true).Name("Oxford Street").Value(300);						
				}
				else if (y == 4) {
					m_property = new Property()
					.Available(true).Name("Bond Street").Value(320);						
				}
				else if (y == 5) {
					m_property = new Property()
					.Available(true).Name("Liverpool St. Station").Value(200);						
				}
				else if (y == 7) {
					m_property = new Property()
					.Available(true).Name("Park Lane").Value(350);						
				}
				else if (y == 9) {
					m_property = new Property()
					.Available(true).Name("MayFair").Value(400);						
				}		
			}//end right row
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