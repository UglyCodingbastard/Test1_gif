package connect;

public class Log_DTO { //�α��ν� �Էµ� ������ �ӽ� �����ϰ� DAO�� �Ѱ��ִ� Ŭ����
	
	String log_id; //�α��ν� �Էµ� ���̵� ������ �����ϴ� ����
	String log_pwd; //�α��ν� �Էµ� pwd ������ �����ϴ� ����
	String log_name; //�α��ν� �Էµ� �̸� ������ �����ϴ� ����
	String log_email; //�α��ν� �Էµ� �̸��� ������ �����ϴ� ����
	String id_num;	  //�α��ν� �Էµ� ȸ����ȣ ������ �����ϴ� ����
	
	

public String getid_num() { 
		
		return id_num;
	}
	public void setid_num(String id_num) { 
		this.id_num=id_num;
	}
	
	public String getlog_id() { //set���� ���� ������ DAO�� �Ѱ���
		return log_id;
	}
	public void setlog_id(String log_id) { //MainUI ���� �α��ν� ����ڰ� �Է��� id������ �޾ƿ� log_id�� ����
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
