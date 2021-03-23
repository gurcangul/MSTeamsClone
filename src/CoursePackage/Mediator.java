package CoursePackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import FilePackage.FileIO;
import FilePackage.Writer;
import UserPackage.User;
import UserPackage.UserMenu;

public class Mediator {

	static ArrayList<Team> teamList= FileIO.getTeams();
	static ArrayList<User> userList = FileIO.getUsers();

	static Scanner scanner = new Scanner(System.in);
	
	public static void addTeam(User user) throws IOException{
		
		if(user.getUserType().equalsIgnoreCase("Instructor")) {
			String defaultChannel ;
			System.out.println("Please enter the team name:");
			String teamName = scanner.nextLine();
			while(teamName.equals("")) {
				System.out.println("Team name cannot be null... Please enter the team name: ");
				teamName = scanner.nextLine();
			}
			System.out.println("Please enter the team ID: ");
			String teamID = scanner.nextLine();
			while(teamID.equals("")) {
				System.out.println("Team ID cannot be null... Please enter the team ID: ");
				teamID = scanner.nextLine();
			}
			defaultChannel = "General";
			System.out.println("Please enter the Default Meeting Day and Time: ");
			String defMeetingDayAndTime =" ";
			defMeetingDayAndTime=scanner.nextLine();
			
			
			Writer.addingTeamToFile(teamName, teamID, defaultChannel, defMeetingDayAndTime, user); // write the team to teamFile
	}
		else
			System.out.println("You cannot add a Team!");
	}
	
	public static ArrayList<Team> getTeamList() {
		return teamList;
	}

	public static void setTeamList(ArrayList<Team> teamList) {
		Mediator.teamList = teamList;
	}

	public static ArrayList<User> getUserList() {
		return userList;
	}

	public static void setUserList(ArrayList<User> userList) {
		Mediator.userList = userList;
	}

	public static void removeTeam(User user) throws IOException{
		boolean flag = false;
		System.out.println("Please enter the TeamID that you want to remove: ");
		String teamID = scanner.nextLine();	
		Team findTeam = null;
		for(Team team: teamList) {
			if(team.getTeamID().equals(teamID)) {
				findTeam = team;
				break;}
		}
		for(int i=0; i<user.getTeamList().size(); i++)
		{
			if(teamID.equals(user.getTeamIDList().get(i))) {
				flag = true;
				for(int k=0; k<findTeam.getOwnerList().size(); k++) {
					if(findTeam.getOwnerList().get(k).equals(user.getUserID())) {
						Writer.removingTeam(user.getTeamList().get(i).getTeamID(), user); // remove the team from teamfile
						break;}
					else System.out.println("You are not an Owner! So you cannot remove the team!");}
			}
		}
		if(!flag) {
			System.out.println("You are not a participant for this team!"); 
		}
	}
	
	public static void updateTeam(User user) throws IOException, UnauthorizedUserOperationException {//Update a team screen	
		System.out.println("Teams that you are a member ");
		for(Team tm: user.getTeamList())
		{
			System.out.println(tm.getTeamID() + " " + tm.getTeamName());
		}
		System.out.println("\nWhich team do you want to update? Please enter the team ID. ");
		String teamID = scanner.next();
		boolean isAMember = false;
		boolean isThereATeam = false;
		for(Team teams: teamList)
		{
			if(teams.getTeamID().equals(teamID)) // if the team is in teamList
			{
				isThereATeam = true;
				for(Team team: user.getTeamList()) {
					if(team.getTeamID().equals(teamID)) // if the user is a member of that team
					{
						isAMember = true;
						while(true) {		
							System.out.println("1- Add a meeting channel");
							System.out.println("2- Remove a meeting channel");
							System.out.println("3- Update a meeting channel ");
							System.out.println("4- Add a member to the team ");
							System.out.println("5- Remove a member from team ");
							System.out.println("6- Monitoring infos of private channels ");
							System.out.println("7- Finding number of distinct students, instructors and teaching assistant ");
							if(user.getUserType().equalsIgnoreCase("Instructor") || user.getUserType().equalsIgnoreCase("Teaching Assisant"))
									System.out.println("8- Update a member as team owner ");
							System.out.println("10- Back ");
							System.out.println("11- Exit ");
							
							String option = scanner.next();
							switch(option) {
							case "1":
								addMeetingChannel(user, team);
								break;
							case "2":
								removingMeetingChannel(user, team);
								break;
							case "3":
								updateMeetingChannel(user, team);	
								break;
							case "4":
								addMemberToTeam(user, team);
								break;
							case "5":
								removeAMemberFromTeam(user, team);
								break;
							case "6":
								monitoringPrivateChannels(team.getMeetingChannels());
								break;
							case "7":
								finding(team);
								break;
							case "8":
								addOwner(user, team);
								break;
							case "10":
								UserMenu.userMainMenu(user); // Back to previous menu
								break;
							case "11":
								System.exit(0);
								break;
							default:
								System.out.println("Invalid option...");
								break;
							}
						}
					}
						}
				if(!isAMember) {
					System.out.println("You are not a member of this team. So you cannot any update operation!");
					break;
				}
			}
		}
		if(!isThereATeam) {
			System.out.println("There is no such team! ");
		}
	}
	
