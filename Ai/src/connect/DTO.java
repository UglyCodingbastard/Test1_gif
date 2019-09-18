package connect;

public class DTO {   //입력된 정보를 DB로 날려주는 클래스

   String id;
   String pwd;
   String pwd_re;
   String email;
   String name;
   
   String log_id;
   String log_pwd;
   
   public String getlog_id() {
      return log_id;
   }
   public void setlog_id(String log_id) {
      this.log_id=log_id;
   }
   
   public String getlog_pwd() {
      return log_pwd;
   }
   public void setlog_pwd(String log_pwd) {
      this.log_pwd=log_pwd;
   }
   
   public String getid() {
      
      return id;
   }
   public void setid(String id) {
      this.id=id;
   }
   
   public String getpwd() {         
      return pwd;
   }
   public void setpwd(String pwd) {
      this.pwd=pwd;
   }
   
   public String getpwd_re() {         
      return pwd_re;
   }
   public void setpwd_re(String pwd_re) {
      this.pwd_re=pwd_re;
   }
   
   public String geteamil() {
      return email;
   }
   public void setemail(String email) {
      this.email=email;
   }
   
   public String getname() {
      return name;
   }
   public void setname(String name) {
      this.name=name;
   }

}