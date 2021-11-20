// @Entity @Table(name = "Events")
import java.util.*;

public class Event {
    private String eventID;
    private String groupID;
    private String eventLocation;
    private String eventDate;
    private String eventTime;
    private Integer eventDuration;
    
    public Event() {}

    public Event(String eventID, String groupID, String eventLocation,
                 String eventDate, String eventTime, Integer eventDuration)
    {
        this.setEventID(eventID);
        this.setGroupID(groupID);
        this.setEventLocation(eventLocation);
        this.setEventDate(eventDate);
        this.setEventTime(eventTime);
        this.setEventDuration(eventDuration);
    }

    // Getters&&Setters
    public String getEventID() { return eventID; }
    public void setEventID(String eventID) { this.eventID = eventID; }
    public String getGroupID() { return groupID; }
    public void setGroupID(String groupID) { this.groupID = groupID; }
    public String getEventLocation() { return eventLocation; }
    public void setEventLocation(String eventLocation) { this.eventLocation = eventLocation; }
    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }
    public String getEventTime() { return eventTime; }
    public void setEventTime(String eventTime) { this.eventTime = eventTime; }
    public Integer getEventDuration() { return eventDuration; }
    public void setEventDuration(Integer eventDuration) { this.eventDuration = eventDuration; }
}
