package FilePackage;

import CoursePackage.Team;
import UserPackage.User;

public interface IWriter {

	public static void updatingUserFile() {};
	
	public static void addingTeamToFile(String teamName, String teamID, String defaultChannel, String defaultMeetingDay, User user) {};
	
	public static void addOrRemoveMember(User user, Team team) {};
	
	public static void removingTeam(String removedTeam, User user) {};
	
	public static void updatingTeamFile(Team team) {};
}
