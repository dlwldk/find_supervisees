import java.sql.*;
import java.io.*;

public class s201702817jdbc {
	public static void main(String args[]) throws SQLException, IOException
	{
	// Load MySQL JDBC Driver
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println ("Could not load the driver");
		}
		System.out.println("DB Connection");
		// Connect to the database
		String db, user, pass;
		db = readEntry("DB : ");
		user = "s201702817";
		pass = "01051242733";
		Connection conn = DriverManager.getConnection("jdbc:mysql://db.hufs.ac.kr:3306/"+db+"?serverTimezone=UTC", user, pass);
		//Perform query using PreparedStatement object
		Statement s = conn.createStatement();
		String ssn = readEntry("Enter a ssn: ");
		int n=0;
		int count=0;
		String[] superssn = new String[100];
		int[] level = new int[100];
		
		while(count<=n) {
			ResultSet r = s.executeQuery("SELECT ssn FROM EMPLOYEE WHERE superssn = " + ssn);
		
			while(r.next()) {
				superssn[n] = r.getString(1);
				if(count==0) {
					level[n] = 1;   
					System.out.println(superssn[n] + " at level " + level[n]);
				}
				else {
					level[n] = level[count-1]+1;
					System.out.println(superssn[n] + " at level " + level[n]);
				}					
				n++;
			}
			
			r.close();
			ssn = superssn[count];	
			count++;
		}
		System.out.println("END OF LIST");
		s.close();
		conn.close();
		
	 }
	 // ReadEntry function -- to read input string
	static String readEntry(String prompt) 
	{
		try {
			StringBuffer buffer = new StringBuffer();
			System.out.print(prompt);
			System.out.flush();
			int c = System.in.read();
			while(c != '\n' && c != -1) {
				buffer.append((char)c);
				c = System.in.read();
			}
			return buffer.toString().trim();
		} catch (IOException e) {
			return "";
		}
	}
}
