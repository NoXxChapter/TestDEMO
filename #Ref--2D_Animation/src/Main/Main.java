package Main;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		GamePanel panel = new GamePanel();
		
		
		
		
		frame.add(panel);
		frame.pack();
		
		frame.setTitle("2D_Animation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of application
		frame.setResizable(false); // Prevent frame from being resized
//		frame.setSize(640, 540); // Set frame size (x, y)
		frame.setLocationRelativeTo(null);	// set location null = center
		frame.setVisible(true); // Make frame visible
		
		

		ImageIcon image = new ImageIcon("image\\icon.png");		// Create an ImageIcon
		frame.setIconImage(image.getImage());		// Change Icon
		frame.getContentPane().setBackground(new Color(0, 102, 204 ));	// Color background - Cannot insert Image yet!
	}	

}
