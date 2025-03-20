
package colombo.institute.of.studies;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search_1 extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JLabel resultLabel;

    public Search_1() {
        // Set up the JFrame
        setTitle("Employee Search ");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        // Create and add components
        searchField = new JTextField(20);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployee();
            }
        });
        add(searchButton);

        resultLabel = new JLabel();
        add(resultLabel);
    }

    private void searchEmployee() {
        // Here, you would implement the code to search for the employee
        // based on the input in the searchField.
        // For simplicity, let's just display a message.
        String employeeName = searchField.getText();
        resultLabel.setText("Searching for " + employeeName + "...");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Search_1().setVisible(true);
            }
        });
    }
}

