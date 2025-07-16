/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class AllUser extends JFrame implements ActionListener {
    private JButton backButton;

    public AllUser() {
        setTitle("All Users");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        displayAllUsers();

        add(backButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
        }
    }

    private void displayAllUsers() {
        ArrayList<User> users = CustomerServices.userList;
        quickSort(users, 0, users.size() - 1);

        int type1Count = 0, type2Count = 0, type3Count = 0;
        for (User user : users) {
            if (user.connectionType.equals("1500 tk")) {
                type1Count++;
            } else if (user.connectionType.equals("1000 tk")) {
                type2Count++;
            } else if (user.connectionType.equals("500 tk")) {
                type3Count++;
            }
        }

        StringBuilder userInfo = new StringBuilder();
        userInfo.append("Total number of users: ").append(users.size()).append("\n");
        userInfo.append("Total Number of type 1 users: ").append(type1Count).append("\n");
        userInfo.append("Total Number of type 2 users: ").append(type2Count).append("\n");
        userInfo.append("Total Number of type 3 users: ").append(type3Count).append("\n");

        JTextArea textArea = new JTextArea(userInfo.toString());
        textArea.setEditable(false);
        add(new JScrollPane(textArea));
    }

    private void quickSort(ArrayList<User> users, int low, int high) {
        if (low < high) {
            int pi = partition(users, low, high);
            quickSort(users, low, pi - 1);
            quickSort(users, pi + 1, high);
        }
    }

    private int partition(ArrayList<User> users, int low, int high) {
        String pivot = users.get(high).connectionType;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (users.get(j).connectionType.compareTo(pivot) <= 0) {
                i++;
                Collections.swap(users, i, j);
            }
        }
        Collections.swap(users, i + 1, high);
        return i + 1;
    }
}
