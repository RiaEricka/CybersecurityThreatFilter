/* import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class DataSet {
    String path = "D:\\Object-Oriented Software Design\\Project3GUI\\src\\Global_Cybersecurity_Threats_2015-2024.csv";
    ArrayList<Threat> L = new ArrayList<>();

    public DataSet() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                L.add(new Threat(values[0], Integer.parseInt(values[1]), values[2], values[3],
                        Double.parseDouble(values[4]), Integer.parseInt(values[5]), values[6], values[7], values[8],
                        Integer.parseInt(values[9])));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Threat> sortByCountry(String country) {
        ArrayList<Threat> countries = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getCountry().equals(country)) {
                countries.add(threat);
            }
        }
        return countries;
    }

    public ArrayList<Threat> sortByYear(int year) {
        ArrayList<Threat> years = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getYear() == year) {
                years.add(threat);
            }
        }
        return years;
    }

    public ArrayList<Threat> sortByAttackType(String attackType) {
        ArrayList<Threat> attackTypes = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getAttackType().equals(attackType)) {
                attackTypes.add(threat);
            }
        }
        return attackTypes;
    }

    public ArrayList<Threat> sortByTargetIndustry(String targetIndustry) {
        ArrayList<Threat> targetIndustries = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getTargetIndustry().equals(targetIndustry)) {
                targetIndustries.add(threat);
            }
        }
        return targetIndustries;
    }

    public ArrayList<Threat> sortByLossInMillions(double lossInMillions) {
        ArrayList<Threat> losses = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getLossInMillions() == lossInMillions) {
                losses.add(threat);
            }
        }
        return losses;
    }

    public ArrayList<Threat> sortByAffectedUsers(int affectedUsers) {
        ArrayList<Threat> users = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getAffectedUsers() == affectedUsers) {
                users.add(threat);
            }
        }
        return users;
    }

    public ArrayList<Threat> sortByAttackSource(String attackSource) {
        ArrayList<Threat> sources = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getAttackSource().equals(attackSource)) {
                sources.add(threat);
            }
        }
        return sources;
    }

    public ArrayList<Threat> sortByVulnerabilityType(String vulnerabilityType) {
        ArrayList<Threat> vulnerabilities = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getVulnerabilityType().equals(vulnerabilityType)) {
                vulnerabilities.add(threat);
            }
        }
        return vulnerabilities;
    }

    public ArrayList<Threat> sortByDefenceMechanism(String defenceMechanism) {
        ArrayList<Threat> defences = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getDefenceMechanism().equals(defenceMechanism)) {
                defences.add(threat);
            }
        }
        return defences;
    }

    public ArrayList<Threat> sortByResolutionType(int resolutionType) {
        ArrayList<Threat> resolutions = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getResoulutionType() == resolutionType) {
                resolutions.add(threat);
            }
        }
        return resolutions;
    }

    public String printList(ArrayList<Threat> arrayList) {
        StringBuilder result = new StringBuilder();
        for (Threat threat : arrayList) {
            result.append(threat.getCountry()).append(" | ")
                  .append(threat.getYear()).append(" | ")
                  .append(threat.getAttackType()).append(" | ")
                  .append(threat.getTargetIndustry()).append(" | Loss: $")
                  .append(threat.getLossInMillions()).append("M | Users: ")
                  .append(threat.getAffectedUsers()).append("\n");
        }
        return result.toString();
    }

    public String Total(ArrayList<Threat> arrayList, Object Filter) {
        String a = Integer.toString(arrayList.size());
        return "There are in total " + a + " of this " + Filter;
    }

    public static void main(String[] args) {
        DataSet dataset = new DataSet();

        String testCountry = "China";
        ArrayList<Threat> threatsInCountry = dataset.sortByCountry(testCountry);

        System.out.println(dataset.printList(threatsInCountry));

        System.out.print((dataset.Total(threatsInCountry,testCountry)));



        if (threatsInCountry.isEmpty()) {
            System.out.print("yes");
        }
    }
}
*/


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class DataSet {
    String path = "D:\\Object-Oriented Software Design\\Project3GUI\\src\\Global_Cybersecurity_Threats_2015-2024.csv";
    ArrayList<Threat> L = new ArrayList<>();

    public DataSet() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            // Skip the header line
            br.readLine();

            int lineNumber = 1; // For debugging
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(",");

                // Skip malformed lines
                if (values.length < 10) {
                    System.err.println("Skipping malformed line " + lineNumber + ": " + line);
                    continue;
                }

                try {
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim(); // remove whitespace
                    }

                    Threat threat = new Threat(
                        values[0],
                        Integer.parseInt(values[1]),
                        values[2],
                        values[3],
                        Double.parseDouble(values[4]),
                        Integer.parseInt(values[5]),
                        values[6],
                        values[7],
                        values[8],
                        Integer.parseInt(values[9])
                    );

                    L.add(threat);

                } catch (NumberFormatException e) {
                    System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                }
            }

            br.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading file: " + path);
            e.printStackTrace();
        }
    }


    public ArrayList<Threat> sortByCountry(String country) {
        ArrayList<Threat> countries = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getCountry().equals(country)) {
                countries.add(threat);
            }
        }
        return countries;
    }

    public ArrayList<Threat> sortByYear(int year) {
        ArrayList<Threat> years = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getYear() == year) {
                years.add(threat);
            }
        }
        return years;
    }

    public ArrayList<Threat> sortByAttackType(String attackType) {
        ArrayList<Threat> attackTypes = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getAttackType().equals(attackType)) {
                attackTypes.add(threat);
            }
        }
        return attackTypes;
    }

    public ArrayList<Threat> sortByTargetIndustry(String targetIndustry) {
        ArrayList<Threat> targetIndustries = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getTargetIndustry().equals(targetIndustry)) {
                targetIndustries.add(threat);
            }
        }
        return targetIndustries;
    }

    public ArrayList<Threat> sortByLossInMillions(double lossInMillions) {
        ArrayList<Threat> losses = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getLossInMillions() == lossInMillions) {
                losses.add(threat);
            }
        }
        return losses;
    }

    public ArrayList<Threat> sortByAffectedUsers(int affectedUsers) {
        ArrayList<Threat> users = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getAffectedUsers() == affectedUsers) {
                users.add(threat);
            }
        }
        return users;
    }

    public ArrayList<Threat> sortByAttackSource(String attackSource) {
        ArrayList<Threat> sources = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getAttackSource().equals(attackSource)) {
                sources.add(threat);
            }
        }
        return sources;
    }

    public ArrayList<Threat> sortByVulnerabilityType(String vulnerabilityType) {
        ArrayList<Threat> vulnerabilities = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getVulnerabilityType().equals(vulnerabilityType)) {
                vulnerabilities.add(threat);
            }
        }
        return vulnerabilities;
    }

    public ArrayList<Threat> sortByDefenceMechanism(String defenceMechanism) {
        ArrayList<Threat> defences = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getDefenceMechanism().equals(defenceMechanism)) {
                defences.add(threat);
            }
        }
        return defences;
    }

    public ArrayList<Threat> sortByResolutionType(int resolutionType) {
        ArrayList<Threat> resolutions = new ArrayList<>();
        for (Threat threat : L) {
            if (threat.getResoulutionType() == resolutionType) {
                resolutions.add(threat);
            }
        }
        return resolutions;
    }

    public String printList(ArrayList<Threat> arrayList) {
        StringBuilder result = new StringBuilder();
        for (Threat threat : arrayList) {
            result.append(threat.getCountry()).append(" | ")
                  .append(threat.getYear()).append(" | ")
                  .append(threat.getAttackType()).append(" | ")
                  .append(threat.getTargetIndustry()).append(" | Loss: $")
                  .append(threat.getLossInMillions()).append("M | Users: ")
                  .append(threat.getAffectedUsers()).append("\n");
        }
        return result.toString();
    }
    // Add similar methods for sorting or filtering by other fields if needed
}
