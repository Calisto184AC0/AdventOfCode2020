import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * day3
 */
public class Day3Part2 {

    public static void main(String[] args) throws Exception {
        String file = "3/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) { lines.add(line); }

        String aux = "";
        int cont = 0;
        long res;

        // right 1 down 1
        for (int i = 1; i < lines.size(); i++) {
            aux = lines.get(i);
            if (aux.charAt(i % aux.length()) == '#') { cont++; }
        }

        res = cont;
        cont = 0;

        // right 3 down 1
        for (int i = 1; i < lines.size(); i++) {
            aux = lines.get(i);
            if (aux.charAt((i * 3) % aux.length()) == '#') { cont++; }
        }

        res *= cont;
        cont = 0;

        // right 5 down 1
        for (int i = 1; i < lines.size(); i++) {
            aux = lines.get(i);
            if (aux.charAt((i * 5) % aux.length()) == '#') { cont++; }
        }

        res *= cont;
        cont = 0;

        // right 7 down 1
        for (int i = 1; i < lines.size(); i++) {
            aux = lines.get(i);
            if (aux.charAt((i * 7) % aux.length()) == '#') { cont++; }
        }

        res *= cont;
        cont = 0;

        // right 1 down 2
        for (int i = 2; i < lines.size(); i = i + 2) {
            aux = lines.get(i);
            if (aux.charAt((i/2) % aux.length()) == '#') { cont++; }
        }

        res *= cont;

        System.out.println(res);

        br.close();
    }
}