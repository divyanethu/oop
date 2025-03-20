/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colombo.institute.of.studies;

import javax.swing.*;

public class PasswordRecoveryExample {
    // Simulating a database of user information (username, security question, and answer)
    private static String[][] userData = {
            {"user1", "What is your pet's name?", "tom"},
            {"user2", "Where were you born?", "kurunegala"},
            // Add more user data as needed
    };

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog(null, "Enter your username:");

        if (username != null && !username.isEmpty()) {
            int userIndex = findUserIndex(username);

            if (userIndex != -1) {
                String securityQuestion = userData[userIndex][1];
                String userAnswer = JOptionPane.showInputDialog(null, "Security Question: " + securityQuestion);

                if (userAnswer != null && userAnswer.equals(userData[userIndex][2])) {
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:");
                    if (newPassword != null && !newPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Password reset successfully for user: " + username);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid password. Password reset failed.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect answer to security question. Password reset failed.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found. Password reset failed.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username. Password reset failed.");
        }
    }

    private static int findUserIndex(String username) {
        for (int i = 0; i < userData.length; i++) {
            if (userData[i][0].equals(username)) {
                return i;
            }
        }
        return -1; // User not found
    }
}
