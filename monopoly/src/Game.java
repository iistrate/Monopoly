/*
 * Contains Frame; Game Loop
 */

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	//member declarations
	private boolean m_brunning;
	private JFrame m_frame;
	private JPanel m_wrapper;
	
	// tilelist created in world constructor
	World world = new World();
	
	
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
			setuptiles();
		}
		while (m_brunning);
	}
	//stops game loop and clears resources
	void quit() {
		m_frame.dispose();
		m_brunning = false;
	}

	// add tiles from tilelist in world.java to current frame
	void setuptiles(){
		// temp tile object
		Tile temptile = null;
		// temp tilelist 
		ArrayList<Tile> tilelist = new ArrayList<Tile>();
		tilelist = world.getTilelist();
		for (int i = 0; i < tilelist.size(); i++){
			//get current tile from list
			temptile = tilelist.get(i);
			//add tile to frame
			m_wrapper.add(temptile.getPanel());
		}
	}
}