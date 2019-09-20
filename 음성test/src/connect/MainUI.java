package connect;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import connect.JPanel01.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainUI extends JFrame {
	Container pane = getContentPane();// 컨텐트팬 알아내기
	
	public static void main(String[] args) {

		JPanelTest win = new JPanelTest();

		win.setTitle("frame test");
		win.jpanel01 = new JPanel01(win);
		win.jpanel02 = new JPanel02(win);
		win.jpanel03 = new JPanel03(win);

		win.add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		win.setSize(500, 450);
		win.setVisible(true);
		win.setLocationRelativeTo(null); // 창화면이 센터에 나오게함
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // 메인 홈 화면

	private JPasswordField passwordField;
	private static JPanelTest win;
	static JButton btn = new JButton("로그인");
	static JTextField id = new JTextField("아이디");
	static JPasswordField pwd = new JPasswordField("비밀번호");
	
	public JPanel01(JPanelTest win) {
		setLayout(null);
		this.win = win;
     
		win.setBackground(new Color(102, 204, 102));
		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);

		id.setBounds(120, 76, 260, 35);
		add(id);
		id.setColumns(10);

		pwd.setBounds(120, 125, 260, 35);
		add(pwd);

		btn.setBackground(Color.green);
		btn.setBorder(bevel);
		btn.setSize(260, 47);
		btn.setLocation(120, 174);
		add(btn);

		JCheckBox check = new JCheckBox("로그인 상태 유지", false);
		check.setSize(115, 20);
		check.setLocation(120, 233);
		add(check);

		JButton btn2 = new JButton("회원가입");
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // 테두리 제거
		btn2.setContentAreaFilled(false); // 배경제거

		btn2.setSize(70, 20);
		btn2.setLocation(310, 233);
		add(btn2);

		btn.addActionListener(new Login());
		btn2.addActionListener(new Membership());
	}
   
	static class Login implements ActionListener { // 로그인 버튼 눌렀을 때, 버튼 키 눌리면 패널 3번(로그인 후 화면) 호출

		static String ide;
	    static String pass;
		@Override
		public void actionPerformed(ActionEvent e) {
			 
			Log_DTO ldto = new Log_DTO();
			Object obj3 = e.getSource();

			String db_id = null;
			String db_pwd = null;
			String db_name = null;
			String db_email = null;

			if ((JButton) obj3 == btn) { // 로그인 버튼 눌렀을 때

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
							"12345");
					String get_inputid = id.getText();
					String get_inputpwd = pwd.getText();

					String qu = "select * from member_info where id=" + "'" + get_inputid + "'";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu);

					while (rs.next()) {

						db_id = rs.getString("id");
						db_pwd = rs.getString("pwd");
						//db_name = rs.getString("name");
						//db_email = rs.getString("email");						
						//System.out.println(db_id+db_pwd+db_name+db_email);

					}

					if (db_id.equals(id.getText()) && db_pwd.equals(pwd.getText())) {
						
						
						ide=db_id;
						pass=db_pwd;
                        System.out.println(ide);
                        System.out.println(db_pwd);
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						win.change("panel03");

					} else {
						JOptionPane.showMessageDialog(null, "아이디나 패스워드가 일치하지 않습니다.");
					}

					st.close();
				} catch (Exception e2) {
					System.err.println("오류!");
					System.out.println(e2.getMessage());
				}
			}

		}
	}

	class Membership implements ActionListener { // 버튼 키 눌리면 패널 2번(회원가입) 호출

		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel02");
		}
	}
}

class JPanel02 extends JPanel { // 회원가입 화면
	private JButton jButton1, jButton2, jButton3;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;

	private JTextField textField;
	private JPasswordField passwordField;
	private JPanelTest win;

	JTextField input_id = new JTextField(10);
	JPasswordField input_pwd = new JPasswordField(10);
	JTextField input_email = new JTextField(30); // 이메일
	JTextField input_name = new JTextField(20); // 사용자 이름
	JPasswordField input_pwd_re = new JPasswordField(10);

	JButton over = new JButton();
	JButton btn3 = new JButton();

