package stfinal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


class Employee {
    String name;
    String id;
    String department;
    String position;


    public Employee(String name, String id, String department, String position) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Department: " + department + ", Position: " + position;
    }
}

class ManagerInterface {
    private JFrame managerFrame;
    private JTextArea outputArea;
    private ArrayList<Employee> employees;

    public ManagerInterface(ArrayList<Employee> employees) {
        this.employees = employees;

        managerFrame = new JFrame("Manager Interface");
        managerFrame.setBounds(100, 100, 600, 400);
        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerFrame.getContentPane().setLayout(null);

        initialize();

        managerFrame.setVisible(true);
    }

    private void initialize() {
        outputArea = new JTextArea();
        outputArea.setBounds(50, 50, 500, 300);
        managerFrame.getContentPane().add(outputArea);

        JButton btnViewEmployees = new JButton("View Employees");
        btnViewEmployees.setBounds(50, 10, 150, 25);
        managerFrame.getContentPane().add(btnViewEmployees);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(210, 10, 80, 25);
        managerFrame.getContentPane().add(btnLogout);

        btnViewEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("Employee List:\n");
                for (Employee employee : employees) {
                    outputArea.append(employee.toString() + "\n");
                }
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerFrame.dispose();
            }
        });
    }
}

public class ColorfulEmployeeManagementSystem {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea outputArea;
    private Map<String, String> adminCredentials;
    private Map<String, String> managerCredentials;
    private Map<String, String> assistantManagerCredentials;
    private ArrayList<Employee> employees;

    private static final String ADMIN_FILE = "admin.txt";
    private static final String MANAGER_FILE = "manager.txt";
    private static final String ASSISTANT_MANAGER_FILE = "assistantManager.txt";
    private static final String EMPLOYEE_FILE = "employees.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ColorfulEmployeeManagementSystem();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ColorfulEmployeeManagementSystem() {
        frame = new JFrame("Employee Management System");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        initialize();

