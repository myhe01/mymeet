# Query Class Documentation
This is stuff Charles is working on.  
Interest.java is unfinished but can easily be finished as soon as the list of interests is complete. It will convert the database stored integer to and from an ArrayList.  
database rough draft.txt is the COMPLETED draft of the MySQL input needed to create our database.

## Database Calls and Descriptions
`Query.userByUsername(String username)`  
Returns an integer value equal to the *userID* of the user with the username *username*. Throws UserNotFound exception if a User with a username of *username* does not exist (surround with try/catch).  

`Query.userByUserID(int userID)`  
Returns an Object of class User that contains the information from the database. Throws UserNotFound exception if the integer of *userID* does not exist (surround with try/catch).  

`Query.updateUser(User user)`  
Void method. If *user*'s userID already exists, the database will replace all the fields in the database with the values in the class. Throws UserNotFound exception if *user*'s userID does not exist (surround with try/catch).  

`Query.addUser(User user)`  
Returns an Object of class User that is exactly the same as the input object except for the userID, which will now be updated to equal the one in the database. Throws UserAlreadyExist exception if *user*'s username already exists (surround with try/catch).  

`Query.deleteUser(int userID)`  
Void method. If *userID* exists, user is deleted from the database.
Throws UserNotFound exception if *userID* does not exist (surround with try/catch).  

`Query.groupByGroupName(String groupName)`  
Returns an integer value equal to the groupID of the group with the group name *groupName*. Throws GroupNotFound exception if group with a groupName of *groupName* does not exist (surround with try/catch).  

`Query.groupByGroupID(int groupID)`
Returns an Object of class Group that contains the information from the database. Throws GroupNotFound exception if *groupID* does not exist (surround with try/catch).  

`Query.createGroup(Group group, int userID)`
Returns an object of type Group that is the same as *group* except that GroupID will now reflect the database. Creates a group in the database with contents of *group*. *groupMembers* entry created in database between *userID*, *groupID* and *isAdmin* set to true. Throws GroupAlreadyExist exception if a group with *groupName* already exists in the database. Throws UserNotFound exception if no user with *userID* exists (surround with try/catch).  

`Query.updateGroup(Group group, int userID)`
Void method. Changes the group entry in the database with *group*'s ID with the contents of *group*. Throws GroupNotFound exception if *group* does not exist in database. Throws NotAdmin exception if *userID* is not an admin of *group* (surround with try/catch).  

`Query.joinGroup(int userID, int groupID)`
Void method. Creates new entry in groupMembers of relationship between *userID* and *groupID* with isAdmin set to false. Throws UserNotFound exception if *userID* does not exist. Throws GroupNotFound exception if *groupID* does not exist. Throws UserBanned exception if relationship between *userID* and *groupID* found in kickedFromGroup. Throws AlreadyJoinedGroup exception if relationship between *userID* and *groupID* found in groupMembers (surround with try/catch).

`Query.makeAdmin(int adminUserID, int userID, int groupID)`
Void method. Updates the relationship between *userID* and *groupID* to be admin. Throws GroupNotFound exception if *groupID* does not exist. Throws NotAdmin exception if relationship in groupMembers *adminUserID* and *groupID* has isAdmin = false (or relationship doesn't exist, or *adminUserID* doesn't exist). Throws NotGroupMember exception if relationship in groupMembers *userID* and *groupID* doesn't exist (or *userID* doesn't exist) (surround with try/catch).

## TODO:

`Query.stopAdmin`

`Query.unjoinGroup`

`Query.banUserFromGroup`

`Query.unbanUserFromGroup`

`Query.createEvent`

`Query.eventByEventName`

`Query.eventByEventID`

`Query.editEvent`

`Query.cancelEvent`

`Query.joinEvent`

`Query.unjoinEvent`

`Query.banUserFromEvent`

`Query.unbanUserFromEvent`

`Query.addImage`

`Query.removeImage`