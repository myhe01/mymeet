// @Entity @Table(name = "Groups")
import java.util.*;

public class Group {
    private String groupID;
    private String groupName;
    private boolean isActive;
    private ArrayList<String> interestsList;
    private String message;

    public Group() {}

    public Group(String groupID, boolean isActive, ArrayList<String> interestsList)
    {
        this.setGroupID(groupID);
        this.setIsActive(isActive);
        this.setInterestsList(interestsList);
    }

    // Getters and setters
    public String getGroupID() {return groupID;}
    public void setGroupID(String groupID) {this.groupID = groupID;}
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(boolean isActive) {this.isActive = isActive;}
    public String getGroupName() {return groupName;}
    public void setGroupName(String groupName) {this.groupName = groupName;}
    public ArrayList<String> getInterestsList() {return interestsList;}
    public String getMessage() {return message;}
    public void setMessage() {this.message = message; }

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

    // Match groups to users:
    // Given a user ID, retrieve a number of random groups from the database and order their group IDs
    // most to least similar. Returns an ordered ArrayList of group IDs.
    public ArrayList matchGroup(String userID) {
        final int MAX_GROUPS = 50;                  // Number of groups wanted queried from db
        final int NUM_INTERESTS = 16;               // Number of interests (TODO: replace with function that counts lines in INTERESTS.lst)
        Random rand = new Random();
        ArrayList<Group> groups = null;             // Return this ArrayList
        ArrayList<String> groupInterests;         // List of each group's interests
        ArrayList<String> userInterests;            // List of user's interests
        ArrayList<Integer> groupWeight;             // Weight of groups
        ArrayList<Group> groupSorted = new ArrayList<Group>();
        User user;
        int i, j;
        int numGroups = Query.countofGroups();      // Number of groups current in db
        int numInterests;                           // Number of interests from the user

        // No user ID or no groups
        if (userID.isBlank() || (numGroups <= 0)) {
            return groups;
        }

        // Get User; check if ID valid
        try {
            user = Query.userByUserID(userID);
        } 

        // ID is invalid
        catch (UserNotFound e) {
            return groups;
        }

        // Get the list of the user's interests, generate new ArrayLists
        userInterests = user.getInterests();
        groups = new ArrayList<Group>();
        groupInterests = new ArrayList<String>;
        groupWeight = new ArrayList<Integer>();

        // Try to query group and add to list, up to max number of groups
        // Add group's interests to ArrayList array of interests
        // "Skip" loop if a group isn't found
        for (i = 0; i < MAX_GROUPS; i++) {
            try {
                groups.add(Query.groupByGroupID(rand.nextInt(numGroups - 1)) + 1);
            }
            
            catch (GroupNotFound e) {
                i--;
            }
        }

        // Get number of user interests
        numInterests = userInterests.size();

        // User has no interests, return random assortment of groups
        if (numInterests <= 0) {
            return groups;
        }

        // Assign weights
        // Increase weight by 1 if user interest matches any group interest
        // Iterate thru user interests
        for (i = 0; i < MAX_GROUPS; i++) {
            // Get group i's interest list
            groupInterests = groups.get(i).getInterestsList();
            groupWeight.add(0);

            // Iterate through user's interest list to see if it matches any of group i's interests
            for (j = 0; j < numInterests; j++) {
                if (groupInterests.contains(userInterests.get(j))) {  
                    groupWeight.set(i, (groupWeight.get(i)) + 1);
                }
            }
        }

        // Runs while there's still groups
        while (!(groups.isEmpty())) {
            for (i = NUM_INTERESTS; i >= 0; i--) {
                for (j = 0; j < groups.size(); j++) {
                    if (groupWeight.get(j) == i) {
                        groupSorted.add(groups.get(j));
                        groups.remove(j);
                        groupWeight.remove(j);
                    }
                }  
            }
        }

        return groupSorted;

        /* Weights explained:
        The group at groups[n] has its matching weight at groupWeight[n].
        */
    }
}

