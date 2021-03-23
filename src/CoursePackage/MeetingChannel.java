package CoursePackage;

import java.util.ArrayList;

import UserPackage.User;



public class MeetingChannel {
	private String channelName;
	private String meetingDayAndTime;
	private String userName;
	private String status ;
	private String participantID;
	private ArrayList<String> participantIDList = new ArrayList<String>();
	private ArrayList<User> userList = new ArrayList<User>();
	Team team = new Team();
	
	// parameterized constructor
	public MeetingChannel(String channelName, String meetingDayAndTime, ArrayList<String> participantIDList, String status) { // adding meeting channel
		this.setChannelName(channelName);
		this.setParticipantIDList(participantIDList);
		this.setMeetingDayAndTime(meetingDayAndTime);
		this.setStatus(status);
	}
	
	// non-parameterized constructor
	public MeetingChannel(String channelName, String meetingDayAndTime) { // default meeting channel 
		this.setChannelName(channelName);
		this.setMeetingDayAndTime(meetingDayAndTime);
	
	}
	
	// getter and setter methods
	public MeetingChannel() {
		this.setChannelName("");
		this.setMeetingDayAndTime("");
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;

	}

	public String getMeetingDayAndTime() {
		return meetingDayAndTime;
	}

	public void setMeetingDayAndTime(String meetingDayAndTime) {
		this.meetingDayAndTime = meetingDayAndTime;		
	}
	public String getParticipantID() {
		return participantID;
	}

	public void setParticipantID(String participantID) {
		this.participantID = participantID;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public ArrayList<String> getParticipantIDList() {
		return participantIDList;
	}

	public void setParticipantIDList(ArrayList<String> participantIDList) {
		this.participantIDList = participantIDList;
	}


	
	public void addParticipantID(User user, String status) {
		if(status.equalsIgnoreCase("General"))
			this.getParticipantIDList().add("all members");
		else if(!status.equalsIgnoreCase("General"))
			this.getParticipantIDList().add(user.getUserID());
		
		
	}
	
	public void removeParticipantID(User user) {
		this.getParticipantIDList().remove(user.getUserID());
	}
	
	public void addUserList(User user) {
		this.getUserList().add(user);
	}


	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return " " + channelName + " " + meetingDayAndTime + " "
				 + participantIDList; 
	}


}