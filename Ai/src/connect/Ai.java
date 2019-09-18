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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.border.LineBorder;

public class Ai extends JFrame {
	Container pane = getContentPane();// 컨텐트팬 알아내기

	public static void main(String[] args) {

		JPanelTest win = new JPanelTest();

		win.setTitle("frame test");
		win.jpanel01 = new JPanel01(win);
		win.jpanel02 = new JPanel02(win);
		win.jpanel03 = new JPanel03(win);

		win.jpanel01.setBackground(new Color(244, 244, 244)); // jpanel01 뒷 배경색
		win.jpanel02.setBackground(new Color(244, 244, 244)); // jpanel02 뒷 배경색
		//win.jpanel03.setBackground(new Color(244, 244, 244)); // jpanel03 뒷 배경색

		win.add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		win.setSize(500, 450);
		// win.setBackground(Color.black);

		win.setVisible(true);
		win.setLocationRelativeTo(null); // 창화면이 센터에 나오게함
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // 메인 홈 화면
	// private JTextField textField;
	private JPanelTest win;
	JTextField id = new JTextField();
	JPasswordField password = new JPasswordField();
	Color pwd;

	public JPanel01(JPanelTest win) {
		setLayout(null);
		this.win = win;

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
		id = new JTextField("아이디");
		id.setForeground(new Color(150, 150, 150));
		id.setBounds(120, 116, 260, 35);
		id.addFocusListener(new myFocusListener_id());
		// id.setBorder(b2);
		// id.setFocusable(false);
		// id.setColumns(5);
		add(id);

		password = new JPasswordField("비밀번호");
		// password.setForeground(Color.RED);
		password.setBounds(120, 165, 260, 35);
		password.setForeground(new Color(150, 150, 150));
		password.addFocusListener(new myFocusListener_password());
		// password.setBorder(b2);
		add(password);

		// ImageIcon normalicon = new ImageIcon("icon_images/로그인.png");
		JButton btn = new JButton("로그인");
		btn.setBackground(new Color(102, 204, 102));
		btn.setBorder(bevel);
//		btn.setOpaque(false);	//배경 없애고 테두리하고 클릭시 효과
//		btn.setBorderPainted(false);	//테두리 제거
//		btn.setContentAreaFilled(false);	//배경제거

		btn.setSize(260, 47);
		btn.setLocation(120, 214);
		btn.setFocusPainted(false);
		add(btn);

		JCheckBox check = new JCheckBox("로그인 상태 유지", false);
		check.setSize(115, 20);
		check.setLocation(120, 273);
		check.setBackground(new Color(244, 244, 244));
		check.setFocusPainted(false); // 버튼 포커스 제거
		add(check);

		// ImageIcon normalicon1 = new ImageIcon("icon_images/회원가입.png");
		JButton btn2 = new JButton("회원가입");
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // 테두리 제거
		btn2.setContentAreaFilled(false); // 배경제거
		btn2.setFocusPainted(false); // 버튼 포커스 제거

		btn2.setSize(70, 20);
		btn2.setLocation(310, 273);
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
			if (password.getText().equals("비밀번호")) { // 텍스트 필드에 "비밀번호"문구가 있을경우 빈칸으로 초기화
				password.setForeground(new Color(0, 0, 0));
				password.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // 포커스를 잃어버릴 때
			if (password.getText().equals("")) {
				password.setForeground(new Color(150, 150, 150));
				password.setText("비밀번호");
			}
		}
	}

	class Login implements ActionListener { // 버튼 키 눌리면 패널 3번(로그인 후 화면) 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel03");
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
		// this.input_pwd = input_pwd;
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
		// btn3.setBackground(Color.green);
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

	class Over implements ActionListener {
		int result = 3; // 아이디 중복 체크

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
//			DTO dto = new DTO(); // 회원가입
//			DAO dao = new DAO();
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
						if (input_id.getText().equals("")) { // 공백일 때
							result = 1;
						} else if (rs.getString("id").equals(input_id.getText())) { // 아이디가 중복일때
							result = 0;
							break;
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
			} else if (result == 2) {
				login = true;
				// System.out.println(login);
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
			}
			// login++;
			// System.out.println("login:"+login);
			System.out.println(login);
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
						if (login == true) {
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
						} else {
							// System.out.println(login);
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
	private JButton jButton1, jButton2, jButton3, jButton4;
	private JTextField textField;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanelTest win;
	private final static String newline = "\n";

	private JTextField Text_field = new JTextField();
	private JTextArea Text_area = new JTextArea();
	private Choice ch1, ch2;

	String var_1 = "";

	public JPanel03(JPanelTest win) {
		this.win = win;
		this.Text_field = Text_field;
		setLayout(null);

		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
		LineBorder b2 = new LineBorder(new Color(102, 204, 102), 3);

		ImageIcon play = new ImageIcon("icon_images/초록음성3.jpg");
		jButton1 = new JButton(play);
		// ImageIcon stop = new ImageIcon("icon_images/음성진행중.png");
		// jButton4 = new JButton(play);
		// jButton1.setBorder(b2);
		jButton1.setSize(300, 100);
		jButton1.setLocation(100, 300);
		jButton1.setBackground(new Color(244, 244, 244));

		// jButton1.setOpaque(false); //배경 없애고 테두리하고 클릭시 효과
		// jButton1.setContentAreaFilled(false); //배경제거
		// jButton1.setFocusPainted(false); //버튼 포커스 제거
		// jButton1.setBorderPainted(false); // 테두리 제거
		// jButton1.setBorder(bevel); //입체적 효과

//		btn.setBorder(bevel);
//		btn.setOpaque(false);	//배경 없애고 테두리하고 클릭시 효과
//		btn.setBorderPainted(false);	//테두리 제거
//		btn.setContentAreaFilled(false);	//배경제거

		add(jButton1);

		ch1 = new Choice(); // 사용할 사이트
		ch1.addItem("사용할 사이트");
		ch1.addItem("www.naver.com");
		ch1.addItem("www.google.com");
		ch1.addItem("www.daum.net");
		ch1.setBounds(20, 20, 109, 35);
		add(ch1);

		ch2 = new Choice(); // 최근 검색어
		ch2.addItem("최근 검색어");
		ch2.setBounds(159, 20, 109, 35);
		add(ch2);

//		textField = new JTextField("사용할 사이트"); // 사용할 사이트
//		textField.setBounds(20, 99, 109, 35);
//		add(textField);

		JLabel label_user = new JLabel("사용자이름"); // 로그인한 사용자 id출력하게 해야됨(id 최대 길이를 정해둬야됨
		label_user.setBounds(300, 8, 60, 50);
		add(label_user);

		jButton2 = new JButton("로그아웃"); // 로그아웃
		jButton2.setSize(82, 25);
		jButton2.setLocation(386, 20);
		// jButton2.setBackground(new Color(102, 204, 102));
		// jButton2.setBackground(Color.GRAY);
		jButton2.setContentAreaFilled(false); // 배경제거
		add(jButton2);

		Text_field.setBounds(100, 70, 300, 30); // 검색 창
		// Text_field.setBorder(b2);
		add(Text_field);
		JScrollPane pane = new JScrollPane(Text_area); // JTextArea area = new JTextArea("검색어"); //검색어
		// Text_area.setBackground(Color.DARK_GRAY); //영역 배경색 지정
		// Text_area.setFont(new Font("굴림체",Font.BOLD,13)); //폰트 지정
		// Text_area.setForeground(Color.WHITE); //글자색 지정
		// Text_area.setBackground(new Color(122, 224, 142));

		pane.setSize(300, 180);
		pane.setLocation(100, 105);

		Text_area.setEditable(false); // JTextArea 안에 텍스트 입력 금지
		Text_area.setLineWrap(true); // 행 넘기기 기능 켜기

		Border lineBorder = BorderFactory.createLineBorder(new Color(102, 204, 102), 3); // TextArea의 테두리선의 색을 검정
																							// 두깨를
																							// 3px로 설정합니다.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서
																			// emptyBorder를 생성합니다.
		// pane.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		// // TextArea에 lineBorder(검정테두리),
		// emptyBorder(여백)로 구성된 복합
		// 경계선을
		// 설정합니다.
		add(pane);

		jButton4 = new JButton("Clear");
		jButton4.setSize(70, 30);
		jButton4.setLocation(400, 260);
		jButton4.setFocusPainted(false); // 포커스 제거
		jButton4.setBorderPainted(false); // 테두리 제거
		jButton4.setContentAreaFilled(false); // 배경제거
		add(jButton4);

		// ImageIcon normalicon = new ImageIcon("icon_images/돋보기2.jpg");
		jButton3 = new JButton("검색");
		jButton3.setSize(58, 30);
		jButton3.setLocation(410, 70);
		jButton3.setBorder(b2);
		jButton3.setFocusPainted(false); // 버튼 포커스 제거
		// jButton3.setContentAreaFilled(false); // 배경제거
		jButton3.setBorderPainted(false); // 테두리 제거
		jButton3.setBackground(new Color(102, 204, 102));
		// jButton3.setBorder(bevel);
		add(jButton3);

		jButton1.addActionListener(new Play()); // 버튼 클시 음성 시작
		jButton2.addActionListener(new Home()); // 로그아웃 버튼 누를시 로그인 창 출력
		jButton3.addActionListener(new search()); // 검색 버튼 누를시 아래에 출력
		Text_field.addActionListener(new search()); // Enter 눌렀을 시 아래에 출력
		jButton4.addActionListener(new Clear());
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
				// System.out.println("눌림");
				// Text_area.setT
				Text_area.append("What can I help you with?" + newline);
				// jButton1.setImageIcon("icon_images/음성진행중.png");
				// jButton1.ch();
				// ImageIcon play = new ImageIcon("icon_images/음성진행중.png");
			}
		}
	}

	class Home implements ActionListener { // 버튼 키 눌리면 패널 1번(로그인 화면) 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			Text_area.setText("");
			win.change("panel01");
		}
	}

	class search implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Text_field || e.getSource() == jButton3) { // Enter or 검색 버튼 누를 시 검색 기능
				if (Text_field.getText().equals("")) {
					// Text_area.setHorizontalAlignment(Text_area.);
					// Text_area.setAlignment(Component.CENTER);
					Text_area.append("검색어를 입력해주세요." + newline);
					// Text_area.setForeground(Color.red); //글자색 지정
					Text_field.requestFocus();
				} else {
					String str = Text_field.getText(); // 돋보기 버튼 누를시 텍스트 필드의 값을 불러옴
					Text_area.append(str + newline); // 텍스트area에 텍스트 필드의 값과 + '\n'을 합쳐서 엔터기능 만듬
					// Text_field.selectAll(); //검색 후 검색 한 텍스트가 전체 드래그 된다.
					Text_field.requestFocus(); // 검색 버튼 누른 후 텍스트 포커스가 다시 Text_field에 위치한다.
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