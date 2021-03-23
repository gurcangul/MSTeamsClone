package UserPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import CoursePackage.MeetingChannel;
import CoursePackage.Team;
import CoursePackage.UnauthorizedUserOperationException;
import FilePackage.FileIO;

public class Login implements ILogin { 
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void loginPage() throws IOException, UnauthorizedUserOperationException
	{
		ArrayList<User> userList = FileIO.getUsers();
		ArrayList<Team> teamList= FileIO.getTeams();

		boolean flag = false;
		
		
		
		for(User usr: userList){ // |User have a team|______|Team have an user| -  Aggregation (MANY TO MANY) HAS-A Relationship 
			for(String teamID: usr.getTeamIDList()){
				for(Team team: teamList){
					if(teamID.equals(team.getTeamID())){
						usr.addTeam(team);
						}	
				}//team.size
			}//user's teams.size
		}//user.size
	
		for(Team team: teamList){// |User have a M.Channel|______|M.Channel have an user| -  Aggregation (MANY TO MANY) HAS-A Relationship 
			for(MeetingChannel channel: team.getMeetingChannels()){
				for(String pID: channel.getParticipantIDList()){
					for(User usr: userList){
						if(pID.equals(usr.getUserID())){
							usr.addChannelList(channel);
						}
					}//user.size
				}//team's m.channel's users.size
			}//team's m.channel.size
		}//team.size
		
		System.out.println("Enter the email: ");
		String eMail = scanner.next();
		for(int i=0; i<userList.size(); i++)
		{
			if(userList.get(i).geteMail().equals(eMail))
			{
				System.out.println("Enter the Password: ");
				String password = scanner.next();
				while(!userList.get(i).getPassword().equals(password))
				{
					System.out.println("Wrong password...");
					System.out.println("Enter the Password: ");
					password = scanner.next();
				}
				if(userList.get(i).getPassword().equals(password))
				{
					flag = true;
					System.out.println("Welcome " + userList.get(i).getUserName());
					String userType = userList.get(i).getUserType();
					if(userType.equalsIgnoreCase("Student"))
					{
						Student.studentPage(userList.get(i));
						break;
					}
					if(userType.equalsIgnoreCase("Instructor"))
					{
						Instructor.instructorPage(userList.get(i));
						break;
					}
					if(userType.equalsIgnoreCase("Teaching Assistant"))
					{
						Assistant.assistantPage(userList.get(i));
						break;
					}
					
				}
			}
		}
		if(!flag)
		{
			System.out.println("Wrong email. Please try again..");
			loginPage();
		}
	}
}
