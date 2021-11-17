/*
 * This class queries information from the database
 */

import java.util.ArrayList;
import java.sql.*;
import com.mysql.jdbc.Driver;

public class Query {
	
	public void isOnline() throws SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
		Statement stmt = con.createStatement();
	}
	
	public int userByUsername(String username) throws ClassNotFoundException, SQLException, UserNotFound {
		int userID;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT userID FROM user WHERE username='" + username+"'");
		if (rs.next()==false) throw new UserNotFound("user not found");
		else userID=rs.getInt(1);
		return userID;
	}
	
	public User userByUserID(int userID) throws ClassNotFoundException, SQLException, UserNotFound {
		User user = new User();
		Integer i = userID;
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
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
	
	public void updateUser(User user) throws ClassNotFoundException, SQLException, UserNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
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
	
	public User addUser(User user) throws ClassNotFoundException, SQLException, UserAlreadyExist{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
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
		stmt.executeUpdate("INSERT INTO user(userID, userEmail, username, password, aeronautics, anime, art, books, cars, history, medical, moviesAndTV, music, nature, photography, religion, school, sports, technology, videoGames, firstName, lastName) VALUES ("+user.getUserID+", '"+user.getUserEmail()+"', '"+user.getUserName()+"', '"+user.getPassword()+"', aeronautics="+aeronautics.toString()+", anime="+anime.toString()+", art="+art.toString()+", books="+books.toString()+"cars="+cars.toString()+", history="+history.toString()+", medical="+medical.toString()+", moviesAndTV="+moviesAndTV.toString()+", music="+music.toString()+", nature="+nature.toString()+", photography="+photography.toString()+", religion="+religion.toString()+", school="+school.toString()+", technology="+technology.toString()+", videogames="+videogames.toString()+", firstName='"+user.getFirstName()+"', lastName='"+user.getLastName()+"'");
		return user;
	}
	
	public void deleteuser(int userID) throws ClassNotFoundException, SQLException, UserNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
		Statement stmt = con.createStatement();
		Integer i = userID;
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+i.toString());
		if (rs.next()==false) throw new UserNotFound("user not found");
		stmt.executeUpdate("DELETE FROM user WHERE userID="+i.toString());
		return;
	}
	
	public int groupByGroupName(String groupName) throws ClassNotFoundException, SQLException, GroupNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT groupID FROM meetGroup WHERE groupName='"+groupName+"'");
		if (rs.next()==false) throw new GroupNotFound("group not found");
		return rs.getInt("groupID");
	}
	
	public Group groupByGroupID(int groupID) throws ClassNotFoundException, SQLException, GroupNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mymeet","root","Ennis-1998");
		Statement stmt = con.createStatement();
		Group group = new Group();
		Integer i = groupID;
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+i.toString());
		if (rs.next()==false) throw new GroupNotFound("group not found");
		
		//TODO
	}
	
	public Group createGroup(Group group, int userID) {
		//TODO
	}
	
	public void updateGroup(Group group, int userID) {
		//TODO
	}
	
	public void joinGroup(int userID, int groupID) {
		//TODO
	}
	
	public void makeAdmin(int adminUserID, int userID, int groupID) {
		//TODO
	}
	
	public void stopAdmin(int userID, int groupID) {
		//TODO
	}
	
	public void unjoinGroup(int userID, int groupID) {
		//TODO	
	}
	
	public void banUserFromGroup(int adminUserID, int userID, int groupID) {
		//TODO
	}
	
	public void unbanUserFromGroup(int adminUserID, int userID, int groupID) {
		//TODO 
	}
	
	public Event createEvent(Event event) {
		//TODO
	}
	
	public ArrayList<Integer> eventByEventName(String eventName) {
		//TODO
	}
	
	public Event eventByEventID(int eventID) {
		//TODO
	}
	
	public void editEvent(Event event, int userID) {
		//TODO
	}
	
	public void cancelEvent(int eventID, int userID) {
		//TODO
	}
	
	public void joinEvent(int eventID, int userID) {
		//TODO
	}
	
	public void unjoinEvent(int eventID, int userID) {
		//TODO
	}
	
	public void banUserFromEvent(int creatorID, int userID, int eventID) {
		//TODO
	}
	
	public void unbanUserFromEvent(int creatorID, int userID, int eventID) {
		//TODO
	}
	
	public void addImagePath(int eventID, String pathway) {
		//TODO
	}
	
	public void removeImagePath(int eventID) {
		//TODO
	}
	
	public int countOfGroups() {
		//TODO
	}
	
	public String getMessages(int groupID) {
		//TODO
	}
	
	public void updateMessages(int groupID, String message) {
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