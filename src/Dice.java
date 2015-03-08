/*
 *  Author Wajih Ul Hassan, integrated/modified by Ioan Istrate
 *  2/24/2014
 *  Monopoly
 * 
 * */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class Dice{
	// =============== VARIABLES =================================
//	private JFrame d_frame;
	private JPanel m_wrapper;
	protected JPanel d_panel1;
	protected JPanel d_panel2;
	protected CustomImage d_image1;
	protected CustomImage d_image2;
	private  JLabel  diceLabel1;
	private  JLabel  diceLabel2;
	private JButton d_button;
	public final static int d_Delay = 100;
	Thread d_thread;
	public boolean loadingDice = true;
	
	private int m_irandom;
	boolean m_bdouble;
	boolean m_bupdated;
	
	// =============================================================
	
	// ======== CONSTRUCTOR ================================
	public Dice() throws InterruptedException {
		m_bdouble = false;
		m_wrapper = new JPanel();
		m_wrapper.setPreferredSize(new Dimension(189, 400));
		//  ------ Creating Panel ---------------------- 
		d_panel1 = new JPanel();
		d_panel2 = new JPanel();
		m_wrapper.add(d_panel1,BorderLayout.NORTH);
		m_wrapper.add(d_panel2,BorderLayout.SOUTH);
		m_wrapper.setBackground(Color.black);
		// ----------------------------------------------
		//creates image
		d_image1 = new CustomImage("assets/diceSprite.png", 189, 199,0, 0);
		d_image2 = new CustomImage("assets/diceSprite.png", 189, 199,0, 0);
		//set panel size
		
		d_panel1.setPreferredSize(new Dimension(189, 199));
		d_panel2.setPreferredSize(new Dimension(189, 199));
		//adds image to panel
		diceLabel1 = new JLabel(new ImageIcon(d_image1.getImage()));
		diceLabel1.setHorizontalAlignment(JLabel.CENTER);
		diceLabel1.setVerticalAlignment(JLabel.CENTER);
		diceLabel2 = new JLabel(new ImageIcon(d_image2.getImage()));
		diceLabel2.setHorizontalAlignment(JLabel.CENTER);
		diceLabel2.setVerticalAlignment(JLabel.CENTER);
		//================== Button ======================
		d_button = new JButton(" ROLL ");
		d_button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  //
				  Random rd=new Random();
				  int rnd1 = rd.nextInt(6)+1;
			      Random rd2=new Random();
			      int rnd2 = rd2.nextInt(6)+1;
			      if (rnd1 == rnd2) {
			    	  m_bdouble = true;
			      }
			      else {
			    	  m_bdouble = false;
			      }
			      m_irandom = rnd1 + rnd2;
				  DiceThread dice1 = new DiceThread(diceLabel1,d_button,rnd1, loadingDice);
				  DiceThread dice2 = new DiceThread(diceLabel2,d_button,rnd2, loadingDice);
				  loadingDice = false;
				  m_bupdated = true;
				  d_button.setEnabled(false);
				  } 
				} );
		//================================================
		// ----------------------------------------
		m_wrapper.setLayout(new BorderLayout());
		m_wrapper.add(diceLabel1,BorderLayout.NORTH);
		m_wrapper.add(diceLabel2,BorderLayout.CENTER);
		m_wrapper.add(d_button,BorderLayout.SOUTH);
		
		d_button.doClick();
		//------------------------------------------	
	}
public JPanel returnPanel() {
	return m_wrapper;
}
public int getRandom() {
	return m_irandom;
}
public boolean isUpdated() {
	return m_bupdated;
}
public void setIsUpdated(boolean b) {
	m_bupdated = b; 
}
public boolean isDouble() {
	return m_bdouble;
}

}