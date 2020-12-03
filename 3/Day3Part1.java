import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * day3
 */
public class Day3Part1 {

    public static void main(String[] args) throws Exception {
        String file = "3/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) { lines.add(line); }

        String aux = "";
        int cont = 0;

        for (int i = 1; i < lines.size(); i++) {
            aux = lines.get(i);
            if (aux.charAt((i * 3) % aux.length()) == '#') { cont++; }
        }

        System.out.println(cont);

        br.close();
    }
}