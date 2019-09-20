package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DAO { 

   public static void main(String[] args) {

   }

   public static boolean create(DTO dto) throws Exception {

      boolean flag = false;

      Connection con = null;
      Statement stmt = null; // 데이터를 전송하는 객체
      String 
      id = dto.getid();
      String pwd = dto.getpwd();
      String email = dto.geteamil();
      String name = dto.getname();
      String zero = "'0'";
      
      

      String sql = "INSERT INTO member_info(id, pwd, email, name) VALUES";
      String sql2 = "INSERT INTO site_search(naver_count, daum_count, google_count) VALUES";
      try {
         sql2 +="("+zero+","+zero+","+zero+")";
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/members?useSSL=false&useUnicode=true&characterEncoding=euckr&user=root&password=1234");
         stmt = (Statement) con.createStatement();
         stmt.executeUpdate(sql2);
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
         
         
         
      
      try {
         sql += "('" + new String(id.getBytes(), "ISO-8859-1") + "','"

               + new String(pwd.getBytes(), "ISO-8859-1") + "','" + new String(email.getBytes(), "ISO-8859-1")
               + "','" + new String(name.getBytes(), "euckr") + "')";

         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/members?useSSL=false&useUnicode=true&characterEncoding=euckr&user=root&password=1234");
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