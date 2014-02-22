import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Tile extends JPanel implements GameObject {
	private JPanel m_panel = new JPanel();
	private CustomImage m_image;
	
	Tile(String filename, int w, int h, int x, int y) {
		m_image = new CustomImage(filename, w, h, x, y);
		m_panel.setPreferredSize(new Dimension(w, h));
		m_panel.add(new JLabel(new ImageIcon(m_image.getImage())));
	}
	//tests
	public JPanel getTile() {
		return m_panel;
	}
}