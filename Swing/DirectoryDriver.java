import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * My Homework 6 Solution to build a Swing for Student Directory.
 * @author Yuanyuan Ma yuanyuam
 */
public class DirectoryDriver extends JFrame {
    /**
     *  Reference to the text area First Name.
     */
    private Directory directory;
    /**
     *  Reference to the text area First Name.
     */
    private JTextField textFieldFirstName;
    /**
     *  Reference to the text area Last Name.
     */
    private JTextField textFieldLastName;
    /**
     *  Reference to the text area Andrew ID.
     */
    private JTextField textFieldAndrewId;
    /**
     *  Reference to the text area Phone Number.
     */
    private JTextField textFieldPhoneNumber;
    /**
     *  Reference to the button add.
     */
    private JButton buttonAdd;
    /**
     *  Reference to the button delete.
     */
    private JButton buttonDelete;
    /**
     *  Reference to the text area Andrew ID (delete pane).
     */
    private JTextField textFieldAndrewIdDelete;
    /**
     *  Reference to the text area Search key.
     */
    private JTextField textFieldSearchKey;
    /**
     *  Reference to the button byAndrewId.
     */
    private JButton buttonByAndrewId;
    /**
     *  Reference to the button byFirstName.
     */
    private JButton buttonByFirstName;
    /**
     *  Reference to the button byLastName.
     */
    private JButton buttonByLastName;
    /**
     *  Reference to the text area Results.
     */
    private JTextArea textAreaResults;

