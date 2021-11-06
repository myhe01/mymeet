// @Entity @Table(name = "Users")
import java.util.*;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String userEmail;
    private String password;
    private String userID;
    private ArrayList<String> interestsList;

    public User(){};
    // FIXME: ArrayList<String>
    public User(String firstName, String lastName, String userName, String userEmail, String password, String userID, ArrayList<String> interestsList)
    {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setUserEmail(userEmail);
        this.setPassword(password);
        this.setUserID(userID);
        this.setInterests(interestsList);
    }
    
    // Getters and Setters
    public String getFirstName() {return firstName; }
    public void setFirstName(String firstName) {this.firstName = firstName; }
    public String getLastName() {return lastName; }
    public void setLastName(String lastName) {this.lastName = lastName; }
    public String getUserName() {return userName; }
    public void setUserName(String userName) {this.userName = userName; }
    public String getUserEmail() {return userEmail; }
    public void setUserEmail(String userEmail) {this.userEmail = userEmail; }
    public String getPassword() {return password; }
    public void setPassword(String password) {this.password = password; }
    public String getUserID() {return userID; }
    public void setUserID(String userID) {this.userID = userID; }
    public ArrayList<String> getInterests(){ return interestsList; }
    public void setInterests(ArrayList<String> interests)
    {
        this.interestsList = new ArrayList<String>();

        for (String s: interests) {
            interestsList.add(s);
        }
    }

    public void addInterest(String interest) {
        if (interestsList == null) {
            interestsList = new ArrayList<String>();
        }

        interestsList.add(interest);
    }


   

    // Create, Read, Update, and Delete functions (CRUD)
    // TODO: add functionality between database and functions
    public static void add(){}
    public static ArrayList<User> retrieveAllUsers(){ return new ArrayList<>(); }
    public static User retrieve(){ return new User(); }
    public static void updateUser(){}
    // public static void updateSettings(){}
    public static void deleteUser(){}
    public static void clearUserData(){}
    public static void createTestUser(){}
}