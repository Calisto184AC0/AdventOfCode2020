import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10Part1 {

    public static void main(String[] args) throws Exception {
        String file = "10/input1";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;

        List<Integer> numbers = new ArrayList<>();

        while ((line = br.readLine()) != null) { numbers.add(Integer.parseInt(line)); }

        br.close();

        numbers.add(0);
        Collections.sort(numbers);
        numbers.add(numbers.get(numbers.size() - 1) + 3);
        
        List<Integer> diff1 = new ArrayList<>(), diff3 = new ArrayList<>();

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) + 1 == numbers.get(i + 1)) { diff1.add(numbers.get(i)); }
            else if (numbers.get(i) + 3 == numbers.get(i + 1)) { diff3.add(numbers.get(i)); }
        }

        System.out.println(diff1.size() * diff3.size());
    }
}