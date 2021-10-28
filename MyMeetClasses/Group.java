// @Entity @Table(name = "Groups")
import java.util.*;

public class Group {
    private String groupID;
    private boolean isActive;
    private String[] interestsList;

    public Group() {}

    public Group(String groupID, boolean isActive, String[] interestsList)
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

    public String[] getInterestsList() { return interestsList; }
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
}

