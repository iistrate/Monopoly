/*
 * Contains Frame; Game Loop
 */

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	//member declarations
	private boolean m_brunning;
	private JFrame m_frame;
	private JPanel m_wrapper;
	
	//does not belong here move in world, gui class incomming (world draws game world; gui draws gui)
	
	Tile tst = new Tile(115, 115, 30, 30, 780, 780);
	Tile tst2 = new Tile(115, 115, 30, 30, 0, 0);
	
	//constructor and initialization
	Game(int w, int h, String name) {
		m_brunning = false;
		m_frame = new JFrame();
		m_frame.setSize(w, h);
		m_frame.setVisible(true);
		m_frame.setName(name);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_wrapper = new JPanel();
	}
	//condition for game loop set to true
	void init() {
		m_brunning = true;
	}
	//start game loop
	void run() {
		do {
			m_frame.add(m_wrapper);
			m_wrapper.setBackground(Color.black);
			//we add the tiles like this
			m_wrapper.add(tst.getPanel());
			m_wrapper.add(tst2.getPanel());
			m_brunning = false;
		}
		while (m_brunning);
	}
	//stops game loop and clears resources
	void quit() {
		m_frame.dispose();
		m_brunning = false;
	}
}