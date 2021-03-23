package FilePackage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import CoursePackage.Team;
import UserPackage.Student;
import UserPackage.User;
import UserPackage.Instructor;
import UserPackage.Assistant;


public class FileIO implements IFileIO{

	static ArrayList<String> usersFile = Reader.readFromCSVFile("userList.csv");
	static ArrayList<String> teamsFile = Reader.readFromCSVFile("teamList.csv");
	static ArrayList<Team> teams = new ArrayList<Team>();
	static ArrayList<User> users = new ArrayList<User>();
	static Random rnd = new Random();
	static int low = 1;
	static int high = 1000;
	
	public static ArrayList<Team> getTeams() {
		return teams;
	}

	public static void setTeams(ArrayList<Team> teams) {
		FileIO.teams = teams;
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static void setUsers(ArrayList<User> users) {
		FileIO.users = users;
	}
	
	
	public static ArrayList<User> splitUsers() throws IOException{ // creating user objects
		User user;
		
		for(int i=1; i<usersFile.size();i++) {
			List<String> elements= new ArrayList<String>();			
			String str = usersFile.get(i);
			String[] split = str.split(",",-1); 
			ArrayList<String> teamIDList= new ArrayList<String>();
			for (int k = 0; k < split.length; k++) {
				if((k==0)||(k==1)||(k==3)||(k==4))
					elements.add(split[k]);
				else if(k==2){ 
					if(split[2].equals(""))  
					elements.add(String.valueOf(generatedUserID()));
					else
					elements.add(split[k]); 			
				}
				else if(k>3) {		 
					if(!split[k].equals(""))					
						teamIDList.add(split[k]);
				}
			} 	
			if(elements.get(0).equalsIgnoreCase("Student")){
				user = new Student(elements.get(0),elements.get(1),elements.get(2),elements.get(3),elements.get(4),teamIDList);
				users.add(user);
			}
			else if(elements.get(0).equalsIgnoreCase("Teaching Assistant")){
				user = new Assistant(elements.get(0),elements.get(1),elements.get(2),elements.get(3),elements.get(4),teamIDList);	
				users.add(user);			
			}
			else if(elements.get(0).equalsIgnoreCase("Instructor"))		{
				user = new Instructor(elements.get(0),elements.get(1),elements.get(2),elements.get(3),elements.get(4),teamIDList);	
				users.add(user);			
			}
			Writer.updatingUserFile();
			
		} 
		return users;
	}
	static ArrayList<String> participantsList;
	public static ArrayList<Team> splitTeams(){ // creating team objects
		for(int i=1; i<teamsFile.size();i++) {
			List<String> elements= new ArrayList<String>();			
			String str = teamsFile.get(i);
			String[] split = str.split(",",-1); 
		    String channelName ="";
		    String meetingDayAndTime = "";
		    for (int k = 0; k < 4; k++) { 
		    	elements.add(split[k]);
		    } 
		    
		    Team team = new Team(elements.get(0),elements.get(1),elements.get(2),elements.get(3));	
		    int k = 4;
		    while ( k < split.length-1) { 
		    	participantsList= new ArrayList<String>();
		    	channelName = split[k];
		    	meetingDayAndTime = split[k+1];
	    		int m=k+2;
	    		if(!split[m].equals("")) {
		    		while(true) {
		    			char last = split[m].charAt(split[m].length() - 1);
		    			if(last == '"') {
			    			split[m] = split[m].replaceAll("\"", "");
			    			participantsList.add(split[m]);
			    			break;
			    			}
		    			else {
		    				split[m] = split[m].replaceAll("\"", "");
		    				split[m] = split[m].replaceAll(" ", "");
		    				participantsList.add(split[m]); 
		    				m++;		    			
		    			}				    	
			    	}		    				    				    		
	    		}
		    		k = m+1;
		    		team.addMeetingChannel(channelName, meetingDayAndTime, participantsList,"");		    		
		    }		  		    
		    teams.add(team);			    
		} 
		return teams;
	}
	
	static int userID = rnd.nextInt(high-low)+low;
	public static int generatedUserID() {  			// generate userID
		userID =rnd.nextInt(high-low)+low;
		for(User usr: users) {
			if(usr.getUserID().equals(String.valueOf(userID)))
			{
				generatedUserID();
			}
		}
		return userID;
			
	}
}	