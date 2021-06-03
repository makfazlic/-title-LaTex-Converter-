import java.awt.EventQueue;


/**
 * Main function to be ran upon execution.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Help
{
    /**
     * Main of the LaTex converter.
     * 
     */
    public static void main() {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final DrawHelp frame = new DrawHelp();
                frame.setVisible(true);
            }
        } );
        
    }
}