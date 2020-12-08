import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day8Part2 {
    
    static class Instruction {
        int operation, argument;

        Instruction(String ins) {
            switch (ins.split(" ")[0]) {
                case "nop":
                    this.operation = -1;
                    break;

                case "acc":
                    this.operation = 0;
                    break;

                case "jmp":
                    this.operation = 1;
                    break;
                
                default:
                    break;
            }

            argument = Integer.parseInt(ins.split(" ")[1]);
        }
    }

    public static void main(String[] args) throws Exception {
        String file = "8/input";
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));

        String line;
        List<Instruction> ins = new ArrayList<>();

        while ((line = br.readLine()) != null) { ins.add(new Instruction(line)); }

        br.close();

        int pc, acc = 0;
        List<Integer> memory = new ArrayList<>();
        Instruction instruction;

        // save actual state
        int index = -1, auxAcc = -1;
        List<Integer> auxMemory = new ArrayList<>(), tests = new ArrayList<>();

        for (pc = 0; pc < ins.size(); pc++) {
            instruction = ins.get(pc);
            memory.add(pc);

            switch (instruction.operation) {
                // acc instruction
                case 0:
                    acc += instruction.argument;
                    break;
                
                // jmp instruction
                case 1:
                    if (index < 0) {        // no change
                        if (!tests.contains(pc) && instruction.argument < 0) {  // jmp to nop
                            index = pc;
                            auxAcc = acc;
                            auxMemory = new ArrayList<>(memory);
                            tests.add(pc);

                            // nop
                        } else {
                            if (memory.contains(pc + instruction.argument)) {
                                // loop
                                pc = index - 1;
                                index = -1;
                                acc = auxAcc;
                                memory.clear();
                                memory = new ArrayList<>(auxMemory);
                                memory.remove(memory.size() - 1);
                                auxMemory.clear();
                            } else {
                                pc += instruction.argument - 1;
                            }
                        }
                    } else {                // there is a change
                        if (memory.contains(pc + instruction.argument)) {
                            // loop
                            pc = index - 1;
                            index = -1;
                            acc = auxAcc;
                            memory.clear();
                            memory = new ArrayList<>(auxMemory);
                            memory.remove(memory.size() - 1);
                            auxMemory.clear();
                        } else {
                            pc += instruction.argument - 1;
                        }
                    }

                    break;
                
                // nop instruction
                default:
                    if (index < 0 && !tests.contains(pc) && instruction.argument > 0) { // nop to jmp
                        index = pc;
                        auxAcc = acc;
                        auxMemory = new ArrayList<>(memory);
                        tests.add(pc);

                        // jmp
                        if (memory.contains(pc + instruction.argument)) {
                            // loop
                            pc = index - 1;
                            index = -1;
                            acc = auxAcc;
                            memory.clear();
                            memory = new ArrayList<>(auxMemory);
                            memory.remove(memory.size() - 1);
                            auxMemory.clear();
                        } else {
                            pc += instruction.argument - 1;
                        }
                    }
                    break;
            }
        }
        
        System.out.println("Total: " + acc);
    }
}
