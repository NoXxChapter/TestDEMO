package Main;

// FOLDERs


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{

	private final int originalTileSize = 16;	// 16*16
	private final int scale = 4;
	private final int tileSize = originalTileSize * scale;	// 64*64
	
	private final int maxCol = 20;
	private final int maxRow = 12;
	
	private final int screenWidth = maxCol * tileSize;	// 1280
	private final int screenHeight = maxRow * tileSize;	// 768
	
	
	// SPRITE PROPERTIES
	private BufferedImage playerImage, subPlayerImage;	
	
	
	// THREAD
	private Thread gameThread = new Thread();
	
	
	//	PLAYER
	
	
	public GamePanel(){
		
		this.setFocusable( true );
		this.setDoubleBuffered(true);
		this.setPreferredSize( new Dimension( screenWidth, screenHeight) );
		this.setBackground(Color.black);
		
		setUp();
		
	}

	//	1.
	//	SET UP
	//
	private void setUp() {
		
		// INITAILIZE THREAD
		gameThread = new Thread(this);
		gameThread.start(); 
		
		
		// SPRITE
		// Load the sprite sheet image
		InputStream player_sprite = getClass().getResourceAsStream("/Player_BlueSoldier.png");
		
		try {
			
			playerImage = ImageIO.read(player_sprite);
			
		} catch (IOException e) {
			System.out.println("Loading Image ERROR");
		}
        
	}
	
	//	2.
	//	PAINT
	//
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		
		// GRIDs
		int gridX = tileSize;
		int gridY = tileSize;
		for (int i = 0; i < maxRow; i++) {
			for ( int j = 0; j < maxCol; j++) {
				
				g.setColor(Color.green);
				g.drawRect(gridX*j, gridY*i, tileSize, tileSize);
				
			}
		}
	
		//	PLAYER		
		// TEST 1
		subPlayerImage = playerImage.getSubimage( 
				tileSize + (tileSize * 9) ,originalTileSize*3, 
				originalTileSize*5, originalTileSize*5);
		g.drawImage( subPlayerImage , tileSize, tileSize, 
				tileSize, tileSize, null );
		
		
		// TEST 2
		//
		// ACCEPT
//		g.setColor(Color.red);
//		g.fillRect(tileSize, tileSize*2, tileSize*3, tileSize*3);
		
		subPlayerImage = playerImage.getSubimage( 
				0 ,0 , tileSize*3, tileSize*3);
		g.drawImage( subPlayerImage , tileSize, tileSize*2, 
				tileSize*3, tileSize*3, null );
		
		
		// TEST 3
		//
		//	SUCCESS
		subPlayerImage = playerImage.getSubimage( 
				tileSize*3 ,0 , tileSize*3, tileSize*3);
		g.drawImage( subPlayerImage , tileSize, tileSize*4, 
				tileSize*3, tileSize*3, null );
		
		// TEST 4
		//
		//	SUCCESS
		subPlayerImage = playerImage.getSubimage( 
				tileSize*3 ,tileSize*3 , tileSize*3, tileSize*3);
		g.drawImage( subPlayerImage , tileSize, tileSize*6, 
				tileSize*3, tileSize*3, null );
		
		
		//==========================================================//
		/* FROM those 3 test
		 * i concluded that the sprite is best when use in 3*3 
		 * Both when subImage and drawImage
		 * to maintain the sprite stay at the center
		 * Avoid any chance that the character's frame
		 * move out of single tileSize.
		 */

	}
	
	
	
	//	3.
	// 	LOOP
	//	THREAD RUN
	//	DELTA CLOCK
	//
	@Override
	public void run() {
		
		long now;
		long lastTime = System.nanoTime();
		
		double delta = 0;
		int FPS = 120;		
		int drawCounter = 0;
		int timer = 0;
		double sec = 1e9 / FPS;	// 1e9 == 1 billion
		
		while ( gameThread != null) {
			
			now = System.nanoTime();
			delta += ( now - lastTime ) / sec;
			timer += ( now - lastTime );
			lastTime = now;
			
			if ( delta >= 1 ) {
				repaint();			// EXECUTE 2. paintComponent METHOD once again
				lastTime = now;
				drawCounter++;
				delta--;
			}
			
			// DISPLAY FPS
			if (timer >= 1e9) {
				System.out.println("FPS: " + drawCounter);
				drawCounter = 0;
				timer = 0;
			}
			
			
		}
	}

	
	//
	// KEYLISTENER
	//
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
