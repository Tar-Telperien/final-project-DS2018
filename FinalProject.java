/*Things needing to be done:
3. Animate circles to follow mouse motion by creating a list of circles and delaying their motion by different amounts.
4. Change circles to be parts of an animal
5. Add 3D graphics
6. Figure out how to actually draw circles with a coordinate point as centre.
Things done:
1. Populate HashMap
2. Implement mouse movement sensor
3. Create simple GUI interface.
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

public class FinalProject extends JFrame {

    public static void main(String[] args){
        FinalProject frame = new FinalProject();

        frame.pack();
        frame.setVisible(true);

        populateHashMap(500, 350, 10); //REPLACE MAGIC NUMBERS
    }


    //hashmap to hold the circles that we'll be animating
    static HashMap<String, Point> circles = new HashMap<String, Point>();


    //populateHashMap function
    static void populateHashMap(int i, int j, int num){
        int counter = 1;
        for(int m = 0; m < num; m++){
            int x = i;
            int y = j;
            Point Position = new Point(x, y);
            circles.put(Integer.toString(counter), Position);
            counter++;
        }
    }

    public FinalProject(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        main.addMouseListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e){
                Point currentPos = MouseInfo.getPointerInfo().getLocation();
                //Need a way to get currentPos as a global variable.
            }
        });
    }
}
