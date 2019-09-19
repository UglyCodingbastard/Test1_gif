//package connect;
//
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Connection;
//
//
//public class Log_DAO {
//
//	public static void main(String[] args) {
//		String id1;
//		String pwd1;
//		MainUI ui = new MainUI();
//		Log_DTO ldto = new Log_DTO();
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members","root","12345");
//			String a = "'rnfma2413'";
//			
//			
//			String qu = "select * from member_info where id="+a;
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(qu);
//			
//			while(rs.next()) {
//
//				id1 = rs.getString("id");
//				pwd1 = rs.getString("pwd");		
//				System.out.println(id1+"        "+pwd1+"\n");
//			}
//			st.close();
//		}catch(Exception e) {
//			System.err.println("¿À·ù!");
//			System.out.println(e.getMessage());
//		}
//	}
//}
