package connect;

public class DTO {	//ȸ�����Խ� �Էµ� ������ DAO�� �����ִ� Ŭ����

	String id;	//ȸ�����Խ� ����ڰ� �Է��� id ������ �����ϴ� ����
	String pwd; //ȸ�����Խ� ����ڰ� �Է���  pwd������ �����ϴ� ����
	String pwd_re; //ȸ�����Խ� ����ڰ� �Է��� pwd��Ȯ�� ������ �����ϴ� ����
	String email; //ȸ�����Խ� ����ڰ� �Է��� �̸��� ������ �����ϴ� ����
	String name; //ȸ�����Խ� ����ڰ� �Է��� �̸� ������ �����ϴ� ����
 
	
	public String getid() { //set���� ���� ������ DAO�� �Ѱ���
		
		return id;
	}
	public void setid(String id) { //MainUI ���� ȸ�����Խ� ����ڰ� �Է��� id������ �޾ƿ� log_id�� ����
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
