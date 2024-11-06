import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class User_Login {
    private  String username;
    private  String password;
    private String role;

    public User_Login(String username, String password, String role) {
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public static User_Login login() throws IOException, ParseException {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your username");
        String username=input.nextLine();
        System.out.println("Enter password:");
        String password=input.nextLine();

        //getting info from users.json
        String filepath="./src/main/resources/users.json";
        JSONParser parser=new JSONParser();
        JSONArray arrayuser=(JSONArray) parser.parse(new FileReader(filepath));

       //getting object from arrayuser of users.json file
        for (Object userObj:arrayuser){
            JSONObject jsonUser=(JSONObject) userObj;
            if (jsonUser.get("username").equals(username) && jsonUser.get("password").equals(password)){
                return new User_Login(username,password,(String)jsonUser.get("role"));
            }
        }


        System.out.println("Invalid credentials!Please,try again.");
        return null;
    }

    public String getRole(){
        return this.role;
    }
    public String getUsername(){
        return this.username;
    }

}
