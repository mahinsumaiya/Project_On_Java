import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Home_Page{
    public static void main(String[] args) throws IOException, ParseException {
        User_Login userLogin=null;

        while(userLogin==null){
            userLogin=User_Login.login();
        }
        if (userLogin.getRole().equals("admin")){
            System.out.println("Welcome admin! Please create new questions in the question bank.");
            Admin_Page.addQuizQuestions();
        } else if (userLogin.getRole().equals("student")) {
            System.out.println("Welcome " +userLogin.getUsername()+ " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
            Student_Page.quiz();
        }

    }
    
}
