package connect;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.lang.StringBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledEditorKit.BoldAction;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Ai extends JFrame {
	Container pane = getContentPane();// 컨텐트팬 알아내기

	public static void main(String[] args) {

		JPanelTest win = new JPanelTest(); // win JPanelTest선언

		win.setTitle("frame test"); // Title name
		win.jpanel01 = new JPanel01(win); // JPanel01
		win.jpanel02 = new JPanel02(win); // JPanel02
		win.jpanel03 = new JPanel03(win); // JPanel03

		win.jpanel01.setBackground(new Color(244, 244, 244)); // jpanel01 뒷 배경색
		win.jpanel02.setBackground(new Color(244, 244, 244)); // jpanel02 뒷 배경색
		win.jpanel03.setBackground(new Color(244, 244, 244)); // jpanel03 뒷 배경색

//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root", "1234");
//			String get_inputid = id_readBuffer;
//			String get_inputpwd = pw_readBuffer;
//
//			String qu = "select * from member_info where id=" + "'" + get_inputid + "'";
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(qu);
//
//			while (rs.next()) {
//
//				db_id_num = rs.getString("id_num");
//				db_id = rs.getString("id");
//				db_pwd = rs.getString("pwd");
//				db_name = rs.getString("name");
//				db_email = rs.getString("email");
//
//			}
//
//			if (db_id.equals(id_readBuffer) && db_pwd.equals(pw_readBuffer)) {
//
//				JPanel03.in_id_num = db_id_num;
//				JPanel03.in_id = db_id;
//				JPanel03.in_pwd = db_pwd;
//				JPanel03.in_name = db_name;
//				JPanel03.in_email = db_email;
//				JPanel03.label_user.setText(JPanel03.in_name + "님"); // 패널1의 데이터를 패널3에서 쓰기위해 넣음
//				
//				//win.add(win.jpanel03);
//				//System.out.println("true");
//				JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
//				win.change("panel03");
//
//			} else if (!db_id.equals(id_readBuffer) || !db_pwd.equals(pw_readBuffer)) {
//                  JOptionPane.showMessageDialog(null, "아이디나 패스워드가 일치하지 않습니다.");
//               }
//
//			st.close();
//		} catch (Exception e2) {
//			JOptionPane.showMessageDialog(null, "아이디나 패스워드가 일치하지 않습니다.");
//			System.err.println("오류!");
//			// System.out.println(e2.getMessage());
//		}

		win.add(win.jpanel01); // 프로그램 시작시 JPanel01 생성

		// win.add(win.jpanel01);
		// System.out.println("true");
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 윈도우창 종료 시 프로세스까지 닫음

		win.setSize(500, 450); // win 사이즈
		// win.setBackground(Color.black);

		win.setVisible(true);
		win.setLocationRelativeTo(null); // 창화면이 센터에 나오게함
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // 메인 홈 화면
	// private JTextField textField;
	private static JPanelTest win;

	static JButton btn = new JButton("로그인");
	static JTextField id = new JTextField("아이디");
	static JCheckBox check; // check 자동 로그인
	static JPasswordField pwd = new JPasswordField("비밀번호");

	static String id_readBuffer = null; // 파일 ID변수
	static String pw_readBuffer = null; // 파일 PW변수
	static String state_readBuffer = null; // 파일 state변수

	public JPanel01(JPanelTest win) {
		setLayout(null);
		this.win = win;
		/**
		 * 프로그램이 처음 실행되면 사용자가 자동로그인 상태인지 아닌지를 구별하기 위해 state.txt 파일에 사용자의 프로그램 종료 전의 상태를
		 * 저장한다. 자동로그인 기능을 체크 로그인 후 로그아웃 하지 않고 종료시 state.txt에 true 저장 자동로그인 기능을 체크 로그인 후
		 * 로그아웃으로 프로그램 종료시 state.txt에 false 저장 상태에 따라 id.textfield와 pwd.textfield 에 정보
		 * 입력되어있음
		 **/
		// 파일 객체 생성
		Path path_id = Paths.get("C:/Users/User/Desktop/음성AI/자동로그인/ID.txt"); // ID.txt 경로
		Path path_pw = Paths.get("C:/Users/User/Desktop/음성AI/자동로그인/PW.txt"); // PW.txt 경로
		Path path_state = Paths.get("C:/Users/User/Desktop/음성AI/자동로그인/state.txt"); // state.txt 경로

		// 캐릭터셋 지정
		Charset cs = StandardCharsets.UTF_8;

		// 파일 내용담을 리스트
		List<String> list_id = new ArrayList<String>();
		List<String> list_pw = new ArrayList<String>();
		List<String> list_state = new ArrayList<String>();

		try {
			list_id = Files.readAllLines(path_id, cs);
			list_pw = Files.readAllLines(path_pw, cs);
			list_state = Files.readAllLines(path_state, cs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String readLine_id : list_id) {
			id_readBuffer = readLine_id; // ID.txt String으로 불러와 id_readBuffer 저장
		}
		for (String readLine_pw : list_pw) {
			pw_readBuffer = readLine_pw; // PW.txt String으로 불러와 pw_readBuffer 저장
		}
		for (String readLine_state : list_state) {
			state_readBuffer = readLine_state; // state.txt String으로 불러와 state_readBuffer 저장
		}

		win.setBackground(new Color(102, 204, 102));
		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
		LineBorder b2 = new LineBorder(Color.darkGray, 3);

		JTextField trash = new JTextField(); // 첫 포커스 없애기 위한 trash 텍스트 필드
		trash.setBounds(0, 0, 0, 0);
		add(trash);

		ImageIcon img = new ImageIcon("icon_images/로고.png");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(120, 60, 255, 35);
		add(imageLabel);

		if (state_readBuffer.equals("true")) { // state.txt 상태가 true인 경우 아이디와 비밀번호 입력돼있음
			id = new JTextField(id_readBuffer);
			id.setForeground(new Color(0, 0, 0));

			pwd = new JPasswordField(pw_readBuffer);
			pwd.setForeground(new Color(0, 0, 0));

		} else if (state_readBuffer.equals("false")) { // state.txt 상태가 false인 경우 첫 로그인 화면 나옴
			id = new JTextField("아이디");
			id.setForeground(new Color(150, 150, 150));

			pwd = new JPasswordField("비밀번호");
			pwd.setForeground(new Color(150, 150, 150));

		}
		id.setBounds(120, 116, 260, 35);
		id.addFocusListener(new myFocusListener_id());
		add(id);

		pwd.setBounds(120, 165, 260, 35);
		pwd.addFocusListener(new myFocusListener_password());
		add(pwd);

		btn.setBackground(new Color(102, 204, 102));
		btn.setBorder(bevel);
		btn.setSize(260, 47);
		btn.setLocation(120, 214);
		btn.setFocusPainted(false);
		add(btn); // 로그인 버튼 생성

		if (state_readBuffer.equals("true")) { // true인 경우 check박스 체크
			check = new JCheckBox("로그인 정보 저장", true);
		} else if (state_readBuffer.equals("false")) { // false인 경우 check박스 해제
			check = new JCheckBox("로그인 정보 저장", false);
		}
		check.setSize(115, 20);
		check.setLocation(120, 273);
		check.setBackground(new Color(244, 244, 244));
		check.setFocusPainted(false); // 버튼 포커스 제거
		add(check); // 로그인 정보 저장 체크 박스

		JButton btn2 = new JButton("회원가입");
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // 테두리 제거
		btn2.setContentAreaFilled(false); // 배경제거
		btn2.setFocusPainted(false); // 버튼 포커스 제거
		btn2.setSize(70, 20);
		btn2.setLocation(310, 273);
		add(btn2);

		btn.addActionListener(new Login()); // 로그인 액션
		btn2.addActionListener(new Membership()); // 회원가입 액션
	}

	class myFocusListener_id extends FocusAdapter { // 아이디 텍스트필드 포커스 이벤트
		public void focusGained(FocusEvent e) {
			if (id.getText().equals("아이디")) { // 텍스트 필드에 "아이디"문구가 있을경우 빈칸으로 초기화
				id.setForeground(new Color(0, 0, 0));
				id.setText(""); // 포커스를 가져왔을 때 텍스트 클릭시 빈칸으로 초기화
			}
		}

		public void focusLost(FocusEvent e) { // 포커스를 잃어버릴 때
			if (id.getText().equals("")) { // id.textfield에 빈칸인 경우
				id.setForeground(new Color(150, 150, 150));
				id.setText("아이디"); // 아이디 텍스트 생성
			}
		}
	}

	class myFocusListener_password extends FocusAdapter { // 비밀번호 텍스트필드 포커스 이벤트

		public void focusGained(FocusEvent e) { // 포커스를 가져왔을 때
			if (pwd.getText().equals("비밀번호")) { // 텍스트 필드에 "비밀번호"문구가 있을경우 빈칸으로 초기화
				pwd.setForeground(new Color(0, 0, 0));
				pwd.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // 포커스를 잃어버릴 때
			if (pwd.getText().equals("")) { // pwd.textfield에 빈칸인 경우
				pwd.setForeground(new Color(150, 150, 150));
				pwd.setText("비밀번호"); // 비밀번호 텍스트 생성
			}
		}
	}

	static class Login implements ActionListener { // 로그인 버튼 기능

		@Override
		public void actionPerformed(ActionEvent e) {

			Log_DTO ldto = new Log_DTO();
			Object obj3 = e.getSource();
			boolean index = check.isSelected(); // check 상태 체크

			String db_id = null;
			String db_pwd = null;
			String db_name = null;
			String db_email = null;
			String db_id_num = null;

			if ((JButton) obj3 == btn) { // 로그인 버튼 눌렀을 때
				if (index == true) { // choice 에서 클릭한 아이템을 불러온다. 자동 로그인 체크 기능 눌렸을시

					BufferedOutputStream bs = null;
					BufferedOutputStream bs1 = null;
					BufferedOutputStream bs2 = null;

					try {
						bs = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/음성AI/자동로그인/ID.txt")); // id저장
						bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/음성AI/자동로그인/PW.txt")); // pw저장
						bs2 = new BufferedOutputStream(
								new FileOutputStream("C:/Users/User/Desktop/음성AI/자동로그인/state.txt")); // 자동 로그인 상태저장

						String str = id.getText(); // id를 받아옴
						String str1 = pwd.getText();// pw를 받아옴

						if (index == true) { // 자동로그인 상태가 true일 경우 텍스트 파일에 true저장
							String str2 = "true";
							bs2.write(str2.getBytes());
						}

						bs.write(str.getBytes()); // Byte형으로만 넣을 수 있음
						bs1.write(str1.getBytes()); // Byte형으로만 넣을 수 있음

					} catch (Exception e1) {
						e1.getStackTrace();
						// TODO: handle exception
					} finally {
						try {
							bs.close();
							bs1.close();
							bs2.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} // 반드시 닫는다.
					}
				}

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root", "1234");
					String get_inputid = id.getText();
					String get_inputpwd = pwd.getText();

					String qu = "select * from member_info where id=" + "'" + get_inputid + "'";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu);

					while (rs.next()) {

						db_id_num = rs.getString("id_num");
						db_id = rs.getString("id");
						db_pwd = rs.getString("pwd");
						db_name = rs.getString("name");
						db_email = rs.getString("email");

					}

					if (db_id.equals(id.getText()) && db_pwd.equals(pwd.getText())) {

						JPanel03.in_id_num = db_id_num;
						JPanel03.in_id = db_id;
						JPanel03.in_pwd = db_pwd;
						JPanel03.in_name = db_name;
						JPanel03.in_email = db_email;
						JPanel03.label_user.setText(JPanel03.in_name + "님"); // 패널1의 데이터를 패널3에서 쓰기위해 넣음

						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						id.setText("아이디");
						id.setForeground(new Color(150, 150, 150));
						pwd.setText("비밀번호");
						pwd.setForeground(new Color(150, 150, 150));
						win.change("panel03");

					} else if (!db_id.equals(id.getText()) || !db_pwd.equals(pwd.getText())) {
						JOptionPane.showMessageDialog(null, "아이디나 패스워드가 일치하지 않습니다.");
					}

					st.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "아이디나 패스워드가 일치하지 않습니다.");
					System.err.println("오류!");
					// System.out.println(e2.getMessage());
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

	private Connection con = null;
	private Statement stmt = null; // 데이터를 전송하는 객체
	private ResultSet rs = null;
	private Boolean connect = false;
	Boolean login = false;

	JTextField input_id = new JTextField(10); // 아이디
	JPasswordField input_pwd = new JPasswordField(10); // 패스워드
	JPasswordField input_pwd_re = new JPasswordField(10); // 패스워드 재확인
	JTextField input_email = new JTextField(30); // 이메일
	JTextField input_name = new JTextField(30); // 사용자 이름

	JButton over = new JButton(); // 중복확인 버튼
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
		over.setFocusPainted(false); // 버튼 포커스 제거
		add(over);

		btn3 = new JButton("회원가입 완료");
		btn3.setSize(260, 35);
		btn3.setLocation(120, 321);
		btn3.setBorder(bevel);
		btn3.setBackground(new Color(102, 204, 102));
		btn3.setFocusPainted(false); // 버튼 포커스 제거
		add(btn3);

		jButton2 = new JButton("로그인");
		jButton2.setSize(70, 35);
		jButton2.setLocation(217, 370);
		jButton2.setBorderPainted(false); // 테두리 제거
		jButton2.setContentAreaFilled(false); // 배경제거
		jButton2.setFocusPainted(false); // 버튼 포커스 제거
		add(jButton2);

		jButton2.addActionListener(new Home()); // 취소 버튼 누를 시 홈 화면으로 이동
		btn3.addActionListener(new Ok()); // 회원가입 완료 버튼 누를시 회원가입 완료 라는 문구와 같이 새로운 창이 하나뜨면서 홈으로 이동
		over.addActionListener(new Over()); // 아이디 중복체크
	}

	class Home implements ActionListener { // 버튼 키 눌리면 패널 1번(로그인 화면) 호출

		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}

	class Over implements ActionListener { // 아이디 중복 체크
		int result = 3; // 아이디 중복 체크 3으로 해둬야 오류 안생김

		@Override
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();
			String sql = null;

			// TODO Auto-generated method stub
			if ((JButton) obj == over) { // 중복확인 버튼을 눌렀을때
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root", "1234");
					String qu = "select * from member_info";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu);

					while (rs.next()) {
						if (input_id.getText().equals("")) { // 회원가입 아이디 칸이 공백일 때
							result = 1;
						} else if (rs.getString("id").equals(input_id.getText())) { // 회원가입 아이디가 중복일때
							result = 0;
							break;
						} else { // 사용 가능한 아이디 일 경우
							result = 2;
						}
					}
					st.close();
				} catch (Exception e1) {
					System.err.println("오류!");
					System.out.println(e1.getMessage());
				}
			}

			if (result == 0) {
				JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
			} else if (result == 1) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.");
			} else if (result == 2) {
				login = true;
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
			}
		}
	}

	class Ok implements ActionListener { // 버튼 키 눌리면 '회원가입 완료' 문구와 함께 창이 새롭게 하나 뜬 후 회원가입 완료

		@Override
		public void actionPerformed(ActionEvent e) {

			DTO dto = new DTO(); // 회원가입
			DAO dao = new DAO();

			Object obj = e.getSource();

			if ((JButton) obj == btn3) { // 회원가입 완료 눌렀을 때
				dto.setid(input_id.getText());
				dto.setpwd(input_pwd.getText());
				dto.setpwd_re(input_pwd_re.getText());
				dto.setemail(input_email.getText());
				dto.setname(input_name.getText());

				System.out.println("회원가입" + login);

				if (input_pwd.getText().equals(input_pwd_re.getText())) {
					if (dto.id.equals("") || dto.pwd.equals("") || dto.email.equals("") || dto.name.equals("")) {
						JOptionPane.showMessageDialog(null, "입력되지 않은 사항이 있습니다.");
					} else {
						if (login == true) { // 회원가입 완료할시 아이디 중복체크 여부를 확인하기 위함(사용가능한 아이디일 경우에만 해당)
							JOptionPane.showMessageDialog(null, "회원가입 완료"); // 회원가입 알람 창이 나타남

							input_id.setText(""); // 회원가입 후 입력 칸 초기화
							input_pwd.setText("");
							input_pwd_re.setText("");
							input_email.setText("");
							input_name.setText("");
							win.change("panel01");
							login = false;
							try {
								DAO.create(dto); // dto를 DAO에 넘겨준다.
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else { // 아이디 중복체크 여부를 확인 안한 경우
							JOptionPane.showMessageDialog(null, "아이디 중복확인 해주세요."); // 회원가입 알람 창이 나타남
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

class JPanel03 extends JPanel { // 로그인 후 사용자가 서비스를 이용할 화면
	static JLabel label_user;

	static String in_id_num;
	static String in_id;
	static String in_pwd;
	static String in_name;
	static String in_email;

	private JButton jButton1, jButton2, jButton3, jButton4;
	private JTextField textField;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanelTest win;
	private final static String newline = "\n";

	private JTextField Text_field = new JTextField();
	private JTextArea Text_area = new JTextArea();
	private Choice ch1, ch2;

	JTextField search = new JTextField();// 검색 창
	String var_1 = "";

	public JPanel03(JPanelTest win) {
		this.win = win;
		this.Text_field = Text_field;

		setLayout(null);

		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
		LineBorder b2 = new LineBorder(new Color(102, 204, 102), 3);

		ImageIcon play = new ImageIcon("icon_images/초록음성3.jpg"); // 음성인식 시작 버튼
		jButton1 = new JButton(play);
		jButton1.setSize(300, 100);
		jButton1.setLocation(100, 300);
		jButton1.setBackground(new Color(244, 244, 244));
		add(jButton1);

		ch1 = new Choice(); // 사용할 사이트
		ch1.addItem("사용할 사이트");
		ch1.addItem("naver");
		ch1.addItem("google");
		ch1.addItem("daum");
		ch1.setBounds(20, 20, 109, 35);
		add(ch1);

		ch2 = new Choice(); // 추천 검색어
		ch2.addItem("추천 검색어");
		ch2.setBounds(159, 20, 109, 35);
		add(ch2);

		label_user = new JLabel(in_id_num); // 로그인한 사용자 id출력하게 해야됨(id 최대 길이를 정해둬야됨
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
		Border lineBorder = BorderFactory.createLineBorder(new Color(102, 204, 102), 3); // TextArea의 테두리선의 색을 검정
																							// 두깨를
																							// 3px로 설정합니다.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서
																			// emptyBorder를 생성합니다.

		add(pane);

		jButton4 = new JButton("Clear"); // Text_area 초기화
		jButton4.setSize(70, 30);
		jButton4.setLocation(400, 260);
		jButton4.setFocusPainted(false); // 포커스 제거
		jButton4.setBorderPainted(false); // 테두리 제거
		jButton4.setContentAreaFilled(false); // 배경제거
		add(jButton4);

		jButton3 = new JButton("검색"); // 검색 버튼
		jButton3.setSize(58, 30);
		jButton3.setLocation(410, 70);
		jButton3.setBorder(b2);
		jButton3.setFocusPainted(false); // 버튼 포커스 제거
		jButton3.setBorderPainted(false); // 테두리 제거
		jButton3.setBackground(new Color(102, 204, 102));
		add(jButton3);

		jButton1.addActionListener(new Play()); // 버튼 클시 음성 시작
		jButton2.addActionListener(new Home()); // 로그아웃 버튼 누를시 로그인 창 출력
		jButton3.addActionListener(new site()); // 검색 버튼 누를시 아래에 출력
		Text_field.addActionListener(new search()); // Enter 눌렀을 시 아래에 출력
		jButton4.addActionListener(new Clear());
	}

	class site implements ActionListener { // 검색 버튼 누를시 검색 기능 수행

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Log_DTO ldto2 = new Log_DTO();
			Object obj2 = e.getSource();

			if ((JButton) obj2 == jButton3) { // 검색 버튼 누름

				if (Text_field.getText().equals("")) { // 사용자가 검색어를 아무것도 입력하지 않을경우
					if (ch1.getSelectedItem().equals("사용할 사이트")) { // 처음 등록된 사용할 사이트를 선택하지 않은 경우
						Text_area.setForeground(Color.red); // 글자색 지정
						Text_area.append("검색어와 사이트를 입력해주세요." + newline); // 검색어와 사이트를 입력 문구 출력
						Text_field.requestFocus();
					} else {
						ldto2.setid_num(JPanel03.in_id_num);
						ldto2.setlog_id(JPanel03.in_id);
						ldto2.setlog_pwd(JPanel03.in_pwd);
						ldto2.setlog_email(JPanel03.in_email);
						ldto2.setlog_name(JPanel03.in_name);

						if (ch1.getSelectedItem().equals("naver")) { // 사용할 사이트를 naver를 선택한 경우

							try {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members",
										"root", "1234");
								String qu3 = "update site_search set naver = naver+1 where id_num=" + "'"
										+ JPanel03.in_id_num + "'";
								Statement st = con.createStatement();
								int rs = st.executeUpdate(qu3);

								st.close();
							} catch (Exception e1) {
								System.err.println("오류!");
								System.out.println(e1.getMessage());
							}

						} else if (ch1.getSelectedItem().equals("daum")) { // 사용할 사이트를 daum를 선택한 경우
							try {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members",
										"root", "1234");
								String qu3 = "update site_search set daum = daum+1 where id_num=" + "'"
										+ JPanel03.in_id_num + "'";
								Statement st = con.createStatement();
								int rs = st.executeUpdate(qu3);

								st.close();
							} catch (Exception e1) {
								System.err.println("오류!");
								System.out.println(e1.getMessage());
							}
						} else if (ch1.getSelectedItem().equals("google")) { // 사용할 사이트를 google를 선택한 경우
							try {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members",
										"root", "1234");
								String qu3 = "update site_search set google = google+1 where id_num=" + "'"
										+ JPanel03.in_id_num + "'";
								Statement st = con.createStatement();
								int rs = st.executeUpdate(qu3);

								st.close();
							} catch (Exception e1) {
								System.err.println("오류!");
								System.out.println(e1.getMessage());
							}
						}
						String index = ch1.getSelectedItem(); // choice 에서 클릭한 아이템을 불러온다.
						Runtime runtime = Runtime.getRuntime();
						if (index.equals("daum")) {
							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".net/"); // 선택한 포털사이트의 홈이 뜨게 한다.
							} catch (IOException ex) {

							}
						} else {

							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".com/"); // 선택한 포털사이트의 홈이 뜨게 한다.
							} catch (IOException ex) {

							}
						}
					}
				} else {
					String str = Text_field.getText(); // 검색 버튼 누를시 텍스트 필드의 값을 불러옴
					String name1 = str; // String name1에 str을 저장한다.
					for (int i = 0; i < name1.length(); i++) {

						name1 = name1.replace("에", ""); // 기본 알고리즘을 통해 에,를,해줘 조사 제거
						name1 = name1.replace("를", "");
						name1 = name1.replace("해줘", "");

						name1 = name1.replace("네이버", // 네이버란 단어를 발견했을 시 아래와 같은 url로 변환
								"https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=");

						name1 = name1.replace("검색", "&oquery"); // 검색 단어를 발견시 검색기능으로 변환
					}

					System.out.println(name1); // 가공한 주소 + 키워드 + 동작
					Runtime runtime = Runtime.getRuntime();

					try {
						runtime.exec("C:/Program Files/internet explorer/iexplore.exe " + name1); // 기본 익스플로어 뒤에 가공한
																									// url를 입력한다.
					} catch (IOException ex) {

					}
					Text_area.append(str + newline); // 텍스트area에 텍스트 필드의 값과 + '\n'을 합쳐서 엔터기능 만듬
					Text_field.requestFocus(); // 검색 버튼 누른 후 텍스트 포커스가 다시 Text_field에 위치한다.
					Text_field.setText(""); // 검색 후 검색 칸이 빈칸으로 초기화 돤디.

				}
			}
		}
	}

	class Clear implements ActionListener { // clear 버튼

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Text_area.setText(""); // Text_area 화면을 전체 초기화
			Text_field.requestFocus();
		}
	}

	class Play implements ActionListener { // 음성인식 버튼

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == jButton1) {
				Text_area.append("What can I help you with?" + newline);
			}
		}
	}

	class Home implements ActionListener { // 로그아웃 기능
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton2) { // 로그아웃 버튼 클릭시 홈 화면으로 이동 후 모든 내용 초기화

				BufferedOutputStream bs = null;
				BufferedOutputStream bs1 = null;
				BufferedOutputStream bs2 = null;
				try {
					bs = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/음성AI/자동로그인/ID.txt")); // id저장
					bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/음성AI/자동로그인/PW.txt")); // pw저장
					bs2 = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/음성AI/자동로그인/state.txt")); // 자동
																														// 로그인
																														// 상태저장

					String str = ""; // id를 받아옴
					String str1 = "";// pw를 받아옴
					String str2 = "false";

					bs.write(str.getBytes()); // Byte형으로만 넣을 수 있음
					bs1.write(str1.getBytes()); // Byte형으로만 넣을 수 있음
					bs2.write(str2.getBytes());

				} catch (Exception e1) {
					e1.getStackTrace();
					// TODO: handle exception
				} finally {
					try {
						bs.close();
						bs1.close();
						bs2.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // 반드시 닫는다.
				}
				Text_area.setText("");
				win.change("panel01");
			}
		}
	}

	class search implements ActionListener { // 검색 텍스트 필드에서 엔터를 입력했을 경우

		@Override
		public void actionPerformed(ActionEvent e) {

			Log_DTO ldto2 = new Log_DTO();
			Object obj2 = e.getSource();

			if (e.getSource() == Text_field || e.getSource() == jButton3) { // Enter or 검색 버튼 누를 시 검색 기능

				if (Text_field.getText().equals("")) { // 사용자가 검색어를 아무것도 입력하지 않을경우
					if (ch1.getSelectedItem().equals("사용할 사이트")) {
						Text_area.setForeground(Color.red); // 글자색 지정
						Text_area.append("검색어와 사이트를 입력해주세요." + newline);
						Text_field.requestFocus();
					} else {
						ldto2.setid_num(JPanel03.in_id_num);
						ldto2.setlog_id(JPanel03.in_id);
						ldto2.setlog_pwd(JPanel03.in_pwd);
						ldto2.setlog_email(JPanel03.in_email);
						ldto2.setlog_name(JPanel03.in_name);

						if (ch1.getSelectedItem().equals("naver")) {

							try {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members",
										"root", "1234");
								String qu3 = "update site_search set naver = naver+1 where id_num=" + "'"
										+ JPanel03.in_id_num + "'";
								Statement st = con.createStatement();
								int rs = st.executeUpdate(qu3);

								st.close();
							} catch (Exception e1) {
								System.err.println("오류!");
								System.out.println(e1.getMessage());
							}

						} else if (ch1.getSelectedItem().equals("daum")) {
							try {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members",
										"root", "1234");
								String qu3 = "update site_search set daum = daum+1 where id_num=" + "'"
										+ JPanel03.in_id_num + "'";
								Statement st = con.createStatement();
								int rs = st.executeUpdate(qu3);

								st.close();
							} catch (Exception e1) {
								System.err.println("오류!");
								System.out.println(e1.getMessage());
							}
						} else if (ch1.getSelectedItem().equals("google")) {
							try {
								Class.forName("com.mysql.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members",
										"root", "1234");
								String qu3 = "update site_search set google = google+1 where id_num=" + "'"
										+ JPanel03.in_id_num + "'";
								Statement st = con.createStatement();
								int rs = st.executeUpdate(qu3);

								st.close();
							} catch (Exception e1) {
								System.err.println("오류!");
								System.out.println(e1.getMessage());
							}
						}
						String index = ch1.getSelectedItem(); // choice 에서 클릭한 아이템을 불러온다.
						Runtime runtime = Runtime.getRuntime();
						if (index.equals("daum")) {
							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".net/"); // 선택한 포털사이트의 홈이 뜨게 한다.
							} catch (IOException ex) {

							}
						} else {

							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".com/"); // 선택한 포털사이트의 홈이 뜨게 한다.
							} catch (IOException ex) {

							}
						}
					}
				} else {
					String str = Text_field.getText(); // 엔터 누를시 텍스트 필드의 값을 불러옴
					String name1 = str; // String name1에 str을 저장한다.
					for (int i = 0; i < name1.length(); i++) {

						name1 = name1.replace("에", ""); // 기본 알고리즘을 통해 에,를,해줘 조사 제거
						name1 = name1.replace("를", "");
						name1 = name1.replace("해줘", "");

						name1 = name1.replace("네이버", // 네이버란 단어를 발견했을 시 아래와 같은 url로 변환
								"https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=");

						name1 = name1.replace("검색", "&oquery"); // 검색 단어를 발견시 검색기능으로 변환
					}

					System.out.println(name1); // 가공한 주소 + 키워드 + 동작

					// Text_area.append(index + newline); // Text_area에 아이템을 출력후 엔터를 친다
					Runtime runtime = Runtime.getRuntime();
					try {
						runtime.exec("C:/Program Files/internet explorer/iexplore.exe " + name1); // 기본 익스플로어 뒤에 가공한
																									// url를 입력한다.
					} catch (IOException ex) {

					}

					Text_area.append(str + newline); // 텍스트area에 텍스트 필드의 값과 + '\n'을 합쳐서 엔터기능 만듬
					Text_field.requestFocus(); // 검색 버튼 누른 후 텍스트 포커스가 다시 Text_field에 위치한다.
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

	public void change(String panelName) { // 패널 1번 2번 3번 변경 후 재설정

		if (panelName.equals("panel01")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			revalidate();
			repaint();
		} else if (panelName.contentEquals("panel02")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			revalidate();
			repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(jpanel03);
			revalidate();
			repaint();
		}
	}
}