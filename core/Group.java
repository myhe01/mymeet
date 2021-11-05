// @Entity @Table(name = "Groups")
import java.util.*;

public class Group {
    private String groupID;
    private boolean isActive;
    private ArrayList<String> interestsList;

    public Group() {}

    public Group(String groupID, boolean isActive, ArrayList<String> interestsList)
    {
        this.setGroupID(groupID);
        this.setIsActive(isActive);
        this.setInterestsList(interestsList);
    }

    // Getters&&Setters
    public String getGroupID() { return groupID; }
    public void setGroupID(String groupID) { this.groupID = groupID; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public ArrayList<String> getInterestsList() { return interestsList; }
    public void setInterestsList(String... interestsList)
    {
        // TODO: Add functionality to setInterests
    }

    // CRUD
    // TODO: add functionality between database and functions
    public static void addGroup() {}
    public static ArrayList<Group> retrieveAllGroups() { return new ArrayList<>(); }
    public static Group retrieveGroup() { return new Group(); }
    public static void updateGroupName() {}
    public static void updateGroupInterests() {}
    public static void deleteGroup() {}
    public static void clearGroupData() {}
    public static void createTestGroup() {}

    // Match groups to users:
    // Given a user, retrieve a number of random groups from the database and order their group IDs
    // most to least similar. Returns an ordered ArrayList of group IDs.
    public ArrayList matchGroup(String userID) {
        final int MAX_GROUPS = 50;                  // Number of groups wanted queried from db
        ArrayList<Group> groups = null;
        ArrayList interests;                        // FIXME: ArrayList?
        User user;
        String temp;
        Random rand = new Random();
        int i;
        int numGroups;                              // Number of groups current in db

        // No user ID
        if (userID.isBlank()) {
            return groups;
        }

        // Check if ID valid
        try {
            user = Query.userByUserID(userID);
        } 

        // ID invalid
        catch (UserNotFound e) {
            return groups;
        }

        interests = User.getInterests();
        groups = new ArrayList<Groups>();

        // Try to query group and add to list, up to max number of groups, "skip" loop if a group isn't found
        for (i = 0; i < MAX_GROUPS; i++) {
            try {
                groups.add(Query.groupByGroupID(rand.nextInt(numGroups)));
            }
            
            catch (GroupNotFound e) {
                i--;
            }
        }

        for (i = 0)

    }
}

