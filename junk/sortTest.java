import java.util.*;

public class sortTest {
    public static void main(String[] args) {        
        User userTest = new User();
        Group groupTest1 = new Group();
        Group groupTest2 = new Group();

        userTest.setUserID("0");
        userTest.addInterest("Anime");
        userTest.addInterest("Movies & TV");
        userTest.addInterest("Sports");
        userTest.addInterest("Technology");
        userTest.addInterest("Video Games");

        groupTest1.setGroupID("1");
        groupTest1.addInterest("Anime");
        groupTest1.addInterest("School");
        groupTest1.addInterest("Sports");

        groupTest2.setGroupID("2");
        groupTest2.addInterest("Anime");
        groupTest2.addInterest("Movies & TV");
        groupTest2.addInterest("Technology");
        groupTest2.addInterest("Video Games");


        // ----------------------------------------------------------------------------------------

        final int MAX_GROUPS = 2;                   // Number of groups wanted queried from db
        final int NUM_INTERESTS = 16;               // Number of interests (TODO: replace with function that counts lines in INTERESTS.lst)
        Random rand = new Random();
        ArrayList<Group> groups = new ArrayList<Group>();             // Return this ArrayList
        ArrayList<String> groupInterests;           // List of a group's interests
        ArrayList<String> userInterests;            // List of user's interests
        ArrayList<Integer> groupWeight;             // Weight of groups
        ArrayList<Group> groupSorted = new ArrayList<Group>();
//      User user;
        int i, j;
        int size;
        int numGroups = 2;                          // Number of groups current in db
        int numInterests;                           // Number of interests from the user

        // Get the list of the user's interests, generate new ArrayLists
        userInterests = userTest.getInterests();
        groups = new ArrayList<Group>();
        groupInterests = new ArrayList<String>();
        groupWeight = new ArrayList<Integer>();

        // Try to query group and add to list, up to max number of groups
        // Add group's interests to ArrayList array of interests
        // "Skip" loop if a group isn't found
        groups.add(groupTest1);
//      groupInterests = groupTest1.getInterestsList()); 

        groups.add(groupTest2);
//      groupInterests.set(1, groupTest2.getInterestsList());

        /*
        for (i = 0; i < MAX_GROUPS; i++) {
            try {
                groups.add();
                groupInterests[i] = (group.get(i)).getInterestsList();
            }
            
            catch (GroupNotFound e) {
                i--;
            }
        }
        */

        // Get number of user interests
        numInterests = userInterests.size();

        /*
        // User has no interests, return random assortment of groups
        if (numInterests <= 0) {
            return groups;
        }
        */

        // Assign weights
        // Increase weight by 1 if user interest matches any group interest
        // Iterate thru user interests
        for (i = 0; i < MAX_GROUPS; i++) {
            // Get size of group i's interest list
            size = groups.get(i).getInterestsList().size();

            // Get group i's interest list
            groupInterests = groups.get(i).getInterestsList();

            groupWeight.add(0);

            System.out.println("## Group " + (i + 1));
            System.out.println(">> Group interests: " + groupInterests.toString());

            // Iterate through user's interest list to see if it matches any of group i's interests
            for (j = 0; j < numInterests; j++) {
                System.out.println(">> User interest: " + userInterests.get(j));

                if (groupInterests.contains(userInterests.get(j))) {
                    
                    System.out.println(">> Group " + i + " match for " + userInterests.get(j));
                    groupWeight.set(i, (groupWeight.get(i)) + 1);
                }
            }
        }


        System.out.println("Group 1 Interests: " + groupTest1.getInterestsList());
        System.out.println("Group 2 Interests: " + groupTest2.getInterestsList());
        System.out.println("User Interests:    " + userTest.getInterests());

        System.out.println("Weight for group 1: " + groupWeight.get(0) + " (" + groups.get(0) + ")");
        System.out.println("Weight for group 2: " + groupWeight.get(1) + " (" + groups.get(1) + ")");

        // Runs while there's still groups
        while (!(groups.isEmpty())) {
            for (i = NUM_INTERESTS; i >= 0; i--) {
                for (j = 0; j < groups.size(); j++) {
                    if (groupWeight.get(j) == i) {
                        System.out.println(">> Adding " + groups.get(j));
                        groupSorted.add(groups.get(j));
                        groups.remove(j);
                        groupWeight.remove(j);
                    }
                }  
            }
        }

        System.out.println("Groups sorted: " + groupSorted);
        System.out.println("groupSorted[0]: " + groupSorted.get(0).getInterestsList());
        System.out.println("groupSorted[1]: " + groupSorted.get(1).getInterestsList());

        // ----------------------------------------------------------------------------------------
    }
}

class Group {
    private String groupID;
//  private boolean isActive;
    private ArrayList<String> interestsList;

    public Group() {}

    public Group(String groupID, boolean isActive, ArrayList<String> interestsList)
    {
        this.setGroupID(groupID);
//      this.setIsActive(isActive);
        this.setInterestsList(interestsList);
    }

    // Getters and setters
    public String getGroupID() { return groupID; }
    public void setGroupID(String groupID) { this.groupID = groupID; }
//  public Boolean getIsActive() { return isActive; }
//  public void setIsActive(boolean isActive) { this.isActive = isActive; }
    public ArrayList<String> getInterestsList() { return interestsList; }
    public void setInterestsList(ArrayList<String> interests) {
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
}

class User {
//  private String firstName;
//  private String lastName;
//  private String userName;
//  private String userEmail;
//  private String password;
    private String userID;
    private ArrayList<String> interestsList;

    public User(){};
    public User(/*String firstName, String lastName, String userName, String userEmail, String password, */String userID, ArrayList<String> interestsList)
    {
//      this.setFirstName(firstName);
//      this.setLastName(lastName);
//      this.setUserName(userName);
//      this.setUserEmail(userEmail);
//      this.setPassword(password);
        this.setUserID(userID);
        this.setInterests(interestsList);
    }
    
    // Getter&&Setters
//  public String getFirstName() {return firstName; }
//  public void setFirstName(String firstName) {this.firstName = firstName; }
//  public String getLastName() {return lastName; }
//  public void setLastName(String lastName) {this.lastName = lastName; }
//  public String getUserName() {return userName; }
//  public void setUserName(String userName) {this.userName = userName; }
//  public String getUserEmail() {return userEmail; }
//  public void setUserEmail(String userEmail) {this.userEmail = userEmail; }
//  public String getPassword() {return password; }
//  public void setPassword(String password) {this.password = password; }
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
}
