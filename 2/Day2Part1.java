import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * day2
 */
public class Day2Part1 {
    
    static class Line {
    
        int low, hight;
        String letter, password;

        public Line(int low, int hight, String letter, String password) {
            this.low = low;
            this.hight = hight;
            this.letter = letter;
            this.password = password;
        }

        public boolean isValid() {
            boolean res = false;

            if (password.contains(letter)) {
                String matches = password.replaceAll("[^" + letter + "]", "");
                
                if (matches.length() <= hight && matches.length() >= low) {
                    res = true;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) throws Exception {
        String file = "2/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));

        String line;
        List<Line> lines = new ArrayList<Line>();
        String[] aux = br.readLine().split("[^0-9a-z]+");
        lines.add(new Line(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), aux[2], aux[3]));
        while ((line = br.readLine()) != null) {
            aux = line.split("[^0-9a-z]+");
            lines.add(new Line(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), aux[2], aux[3]));
        }

        int cont = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isValid()) { cont++; }
        }

        System.out.println(cont);

        br.close();
    }
}