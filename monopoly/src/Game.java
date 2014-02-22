import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	private boolean m_brunning = false;
	private JFrame m_frame = new JFrame();
	private JPanel m_wrapper = new JPanel(new BorderLayout());
	private Tile tst = new Tile("assets/sprites-board.jpg", 115, 115, 780, 780);
	
	Game(int w, int h, String name) {
		m_frame.setSize(w, h);
		m_frame.setVisible(true);
		m_frame.setName(name);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void init() {
		m_brunning = true;
	}
	//get all panels and add them to frame
	void run() {
		do {
			m_frame.add(m_wrapper);
			m_wrapper.setBackground(Color.black);
			m_wrapper.add(tst.getTile());
			m_brunning = false;
		}
		while (m_brunning);
	}
	void quit() {
		m_frame.dispose();
		m_brunning = false;
	}
}