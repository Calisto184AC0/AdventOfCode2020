import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day12Part1 {
    
    public static void main(String[] args) throws Exception {
        String file = "12/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;

        List<Instruction> insts = new ArrayList<>();

        while ((line = br.readLine()) != null) { insts.add(new Instruction(line)); }

        br.close();

        Ship myShip = new Ship('E');
        myShip.doInstructions(insts);
        myShip.ManhattanDistance();
    }

    static class Instruction {
    
        char op;
        int arg;

        Instruction(String line) {
            op = line.charAt(0);
            arg = Integer.parseInt(line.substring(1));
        }
    }

    static class Ship {
    
        int actualFacing; // in degrees
        int n_s, e_w;

        Ship(char facing) {
            if (facing == 'N') { actualFacing = 0; }
            else if (facing == 'S') { actualFacing = 180; }
            else if (facing == 'E') { actualFacing = 90; }
            else if (facing == 'W') { actualFacing = 270; }
            n_s = e_w = 0;
        }

        void doInstructions(List<Instruction> instList) {
            for (int i = 0; i < instList.size(); i++) {
                doInstruction(instList.get(i));
            }
        }

        void doInstruction(Instruction instr) {
            if (instr.op == 'N') {
                n_s += instr.arg;
            } else if (instr.op == 'S') {
                n_s -= instr.arg;
            } else if (instr.op == 'E') {
                e_w += instr.arg;
            } else if (instr.op == 'W') {
                e_w -= instr.arg;
            } else if (instr.op == 'L') {
                actualFacing -= instr.arg;
                if (actualFacing < 0) {
                    actualFacing = 360 + actualFacing;
                }
                actualFacing %= 360;
            } else if (instr.op == 'R') {
                actualFacing += instr.arg;
                actualFacing %= 360;
            } else if (instr.op == 'F') {
                if (actualFacing == 0) { n_s += instr.arg; }
                else if (actualFacing == 180) { n_s -= instr.arg; }
                else if (actualFacing == 90) { e_w += instr.arg; }
                else if (actualFacing == 270) { e_w -= instr.arg; }
            }
        }

        void ManhattanDistance() {
            System.out.println(Math.abs(n_s) + Math.abs(e_w));
        }
    }
}
