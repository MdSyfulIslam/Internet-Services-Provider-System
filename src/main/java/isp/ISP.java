
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Lenovo
 */
package isp;



import javax.swing.*;
import java.awt.event.*;

public class ISP extends JFrame implements ActionListener {
    private JButton customerServicesButton, mappingButton, staffButton, billingButton, allUserButton;

    public ISP() {
        setTitle("ISP Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        customerServicesButton = new JButton("Customer Services");
        mappingButton = new JButton("Mapping");
        staffButton = new JButton("Staff");
        billingButton = new JButton("Billing");
        allUserButton = new JButton("All Users");

        customerServicesButton.addActionListener(this);
        mappingButton.addActionListener(this);
        staffButton.addActionListener(this);
        billingButton.addActionListener(this);
        allUserButton.addActionListener(this);

        add(customerServicesButton);
        add(mappingButton);
        add(staffButton);
        add(billingButton);
        add(allUserButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customerServicesButton) {
            new CustomerServices().setVisible(true);
        } else if (e.getSource() == mappingButton) {
            new Mapping().setVisible(true);
        } else if (e.getSource() == staffButton) {
            new Staff().setVisible(true);
        } else if (e.getSource() == billingButton) {
            JOptionPane.showMessageDialog(this, "Billing module will be built in the future.");
        } else if (e.getSource() == allUserButton) {
            new AllUser().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ISP().setVisible(true);
            }
        });
    }
}
