/*
 * Holds our game object members and interface
 */
import javax.swing.JPanel;

public class GameObject {
	protected JPanel m_panel;
	protected CustomImage m_image;
	protected int m_x;
	protected int m_y;
	protected int m_h;
	protected int m_w;

	//setters
	public void setX(int x) {
		m_x = x;
	}
	public void setY(int y) {
		m_y = y;
	}
	public void setW(int w) {
		m_w = w;
	}
	public void setH(int h) {
		m_h = h;
	}	
	//getters
	public int getX() {
		return m_x;
	}
	public int getY() {
		return m_y;
	}
	public int getW() {
		return m_w;
	}
	public int getH() {
		return m_h;
	}
	//return panel
	public JPanel getPanel() {
		return m_panel;
	}
}