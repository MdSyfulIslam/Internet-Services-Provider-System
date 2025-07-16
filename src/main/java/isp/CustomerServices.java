/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class User {
    String name, userId, phoneNo, nidNo, address, connectionType;

    public User(String name, String userId, String phoneNo, String nidNo, String address, String connectionType) {
        this.name = name;
        this.userId = userId;
        this.phoneNo = phoneNo;
        this.nidNo = nidNo;
        this.address = address;
        this.connectionType = connectionType;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", nidNo='" + nidNo + '\'' +
                ", address='" + address + '\'' +
                ", connectionType='" + connectionType + '\'' +
                '}';
    }
}

public class CustomerServices extends JFrame implements ActionListener {
    private JButton addButton, deleteButton, infoButton, backButton;
    static ArrayList<User> userList = new ArrayList<>();

    public CustomerServices() {
        setTitle("Customer Services");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addButton = new JButton("Add User");
        deleteButton = new JButton("Delete User");
        infoButton = new JButton("User Info");
        backButton = new JButton("Back");

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        infoButton.addActionListener(this);
        backButton.addActionListener(this);

        add(addButton);
        add(deleteButton);
        add(infoButton);
        add(backButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addUser();
        } else if (e.getSource() == deleteButton) {
            deleteUser();
        } else if (e.getSource() == infoButton) {
            displayUserInfo();
        } else if (e.getSource() == backButton) {
            this.dispose();
        }
    }

    private void addUser() {
        JTextField nameField = new JTextField();
        JTextField userIdField = new JTextField();
        JTextField phoneNoField = new JTextField();
        JTextField nidNoField = new JTextField();
        JTextField addressField = new JTextField();
        String[] connectionTypes = {"1500 tk", "1000 tk", "500 tk"};
        JComboBox<String> connectionTypeBox = new JComboBox<>(connectionTypes);

        Object[] fields = {
                "Name:", nameField,
                "User ID:", userIdField,
                "Phone No:", phoneNoField,
                "NID No:", nidNoField,
                "Address:", addressField,
                "Connection Type:", connectionTypeBox
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            User user = new User(
                    nameField.getText(),
                    userIdField.getText(),
                    phoneNoField.getText(),
                    nidNoField.getText(),
                    addressField.getText(),
                    (String) connectionTypeBox.getSelectedItem()
            );
            userList.add(user);
            JOptionPane.showMessageDialog(this, "User added successfully.");
        }
    }

    private void deleteUser() {
        JTextField userIdField = new JTextField();

        Object[] fields = {
                "User ID:", userIdField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Delete User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String userId = userIdField.getText();
            User userToRemove = null;
            for (User user : userList) {
                if (user.userId.equals(userId)) {
                    userToRemove = user;
                    break;
                }
            }

            if (userToRemove != null) {
                userList.remove(userToRemove);
                JOptionPane.showMessageDialog(this, "User removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        }
    }

    private void displayUserInfo() {
        JTextField userIdField = new JTextField();

        Object[] fields = {
                "User ID:", userIdField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "User Info", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String userId = userIdField.getText();
            User userToShow = null;
            for (User user : userList) {
                if (user.userId.equals(userId)) {
                    userToShow = user;
                    break;
                }
            }

            if (userToShow != null) {
                JOptionPane.showMessageDialog(this, userToShow.toString());
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        }
    }
}
