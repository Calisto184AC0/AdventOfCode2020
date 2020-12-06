import java.io.BufferedReader;
import java.io.FileReader;

public class Day6Part2 {
    
    public static void main(String[] args) throws Exception {
        String file = "6/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line, aux;
        int cont = 0;

        while ((line = br.readLine()) != null) {
            aux = line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                aux += " " + line;
            }

            //System.out.println(aux + "    " + findDuplicates(aux));
            cont += findDuplicates(aux);
        }

        System.out.println(cont);

        br.close();
    }

    private static int findDuplicates(String str) {
        String[] strs = str.split("[ ]");
        int cont = 0, contAux;

        if (strs.length == 1) { cont = strs[0].length(); }
        else {
            for (int i = 0; i < strs[0].length(); i++) {
                contAux = 1;
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].contains("" + strs[0].charAt(i))) { contAux++; }
                    if (contAux == strs.length) { cont++; }
                }
            }
        }

        return cont;
    }
}
