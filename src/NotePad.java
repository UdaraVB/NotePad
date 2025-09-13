import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NotePad {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Note Pad");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newNoteItem = new JMenuItem("New Note");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenu aboutMenu = new JMenu("About");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem colorButton = new JMenuItem("Change Text Color");

        fileMenu.add(newNoteItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(colorButton);
        aboutMenu.add(help);
        frame.setJMenuBar(menuBar);

        // New note
        newNoteItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to create a new note?");
            if (choice == JOptionPane.YES_OPTION) {
                textArea.setText("");
            }
        });

        // Color button

        colorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(frame, "Pick a Color", textArea.getForeground());
            if (color != null) {
                textArea.setForeground(color);
            }
        });


        // Open
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (FileReader reader = new FileReader(file)) {
                    textArea.read(reader, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        // Save note
        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(file)) {
                    textArea.write(writer);
                    JOptionPane.showMessageDialog(frame,"Successfully Saved!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        // Exit
        exitItem.addActionListener(e -> System.exit(0));

        help.addActionListener(e -> {
                    JOptionPane.showMessageDialog(frame,"Contact us -> jcode@gmail.com");
        });


        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
