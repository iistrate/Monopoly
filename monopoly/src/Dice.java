/*
 *  Wajih Ul Hassan
 *  2/24/2014
 *  Monopoly
 * 
 * */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Dice{
	// =============== VARIABLES =================================
//	private JFrame d_frame;
	private JPanel m_wrapper;
	protected JPanel d_panel;
	protected CustomImage d_image;
	private  JLabel  diceLabel;
	private JButton d_button;
	public final static int d_Delay = 100;
	Thread d_thread;
	
	private int m_irandom;
	boolean m_bupdated;
	
	// =============================================================
	
	// ======== CONSTRUCTOR ================================
	public Dice() throws InterruptedException {
		// ----------- FRAME Initialization ------------
//		d_frame = new JFrame("Dice");
//		d_frame.setSize(600, 600);
//		d_frame.setVisible(true);
//		d_frame.setName("DICE");
//		d_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		m_wrapper = new JPanel();
		m_wrapper.setPreferredSize(new Dimension(189, 300));
		//  ------ Creating Panel ---------------------- 
		d_panel = new JPanel();
		m_wrapper.add(d_panel);
		m_wrapper.setBackground(Color.black);
		// ----------------------------------------------
		//creates image
		d_image = new CustomImage("assets/diceSprite.png", 189, 199,0, 0);
		
		//set panel size
		d_panel.setPreferredSize(new Dimension(189, 199));
		
		//adds image to panel
		diceLabel = new JLabel(new ImageIcon(d_image.getImage()));
		diceLabel.setHorizontalAlignment(JLabel.CENTER);
		diceLabel.setVerticalAlignment(JLabel.CENTER);
		
		//================== Button ======================
		d_button = new JButton(" ROLL ");
		d_button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  //
				  DiceThread temp =  new DiceThread(diceLabel,d_button);
				  m_irandom = temp.getRandom();
				  m_bupdated = true;
				  d_button.setEnabled(false);
				  } 
				} );
		//================================================
		// ----------------------------------------
		m_wrapper.setLayout(new BorderLayout());
		m_wrapper.add(diceLabel);
		m_wrapper.add(d_button,BorderLayout.SOUTH);
		
//		d_frame.setLayout(new BorderLayout());
//		d_frame.add(diceLabel);
//		d_frame.add(d_button,BorderLayout.SOUTH);
		d_button.doClick();
//		d_frame.pack();
//		d_frame.setLocationRelativeTo(null);
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
// ======================== MAIN ======================================
public static void main(String[] args) throws InterruptedException {
	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             try {
				new Dice();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
         }
     });
	}//end main
// =====================================================================

}