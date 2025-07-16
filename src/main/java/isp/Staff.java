/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp;


import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class StaffMember {
    String name, id, position, phoneNo, joiningDate;
    int salary;

    public StaffMember(String name, String id, String position, String phoneNo, int salary, String joiningDate) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.phoneNo = phoneNo;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", salary=" + salary +
                ", joiningDate='" + joiningDate + '\'' +
                '}';
    }
}

public class Staff extends JFrame implements ActionListener {
    private JButton addStaffButton, removeStaffButton, staffInfoButton, backButton;
    static ArrayList<StaffMember> staffList = new ArrayList<>();

    public Staff() {
        setTitle("Staff Management");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addStaffButton = new JButton("Add Staff");
        removeStaffButton = new JButton("Remove Staff");
        staffInfoButton = new JButton("Staff Info");
        backButton = new JButton("Back");

        addStaffButton.addActionListener(this);
        removeStaffButton.addActionListener(this);
        staffInfoButton.addActionListener(this);
        backButton.addActionListener(this);

        add(addStaffButton);
        add(removeStaffButton);
        add(staffInfoButton);
        add(backButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStaffButton) {
            addStaff();
        } else if (e.getSource() == removeStaffButton) {
            removeStaff();
        } else if (e.getSource() == staffInfoButton) {
            displayStaffInfo();
        } else if (e.getSource() == backButton) {
            this.dispose();
        }
    }

    private void addStaff() {
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField positionField = new JTextField();
        JTextField phoneNoField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField joiningDateField = new JTextField();

        Object[] fields = {
                "Name:", nameField,
                "ID:", idField,
                "Position:", positionField,
                "Phone No:", phoneNoField,
                "Salary:", salaryField,
                "Joining Date:", joiningDateField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Staff", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            StaffMember staffMember = new StaffMember(
                    nameField.getText(),
                    idField.getText(),
                    positionField.getText(),
                    phoneNoField.getText(),
                    Integer.parseInt(salaryField.getText()),
                    joiningDateField.getText()
            );
            staffList.add(staffMember);
            JOptionPane.showMessageDialog(this, "Staff member added successfully.");
        }
    }

    private void removeStaff() {
        JTextField idField = new JTextField();

        Object[] fields = {
                "ID:", idField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Remove Staff", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            StaffMember staffToRemove = null;
            for (StaffMember staff : staffList) {
                if (staff.id.equals(id)) {
                    staffToRemove = staff;
                    break;
                }
            }

            if (staffToRemove != null) {
                staffList.remove(staffToRemove);
                JOptionPane.showMessageDialog(this, "Staff member removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Staff member not found.");
            }
        }
    }

    private void displayStaffInfo() {
        ArrayList<StaffMember> sortedStaff = mergeSort(staffList);

        StringBuilder staffInfo = new StringBuilder("Staff Members (sorted by salary):\n");
        for (StaffMember staff : sortedStaff) {
            staffInfo.append(staff.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(this, staffInfo.toString());
    }

    private ArrayList<StaffMember> mergeSort(ArrayList<StaffMember> staffList) {
        if (staffList.size() <= 1) {
            return staffList;
        }

        int mid = staffList.size() / 2;
        ArrayList<StaffMember> left = new ArrayList<>(staffList.subList(0, mid));
        ArrayList<StaffMember> right = new ArrayList<>(staffList.subList(mid, staffList.size()));

        return merge(mergeSort(left), mergeSort(right));
    }

    private ArrayList<StaffMember> merge(ArrayList<StaffMember> left, ArrayList<StaffMember> right) {
        ArrayList<StaffMember> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).salary >= right.get(j).salary) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }
}
