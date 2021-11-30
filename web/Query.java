/*
 * This class queries information from the database
 */

import java.util.*;
import java.sql.*;
// import com.mysql.jdbc.Driver;

public class Query {
	
	final static private String connectionAddress = "jdbc:mysql://localhost:3306/mymeet";
	final static private String connectionUsername = "root";
	final static private String connectionPWord = "root";
	
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
		stmt.executeUpdate("UPDATE user SET userEmail='"+user.getUserEmail()+"', password='"+user.getPassword()+"', aeronautics="+aeronautics.toString()+", anime="+anime.toString()+", art="+art.toString()+", books="+books.toString()+"cars="+cars.toString()+", history="+history.toString()+", medical="+medical.toString()+", moviesAndTV="+moviesAndTV.toString()+", music="+music.toString()+", nature="+nature.toString()+", photography="+photography.toString()+", religion="+religion.toString()+", school="+school.toString()+", technology="+technology.toString()+", videogames="+videogames.toString()+", firstName='"+user.getFirstName()+"', lastName='"+user.getLastName()+"' WHERE userID="+user.getUserID());
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
	
	static public Group createGroup(Group group, int userID) throws ClassNotFoundException, SQLException, GroupAlreadyExist, UserNotFound{
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
		stmt.executeUpdate("INSERT INTO groupMembers(userID, groupID, isAdmin) VALUES ("+userID+", "+group.getGroupID()+", 1)");
		return group;
	}
	
