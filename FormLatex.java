import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class create the .tex file and convert it in a PDF file.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */

public class FormLatex {
    
    /**
     * To complete with a correct desctription.
     * 
     * @param commands is an ArrayList of Strings.
     * @return a String.
     */
    public static String form(final ArrayList<String> commands) {
        String output;
        final String latex;
        final String begin = "\\documentclass{article}" +
"\\usepackage[english]{babel}"+
"\\usepackage[utf8]{inputenc}"+
"\\usepackage{fancyhdr}"+
"\\pagestyle{fancy}"+
"\\fancyhf{}"+
"\\rhead{ROberto Ferrari and Mak Fazlic}"+
"\\lhead{Latex Converter}"+
"\\rfoot{Page \\thepage}" +
"\\begin{document}";
                                
        final String end = "\\end{document}";
        
        String middle = "";
        
        for (final String str : commands) {   
            if (str.contains("section") || str.contains("textit")) {
                middle = middle + str;
            } else {
            middle = middle + "\\[ " + str + " \\]"; 
            }
        }
        
        try { // try create output.tex
    
            final FileWriter myWriter = new FileWriter("output.tex");
            myWriter.write(begin + middle + end);
            myWriter.close();
            output = "Succesfully parsed to output.tex";
            
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
            return "An error occurred.";
        }
        /*
        final String command = "pdflatex output.tex";
            
        try { // try create output.pdf
            final Process process = Runtime.getRuntime().exec(command);
            final BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            String line;
            line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
            }
            reader.close();
            output = "Succesfully compiled to: output.pdf";
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */
        return output;
    }

}
