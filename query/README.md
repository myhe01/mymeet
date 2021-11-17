/# Query Class Documentation
This is stuff Charles is working on.  
Interest.java is unfinished but can easily be finished as soon as the list of interests is complete. It will convert the database stored integer to and from an ArrayList.  
database rough draft.txt is the COMPLETED draft of the MySQL input needed to create our database.

## Database Calls and Descriptions
`Query.isOnline(void)`  
Checks if database is accessible. Throws SQLException exception if unable to access (surround with try/catch).  

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

`Query.stopAdmin(int userID, int groupID)`  
Void method. Updates the relationship between *userID* and *groupID* to be not admin iff they are not the last admin.  Throws GroupNotFound exception if *groupID* does not exist. Throws LastAdmin exception if there are no other admins for that group. Throws NotAdmin exception if relationship in groupMembers *UserID* and *groupID* has isAdmin = false. Throws NotGroupMember exception if relationship in groupMembers *userID* and *groupID* doesn't exist (or *userID* doesn't exist) (surround with try/catch).

`Query.unjoinGroup(int userID, int groupID)`  
Void method.  Removes the realationship between *userID* and *groupID* in groupMembers. Throws GroupNotFound exception if *groupID* does not exist.  Throws UserNotFound exception if *userID* does not exist.  Throws NotGroupMember exception if the relationship doesn't exist.  Throws isAdmin exception if the relationship exists but the user is an Admin.

`Query.banUserFromGroup(int adminUserID, int userID, int groupID)`  
Void method. Adds relationship between *userID* and *groupID* in bannedFromGroup. Throws GroupNotFound exception if *groupID* does not exist. Throws UserNotFound exception if *userID* doesn't exist.  Throws UserNotFound exception if *adminUserID* doesn't exist. Throws NotAdmin exception if relationship in groupMembers *adminUserID* and *groupID* has isAdmin = false (or relationship doesn't exist, or *adminUserID* doesn't exist). Throws alreadyBanned exception if relationship in bannedFromGroup *userID* and *groupID* exists (surround with try/catch).

`Query.unbanUserFromGroup(int adminUserID, int userID, int groupID)`  
Void method. Removes relationship between *userID* and *groupID* in bannedFromGroup. Throws GroupNotFound exception if *groupID* does not exist. Throws UserNotFound exception if *userID* doesn't exist.  Throws UserNotFound exception if *adminUserID* doesn't exist. Throws NotAdmin exception if relationship in groupMembers *adminUserID* and *groupID* has isAdmin = false (or relationship doesn't exist, or *adminUserID* doesn't exist). Throws notBanned exception if relationship in bannedFromGroup *userID* and *groupID* doesn't exist (surround with try/catch).

`Query.createEvent(Event event)`  
Returns an Object of class Event that is exactly the same as the input object except for the eventID, which will now be updated to equal the one in the database. No Errors thrown.

`Query.eventByEventName(String eventName)`  
Returns an arraylist of integers (can be size 1 or more) that contains all the eventID's that have a name that matches *eventName*.  If there are no events with that name, throws EventNotFound exception. (surround with try/catch)

`Query.eventByEventID(int eventID)`  
 Returns an Object of class Event that contains the information from the database. Throws EventNotFound exception if *eventID* does not exist (surround with try/catch).

`Query.editEvent(Event event, int userID)`  
Void Method.  Replaces the event in the database with eventID = *event.eventID* with all the information in the class passed to it. Throws EventNotFound exception if *event.eventID* doesn't exist. Throws NotCreator exception if *userID* is not the creator or doesn't exist. (surround with try/catch)

`Query.cancelEvent(int eventID, int userID)`  
Void Method. Delets the event.  Throws EventNotFound exception if the event doesn't exist.  Throws NotCreator exception if userID is not the event creator or doesn't exist.(surround with try/catch)

`Query.joinEvent(int eventID, int userID)`  
Void Method. creates an joining event relationship between *userID* and *eventID*.  Throws EventNotFound exception if *eventID* doesn't exist.  Throws UserNotFound exception if *userID* doesn't exist.  Throws AlreadyJoined exception if the relationship already exists. (surround with try/catch)

`Query.unjoinEvent(int eventID, int userID)`  
Void method. deletes a relationship join between *userID* and *eventID*. Throws EventNotFound exception if *eventID* doesn't exist.  Throws UserNotFound exception if *userID* doesn't exist.  Throws NotJoined exception if the relationship doesn't exist. (surround with try/catch)

`Query.banUserFromEvent(int creatorID, int userID, int eventID)`  
void method. Add relationship between *userID* and *eventID* in bannedFromEvent.  Throws NotCreator exception if *creatorID* is not the creator of *eventID* or of *creatorID* doesn't exist.  Throws UserNotFound exception if *userID* doesn't exist. Throws AlreadyBanned exception if relationship already exists. (surround with try/catch)

`Query.unbanUserFromEvent(int creatorID, int userID, int eventID)`  
void method. delete relationship between *userID* and *eventID* in bannedFromEvent.  Throws NotCreator exception if *creatorID* is not the creator of *eventID* or of *creatorID* doesn't exist.  Throws UserNotFound exception if *userID* doesn't exist. Throws NotBanned exception if relationship doesn't exist. (surround with try/catch)

`Query.addImagePath(int eventID, String pathway)`  
void method.  changes the *eventID* image path to the *pathway*.  Throws EventNotFound exception if *eventID* doesn't exist. (surround with try/catch)

`Query.removeImagePath(int eventID)`  
void method. changes the *eventID* image path to NULL.  Throws EventNotFound exception if *eventID* doesn't exist. (surround with try/catch)

`Query.countOfGroups()`  
Returns an integer value equal to the number of groups in the database.  no exceptions throwable

`Query.getMessages(int groupID)`  
Returns an object of class String of the messages for the group, if *groupID* doesn't exist, throws GroupNotFound exception

`Query.updateMessages(int groupID, String messages)`  
sets the messages column of *groupID* to the string of *messages*.  if *groupID* doesn't exist, throws GroupNotFound exception
