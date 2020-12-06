import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day5Part1 {

    public static int processBoardingPass(String boardingPass) {
        String rows = boardingPass.substring(0, 7);
        String cols = boardingPass.substring(7);
        double nRows[] = {0, 127}, nCols[] = {0, 7}, aux, row = 0, col = 0;

        // process rows
        for (int i = 0; i < rows.length(); i++) {
            if (i < rows.length() - 1) {
                aux = Math.ceil((nRows[1] - nRows[0]) / 2);
                if (rows.charAt(i) == 'F') {
                    nRows[1] -= aux;
                } else {
                    nRows[0] += aux;
                }
            } else {
                if (rows.charAt(i) == 'F') {
                    row = nRows[0];
                } else {
                    row = nRows[1];
                }
            }
        }

        // process cols
        for (int i = 0; i < cols.length(); i++) {
            if (i < cols.length() - 1) {
                aux = Math.ceil(nCols[1] - nCols[0]) / 2;
                if (cols.charAt(i) == 'L') {
                    nCols[1] -= aux;
                } else {
                    nCols[0] += aux;
                }
            } else {
                if (cols.charAt(i) == 'L') {
                    col = nCols[0];
                } else {
                    col = nCols[1];
                }
            }
        }

        return (int) (row * 8 + col);
    }

    public static void main(String[] args) throws Exception {
        String file = "5/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;
        List<Integer> boardingPassIds = new ArrayList<>();

        System.out.println(processBoardingPass("FBFBBFFRLR"));

        while ((line = br.readLine()) != null) { boardingPassIds.add(processBoardingPass(line)); }

        boardingPassIds.sort((c1, c2) -> c1.compareTo(c2));
        System.out.println(boardingPassIds.get(boardingPassIds.size() - 1));
        br.close();
    }
}
