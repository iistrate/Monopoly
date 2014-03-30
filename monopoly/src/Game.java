/*
 * Contains Frame; Game Loop
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Game {
	// member declarations
	private boolean m_brunning;
	private boolean m_bupdated;
	private int m_imovement;
	private int m_iplayerNr;
	private int m_iturn;
	private int m_igameTurn;

	// layout
	private JFrame m_frame;
	private JPanel m_wrapper;
	private JPanel m_board;
	private JPanel m_topLayer;
	private JPanel m_players;
	private JPanel m_info;
	private JPanel m_propertyInfo;
	private GridBagConstraints m_constraints;

	// info
	private JLabel m_topInfo;
	private JLabel m_centerInfo;

	// Dice
	private Dice dice;

	// tile list created in world constructor
	World world = new World();

	// constructor and initialization
	public Game(int w, int h, String name) {
		m_brunning = false;
		try {
			dice = new Dice();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Layout init
		 */
		// frame
		m_frame = new JFrame();
		m_frame.setSize(w, h);
		m_frame.setVisible(true);
		m_frame.setName(name);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// panels
		m_wrapper = new JPanel();
		m_board = new JPanel(new GridBagLayout());
		m_topLayer = new JPanel();
		m_info = new JPanel();
		m_players = new JPanel();
		m_propertyInfo = new JPanel();

		// labels
		m_topInfo = new JLabel();
		m_centerInfo = new JLabel();

		// constraints
		m_constraints = new GridBagConstraints();
		m_constraints.fill = GridBagConstraints.BOTH;

		/*
		 * Member vars init
		 */
		m_bupdated = false;
		m_imovement = 0;
		m_iplayerNr = 0;
		m_iturn = 1;
		m_igameTurn = 1;
	}

	// condition for game loop set to true
	public void init() {
		m_brunning = true;
		m_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		m_frame.add(m_wrapper);
		m_wrapper.setBackground(Color.black);
		m_board.setPreferredSize(new Dimension(900, 897));
		m_board.setVisible(true);
		m_board.setBackground(Color.black);
		m_wrapper.add(m_board);
		m_wrapper.add(m_topLayer);
		setuptiles();
		setupplayers();
	}

	// start game loop
	public void run() {
		int p_x = 0;
		int p_y = 0;
		Tile playerTile = new Tile();
	
		buildGUI();
		do {
			m_bupdated = dice.isUpdated();
			if (m_bupdated) {
				p_x = world.getPlayerlist().get(m_iturn - 1).getX();
				p_y = world.getPlayerlist().get(m_iturn - 1).getY();
				playerTile = world.getTile(p_x, p_y);
				m_propertyInfo.add(new JLabel("Name: " + playerTile.getProperty().getName()));
				
				m_imovement = dice.getRandom();
				// if player isn't in jail or player is in jail and rolls
				// doubles go ahead and move player
				if (!world.isplayerinjail(m_iturn)
						|| world.isplayerinjail(m_iturn) && dice.isDouble())
					world.movePlayer(m_imovement, m_iturn);
				if (world.isplayerinjail(m_iturn) && dice.isDouble()) {
					world.playerleavejail(m_iturn);
				}
				// when movement is done set false
				dice.setIsUpdated(false);
				drawComponents();
				// increment player turn
				if (m_iturn < m_iplayerNr) {
					// increase turn
					m_iturn++;
				} else {
					m_iturn = 1;
					m_igameTurn++;
				}
				m_topInfo.setText("Game turn: " + m_igameTurn + " | Player "
						+ m_iturn + " | Cash " + getcurrentplayer(m_iturn).getM_iplayercash());
			}
		} while (m_brunning);
	}

	// stops game loop and clears resources
	public void quit() {
		m_frame.dispose();
		m_brunning = false;
	}

	public Player getcurrentplayer(int inCurrentplayer) {
		inCurrentplayer = inCurrentplayer - 1;
		// temp Player object
		Player tempplayer = null;
		// temp player list
		ArrayList<Player> playerlist = new ArrayList<Player>();
		playerlist = world.getPlayerlist();
		for (int i = 0; i < playerlist.size(); i++) {
			// get current tile from list
			if (i == inCurrentplayer){
			tempplayer = playerlist.get(i);
			}
		}
		return tempplayer;
	}

	// add tiles from tilelist in world.java to current frame
	public void setuptiles() {
		// temp tile object
		Tile temptile = null;
		// temp tilelist
		ArrayList<Tile> tilelist = new ArrayList<Tile>();
		tilelist = world.getTilelist();
		for (int i = 0; i < tilelist.size(); i++) {
			// get current tile from list
			temptile = tilelist.get(i);
			m_constraints.gridx = temptile.getX();
			m_constraints.gridy = temptile.getY();
			// ugly fix for ugly margin; treated symptom not causes
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
	public void setupplayers() {
		// temp Player object
		Player tempplayer = null;
		// temp player list
		ArrayList<Player> playerlist = new ArrayList<Player>();
		playerlist = world.getPlayerlist();
		// count nr of players
		m_iplayerNr = playerlist.size();
		for (int i = 0; i < playerlist.size(); i++) {
			// get current tile from list
			tempplayer = playerlist.get(i);
			tempplayer.getPanel().setVisible(true);
			m_constraints.gridx = tempplayer.getX();
			m_constraints.gridy = tempplayer.getY();
			m_constraints.gridheight = 1;
			m_constraints.gridwidth = 1;
			// m_players.add(tempplayer.getPanel());
			m_board.add(tempplayer.getPanel(), m_constraints);
			m_board.validate();

			// update player info
			//m_centerInfo.setText("Cash: " + tempplayer.getM_iplayercash());
		}
	}

	public void drawComponents() {
		// clear board
		m_board.removeAll();
		m_board.updateUI();
		// redraw board
		setupplayers();
		setuptiles();
		m_frame.revalidate();
	}

	public void buildGUI() {
		m_topLayer.setPreferredSize(new Dimension(300, 840));
		m_topLayer.setLayout(new BorderLayout());
		m_topLayer.setBackground(Color.black);
		m_info.setPreferredSize(new Dimension(300, 430));
		m_info.setBackground(Color.gray);
		m_info.setBorder(new EmptyBorder(10, 10, 10, 10));
		m_topLayer.add(dice.returnPanel(), BorderLayout.NORTH);
		m_topLayer.add(m_info, BorderLayout.SOUTH);
		m_info.add(m_topInfo);
		m_info.add(m_centerInfo);
		m_info.add(m_propertyInfo);
	}
}