import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Student_Page {
    public static void quiz() throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        String filepath = "./src/main/resources/quiz.json";
        JSONParser parser = new JSONParser();
        JSONArray quizArray = (JSONArray) parser.parse(new FileReader(filepath));

        while (true) {
            String ask = input.nextLine();

            if (!ask.equalsIgnoreCase("s")) {
                if (ask.equalsIgnoreCase("q")) {
                    break;
                }
                System.out.println("Invalid! Please press 's' to start or 'q' to quit");
                continue;
            }
            int score = 0;
            Random random = new Random();
            HashSet<Integer> askQues = new HashSet<>();

            for (int i = 0; i < 10; i++) {
                int randomIndex;
                do {
                    randomIndex = random.nextInt(quizArray.size());
                } while (askQues.contains(randomIndex));

                askQues.add(randomIndex);

                JSONObject quesObj = (JSONObject) quizArray.get(randomIndex);

                System.out.println("[question " + (i + 1) + "] " + quesObj.get("Question"));
                System.out.println("1. " + quesObj.get("option 1"));
                System.out.println("2. " + quesObj.get("option 2"));
                System.out.println("3. " + quesObj.get("option 3"));
                System.out.println("4. " + quesObj.get("option 4"));
                System.out.println("Answer: ");

                int userAnswer = input.nextInt();
                input.nextLine();

                int correctAnswer = ((Long) quesObj.get("answerKey")).intValue();
                if (userAnswer == correctAnswer) {
                    score++;
                }
            }
            System.out.println("You got " + score + "out of 10.");
            if (score >= 8) {
                System.out.println("Excellent!");
            } else if (score >= 5) {
                System.out.println("Good.");
            } else if (score >= 2) {
                System.out.println("Very poor.");
            } else {
                System.out.println("Very sorry,you are failed");
            }

            System.out.println("Would Would you like to start again? Press 's' to start or 'q' to quit.");
            ask = input.nextLine();

            if (ask.equalsIgnoreCase("q")) {
                break;
            }
        }

    }
}
