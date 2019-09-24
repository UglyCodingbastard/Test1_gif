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
	Container pane = getContentPane();// ����Ʈ�� �˾Ƴ���

	public static void main(String[] args) {

		JPanelTest win = new JPanelTest(); // win JPanelTest����

		win.setTitle("frame test"); // Title name
		win.jpanel01 = new JPanel01(win); // JPanel01
		win.jpanel02 = new JPanel02(win); // JPanel02
		win.jpanel03 = new JPanel03(win); // JPanel03

		win.jpanel01.setBackground(new Color(244, 244, 244)); // jpanel01 �� ����
		win.jpanel02.setBackground(new Color(244, 244, 244)); // jpanel02 �� ����
		win.jpanel03.setBackground(new Color(244, 244, 244)); // jpanel03 �� ����

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
//				JPanel03.label_user.setText(JPanel03.in_name + "��"); // �г�1�� �����͸� �г�3���� �������� ����
//				
//				//win.add(win.jpanel03);
//				//System.out.println("true");
//				JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
//				win.change("panel03");
//
//			} else if (!db_id.equals(id_readBuffer) || !db_pwd.equals(pw_readBuffer)) {
//                  JOptionPane.showMessageDialog(null, "���̵� �н����尡 ��ġ���� �ʽ��ϴ�.");
//               }
//
//			st.close();
//		} catch (Exception e2) {
//			JOptionPane.showMessageDialog(null, "���̵� �н����尡 ��ġ���� �ʽ��ϴ�.");
//			System.err.println("����!");
//			// System.out.println(e2.getMessage());
//		}

		win.add(win.jpanel01); // ���α׷� ���۽� JPanel01 ����

		// win.add(win.jpanel01);
		// System.out.println("true");
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ������â ���� �� ���μ������� ����

		win.setSize(500, 450); // win ������
		// win.setBackground(Color.black);

		win.setVisible(true);
		win.setLocationRelativeTo(null); // âȭ���� ���Ϳ� ��������
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // ���� Ȩ ȭ��
	// private JTextField textField;
	private static JPanelTest win;

	static JButton btn = new JButton("�α���");
	static JTextField id = new JTextField("���̵�");
	static JCheckBox check; // check �ڵ� �α���
	static JPasswordField pwd = new JPasswordField("��й�ȣ");

	static String id_readBuffer = null; // ���� ID����
	static String pw_readBuffer = null; // ���� PW����
	static String state_readBuffer = null; // ���� state����

	public JPanel01(JPanelTest win) {
		setLayout(null);
		this.win = win;
		/**
		 * ���α׷��� ó�� ����Ǹ� ����ڰ� �ڵ��α��� �������� �ƴ����� �����ϱ� ���� state.txt ���Ͽ� ������� ���α׷� ���� ���� ���¸�
		 * �����Ѵ�. �ڵ��α��� ����� üũ �α��� �� �α׾ƿ� ���� �ʰ� ����� state.txt�� true ���� �ڵ��α��� ����� üũ �α��� ��
		 * �α׾ƿ����� ���α׷� ����� state.txt�� false ���� ���¿� ���� id.textfield�� pwd.textfield �� ����
		 * �ԷµǾ�����
		 **/
		// ���� ��ü ����
		Path path_id = Paths.get("C:/Users/User/Desktop/����AI/�ڵ��α���/ID.txt"); // ID.txt ���
		Path path_pw = Paths.get("C:/Users/User/Desktop/����AI/�ڵ��α���/PW.txt"); // PW.txt ���
		Path path_state = Paths.get("C:/Users/User/Desktop/����AI/�ڵ��α���/state.txt"); // state.txt ���

		// ĳ���ͼ� ����
		Charset cs = StandardCharsets.UTF_8;

		// ���� ������� ����Ʈ
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
			id_readBuffer = readLine_id; // ID.txt String���� �ҷ��� id_readBuffer ����
		}
		for (String readLine_pw : list_pw) {
			pw_readBuffer = readLine_pw; // PW.txt String���� �ҷ��� pw_readBuffer ����
		}
		for (String readLine_state : list_state) {
			state_readBuffer = readLine_state; // state.txt String���� �ҷ��� state_readBuffer ����
		}

		win.setBackground(new Color(102, 204, 102));
		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
		LineBorder b2 = new LineBorder(Color.darkGray, 3);

		JTextField trash = new JTextField(); // ù ��Ŀ�� ���ֱ� ���� trash �ؽ�Ʈ �ʵ�
		trash.setBounds(0, 0, 0, 0);
		add(trash);

		ImageIcon img = new ImageIcon("icon_images/�ΰ�.png");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(120, 60, 255, 35);
		add(imageLabel);

		if (state_readBuffer.equals("true")) { // state.txt ���°� true�� ��� ���̵�� ��й�ȣ �Էµ�����
			id = new JTextField(id_readBuffer);
			id.setForeground(new Color(0, 0, 0));

			pwd = new JPasswordField(pw_readBuffer);
			pwd.setForeground(new Color(0, 0, 0));

		} else if (state_readBuffer.equals("false")) { // state.txt ���°� false�� ��� ù �α��� ȭ�� ����
			id = new JTextField("���̵�");
			id.setForeground(new Color(150, 150, 150));

			pwd = new JPasswordField("��й�ȣ");
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
		add(btn); // �α��� ��ư ����

		if (state_readBuffer.equals("true")) { // true�� ��� check�ڽ� üũ
			check = new JCheckBox("�α��� ���� ����", true);
		} else if (state_readBuffer.equals("false")) { // false�� ��� check�ڽ� ����
			check = new JCheckBox("�α��� ���� ����", false);
		}
		check.setSize(115, 20);
		check.setLocation(120, 273);
		check.setBackground(new Color(244, 244, 244));
		check.setFocusPainted(false); // ��ư ��Ŀ�� ����
		add(check); // �α��� ���� ���� üũ �ڽ�

		JButton btn2 = new JButton("ȸ������");
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // �׵θ� ����
		btn2.setContentAreaFilled(false); // �������
		btn2.setFocusPainted(false); // ��ư ��Ŀ�� ����
		btn2.setSize(70, 20);
		btn2.setLocation(310, 273);
		add(btn2);

		btn.addActionListener(new Login()); // �α��� �׼�
		btn2.addActionListener(new Membership()); // ȸ������ �׼�
	}

	class myFocusListener_id extends FocusAdapter { // ���̵� �ؽ�Ʈ�ʵ� ��Ŀ�� �̺�Ʈ
		public void focusGained(FocusEvent e) {
			if (id.getText().equals("���̵�")) { // �ؽ�Ʈ �ʵ忡 "���̵�"������ ������� ��ĭ���� �ʱ�ȭ
				id.setForeground(new Color(0, 0, 0));
				id.setText(""); // ��Ŀ���� �������� �� �ؽ�Ʈ Ŭ���� ��ĭ���� �ʱ�ȭ
			}
		}

		public void focusLost(FocusEvent e) { // ��Ŀ���� �Ҿ���� ��
			if (id.getText().equals("")) { // id.textfield�� ��ĭ�� ���
				id.setForeground(new Color(150, 150, 150));
				id.setText("���̵�"); // ���̵� �ؽ�Ʈ ����
			}
		}
	}

	class myFocusListener_password extends FocusAdapter { // ��й�ȣ �ؽ�Ʈ�ʵ� ��Ŀ�� �̺�Ʈ

		public void focusGained(FocusEvent e) { // ��Ŀ���� �������� ��
			if (pwd.getText().equals("��й�ȣ")) { // �ؽ�Ʈ �ʵ忡 "��й�ȣ"������ ������� ��ĭ���� �ʱ�ȭ
				pwd.setForeground(new Color(0, 0, 0));
				pwd.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // ��Ŀ���� �Ҿ���� ��
			if (pwd.getText().equals("")) { // pwd.textfield�� ��ĭ�� ���
				pwd.setForeground(new Color(150, 150, 150));
				pwd.setText("��й�ȣ"); // ��й�ȣ �ؽ�Ʈ ����
			}
		}
	}

	static class Login implements ActionListener { // �α��� ��ư ���

		@Override
		public void actionPerformed(ActionEvent e) {

			Log_DTO ldto = new Log_DTO();
			Object obj3 = e.getSource();
			boolean index = check.isSelected(); // check ���� üũ

			String db_id = null;
			String db_pwd = null;
			String db_name = null;
			String db_email = null;
			String db_id_num = null;

			if ((JButton) obj3 == btn) { // �α��� ��ư ������ ��
				if (index == true) { // choice ���� Ŭ���� �������� �ҷ��´�. �ڵ� �α��� üũ ��� ��������

					BufferedOutputStream bs = null;
					BufferedOutputStream bs1 = null;
					BufferedOutputStream bs2 = null;

					try {
						bs = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/����AI/�ڵ��α���/ID.txt")); // id����
						bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/����AI/�ڵ��α���/PW.txt")); // pw����
						bs2 = new BufferedOutputStream(
								new FileOutputStream("C:/Users/User/Desktop/����AI/�ڵ��α���/state.txt")); // �ڵ� �α��� ��������

						String str = id.getText(); // id�� �޾ƿ�
						String str1 = pwd.getText();// pw�� �޾ƿ�

						if (index == true) { // �ڵ��α��� ���°� true�� ��� �ؽ�Ʈ ���Ͽ� true����
							String str2 = "true";
							bs2.write(str2.getBytes());
						}

						bs.write(str.getBytes()); // Byte�����θ� ���� �� ����
						bs1.write(str1.getBytes()); // Byte�����θ� ���� �� ����

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
						} // �ݵ�� �ݴ´�.
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
						JPanel03.label_user.setText(JPanel03.in_name + "��"); // �г�1�� �����͸� �г�3���� �������� ����

						JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
						id.setText("���̵�");
						id.setForeground(new Color(150, 150, 150));
						pwd.setText("��й�ȣ");
						pwd.setForeground(new Color(150, 150, 150));
						win.change("panel03");

					} else if (!db_id.equals(id.getText()) || !db_pwd.equals(pwd.getText())) {
						JOptionPane.showMessageDialog(null, "���̵� �н����尡 ��ġ���� �ʽ��ϴ�.");
					}

					st.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "���̵� �н����尡 ��ġ���� �ʽ��ϴ�.");
					System.err.println("����!");
					// System.out.println(e2.getMessage());
				}
			}
		}
	}

	class Membership implements ActionListener { // ��ư Ű ������ �г� 2��(ȸ������) ȣ��

		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel02");
		}
	}
}

