/*Things needing to be done:
1. Create simple GUI interface.
2. Implement mouse movement sensor
3. Animate circles to follow mouse motion by creating a list of circles and delaying their motion by different amounts.
4. Change circles to be parts of an animal
5. Add 3D graphics
6.
Things done:
*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class FinalProject extends JFrame {

    public static void main(String[] args){
        FinalProject frame = new FinalProject();

        frame.pack();
        frame.setVisible(true);
    }

    public FinalProject(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mouseMoved(MouseEvent e){
        //do stuff
    }
}