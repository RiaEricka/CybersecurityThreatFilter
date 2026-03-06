import javax.swing.*;
import java.awt.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class ThreatFilterGUI extends JFrame {
    private DataSet dataset;

    public ThreatFilterGUI() {
        dataset = new DataSet();

        setTitle("Global Cybersecurity Threats");
        setSize(1250, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Components
        JLabel filterLabel = new JLabel("Select a filter:");
        filterLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        String[] filters = {
            "Country", "Year", "Attack Type", "Target Industry", "Loss in Millions",
            "Affected Users", "Attack Source", "Vulnerability Type", "Defense Mechanism", "Resolution Type"
        };
        JComboBox<String> filterBox = new JComboBox<>(filters);
        filterBox.setFont(new Font("SansSerif", Font.PLAIN,12));

        JLabel specifierLabel = new JLabel("Select a specifier:");
        specifierLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        JComboBox<String> specifierBox = new JComboBox<>();
        specifierBox.setFont(new Font("SansSerif", Font.PLAIN,12));

        JButton filterButton = new JButton("Filter");

        JLabel resultHeader = new JLabel("Filter the results above.", JLabel.CENTER);
        resultHeader.setFont(new Font("SansSerif", Font.BOLD, 15));

        JTextArea resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 12));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JPanel topPanel = new JPanel();
        topPanel.add(filterLabel);
        topPanel.add(filterBox);
        topPanel.add(specifierLabel);
        topPanel.add(specifierBox);
        topPanel.add(filterButton);

        add(topPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(resultHeader, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton saveButton = new JButton("Save as .txt file");
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);



        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save filtered results");
        
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".txt");
                    
                    java.io.FileWriter writer = new java.io.FileWriter(fileToSave);
                    writer.write(resultArea.getText());
                    writer.close();
        
                    JOptionPane.showMessageDialog(this, "File saved.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }
            }
        });
        

        filterBox.addActionListener(e -> {
            String selectedFilter = (String) filterBox.getSelectedItem();
            specifierBox.removeAllItems();
            Set<String> options = new TreeSet<>();

            for (Threat t : dataset.L) {
                switch (selectedFilter) {
                    case "Country":
                        options.add(t.getCountry());
                        break;
                    case "Year":
                        options.add(String.valueOf(t.getYear()));
                        break;
                    case "Attack Type":
                        options.add(t.getAttackType());
                        break;
                    case "Target Industry":
                        options.add(t.getTargetIndustry());
                        break;
                    case "Loss in Millions":
                        options.add(String.valueOf(t.getLossInMillions()));
                        break;
                    case "Affected Users":
                        options.add(String.valueOf(t.getAffectedUsers()));
                        break;
                    case "Attack Source":
                        options.add(t.getAttackSource());
                        break;
                    case "Vulnerability Type":
                        options.add(t.getVulnerabilityType());
                        break;
                    case "Defense Mechanism":
                        options.add(t.getDefenceMechanism());
                        break;
                    case "Resolution Type":
                        options.add(String.valueOf(t.getResoulutionType()));
                        break;
                }
            }

            for (String option : options) {
                specifierBox.addItem(option);
            }
        });

        filterBox.setSelectedIndex(0);

        filterButton.addActionListener(e -> {
            String filter = (String) filterBox.getSelectedItem();
            String specifier = (String) specifierBox.getSelectedItem();

            if (specifier == null || specifier.isEmpty()) {
                resultHeader.setText("All results");
                StringBuilder allSb = new StringBuilder();
                String format = "%-12s %-8s %-20s %-20s %-10s %-15s %-15s %-20s %-20s %-8s\n";
                allSb.append(String.format(format,
                "Country", "Year", "Attack Type", "Target Industry", "Loss($M)",
                "Users Affected", "Source", "Vulnerability", "Defense", "Resolution Time(hrs)"
            ));
            allSb.append("=".repeat(168)).append("\n");
            for (Threat t : dataset.L) {
                allSb.append(String.format(format,
                    t.getCountry(),
                    t.getYear(),
                    t.getAttackType(),
                    t.getTargetIndustry(),
                    t.getLossInMillions(),
                    t.getAffectedUsers(),
                    t.getAttackSource(),
                    t.getVulnerabilityType(),
                    t.getDefenceMechanism(),
                    t.getResoulutionType()
                ));
            }
        
            resultArea.setText(allSb.toString());
            resultArea.setCaretPosition(0);
            return;
        }

            ArrayList<Threat> results = new ArrayList<>();

                switch (filter) {
                    case "Country":
                        results = dataset.sortByCountry(specifier);
                        break;
                    case "Year":
                        results = dataset.sortByYear(Integer.parseInt(specifier));
                        break;
                    case "Attack Type":
                        results = dataset.sortByAttackType(specifier);
                        break;
                    case "Target Industry":
                        results = dataset.sortByTargetIndustry(specifier);
                        break;
                    case "Loss in Millions":
                        results = dataset.sortByLossInMillions(Double.parseDouble(specifier));
                        break;
                    case "Affected Users":
                        results = dataset.sortByAffectedUsers(Integer.parseInt(specifier));
                        break;
                    case "Attack Source":
                        results = dataset.sortByAttackSource(specifier);
                        break;
                    case "Vulnerability Type":
                        results = dataset.sortByVulnerabilityType(specifier);
                        break;
                    case "Defense Mechanism":
                        results = dataset.sortByDefenceMechanism(specifier);
                        break;
                    case "Resolution Type":
                        results = dataset.sortByResolutionType(Integer.parseInt(specifier));
                        break;
                }


            resultHeader.setText("Showing results for " + filter + " - " + specifier);

            String format = "%-12s %-8s %-20s %-20s %-10s %-15s %-15s %-20s %-20s %-8s\n";
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(format,
                "Country", "Year", "Attack Type", "Target Industry", "Loss($M)",
                "Users Affected", "Source", "Vulnerability", "Defense", "Resolution Time(hrs)"
            ));
            sb.append("=".repeat(169)).append("\n");

            for (Threat threat : results) {
                sb.append(String.format(format,
                    threat.getCountry(),
                    threat.getYear(),
                    threat.getAttackType(),
                    threat.getTargetIndustry(),
                    threat.getLossInMillions(),
                    threat.getAffectedUsers(),
                    threat.getAttackSource(),
                    threat.getVulnerabilityType(),
                    threat.getDefenceMechanism(),
                    threat.getResoulutionType()
                ));
            }

            if (results.isEmpty()) {
                resultArea.setText("No results found.");
            } else {
                resultArea.setText(sb.toString());
                resultArea.setCaretPosition(0);
                if (results.size() > 1) {
                    showMessageDialog(this, "There are " + results.size() + " threats of this specified filter.", "Summary Report", JOptionPane.INFORMATION_MESSAGE);
                } else showMessageDialog(this, "There is " + results.size() + " threat of this specified filter.", "Summary Report", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });

        setVisible(true);

    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThreatFilterGUI());
    }
}
