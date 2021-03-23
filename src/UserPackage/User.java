package UserPackage;

import java.util.ArrayList;
import java.util.Random;
import CoursePackage.MeetingChannel;
import CoursePackage.Team;

public class User { 
	// attributes
	private String userType;
	private String userName;
	private String userID;
	private String eMail;
	private String password;
	private ArrayList<Team> teamList= new ArrayList<Team>();
	private ArrayList<MeetingChannel> channelList= new ArrayList<MeetingChannel>();
	private ArrayList<String> teamIDList;

	
	public User(String userType, String userName, String userID, String eMail, String password, ArrayList<String> teamIDList) {
		// parameterized constructor
		this.setUserType(userType);
		this.setUserName(userName);
		this.setUserID(userID);
		this.seteMail(eMail);
		this.setPassword(password);
		this.setTeamIDList(teamIDList);
	}
	
	public User() { // non-parameterized constructor
		this.setUserType("Unknown");
		this.setUserName("Unknown");
		this.setUserID("Unknown");
		this.seteMail("Unknown");
		this.setPassword("Unknown");
	}
	
	// getter and setter methods
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID =userID;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
        if(eMail.equals("")) {	
        	eMail = generateEmail();
        	this.eMail = eMail;}
        else	this.eMail = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password.equals("")) {
			 password=generatePassword();
			 this.password = password;}
		else
			this.password = password;
	}
	
	public ArrayList<String> getTeamIDList() {
		return teamIDList;
	}
	public void setTeamIDList(ArrayList<String> teamIDList) {
		  this.teamIDList = teamIDList;
				  		
	}
	
	public ArrayList<Team> getTeamList() {
		return teamList;
	}
	
	
	public ArrayList<MeetingChannel> getChannelList() {
		return channelList;
	}

	public void setChannelList(ArrayList<MeetingChannel> channelList) {
		this.channelList = channelList;
	}

	public void setTeamList(ArrayList<Team> teamList) {
		this.teamList = teamList;
	}
	
	public void removeTeam(Team team) { 
	    team.removeUser(this);
	    this.getTeamList().remove(team);
	 }
    public void addTeam(Team team) { 
    	team.addUser(this);
    	this.getTeamList().add(team);
    }
	public void addChannelList(MeetingChannel channel) {    	
		channel.addUserList(this);
		this.getChannelList().add(channel);
		
	}
	
	// creates generated e-mail
	public String generateEmail(){		
		String createdMail = "";
    	String userName =getUserName();
    	userName = userName.replace(" ", "");
    	userName = userName.toLowerCase();
    	String userType = getUserType();
    	String domain = "";
	    	if(userType.equals("Student")){
	    		domain = "std.iyte.edu.tr";
	    	}
	    	if(userType.equals("Instructor") || userType.equals("Teaching Assistant"))
	    		domain = "iyte.edu.tr";
	    	createdMail = userName + "@" + domain;
		return createdMail;				
	}		
	
	// creates generated password
	public String generatePassword(){
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 4;
	    Random random = new Random();
	
		    String generatedStringPassword = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		    
		   
		  return generatedStringPassword;
	}		
	
	@Override
	public String toString() {
		return " " + userType + " " + userName + " " + userID + " " + eMail
				+ " " + password + " " + teamIDList +" ";
	}




	
	
}
