import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4Part1 {
    
    static class Passport {
    
        String byr, iyr, eyr, hgt, hcl, ecl, pid, cid;

        Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid, String cid) {
            this.byr = byr;
            this.iyr = iyr;
            this.eyr = eyr;
            this.hgt = hgt;
            this.hcl = hcl;
            this.ecl = ecl;
            this.pid = pid;
            this.cid = cid;
        }

        public static Passport tryNewPassport(Pair[] pairs) {
            Passport res = null;

            int cont = 0;
            for (Pair pair : pairs) {
                if (!pair.value.isEmpty()) { cont++; }
            }

            if (cont == 8 || (cont == 7 && pairs[7].value.isEmpty())) { res = new Passport(pairs[0].value, pairs[1].value, pairs[2].value, pairs[3].value, pairs[4].value, pairs[5].value, pairs[6].value, pairs[7].value); }

            return res;
        }
    }

    static class Pair {
    
        String key, value;

        Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static Pair[] iniPasswordKeys() {
            Pair[] res = new Pair[8];

            res[0] = new Pair("byr", "");
            res[1] = new Pair("iyr", "");
            res[2] = new Pair("eyr", "");
            res[3] = new Pair("hgt", "");
            res[4] = new Pair("hcl", "");
            res[5] = new Pair("ecl", "");
            res[6] = new Pair("pid", "");
            res[7] = new Pair("cid", "");

            return res;
        }
    }

    public static void main(String[] args) throws Exception {
        String file = "4/input";
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        String line, saux;
        String[] values;
        Pair[] pairs;
        Pair paux;
        List<Passport> passports = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            saux = line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                saux += " " + line;
            }

            values = saux.split("[ ]");
            if (values.length >= 7) {
                pairs = Pair.iniPasswordKeys();

                for (String value : values) {
                    paux = new Pair(value.split("[:]")[0], value.split("[:]")[1]);
                    for (Pair pair : pairs) {
                        if (paux.key.equals(pair.key))
                            pair.value = paux.value;
                    }
                }
                
                if (Passport.tryNewPassport(pairs) != null)
                    passports.add(Passport.tryNewPassport(pairs));
            }
        }

        System.out.println(passports.size());
        br.close();

    }
}