	public static void addOwner(User user, Team team) {
		boolean isOwner = false;
		for(String str: team.getOwnerList()) {
			if(user.getUserID().equals(str)) {
				isOwner = true;
				System.out.println("You can update the teaching assistants as an team owner");
				System.out.println("Teaching Assistant list of the team: ");
				
				for(User usr: team.getUserList()) { // list the teaching assistants of the team
					if(usr.getUserType().equalsIgnoreCase("Teaching Assistant"))
					{
						System.out.println(usr.getUserID() + " " + usr.getUserName());
					}
				}
				boolean flag = false;
				while(!flag) {
					System.out.println("Please enter the user ID of teaching assistant that you want to update an owner");
					String userID = scanner.next();
			
					for(User usr: team.getUserList())
					{
						if(usr.getUserType().equalsIgnoreCase("Teaching Assistant"))
						{
							if(usr.getUserID().equals(userID)) // if entered user ID equals to any teaching assistant's user ID
							{
								flag = true;
								team.getOwnerList().add(userID);
								System.out.println("User is updated the team's owner");
								break;
							}
						}
					}
					if(!flag) System.out.println("There is no such a teaching assistant!");
				}
			}
		}
		if(!isOwner) System.out.println("You are not an owner this team! So you cannot update any member as a team owner! ");
	}
	
	public static void addMemberToTeam(User user, Team team) throws IOException, UnauthorizedUserOperationException {
		boolean isOwner = false;
		boolean isAMember = false;
		boolean isUser = false;
		for(String owner: team.getOwnerList()) {
			if(user.getUserID().equals(owner))
			{
				isOwner = true; // is user an owner?
			}
		}
		if(isOwner) {
			for(User usr: userList) {
				System.out.println(usr.getUserID() + " " + usr.getUserName());
			}
			while(!isUser) {
				System.out.println("Please enter the userID that you want to add a team: ");
				String userID = scanner.next();
				for(User us: userList) {	// get all users
					if(us.getUserID().equals(userID)) {
						isUser = true;
						for(User usr: team.getUserList()) // get the team's user
						{
							if(usr.getUserID().equals(userID))
							{
								isAMember = true;
								System.out.println("This user is already a member of team! ");
								break;
							}
						}
						if(!isAMember)
						{
							team.getUserList().add(us); 
							us.getTeamList().add(team);
							us.getTeamIDList().add(team.getTeamID());
							Writer.addOrRemoveMember(us, team);
							System.out.println(us.getUserName() + " is added to the " + team.getTeamID() + " team!");
							break;
						}
					}
				}
				if(!isUser) System.out.println("There is no such user!");
			}
		}
		else {
			throw new UnauthorizedUserOperationException("Only owners can add a member to the team!");
		}
	}
	
	public static void removeAMemberFromTeam(User user, Team team) throws IOException, UnauthorizedUserOperationException{
		
		boolean isOwner = false;
		boolean isAMember = false;
		boolean isUser = false;
		for(String owner: team.getOwnerList()) {
			if(user.getUserID().equals(owner))
			{
				isOwner = true;
			}
		}
		if(isOwner) {
			for(User usr: userList) { // list the all users
				System.out.println(usr.getUserID() + " " + usr.getUserName());
			}
			while(!isUser) {
				System.out.println("Please enter the userID that you want to remove a team: ");
				String userID = scanner.next();
				for(User us: userList) { // get all users
					if(us.getUserID().equals(userID)) { 
						isUser = true;
						for(User usr: team.getUserList()) // get team's userList
						{
							if(usr.getUserID().equals(userID)) 
							{
								isAMember = true;
								team.getUserList().remove(us); // remove the user from team's userList
								us.getTeamList().remove(team); // remove the team from user's teamList
								us.getTeamIDList().remove(team.getTeamID());	//remove the team's teamID from user's teamIDList
								Writer.addOrRemoveMember(us, team); // remove the member from team
								System.out.println(us.getUserName() + " is removed the " + team.getTeamID() + " team!");
								break;
							}
						}
						if(!isAMember)
						{
							System.out.println("This user is already not a member of team! ");
							break;	
						}
					}
				}
				if(!isUser) System.out.println("There is no such user!");
			}
		}
		else {
			throw new UnauthorizedUserOperationException("Only owners can remove a member from the team!");
		}
	}
	
