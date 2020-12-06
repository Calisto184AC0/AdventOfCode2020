import java.io.BufferedReader;
import java.io.FileReader;

public class Day6Part1 {
    
    public static void main(String[] args) throws Exception {
        String file = "6/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line, aux;
        int cont = 0;

        while ((line = br.readLine()) != null) {
            aux = line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                aux += line;
            }

            aux = removeDuplicates(aux);
            //System.out.println(aux);
            cont += aux.length();
        }

        System.out.println(cont);

        br.close();
    }

    private static String removeDuplicates(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    if (j < str.length() - 1) { str = str.substring(0, j) + str.substring(j + 1); }
                    else { str = str.substring(0, j); }
                    j--;
                }
            }
        }

        return str;
    }
}
