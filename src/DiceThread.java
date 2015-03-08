/*
 *  Wajih Ul Hassan
 *  2/24/2014
 *  Monopoly
 * 
 * */

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class DiceThread extends Thread {
	// -------------------------------
	private boolean loadingDice;
	private CustomImage d_image;
	private  JLabel  diceLabel;
	private JButton d_button;
	private int d_random;
	private int d_Delay; // Delay between animation
	//================================
	
	   DiceThread(JLabel jl, JButton jb,int j, boolean ld) {
		      super("Dice Thread");
		      loadingDice = ld;
		      diceLabel = jl;
		      d_button = jb;
		      d_Delay = 40;
		      d_random = j;
		      start(); // Start the thread
		   }
 public void run() {

	        if (!loadingDice){
	        // Creating Animation ==============================      
	        int tmp = 0;
			tmp = tmp +189;  // offset for image 
			
			// First Row of Sprite //
			for(int i=0 ;i<4 ;i++){
				d_image = new CustomImage("assets/diceSprite.png", 189, 199,tmp, 0);
				tmp = tmp +189;
				diceLabel.setIcon(new ImageIcon(d_image.getImage()));
				try {
					Thread.sleep(d_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			// Second Row of Sprite //
			tmp =0;
			for(int i=0 ;i<5 ;i++){
				d_image = new CustomImage("assets/diceSprite.png", 189,199,tmp, 199);
				tmp = tmp +189;
				diceLabel.setIcon(new ImageIcon(d_image.getImage()));
				try {
					Thread.sleep(d_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			// Third Row of Sprite //
			tmp =0;
			for(int i=0 ;i<5 ;i++){
				d_image = new CustomImage("assets/diceSprite.png", 189, 199,tmp, 398);
				tmp = tmp +189;
				diceLabel.setIcon(new ImageIcon(d_image.getImage()));
				try {
					Thread.sleep(d_Delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        }
			//  End of Sprite    //
		//========= Displaying last result on DICE =======================
	        switch(d_random){
            case 1:
            	d_image = new CustomImage("assets/1.png", 189, 199,0, 0);
                break;
            case 2:
            	d_image = new CustomImage("assets/2.png", 189, 199,0, 0);
                break;
            case 3:
            	d_image = new CustomImage("assets/3.png", 189, 199,0, 0);
                break;
            case 4:
            	d_image = new CustomImage("assets/4.png", 189, 199,0, 0);
                break;
            case 5:
            	d_image = new CustomImage("assets/5.png", 189, 199,0, 0);
                break;
            case 6:
            	d_image = new CustomImage("assets/6.png", 189, 199,0, 0);
                break;
        }
	        diceLabel.setIcon(new ImageIcon(d_image.getImage()));
			
			d_button.setEnabled(true);
	// ========================================================================
 	}
 // === Getter for random number generator ===================
   public int getRandom(){
	  // System.out.println("IN dice function = "+  d_random);
	   return d_random;	
	  
   }
 //============================================================
	  
}



