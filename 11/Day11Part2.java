import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day11Part2 {

    static class WaitingArea {
    
        String[][] matrix;

        WaitingArea(int rows, int cols) {
            matrix = new String[rows][cols];
        }

        void chaosToQuiet() {
            boolean quiet = false;
            String[][] aux = matrix.clone();

            while (!quiet) {
                nextRound();
                //show();
                for (int i = 0; i < aux.length; i++) {
                    for (int j = 0; j < aux[i].length; j++) {
                        if (aux[i][j].equals(matrix[i][j])) {
                            if (i == 0 && j == 0) { quiet = true; }
                            else { quiet &= true; }
                        } else { quiet = false; }
                    }
                }
                aux = matrix.clone();
            }
        }

        void show() {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j]);
                    if (j == matrix[i].length - 1) {System.out.println("");}
                }
            }
            System.out.println("");
        }

        private void nextRound() {
            String[][] aux = new String[matrix.length][matrix[0].length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j].equals("L")) {
                        aux[i][j] = emptyToOccupied(i, j);
                    } else if (matrix[i][j].equals("#")) {
                        aux[i][j] = occupiedToEmpty(i, j);
                    } else {
                        aux[i][j] = ".";
                    }
                }
            }

            matrix = aux.clone();
        }

        int howManyOccupiedSeats() {
            int res = 0;

            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++)
                    if (matrix[i][j].equals("#"))
                        res++;

            return res;
        }

        private String occupiedToEmpty(int i, int j) {
            String res = "#";
            int cont = 0;

            boolean wall = false, seat = false;
            int x = i, y = j;

            // north
            while(!wall && !seat) {
                x--;
                if (x < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // north - east
            while(!wall && !seat) {
                x--; y++;
                if (x < 0 || y > matrix[0].length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // east
            while(!wall && !seat) {
                y++;
                if (y > matrix[0].length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // south - east
            while(!wall && !seat) {
                x++; y++;
                if (x > matrix.length - 1 || y > matrix[0].length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // south
            while(!wall && !seat) {
                x++;
                if (x > matrix.length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // south - west
            while(!wall && !seat) {
                x++; y--;
                if (x > matrix.length - 1 || y < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // west
            while(!wall && !seat) {
                y--;
                if (y < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;


            // north - west
            while(!wall && !seat) {
                x--; y--;
                if (x < 0 || y < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            if (cont >= 5) { res = "L"; }

            return res;
        }

        private String emptyToOccupied(int i, int j) {
            String res = "L";
            int cont = 0;

            boolean wall = false, seat = false;
            int x = i, y = j;

            // north
            while(!wall && !seat) {
                x--;
                if (x < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // north - east
            while(!wall && !seat) {
                x--; y++;
                if (x < 0 || y > matrix[0].length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // east
            while(!wall && !seat) {
                y++;
                if (y > matrix[0].length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // south - east
            while(!wall && !seat) {
                x++; y++;
                if (x > matrix.length - 1 || y > matrix[0].length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // south
            while(!wall && !seat) {
                x++;
                if (x > matrix.length - 1) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // south - west
            while(!wall && !seat) {
                x++; y--;
                if (x > matrix.length - 1 || y < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;

            // west
            while(!wall && !seat) {
                y--;
                if (y < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            wall = seat = false;
            x = i; y = j;


            // north - west
            while(!wall && !seat) {
                x--; y--;
                if (x < 0 || y < 0) { wall = true; }
                else if (matrix[x][y].equals("#")) {
                    seat = true;
                    cont++;
                } else if (matrix[x][y].equals("L")) {
                    seat = true;
                }
            }

            if (cont == 0) { res = "#"; }

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
                area.matrix[i][j] = Character.toString(rows.get(i).charAt(j));
            }
        }

        //area.show();
        area.chaosToQuiet();
        System.out.println(area.howManyOccupiedSeats());
    }
}
