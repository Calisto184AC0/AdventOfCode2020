import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day8Part1 {
    
    public static void main(String[] args) throws Exception {
        String file = "8/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line, decoder[] = new String[2];
        
        int pc,     // program counter
            acc = 0,    // accumulator
            operation = 0,  // operation: -1 -> nop; 0 -> acc; 1 -> jmp
            argument = 0;   // argument
        List<Integer> memory = new ArrayList<>(); // saves lines visited
        List<String> ins = new ArrayList<>(); // instructions
        boolean inf = false;    // infinite loop found

        while ((line = br.readLine()) != null) { ins.add(line); }

        br.close();

        // CPU
        for (pc = 0; pc < ins.size() && !inf; pc++) {
            // saves pc
            memory.add(pc);

            // Decoder
            decoder = ins.get(pc).split(" ");
            argument = Integer.parseInt(decoder[1]);

            switch (decoder[0]) {
                case "nop":
                    operation = -1;
                    break;

                case "acc":
                    operation = 0;
                    break;

                case "jmp":
                    operation = 1;
                    break;
                
                default:
                    break;
            }

            // ALU
            // acc
            if (operation == 0) {
                acc += argument;
            }
            
            // jmp
            if (operation == 1) {
                if (memory.contains(pc + argument)) { inf = true; }
                else { pc += argument - 1; }
            }
        }

        System.out.println(acc);
    }
}
