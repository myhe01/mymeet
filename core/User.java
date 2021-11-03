// @Entity @Table(name = "Users")
import java.util.*;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String userEmail;
    private String password;
    private String userID;
    private String[] interestsList;

    public User(){};
    public User(String firstName, String lastName, String userName, String userEmail, String password, String userID, String... interestsList)
    {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setUserEmail(userEmail);
        this.setPassword(password);
        this.setUserID(userID);
        this.setInterests(interestsList);
    }
    
    // Getter&&Setters
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
    
    public String[] getInterests(){ return interestsList; }                         // FIXME: switch to ArrayLists? -Brendan
    public void setInterests(String... interestLists)
    {
        // TODO: Add functionality to setInterests
    };

   

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