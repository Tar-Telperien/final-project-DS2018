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

    //Constants
    int WIDTH = 1000;
    int HEIGHT = 700;
    double Scale = 200;
    int FractalSize = 41;
    int MouseDiff = 21;
    Point p = new Point((WIDTH/2), (HEIGHT/2));

    //Buffered Images
    BufferedImage processedImage;
    BufferedImage backgroundImage;

    public static void main(String[] args){
	    new Amelia();
    }

    public Amelia(){
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    processedImage = buildSolidImage(Color.BLACK, BufferedImage.TYPE_INT_ARGB);
	    setBackgroundImage(processedImage);


	    // When the user clicks the red "x", close the window
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());

	    //Panel to hold the image
	    mainPanel = new JPanel(){
		    public void paintComponent(Graphics g){
		        super.paintComponent(g);
		        g.drawImage(processedImage, 0, 0, this);
		    }
	    };

	    mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //Mouse listener; updates the portion of the fractal shown with every movement of the mouse.
	    mainPanel.addMouseMotionListener(new MouseMotionAdapter(){
	        public void mouseMoved(MouseEvent e) {
	            p = MouseInfo.getPointerInfo().getLocation();
	            revelio(p);
	            mainPanel.repaint();
	        }
	    });

	    add(mainPanel, BorderLayout.CENTER);

	    pack();
	    setVisible(true);
    }

    //Builds the black background image
    BufferedImage buildSolidImage(Color c, int type){
        BufferedImage im = new BufferedImage(WIDTH, HEIGHT, type);
        Graphics g = im.getGraphics();
        g.setColor(c);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        return im;
    }

    //Sets the permanent background image to which the revelio function will revert
    void setBackgroundImage(BufferedImage im){
        ColorModel c = im.getColorModel();
        boolean iap = c.isAlphaPremultiplied();
        WritableRaster raster = im.copyData(null);
        backgroundImage = new BufferedImage(c, raster, iap, null);
    }

    //Draws a portion of a fractal on top of the background image.
    void revelio(Point p){
        ComplexNumber cNum = new ComplexNumber (0.3, 0.7);

        // Restart with the saved image
        ColorModel c = backgroundImage.getColorModel();
        boolean iap = c.isAlphaPremultiplied();
        WritableRaster raster = backgroundImage.copyData(null);
        processedImage = new BufferedImage(c, raster, iap, null);

        for(int x = (p.x - FractalSize); x < (p.x + FractalSize); x++){
	        for(int y = (p.y - FractalSize); y < (p.y + FractalSize); y++){
	            //ComplexNumber cNum = new ComplexNumber (p.x/50.0, p.y/50.0);
		        ComplexNumber z = new ComplexNumber((x - WIDTH/2)/Scale, (y - HEIGHT/2)/Scale);
		        //ComplexNumber z = new ComplexNumber((x-p.x)/10.0, (y-p.y)/10.0);
		        for(int i = 0; i < 12; i++){
		            z = z.multiply(z).add(cNum);
		        }
		        if(z.norm() > 5)
		            processedImage.setRGB(x, y, 0xFF000080);
		        else if(z.norm() < 10)
		            processedImage.setRGB(x, y, 0xFF00CC00);
		        //else if(z.norm() > 15)
		            //  processedImage.setRGB(x, y, 0xFF8080FF);
		        else
		            processedImage.setRGB(x, y, 0xFFFF0066);
	        }
	    }
    }
}
