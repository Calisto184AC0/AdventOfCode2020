import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Day7 {

    public static void main(String[] args) throws Exception{
        String file = "7/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line, pair[];
        Graph allBags = new Graph();
        Bag aux;

        while ((line = br.readLine()) != null) {
            pair = line.split(" bags contain ");
            
            // pair 0
            allBags.addBag(pair[0]);

            // pair 1
            if (!pair[1].contains("no")) {
                String[] bagsText = pair[1].split(" bag[s]?[.,][ ]?"), nb = new String[2];
                for (int i = 0; i < bagsText.length; i++) {
                    nb[0] = bagsText[i].split(" ")[0];                  // number
                    nb[1] = bagsText[i].substring(nb[0].length() + 1);  // color

                    aux = allBags.bags.get(nb[1]);
                    if (aux != null) {
                        allBags.bagContainsBag(allBags.bags.get(pair[0]), aux, Integer.parseInt(nb[0]));
                    } else {
                        allBags.addBag(nb[1]);
                        allBags.bagContainsBag(allBags.bags.get(pair[0]), allBags.bags.get(nb[1]), Integer.parseInt(nb[0]));
                    }
                }
            }
        }

        System.out.println("Part 1 = " + allBags.bagsCanContainBag("shiny gold"));
        System.out.println("Part 2 = " + allBags.howMuchBags("shiny gold"));
        br.close();
    }
}

class Bag {
    String color;
    List<Contain> contains;

    Bag(String color) {
        this.color = color;
        this.contains = new ArrayList<>();
    }
}

class Contain {
    int amount;
    Bag bag;

    Contain(int amount, Bag bag) {
        this.amount = amount;
        this.bag = bag;
    }
}

class Edge {
    Bag origin, end;
    int weight;

    Edge(Bag origin, int weight, Bag end) {
        this.origin = origin;
        this.end = end;
        this.weight = weight;
    }
}

class Graph {
    List<Edge> edges;
    List<String> colors;
    Hashtable <String, Bag> bags;

    Graph() {
        edges = new ArrayList<>();
        colors = new ArrayList<>();
        bags = new Hashtable<>();
    }

    // add nodes
    public void addBag(String color) {
        colors.add(color);
        bags.put(color, new Bag(color));
    }

    // add edges
    public void bagContainsBag(Bag origin, Bag end, int weight) {
        edges.add(new Edge(origin, weight, end));
        bags.get(origin.color).contains.add(new Contain(weight, end));
    }

    public int bagsCanContainBag(String color) {
        List<String> contain = new ArrayList<>(), noContain = new ArrayList<>();
        contain.add(color);
        Bag aux; boolean find;

        for (int i = 0; i < colors.size(); i++) {
            find = false;
            aux = bags.get(colors.get(i));
            if (!contain.contains(aux.color) && !noContain.contains(aux.color)) {
                for (int j = 0; j < aux.contains.size() && !find; j++) {
                    if (contain.contains(aux.contains.get(j).bag.color)) {
                        find = true;
                        contain.add(colors.get(i));
                        i = -1;
                    }
                }
            }
        }

        return contain.size() - 1;
    }

    public int howMuchBags(String color) {
        int res = 0, nextRes;
        Bag aux = bags.get(color);

        // base case
        if (aux.contains.size() == 0) { res = 1; }

        // default case
        else {
            for (int i = 0; i < aux.contains.size(); i++) {
                nextRes = howMuchBags(aux.contains.get(i).bag.color);
                if (nextRes == 1) {
                    res += aux.contains.get(i).amount * nextRes;
                } else {
                    res += aux.contains.get(i).amount + aux.contains.get(i).amount * nextRes;
                }
            }
        }

        return res;
    }
}
