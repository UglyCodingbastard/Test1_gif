package connect;

public class Log_DTO { //로그인시 입력된 정보를 임시 저장하고 DAO로 넘겨주는 클래스
	
	String log_id; //로그인시 입력된 아이디 정보를 저장하는 변수
	String log_pwd; //로그인시 입력된 pwd 정보를 저장하는 변수
	String log_name; //로그인시 입력된 이름 정보를 저장하는 변수
	String log_email; //로그인시 입력된 이메일 정보를 저장하는 변수
	String id_num;	  //로그인시 입력된 회원번호 정보를 저장하는 변수
	
	

public String getid_num() { 
		
		return id_num;
	}
	public void setid_num(String id_num) { 
		this.id_num=id_num;
	}
	
	public String getlog_id() { //set에서 받은 정보를 DAO로 넘겨줌
		return log_id;
	}
	public void setlog_id(String log_id) { //MainUI 에서 로그인시 사용자가 입력한 id정보를 받아와 log_id로 저장
		this.log_id=log_id;
	}
	
	public String getlog_pwd() {
		return log_pwd;
	}
	public void setlog_pwd(String log_pwd) {
		this.log_pwd=log_pwd;
	}
	
	public String getlog_name() {
		return log_name;
	}
	public void setlog_name(String log_name) {
		this.log_name=log_name;
	}
	
	public String getlog_email() {
		return log_email;
	}
	public void setlog_email(String log_email) {
		this.log_email=log_email;
	}
}