	public JPanel02(JPanelTest win) {
		this.win = win;
		setLayout(null);
		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);

		JLabel label_id = new JLabel("아이디");
		label_id.setBounds(120, 17, 50, 50);
		add(label_id);
		input_id.setBounds(120, 50, 260, 30);
		add(input_id);

		JLabel label_password = new JLabel("비밀번호");
		label_password.setBounds(120, 70, 50, 50);
		add(label_password);
		input_pwd.setBounds(120, 103, 260, 30);
		add(input_pwd);

		JLabel label_repassword = new JLabel("비밀번호 재확인");
		label_repassword.setBounds(120, 123, 100, 50);
		add(label_repassword);
		input_pwd_re.setBounds(120, 156, 260, 30);
		add(input_pwd_re);

		JLabel label_email = new JLabel("이메일");
		label_email.setBounds(120, 176, 50, 50);
		add(label_email);
		input_email.setBounds(120, 209, 260, 30);
		add(input_email);

		JLabel label_uesrname = new JLabel("사용자이름");
		label_uesrname.setBounds(120, 229, 70, 50);
		add(label_uesrname);
		input_name.setBounds(120, 262, 260, 30);
		add(input_name);

		over = new JButton("중복확인");
		over.setSize(82, 30);
		over.setLocation(385, 50);
		over.setBorderPainted(false); // 테두리 제거
		over.setContentAreaFilled(false); // 배경제거
		add(over);

		btn3 = new JButton("회원가입 완료");
		btn3.setSize(260, 35);
		btn3.setLocation(120, 321);
		btn3.setBorder(bevel);
		btn3.setBackground(Color.green);
		add(btn3);

		jButton2 = new JButton("로그인");
		jButton2.setSize(70, 35);
		jButton2.setLocation(217, 370);
		jButton2.setBorderPainted(false); // 테두리 제거
		jButton2.setContentAreaFilled(false); // 배경제거
		add(jButton2);

