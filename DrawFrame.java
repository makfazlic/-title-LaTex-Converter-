import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;


/**
 * Write a description of class DrawFrame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DrawFrame extends JFrame
{

    JLabel label;
    
    /**
     * Constructor for objects of class DrawFrame
     */
    public DrawFrame(Function funct)
    {
        super();
        setTitle("Math Operation Visualizer");
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 500));
        final JPanel panel = new JPanel();
        
        final JButton ev = new JButton("Evaluate");        
        
        final JTextField expressionField = new JTextField(funct.getExpression(), 15);
        Font bigFont = expressionField.getFont().deriveFont(Font.PLAIN, 30f);
        expressionField.setFont(bigFont);        
        
        panel.add(expressionField);
        panel.add(ev);
        add(panel, BorderLayout.NORTH);

        
        label = new JLabel (funct.getExpression(), SwingConstants.CENTER);
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Verdana", Font.PLAIN, 50));
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        add(label, BorderLayout.CENTER);
        
        ev.addActionListener(new ActionListener() { 
            public void actionPerformed(final ActionEvent e) {
                final String text = expressionField.getText();
                funct.setExpression(text);
                final String newText = funct.getExpression();
                label.setText(newText);
            }
        });
        pack();
    }

}
