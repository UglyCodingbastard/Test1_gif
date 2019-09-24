//package connect;
//
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Connection;
//
//public class Log_DAO {
//
//	public static void main(String[] args) {
//
//	}
//
//	public static boolean create(Log_DTO ldto) throws Exception {
//
//		boolean flag = false;
//		String id_num = ldto.getid_num();
//		Connection con = null;
//		Statement stmt = null; // 데이터를 전송하는 객체
//
//		String sql = "update site_search set naver = naver+1 where id_num=" +"'"+ id_num+"'";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection(
//					"jdbc:mysql://127.0.0.1/members?useSSL=false&useUnicode=true&characterEncoding=euckr&user=root&password=12345");
//			stmt = (Statement) con.createStatement();
//			stmt.executeUpdate(sql);
//			flag = true;
//		} catch (Exception e) {
//			System.out.println(e);
//			flag = false;
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//				if (con != null)
//					con.close();
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//
//		}
//		return flag;
//	}
//
//}