class JPanel02 extends JPanel { // ȸ������ ȭ��
	private JButton jButton1, jButton2, jButton3;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;

	private JTextField textField;
	private JPasswordField passwordField;
	private JPanelTest win;

	private Connection con = null;
	private Statement stmt = null; // �����͸� �����ϴ� ��ü
	private ResultSet rs = null;
	private Boolean connect = false;
	Boolean login = false;

	JTextField input_id = new JTextField(10); // ���̵�
	JPasswordField input_pwd = new JPasswordField(10); // �н�����
	JPasswordField input_pwd_re = new JPasswordField(10); // �н����� ��Ȯ��
	JTextField input_email = new JTextField(30); // �̸���
	JTextField input_name = new JTextField(30); // ����� �̸�

	JButton over = new JButton(); // �ߺ�Ȯ�� ��ư
	JButton btn3 = new JButton();

	public JPanel02(JPanelTest win) {
		this.win = win;

		setLayout(null);
		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);

		JLabel label_id = new JLabel("���̵�");
		label_id.setBounds(120, 17, 50, 50);
		add(label_id);
		input_id.setBounds(120, 50, 260, 30);
		add(input_id);

		JLabel label_password = new JLabel("��й�ȣ");
		label_password.setBounds(120, 70, 50, 50);
		add(label_password);
		input_pwd.setBounds(120, 103, 260, 30);
		add(input_pwd);

