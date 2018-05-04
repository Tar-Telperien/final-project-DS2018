//MY COPY THAT I CAN EDIT

/*
THINGS DONE:

THINGS NEEDING TO BE DONE:
Create small buffered image to hold fractal. Fractal changes based off of mouse coordinates and moves with mouse.
Create larger buffered image; overlay the smaller image over the larger. Use revelio function as prototype.
Make solid black background.

Useful pieces from GitHub:
Steganography.java;

Start by building a white box 41X41 that tracks the mouse location; overlay on black box.

*/

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.Point;


public class Amelia extends JFrame{

    JPanel combinedPanel;

    // Some Constants
    int WIDTH = 40;
    int HEIGHT = 40;
    double SCALE = 200;
    Point p; //ADD VALUES TO THIS AND ADD MOUSE HANDLER

    // The files
    BufferedImage processedImage;

    public static void main(String[] args){
	    new Amelia();
    }

    public Amelia(){
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));

	    // When the user clicks the red "x", close the window
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    p = MouseInfo.getPointerInfo().getLocation();


	    // Panel to hold the combined images
	    processedImage = buildFractalImage(BufferedImage.TYPE_INT_ARGB, p);

	    combinedPanel = new JPanel(){
		    public void paintComponent(Graphics g){
		        super.paintComponent(g);
		        g.drawImage(processedImage, 0, 0, this);
		    }
	    };
	    combinedPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

	    add(combinedPanel, BorderLayout.CENTER);

	    pack();
	    setVisible(true);
    }


    /**
     * Build a simple image containing the given line of text
     */
    BufferedImage buildFractalImage(int type, Point p){
	    BufferedImage im = new BufferedImage(WIDTH, HEIGHT, type);
	    ComplexNumber c = new ComplexNumber (.3, .7);

	    for(int x = 0; x < WIDTH; x++){
	        for(int y = 0; y < HEIGHT; y++){

		    ComplexNumber z = new ComplexNumber((p.x - WIDTH/2)/SCALE, (p.y-HEIGHT/2)/SCALE);
		    for(int i = 0; i < 10; i++){
		        z = z.multiply(z).add(c);
		    }
		    if(z.norm() > 5)
		        im.setRGB(x, y, 0xFF00FF00);
		    else if(z.norm() > 10)
		        im.setRGB(x, y, 0xFF0000FF);
		    else
		        im.setRGB(x, y, 0xFFFF0000);
	        }
	    }
	    return im;
    }


    /**
     * From this site: https://stackoverflow.com/questions/9417356/bufferedimage-resize
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
	Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	BufferedImage dimg = new BufferedImage(newW, newH, img.getType());

	Graphics2D g2d = dimg.createGraphics();
	g2d.drawImage(tmp, 0, 0, null);
	g2d.dispose();

    return dimg;
    }

    void revelio(int x, int y){
        BufferedImage newProcessedImage =
        // Now overlay with a "revealed image" made from the last bit of the red component
        int mask = 0x00010000;
        int radius = 100;
        BufferedImage newProcessedImage = //black box
        //Next, build a little 41X41
            new BufferedImage(2*radius + 1, 2*radius + 1, BufferedImage.TYPE_INT_ARGB);
        for(int i = y-radius; i <= y + radius; i++){
            if(i < 0 || i >= processedImage.getHeight()) continue;
        for(int j = x-radius; j <= x + radius; j++){
            if(j < 0 || j >= processedImage.getWidth()) continue;
            int rgb = (processedImage.getRGB(j, i) & mask) == 0 ?
            Color.black.getRGB() : Color.white.getRGB();
            int alpha = 255 - (int)(255 * Math.sqrt((x - j)*(x - j) + (y - i)*(y - i))) / radius;
            if(alpha < 0) alpha = 0;
            newProcessedImage.setRGB(j - (x - radius), i - (y - radius), (rgb & 0x00FFFFFF) + (alpha << 24));
      }
    }
    Graphics2D g = (Graphics2D) processedImage.getGraphics();
    g.drawImage(newProcessedImage, x - radius, y - radius, null);

    combinedPanel.repaint();
  }


}
