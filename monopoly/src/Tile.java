/*
 * Tile as a game object; loads image to label and label to panel
 */
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Tile extends GameObject {
	private int squarePosition = 0;
	private int squarePrice = 0;
	private int squareRent = 0;
	
	Tile(int w, int h, int x, int y, int sX, int sY, int inBoardSqaure) {
		//initialize game objects members
		squarePosition = inBoardSqaure;
		m_w = w;
		m_h = h;
		m_x = x;
		m_y = y;
		m_panel = new JPanel();
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
	
	public int getBoardSquare() {
		return squarePosition;
	}
	public void setBoardSquare(int boardSquare) {
		this.squarePosition = boardSquare;
	}
}