		JLabel label_repassword = new JLabel("��й�ȣ ��Ȯ��");
		label_repassword.setBounds(120, 123, 100, 50);
		add(label_repassword);
		input_pwd_re.setBounds(120, 156, 260, 30);
		add(input_pwd_re);

		JLabel label_email = new JLabel("�̸���");
		label_email.setBounds(120, 176, 50, 50);
		add(label_email);
		input_email.setBounds(120, 209, 260, 30);
		add(input_email);

		JLabel label_uesrname = new JLabel("������̸�");
		label_uesrname.setBounds(120, 229, 70, 50);
		add(label_uesrname);
		input_name.setBounds(120, 262, 260, 30);
		add(input_name);

		over = new JButton("�ߺ�Ȯ��");
		over.setSize(82, 30);
		over.setLocation(385, 50);
		over.setBorderPainted(false); // �׵θ� ����
		over.setContentAreaFilled(false); // �������
		over.setFocusPainted(false); // ��ư ��Ŀ�� ����
		add(over);

		btn3 = new JButton("ȸ������ �Ϸ�");
		btn3.setSize(260, 35);
		btn3.setLocation(120, 321);
		btn3.setBorder(bevel);
		btn3.setBackground(new Color(102, 204, 102));
		btn3.setFocusPainted(false); // ��ư ��Ŀ�� ����
		add(btn3);

