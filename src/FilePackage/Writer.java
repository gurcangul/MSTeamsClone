package FilePackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import CoursePackage.Mediator;
import CoursePackage.MeetingChannel;
import CoursePackage.Team;
import UserPackage.User;

public class Writer implements IWriter{
	
	static ArrayList<String> usersFile = Reader.readFromCSVFile("userList.csv");
	static ArrayList<String> teamsFile = Reader.readFromCSVFile("teamList.csv");
	static ArrayList<User> users = Mediator.getUserList();
	static ArrayList<Team> teams = Mediator.getTeamList();
	
	
	
	public static void updatingUserFile() throws IOException{
		
		String updatedLine = "";
		for(int i= 0; i<users.size(); i++){
			String updatedLine2 = "";
			for(int j=0; j<users.get(i).getTeamIDList().size(); j++){
				updatedLine2 += "," + users.get(i).getTeamIDList().get(j);
			}					
			updatedLine = users.get(i).getUserType() + "," + users.get(i).getUserName() + "," + 
			users.get(i).getUserID() + "," + users.get(i).geteMail() + "," + users.get(i).getPassword() + updatedLine2;
			
			usersFile.set(i+1, updatedLine);
		}

		BufferedWriter updatedFile =new BufferedWriter(new FileWriter("userList.csv"));
		for(String updatedFileLine: usersFile){
			updatedFile.write(updatedFileLine+ "\n"); // this line writes the userList.csv for the updatedLine
		}
		updatedFile.flush();
		updatedFile.close();
	}
	
	public static void addingTeamToFile(String teamName, String teamID, String defaultChannel, String defaultMeetingDay, User user) throws IOException{	
		boolean flag = true;
		for(int i = 0; i<teams.size(); i++){	
			if(teams.get(i).getTeamID().equals(teamID))	{	
				flag=false;
				System.out.println("This team has already exists...");
				break;
			}
		}
		if(flag){	
			String fileName = "teamList.csv";
			BufferedWriter writer =new BufferedWriter(new FileWriter(fileName, true));

			writer.write(teamName + "," + teamID + "," + defaultChannel + "," + defaultMeetingDay + "," + "," + "," + "\n");
			writer.flush();
			teamsFile.add(teamName + "," + teamID + "," + defaultChannel + "," + defaultMeetingDay);
			Team team = new Team(teamName, teamID, defaultChannel, defaultMeetingDay);
			teams.add(team);	
			
			System.out.println("Team is added!");
			writer.close();
			for(User s:users) {
				if(s.getUserID().equals(user.getUserID())) {
					s.getTeamIDList().add(team.getTeamID());
					System.out.println("User is added!");
					s.addTeam(team);
					System.out.println(user.getTeamList());
					updatingUserFile();
					break;
				}
			}
		}		
		
	}	
	public static void addOrRemoveMember(User user, Team team) throws IOException{		
		System.out.println(user.getTeamList());
		int index = 0;
		String updatedLine = "";
		for(User usr: users)
		{
			if(usr.getUserID().equals(user.getUserID()))
			{
				users.set(index, user);
				String updatedLine2 = "";			
				for(int j=0; j<user.getTeamIDList().size(); j++){
					updatedLine2 += "," + user.getTeamIDList().get(j)  ;
				}					
				updatedLine = user.getUserType() + "," + user.getUserName() + "," + 
						user.getUserID() + "," + user.geteMail() + "," + user.getPassword() + updatedLine2;
				usersFile.set(index+1, updatedLine);
				break;
			}
			else index++;
		}
		BufferedWriter updatedFile =new BufferedWriter(new FileWriter("userList.csv"));
		for(String updatedFileLine: usersFile){
			updatedFile.write(updatedFileLine+ "\n"); // this line writes the userList.csv for the updatedLine
		}
		updatedFile.flush();
		updatedFile.close();	
	}
	
	public static void removingTeam(String removedTeam, User user) throws IOException{
		String fileName = "teamList.csv";
		for(Team team: teams) {
			if(removedTeam.equalsIgnoreCase(team.getTeamID()))	{
				teams.remove(team);
				team.removeUser(user);
				System.out.println("Team is removed from database!");
				user.removeTeam(team);
				break;
			}
		}
		for(User s:users) {
			if(s.getUserID().equals(user.getUserID())) {
				s.getTeamIDList().remove(removedTeam);
				System.out.println("Team is removed from " + user.getUserName() + "'s team list!");
				updatingUserFile();
				break;
			}
		}

		String firstLine = teamsFile.get(0);
		BufferedWriter updatedFile =new BufferedWriter(new FileWriter(fileName, false));
		teamsFile.clear();
		teamsFile.add(firstLine);
		
		for(Team team: teams) {
			String updatedTeamLine = "";
			String meetingChannelLine = "";
			for(int j=0; j<team.getMeetingChannels().size(); j++)
			{
				String participantIDLine = "\"";
				for(int i =0; i<team.getMeetingChannels().get(j).getParticipantIDList().size(); i++)
				{
					participantIDLine += team.getMeetingChannels().get(j).getParticipantIDList().get(i) + ",";
				}
				participantIDLine = participantIDLine.substring(0, participantIDLine.length()-1);
				participantIDLine += "\"";
				meetingChannelLine += team.getMeetingChannels().get(j).getChannelName() + "," +
						team.getMeetingChannels().get(j).getMeetingDayAndTime() + ","
						+ participantIDLine;
			}
			updatedTeamLine += team.getTeamName() + "," + team.getTeamID() + "," + team.getDefaultChannel()+ ","
					+ team.getDefaultMeetingDayAndTime()+ "," + meetingChannelLine;			
			teamsFile.add(updatedTeamLine);
		}
		for(String updatedFileLine: teamsFile){
			updatedFile.write(updatedFileLine+ "\n"); // this line writes the userList.csv for the updatedLine
		}
		updatedFile.flush();
		updatedFile.close();
	}	
	
	public static void updatingTeamFile(Team team) throws IOException{
			String updatedTeamLine = "";
			String meetingChannelLine = "";
			int index = 0;
			for(Team tm: teams)
			{
				if(tm.getTeamID().equals(team.getTeamID()))
				{
					for(MeetingChannel mc : team.getMeetingChannels())
					{
						String participantIDLine = "\"";
						for(String pID: mc.getParticipantIDList())
						{
							participantIDLine += pID + ",";
						}
						participantIDLine = participantIDLine.substring(0, participantIDLine.length()-1);
						participantIDLine += "\"";
						meetingChannelLine += mc.getChannelName() + "," +
								mc.getMeetingDayAndTime() + ","
								+ participantIDLine + ",";
					}
					updatedTeamLine += team.getTeamName() + "," + team.getTeamID() + "," + team.getDefaultChannel()+ ","
							+ team.getDefaultMeetingDayAndTime()+ "," + meetingChannelLine;
					
					teamsFile.set(index+1,updatedTeamLine);
				}
				else index++;
			}
			
		BufferedWriter updatedFile =new BufferedWriter(new FileWriter("teamList.csv"));
		for(String updatedFileLine: teamsFile){
			updatedFile.write(updatedFileLine+ "\n"); // this line writes the userList.csv for the updatedLine
		}
		updatedFile.flush();
		updatedFile.close();
		
	}
	
}
