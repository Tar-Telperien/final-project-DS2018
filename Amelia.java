//MY COPY THAT I CAN EDIT

/*
Build external file for buildFractalImage; make it a separate thread to speed things up. Right now, play with fractal.
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
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.Point;


public class Amelia extends JFrame{

    JPanel mainPanel;

    // Some Constants
    int WIDTH = 1000;
    int HEIGHT = 700;
    int FractalSize = 41;
    int MouseDiff = 21;
    Point p = new Point((WIDTH/2), (HEIGHT/2));

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

	    // Panel to hold the combined images
	    mainPanel = new JPanel(){
		    public void paintComponent(Graphics g){
		        super.paintComponent(g);
		        g.drawImage(processedImage, 0, 0, this);
		    }
	    };

	    mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

	    mainPanel.addMouseMotionListener(new MouseMotionAdapter(){
	        public void mouseMoved(MouseEvent e) {
	            p = MouseInfo.getPointerInfo().getLocation();
	            processedImage = buildFractalImage(BufferedImage.TYPE_INT_ARGB, p);
	            mainPanel.repaint();
	        }
	    });

	    add(mainPanel, BorderLayout.CENTER);

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
	            //if(p.x <= x & x <= (p.x + FractalSize) & p.y <= y & y <= (p.y + FractalSize)){
	                //for(int a = (p.x - FractalSize); a < (p.x + FractalSize); a++){
	                    //for(int b = (p.y - FractalSize); b < (p.y + FractalSize); b++){
		                    ComplexNumber z = new ComplexNumber(p.x, p.y);
		                    for(int i = 0; i < 10; i++){
		                        z = z.multiply(z).add(c);
		                    }
		                    if(z.norm() > 10)
		                        //im.setRGB(a, b, 0xFF00FF00); NORMAL VERSION
		                        im.setRGB(x, y, 0xFF00FF00);
		                    else
		                        //im.setRGB(a, b, 0xFFFF0000); NORMAL VERSION
		                        im.setRGB(x, y, 0xFFFF0000);
	                    }
	                }
	            //}
	            //else{
	                //im.setRGB(x, y, 0xFF000000);
	            //}
	        //}
	    //}
	    return im;
    }

/*    void revelio(int x, int y){
        int mask = 0x00010000;
        int radius = 40;
        BufferedImage newProcessedImage = //black box
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

        mainPanel.repaint();
    }*/ //Do I still need this? I may have moved its functionality, into buildFractalImage.
}
