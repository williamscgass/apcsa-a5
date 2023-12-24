import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private String filepath;
    private int totalPoints;
    private int achievedPoints = 0;

    public Logger(String filepath, int totalPoints) {
        this.filepath = filepath;
        this.totalPoints = totalPoints;

        // open file and clear contents
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResult(String testName, int numPoints, String params, String expected, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            writer.append("Test name: " + testName + "\n");
            writer.append("Points awarded: " + numPoints + "\n");
            writer.append("Arguments: " + params + "\n");
            writer.append("Expected: " + expected + "\n");
            writer.append("Result: " + result + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTotalPoints() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            writer.append("Overall Test Results: " + this.achievedPoints + "/" + this.totalPoints + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void incPoints(int points) {
        this.achievedPoints += points;
    }
}