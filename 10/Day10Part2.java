import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Day10Part2 {
    
    public static void main(String[] args) throws Exception {
        String file = "10/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;

        List<Integer> numbers = new ArrayList<>();

        while ((line = br.readLine()) != null) { numbers.add(Integer.parseInt(line)); }

        br.close();

        numbers.add(0);
        Collections.sort(numbers);
        numbers.add(numbers.get(numbers.size() - 1) + 3);

        //for (int i = 0; i < numbers.size(); i++) {
        //    System.out.println(numbers.get(i));
        //}

        Graph jolts = new Graph();
        int diff = 0;

        for (int i = 0; i < numbers.size() - 1; i++) {
            jolts.addNode(numbers.get(i));
            diff = numbers.get(i + 1) - numbers.get(i);
            jolts.addNode(numbers.get(i + 1));
            jolts.addEdge(jolts.nodes.get(numbers.get(i)), jolts.nodes.get(numbers.get(i + 1)), diff);

            if (i < numbers.size() - 3) {
                if (numbers.get(i) + 2 == numbers.get(i + 2)) {
                    jolts.addNode(numbers.get(i + 2));
                    jolts.addEdge(jolts.nodes.get(numbers.get(i)), jolts.nodes.get(numbers.get(i + 2)), 2);
                }
                if (numbers.get(i) + 3 == numbers.get(i + 3)) {
                    jolts.addNode(numbers.get(i + 3));
                    jolts.addEdge(jolts.nodes.get(numbers.get(i)), jolts.nodes.get(numbers.get(i + 3)), 3);
                }
            }
        }

        System.out.println(jolts.numberOfPaths());
    }

    static class Node {

        int number, indegree;
        List<Edge> edges;

        Node(int number) {
            this.number = number;
            this.edges = new ArrayList<>();
        }
    }

    static class Edge {
    
        int weight;
        Node node;

        Edge(int weight, Node node) {
            this.weight = weight;
            this.node = node;
        }
    }

    static class Graph {
    
        List<Integer> numbers;
        Hashtable<Integer, Node> nodes;

        Graph() {
            numbers = new ArrayList<>();
            nodes = new Hashtable<>();
        }

        void addNode(int i) {
            if (isNull(i)) {
                numbers.add(i);
                nodes.put(i, new Node(i));
            }
        }

        void addEdge(Node origin, Node end, int weight) {
            nodes.get(origin.number).edges.add(new Edge(weight, nodes.get(end.number)));
            nodes.get(end.number).indegree++;
        }

        boolean isNull(int i) {
            return nodes.get(i) == null;
        }

        long numberOfPaths() {
            long dp[] = new long[numbers.size()];
            dp[0] = 0;
            dp[numbers.size() - 1] = 1;

            for (int i = numbers.size() - 1; i >= 0; i--) {
                for (int j = 0; j < nodes.get(numbers.get(i)).edges.size(); j++) {
                    for (int t = 0; t < numbers.size(); t++) {
                        if (nodes.get(numbers.get(i)).edges.get(j).node.number == numbers.get(t)) {
                            dp[i] += dp[t];
                        }
                    }
                }
            }

            return dp[0];
        }
    }
}
