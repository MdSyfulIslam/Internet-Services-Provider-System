/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp;


import javax.swing.*;
import java.awt.event.*;

public class Mapping extends JFrame implements ActionListener {
    private JButton minimumCostButton, routeButton, backButton;

    public Mapping() {
        setTitle("Mapping");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        minimumCostButton = new JButton("Minimum Wire Cost");
        routeButton = new JButton("Route");
        backButton = new JButton("Back");

        minimumCostButton.addActionListener(this);
        routeButton.addActionListener(this);
        backButton.addActionListener(this);

        add(minimumCostButton);
        add(routeButton);
        add(backButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == minimumCostButton) {
            findMinimumCost();
        } else if (e.getSource() == routeButton) {
            findRoute();
        } else if (e.getSource() == backButton) {
            this.dispose();
        }
    }

    private void findMinimumCost() {
        // Placeholder for the Kruskal's algorithm implementation
      //  JOptionPane.showMessageDialog(this, "Minimum cost calculation (Kruskal's algorithm) will be implemented here.");
        // Call the main method of KruskalAlgorithmGUI
        KruskalAlgorithmGUI.main(new String[0]);
    }

    private void findRoute() {
        // Placeholder for the Dijkstra's algorithm implementation
        JOptionPane.showMessageDialog(this, "Route calculation (Dijkstra's algorithm) will be implemented here.");
        DijkstraGUI.main(new String[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Mapping().setVisible(true);
            }
        });
    }
}
