import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin_Page {
    public static void addQuizQuestions() throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        JSONParser parser = new JSONParser();
        String filepath = "./src/main/resources/quiz.json";

        JSONArray quizArray = (JSONArray) parser.parse(new FileReader(filepath));

        //Adding question

        while (true) {
            System.out.println("Input your question");
            String question = input.nextLine();

            System.out.println("Input option 1:");
            String option1 = input.nextLine();

            System.out.println("Input option 2:");
            String option2 = input.nextLine();

            System.out.println("Input option 3:");
            String option3 = input.nextLine();

            System.out.println("Input option 4:");
            String option4 = input.nextLine();


            //for getting correct answer
            int answerKey;
            while (true) {
                System.out.println("What is the answer? (1-4)");
                answerKey = input.nextInt();
                input.nextLine();

                if (answerKey >= 1 && answerKey <= 4) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number between 1 and 4.");
                }
            }

            //adding question to quiz.json

            JSONObject quesObj = new JSONObject();
            quesObj.put("Question", question);
            quesObj.put("option 1", option1);
            quesObj.put("option 2", option2);
            quesObj.put("option 3", option3);
            quesObj.put("option 4", option4);
            quesObj.put("answerKey", answerKey);

            quizArray.add(quesObj);

            FileWriter writer = new FileWriter(filepath);
            writer.write(quizArray.toJSONString());
            writer.flush();
            writer.close();
            System.out.println("Saved Successfully!");

            //Asking admin to continue adding questions or quit
            String ask;

            while (true) {
                System.out.println("Do you want to add more questions?(Press 's' to start adding,'q' to quit)");
                ask = input.nextLine();

                if (ask.equalsIgnoreCase("s")) {
                    break;
                } else if (ask.equalsIgnoreCase("q")) {
                    return;
                } else {
                    System.out.println("Invalid input.Please press 's' to start adding more or 'q' to quit.");
                }

            }
        }

    }
}

