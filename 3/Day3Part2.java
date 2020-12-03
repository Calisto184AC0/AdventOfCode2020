import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * day3
 */
public class Day3Part2 {

    static class Coordinate {
    
        public int right, down;
        Coordinate(int right, int down) {
            this.right = right;
            this.down = down;
        }
    }

    public static void main(String[] args) throws Exception {
        String file = "3/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) { lines.add(line); }

        String aux = "";
        int cont = 0;
        long res = 1;

        Coordinate[] coordinates = {
            new Coordinate(1, 1),
            new Coordinate(3, 1),
            new Coordinate(5, 1),
            new Coordinate(7, 1),
            new Coordinate(1, 2)
        };

        for (Coordinate coordinate : coordinates) {
            cont = 0;
            for (int i = coordinate.down; i < lines.size(); i += coordinate.down) {
                aux = lines.get(i);
                if (aux.charAt(((i / coordinate.down) * coordinate.right) % aux.length()) == '#') { cont++; }
            }
            res *= cont;
        }

        System.out.println(res);

        br.close();
    }
}