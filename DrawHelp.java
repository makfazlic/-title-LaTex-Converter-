import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Allows the frame drawing.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */

public class DrawHelp extends JFrame
{
    /**
     * Constructor for objects of class DrawHelp.
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
