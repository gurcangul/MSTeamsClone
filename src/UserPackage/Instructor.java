package UserPackage;

import java.io.IOException;
import java.util.ArrayList;

import CoursePackage.Mediator;
import CoursePackage.UnauthorizedUserOperationException;

public class Instructor extends Academician implements IInstructor{ // the keyword "extends" is used for "INHERITANCE" ("IS-A relationship")
																	// the class implements from related Interface
	
	public Instructor(String userType, String userName, String userID, String eMail, String password, ArrayList<String> teamIDList) {
		super(userType, userName, userID,  eMail,  password, teamIDList );
	}
	
	public Instructor() {
		super("Unknown", "Unknown", "Unknown", "Unknown", "Unknown", null );
	}
	
		static Mediator mediator = new Mediator();
		static User user = new User();
		
		public static void instructorPage(User user) throws IOException, UnauthorizedUserOperationException
		{
			
			UserMenu.userMainMenu(user);
			
			
		}
} 
