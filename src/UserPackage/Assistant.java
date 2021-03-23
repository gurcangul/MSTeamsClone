package UserPackage;

import java.io.IOException;
import java.util.ArrayList;

import CoursePackage.UnauthorizedUserOperationException;

public class Assistant extends Academician implements IAssistant{ // the class implements from related Interface
	// the keyword "extends" is used for "INHERITANCE" ("IS-A relationship")
	
	public Assistant(String userType, String userName, String userID, String eMail, String password, ArrayList<String> teamIDList) {
		super(userType, userName, userID,  eMail,  password, teamIDList); // call the parameterized constructor of the superclass
	}
	
	public static void assistantPage(User user) throws IOException, UnauthorizedUserOperationException
	{
		UserMenu.userMainMenu(user);
	}
 
}
