import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * day1
 */
public class Day1Part2 {

    public static void main(String[] args) throws Exception {
        String file = "1/input";
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));

        String line;
        List<Integer> lines = new ArrayList<Integer>();
        while ((line = br.readLine()) != null) { lines.add(Integer.parseInt(line)); }

        boolean find = false;
        for (int i = 0; i < lines.size() && !find; i++) {
            for (int j = i + 1; j < lines.size() && !find; j++) {
                for (int w = j + 1; w < lines.size() && !find; w++) {
                    if (lines.get(i) + lines.get(j) + lines.get(w) == 2020) {
                        System.out.println(lines.get(i) * lines.get(j) * lines.get(w));
                        find = true;
                    }
                }
            }
        }

        br.close();
    }
}