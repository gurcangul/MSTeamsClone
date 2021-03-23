
package CoursePackage; 
import java.util.ArrayList;


import UserPackage.User;

public class Team {
	//Team Name,Team ID,Default Channel,Default Meeting Day and Time,Meeting Channel,Meeting Day and Time,Participant ID 
	private String teamName; 
	//using the access modifier private, data members are encapsulated (accessible in class scope only)
	private String teamID; 
	private String defaultChannel; 
	private String defaultMeetingDayAndTime; 
	private ArrayList<MeetingChannel> meetingChannels = new ArrayList<MeetingChannel>(); 
	public void setMeetingChannels(ArrayList<MeetingChannel> meetingChannels) {
		this.meetingChannels = meetingChannels;
	}

	private ArrayList<User> userList= new ArrayList<>();
	private MeetingChannel channel;
	private ArrayList<String> ownerList = new ArrayList<String>();


	// parameterized constructor
	public Team(String teamName, String teamID, String defaultChannel,String defaultMeetingDayAndTime) {
		this.setTeamName(teamName); 
		this.setTeamID(teamID); 
		this.setDefaultChannel(defaultChannel);
		this.setDefaultMeetingDayAndTime(defaultMeetingDayAndTime);

		
	} 	
	// non-parameterized constructor
	public Team() {
		this.setTeamName(""); 
		this.setTeamID(""); 
		this.setDefaultChannel("");
		this.setDefaultMeetingDayAndTime("");
	
	} 
		
	
	// getter and setter methods
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	public MeetingChannel getChannel() {
		return channel;
	}
	public void setChannel(MeetingChannel channel) {
		this.channel = channel;
	}
	public void setMeetingChannels(ArrayList<MeetingChannel> meetingChannels, MeetingChannel channel) {
		int index = 0;
		for(MeetingChannel mc: meetingChannels)
		{
			if(channel.getChannelName().equalsIgnoreCase(mc.getChannelName()))
			{
				meetingChannels.set(index, channel);
				break;
			}
			else index++;
		}

		this.meetingChannels = meetingChannels;
	}
	public String getTeamName() { 
		 return teamName; 
	} 
	
	public void setTeamName(String teamName) { 
		 this.teamName = teamName; 
	 } 
	 
	public String getTeamID() {
		 return teamID; 
	} 
	 
	public void setTeamID(String teamID) { 
		 this.teamID = teamID; 
	} 
	 
	//----
	public String getDefaultMeetingDayAndTime() { 
		 return defaultMeetingDayAndTime; 
	 } 
	
	public void setDefaultMeetingDayAndTime(String defaultMeetingDayAndTime) {
		if(defaultMeetingDayAndTime.equals(" "))
			this.defaultMeetingDayAndTime = "Monday 09:45 AM";
		else
		 this.defaultMeetingDayAndTime = defaultMeetingDayAndTime; } 
	 	 
	//----
	public String getDefaultChannel() {
		 return defaultChannel; 
	}
	public void setDefaultChannel(String defaultChannel) { 
		if(defaultChannel.equals(""))
			this.defaultChannel = "General";
		else
			this.defaultChannel = defaultChannel; 
	} 

	
	public ArrayList<MeetingChannel> getMeetingChannels() {
		return meetingChannels;
	}
	
	public ArrayList<String> getOwnerList() {
		return ownerList;
	}

	public void setOwnerList(ArrayList<String> ownerList) {
		this.ownerList = ownerList;
	}
	
	public void removeUser(User user) {
    	this.userList.remove(user);        
    }
	
	public void addUser(User user) {
		if(user.getUserType().equalsIgnoreCase("Instructor"))
			this.ownerList.add(user.getUserID());
    	this.getUserList().add(user);        
    }	
	

	public void addMeetingChannel(String channelName, String meetingDayAndTime, ArrayList<String> participantID, String status) {
			channel = new MeetingChannel(channelName, meetingDayAndTime,participantID, status);
			meetingChannels.add(channel);	

	}
	
	public void removeMeetingChannel(MeetingChannel channel) {
		meetingChannels.remove(channel);
	}

    
    public void print() {
        System.out.println("Team " + this.teamID + "'s user are:");
        for (User user:this.userList) {
            System.out.println("- " + user.getUserName());
        }
    }
    
    @Override
	public String toString() {
		return " " + teamName + " " + teamID + " " + defaultChannel + " " + defaultMeetingDayAndTime + " " + meetingChannels;
	}    		
}