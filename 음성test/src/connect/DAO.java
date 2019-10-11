package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DAO { // DTO에서 받은 회원가입 정보를 바탕으로 쿼리에 명령을 내려주는 클래스

	public static void main(String[] args) {

	}

	public static boolean create(DTO dto) throws Exception {

		boolean flag = false;

		Connection con = null; // 데이터베이스와 연결을 담당하는 객체
		Statement stmt = null; // 데이터를 전송하는 객체
		String id = dto.getid(); // DTO에서 전송받은 ID 값을 저장하는 변수
		String pwd = dto.getpwd(); // DTO에서 전송받은 pwd 값을 저장하는 변수
		String email = dto.geteamil(); // DTO에서 전송받은 이메일값을 저장하는 변수
		String name = dto.getname(); // DTO에서 전송받은 이름 값을 저장하는 변수
		String zero = "'0'"; // search_site의 카운트를 0으로 초기화 시켜주기 위한 변수

		String sql = "INSERT INTO member_info(id, pwd, email, name) VALUES"; // 회원가입시 사용자가 입력한 정보를 데이터베이스에 입력하는 쿼리문
																				// member_info 테이블
		String sql2 = "INSERT INTO site_search(id,naver, daum, google) VALUES"; // 회원가입시 사용자가 입력한 id정보와 회원 정보를 데이터 베이스에
																				// 입력하는 쿼리문 site(search) 테이블
		try {
			sql2 += "('" + new String(id.getBytes(), "ISO-8859-1") + "'," + zero + "," + zero + "," + zero + ")"; // search_site테이블에
																													// id_num,
																													// id값을
																													// 저장하고
																													// 카운트
																													// 값을
																													// 0으로
																													// 초기화
																													// 시켜주는
																													// 쿼리문
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection( // 실질적으로 데이터베이스와 클래스를 연결시켜주는 객체
					"jdbc:mysql://127.0.0.1/members?useSSL=false&useUnicode=true&characterEncoding=euckr&user=root&password=12345");
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate(sql2); // table에 값을 업데이트 시켜주는 부분
			flag = true; // try문을 성공적으로 실행했을때 반환되는 flag값
		} catch (Exception e) {
			System.out.println(e); // 오류를 출력해주는 부분
			flag = false; // try문이 성공적으로 실행되지 않았을때 반환되는 flag값
		} finally { // 최종적으로 각 객체가 null이 아닐때 프로그램이 실행되고 닫아주는 부분
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		try { // member_info에 값을 추가해주는 부분 ( name부분에 euckr을 입력 해주는 이유는 name값에 한글이 들어올 수 있기
				// 때문이다.
			sql += "('" + new String(id.getBytes(), "ISO-8859-1") + "','"

					+ new String(pwd.getBytes(), "ISO-8859-1") + "','" + new String(email.getBytes(), "ISO-8859-1")
					+ "','" + new String(name.getBytes(), "euckr") + "')";

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/members?useSSL=false&useUnicode=true&characterEncoding=euckr&user=root&password=12345");
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
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
