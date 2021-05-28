import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.*;

/**
 * Allows the frame drawing.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class DrawFrame extends JFrame
{

    
    private ArrayList<String> latex = new ArrayList<String>();
    
    /**
     * Constructor for objects of class DrawFrame
     */
    public DrawFrame(final Function funct)
    {
        super();
        setTitle("Expression to Latex converter");
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 700));
        
        final JPanel panel = new JPanel();

        final JButton convert = new JButton("Convert");   
        final JButton add = new JButton("Add");
        final JButton process = new JButton("Process");
        
        final JTextField sourcecode = new JTextField(funct.getExpression(), 15);
        Font bigFont = sourcecode.getFont().deriveFont(Font.PLAIN, 30f);
        sourcecode.setFont(bigFont);        

        panel.add(sourcecode);
        panel.add(convert);
        panel.add(add);
        panel.add(process);
        add(panel, BorderLayout.NORTH);

        
        final JLabel label = new JLabel(funct.getExpression(), SwingConstants.CENTER);
        label.setIcon(new ImageIcon("Background.jpg"));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setBounds(100, 100, 100, 100);
        label.setFont(new Font("Verdana", Font.PLAIN, 50));
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        add(label, BorderLayout.CENTER);
        
        
        final JLabel counter = new JLabel(funct.getExpression(), SwingConstants.CENTER);
        add(counter, BorderLayout.SOUTH);
        convert.addActionListener(new ActionListener() { 
            public void actionPerformed(final ActionEvent e) {
                final String text = sourcecode.getText();
                funct.setExpression(text);
                final String newText = funct.getExpression();
                label.setText(newText);
            }}
        );
        
        add.addActionListener(new ActionListener() { 
            public void actionPerformed(final ActionEvent e) {
                System.out.println(label.getText());
                if (label.getText().equals("Added!") || label.getText().equals("Convert again!")){
                label.setText("Convert again!");
                }
                else if (label.getText() != null) {
                latex.add(label.getText());
                counter.setText("Additions: " + latex.size() + ", Last addition = " + label.getText());
                label.setText("Added!");
                }
                else {
                label.setText("No LaTex command to add!");
            }}}
        );
        
        process.addActionListener(new ActionListener() { 
            public void actionPerformed(final ActionEvent e) {
                label.setText("Initialized compilation");
                label.setText(FormLatex.form(latex));
            }}
        );
        
        pack();
    }
}
