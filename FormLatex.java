import java.io.*;  // Import the IOException class to handle errors
import java.util.*;
/**
 * Write a description of class FormLatex here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FormLatex
{
    public FormLatex(String middle){}

    public static String form(ArrayList<String> commands) {
    String latex;
    
    String begin1 = 
                "\\documentclass[tikz,14pt]{article}\n"
                +"\\usepackage[utf8]{inputenc}\n"
                +"\\usepackage[margin=1in]{geometry}\n"
                +"\\usepackage[titletoc,title]{appendix}\n"
                +"\\usepackage{latexsym}\n"
                +"\\usepackage{amssymb}\n"
                +"\\usepackage{amsmath}\n"
                +"\\usepackage{amsfonts}\n"
                +"\\usepackage{multicol}\n"
                +"\\usepackage{graphicx}\n"
                +"\\usepackage{fancyhdr}\n"
                +"\\usepackage[linguistics]{forest}\n"
                +"\\usepackage{colortbl}"
                +"\\usepackage[dvipsnames]{xcolor}\n"
                +"\\usepackage{pdfpages}\n"
                +"\\usepackage{titletoc}\n"
                +"\\usepackage[export]{adjustbox}\n"
                +"\\usepackage{pgfplots}\n"
                +"\\usetikzlibrary{angles, quotes}\n"
                +"\\usepackage{tikz-3dplot}\n"
                +"\\usepackage{enumitem}\n"
                +"\\pagestyle{fancy}\n"
                +"\\fancyhf{}\n"
                +"\\rhead{2nd Semester 2021}\n"
                +"\\lhead{Mak Fazlic - Assignment 1}\n"
                +"\\rfoot{Page \\thepage}\n"
                +"\\setlength{\\parindent}{0pt}\n"
                +"\\begin{document}\n"
                +"\\titlecontents{section}[0em]\n"
                +"{\\vskip 0.5ex}\n"
                +"{\\scshape}% numbered sections formatting.\n"
                +"{\\itshape}% unnumbered sections formatting.\n"
                +"\\begin{multicols}{2}% 2-column layout\n"
                +"  \\begin{minipage}{0.6\\textwidth}\n"
                +"  \\vspace{0em}\n"
                +"    \\hspace*{1em}\\Large{Linear Algebra Assignment 1}\\\\\n"
                +"    \\hspace*{0.8em}\\normalsize \\textbf{Student: }Mak Fazlic\n"
                +"    \\normalsize \\textbf{Date: }08.03.2021\\\\\n"
                +"    \\hspace*{1em}\\textit{Bonus exercise is denoted by red color.}\n"
                +"  \\end{minipage}\n"
                +"  \\begin{minipage}{0.45\\textwidth}\n"
                +"  \\vspace{-2em}\n"
                +"  \\hspace{1.5em}\n"
                +"    \\includegraphics[width=0.7\\linewidth,right]{logo.png}\n"
                +"  \\end{minipage}\n"
                +"\\end{multicols}\n"
                +"\\vspace{-3em}\n";
    
        
        String begin = "\\documentclass{article}\\usepackage[utf8]{inputenc}\\begin{document}\\section{Introduction}";
    
        String end = "\\end{document}";
    
        String middle = "";
        
        for (String str : commands) {             
           middle = middle + "\\[ " + str + " \\]"; 
        }
        
        System.out.println();
        try {
            FileWriter myWriter = new FileWriter("output.tex");
            myWriter.write(begin + middle + end);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            
            
            
            String command = "/usr/bin/pdftex /home/mak/Desktop/GUI/project-latex-ferrari-fazlic/output.tex";
 
            try {
                Process process = Runtime.getRuntime().exec(command);
 
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println(line);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
           
            return "Succesfully compiled to: output.pdf";
            }  
        catch (IOException e) {
            System.out.println("An error occurred.");
                        e.printStackTrace();
            return "An error occurred.";
        }
    }

}