		jButton2.addActionListener(new Home()); // 취소 버튼 누를 시 홈 화면으로 이동
		btn3.addActionListener(new Ok()); // 회원가입 완료 버튼 누를시 회원가입 완료 라는 문구와 같이 새로운 창이 하나뜨면서 홈으로 이동
		over.addActionListener(new Ok()); // 중복확인 버튼 누를시
	}

	class Home implements ActionListener { // 버튼 키 눌리면 패널 1번(로그인 화면) 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}

	class Ok implements ActionListener { // 버튼 키 눌리면 '회원가입 완료' 문구와 함께 창이 새롭게 하나 뜬 후 회원가입 완료

		@Override
		public void actionPerformed(ActionEvent e) {

			DTO dto = new DTO(); // 회원가입
			DAO dao = new DAO();
			Object obj = e.getSource();
			String sql = null;

			if ((JButton) obj == btn3) { // 회원가입 완료 눌렀을 때
				dto.setid(input_id.getText());
				dto.setpwd(input_pwd.getText());
				dto.setpwd_re(input_pwd_re.getText());
				dto.setemail(input_email.getText());
				dto.setname(input_name.getText());

				if (input_pwd.getText().equals(input_pwd_re.getText())) {
					if (dto.id.equals("") || dto.pwd.equals("") || dto.email.equals("") || dto.name.equals("")) {
						JOptionPane.showMessageDialog(null, "입력되지 않은 사항이 있습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "회원가입 완료"); // 회원가입 알람 창이 나타남
						win.change("panel01");
						try {
							DAO.create(dto); // dto를 DAO에 넘겨준다.
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else if (input_pwd.getText() != input_pwd_re.getText()) {

					JOptionPane.showMessageDialog(null, "비밀번호를 재확인 해주세요.");
					win.change("panel02");

				}

			}
		}
	}
}

class JPanel03 extends JPanel { // 로그인 후 화면
	private JButton jButton1, jButton2, jButton3;
	private JTextField textField;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanelTest win;
	private final static String newline = "\n";

	private JTextField Text_field = new JTextField();
	private JTextArea Text_area = new JTextArea();
	String var_1 = "";
	Choice ch;

	public JPanel03(JPanelTest win) {
		this.win = win;
		this.Text_field = Text_field;
		setLayout(null);
        
	
		//Log_DTO ldto = new Log_DTO();
		
		//String db_name = ldto.getlog_name();
		jButton1 = new JButton("음성인식 시작");
		jButton1.setSize(300, 80);
		jButton1.setLocation(100, 300);
		add(jButton1);

		ch = new Choice(); // 사용할 사이트
		ch.addItem("사용할 사이트");
		ch.addItem("www.naver.com");
		ch.addItem("www.google.com");
		ch.addItem("www.daum.net");
		ch.setBounds(20, 20, 109, 35);

		Log_DTO ldto2 = new Log_DTO();
		String user_name=ldto2.getlog_name();
		
		System.out.println(user_name);

		JLabel label_user = new JLabel(user_name); // 로그인한 사용자 id출력하게 해야됨(id 최대 길이를 정해둬야됨
		label_user.setBounds(300, 8, 60, 50);
		add(label_user);

		jButton2 = new JButton("로그아웃"); // 로그아웃
		jButton2.setSize(82, 25);
		jButton2.setLocation(386, 20);
		jButton2.setContentAreaFilled(false); // 배경제거
		add(jButton2);

		Text_field.setBounds(100, 70, 300, 30); // 검색 창
		add(Text_field);
		JScrollPane pane = new JScrollPane(Text_area); // JTextArea area = new JTextArea("검색어"); //검색어
		pane.setSize(300, 180);
		pane.setLocation(100, 105);

		Text_area.setEditable(false); // JTextArea 안에 텍스트 입력 금지
		Text_area.setLineWrap(true); // 행 넘기기 기능 켜기

		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1); // TextArea의 테두리선의 색을 검정 두깨를 3px로 설정합니다.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서
		// emptyBorder를 생성합니다.
		pane.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder)); // TextArea에 lineBorder(검정테두리),
		// emptyBorder(여백)로 구성된 복합 경계선을
		// 설정합니다.
		add(pane);

		ImageIcon normalicon = new ImageIcon("icon_images/돋보기.jpg");
		jButton3 = new JButton("", normalicon); // 로그아웃
		jButton3.setSize(30, 30);
		jButton3.setLocation(410, 70);
		add(jButton3);

		jButton2.addActionListener(new Home()); // 로그아웃 버튼 누를시 로그인 창 출력
		jButton3.addActionListener(new search()); // 검색 버튼 누를시 아래에 출력
		Text_field.addActionListener(new search()); // Enter 눌렀을 시 아래에 출력
	}

	class Home implements ActionListener { // 버튼 키 눌리면 패널 1번(로그인 화면) 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}

	class search implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Text_field || e.getSource() == jButton3) { // Enter or 검색 버튼 누를 시 검색 기능
				if (Text_field.getText().equals("")) {
					Text_area.append("검색어를 입력해주세요." + newline);
					Text_field.requestFocus();
				} else {
					String str = Text_field.getText(); // 돋보기 버튼 누를시 텍스트 필드의 값을 불러옴
					Text_area.append(str + newline); // 텍스트area에 텍스트 필드의 값과 + '\n'을 합쳐서 엔터기능 만듬
					// Text_field.selectAll(); //검색 후 검색 한 텍스트가 전체 드래그 된다.
					Text_field.requestFocus(); // 검색 버튼 누른 후 커서가 다시 Text_field에 위치한다.
					// Text_area.setCaretPosition(Text_area.getDocument().getLength());
					Text_field.setText(""); // 검색 후 검색 칸이 빈칸으로 초기화 돤디.
				}

			}
		}
	}
}

class JPanelTest extends JFrame {

	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;
	public JPanel03 jpanel03 = null;

	public void change(String panelName) { // 패널 1번과 2번 변경 후 재설정

		if (panelName.equals("panel01")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			//revalidate();
			repaint();
		} else if (panelName.contentEquals("panel02")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			//revalidate();
			repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(jpanel03);
			revalidate();
			repaint();
		}
	}

}