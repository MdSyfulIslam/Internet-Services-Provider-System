/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp;

/**
 *
 * @author Lenovo
 */


//package com.example.myapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithmGUI extends JFrame {
    private JTextField sourceField, destField, weightField;
    private JTextArea resultArea;
    private KruskalAlgorithm kruskal;

    public KruskalAlgorithmGUI() {
        setTitle("Kruskal's Algorithm");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Source:"));
        sourceField = new JTextField();
        inputPanel.add(sourceField);
        inputPanel.add(new JLabel("Destination:"));
        destField = new JTextField();
        inputPanel.add(destField);
        inputPanel.add(new JLabel("Wire Cost:"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        JButton addButton = new JButton("Add Wire");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int src = Integer.parseInt(sourceField.getText());
                int dest = Integer.parseInt(destField.getText());
                int weight = Integer.parseInt(weightField.getText());
                kruskal.addEdge(src, dest, weight);
                sourceField.setText("");
                destField.setText("");
                weightField.setText("");
            }
        });

        JButton mstButton = new JButton("Show Minimum Wire");
        mstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Edge> mst = kruskal.kruskalMST();
                resultArea.setText("");
                int totalWeight = 0;
                for (Edge edge : mst) {
                    resultArea.append("Route: " + edge.src + " - " + edge.dest + " Wire Need: " + edge.weight + " Meter\n");
                    totalWeight += edge.weight;
                }
                resultArea.append("Total Wire Need: " + totalWeight + " Meter");
              
            }
        });

        inputPanel.add(addButton);
        inputPanel.add(mstButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        int numVertices = 5; // Example number of vertices
        kruskal = new KruskalAlgorithm(numVertices);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KruskalAlgorithmGUI().setVisible(true);
            }
        });
    }

    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    class UnionFind {
        int[] parent, rank;
        int n;

        public UnionFind(int n) {
            this.n = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (u != parent[u])
                parent[u] = find(parent[u]);
            return parent[u];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    class KruskalAlgorithm {
        int V, E;
        List<Edge> edges;

        public KruskalAlgorithm(int V) {
            this.V = V;
            this.edges = new ArrayList<>();
        }

        public void addEdge(int src, int dest, int weight) {
            edges.add(new Edge(src, dest, weight));
        }

        public List<Edge> kruskalMST() {
            Collections.sort(edges);
            UnionFind uf = new UnionFind(V);
            List<Edge> result = new ArrayList<>();
            for (Edge edge : edges) {
                int rootU = uf.find(edge.src);
                int rootV = uf.find(edge.dest);
                if (rootU != rootV) {
                    result.add(edge);
                    uf.union(rootU, rootV);
                }
            }
            return result;
        }
    }
}
