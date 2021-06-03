import java.awt.EventQueue;


/**
 * Main function to be ran upon execution.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Main
{
    /**
     * Main of the LaTex converter.
     * 
     * @param args represents command from the command line.
     */
    public static void main(String[] args) {
        final Function funct = new Function(null);
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final DrawFrame frame = new DrawFrame(funct);
                frame.setVisible(true);
            }
        } );
        
    }

}