	public static void addMeetingChannel(User user, Team team) throws IOException{

		MeetingChannel mc = new MeetingChannel();
		String status = "";
		System.out.println("Please enter the channel name: ");
		String channelName = scanner.next();
		mc.setChannelName(channelName); // set the channel name of object
		while(true) {
			System.out.println("Please select the channel status: 1- General or 2- Private ");
			int option = scanner.nextInt();
			if(option == 1) {
				mc.setStatus("General");
				status = "General";
				break;}
			else if(option == 2) {
				mc.setStatus("Private");
				status = "Private";
				break;}
			else System.out.println("Invalid entry!");	
		}
		System.out.println("Please enter the meeting day and time: ");
		String meetingDayAndTime = scanner.next();
		mc.setMeetingDayAndTime(meetingDayAndTime); // set the meeting day and time of object
		if(status.equalsIgnoreCase("Private"))
			mc.addParticipantID(user, status);
		
		else if(status.equalsIgnoreCase("General"))
		{
			mc.addParticipantID(user,status);	
		}
		team.setChannel(mc);	
		team.addMeetingChannel(channelName, meetingDayAndTime, mc.getParticipantIDList(),status);
		Writer.updatingTeamFile(team);
	}
	
	public static void removingMeetingChannel(User user, Team team) throws IOException {
		
		System.out.println("Please enter the channel name: ");
		String channelName = scanner.next();
		boolean flag = false;
		for(MeetingChannel ch: team.getMeetingChannels()) // get the team's meeting channel
		{
			if(ch.getChannelName().equals(channelName)) {
				flag = true;
				for(String pID: ch.getParticipantIDList()) // get channel's participantIDList 
				{
					if(pID.equals(user.getUserID())) {
						team.removeMeetingChannel(ch); // remove the channel from team's Meeting channel list
						Writer.updatingTeamFile(team); 	// update team file
						break;
					}
				}
			}
			if(flag) break;
		}
	}
	public static void updateMeetingChannel(User user, Team team) throws IOException, UnauthorizedUserOperationException{
		System.out.println("Channel Name  Status");
		for(MeetingChannel mc: team.getMeetingChannels())
		{
			for(String pID: mc.getParticipantIDList())
			{
				if(pID.equalsIgnoreCase("all members")) {
					System.out.println(mc.getChannelName() + " General");
					break;}
				else { System.out.println(mc.getChannelName() + " Private"); break; }
			}
		}
		System.out.println("Which meeting channel do you update? Please enter the channel name: ");
		boolean isThereChannel = false;
		boolean isAMember = false;
		while(!isThereChannel)
		{
			String channelName = scanner.next();
			for(MeetingChannel mc: team.getMeetingChannels()) // Get all meeting channels of team
			{
				if(mc.getChannelName().equalsIgnoreCase(channelName)) { // if the channel name that is entered exist
					isThereChannel = true;
					for(String pID: mc.getParticipantIDList()) {
						if(pID.equalsIgnoreCase("all members"))
						{
							while(true) {
								System.out.println("To Update meeting date and time please enter Y ");
								{
									String option = scanner.next();
									if(option.equalsIgnoreCase("Y"))
									{
										System.out.println("Please enter the new meeting day and time ");
										String meetingDayAndTime = scanner.next();
										mc.setMeetingDayAndTime(meetingDayAndTime);
										Writer.updatingTeamFile(team);
										break;
									}
									else System.out.println("Invalid input!");
								}
							}
							break;
						}
						else {
							if(user.getUserID().equals(pID))
							{
								isAMember = true;
								System.out.println("1- Add a participant to the channel ");
								System.out.println("2- Remove a participant from the channel ");
								System.out.println("3- Updating meeting day and time ");
								System.out.println("4- Back ");
								String option = scanner.next();
								switch(option) {
								case "1":
									//Add a participant to the channel
									addParticipantToChannel(user, team, mc);
									break;
								case "2":
									// remove a participant from the channel
									removeParticipantFromChannel(user, team, mc);
									break;
								case "3":
									System.out.println("Please enter the new meeting day and time ");
									String meetingDayAndTime = scanner.nextLine();
									mc.setMeetingDayAndTime(meetingDayAndTime);
									Writer.updatingTeamFile(team);
									break;
								case "4":
									updateTeam(user); // Back to previous menu
									break;
								default:
									System.out.println("Invalid option!");
									break;
								}
								break;
							}
						}
					}
					if(!isAMember) System.out.println("You are not a member of this channel!");
				}
			}
			if(!isThereChannel) System.out.println("There is no such channel!");
		}
	}
	
