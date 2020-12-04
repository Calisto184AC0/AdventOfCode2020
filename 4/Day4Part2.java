import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4Part2 {
    
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
                switch (pair.key) {
                    case "byr":
                        if (pair.value.isEmpty()) { break; }

                        if (Integer.parseInt(pair.value) <= 2002 && Integer.parseInt(pair.value) >= 1920) { cont++; }

                        break;
                    
                    case "iyr":
                        if (pair.value.isEmpty()) { break; }

                        if (Integer.parseInt(pair.value) <= 2020 && Integer.parseInt(pair.value) >= 2010) { cont++; }

                        break;
                    
                    case "eyr":
                        if (pair.value.isEmpty()) { break; }

                        if (Integer.parseInt(pair.value) <= 2030 && Integer.parseInt(pair.value) >= 2020) { cont++; }

                        break;
                    
                    case "hgt":
                        if (pair.value.isEmpty()) { break; }

                        if (pair.value.contains("cm")) {
                            if (Integer.parseInt(pair.value.substring(0, pair.value.length() - 2)) <= 193  
                                && Integer.parseInt(pair.value.substring(0, pair.value.length() - 2)) >= 150) { cont++; }
                        }
                        if (pair.value.contains("in")) {
                            if (Integer.parseInt(pair.value.substring(0, pair.value.length() - 2)) <= 76  
                                && Integer.parseInt(pair.value.substring(0, pair.value.length() - 2)) >= 59) { cont++; }
                        }

                        break;
                    
                    case "hcl":
                        if (pair.value.isEmpty()) { break; }

                        if (pair.value.substring(0,1).equals("#") && pair.value.substring(1).matches("[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]")) { cont++; }

                        break;
                    
                    case "ecl":
                        if (pair.value.isEmpty()) { break; }

                        if (pair.value.equals("amb")
                            || pair.value.equals("blu")
                            || pair.value.equals("brn")
                            || pair.value.equals("gry")
                            || pair.value.equals("grn")
                            || pair.value.equals("hzl")
                            || pair.value.equals("oth")) { cont++; }

                        break;
                    
                    case "pid":
                        if (pair.value.isEmpty()) { break; }

                        if (pair.value.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) { cont++; }

                        break;
                    
                    case "cid":
                        cont++;
                        break;
                    
                    default:
                        break;
                }
            }

            if (cont == 8) { res = new Passport(pairs[0].value, pairs[1].value, pairs[2].value, pairs[3].value, pairs[4].value, pairs[5].value, pairs[6].value, pairs[7].value); }

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
