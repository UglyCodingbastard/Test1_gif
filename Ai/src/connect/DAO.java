package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {

	public static void main(String[] args) {
		String id;
		String pwd;
		// MainUI ui = new MainUI();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root", "1234");
			String qu = "select * from member_info";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(qu);

			while (rs.next()) {
//        	 if (rs.getString(id).equals(id)) {
//					System.out.println("중복된 아이디 입니다.");
//				}
				id = rs.getString("id");
				pwd = rs.getString("pwd");
				System.out.println(id + "        " + pwd + "\n");
			}
			st.close();
		} catch (Exception e) {
			System.err.println("오류!");
			System.out.println(e.getMessage());
		}
	}

	public static boolean create(DTO dto) throws Exception {
		
		String id_re;
		String pwd_re;
		
		boolean flag = false;

		Connection con = null;
		Statement stmt = null; // 데이터를 전송하는 객체
		ResultSet rs = null;
		Boolean connect = false;

		StringBuffer sel = new StringBuffer();

		String id = dto.getid();
		String pwd = dto.getpwd();
		String email = dto.geteamil();
		String name = dto.getname();

		String sql = "INSERT INTO member_info(id, pwd, email, name) VALUES";
		
		String query = "SELECT * from member_info";

		try {
			sql += "('" + new String(id.getBytes(), "ISO-8859-1") + "','"

					+ new String(pwd.getBytes(), "ISO-8859-1") + "','" + new String(email.getBytes(), "ISO-8859-1")
					+ "','" + new String(name.getBytes(), "euckr") + "')";

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/members?useSSL=false&useUnicode=true&characterEncoding=euckr&user=root&password=1234");

			connect = true;

			stmt = (Statement) con.createStatement();

			stmt.executeUpdate(sql);
			//stmt.executeQuery(query);
			
			rs = stmt.executeQuery(query);

			flag = true;

			while (rs.next()) {
				id_re = rs.getString("id");
				pwd_re = rs.getString("pwd");
				System.out.println(id_re+"     "+pwd_re+"\n");
//				if (rs.getString(id).equals(id)) {
//					System.out.println("중복된 아이디 입니다.");
//				}
			}
		} catch (Exception e) {
			connect = false;
			System.out.println(e);
			flag = false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return flag;
	}
}