		jButton2 = new JButton("�α���");
		jButton2.setSize(70, 35);
		jButton2.setLocation(217, 370);
		jButton2.setBorderPainted(false); // �׵θ� ����
		jButton2.setContentAreaFilled(false); // �������
		jButton2.setFocusPainted(false); // ��ư ��Ŀ�� ����
		add(jButton2);

		jButton2.addActionListener(new Home()); // ��� ��ư ���� �� Ȩ ȭ������ �̵�
		btn3.addActionListener(new Ok()); // ȸ������ �Ϸ� ��ư ������ ȸ������ �Ϸ� ��� ������ ���� ���ο� â�� �ϳ��߸鼭 Ȩ���� �̵�
		over.addActionListener(new Over()); // ���̵� �ߺ�üũ
	}

	class Home implements ActionListener { // ��ư Ű ������ �г� 1��(�α��� ȭ��) ȣ��

		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}

	class Over implements ActionListener { // ���̵� �ߺ� üũ
		int result = 3; // ���̵� �ߺ� üũ 3���� �ص־� ���� �Ȼ���

		@Override
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();
			String sql = null;

			// TODO Auto-generated method stub
			if ((JButton) obj == over) { // �ߺ�Ȯ�� ��ư�� ��������
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root", "1234");
					String qu = "select * from member_info";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu);

					while (rs.next()) {
						if (input_id.getText().equals("")) { // ȸ������ ���̵� ĭ�� ������ ��
							result = 1;
						} else if (rs.getString("id").equals(input_id.getText())) { // ȸ������ ���̵� �ߺ��϶�
							result = 0;
							break;
						} else { // ��� ������ ���̵� �� ���
							result = 2;
						}
					}
					st.close();
				} catch (Exception e1) {
					System.err.println("����!");
					System.out.println(e1.getMessage());
				}
			}

			if (result == 0) {
				JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �Դϴ�.");
			} else if (result == 1) {
				JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���.");
			} else if (result == 2) {
				login = true;
				JOptionPane.showMessageDialog(null, "��� ������ ���̵� �Դϴ�.");
			}
		}
	}

	class Ok implements ActionListener { // ��ư Ű ������ 'ȸ������ �Ϸ�' ������ �Բ� â�� ���Ӱ� �ϳ� �� �� ȸ������ �Ϸ�

		@Override
		public void actionPerformed(ActionEvent e) {

			DTO dto = new DTO(); // ȸ������
			DAO dao = new DAO();

			Object obj = e.getSource();

			if ((JButton) obj == btn3) { // ȸ������ �Ϸ� ������ ��
				dto.setid(input_id.getText());
				dto.setpwd(input_pwd.getText());
				dto.setpwd_re(input_pwd_re.getText());
				dto.setemail(input_email.getText());
				dto.setname(input_name.getText());

				System.out.println("ȸ������" + login);

				if (input_pwd.getText().equals(input_pwd_re.getText())) {
					if (dto.id.equals("") || dto.pwd.equals("") || dto.email.equals("") || dto.name.equals("")) {
						JOptionPane.showMessageDialog(null, "�Էµ��� ���� ������ �ֽ��ϴ�.");
					} else {
						if (login == true) { // ȸ������ �Ϸ��ҽ� ���̵� �ߺ�üũ ���θ� Ȯ���ϱ� ����(��밡���� ���̵��� ��쿡�� �ش�)
							JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�"); // ȸ������ �˶� â�� ��Ÿ��

							input_id.setText(""); // ȸ������ �� �Է� ĭ �ʱ�ȭ
							input_pwd.setText("");
							input_pwd_re.setText("");
							input_email.setText("");
							input_name.setText("");
							win.change("panel01");
							login = false;
							try {
								DAO.create(dto); // dto�� DAO�� �Ѱ��ش�.
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else { // ���̵� �ߺ�üũ ���θ� Ȯ�� ���� ���
							JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ�� ���ּ���."); // ȸ������ �˶� â�� ��Ÿ��
						}
					}
				} else if (input_pwd.getText() != input_pwd_re.getText()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��Ȯ�� ���ּ���.");
					win.change("panel02");
				}
			}
		}
	}
}

class JPanel03 extends JPanel { // �α��� �� ����ڰ� ���񽺸� �̿��� ȭ��
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

	JTextField search = new JTextField();// �˻� â
	String var_1 = "";

	public JPanel03(JPanelTest win) {
		this.win = win;
		this.Text_field = Text_field;

		setLayout(null);

		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
		LineBorder b2 = new LineBorder(new Color(102, 204, 102), 3);

		ImageIcon play = new ImageIcon("icon_images/�ʷ�����3.jpg"); // �����ν� ���� ��ư
		jButton1 = new JButton(play);
		jButton1.setSize(300, 100);
		jButton1.setLocation(100, 300);
		jButton1.setBackground(new Color(244, 244, 244));
		add(jButton1);

		ch1 = new Choice(); // ����� ����Ʈ
		ch1.addItem("����� ����Ʈ");
		ch1.addItem("naver");
		ch1.addItem("google");
		ch1.addItem("daum");
		ch1.setBounds(20, 20, 109, 35);
		add(ch1);

		ch2 = new Choice(); // ��õ �˻���
		ch2.addItem("��õ �˻���");
		ch2.setBounds(159, 20, 109, 35);
		add(ch2);

		label_user = new JLabel(in_id_num); // �α����� ����� id����ϰ� �ؾߵ�(id �ִ� ���̸� ���ص־ߵ�
		label_user.setBounds(300, 8, 60, 50);
		add(label_user);

		jButton2 = new JButton("�α׾ƿ�"); // �α׾ƿ�
		jButton2.setSize(82, 25);
		jButton2.setLocation(386, 20);
		jButton2.setContentAreaFilled(false); // �������
		add(jButton2);

		Text_field.setBounds(100, 70, 300, 30); // �˻� â
		add(Text_field);

		JScrollPane pane = new JScrollPane(Text_area); // JTextArea area = new JTextArea("�˻���"); //�˻���
		pane.setSize(300, 180);
		pane.setLocation(100, 105);

		Text_area.setEditable(false); // JTextArea �ȿ� �ؽ�Ʈ �Է� ����
		Text_area.setLineWrap(true); // �� �ѱ�� ��� �ѱ�
		Border lineBorder = BorderFactory.createLineBorder(new Color(102, 204, 102), 3); // TextArea�� �׵θ����� ���� ����
																							// �α���
																							// 3px�� �����մϴ�.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // �ؽ�Ʈ�� TextArea ��� ���̿� ������ �α� ���ؼ�
																			// emptyBorder�� �����մϴ�.

		add(pane);

		jButton4 = new JButton("Clear"); // Text_area �ʱ�ȭ
		jButton4.setSize(70, 30);
		jButton4.setLocation(400, 260);
		jButton4.setFocusPainted(false); // ��Ŀ�� ����
		jButton4.setBorderPainted(false); // �׵θ� ����
		jButton4.setContentAreaFilled(false); // �������
		add(jButton4);

		jButton3 = new JButton("�˻�"); // �˻� ��ư
		jButton3.setSize(58, 30);
		jButton3.setLocation(410, 70);
		jButton3.setBorder(b2);
		jButton3.setFocusPainted(false); // ��ư ��Ŀ�� ����
		jButton3.setBorderPainted(false); // �׵θ� ����
		jButton3.setBackground(new Color(102, 204, 102));
		add(jButton3);

		jButton1.addActionListener(new Play()); // ��ư Ŭ�� ���� ����
		jButton2.addActionListener(new Home()); // �α׾ƿ� ��ư ������ �α��� â ���
		jButton3.addActionListener(new site()); // �˻� ��ư ������ �Ʒ��� ���
		Text_field.addActionListener(new search()); // Enter ������ �� �Ʒ��� ���
		jButton4.addActionListener(new Clear());
	}

	class site implements ActionListener { // �˻� ��ư ������ �˻� ��� ����

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Log_DTO ldto2 = new Log_DTO();
			Object obj2 = e.getSource();

			if ((JButton) obj2 == jButton3) { // �˻� ��ư ����

				if (Text_field.getText().equals("")) { // ����ڰ� �˻�� �ƹ��͵� �Է����� �������
					if (ch1.getSelectedItem().equals("����� ����Ʈ")) { // ó�� ��ϵ� ����� ����Ʈ�� �������� ���� ���
						Text_area.setForeground(Color.red); // ���ڻ� ����
						Text_area.append("�˻���� ����Ʈ�� �Է����ּ���." + newline); // �˻���� ����Ʈ�� �Է� ���� ���
						Text_field.requestFocus();
					} else {
						ldto2.setid_num(JPanel03.in_id_num);
						ldto2.setlog_id(JPanel03.in_id);
						ldto2.setlog_pwd(JPanel03.in_pwd);
						ldto2.setlog_email(JPanel03.in_email);
						ldto2.setlog_name(JPanel03.in_name);

						if (ch1.getSelectedItem().equals("naver")) { // ����� ����Ʈ�� naver�� ������ ���

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
								System.err.println("����!");
								System.out.println(e1.getMessage());
							}

						} else if (ch1.getSelectedItem().equals("daum")) { // ����� ����Ʈ�� daum�� ������ ���
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
								System.err.println("����!");
								System.out.println(e1.getMessage());
							}
						} else if (ch1.getSelectedItem().equals("google")) { // ����� ����Ʈ�� google�� ������ ���
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
								System.err.println("����!");
								System.out.println(e1.getMessage());
							}
						}
						String index = ch1.getSelectedItem(); // choice ���� Ŭ���� �������� �ҷ��´�.
						Runtime runtime = Runtime.getRuntime();
						if (index.equals("daum")) {
							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".net/"); // ������ ���л���Ʈ�� Ȩ�� �߰� �Ѵ�.
							} catch (IOException ex) {

							}
						} else {

							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".com/"); // ������ ���л���Ʈ�� Ȩ�� �߰� �Ѵ�.
							} catch (IOException ex) {

							}
						}
					}
				} else {
					String str = Text_field.getText(); // �˻� ��ư ������ �ؽ�Ʈ �ʵ��� ���� �ҷ���
					String name1 = str; // String name1�� str�� �����Ѵ�.
					for (int i = 0; i < name1.length(); i++) {

						name1 = name1.replace("��", ""); // �⺻ �˰����� ���� ��,��,���� ���� ����
						name1 = name1.replace("��", "");
						name1 = name1.replace("����", "");

						name1 = name1.replace("���̹�", // ���̹��� �ܾ �߰����� �� �Ʒ��� ���� url�� ��ȯ
								"https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=");

						name1 = name1.replace("�˻�", "&oquery"); // �˻� �ܾ �߽߰� �˻�������� ��ȯ
					}

					System.out.println(name1); // ������ �ּ� + Ű���� + ����
					Runtime runtime = Runtime.getRuntime();

					try {
						runtime.exec("C:/Program Files/internet explorer/iexplore.exe " + name1); // �⺻ �ͽ��÷ξ� �ڿ� ������
																									// url�� �Է��Ѵ�.
					} catch (IOException ex) {

					}
					Text_area.append(str + newline); // �ؽ�Ʈarea�� �ؽ�Ʈ �ʵ��� ���� + '\n'�� ���ļ� ���ͱ�� ����
					Text_field.requestFocus(); // �˻� ��ư ���� �� �ؽ�Ʈ ��Ŀ���� �ٽ� Text_field�� ��ġ�Ѵ�.
					Text_field.setText(""); // �˻� �� �˻� ĭ�� ��ĭ���� �ʱ�ȭ �õ�.

				}
			}
		}
	}

	class Clear implements ActionListener { // clear ��ư

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Text_area.setText(""); // Text_area ȭ���� ��ü �ʱ�ȭ
			Text_field.requestFocus();
		}
	}

	class Play implements ActionListener { // �����ν� ��ư

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == jButton1) {
				Text_area.append("What can I help you with?" + newline);
			}
		}
	}

	class Home implements ActionListener { // �α׾ƿ� ���
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton2) { // �α׾ƿ� ��ư Ŭ���� Ȩ ȭ������ �̵� �� ��� ���� �ʱ�ȭ

				BufferedOutputStream bs = null;
				BufferedOutputStream bs1 = null;
				BufferedOutputStream bs2 = null;
				try {
					bs = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/����AI/�ڵ��α���/ID.txt")); // id����
					bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/����AI/�ڵ��α���/PW.txt")); // pw����
					bs2 = new BufferedOutputStream(new FileOutputStream("C:/Users/User/Desktop/����AI/�ڵ��α���/state.txt")); // �ڵ�
																														// �α���
																														// ��������

					String str = ""; // id�� �޾ƿ�
					String str1 = "";// pw�� �޾ƿ�
					String str2 = "false";

					bs.write(str.getBytes()); // Byte�����θ� ���� �� ����
					bs1.write(str1.getBytes()); // Byte�����θ� ���� �� ����
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
					} // �ݵ�� �ݴ´�.
				}
				Text_area.setText("");
				win.change("panel01");
			}
		}
	}

	class search implements ActionListener { // �˻� �ؽ�Ʈ �ʵ忡�� ���͸� �Է����� ���

		@Override
		public void actionPerformed(ActionEvent e) {

			Log_DTO ldto2 = new Log_DTO();
			Object obj2 = e.getSource();

			if (e.getSource() == Text_field || e.getSource() == jButton3) { // Enter or �˻� ��ư ���� �� �˻� ���

				if (Text_field.getText().equals("")) { // ����ڰ� �˻�� �ƹ��͵� �Է����� �������
					if (ch1.getSelectedItem().equals("����� ����Ʈ")) {
						Text_area.setForeground(Color.red); // ���ڻ� ����
						Text_area.append("�˻���� ����Ʈ�� �Է����ּ���." + newline);
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
								System.err.println("����!");
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
								System.err.println("����!");
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
								System.err.println("����!");
								System.out.println(e1.getMessage());
							}
						}
						String index = ch1.getSelectedItem(); // choice ���� Ŭ���� �������� �ҷ��´�.
						Runtime runtime = Runtime.getRuntime();
						if (index.equals("daum")) {
							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".net/"); // ������ ���л���Ʈ�� Ȩ�� �߰� �Ѵ�.
							} catch (IOException ex) {

							}
						} else {

							try {
								runtime.exec("C:/Program Files/internet explorer/iexplore.exe http://www." + index
										+ ".com/"); // ������ ���л���Ʈ�� Ȩ�� �߰� �Ѵ�.
							} catch (IOException ex) {

							}
						}
					}
				} else {
					String str = Text_field.getText(); // ���� ������ �ؽ�Ʈ �ʵ��� ���� �ҷ���
					String name1 = str; // String name1�� str�� �����Ѵ�.
					for (int i = 0; i < name1.length(); i++) {

						name1 = name1.replace("��", ""); // �⺻ �˰����� ���� ��,��,���� ���� ����
						name1 = name1.replace("��", "");
						name1 = name1.replace("����", "");

						name1 = name1.replace("���̹�", // ���̹��� �ܾ �߰����� �� �Ʒ��� ���� url�� ��ȯ
								"https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=");

						name1 = name1.replace("�˻�", "&oquery"); // �˻� �ܾ �߽߰� �˻�������� ��ȯ
					}

					System.out.println(name1); // ������ �ּ� + Ű���� + ����

					// Text_area.append(index + newline); // Text_area�� �������� ����� ���͸� ģ��
					Runtime runtime = Runtime.getRuntime();
					try {
						runtime.exec("C:/Program Files/internet explorer/iexplore.exe " + name1); // �⺻ �ͽ��÷ξ� �ڿ� ������
																									// url�� �Է��Ѵ�.
					} catch (IOException ex) {

					}

					Text_area.append(str + newline); // �ؽ�Ʈarea�� �ؽ�Ʈ �ʵ��� ���� + '\n'�� ���ļ� ���ͱ�� ����
					Text_field.requestFocus(); // �˻� ��ư ���� �� �ؽ�Ʈ ��Ŀ���� �ٽ� Text_field�� ��ġ�Ѵ�.
					Text_field.setText(""); // �˻� �� �˻� ĭ�� ��ĭ���� �ʱ�ȭ �õ�.
				}
			}
		}
	}
}

class JPanelTest extends JFrame {

	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;
	public JPanel03 jpanel03 = null;

	public void change(String panelName) { // �г� 1�� 2�� 3�� ���� �� �缳��

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