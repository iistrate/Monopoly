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
	private JFrame d_frame;
	protected JPanel d_panel;
	protected CustomImage d_image;
	private  JLabel  diceLabel;
	private JButton d_button;
	public final static int d_Delay = 100;
	Thread d_thread;
	// =============================================================
	
	// ======== CONSTRUCTOR ================================
	public Dice() throws InterruptedException {
		// ----------- FRAME Initialization ------------
		d_frame = new JFrame("Dice");
		d_frame.setSize(600, 600);
		d_frame.setVisible(true);
		d_frame.setName("DICE");
		d_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//  ------ Creating Panel ---------------------- 
		d_panel = new JPanel();
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
				  new DiceThread(diceLabel,d_button);
				  d_button.setEnabled(false);
				  } 
				} );
		//================================================
		// ----------------------------------------
		d_frame.setLayout(new BorderLayout());
		d_frame.add(diceLabel);
		d_frame.add(d_button,BorderLayout.SOUTH);
		d_button.doClick();
		d_frame.pack();
		d_frame.setLocationRelativeTo(null);
		//------------------------------------------	
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