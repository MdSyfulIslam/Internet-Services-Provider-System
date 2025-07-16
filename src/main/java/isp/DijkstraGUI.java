/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp;

/**
 *
 * @author Lenovo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DijkstraGUI {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DijkstraGUI().createAndShowGUI());
    }
    
    private JFrame frame;
    private JTextField vertexField;
    private JTextField edgeField;
    private JTextArea resultArea;
    private Graph graph;

    public DijkstraGUI() {
        frame = new JFrame("Dijkstra's Algorithm");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel vertexLabel = new JLabel("Number of vertices:");
        vertexField = new JTextField();
        JLabel edgeLabel = new JLabel("Edges (src,dest,weight; separate multiple with space):");
        edgeField = new JTextField();

        inputPanel.add(vertexLabel);
        inputPanel.add(vertexField);
        inputPanel.add(edgeLabel);
        inputPanel.add(edgeField);

        JButton addButton = new JButton("Add Graph");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGraph();
            }
        });

        JButton findPathButton = new JButton("Find Shortest Path");
        findPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findShortestPath();
            }
        });

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(addButton, BorderLayout.WEST);
        frame.add(findPathButton, BorderLayout.EAST);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void createAndShowGUI() {
        frame.setVisible(true);
    }

    private void addGraph() {
        int vertices = Integer.parseInt(vertexField.getText());
        graph = new Graph(vertices);

        String[] edges = edgeField.getText().split(" ");
        for (String edge : edges) {
            String[] parts = edge.split(",");
            int src = Integer.parseInt(parts[0]);
            int dest = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            graph.addEdge(src, dest, weight);
        }

        resultArea.setText("Graph added successfully.");
    }

    private void findShortestPath() {
        if (graph == null) {
            resultArea.setText("Please add a graph first.");
            return;
        }

        int src = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter source vertex:"));
        Dijkstra dijkstra = new Dijkstra(graph.getVertices());
        dijkstra.dijkstra(graph.getAdjMatrix(), src);

        int[] distances = dijkstra.getDistances();
        int[] predecessors = dijkstra.getPredecessors();
        StringBuilder result = new StringBuilder("Shortest paths from vertex " + src + ":\n");
        for (int i = 0; i < distances.length; i++) {
            if (i != src && distances[i] != Integer.MAX_VALUE) {
                result.append("To ").append(i).append(": ").append(distances[i]).append(" via ");
                result.append(getPath(predecessors, i)).append("\n");
            } else if (i == src) {
                result.append("To ").append(i).append(": ").append(distances[i]).append(" (Source)\n");
            } else {
                result.append("To ").append(i).append(": No path\n");
            }
        }

        resultArea.setText(result.toString());
    }

    private String getPath(int[] predecessors, int target) {
        java.util.List<Integer> path = new ArrayList<>();
        for (Integer at = target; at != -1; at = predecessors[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path.toString();
    }

    class Graph {
        private final int vertices;
        private final int[][] adjMatrix;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjMatrix = new int[vertices][vertices];
        }

        public void addEdge(int src, int dest, int weight) {
            adjMatrix[src][dest] = weight;
            adjMatrix[dest][src] = weight;
        }

        public int getVertices() {
            return vertices;
        }

        public int[][] getAdjMatrix() {
            return adjMatrix;
        }
    }

    class Dijkstra {
        private final int[] distances;
        private final int[] predecessors;
        private final Set<Integer> visited;
        private final PriorityQueue<Node> pq;
        private final int vertices;
        private int[][] adjMatrix;

        public Dijkstra(int vertices) {
            this.vertices = vertices;
            distances = new int[vertices];
            predecessors = new int[vertices];
            visited = new HashSet<>();
            pq = new PriorityQueue<>(vertices, new Node());
        }

        public void dijkstra(int[][] adjMatrix, int src) {
            this.adjMatrix = adjMatrix;
            Arrays.fill(distances, Integer.MAX_VALUE);
            Arrays.fill(predecessors, -1);
            pq.add(new Node(src, 0));
            distances[src] = 0;

            while (!pq.isEmpty()) {
                int u = pq.remove().node;
                visited.add(u);
                processNeighbours(u);
            }
        }

        private void processNeighbours(int u) {
            int edgeDistance;
            int newDistance;

            for (int v = 0; v < vertices; v++) {
                if (!visited.contains(v) && adjMatrix[u][v] != 0) {
                    edgeDistance = adjMatrix[u][v];
                    newDistance = distances[u] + edgeDistance;

                    if (newDistance < distances[v]) {
                        distances[v] = newDistance;
                        predecessors[v] = u;
                    }
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        public int[] getDistances() {
            return distances;
        }

        public int[] getPredecessors() {
            return predecessors;
        }

        class Node implements Comparator<Node> {
            public int node;
            public int cost;

            public Node() {
            }

            public Node(int node, int cost) {
                this.node = node;
                this.cost = cost;
            }

            @Override
            public int compare(Node node1, Node node2) {
                return Integer.compare(node1.cost, node2.cost);
            }
        }
    }
}
