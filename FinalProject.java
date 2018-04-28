/*Things needing to be done:
2. Figure out how to create a bufferedImage in white as the background.
3. Animate circles to follow mouse motion by creating a list of circles and delaying their motion by different amounts.
4. Change circles to be parts of an animal
5. Add 3D graphics
6. Figure out how to actually draw circles with a coordinate point as centre.
Things done:
1. Populate HashMap
2. Implement mouse movement sensor
3. Create simple GUI interface.
4. At the end of the method to change the visuals, use panel.repaint(); to tell Java that it needs to redraw itself.

Take distance between current mouse location & current circle location
Divide by # of circles
Circle N moves dist - y*(n-1)
*/

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

    //hashmap to hold the circles that we'll be animating
    static HashMap<String, Point> circles = new HashMap<String, Point>();

//NOTE: write new paint method for the jpanel; use @override; tell that paint method to place the buffered image on the screen.

    //populateHashMap function
    //make each dist equal to the distance between the current circle position and the mouse position in the x or y direction, divided by the # of circles to be drawn.
    static void populateHashMap(int i, int j, int xdist, int ydist, int num){
        int counter = 1;
        int x = i;
        int y = j;
        for(int m = 0; m < num; m++){
            Point Position = new Point(x, y);
            circles.put(Integer.toString(counter), Position);
            counter++;
	    x = x-xdist+circleR;
	    y=y-ydist+circleR;
	}
    }

    BufferedImage base;

    public FinalProject(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Width, Height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	base = buildImage(BufferedImage.TYPE_INT_RGB);

        JPanel main = new JPanel(){
            public void paintComponent(Graphics g){
                    super.paintComponent(g); //asks the parent class to do its thing so that setup and cleanup will be taken care of
                    g.drawImage(base, 0, 0, this);
                }
        };
        main.setLayout(new BorderLayout());
        Point p = new Point();

        main.addMouseListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                currentPos = MouseInfo.getPointerInfo().getLocation();
            }
        });

        populateHashMap(currentPos.x, currentPos.y, 1, 1, 10);

        //for entry in hashmap, draw an ellipse
        for(String c:(circles.keySet())){
            p = circles.get(c);
            Graphics g = base.getGraphics();
            Graphics2D g2 = (Graphics2D)g;
            g2.draw(new Ellipse2D.Double(p.x, p.y, circleDiam, circleDiam));
        }

	add(main, BorderLayout.CENTER);

        pack();
        setVisible(true);

        main.repaint(); //redraws the panel at some rate
    }

    BufferedImage buildImage(int type){
	    BufferedImage im = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	    Graphics g = im.getGraphics();
	    g.fillRect(0, 0, Width, Height);
	    return im;
    }
}