	public static void addParticipantToChannel(User user, Team team, MeetingChannel channel) throws IOException {
		
		for(User usr: team.getUserList())
		{
			// List the users that is a member of the team
			System.out.println(usr.getUserID() + " " + usr.getUserName());
		}
		System.out.println("Please enter the userID that you want to add a participant for the channel: ");
		boolean flag=false;
		boolean isAMember = false;
		String participantIDs = scanner.next();

		for(String pID: channel.getParticipantIDList()) // get the team's meeting channel's participant list
		{
			if(pID.equals(participantIDs)) // if the user has been added
			{
				flag = true;
				System.out.println("This user is already exists!");
				break;
			}
			for (User usr: team.getUserList()){
				
				if(usr.getUserID().equals(participantIDs)) // if is the user a member of the team
				{
					isAMember = true;
					break;
				}
			}

			if(!isAMember) System.out.println("This user is not a member of this team!"); // if the user is not a member of the team
			}
			if(!flag) {
				for(User usr: team.getUserList()) { // get team's userList
					if(usr.getUserID().equals(participantIDs)) {
						channel.addParticipantID(usr,channel.getStatus());	// add the participant to the channel
						channel.getUserList().add(usr); // add the user to the channel
						break;
					}
				}
			}
			team.setChannel(channel); // add the user to participant list in the team
			team.setMeetingChannels(team.getMeetingChannels(), channel);
			Writer.updatingTeamFile(team);
			
		}
	
	public static void removeParticipantFromChannel(User user, Team team, MeetingChannel channel) throws IOException {
		System.out.println("User List that is a member of the channel");
		for(User usr: channel.getUserList())
		{
			System.out.println(usr.getUserID() + " " + usr.getUserName()); // List the members of that channel
		}
		System.out.println("Please enter the userID that you want to remove a participant for the channel: ");
		boolean flag=false;
		String participantIDs = scanner.next();
		for(String pID: channel.getParticipantIDList()) // get the team's meeting channel's participant list
		{
			if(pID.equals(participantIDs)) // if the user exists
			{
				flag = true;
				for(User usr: team.getUserList()) {
					if(usr.getUserID().equals(participantIDs)) {
						channel.removeParticipantID(usr);
						channel.getUserList().remove(usr);
						break;
					}
				}
				team.setChannel(channel); // remove the user to participant list in the team
				team.setMeetingChannels(team.getMeetingChannels(), channel);
				Writer.updatingTeamFile(team);
				break;
			}
		}
		if(!flag)
			System.out.println("This user is not a member of this channel!");
	}
	
	public static void monitoringPrivateChannels(ArrayList<MeetingChannel> channelList) {
		boolean flag = false;
		System.out.println("Private Channels infos:");
		for(MeetingChannel channel :channelList)
		{
			for(String pID: channel.getParticipantIDList()) {
				if(pID.equals("all members"))
				{
					flag = true;
					break;
				}
			}
			if(!flag)
			{
				System.out.println(channel.toString());
			}
		}
		System.out.println();
	}
	
	public static void finding(Team team) throws IOException{
		int numberOfStudent = 0, numberOfInstructor = 0, numberOfAssistant =0;
		
		for(User usr: team.getUserList()) {
			if(usr.getUserType().equalsIgnoreCase("Student"))
				numberOfStudent ++;
			else if(usr.getUserType().equalsIgnoreCase("Instructor"))
				numberOfInstructor ++;
			else if(usr.getUserType().equalsIgnoreCase("Teaching Assistant"))
				numberOfAssistant ++;
		}
		
		System.out.println("Number of Instructor in "+ team.getTeamName() + " team = " + numberOfInstructor );
		System.out.println("Number of Teaching Assistant in "+ team.getTeamName() + " team = " + numberOfAssistant );
		System.out.println("Number of Student in "+ team.getTeamName() + " team = " + numberOfStudent );
		System.out.println();
		
	}
}