        frame.setVisible(true);
    }

    private void initialize() {
        adminCredentials = readCredentials(ADMIN_FILE);
        managerCredentials = readCredentials(MANAGER_FILE);
        assistantManagerCredentials = readCredentials(ASSISTANT_MANAGER_FILE);
        employees = readEmployees();

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 80, 25);
        frame.getContentPane().add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(140, 50, 150, 25);
        frame.getContentPane().add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 90, 80, 25);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 90, 150, 25);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 130, 80, 25);
        frame.getContentPane().add(btnLogin);

        outputArea = new JTextArea();
        outputArea.setBounds(50, 180, 500, 150);
        frame.getContentPane().add(outputArea);

        // Set colorful background
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        // Set colorful button background
        btnLogin.setBackground(new Color(60, 179, 113));

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
                    showAdminMenu();
                } else if (managerCredentials.containsKey(username) && managerCredentials.get(username).equals(password)) {
                    showManagerMenu();
                } else if (assistantManagerCredentials.containsKey(username) && assistantManagerCredentials.get(username).equals(password)) {
                    showAssistantManagerMenu();
                } else {
                    outputArea.setText("Invalid credentials.");
                }
            }
        });
    }

    private Map<String, String> readCredentials(String fileName) {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                credentials.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    private ArrayList<Employee> readEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Employee employee = new Employee(parts[0], parts[1], parts[2], parts[3]);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private void showAdminMenu() {
        outputArea.setText("Admin login successful.\n");

        JButton btnCreateManager = new JButton("Create Manager");
        btnCreateManager.setBounds(350, 130, 200, 25);
        frame.getContentPane().add(btnCreateManager);

        JButton btnCreateAssistantManager = new JButton("Create Assistant Manager");
        btnCreateAssistantManager.setBounds(510, 130, 200, 25);
        frame.getContentPane().add(btnCreateAssistantManager);

        // Set colorful button background
        btnCreateManager.setBackground(new Color(255, 140, 0));
        btnCreateAssistantManager.setBackground(new Color(255, 140, 0));

        btnCreateManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newManagerUsername = JOptionPane.showInputDialog("Enter new manager username:");
                String newManagerPassword = JOptionPane.showInputDialog("Enter new manager password:");
                managerCredentials.put(newManagerUsername, newManagerPassword);
                saveCredentials(managerCredentials, MANAGER_FILE);
                outputArea.setText("Manager account created: " + newManagerUsername);
            }
        });

        btnCreateAssistantManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newAssistantManagerUsername = JOptionPane.showInputDialog("Enter new assistant manager username:");
                String newAssistantManagerPassword = JOptionPane.showInputDialog("Enter new assistant manager password:");
                assistantManagerCredentials.put(newAssistantManagerUsername, newAssistantManagerPassword);
                saveCredentials(assistantManagerCredentials, ASSISTANT_MANAGER_FILE);
                outputArea.setText("Assistant Manager account created: " + newAssistantManagerUsername);
            }
        });
    }

    private void saveCredentials(Map<String, String> credentials, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : credentials.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showManagerMenu() {
        outputArea.setText("Manager login successful.\n");

        JButton btnOpenManagerInterface = new JButton("Open Manager Interface");
        btnOpenManagerInterface.setBounds(250, 130, 200, 25);
        frame.getContentPane().add(btnOpenManagerInterface);

        // Set colorful button background
        btnOpenManagerInterface.setBackground(new Color(255, 69, 0));

        btnOpenManagerInterface.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the main login frame
                new ManagerInterface(employees);
            }
        });
    }

    private void showAssistantManagerMenu() {
        outputArea.setText("Assistant Manager login successful.\n");

        JButton btnAddEmployee = new JButton("Add Employee");
        btnAddEmployee.setBounds(250, 130, 150, 25);
        frame.getContentPane().add(btnAddEmployee);

        JButton btnViewEmployees = new JButton("View Employees");
        btnViewEmployees.setBounds(410, 130, 150, 25);
        frame.getContentPane().add(btnViewEmployees);

        JButton btnSearchEmployee = new JButton("Search Employee");
        btnSearchEmployee.setBounds(250, 160, 150, 25);
        frame.getContentPane().add(btnSearchEmployee);

        JButton btnRemoveEmployee = new JButton("Remove Employee");
        btnRemoveEmployee.setBounds(410, 160, 150, 25);
        frame.getContentPane().add(btnRemoveEmployee);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(50, 130, 80, 25);
        frame.getContentPane().add(btnLogout);

        // Set colorful button background
        btnAddEmployee.setBackground(new Color(0, 128, 128));
        btnViewEmployees.setBackground(new Color(0, 128, 128));
        btnSearchEmployee.setBackground(new Color(0, 128, 128));
        btnRemoveEmployee.setBackground(new Color(0, 128, 128));
        btnLogout.setBackground(new Color(255, 0, 0));

        btnAddEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter employee name:");
                String id = JOptionPane.showInputDialog("Enter employee ID:");
                String department = JOptionPane.showInputDialog("Enter employee department:");
                String position = JOptionPane.showInputDialog("Enter employee position:");

                Employee employee = new Employee(name, id, department, position);
                employees.add(employee);
                saveEmployees(employees);
                outputArea.setText("Employee added:\n" + employee.toString());
            }
        });

        btnViewEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("Employee List:\n");
                for (Employee employee : employees) {
                    outputArea.append(employee.toString() + "\n");
                }
            }
        });

        btnSearchEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId = JOptionPane.showInputDialog("Enter employee ID to search:");
                Employee foundEmployee = findEmployeeById(searchId);
                if (foundEmployee != null) {
                    outputArea.setText("Employee found:\n" + foundEmployee.toString());
                } else {
                    outputArea.setText("Employee not found.");
                }
            }
        });

        btnRemoveEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeId = JOptionPane.showInputDialog("Enter employee ID to remove:");
                Employee foundEmployee = findEmployeeById(removeId);
                if (foundEmployee != null) {
                    employees.remove(foundEmployee);
                    saveEmployees(employees);
                    outputArea.setText("Employee removed:\n" + foundEmployee.toString());
                } else {
                    outputArea.setText("Employee not found.");
                }
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();  // Close the main login frame
            }
        });
    }

    private Employee findEmployeeById(String id) {
        for (Employee employee : employees) {
            if (employee.id.equals(id)) {
                return employee;
            }
        }
        return null;
    }

    private void saveEmployees(ArrayList<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                bw.write(employee.name + "," + employee.id + "," + employee.department + "," + employee.position);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}