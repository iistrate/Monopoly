/*
 * Contains Frame; Game Loop
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	//member declarations
	private boolean m_brunning;
	private JFrame m_frame;
	private JPanel m_wrapper;
	private JPanel m_board;
	private GridBagConstraints m_constraints;
	
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
		m_board = new JPanel(new GridBagLayout());
		m_constraints = new GridBagConstraints();
		m_constraints.fill = GridBagConstraints.BOTH;
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
			m_board.setPreferredSize(new Dimension(900, 897));
			m_wrapper.add(m_board);
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
			if (i == 0) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 0;
			}
			if (i == 1) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 10;
			}
			if (i == 2) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 10;
			}
			if (i == 3) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 0;				
			}
			if (i == 4) {
				m_constraints.gridx = 1;
				m_constraints.gridy = 0;				
			}
			if (i == 5) {
				m_constraints.gridx = 1;
				m_constraints.gridy = 10;				
			}
			if (i == 6) {
				m_constraints.gridx = 2;
				m_constraints.gridy = 0;				
			}
			if (i == 7) {
				m_constraints.gridx = 2;
				m_constraints.gridy = 10;				
			}
			if (i == 8) {
				m_constraints.gridx = 3;
				m_constraints.gridy = 0;				
			}
			if (i == 9) {
				m_constraints.gridx = 3;
				m_constraints.gridy = 10;				
			}
			if (i == 10) {
				m_constraints.gridx = 4;
				m_constraints.gridy = 0;				
			}
			if (i == 11) {
				m_constraints.gridx = 4;
				m_constraints.gridy = 10;				
			}
			if (i == 12) {
				m_constraints.gridx = 8;
				m_constraints.gridy = 0;				
			}
			if (i == 13) {
				m_constraints.gridx = 5;
				m_constraints.gridy = 10;				
			}
			if (i == 14) {
				m_constraints.gridx = 6;
				m_constraints.gridy = 0;				
			}
			if (i == 15) {
				m_constraints.gridx = 6;
				m_constraints.gridy = 10;				
			}
			if (i == 16) {
				m_constraints.gridx = 7;
				m_constraints.gridy = 0;				
			}
			if (i == 17) {
				m_constraints.gridx = 7;
				m_constraints.gridy = 10;				
			}
			if (i == 18) {
				m_constraints.gridx = 5;
				m_constraints.gridy = 0;				
			}
			if (i == 19) {
				m_constraints.gridx = 8;
				m_constraints.gridy = 10;				
			}
			if (i == 20) {
				m_constraints.gridx = 9;
				m_constraints.gridy = 0;				
			}
			if (i == 21) {
				m_constraints.gridx = 9;
				m_constraints.gridy = 10;				
			}
			if (i == 22) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 1;				
			}
			if (i == 23) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 1;				
			}
			if (i == 24) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 2;				
			}
			if (i == 25) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 2;				
			}
			if (i == 26) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 3;				
			}
			if (i == 27) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 3;				
			}
			if (i == 28) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 4;				
			}
			if (i == 29) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 4;				
			}
			if (i == 30) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 5;				
			}
			if (i == 31) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 5;				
			}
			if (i == 32) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 6;				
			}
			if (i == 33) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 6;				
			}
			if (i == 34) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 7;				
			}
			if (i == 35) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 7;				
			}
			if (i == 36) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 8;				
			}
			if (i == 37) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 8;				
			}
			if (i == 38) {
				m_constraints.gridx = 0;
				m_constraints.gridy = 9;				
			}
			if (i == 39) {
				m_constraints.gridx = 10;
				m_constraints.gridy = 9;				
			}			
			m_board.add(temptile.getPanel(), m_constraints);
		}
	}
}