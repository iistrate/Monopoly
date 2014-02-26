/*
 * Contains Frame; Game Loop
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Game {
	//member declarations
	private boolean m_brunning;
	private JFrame m_frame;
	private JPanel m_wrapper;
	private JPanel m_board;
	private JPanel m_topLayer;
	private GridBagConstraints m_constraints;
	private Dice dice;
	// tile list created in world constructor
	World world = new World();
	boolean m_bupdated;
	int m_imovement;
	
	//constructor and initialization
	Game(int w, int h, String name) {
		m_brunning = false;
		try {
			dice = new Dice();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_frame = new JFrame();
		m_frame.setSize(w, h);
		m_frame.setVisible(true);
		m_frame.setName(name);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_wrapper = new JPanel();
		m_board = new JPanel(new GridBagLayout());
		m_topLayer = new JPanel();
		m_constraints = new GridBagConstraints();
		m_constraints.fill = GridBagConstraints.BOTH;
		m_bupdated = false;
		m_imovement = 0;
	}
	//condition for game loop set to true
	void init() {
		m_brunning = true;
	}
	//start game loop
	void run() {
		m_frame.add(m_wrapper);
		m_wrapper.setBackground(Color.black);
		m_board.setPreferredSize(new Dimension(900, 897));
		m_board.setVisible(true);
		m_board.setBackground(Color.black);
		m_wrapper.add(m_board);
		m_wrapper.add(m_topLayer);
		m_topLayer.add(dice.returnPanel());
		setupplayers();
		setuptiles();
		m_frame.revalidate();
		do {
			m_bupdated = dice.isUpdated();
			if (m_bupdated) {
				//do movement would go here
				m_imovement = dice.getRandom();
				
				//when movement is done set false
				dice.setIsUpdated(false);
			}
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
			m_constraints.gridx = temptile.getX();
			m_constraints.gridy = temptile.getY();
			//ugly fix for ugly margin; treated symptom not causes
			m_constraints.insets = new Insets(-5, 0, 0, 0);
			if (i == 40) {
				m_constraints.gridheight = 9;
				m_constraints.gridwidth = 9;	
			}
			m_board.add(temptile.getPanel(), m_constraints);
			m_board.validate();
		}
	}
	// add players from player list in world.java to current frame
		void setupplayers() {
			// temp Player object
			Player tempplayer = null;
			// temp player list
			ArrayList<Player> playerlist = new ArrayList<Player>();
			playerlist = world.getPlayerlist();
			for (int i = 0; i < playerlist.size(); i++){
				//get current tile from list
				tempplayer = playerlist.get(i);
				tempplayer.getPanel().setVisible(true);
				m_constraints.gridx = tempplayer.getX();
				m_constraints.gridy = tempplayer.getY();
				m_constraints.gridheight = 1;
				m_constraints.gridwidth = 1;	
				m_board.add(tempplayer.getPanel(), m_constraints);
				m_board.validate();
			}
		}
}