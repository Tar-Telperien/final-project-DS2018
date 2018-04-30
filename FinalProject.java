import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.awt.geom.*;
import java.awt.Point;
import java.util.Scanner;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class FinalProject extends JFrame {

    //Constants:
    static int Width = 1000;
    static int Height = 700;
    static int initX = Width/2;
    static int initY = Height/2;
    static int circleDiam = 50;
    static int circleR = 25;
    static Point currentPos = new Point(initX, initY);

    public static void main(String[] args){
        new FinalProject();
    }

//NOTE: write new paint method for the jpanel; use @override; tell that paint method to place the buffered image on the screen.

    BufferedImage base;

    public FinalProject(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Width, Height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    base = buildImage(BufferedImage.TYPE_INT_RGB);

        JPanel main = new JPanel(){
            public void paintComponent(Graphics g){
                    super.paintComponent(g); //asks the parent class to do its thing so that setup
                    //and cleanup will be taken care of
                    g.drawImage(base, 0, 0, this);
                }
        };
        main.setLayout(new BorderLayout());
        Point p = new Point();

        main.addMouseListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                currentPos = MouseInfo.getPointerInfo().getLocation();
                p = currentPos;
            }
        });

	    add(main, BorderLayout.CENTER);

        pack();
        setVisible(true);

        main.repaint(); //redraws the panel at some rate
    }

    BufferedImage buildImage(int type){
	    BufferedImage im = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	    Graphics g = im.getGraphics();
	    g.fillRect(0, 0, Width, Height);
	    //now figure out a mask, or set of masks, based off of the distance from P.
	    return im;
    }

    BufferedImage addFractal(BufferedImage img, int fracType, Point p){

    }
}