    /**
     * Constructor where JFrame and other components are instantiated.
     */
    public DirectoryDriver() {
        directory = new Directory();
        JFrame frame = new JFrame("Student Directory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textFieldSearchKey.requestFocusInWindow();
            }
        });

        Font font = new Font(Font.SERIF, Font.BOLD, 15);

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        JPanel addPane = new JPanel();
        addPane.setBorder(BorderFactory.createTitledBorder("Add a new student"));
        addPane.setLayout(flowLayout);

        JPanel line = new JPanel();
        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setFont(font);
        line.add(labelFirstName);

        textFieldFirstName = new JTextField(7);
        textFieldFirstName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        line.add(textFieldFirstName);

        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setFont(font);
        line.add(labelLastName);

        textFieldLastName = new JTextField(7);
        textFieldLastName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        line.add(textFieldLastName);

        JLabel labelAndrewId = new JLabel("Andrew ID:");
        labelAndrewId.setFont(font);
        line.add(labelAndrewId);

        textFieldAndrewId = new JTextField(7);
        textFieldAndrewId.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        line.add(textFieldAndrewId);

        JLabel labelPhoneNumber = new JLabel("Phone Number:");
        labelPhoneNumber.setFont(font);
        line.add(labelPhoneNumber);

        textFieldPhoneNumber = new JTextField(9);
        textFieldPhoneNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        line.add(textFieldPhoneNumber);

        buttonAdd = new JButton("Add");
        buttonAdd.setPreferredSize(new Dimension(60, 35));
        buttonAdd.addActionListener(new ButtonActionListener());
        line.add(buttonAdd);

        addPane.add(line);
        listPane.add(addPane);

        JPanel deletePane = new JPanel();
        deletePane.setBorder(BorderFactory.createTitledBorder("Delete a student"));
        deletePane.setLayout(flowLayout);

        JPanel line1 = new JPanel();

        JLabel labelAndrewIdDelete = new JLabel("Andrew ID:");
        labelAndrewIdDelete.setFont(font);
        line1.add(labelAndrewIdDelete);

        textFieldAndrewIdDelete = new JTextField(10);
        textFieldAndrewIdDelete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        line1.add(textFieldAndrewIdDelete);

        buttonDelete = new JButton("Delete");
        buttonDelete.setPreferredSize(new Dimension(60, 35));
        buttonDelete.addActionListener(new ButtonActionListener());
        line1.add(buttonDelete);

        deletePane.add(line1);
        listPane.add(deletePane);

        JPanel searchPane = new JPanel();
        searchPane.setBorder(BorderFactory.createTitledBorder("Search student(s)"));
        searchPane.setLayout(flowLayout);

        JPanel line2 = new JPanel();

        JLabel labelsearchKey = new JLabel("Search Key:");
        labelsearchKey.setFont(font);
        line2.add(labelsearchKey);

        textFieldSearchKey = new JTextField(10);
        textFieldSearchKey.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        line2.add(textFieldSearchKey);

        buttonByAndrewId = new JButton("By Andrew ID");
        buttonByAndrewId.setPreferredSize(new Dimension(120, 35));
        buttonByAndrewId.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String andrewId = textFieldSearchKey.getText();
                Student s = directory.searchByAndrewId(andrewId);
                String message;
                if (s == null) {
                    message = "There is no student with Andrew ID: " + andrewId + "\n";
                } else {
                    message = s.getFirstName() + " " + s.getLastName()
                            + " (Andrew ID: " + s.getAndrewId() + ", Phone Number: " + s.getPhoneNumber() + ")\n";
                    textFieldSearchKey.setText("");
                }
                textAreaResults.append(message);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        buttonByAndrewId.addActionListener(new ButtonActionListener());
        line2.add(buttonByAndrewId);

        buttonByFirstName = new JButton("By First Name");
        buttonByFirstName.setPreferredSize(new Dimension(120, 35));
        buttonByFirstName.addActionListener(new ButtonActionListener());
        line2.add(buttonByFirstName);

        buttonByLastName = new JButton("By Last Name");
        buttonByLastName.setPreferredSize(new Dimension(120, 35));
        buttonByLastName.addActionListener(new ButtonActionListener());
        line2.add(buttonByLastName);
        searchPane.add(line2);
        listPane.add(searchPane);

        JPanel resultPane = new JPanel();
        resultPane.setBorder(BorderFactory.createTitledBorder("Results"));
        resultPane.setLayout(flowLayout);

        textAreaResults = new JTextArea(8, 60);
        textAreaResults.setEditable(false);
        textAreaResults.setLineWrap(true);
        textAreaResults.setBorder(BorderFactory.createLineBorder(Color.black));

        JScrollPane scrollPane = new JScrollPane(textAreaResults);
        resultPane.add(scrollPane);
        listPane.add(resultPane);

        frame.setContentPane(listPane);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method is used to load data from csv file.
     * @param fileName the name the input file
     */
    public void loadData(String fileName) {
       BufferedReader buffer = null;
       String line = null;

       File f = new File(fileName);
       try {
           buffer = new BufferedReader(new FileReader(f));
           String s;

           boolean eof = false;
           while (!eof) {
               line = buffer.readLine();
               if (line == null) {
                   eof = true;
                   break;
               }

               while ((line = buffer.readLine()) != null) {
                   String[] info = line.split(",");
                   for (int i = 0; i < info.length; i++) {
                       info[i] = info[i].replaceAll("\"", "");
                   }

                   Student student = new Student(info[2], info[0], info[1], info[3]);
                   directory.addStudent(student);
               }
           }

       } catch (FileNotFoundException e) {
            e.printStackTrace();
       } catch (IOException e) {
            e.printStackTrace();
       }

    }

    /**
     * Nested Class.
     * @author Yuanyuan Ma yuanyuam
     */
    private class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd) {
                String firstName = textFieldFirstName.getText();
                System.out.println(firstName);
                String lastName = textFieldLastName.getText();
                String andrewId = textFieldAndrewId.getText();
                String phoneNumber = textFieldPhoneNumber.getText();

                if (firstName.equals("")) {
                    String message = "First Name missing\n";
                    textAreaResults.append(message);
                    return;
                } else if (lastName.equals("")) {
                    String message = "Last Name missing\n";
                    textAreaResults.append(message);
                    return;
                } else if (andrewId.equals("")) {
                    String message = "Andrew ID missing\n";
                    textAreaResults.append(message);
                    return;
                }

                Student s = directory.searchByAndrewId(andrewId);
                if (s == null) {
                    if (phoneNumber.equals("")) {
                        phoneNumber = "N/A";
                    }
                    Student student = new Student(andrewId, firstName, lastName, phoneNumber);
                    directory.addStudent(student);
                    String message = "A new entry was added\n";
                    textAreaResults.append(message);
                    textFieldAndrewId.setText("");
                    textFieldFirstName.setText("");
                    textFieldLastName.setText("");
                    textFieldPhoneNumber.setText("");
                } else {
                    String message = "Data already contains an entry for this Andrew ID\n";
                    textAreaResults.append(message);
                }
            } else if (e.getSource() == buttonDelete) {
                String andrewId = textFieldAndrewIdDelete.getText();
                Student student = directory.searchByAndrewId(andrewId);
                if (student == null) {
                    String message = "There is no student with Andrew ID: " + andrewId + "\n";
                    textAreaResults.append(message);
                } else {
                    directory.deleteStudent(andrewId);
                    String message = "The following entry was deleted\n"
                    + student.getFirstName() + " " + student.getLastName()
                    + " (Andrew ID: " + student.getAndrewId() + ", Phone Number: " + student.getPhoneNumber() + ")\n";
                    textAreaResults.append(message);
                    textFieldAndrewIdDelete.setText("");
                }
            } else if (e.getSource() == buttonByAndrewId) {
                String andrewId = textFieldSearchKey.getText();
                Student s = directory.searchByAndrewId(andrewId);
                String message;
                if (s == null) {
                    message = "There is no student with Andrew ID: " + andrewId + "\n";
                } else {
                    message = s.getFirstName() + " " + s.getLastName()
                            + " (Andrew ID: " + s.getAndrewId() + ", Phone Number: " + s.getPhoneNumber() + ")\n";
                    textFieldSearchKey.setText("");
                }
                textAreaResults.append(message);
            } else if (e.getSource() == buttonByFirstName) {
                String firstName = textFieldSearchKey.getText();
                try {
                    List<Student> listStudent = directory.searchByFirstName(firstName);
                    String message;
                    if (listStudent.size()  == 0) {
                        message = "There is no student with First Name: " + firstName + "\n";
                        textAreaResults.append(message);
                    } else {
                        for (Student s : listStudent) {
                            message = s.getFirstName() + " " + s.getLastName()
                                    + " (Andrew ID: " + s.getAndrewId()
                                    + ", Phone Number: " + s.getPhoneNumber() + ")\n";
                            textAreaResults.append(message);
                        }
                        textFieldSearchKey.setText("");
                    }
                } catch (CloneNotSupportedException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == buttonByLastName) {
                String lastName = textFieldSearchKey.getText();
                try {
                    List<Student> listStudent = directory.searchByLastName(lastName);
                    String message;
                    if (listStudent.size() == 0) {
                        message = "There is no student with Last Name: " + lastName + "\n";
                        textAreaResults.append(message);
                    } else {
                        for (Student s : listStudent) {
                            message = s.getFirstName() + " " + s.getLastName()
                                    + " (Andrew ID: " + s.getAndrewId()
                                    + ", Phone Number: " + s.getPhoneNumber() + ")\n";
                            textAreaResults.append(message);
                        }
                        textFieldSearchKey.setText("");
                    }
                } catch (CloneNotSupportedException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    /**
     * Main method that instantiates GUI Object.
     * @param args the default argument
     */
    public static void main(String[] args) {
        DirectoryDriver directoryDriver = new DirectoryDriver();
        directoryDriver.loadData(args[0]);
    }
}
