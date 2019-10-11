package connect;

public class DTO {	//회원가입시 입력된 정보를 DAO로 날려주는 클래스

	String id;	//회원가입시 사용자가 입력한 id 정보를 저장하는 변수
	String pwd; //회원가입시 사용자가 입력한  pwd정보를 저장하는 변수
	String pwd_re; //회원가입시 사용자가 입력한 pwd재확인 정보를 저장하는 변수
	String email; //회원가입시 사용자가 입력한 이메일 정보를 저장하는 변수
	String name; //회원가입시 사용자가 입력한 이름 정보를 저장하는 변수
 
	
	public String getid() { //set에서 받은 정보를 DAO로 넘겨줌
		
		return id;
	}
	public void setid(String id) { //MainUI 에서 회원가입시 사용자가 입력한 id정보를 받아와 log_id로 저장
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
