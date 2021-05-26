import java.awt.EventQueue;


/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{

    private Main(){
    }

    public static void main (final String[] args){
        final Function funct= new Function(null);
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final DrawFrame frame = new DrawFrame(funct);
                frame.setVisible(true);
            }
        });
        
    }
}
