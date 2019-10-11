package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DAO { // DTO���� ���� ȸ������ ������ �������� ������ ����� �����ִ� Ŭ����

	public static void main(String[] args) {

	}

	public static boolean create(DTO dto) throws Exception {

		boolean flag = false;

		Connection con = null; // �����ͺ��̽��� ������ ����ϴ� ��ü
		Statement stmt = null; // �����͸� �����ϴ� ��ü
		String id = dto.getid(); // DTO���� ���۹��� ID ���� �����ϴ� ����
		String pwd = dto.getpwd(); // DTO���� ���۹��� pwd ���� �����ϴ� ����
		String email = dto.geteamil(); // DTO���� ���۹��� �̸��ϰ��� �����ϴ� ����
		String name = dto.getname(); // DTO���� ���۹��� �̸� ���� �����ϴ� ����
		String zero = "'0'"; // search_site�� ī��Ʈ�� 0���� �ʱ�ȭ �����ֱ� ���� ����

		String sql = "INSERT INTO member_info(id, pwd, email, name) VALUES"; // ȸ�����Խ� ����ڰ� �Է��� ������ �����ͺ��̽��� �Է��ϴ� ������
																				// member_info ���̺�
		String sql2 = "INSERT INTO site_search(id,naver, daum, google) VALUES"; // ȸ�����Խ� ����ڰ� �Է��� id������ ȸ�� ������ ������ ���̽���
																				// �Է��ϴ� ������ site(search) ���̺�
		try {
			sql2 += "('" + new String(id.getBytes(), "ISO-8859-1") + "'," + zero + "," + zero + "," + zero + ")"; // search_site���̺�
																													// id_num,
																													// id����
																													// �����ϰ�
																													// ī��Ʈ
																													// ����
																													// 0����
																													// �ʱ�ȭ
																													// �����ִ�
																													// ������
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection( // ���������� �����ͺ��̽��� Ŭ������ ��������ִ� ��ü
					"jdbc:mysql://127.0.0.1/members?useSSL=false&useUnicode=true&characterEncoding=euckr&user=root&password=12345");
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate(sql2); // table�� ���� ������Ʈ �����ִ� �κ�
			flag = true; // try���� ���������� ���������� ��ȯ�Ǵ� flag��
		} catch (Exception e) {
			System.out.println(e); // ������ ������ִ� �κ�
			flag = false; // try���� ���������� ������� �ʾ����� ��ȯ�Ǵ� flag��
		} finally { // ���������� �� ��ü�� null�� �ƴҶ� ���α׷��� ����ǰ� �ݾ��ִ� �κ�
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		try { // member_info�� ���� �߰����ִ� �κ� ( name�κп� euckr�� �Է� ���ִ� ������ name���� �ѱ��� ���� �� �ֱ�
				// �����̴�.
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
