package UserPackage;

import java.io.IOException;
import java.util.ArrayList;

import CoursePackage.UnauthorizedUserOperationException;

public class Student extends User implements IStudent { // the keyword "extends" is used for "INHERITANCE" ("IS-A relationship")
	// the class implements from related Interface
	
	public Student(String userType, String userName, String userID, String eMail, String password, ArrayList<String> teamIDList) {
		super(userType, userName, userID,  eMail,  password, teamIDList);
	}
	
	
	public static void studentPage(User user) throws IOException, UnauthorizedUserOperationException
	{
		
		UserMenu.userMainMenu(user);
	}
}
