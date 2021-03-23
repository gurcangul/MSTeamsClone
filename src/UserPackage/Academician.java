package UserPackage;

import java.util.ArrayList;

public abstract class Academician extends User {
	
	public Academician(String userType, String userName, String userID, String eMail, String password, ArrayList<String> teamIDList) {
		super(userType, userName, userID,  eMail,  password, teamIDList);
	}

}
