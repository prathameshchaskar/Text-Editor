import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    //Declaring properties of textEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //file menu items
    JMenuItem newFile, openFile, saveFile;
    //edit menu items
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;

    TextEditor() {
        //Initialize a frame
        frame = new JFrame("Text Editor");
        //Initialize menuBar
        menuBar = new JMenuBar();
        //Initialize textArea
        textArea =new JTextArea();
        //Initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //Add ActionListener to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //Add actionListener to menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //Add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Set menuBar to frame
        frame.setJMenuBar(menuBar);
        //Create content pane
        JPanel panel= new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add textArea to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add Scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);

        //Set dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource() == cut) {
             //perform cut operation
             textArea.cut();
         }
         if(e.getSource() == copy) {
            //perform copy operation
             textArea.copy();
         }
        if(e.getSource() == paste) {
            //perform paste operation
            textArea.paste();
        }
        if(e.getSource() == selectAll) {
            //perform selectAll operation
            textArea.selectAll();
        }
        if(e.getSource() == close) {
            //perform close operation
            System.exit(0); //0 means close the code
        }
        if(e.getSource() == openFile) {
            //open file chooser
            JFileChooser fileChooser = new JFileChooser("D:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try {
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //Read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                }
                catch(IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(e.getSource() == saveFile) {
            //open file picker
            JFileChooser fileChooser = new JFileChooser("D:");
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();

                }
            }
        }

    }
}