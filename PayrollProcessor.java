package day_3_lab_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayrollProcessor {
	private static final Logger logger = LoggerFactory.getLogger(PayrollProcessor.class);

    public static void main(String[] args) {
        String inputFile = "employees.txt";
        String outputFile = "processed_employees.txt";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String processedRecord = processRecord(line, lineNumber);
                    writer.write(processedRecord);
                    writer.newLine();
                    logger.info("Successfully processed record at line {}: {}", lineNumber, line);
                } catch (InvalidEmployeeRecordException e) {
                    logger.error("Invalid record: {}", e.getMessage());
                }
            }

        } catch (IOException e) {
            logger.error("Error reading or writing file: {}", e.getMessage(), e);
        }
    }

    private static String processRecord(String line, int lineNumber) throws InvalidEmployeeRecordException {
        String[] parts = line.split(",");

        if (parts.length < 4) {
            throw new InvalidEmployeeRecordException(lineNumber, "Incomplete record");
        }

        // Validate EmployeeID
        int id;
        try {
            id = Integer.parseInt(parts[0].trim());
            if (id <= 0) {
                throw new InvalidEmployeeRecordException(lineNumber, "EmployeeID must be positive");
            }
        } catch (NumberFormatException e) {
            throw new InvalidEmployeeRecordException(lineNumber, "EmployeeID must be numeric");
        }

        // Validate Name
        String name = parts[1].trim();
        if (name.isEmpty()) {
            throw new InvalidEmployeeRecordException(lineNumber, "Name must not be empty");
        }

        // Validate BasicSalary
        double basicSalary;
        try {
            basicSalary = Double.parseDouble(parts[2].trim());
            if (basicSalary <= 0) {
                throw new InvalidEmployeeRecordException(lineNumber, "BasicSalary must be > 0");
            }
        } catch (NumberFormatException e) {
            throw new InvalidEmployeeRecordException(lineNumber, "BasicSalary must be numeric");
        }

        // Validate Bonus
        double bonus;
        try {
            bonus = Double.parseDouble(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new InvalidEmployeeRecordException(lineNumber, "Bonus must be numeric");
        }

        double netSalary = basicSalary + bonus;
        return id + "," + name + "," + basicSalary + "," + bonus + "," + netSalary;
    }
}
