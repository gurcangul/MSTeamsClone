package CoursePackage;

import java.util.ArrayList;

import UserPackage.User;

public interface IMediator {
	
	public static void addTeam(User user) {};
	
	public static void removeTeam(User user) {};
	
	public static void updateTeam(User user) {};
	
	public static void addOwner(User user, Team team) {};
	
	public static void addMemberToTeam(User user, Team team) {};
	
	public static void removeAMemberFromTeam(User user, Team team) {};
	
	public static void addMeetingChannel(User user, Team team) {};
	
	public static void removingMeetingChannel(User user, Team team) {};
	
	public static void updateMeetingChannel(User user, Team team) {};
	
	public static void addParticipantToChannel(User user, Team team, MeetingChannel channel) {};
	
	public static void removeParticipantFromChannel(User user, Team team, MeetingChannel channel) {};
	
	public static void monitoringPrivateChannels(ArrayList<MeetingChannel> channelList) {};
	
	public static void finding(Team team) {};
	
}