	static public void updateGroup(Group group, int userID) throws ClassNotFoundException, SQLException, GroupNotFound, NotAdmin{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+group.getGroupID());
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("Select * FROM groupMembers WHERE userID="+userID+" AND groupID="+group.getGroupID()+" AND isAdmin=1");
		if (rs.next() == false) throw new NotAdmin("not admin");
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
		stmt.executeUpdate("UPDATE meetGroup SET groupName='"+group.getGroupName()+"', aeronautics="+aeronautics.toString()+", anime="+anime.toString()+", art="+art.toString()+", books="+books.toString()+"cars="+cars.toString()+", history="+history.toString()+", medical="+medical.toString()+", moviesAndTV="+moviesAndTV.toString()+", music="+music.toString()+", nature="+nature.toString()+", photography="+photography.toString()+", religion="+religion.toString()+", school="+school.toString()+", technology="+technology.toString()+", videogames="+videogames.toString()+", message='"+group.getMessage()+"' WHERE groupID="+group.getGroupID());
		return;
	}
	
	static public void joinGroup(int userID, int groupID) throws ClassNotFoundException, SQLException, UserNotFound, GroupNotFound, UserBanned, AlreadyJoinedGroup{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT * FROM kickedFromGroup WHERE userID="+userID+" AND groupID="+groupID);
		if (rs.next() == true) throw new UserBanned("user banned");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE userID="+userID+" AND groupID"+groupID);
		if (rs.next() == true) throw new AlreadyJoinedGroup("already joined group");
		stmt.executeUpdate("INSERT INTO groupMembers(userID, groupID, isAdmin) VALUES ("+userID+", "+groupID+", 0)");
		return;
	}
	
	static public void makeAdmin(int adminUserID, int userID, int groupID) throws ClassNotFoundException, SQLException, GroupNotFound, NotAdmin, NotGroupMember{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE userID="+adminUserID+" AND groupID="+groupID+" AND isAdmin=1");
		if (rs.next() == false) throw new NotAdmin("not admin");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE groupID="+groupID+" AND userID="+userID);
		if (rs.next() == false) throw new NotGroupMember("not group member");
		stmt.executeUpdate("UPDATE groupMembers SET isAdmin=1 WHERE userID="+userID+" AND groupID="+groupID);
		return;
	}
	
	static public void stopAdmin(int userID, int groupID) throws ClassNotFoundException, SQLException, GroupNotFound, LastAdmin, NotAdmin, NotGroupMember{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE groupID="+groupID+" AND isAdmin=1 AND NOT userID="+userID);
		if (rs.next() == false) throw new LastAdmin("last admin");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE groupID="+groupID+" AND userID="+userID);
		if (rs.next() == false) throw new NotGroupMember("not group member");
		stmt.executeUpdate("UPDATE groupMembers SET isAdmin=0 WHERE userID="+userID+" AND groupID="+groupID);
		return;
	}
	
	static public void unjoinGroup(int userID, int groupID) throws ClassNotFoundException, SQLException, GroupNotFound, UserNotFound, NotGroupMember, IsAdmin{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE groupID="+groupID+" AND userID="+userID);
		if (rs.next() == false) throw new NotGroupMember("not group member");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE groupID="+groupID+" AND userID="+userID+" AND isAdmin=1");
		if (rs.next() == true) throw new IsAdmin("is admin");
		stmt.executeUpdate("DELETE FROM groupMemebrs WHERE groupID="+groupID+" AND userID="+userID);
		return;
	}
	
	static public void banUserFromGroup(int adminUserID, int userID, int groupID) throws ClassNotFoundException, SQLException, GroupNotFound, UserNotFound, NotAdmin, AlreadyBanned{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM user where userID="+adminUserID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE groupID="+groupID+" AND userID="+adminUserID+" AND isAdmin=1");
		if (rs.next() == false) throw new NotAdmin("not admin");
		rs = stmt.executeQuery("SELECT * FROM kickedFromGroup WHERE groupID="+groupID+" AND userID="+userID);
		if (rs.next() == true) throw new AlreadyBanned("already banned");
		stmt.executeUpdate("INSERT INTO kickedFromGroup(userID, groupID) VALUES ("+userID+", "+groupID+")");
		return;
	}
	
	static public void unbanUserFromGroup(int adminUserID, int userID, int groupID) throws ClassNotFoundException, SQLException, GroupNotFound, UserNotFound, NotAdmin, NotBanned{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM user where userID="+adminUserID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM groupMembers WHERE groupID="+groupID+" AND userID="+adminUserID+" AND isAdmin=1");
		if (rs.next() == false) throw new NotAdmin("not admin");
		rs = stmt.executeQuery("SELECT * FROM kickedFromGroup WHERE groupID="+groupID+" AND userID="+userID);
		if (rs.next() == false) throw new NotBanned("not banned");
		stmt.executeUpdate("DELETE FROM kickedFromGroup WHERE groupID="+groupID+" AND userID="+userID);
		return;
	}
	
	static public Event createEvent(Event event) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT MAX(eventID) FROM event");
		if (rs.next() == false) event.setEventID(1);
		else event.setEventID(rs.getInt(1)+1);
		stmt.executeUpdate("INSERT INTO event(eventID, eventName, groupID, creator, location, date, time, dateEnd, timeEnd) VALUES ("+event.getEventID()+", '"+event.getEventName()+"'), "+event.getGroupID()+", "+event.getCreator()+", '"+event.getLocation()+"', '"+event.getDate()+"', '"+event.getTime()+"', '"+event.getDateEnd()+"', '"+event.getTimeEnd()+"'");
		return event;
	}
	
	static public ArrayList<Integer> eventByEventName(String eventName) throws ClassNotFoundException, SQLException, EventNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ArrayList<Integer> eventList = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery("SELECT eventID FROM event WHERE eventName='"+eventName);
		if (rs.next() == false) throw new EventNotFound("event not found");
		eventList.add(rs.getInt(1));
		while(rs.next()) eventList.add(rs.getInt(1));
		return eventList;
	}
	
	static public Event eventByEventID(int eventID) throws ClassNotFoundException, SQLException, EventNotFound{
		Event event = new Event();
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+eventID);
		if (rs.next() == false) throw new EventNotFound("event not found");
		event.setEventID(rs.getInt("eventID"));
		event.setEventName(rs.getNString("eventName"));
		event.setGroupID(rs.getInt("groupID"));
		event.setCreator(rs.getInt("creator"));
		event.setLocation(rs.getNString("location"));
		event.setDate(rs.getNString("date"));
		event.setTime(rs.getNString("time"));
		event.setDateEnd(rs.getNString("dateEnd"));
		event.setTimeEnd(rs.getNString("timeEnd"));
		return event;
	}
	
	static public void editEvent(Event event, int userID) throws ClassNotFoundException, SQLException, EventNotFound, NotCreator{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+event.getEventID());
		if (rs.next() == false) throw new EventNotFound("event not found");
		rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+event.getEventID()+" AND creator="+userID);
		if (rs.next() == false) throw new NotCreator("not creator");
		stmt.executeUpdate("UPDATE event SET eventName='"+event.getEventName()+"', location='"+event.getLocation()+", date='"+event.getDate()+"', time='"+event.getTime()+"', dateEnd='"+event.getDateEnd()+"', timeEnd='"+event.getTimeEnd()+"' WHERE eventID="+event.getEventID());
		return;
	}
	
	static public void cancelEvent(int eventID, int userID) throws ClassNotFoundException, SQLException, EventNotFound, NotCreator{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+eventID);
		if (rs.next() == false) throw new EventNotFound("event not found");
		rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+eventID+" AND creator="+userID);
		if (rs.next() == false) throw new NotCreator("not creator");
		stmt.executeUpdate("DELETE FROM event WHERE eventID="+eventID);
	}
	
	static public void joinEvent(int eventID, int userID) throws ClassNotFoundException, SQLException, EventNotFound, UserNotFound, AlreadyJoined, AlreadyBanned{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+eventID);
		if (rs.next() == false) throw new EventNotFound("event not found");
		rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM bannedFromEvent WHERE eventID="+eventID+" AND userID="+userID);
		if (rs.next() == true) throw new AlreadyBanned("already banned");
		rs = stmt.executeQuery("SELECT * FROM eventAttend WHERE eventID="+eventID+" AND userID="+userID);
		if (rs.next() == true) throw new AlreadyJoined("already joined");
		stmt.executeUpdate("INSERT INTO eventAttend(eventID, userID) VALUES ("+eventID+", "+userID+")");
		return;
	}
	
	static public void unjoinEvent(int eventID, int userID) throws ClassNotFoundException, SQLException, EventNotFound, UserNotFound, NotJoined{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+eventID);
		if (rs.next() == false) throw new EventNotFound("event not found");
		rs = stmt.executeQuery("SELECT * FROM eventAttend WHERE eventID="+eventID+" AND userID="+userID);
		if (rs.next() == false) throw new NotJoined("not joined");
		stmt.executeUpdate("DELETE FROM eventAttend WHERE eventID="+eventID+" AND userID="+userID);
		return;
	}
	
	static public void banUserFromEvent(int creatorID, int userID, int eventID) throws ClassNotFoundException, SQLException, NotCreator, UserNotFound, AlreadyBanned{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE creator="+creatorID+" AND eventID="+eventID);
		if (rs.next() == false) throw new NotCreator("not creator");
		rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM bannedFromEvent WHERE userID="+userID+" AND eventID="+eventID);
		if (rs.next() == true) throw new AlreadyBanned("already banned");
		rs = stmt.executeQuery("SELECT * FROM eventAttend WHERE userID="+userID+" AND eventID="+eventID);
		if (rs.next() == true) stmt.executeUpdate("DELETE FROM eventAttend WHERE userID="+userID+" AND eventID="+eventID);
		stmt.executeUpdate("INSERT INTO bannedFromEvent(eventID, userID) VALUES ("+eventID+", "+userID+")");
		return;
	}
	
	static public void unbanUserFromEvent(int creatorID, int userID, int eventID) throws ClassNotFoundException, SQLException, NotCreator, UserNotFound, NotBanned{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE creator="+creatorID+" AND eventID="+eventID);
		if (rs.next() == false) throw new NotCreator("not creator");
		rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT * FROM bannedFromEvent WHERE userID="+userID+" AND eventID="+eventID);
		if (rs.next() == false) throw new NotBanned("not banned");
		stmt.executeUpdate("DELETE FROM bannedFromEvent(eventID, userID) VALUES ("+eventID+", "+userID+")");
		return;
	}
		
	static public int countOfGroups() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT MAX(groupID) FROM meetGroup");
		rs.next();
		int numGroups = rs.getInt(1);
		return numGroups;
	}
	
	static public String getMessages(int groupID) throws ClassNotFoundException, SQLException, GroupNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT message FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		String message = rs.getNString("message");
		return message;
	}
	
	static public void updateMessages(int groupID, String message) throws ClassNotFoundException, SQLException, GroupNotFound{
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		stmt.executeUpdate("UPDATE meetGroup SET message='"+message+"' WHERE groupID="+groupID);
		return;
	}
	
	static public ArrayList<Integer> allGroupsInUser(int userID) throws ClassNotFoundException, SQLException, UserNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT groupID FROM groupMember WHERE userID="+userID);
		ArrayList<Integer> groups = new ArrayList<Integer>();
		while(rs.next()) groups.add(rs.getInt(1));
		return groups;
	}
	
	static public ArrayList<Integer> allEventsInUser(int userID) throws ClassNotFoundException, SQLException, UserNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID="+userID);
		if (rs.next() == false) throw new UserNotFound("user not found");
		rs = stmt.executeQuery("SELECT eventID FROM eventAttend WHERE userID="+userID);
		ArrayList<Integer> events = new ArrayList<Integer>();
		while (rs.next()) events.add(rs.getInt(1));
		return events;
	}
	
	static public ArrayList<Integer> allEventsInGroup(int groupID) throws ClassNotFoundException, SQLException, GroupNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT eventID FROM event WHERE groupID="+groupID);
		ArrayList<Integer> events = new ArrayList<Integer>();
		while (rs.next()) events.add(rs.getInt(1));
		return events;
	}
	
	static public ArrayList<Integer> allUsersInGroup(int groupID) throws ClassNotFoundException, SQLException, GroupNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT userID FROM groupMembers WHERE groupID="+groupID);
		ArrayList<Integer> users = new ArrayList<Integer>();
		while (rs.next()) users.add(rs.getInt(1));
		return users;
	}
	
	static public ArrayList<Integer> allAdminsInGroup(int groupID) throws ClassNotFoundException, SQLException, GroupNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM meetGroup WHERE groupID="+groupID);
		if (rs.next() == false) throw new GroupNotFound("group not found");
		rs = stmt.executeQuery("SELECT userID FROM groupMembers WHERE groupID="+groupID+" AND isAdmin=1");
		ArrayList<Integer> users = new ArrayList<Integer>();
		while (rs.next()) users.add(rs.getInt(1));
		return users;
	}
	
	static public ArrayList<Integer> allUsersInEvent(int eventID) throws ClassNotFoundException, SQLException, EventNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM event WHERE eventID="+eventID);
		if (rs.next() == false) throw new EventNotFound("event not found");
		rs = stmt.executeQuery("SELECT userID FROM eventAttend WHERE eventID="+eventID);
		ArrayList<Integer> users = new ArrayList<Integer>();
		while (rs.next()) users.add(rs.getInt(1));
		return users;
	}
	
	static public int eventCreator(int eventID) throws ClassNotFoundException, SQLException, EventNotFound {
		Class.forName("com.mysql.jdcb.Driver");
		Connection con=DriverManager.getConnection(connectionAddress, connectionUsername, connectionPWord);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT creator from event where eventID="+eventID);
		if (rs.next() == false) throw new EventNotFound("event not found");
		int creator = rs.getInt(1);
		return creator;
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

class NotAdmin extends Exception {
	public NotAdmin(String message) {
		super(message);
	}
}

class UserBanned extends Exception {
	public UserBanned(String message) {
		super(message);
	}
}

class AlreadyJoinedGroup extends Exception {
	public AlreadyJoinedGroup(String message) {
		super(message);
	}
}

class NotGroupMember extends Exception {
	public NotGroupMember(String message) {
		super(message);
	}
}

class LastAdmin extends Exception {
	public LastAdmin(String message) {
		super(message);
	}
}

class IsAdmin extends Exception {
	public IsAdmin(String message) {
		super(message);
	}
}

class AlreadyBanned extends Exception {
	public AlreadyBanned(String message) {
		super(message);
	}
}

class NotBanned extends Exception {
	public NotBanned(String message) {
		super(message);
	}
}

class EventNotFound extends Exception {
	public EventNotFound(String message) {
		super(message);
	}
}

class NotCreator extends Exception {
	public NotCreator(String message) {
		super(message);
	}
}

class AlreadyJoined extends Exception {
	public AlreadyJoined(String message) {
		super(message);
	}
}

class NotJoined extends Exception {
	public NotJoined(String message) {
		super(message);
	}
}