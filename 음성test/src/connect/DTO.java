package connect;

public class DTO {	//�Էµ� ������ DB�� �����ִ� Ŭ����

	String id;
	String pwd;
	String pwd_re;
	String email;
	String name;

	
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
