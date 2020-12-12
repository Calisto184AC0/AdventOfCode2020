import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day11Part1 {

    static class WaitingArea {
    
        String[][] matrix;

        WaitingArea(int rows, int cols) {
            this.matrix = new String[rows + 2][cols + 2];
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++)
                    if (i == 0 || i == matrix.length - 1 || j == 0 || j == matrix[i].length - 1) { matrix[i][j] = "."; }
        }

        void chaosToQuiet() {
            boolean quiet = false;
            String[][] aux = matrix.clone();

            while (!quiet) {
                nextRound();
                //show();
                for (int i = 1; i < aux.length - 1; i++) {
                    for (int j = 1; j < aux[i].length - 1; j++) {
                        if (aux[i][j].equals(matrix[i][j])) {
                            if (i == 1 && j == 1) { quiet = true; }
                            else { quiet &= true; }
                        } else { quiet = false; }
                    }
                }
                aux = matrix.clone();
            }
        }

        void nextRound() {
            String[][] nextMatrix = new String[matrix.length][matrix[0].length];

            // ini nextMatrix
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    nextMatrix[i][j] = ".";
                }
            }

            for (int i = 1; i < matrix.length - 1; i++) {
                for (int j = 1; j < matrix[i].length - 1; j++) {
                    if (matrix[i][j].equals("L")) {
                        nextMatrix[i][j] = emptyToOccupied(i, j);
                    } else if (matrix[i][j].equals("#")) {
                        nextMatrix[i][j] = occupiedToEmpty(i, j);
                    } else {
                        nextMatrix[i][j] = ".";
                    }
                }
            }
            matrix = nextMatrix.clone();
        }

        int howManyOccupiedSeats() {
            int res = 0;

            for (int i = 1; i < matrix.length - 1; i++)
                for (int j = 1; j < matrix[i].length - 1; j++)
                    if (matrix[i][j].equals("#"))
                        res++;

            return res;
        }

        void show() {
            for (int i = 1; i < matrix.length - 1; i++) {
                for (int j = 1; j < matrix[i].length - 1; j++) {
                    System.out.print(matrix[i][j]);
                    if (j == matrix[i].length - 2) {System.out.println("");}
                }
            }
            System.out.println("");
        }

        // i = rowNum; j = colNum
        private String emptyToOccupied(int i, int j) {
            String res = "L";
            int cont = 0;

            if (matrix[i - 1][j - 1].equals("#") || matrix[i - 1][j].equals("#") || matrix[i - 1][j + 1].equals("#")
                || matrix[i][j - 1].equals("#") || matrix[i][j + 1].equals("#")
                || matrix[i + 1][j - 1].equals("#") || matrix[i + 1][j].equals("#") || matrix[i + 1][j + 1].equals("#")) { cont++; }

            if (cont == 0) {
                res = "#";
            }

            return res;
        }

        // i = rowNum; j = colNum
        private String occupiedToEmpty(int i, int j) {
            String res = "#";
            int cont = 0;

            if (matrix[i - 1][j - 1].equals("#")) { cont++; }
            if (matrix[i - 1][j].equals("#")) { cont++; }
            if (matrix[i - 1][j + 1].equals("#")) { cont++; }
            
            if (matrix[i][j - 1].equals("#")) { cont++; }
            if (matrix[i][j + 1].equals("#")) { cont++; }
            
            if (matrix[i + 1][j - 1].equals("#")) { cont++; }
            if (matrix[i + 1][j].equals("#")) { cont++; }
            if (matrix[i + 1][j + 1].equals("#")) { cont++; }
            
            if (cont >= 4) {
                res = "L";
            }

            return res;
        }
    }

    public static void main(String[] args) throws Exception {
        String file = "11/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;

        List<String> rows = new ArrayList<>();

        while ((line = br.readLine()) != null) { rows.add(line); }

        br.close();

        WaitingArea area = new WaitingArea(rows.size(), rows.get(0).length());

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).length(); j++) {
                area.matrix[i + 1][j + 1] = Character.toString(rows.get(i).charAt(j));
            }
        }

        //area.show();
        area.chaosToQuiet();
        System.out.println(area.howManyOccupiedSeats());
    }
}
