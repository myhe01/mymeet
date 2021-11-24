/*
 * This class queries information from the database
 */

import java.util.*;
import java.sql.*;
import com.mysql.jdbc.Driver;

public class Query {
	
	final static private String connectionAddress = "jdbc:mysql://localhost:3306/mymeet";
	final static private String connectionUsername = "root";
	final static private String connectionPWord = "Ennis-1998";
	
	static public void isOnline() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		return;
	}
	
	static public int userByUsername(String username) throws ClassNotFoundException, SQLException, UserNotFound {
		int userID;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT userID FROM user WHERE username='" + username+"'");
		if (rs.next()==false) throw new UserNotFound("user not found");
		else userID=rs.getInt(1);
		return userID;
	}
	
	static public User userByUserID(int userID) throws ClassNotFoundException, SQLException, UserNotFound {
		User user = new User();
		Integer i = userID;
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID=" + i.toString());
		if (rs.next()==false) throw new UserNotFound("user not found");
		else {
			user.setUserID(rs.getInt("userID"));
			user.setUserEmail(rs.getNString("userEmail"));
			user.setUserName(rs.getNString("username"));
			user.setPassword(rs.getNString("password"));
			user.setFirstName(rs.getNString("firstName"));
			user.setLastName(rs.getNString("lastname"));
			if (rs.getBoolean("aeronautics")==true) user.addInterest("aeronautics");
			if (rs.getBoolean("anime")==true) user.addInterest("anime");
			if (rs.getBoolean("art")==true) user.addInterest("art");
			if (rs.getBoolean("books")==true) user.addInterest("books");
			if (rs.getBoolean("cars")==true) user.addInterest("cars");
			if (rs.getBoolean("history")==true) user.addInterest("history");
			if (rs.getBoolean("medical")==true) user.addInterest("medical");
			if (rs.getBoolean("moviesAndTV")==true) user.addInterest("moviesAndTV");
			if (rs.getBoolean("music")==true) user.addInterest("music");
			if (rs.getBoolean("nature")==true) user.addInterest("nature");
			if (rs.getBoolean("photography")==true) user.addInterest("photography");
			if (rs.getBoolean("religion")==true) user.addInterest("religion");
			if (rs.getBoolean("school")==true) user.addInterest("school");
			if (rs.getBoolean("sports")==true) user.addInterest("sports");
			if (rs.getBoolean("technology")==true) user.addInterest("technology");
			if (rs.getBoolean("videogames")==true) user.addInterest("videogames");
		}
		return user;
	}
	
	static public void updateUser(User user) throws ClassNotFoundException, SQLException, UserNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Integer aeronautics=0;
		Integer anime=0;
		Integer art=0;
		Integer books=0;
		Integer cars=0;
		Integer history=0;
		Integer medical=0;
		Integer moviesAndTV=0;
		Integer music=0;
		Integer nature=0;
		Integer photography=0;
		Integer religion=0;
		Integer school=0;
		Integer technology=0;
		Integer videogames=0;
		if (user.getInterest().contains("aeronautics")) aeronautics=1;
		if (user.getInterest().contains("anime")) anime=1;
		if (user.getInterest().contains("art")) art=1;
		if (user.getInterest().contains("books")) books=1;
		if (user.getInterest().contains("cars")) cars=1;
		if (user.getInterest().contains("history")) history=1;
		if (user.getInterest().contains("medical")) medical=1;
		if (user.getInterest().contains("moviesAndTV")) moviesAndTV=1;
		if (user.getInterest().contains("music")) music=1;
		if (user.getInterest().contains("nature")) nature=1;
		if (user.getInterest().contains("photography")) photography=1;
		if (user.getInterest().contains("religion")) religion=1;
		if (user.getInterest().contains("school")) school=1;
		if (user.getInterest().contains("technology")) technology=1;
		if (user.getInterest().contains("videogames")) videogames=1;
		Statement stmt = con.createStatement();
		Integer i = user.getUserID();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID=" + i.toString());
		if (rs.next()==false) throw new UserNotFound("user not found");
		stmt.executeUpdate("UPDATE user SET userEmail='"+user.getUserEmail()+"', password='"+user.getPassword()+"', aeronautics="+aeronautics.toString()+", anime="+anime.toString()+", art="+art.toString()+", books="+books.toString()+"cars="+cars.toString()+", history="+history.toString()+", medical="+medical.toString()+", moviesAndTV="+moviesAndTV.toString()+", music="+music.toString()+", nature="+nature.toString()+", photography="+photography.toString()+", religion="+religion.toString()+", school="+school.toString()+", technology="+technology.toString()+", videogames="+videogames.toString()+", firstName='"+user.getFirstName()+"', lastName='"+user.getLastName()+"'");
		return;
	}
	
	static public User addUser(User user) throws ClassNotFoundException, SQLException, UserAlreadyExist{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE username='"+user.getUserName()+"'");
		if (rs.next()==true) throw new UserAlreadyExist("user already exist");
		rs = stmt.executeQuery("SELECT MAX(userID) FROM user");
		if (rs.next()==false) user.setUserID(1);
		else user.setUserID(rs.getInt(1)+1);
		Integer aeronautics=0;
		Integer anime=0;
		Integer art=0;
		Integer books=0;
		Integer cars=0;
		Integer history=0;
		Integer medical=0;
		Integer moviesAndTV=0;
		Integer music=0;
		Integer nature=0;
		Integer photography=0;
		Integer religion=0;
		Integer school=0;
		Integer technology=0;
		Integer videogames=0;
		if (user.getInterest().contains("aeronautics")) aeronautics=1;
		if (user.getInterest().contains("anime")) anime=1;
		if (user.getInterest().contains("art")) art=1;
		if (user.getInterest().contains("books")) books=1;
		if (user.getInterest().contains("cars")) cars=1;
		if (user.getInterest().contains("history")) history=1;
		if (user.getInterest().contains("medical")) medical=1;
		if (user.getInterest().contains("moviesAndTV")) moviesAndTV=1;
		if (user.getInterest().contains("music")) music=1;
		if (user.getInterest().contains("nature")) nature=1;
		if (user.getInterest().contains("photography")) photography=1;
		if (user.getInterest().contains("religion")) religion=1;
		if (user.getInterest().contains("school")) school=1;
		if (user.getInterest().contains("technology")) technology=1;
		if (user.getInterest().contains("videogames")) videogames=1;
		stmt.executeUpdate("INSERT INTO user(userID, userEmail, username, password, aeronautics, anime, art, books, cars, history, medical, moviesAndTV, music, nature, photography, religion, school, sports, technology, videoGames, firstName, lastName) VALUES ("+user.getUserID()+", '"+user.getUserEmail()+"', '"+user.getUserName()+"', '"+user.getPassword()+"', "+aeronautics.toString()+", "+anime.toString()+", "+art.toString()+", "+books.toString()+", "+cars.toString()+", "+history.toString()+", "+medical.toString()+", "+moviesAndTV.toString()+", "+music.toString()+", "+nature.toString()+", "+photography.toString()+", "+religion.toString()+", "+school.toString()+", "+technology.toString()+", "+videogames.toString()+", '"+user.getFirstName()+"', '"+user.getLastName()+"'");
		return user;
	}
	
	static public void deleteuser(int userID) throws ClassNotFoundException, SQLException, UserNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		Integer i = userID;
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+i.toString());
		if (rs.next()==false) throw new UserNotFound("user not found");
		stmt.executeUpdate("DELETE FROM user WHERE userID="+i.toString());
		return;
	}
	
	static public int groupByGroupName(String groupName) throws ClassNotFoundException, SQLException, GroupNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT groupID FROM meetGroup WHERE groupName='"+groupName+"'");
		if (rs.next()==false) throw new GroupNotFound("group not found");
		return rs.getInt("groupID");
	}
	
	static public Group groupByGroupID(int groupID) throws ClassNotFoundException, SQLException, GroupNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		Group group = new Group();
		Integer i = groupID;
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+i.toString());
		if (rs.next()==false) throw new GroupNotFound("group not found");
		group.setGroupID(rs.getInt("groupID"));
		group.setGroupName(rs.getNString("groupName"));
		if (rs.getBoolean("aeronautics")==true) group.addInterest("aeronautics");
		if (rs.getBoolean("anime")==true) group.addInterest("anime");
		if (rs.getBoolean("art")==true) group.addInterest("art");
		if (rs.getBoolean("books")==true) group.addInterest("books");
		if (rs.getBoolean("cars")==true) group.addInterest("cars");
		if (rs.getBoolean("history")==true) group.addInterest("history");
		if (rs.getBoolean("medical")==true) group.addInterest("medical");
		if (rs.getBoolean("moviesAndTV")==true) group.addInterest("moviesAndTV");
		if (rs.getBoolean("music")==true) group.addInterest("music");
		if (rs.getBoolean("nature")==true) group.addInterest("nature");
		if (rs.getBoolean("photography")==true) group.addInterest("photography");
		if (rs.getBoolean("religion")==true) group.addInterest("religion");
		if (rs.getBoolean("school")==true) group.addInterest("school");
		if (rs.getBoolean("sports")==true) group.addInterest("sports");
		if (rs.getBoolean("technology")==true) group.addInterest("technology");
		if (rs.getBoolean("videogames")==true) group.addInterest("videogames");
		group.setMessage(rs.getNString("message"));
		return group;
	}
	
	static public Group createGroup(Group group, int userID) {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupName ='"+group.getGroupName()+"'");
		if (rs.next() == true) throw new GroupAlreadyExist("group already exist");
		rs = stmt.executeQuery("SELECT MAX(groupID) FROM meetGroup");
		if (rs.next() == false) group.setGroupID(1);
		else {
			group.setGroupID(rs.getInt(1)+1);
		}
		Integer aeronautics=0;
		Integer anime=0;
		Integer art=0;
		Integer books=0;
		Integer cars=0;
		Integer history=0;
		Integer medical=0;
		Integer moviesAndTV=0;
		Integer music=0;
		Integer nature=0;
		Integer photography=0;
		Integer religion=0;
		Integer school=0;
		Integer technology=0;
		Integer videogames=0;
		if (group.getInterest().contains("aeronautics")) aeronautics=1;
		if (group.getInterest().contains("anime")) anime=1;
		if (group.getInterest().contains("art")) art=1;
		if (group.getInterest().contains("books")) books=1;
		if (group.getInterest().contains("cars")) cars=1;
		if (group.getInterest().contains("history")) history=1;
		if (group.getInterest().contains("medical")) medical=1;
		if (group.getInterest().contains("moviesAndTV")) moviesAndTV=1;
		if (group.getInterest().contains("music")) music=1;
		if (group.getInterest().contains("nature")) nature=1;
		if (group.getInterest().contains("photography")) photography=1;
		if (group.getInterest().contains("religion")) religion=1;
		if (group.getInterest().contains("school")) school=1;
		if (group.getInterest().contains("technology")) technology=1;
		if (group.getInterest().contains("videogames")) videogames=1;
		rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		stmt.executeUpdate("INSERT INTO meetGroup(groupID, groupName, aeronautics, anime, art, books, cars, history, medical, moviesAndTV, music, nature, photography, religion, school, sports, technology, videoGames, message) VALUES ("+group.getGroupID()+", '"+group.getGroupName()+"', "+aeronautics.toString()+", "+anime.toString()+", "+art.toString()+", "+books.toString()+", "+cars.toString()+", "+history.toString()+", "+medical.toString()+", "+moviesAndTV.toString()+", "+music.toString()+", "+nature.toString()+", "+photography.toString()+", "+religion.toString()+", "+school.toString()+", "+technology.toString()+", "+videogames.toString()+", '"+group.getMessage()+"')");
		stmt.executeUpdate("INSERT INTO groupMembers(userID, groupID, isAdmin) VALUES ("+userID+", "+group.getGroupID()+", "+1+")");
		return group;
	}
	
	static public void updateGroup(Group group, int userID) {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		//TODO
	}
	
	static public void joinGroup(int userID, int groupID) {
		//TODO
	}
	
	static public void makeAdmin(int adminUserID, int userID, int groupID) {
		//TODO
	}
	
	static public void stopAdmin(int userID, int groupID) {
		//TODO
	}
	
	static public void unjoinGroup(int userID, int groupID) {
		//TODO	
	}
	
	static public void banUserFromGroup(int adminUserID, int userID, int groupID) {
		//TODO
	}
	
	static public void unbanUserFromGroup(int adminUserID, int userID, int groupID) {
		//TODO 
	}
	
	static public Event createEvent(Event event) {
		//TODO
	}
	
	static public ArrayList<Integer> eventByEventName(String eventName) {
		//TODO
	}
	
	static public Event eventByEventID(int eventID) {
		//TODO
	}
	
	static public void editEvent(Event event, int userID) {
		//TODO
	}
	
	static public void cancelEvent(int eventID, int userID) {
		//TODO
	}
	
	static public void joinEvent(int eventID, int userID) {
		//TODO
	}
	
	static public void unjoinEvent(int eventID, int userID) {
		//TODO
	}
	
	static public void banUserFromEvent(int creatorID, int userID, int eventID) {
		//TODO
	}
	
	static public void unbanUserFromEvent(int creatorID, int userID, int eventID) {
		//TODO
	}
	
	static public void addImagePath(int eventID, String pathway) {
		//TODO
	}
	
	static public void removeImagePath(int eventID) {
		//TODO
	}
	
	static public int countOfGroups() {
		//TODO
	}
	
	static public String getMessages(int groupID) {
		//TODO
	}
	
	static public void updateMessages(int groupID, String message) {
		//TODO
	}
}

class UserNotFound extends Exception  {
	public UserNotFound(String message) {
		super(message);
	}
}

class UserAlreadyExist extends Exception {
	public UserAlreadyExist(String message) {
		super(message);
	}
}

class GroupNotFound extends Exception {
	public GroupNotFound(String message) {
		super(message);
	}
}

class GroupAlreadyExist extends Exception {
	public GroupAlreadyExist(String message) {
		super(message);
	}
}