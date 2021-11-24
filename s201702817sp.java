import java.sql.*;
import java.util.Scanner;

public class s201702817sp {
	public static void main (String args[])
	{
		try
		{
			Class.forName ("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://db.hufs.ac.kr:3306/s201702817DB?serverTimezone=UTC", "아이디", "비밀번호");
			CallableStatement c = con.prepareCall("{call find_all_supervisees(?)}");
			System.out.print("Enter a ssn : ");
			Scanner scan = new Scanner(System.in);
			String ssn = scan.nextLine();
			c.setString(1, ssn);
			ResultSet r = c.executeQuery();
			while (r.next())
				System.out.println(r.getString(1));
			System.out.println("END OF LIST");
			con.close();
		}
		catch (SQLException ex)
		{
			System.out.println("SQLException" + ex);
		}
		catch (Exception ex)
		{
			System.out.println("Exception:" + ex);
		}
	}
}
