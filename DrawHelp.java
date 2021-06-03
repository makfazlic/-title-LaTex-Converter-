import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Allows the frame drawing.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */

public class DrawHelp extends JFrame
{
    /**
     * Constructor for objects of class DrawHelp
     * @param funct is a Function Object.
     */
    public DrawHelp() {
        super();
        setTitle("Help page - Important commands");
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(640, 1000));
        
        final JPanel panel = new JPanel();
        add(panel);
        final JLabel label = new JLabel();
        label.setIcon(new ImageIcon("help.png"));
        add(label, BorderLayout.CENTER);
        pack();
    }
}
