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

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.lang.StringBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.border.LineBorder;
import connect.JPanel01.Login;
import com.thehowtotutorial.splashscreen.JSplash;

public class MainUI extends JFrame {
	Container pane = getContentPane();// 컨텐트팬 알아내기

	public static void main(String[] args) throws InterruptedException {

		JSplash splash = new JSplash(MainUI.class.getResource("그림15.png"), true, true, false, "V1", null, Color.BLUE,
				new Color(0, 255, 204)); // 로딩 화면 스플래시
		splash.setBackground(new Color(0, 0, 0));
		splash.splashOn();

		for (int i = 1; i < 101; i++) {
			splash.setForeground(Color.black);
			splash.setProgress(i, i + "%"); // 로딩 1~100% 표시
			Thread.sleep(15);
		}
		splash.splashOff();
		JPanelTest win = new JPanelTest(); // win JPanelTest선언

		win.setTitle("frame test"); // Title name
		win.jpanel01 = new JPanel01(win); // JPanel01
		win.jpanel02 = new JPanel02(win); // JPanel02
		win.jpanel03 = new JPanel03(win); // JPanel03

		win.jpanel01.setBackground(new Color(0, 0, 0)); // jpanel01 뒷 배경색
		win.jpanel02.setBackground(new Color(0, 0, 0)); // jpanel02 뒷 배경색
		win.jpanel03.setBackground(new Color(0, 0, 0)); // jpanel03 뒷 배경색

		win.add(win.jpanel01); // 프로그램 시작시 JPanel01 생성

		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 윈도우창 종료 시 프로세스까지 닫음
		win.setSize(901, 901); // win 사이즈
		win.setVisible(true);
		win.setLocationRelativeTo(null); // 창화면이 센터에 나오게함
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // 메인 홈 화면

	private static JPanelTest win;
	static JTextField id = new JTextField("아이디");
	static JPasswordField pwd = new JPasswordField("비밀번호");
	static JButton btn = new JButton("로그인");
	static JCheckBox check; // check 자동 로그인
	static String login_id;

	static String id_readBuffer = null; // 파일 ID변수
	static String pw_readBuffer = null; // 파일 PW변수
	static String state_readBuffer = null; // 파일 state변수

	public JPanel01(JPanelTest win) {
		setLayout(null);
		this.win = win;
		this.login_id = login_id;

		// 파일 객체 생성
		Path path_id = Paths.get("C:/Users/nyj/Desktop/음성AI/자동로그인/ID.txt"); // ID.txt 경로
		Path path_pw = Paths.get("C:/Users/nyj/Desktop/음성AI/자동로그인/PW.txt"); // PW.txt 경로
		Path path_state = Paths.get("C:/Users/nyj/Desktop/음성AI/자동로그인/state.txt"); // state.txt 경로

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

		ImageIcon img = new ImageIcon("icon_images/미운코딩새끼_청색.gif");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(100, 150, 700, 115);
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

		id.setBounds(225, 310, 450, 60);
		id.setFont(id.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		id.addFocusListener(new myFocusListener_id());
		add(id);

		pwd.setBounds(225, 385, 450, 60);
		pwd.setFont(pwd.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		pwd.addFocusListener(new myFocusListener_password());
		add(pwd);

		btn.setBackground(new Color(000, 255, 204));
		btn.setBorder(bevel);
		btn.setSize(450, 70);
		btn.setLocation(225, 490);
		btn.setFocusPainted(false);
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(btn.getFont().deriveFont(25.0f)); // 폰트 크기 조절
		add(btn); // 로그인 버튼 생성

		if (state_readBuffer.equals("true")) { // true인 경우 check박스 체크
			check = new JCheckBox("로그인 정보 저장", true);
		} else if (state_readBuffer.equals("false")) { // false인 경우 check박스 해제
			check = new JCheckBox("로그인 정보 저장", false);
		}
		check.setSize(170, 30);
		check.setLocation(225, 570);
		check.setBackground(new Color(0, 0, 0));
		check.setForeground(Color.white);
		check.setFocusPainted(false); // 버튼 포커스 제거
		check.setFont(check.getFont().deriveFont(18.0f)); // 폰트 크기 조절
		add(check); // 로그인 정보 저장 체크 박스

		JButton btn2 = new JButton("회원가입");
		btn2.setForeground(Color.white);
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // 테두리 제거
		btn2.setContentAreaFilled(false); // 배경제거
		btn2.setFocusPainted(false); // 버튼 포커스 제거
		btn2.setSize(70, 30);
		btn2.setLocation(605, 570);
		btn2.setFont(btn2.getFont().deriveFont(15.0f)); // 폰트 크기 조절
		add(btn2);

		btn.addActionListener(new Login());
		btn2.addActionListener(new Membership());
	}

	class myFocusListener_id extends FocusAdapter { // 아이디 텍스트필드 포커스 이벤트
		public void focusGained(FocusEvent e) { // 포커스를 가져왔을 때
			if (id.getText().equals("아이디")) { // 텍스트 필드에 "아이디"문구가 있을경우 빈칸으로 초기화
				id.setForeground(new Color(0, 0, 0));
				id.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // 포커스를 잃어버릴 때
			if (id.getText().equals("")) {
				id.setForeground(new Color(150, 150, 150));
				id.setText("아이디");
			}
		}
	}

	class myFocusListener_password extends FocusAdapter { // 아이디 텍스트필드 포커스 이벤트

		public void focusGained(FocusEvent e) { // 포커스를 가져왔을 때
			if (pwd.getText().equals("비밀번호")) { // 텍스트 필드에 "비밀번호"문구가 있을경우 빈칸으로 초기화
				pwd.setForeground(new Color(0, 0, 0));
				pwd.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // 포커스를 잃어버릴 때
			if (pwd.getText().equals("")) {
				pwd.setForeground(new Color(150, 150, 150));
				pwd.setText("비밀번호");
			}
		}
	}

	class Login implements ActionListener { // 버튼 키 눌리면 패널 3번(로그인 후 화면) 호출

		@Override
		public void actionPerformed(ActionEvent e) {

			Log_DTO ldto = new Log_DTO();
			Object obj3 = e.getSource();

			boolean index = check.isSelected(); // check 상태 체크
			String db_id = null; // 데이터베이스에서 mmeber_info 테이블에 접속해 저장된 아이디 값을 저장해주는 변수
			String db_pwd = null;
			String db_name = null;
			String db_email = null;
			String db_id_num = null;

			if ((JButton) obj3 == btn) { // 로그인 버튼 눌렀을 때 발생되는 이벤트
				System.out.println(id.getText() + "+" + pwd.getText());

				if (index == true) { // choice 에서 클릭한 아이템을 불러온다. 자동 로그인 체크 기능 눌렸을시

					BufferedOutputStream bs = null;
					BufferedOutputStream bs1 = null;
					BufferedOutputStream bs2 = null;

					try {
						bs = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/ID.txt")); // id저장
						bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/PW.txt")); // pw저장
						bs2 = new BufferedOutputStream(
								new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/state.txt")); // 자동 로그인 상태저장

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
				} else if (index == false) {
					BufferedOutputStream bs = null;
					BufferedOutputStream bs1 = null;
					BufferedOutputStream bs2 = null;

					try {
						bs = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/ID.txt")); // id저장
						bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/PW.txt")); // pw저장
						bs2 = new BufferedOutputStream(
								new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/state.txt")); // 자동 로그인 상태저장

						if (index == false) { // 자동로그인 상태가 true일 경우 텍스트 파일에 true저장
							String str2 = "false";
							bs2.write(str2.getBytes());
						}

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
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
							"12345");
					String get_inputid = id.getText(); // 로그인 창에서 id를 입력시 text를 저장하는 변수
					String get_inputpwd = pwd.getText(); // 로그인 창에서 pwd를 입력시 text를 저장하는 변수

					String qu = "select * from member_info where id=" + "'" + get_inputid + "'"; // 로그인된 정보를 바탕으로 데이터베이스
																									// 내에서 회원 정보를 알맞게
																									// 불러오는 쿼리문
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu); // 쿼리문을 실행시켜주는 객체

					while (rs.next()) { // 데이터 베이스에서 불러온 정보를 변수에 저장해주는 부분
						db_id_num = rs.getString("id_num");
						db_id = rs.getString("id");
						db_pwd = rs.getString("pwd");
						db_name = rs.getString("name");
						db_email = rs.getString("email");

					}
					if (id.getText().equals("아이디") || pwd.getText().equals("비밀번호")) {
						JOptionPane.showMessageDialog(null, "입력되지 않은 사항이 있습니다.");
					}

					else if (db_id.equals(id.getText()) && db_pwd.equals(pwd.getText())) { // 사용자가 입력한 id,pwd값과 데이터베이스에
																							// 저장된
																							// 회원정보를 비교해 로그인 성공과 실패를
																							// 나눠주는 부분

						JPanel03.in_id_num = db_id_num; // 로그인 후 사용자 정보를 사용할 수 있도록 로그인 후의 패널인 패널3에 값을 저장하는 부분
						JPanel03.in_id = db_id;
						JPanel03.in_pwd = db_pwd;
						JPanel03.in_name = db_name;
						JPanel03.in_email = db_email;
						JPanel03.label_user.setText(JPanel03.in_id); // 로그인된 정보를 바탕으로 사용자 이름을 출력해주는 부분

						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						win.change("panel03");

					} else if (!db_id.equals(id.getText()) || !db_pwd.equals(pwd.getText())) { // 아이디나 비밀번호가 다를경우 오류메세지
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
	JButton btn3 = new JButton(); // 회원가입 완료 버튼

	public JPanel02(JPanelTest win) {
		this.win = win;
		setLayout(null);
		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);

		ImageIcon img = new ImageIcon("icon_images/미운코딩새끼_청색.gif");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(100, 70, 700, 115);
		add(imageLabel);

		JLabel label_id = new JLabel("아이디");
		label_id.setForeground(Color.white);
		label_id.setBounds(225, 200, 70, 60);
		label_id.setFont(label_id.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(label_id);
		input_id.setBounds(225, 245, 450, 50);
		input_id.setFont(input_id.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		add(input_id);

		JLabel label_password = new JLabel("비밀번호");
		label_password.setForeground(Color.white);
		label_password.setBounds(225, 290, 70, 60);
		label_password.setFont(label_password.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(label_password);
		input_pwd.setBounds(225, 335, 450, 50);
		input_pwd.setFont(input_pwd.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		add(input_pwd);

		JLabel label_repassword = new JLabel("비밀번호 재확인");
		label_repassword.setForeground(Color.white);
		label_repassword.setBounds(225, 380, 150, 60);
		label_repassword.setFont(label_repassword.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(label_repassword);
		input_pwd_re.setBounds(225, 425, 450, 50);
		input_pwd_re.setFont(input_pwd_re.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		add(input_pwd_re);

		JLabel label_email = new JLabel("이메일");
		label_email.setForeground(Color.white);
		label_email.setBounds(225, 470, 150, 60);
		label_email.setFont(label_email.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(label_email);
		input_email.setBounds(225, 515, 450, 50);
		input_email.setFont(input_email.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		add(input_email);

		JLabel label_uesrname = new JLabel("사용자이름");
		label_uesrname.setForeground(Color.white);
		label_uesrname.setBounds(225, 560, 150, 60);
		label_uesrname.setFont(label_uesrname.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(label_uesrname);
		input_name.setBounds(225, 605, 450, 50);
		input_name.setFont(input_name.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		add(input_name);

		over = new JButton("중복확인");
		over.setForeground(new Color(000, 255, 204));
		over.setSize(150, 60);
		over.setLocation(660, 245);
		over.setBorderPainted(false); // 테두리 제거
		over.setContentAreaFilled(false); // 배경제거
		over.setFocusPainted(false); // 버튼 포커스 제거
		over.setFont(over.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(over);

		btn3 = new JButton("가입하기");
		btn3.setSize(450, 70);
		btn3.setLocation(225, 700);
		btn3.setBorder(bevel);
		btn3.setBackground(new Color(000, 255, 204));
		btn3.setFocusPainted(false); // 버튼 포커스 제거
		btn3.setForeground(Color.white);
		btn3.setFont(btn3.getFont().deriveFont(25.0f)); // 폰트 크기 조절
		add(btn3);

		jButton2 = new JButton("로그인");
		jButton2.setForeground(Color.white);
		jButton2.setSize(90, 50);
		jButton2.setLocation(405, 780);
		jButton2.setBorderPainted(false); // 테두리 제거
		jButton2.setContentAreaFilled(false); // 배경제거
		jButton2.setFocusPainted(false); // 버튼 포커스 제거
		jButton2.setFont(jButton2.getFont().deriveFont(17.0f)); // 폰트 크기 조절
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

	class Over implements ActionListener {
		int result = 3; // 아이디 중복 체크

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource(); // 버튼을 눌렀을 때 발생되는 소스를 받아와 저장하는 변수
			String sql = null;

			// TODO Auto-generated method stub
			if ((JButton) obj == over) { // 중복확인 버튼을 눌렀을때
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
							"12345");
					String qu = "select * from member_info";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu);

					while (rs.next()) {
						if (input_id.getText().equals("")) { // 공백일 때
							result = 1;
						} else if (rs.getString("id").equals(input_id.getText())) { // 아이디가 중복일때
							// System.out.println(input_id.getText().length());
							result = 0;
							break;
						} else if (input_id.getText().length() > 10) {
							result = 3;
						} else if (input_pwd.getText().length() > 10) {
							result = 4;
						} else { // 사용 가능한 아이디 일 경우
							result = 2;
							// System.out.println(login);
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
			} else if (result == 3) {
				login = false;
				JOptionPane.showMessageDialog(null, "아이디는 최대 10자 입니다.");
			} else if (result == 4) {
				login = false;
				JOptionPane.showMessageDialog(null, "비밀번호는 최대 10자 입니다.");
			} else if (result == 2) {
				login = true;
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
			}
			System.out.println(login);
		}

	}

	class Ok implements ActionListener { // 버튼 키 눌리면 '회원가입 완료' 문구와 함께 창이 새롭게 하나 뜬 후 회원가입 완료

		@Override
		public void actionPerformed(ActionEvent e) {

			DTO dto = new DTO(); // 회원가입시 입력한 정보를 임시저장 하는 클래스
			DAO dao = new DAO(); // 회원가입시 입력한 정보를 전달받아 쿼리 명령문을 내리는 클래스

			Object obj = e.getSource();

			if ((JButton) obj == btn3) { // 회원가입 완료 눌렀을 때 입력된 정보를 DTO클래스에 넘겨주는 부분
				dto.setid(input_id.getText());
				dto.setpwd(input_pwd.getText());
				dto.setpwd_re(input_pwd_re.getText());
				dto.setemail(input_email.getText());
				dto.setname(input_name.getText());
				// System.out.println(dto.getid()+"불러온 아이디 값");

				String db_id2 = null; // 데이터베이스에서 mmeber_info 테이블에 접속해 저장된 아이디 값을 저장해주는 변수
				String db_pwd2 = null;
				String db_name2 = null;
				String db_email2 = null;
				String db_id_num2 = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
							"12345");

					String qu = "select * from member_info"; // 로그인된 정보를 바탕으로 데이터베이스
																// 내에서 회원 정보를 알맞게
																// 불러오는 쿼리문
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu); // 쿼리문을 실행시켜주는 객체

					while (rs.next()) { // 데이터 베이스에서 불러온 정보를 변수에 저장해주는 부분
						db_id_num2 = rs.getString("id_num");
						db_id2 = rs.getString("id");
						db_pwd2 = rs.getString("pwd");
						db_name2 = rs.getString("name");
						db_email2 = rs.getString("email");

					}

					if (db_id_num2.equals(null) && db_id2.contentEquals(null) && db_name2.equals(null)
							&& db_email2.equals(null)) {
						dto.setid(input_id.getText());
						dto.setpwd(input_pwd.getText());
						dto.setpwd_re(input_pwd_re.getText());
						dto.setemail(input_email.getText());
						dto.setname(input_name.getText());
						JOptionPane.showMessageDialog(null, "회원가입 완료");

					}
					st.close();
				} catch (Exception e2) {
					System.err.println("오류!");
					System.out.println(e2.getMessage());
				}

				if (input_pwd.getText().equals(input_pwd_re.getText())) { // 입력되지 않은 사항이 있으면 오류 출력, 전부다 입력했으면 회원가입 완료
																			// 메세지, 전부 입력하지 않으면 오류메세지 출력
					if (dto.id.equals("") || dto.pwd.equals("") || dto.email.equals("") || dto.name.equals("")) {
						JOptionPane.showMessageDialog(null, "입력되지 않은 사항이 있습니다.");
					} else if (input_pwd.getText().length() > 10 || input_pwd_re.getText().length() > 10) {
						login = false;
						JOptionPane.showMessageDialog(null, "비밀번호는 10글자를 초과할수 없습니다.");
					} else {
						if (login == true) {
							JOptionPane.showMessageDialog(null, "회원가입 완료"); // 회원가입 알람 창이 나타남
							BufferedWriter bw = null; // 회원가입 완료시 회원별 최근 검색어를 저장하기위해 recent_record txt파일에 회원 정보를 저장하는 부분

							try {
								bw = new BufferedWriter(new FileWriter(
										"C:/Users/nyj/Desktop/포트폴리오 자료/프로젝트 자료/AI음성인식 프로그램/recent_record.txt", true)); // 텍스트파일
																														// 경로설정
							} catch (IOException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							PrintWriter pw = new PrintWriter(bw, true); // 텍스트 파일에 실질적으로 값을 입력해주는 객체
							try { // 텍스트 파일에 값을 입력해주는부분

								pw.write(input_id.getText() + ",");
								// System.out.println(input_id.getText());
								pw.write(input_name.getText() + "," + "검색어 : \n");
								pw.close();

							} catch (Exception e2) {
								e2.getStackTrace();
								// TODO: handle exception
							} finally {
								try {
									bw.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} // 반드시 닫는다.
							}

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

						} else {
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

class JPanel03 extends JPanel { // 로그인 후 화면
	static JLabel label_user; // 패널1에서 로그인시 불러온 회원 이름 정보를 저장해주는 라벨

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
	LineBorder la, lb;
	JTextField search = new JTextField();// 검색 창
	String var_1 = "";

	public JPanel03(JPanelTest win) {

		this.win = win;
		this.Text_field = Text_field; // 검색어를 입력받는 텍스트필드
		setLayout(null);

		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
		LineBorder b2 = new LineBorder(new Color(102, 204, 102), 3);

		ImageIcon img = new ImageIcon("icon_images/미운코딩새끼_청색.gif");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(90, 80, 700, 115);
		add(imageLabel);

		jButton1 = new JButton(new ImageIcon("icon_images/66.gif"));
		jButton1.setSize(700, 180);
		jButton1.setLocation(90, 640);
		lb = new LineBorder(Color.black, 0);
		// jButton1.setBorder(lb);
		jButton1.setBackground(Color.black);
		// jButton1.setFocusPainted(false); // 버튼 포커스 제거
		add(jButton1);

		ch1 = new Choice(); // 사용할 사이트
		ch1.addItem("사용할 사이트");
		ch1.addItem("naver");
		ch1.addItem("google");
		ch1.addItem("daum");
		ch1.setBounds(20, 20, 109, 35);
		add(ch1);

		ch2 = new Choice(); // 최근 검색어
		ch2.addItem("추천 검색어");
		ch2.setBounds(159, 20, 109, 35);
		add(ch2);

		label_user = new JLabel(in_id_num); // 로그인한 사용자 id출력하게 해야됨(id 최대 길이를 정해둬야됨
		label_user.setForeground(Color.white);
		label_user.setBounds(650, 8, 100, 60);
		label_user.setFont(label_user.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		add(label_user);

		jButton2 = new JButton("로그아웃"); // 로그아웃
		jButton2.setForeground(Color.white);
		jButton2.setSize(110, 40);
		jButton2.setLocation(750, 20);
		jButton2.setContentAreaFilled(false); // 배경제거
		jButton2.setFont(jButton2.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(jButton2);

		JScrollPane pane = new JScrollPane(Text_area); // JTextArea area = new JTextArea("검색어"); //검색어
		pane.setSize(700, 350);
		pane.setLocation(90, 270);

		Text_area.setEditable(false); // JTextArea 안에 텍스트 입력 금지
		Text_area.setLineWrap(true); // 행 넘기기 기능 켜기
		Text_area.setFont(Text_area.getFont().deriveFont(20.0f)); // 폰트 크기 조절
		Border lineBorder = BorderFactory.createLineBorder(new Color(0, 0, 0), 3); // TextArea의 테두리선의 색을 검정
		// 두깨를
		// 3px로 설정합니다.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서
		// emptyBorder를 생성합니다.
		add(pane);

		jButton4 = new JButton("Clear"); // Text_area 초기화
		jButton4.setForeground(Color.white);
		jButton4.setSize(100, 30);
		jButton4.setLocation(780, 595);
		jButton4.setFocusPainted(false); // 포커스 제거
		jButton4.setBorderPainted(false); // 테두리 제거
		jButton4.setContentAreaFilled(false); // 배경제거
		jButton4.setFont(jButton4.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(jButton4);

		Text_field.setBounds(90, 210, 700, 50); // 검색 창
		Text_field.setFont(Text_field.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(Text_field);

		jButton3 = new JButton("검색"); // 검색 버튼
		jButton3.setSize(58, 50);
		jButton3.setLocation(805, 210);
		jButton3.setBorder(b2);
		jButton3.setContentAreaFilled(false); // 배경제거
		jButton3.setForeground(Color.white);
		jButton3.setFont(jButton3.getFont().deriveFont(17.0f)); // 폰트 크기 조절
		add(jButton3);

		jButton1.addActionListener(new Play()); // 버튼 클시 음성 시작
		jButton2.addActionListener(new Home()); // 로그아웃 버튼 누를시 로그인 창 출력
		jButton3.addActionListener(new search()); // 검색 버튼 누를시 아래에 출력
		Text_field.addActionListener(new search()); // Enter 눌렀을 시 아래에 출력
		jButton4.addActionListener(new Clear());
	}

	class site implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	class Clear implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Text_area.setText("");
			Text_field.requestFocus();
		}
	}

	class Play implements ActionListener {

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
					bs = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/ID.txt")); // id저장
					bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/PW.txt")); // pw저장
					bs2 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/음성AI/자동로그인/state.txt")); // 자동
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

	class search implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj4 = e.getSource();
			if (obj4 == Text_field || (JButton) obj4 == jButton3) { // Enter or 검색 버튼 누를 시 검색 기능
				if (Text_field.getText().equals("")) {
					Text_area.append("검색어를 입력해주세요." + newline);
					Text_field.requestFocus();
				} else {
					String str = Text_field.getText(); // 돋보기 버튼 누를시 텍스트 필드의 값을 불러옴

					ArrayList<String> ar = new ArrayList<String>(); // 검색어를 저장할 arrayList
					String[] arr = new String[3]; // 사용자id,name, "검색어 : "를 저장하는 배열
					String originalString = "";
					String replaceString = "";
					Log_DTO ldto2 = new Log_DTO();
					Object obj2 = e.getSource();
					int count = 0;

					ldto2.setid_num(JPanel03.in_id_num); // 로그인시 입력된 정보를 바탕으로 데이터베이스에서 사용자 정보를 불러오는 부분
					ldto2.setlog_id(JPanel03.in_id);
					ldto2.setlog_pwd(JPanel03.in_pwd);
					ldto2.setlog_email(JPanel03.in_email);
					ldto2.setlog_name(JPanel03.in_name);

					String fileName = "C:/Users/nyj/Desktop/포트폴리오 자료/프로젝트 자료/AI음성인식 프로그램/recent_record.txt"; // 원본파일경로
					// file 객체 생성
					File inputFile = new File(fileName); // 입력하는 파일
					File outputFile = new File(fileName + ".temp"); // 출력하는 파일(임시저장)
					FileInputStream fileinputStream = null;
					BufferedReader bufferedReader = null;
					FileOutputStream fileOutputStream = null;
					BufferedWriter bufferedWriter = null;
					boolean result = false;
					try {
						// FileInputStream,FileOutputStream,BufferedReader,BufferedWriter 생성
						fileinputStream = new FileInputStream(inputFile);
						fileOutputStream = new FileOutputStream(outputFile);
						bufferedReader = new BufferedReader(new InputStreamReader(fileinputStream));
						bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream)); // 원본 파일에서 읽어 들이는
																										// 한라인
						String line; // 패턴에 일치하는 문자로 대체하고 난 후의 string
						String repLine; // 바꾸고자 하는 string과 바꿀 string 정의

						try {
							// 파일 객체 생성
							File file = new File("C:/Users/nyj/Desktop/포트폴리오 자료/프로젝트 자료/AI음성인식 프로그램/recent_record.txt");
							// 입력 스트림 생성
							FileReader filereader = new FileReader(file);
							// 입력 버퍼 생성
							BufferedReader bufReader = new BufferedReader(filereader);
							String line_1 = "";

							ArrayList<String> bufRead = new ArrayList<String>(); // 텍스트 파일의 라인을 하나씩 넣어줌
							// HashMap<String, String> mValue = new HashMap<String, String>(); //
							// System.out.println(label_user.getText()+ "라벨유저");
							while ((line_1 = bufReader.readLine()) != null) { // 저장할 검색어의 라인위치를 찾아주는 부분
								bufRead.add(line_1);
								arr = line_1.split(",");

								if (label_user.getText().equals(arr[0])) {
									originalString = line_1;
									// System.out.println("count = " + count);
									// System.out.println(arr[0]);
									break;
								}

								count++;
							}
							bufRead.get(0);
							bufReader.close();
						} catch (FileNotFoundException e5) {
							// TODO: handle exception
						} catch (IOException e5) {
							System.out.println(e);
						}

						ar.add(str + "/");

						// System.out.println("입력 ar = "+ar.get(0));
						// System.out.println(ar.size());
						for (int i = 0; i < ar.size(); i++) {
							// System.out.println("출력 ar = " + ar.get(i) + "검색어");
							replaceString = ar.get(i);
						}
						// System.out.println(originalString);
						String temp = "";
						while ((line = bufferedReader.readLine()) != null) {// 원본 파일에서 한라인씩 읽는다.
							// System.out.println(originalString + "오리지널 스트링");
							if (originalString.equals(line)) {
								temp = temp + line + replaceString + "\n";
							} else {
								temp = temp + line + "\n";
								// System.out.println("검색어 안들어감");
							}
							// System.out.println(line + "while문의 라인");
						}
						bufferedWriter.write(temp);// 새로운 파일에 쓴다.
						// System.out.println(temp + " 써주는 곳");

						result = true; // 정상적으로 수행되었음을 알리는 flag
					} catch (IOException ex) {
						ex.printStackTrace();
					} finally {
						try {
							bufferedReader.close();
						} catch (IOException ex1) {
							ex1.printStackTrace();
						}
						try {
							bufferedWriter.close();
						} catch (IOException ex2) {
							ex2.printStackTrace();
						}
						if (result) { // 정상적으로 수행되었을 경우 원본 파일을 지우고 새로운 파일명을 원본 파일명으로 rename
							inputFile.delete();
							outputFile.renameTo(new File(fileName));
						}
					}

					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					// 검색어 저장부분

					if (ch1.getSelectedItem().equals("naver")) { // 네이버를 선택하고 검색버튼을 누를경우 site_search 테이블에 접속해 네이버 카운트를 1
																	// 증가시킨다.

						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
									"12345");
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
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
									"12345");
							String qu3 = "update site_search set daum = daum+1 where id_num=" + "'" + JPanel03.in_id_num
									+ "'";
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
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
									"12345");
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

					Text_area.append(str + newline); // 텍스트area에 텍스트 필드의 값과 + '\n'을 합쳐서 엔터기능 만듬
					Text_field.requestFocus(); // 검색 버튼 누른 후 텍스트 포커스가 다시 Text_field에 위치한다.
					Text_field.setText(""); // 검색 후 검색 칸이 빈칸으로 초기화 된다.

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