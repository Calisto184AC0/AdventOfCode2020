import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 {
    
    public static void main(String[] args) throws Exception {
        String file = "9/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line;

        List<Long> numbers = new ArrayList<>();

        while ((line = br.readLine()) != null) { numbers.add(Long.parseLong(line)); }

        br.close();

        long invNum = decoderXMAS(numbers, 25);
        System.out.println("Invalid number: " + invNum);
        System.out.println("Sum: " + decoderXMAS2Part(numbers, invNum));
    }

    static long decoderXMAS(List<Long> numbers, int preamble) {
        long res = -1;
        int i = 0, j = 0, w = 0, t = 0;
        boolean findSum = false, findNotValid = false;

        for (i = preamble; i < numbers.size() && !findNotValid; i++) {
            findSum = false;
            for (j = t; j < preamble - 1 && !findSum; j++) {
                for (w = j + 1; w < preamble && !findSum; w++) {
                    if (numbers.get(i) == numbers.get(j) + numbers.get(w)) {
                        findSum = true;
                        preamble++;
                        t++;
                    }
                }
            }
            if (j == preamble - 1 && w == preamble && !findSum) {
                findNotValid = true;
                res = numbers.get(i);
            }
        }

        return res;
    }

    static long decoderXMAS2Part(List<Long> numbers, long invNum) {
        long low = 0, hight = 0;
        List<Long> subList;

        for (int i = 0; i < numbers.indexOf(invNum) - 1; i++) {
            for (int j = i + 1; j < numbers.indexOf(invNum); j++) {
                subList = numbers.subList(i, j);
                if (invNum == sumList(subList)) {
                    Collections.sort(subList);
                    low = subList.get(0);
                    hight = subList.get(subList.size() - 1);
                }
            }
        }

        return low + hight;
    }

    private static long sumList(List<Long> list) {
        long res = 0;

        for (int i = 0; i < list.size(); i++) { res += list.get(i); }

        return res;
    }
}
