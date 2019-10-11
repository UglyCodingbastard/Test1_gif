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
	Container pane = getContentPane();// ����Ʈ�� �˾Ƴ���

	public static void main(String[] args) throws InterruptedException {

		JSplash splash = new JSplash(MainUI.class.getResource("�׸�15.png"), true, true, false, "V1", null, Color.BLUE,
				new Color(0, 255, 204)); // �ε� ȭ�� ���÷���
		splash.setBackground(new Color(0, 0, 0));
		splash.splashOn();

		for (int i = 1; i < 101; i++) {
			splash.setForeground(Color.black);
			splash.setProgress(i, i + "%"); // �ε� 1~100% ǥ��
			Thread.sleep(15);
		}
		splash.splashOff();
		JPanelTest win = new JPanelTest(); // win JPanelTest����

		win.setTitle("frame test"); // Title name
		win.jpanel01 = new JPanel01(win); // JPanel01
		win.jpanel02 = new JPanel02(win); // JPanel02
		win.jpanel03 = new JPanel03(win); // JPanel03

		win.jpanel01.setBackground(new Color(0, 0, 0)); // jpanel01 �� ����
		win.jpanel02.setBackground(new Color(0, 0, 0)); // jpanel02 �� ����
		win.jpanel03.setBackground(new Color(0, 0, 0)); // jpanel03 �� ����

		win.add(win.jpanel01); // ���α׷� ���۽� JPanel01 ����

		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ������â ���� �� ���μ������� ����
		win.setSize(901, 901); // win ������
		win.setVisible(true);
		win.setLocationRelativeTo(null); // âȭ���� ���Ϳ� ��������
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // ���� Ȩ ȭ��

	private static JPanelTest win;
	static JTextField id = new JTextField("���̵�");
	static JPasswordField pwd = new JPasswordField("��й�ȣ");
	static JButton btn = new JButton("�α���");
	static JCheckBox check; // check �ڵ� �α���
	static String login_id;

	static String id_readBuffer = null; // ���� ID����
	static String pw_readBuffer = null; // ���� PW����
	static String state_readBuffer = null; // ���� state����

	public JPanel01(JPanelTest win) {
		setLayout(null);
		this.win = win;
		this.login_id = login_id;

		// ���� ��ü ����
		Path path_id = Paths.get("C:/Users/nyj/Desktop/����AI/�ڵ��α���/ID.txt"); // ID.txt ���
		Path path_pw = Paths.get("C:/Users/nyj/Desktop/����AI/�ڵ��α���/PW.txt"); // PW.txt ���
		Path path_state = Paths.get("C:/Users/nyj/Desktop/����AI/�ڵ��α���/state.txt"); // state.txt ���

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

		ImageIcon img = new ImageIcon("icon_images/�̿��ڵ�����_û��.gif");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(100, 150, 700, 115);
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

		id.setBounds(225, 310, 450, 60);
		id.setFont(id.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		id.addFocusListener(new myFocusListener_id());
		add(id);

		pwd.setBounds(225, 385, 450, 60);
		pwd.setFont(pwd.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		pwd.addFocusListener(new myFocusListener_password());
		add(pwd);

		btn.setBackground(new Color(000, 255, 204));
		btn.setBorder(bevel);
		btn.setSize(450, 70);
		btn.setLocation(225, 490);
		btn.setFocusPainted(false);
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(btn.getFont().deriveFont(25.0f)); // ��Ʈ ũ�� ����
		add(btn); // �α��� ��ư ����

		if (state_readBuffer.equals("true")) { // true�� ��� check�ڽ� üũ
			check = new JCheckBox("�α��� ���� ����", true);
		} else if (state_readBuffer.equals("false")) { // false�� ��� check�ڽ� ����
			check = new JCheckBox("�α��� ���� ����", false);
		}
		check.setSize(170, 30);
		check.setLocation(225, 570);
		check.setBackground(new Color(0, 0, 0));
		check.setForeground(Color.white);
		check.setFocusPainted(false); // ��ư ��Ŀ�� ����
		check.setFont(check.getFont().deriveFont(18.0f)); // ��Ʈ ũ�� ����
		add(check); // �α��� ���� ���� üũ �ڽ�

		JButton btn2 = new JButton("ȸ������");
		btn2.setForeground(Color.white);
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // �׵θ� ����
		btn2.setContentAreaFilled(false); // �������
		btn2.setFocusPainted(false); // ��ư ��Ŀ�� ����
		btn2.setSize(70, 30);
		btn2.setLocation(605, 570);
		btn2.setFont(btn2.getFont().deriveFont(15.0f)); // ��Ʈ ũ�� ����
		add(btn2);

		btn.addActionListener(new Login());
		btn2.addActionListener(new Membership());
	}

	class myFocusListener_id extends FocusAdapter { // ���̵� �ؽ�Ʈ�ʵ� ��Ŀ�� �̺�Ʈ
		public void focusGained(FocusEvent e) { // ��Ŀ���� �������� ��
			if (id.getText().equals("���̵�")) { // �ؽ�Ʈ �ʵ忡 "���̵�"������ ������� ��ĭ���� �ʱ�ȭ
				id.setForeground(new Color(0, 0, 0));
				id.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // ��Ŀ���� �Ҿ���� ��
			if (id.getText().equals("")) {
				id.setForeground(new Color(150, 150, 150));
				id.setText("���̵�");
			}
		}
	}

	class myFocusListener_password extends FocusAdapter { // ���̵� �ؽ�Ʈ�ʵ� ��Ŀ�� �̺�Ʈ

		public void focusGained(FocusEvent e) { // ��Ŀ���� �������� ��
			if (pwd.getText().equals("��й�ȣ")) { // �ؽ�Ʈ �ʵ忡 "��й�ȣ"������ ������� ��ĭ���� �ʱ�ȭ
				pwd.setForeground(new Color(0, 0, 0));
				pwd.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // ��Ŀ���� �Ҿ���� ��
			if (pwd.getText().equals("")) {
				pwd.setForeground(new Color(150, 150, 150));
				pwd.setText("��й�ȣ");
			}
		}
	}

	class Login implements ActionListener { // ��ư Ű ������ �г� 3��(�α��� �� ȭ��) ȣ��

		@Override
		public void actionPerformed(ActionEvent e) {

			Log_DTO ldto = new Log_DTO();
			Object obj3 = e.getSource();

			boolean index = check.isSelected(); // check ���� üũ
			String db_id = null; // �����ͺ��̽����� mmeber_info ���̺� ������ ����� ���̵� ���� �������ִ� ����
			String db_pwd = null;
			String db_name = null;
			String db_email = null;
			String db_id_num = null;

			if ((JButton) obj3 == btn) { // �α��� ��ư ������ �� �߻��Ǵ� �̺�Ʈ
				System.out.println(id.getText() + "+" + pwd.getText());

				if (index == true) { // choice ���� Ŭ���� �������� �ҷ��´�. �ڵ� �α��� üũ ��� ��������

					BufferedOutputStream bs = null;
					BufferedOutputStream bs1 = null;
					BufferedOutputStream bs2 = null;

					try {
						bs = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/ID.txt")); // id����
						bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/PW.txt")); // pw����
						bs2 = new BufferedOutputStream(
								new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/state.txt")); // �ڵ� �α��� ��������

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
				} else if (index == false) {
					BufferedOutputStream bs = null;
					BufferedOutputStream bs1 = null;
					BufferedOutputStream bs2 = null;

					try {
						bs = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/ID.txt")); // id����
						bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/PW.txt")); // pw����
						bs2 = new BufferedOutputStream(
								new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/state.txt")); // �ڵ� �α��� ��������

						if (index == false) { // �ڵ��α��� ���°� true�� ��� �ؽ�Ʈ ���Ͽ� true����
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
						} // �ݵ�� �ݴ´�.
					}
				}

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
							"12345");
					String get_inputid = id.getText(); // �α��� â���� id�� �Է½� text�� �����ϴ� ����
					String get_inputpwd = pwd.getText(); // �α��� â���� pwd�� �Է½� text�� �����ϴ� ����

					String qu = "select * from member_info where id=" + "'" + get_inputid + "'"; // �α��ε� ������ �������� �����ͺ��̽�
																									// ������ ȸ�� ������ �˸°�
																									// �ҷ����� ������
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu); // �������� ��������ִ� ��ü

					while (rs.next()) { // ������ ���̽����� �ҷ��� ������ ������ �������ִ� �κ�
						db_id_num = rs.getString("id_num");
						db_id = rs.getString("id");
						db_pwd = rs.getString("pwd");
						db_name = rs.getString("name");
						db_email = rs.getString("email");

					}
					if (id.getText().equals("���̵�") || pwd.getText().equals("��й�ȣ")) {
						JOptionPane.showMessageDialog(null, "�Էµ��� ���� ������ �ֽ��ϴ�.");
					}

					else if (db_id.equals(id.getText()) && db_pwd.equals(pwd.getText())) { // ����ڰ� �Է��� id,pwd���� �����ͺ��̽���
																							// �����
																							// ȸ�������� ���� �α��� ������ ���и�
																							// �����ִ� �κ�

						JPanel03.in_id_num = db_id_num; // �α��� �� ����� ������ ����� �� �ֵ��� �α��� ���� �г��� �г�3�� ���� �����ϴ� �κ�
						JPanel03.in_id = db_id;
						JPanel03.in_pwd = db_pwd;
						JPanel03.in_name = db_name;
						JPanel03.in_email = db_email;
						JPanel03.label_user.setText(JPanel03.in_id); // �α��ε� ������ �������� ����� �̸��� ������ִ� �κ�

						JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
						win.change("panel03");

					} else if (!db_id.equals(id.getText()) || !db_pwd.equals(pwd.getText())) { // ���̵� ��й�ȣ�� �ٸ���� �����޼���
						JOptionPane.showMessageDialog(null, "���̵� �н����尡 ��ġ���� �ʽ��ϴ�.");
					}

					st.close();
				} catch (Exception e2) {
					System.err.println("����!");
					System.out.println(e2.getMessage());
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
	JButton btn3 = new JButton(); // ȸ������ �Ϸ� ��ư

	public JPanel02(JPanelTest win) {
		this.win = win;
		setLayout(null);
		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);

		ImageIcon img = new ImageIcon("icon_images/�̿��ڵ�����_û��.gif");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(100, 70, 700, 115);
		add(imageLabel);

		JLabel label_id = new JLabel("���̵�");
		label_id.setForeground(Color.white);
		label_id.setBounds(225, 200, 70, 60);
		label_id.setFont(label_id.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(label_id);
		input_id.setBounds(225, 245, 450, 50);
		input_id.setFont(input_id.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		add(input_id);

		JLabel label_password = new JLabel("��й�ȣ");
		label_password.setForeground(Color.white);
		label_password.setBounds(225, 290, 70, 60);
		label_password.setFont(label_password.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(label_password);
		input_pwd.setBounds(225, 335, 450, 50);
		input_pwd.setFont(input_pwd.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		add(input_pwd);

		JLabel label_repassword = new JLabel("��й�ȣ ��Ȯ��");
		label_repassword.setForeground(Color.white);
		label_repassword.setBounds(225, 380, 150, 60);
		label_repassword.setFont(label_repassword.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(label_repassword);
		input_pwd_re.setBounds(225, 425, 450, 50);
		input_pwd_re.setFont(input_pwd_re.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		add(input_pwd_re);

		JLabel label_email = new JLabel("�̸���");
		label_email.setForeground(Color.white);
		label_email.setBounds(225, 470, 150, 60);
		label_email.setFont(label_email.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(label_email);
		input_email.setBounds(225, 515, 450, 50);
		input_email.setFont(input_email.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		add(input_email);

		JLabel label_uesrname = new JLabel("������̸�");
		label_uesrname.setForeground(Color.white);
		label_uesrname.setBounds(225, 560, 150, 60);
		label_uesrname.setFont(label_uesrname.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(label_uesrname);
		input_name.setBounds(225, 605, 450, 50);
		input_name.setFont(input_name.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		add(input_name);

		over = new JButton("�ߺ�Ȯ��");
		over.setForeground(new Color(000, 255, 204));
		over.setSize(150, 60);
		over.setLocation(660, 245);
		over.setBorderPainted(false); // �׵θ� ����
		over.setContentAreaFilled(false); // �������
		over.setFocusPainted(false); // ��ư ��Ŀ�� ����
		over.setFont(over.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(over);

		btn3 = new JButton("�����ϱ�");
		btn3.setSize(450, 70);
		btn3.setLocation(225, 700);
		btn3.setBorder(bevel);
		btn3.setBackground(new Color(000, 255, 204));
		btn3.setFocusPainted(false); // ��ư ��Ŀ�� ����
		btn3.setForeground(Color.white);
		btn3.setFont(btn3.getFont().deriveFont(25.0f)); // ��Ʈ ũ�� ����
		add(btn3);

		jButton2 = new JButton("�α���");
		jButton2.setForeground(Color.white);
		jButton2.setSize(90, 50);
		jButton2.setLocation(405, 780);
		jButton2.setBorderPainted(false); // �׵θ� ����
		jButton2.setContentAreaFilled(false); // �������
		jButton2.setFocusPainted(false); // ��ư ��Ŀ�� ����
		jButton2.setFont(jButton2.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
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

	class Over implements ActionListener {
		int result = 3; // ���̵� �ߺ� üũ

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource(); // ��ư�� ������ �� �߻��Ǵ� �ҽ��� �޾ƿ� �����ϴ� ����
			String sql = null;

			// TODO Auto-generated method stub
			if ((JButton) obj == over) { // �ߺ�Ȯ�� ��ư�� ��������
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
							"12345");
					String qu = "select * from member_info";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu);

					while (rs.next()) {
						if (input_id.getText().equals("")) { // ������ ��
							result = 1;
						} else if (rs.getString("id").equals(input_id.getText())) { // ���̵� �ߺ��϶�
							// System.out.println(input_id.getText().length());
							result = 0;
							break;
						} else if (input_id.getText().length() > 10) {
							result = 3;
						} else if (input_pwd.getText().length() > 10) {
							result = 4;
						} else { // ��� ������ ���̵� �� ���
							result = 2;
							// System.out.println(login);
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
			} else if (result == 3) {
				login = false;
				JOptionPane.showMessageDialog(null, "���̵�� �ִ� 10�� �Դϴ�.");
			} else if (result == 4) {
				login = false;
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �ִ� 10�� �Դϴ�.");
			} else if (result == 2) {
				login = true;
				JOptionPane.showMessageDialog(null, "��� ������ ���̵� �Դϴ�.");
			}
			System.out.println(login);
		}

	}

	class Ok implements ActionListener { // ��ư Ű ������ 'ȸ������ �Ϸ�' ������ �Բ� â�� ���Ӱ� �ϳ� �� �� ȸ������ �Ϸ�

		@Override
		public void actionPerformed(ActionEvent e) {

			DTO dto = new DTO(); // ȸ�����Խ� �Է��� ������ �ӽ����� �ϴ� Ŭ����
			DAO dao = new DAO(); // ȸ�����Խ� �Է��� ������ ���޹޾� ���� ��ɹ��� ������ Ŭ����

			Object obj = e.getSource();

			if ((JButton) obj == btn3) { // ȸ������ �Ϸ� ������ �� �Էµ� ������ DTOŬ������ �Ѱ��ִ� �κ�
				dto.setid(input_id.getText());
				dto.setpwd(input_pwd.getText());
				dto.setpwd_re(input_pwd_re.getText());
				dto.setemail(input_email.getText());
				dto.setname(input_name.getText());
				// System.out.println(dto.getid()+"�ҷ��� ���̵� ��");

				String db_id2 = null; // �����ͺ��̽����� mmeber_info ���̺� ������ ����� ���̵� ���� �������ִ� ����
				String db_pwd2 = null;
				String db_name2 = null;
				String db_email2 = null;
				String db_id_num2 = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/members", "root",
							"12345");

					String qu = "select * from member_info"; // �α��ε� ������ �������� �����ͺ��̽�
																// ������ ȸ�� ������ �˸°�
																// �ҷ����� ������
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(qu); // �������� ��������ִ� ��ü

					while (rs.next()) { // ������ ���̽����� �ҷ��� ������ ������ �������ִ� �κ�
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
						JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�");

					}
					st.close();
				} catch (Exception e2) {
					System.err.println("����!");
					System.out.println(e2.getMessage());
				}

				if (input_pwd.getText().equals(input_pwd_re.getText())) { // �Էµ��� ���� ������ ������ ���� ���, ���δ� �Է������� ȸ������ �Ϸ�
																			// �޼���, ���� �Է����� ������ �����޼��� ���
					if (dto.id.equals("") || dto.pwd.equals("") || dto.email.equals("") || dto.name.equals("")) {
						JOptionPane.showMessageDialog(null, "�Էµ��� ���� ������ �ֽ��ϴ�.");
					} else if (input_pwd.getText().length() > 10 || input_pwd_re.getText().length() > 10) {
						login = false;
						JOptionPane.showMessageDialog(null, "��й�ȣ�� 10���ڸ� �ʰ��Ҽ� �����ϴ�.");
					} else {
						if (login == true) {
							JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�"); // ȸ������ �˶� â�� ��Ÿ��
							BufferedWriter bw = null; // ȸ������ �Ϸ�� ȸ���� �ֱ� �˻�� �����ϱ����� recent_record txt���Ͽ� ȸ�� ������ �����ϴ� �κ�

							try {
								bw = new BufferedWriter(new FileWriter(
										"C:/Users/nyj/Desktop/��Ʈ������ �ڷ�/������Ʈ �ڷ�/AI�����ν� ���α׷�/recent_record.txt", true)); // �ؽ�Ʈ����
																														// ��μ���
							} catch (IOException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							PrintWriter pw = new PrintWriter(bw, true); // �ؽ�Ʈ ���Ͽ� ���������� ���� �Է����ִ� ��ü
							try { // �ؽ�Ʈ ���Ͽ� ���� �Է����ִºκ�

								pw.write(input_id.getText() + ",");
								// System.out.println(input_id.getText());
								pw.write(input_name.getText() + "," + "�˻��� : \n");
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
								} // �ݵ�� �ݴ´�.
							}

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

						} else {
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

class JPanel03 extends JPanel { // �α��� �� ȭ��
	static JLabel label_user; // �г�1���� �α��ν� �ҷ��� ȸ�� �̸� ������ �������ִ� ��

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
	JTextField search = new JTextField();// �˻� â
	String var_1 = "";

	public JPanel03(JPanelTest win) {

		this.win = win;
		this.Text_field = Text_field; // �˻�� �Է¹޴� �ؽ�Ʈ�ʵ�
		setLayout(null);

		BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
		LineBorder b2 = new LineBorder(new Color(102, 204, 102), 3);

		ImageIcon img = new ImageIcon("icon_images/�̿��ڵ�����_û��.gif");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(90, 80, 700, 115);
		add(imageLabel);

		jButton1 = new JButton(new ImageIcon("icon_images/66.gif"));
		jButton1.setSize(700, 180);
		jButton1.setLocation(90, 640);
		lb = new LineBorder(Color.black, 0);
		// jButton1.setBorder(lb);
		jButton1.setBackground(Color.black);
		// jButton1.setFocusPainted(false); // ��ư ��Ŀ�� ����
		add(jButton1);

		ch1 = new Choice(); // ����� ����Ʈ
		ch1.addItem("����� ����Ʈ");
		ch1.addItem("naver");
		ch1.addItem("google");
		ch1.addItem("daum");
		ch1.setBounds(20, 20, 109, 35);
		add(ch1);

		ch2 = new Choice(); // �ֱ� �˻���
		ch2.addItem("��õ �˻���");
		ch2.setBounds(159, 20, 109, 35);
		add(ch2);

		label_user = new JLabel(in_id_num); // �α����� ����� id����ϰ� �ؾߵ�(id �ִ� ���̸� ���ص־ߵ�
		label_user.setForeground(Color.white);
		label_user.setBounds(650, 8, 100, 60);
		label_user.setFont(label_user.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		add(label_user);

		jButton2 = new JButton("�α׾ƿ�"); // �α׾ƿ�
		jButton2.setForeground(Color.white);
		jButton2.setSize(110, 40);
		jButton2.setLocation(750, 20);
		jButton2.setContentAreaFilled(false); // �������
		jButton2.setFont(jButton2.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(jButton2);

		JScrollPane pane = new JScrollPane(Text_area); // JTextArea area = new JTextArea("�˻���"); //�˻���
		pane.setSize(700, 350);
		pane.setLocation(90, 270);

		Text_area.setEditable(false); // JTextArea �ȿ� �ؽ�Ʈ �Է� ����
		Text_area.setLineWrap(true); // �� �ѱ�� ��� �ѱ�
		Text_area.setFont(Text_area.getFont().deriveFont(20.0f)); // ��Ʈ ũ�� ����
		Border lineBorder = BorderFactory.createLineBorder(new Color(0, 0, 0), 3); // TextArea�� �׵θ����� ���� ����
		// �α���
		// 3px�� �����մϴ�.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // �ؽ�Ʈ�� TextArea ��� ���̿� ������ �α� ���ؼ�
		// emptyBorder�� �����մϴ�.
		add(pane);

		jButton4 = new JButton("Clear"); // Text_area �ʱ�ȭ
		jButton4.setForeground(Color.white);
		jButton4.setSize(100, 30);
		jButton4.setLocation(780, 595);
		jButton4.setFocusPainted(false); // ��Ŀ�� ����
		jButton4.setBorderPainted(false); // �׵θ� ����
		jButton4.setContentAreaFilled(false); // �������
		jButton4.setFont(jButton4.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(jButton4);

		Text_field.setBounds(90, 210, 700, 50); // �˻� â
		Text_field.setFont(Text_field.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(Text_field);

		jButton3 = new JButton("�˻�"); // �˻� ��ư
		jButton3.setSize(58, 50);
		jButton3.setLocation(805, 210);
		jButton3.setBorder(b2);
		jButton3.setContentAreaFilled(false); // �������
		jButton3.setForeground(Color.white);
		jButton3.setFont(jButton3.getFont().deriveFont(17.0f)); // ��Ʈ ũ�� ����
		add(jButton3);

		jButton1.addActionListener(new Play()); // ��ư Ŭ�� ���� ����
		jButton2.addActionListener(new Home()); // �α׾ƿ� ��ư ������ �α��� â ���
		jButton3.addActionListener(new search()); // �˻� ��ư ������ �Ʒ��� ���
		Text_field.addActionListener(new search()); // Enter ������ �� �Ʒ��� ���
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

	class Home implements ActionListener { // �α׾ƿ� ���
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton2) { // �α׾ƿ� ��ư Ŭ���� Ȩ ȭ������ �̵� �� ��� ���� �ʱ�ȭ

				BufferedOutputStream bs = null;
				BufferedOutputStream bs1 = null;
				BufferedOutputStream bs2 = null;
				try {
					bs = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/ID.txt")); // id����
					bs1 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/PW.txt")); // pw����
					bs2 = new BufferedOutputStream(new FileOutputStream("C:/Users/nyj/Desktop/����AI/�ڵ��α���/state.txt")); // �ڵ�
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

	class search implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj4 = e.getSource();
			if (obj4 == Text_field || (JButton) obj4 == jButton3) { // Enter or �˻� ��ư ���� �� �˻� ���
				if (Text_field.getText().equals("")) {
					Text_area.append("�˻�� �Է����ּ���." + newline);
					Text_field.requestFocus();
				} else {
					String str = Text_field.getText(); // ������ ��ư ������ �ؽ�Ʈ �ʵ��� ���� �ҷ���

					ArrayList<String> ar = new ArrayList<String>(); // �˻�� ������ arrayList
					String[] arr = new String[3]; // �����id,name, "�˻��� : "�� �����ϴ� �迭
					String originalString = "";
					String replaceString = "";
					Log_DTO ldto2 = new Log_DTO();
					Object obj2 = e.getSource();
					int count = 0;

					ldto2.setid_num(JPanel03.in_id_num); // �α��ν� �Էµ� ������ �������� �����ͺ��̽����� ����� ������ �ҷ����� �κ�
					ldto2.setlog_id(JPanel03.in_id);
					ldto2.setlog_pwd(JPanel03.in_pwd);
					ldto2.setlog_email(JPanel03.in_email);
					ldto2.setlog_name(JPanel03.in_name);

					String fileName = "C:/Users/nyj/Desktop/��Ʈ������ �ڷ�/������Ʈ �ڷ�/AI�����ν� ���α׷�/recent_record.txt"; // �������ϰ��
					// file ��ü ����
					File inputFile = new File(fileName); // �Է��ϴ� ����
					File outputFile = new File(fileName + ".temp"); // ����ϴ� ����(�ӽ�����)
					FileInputStream fileinputStream = null;
					BufferedReader bufferedReader = null;
					FileOutputStream fileOutputStream = null;
					BufferedWriter bufferedWriter = null;
					boolean result = false;
					try {
						// FileInputStream,FileOutputStream,BufferedReader,BufferedWriter ����
						fileinputStream = new FileInputStream(inputFile);
						fileOutputStream = new FileOutputStream(outputFile);
						bufferedReader = new BufferedReader(new InputStreamReader(fileinputStream));
						bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream)); // ���� ���Ͽ��� �о� ���̴�
																										// �Ѷ���
						String line; // ���Ͽ� ��ġ�ϴ� ���ڷ� ��ü�ϰ� �� ���� string
						String repLine; // �ٲٰ��� �ϴ� string�� �ٲ� string ����

						try {
							// ���� ��ü ����
							File file = new File("C:/Users/nyj/Desktop/��Ʈ������ �ڷ�/������Ʈ �ڷ�/AI�����ν� ���α׷�/recent_record.txt");
							// �Է� ��Ʈ�� ����
							FileReader filereader = new FileReader(file);
							// �Է� ���� ����
							BufferedReader bufReader = new BufferedReader(filereader);
							String line_1 = "";

							ArrayList<String> bufRead = new ArrayList<String>(); // �ؽ�Ʈ ������ ������ �ϳ��� �־���
							// HashMap<String, String> mValue = new HashMap<String, String>(); //
							// System.out.println(label_user.getText()+ "������");
							while ((line_1 = bufReader.readLine()) != null) { // ������ �˻����� ������ġ�� ã���ִ� �κ�
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

						// System.out.println("�Է� ar = "+ar.get(0));
						// System.out.println(ar.size());
						for (int i = 0; i < ar.size(); i++) {
							// System.out.println("��� ar = " + ar.get(i) + "�˻���");
							replaceString = ar.get(i);
						}
						// System.out.println(originalString);
						String temp = "";
						while ((line = bufferedReader.readLine()) != null) {// ���� ���Ͽ��� �Ѷ��ξ� �д´�.
							// System.out.println(originalString + "�������� ��Ʈ��");
							if (originalString.equals(line)) {
								temp = temp + line + replaceString + "\n";
							} else {
								temp = temp + line + "\n";
								// System.out.println("�˻��� �ȵ�");
							}
							// System.out.println(line + "while���� ����");
						}
						bufferedWriter.write(temp);// ���ο� ���Ͽ� ����.
						// System.out.println(temp + " ���ִ� ��");

						result = true; // ���������� ����Ǿ����� �˸��� flag
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
						if (result) { // ���������� ����Ǿ��� ��� ���� ������ ����� ���ο� ���ϸ��� ���� ���ϸ����� rename
							inputFile.delete();
							outputFile.renameTo(new File(fileName));
						}
					}

					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					// �˻��� ����κ�

					if (ch1.getSelectedItem().equals("naver")) { // ���̹��� �����ϰ� �˻���ư�� ������� site_search ���̺� ������ ���̹� ī��Ʈ�� 1
																	// ������Ų��.

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
							System.err.println("����!");
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
							System.err.println("����!");
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
							System.err.println("����!");
							System.out.println(e1.getMessage());
						}
					}

					Text_area.append(str + newline); // �ؽ�Ʈarea�� �ؽ�Ʈ �ʵ��� ���� + '\n'�� ���ļ� ���ͱ�� ����
					Text_field.requestFocus(); // �˻� ��ư ���� �� �ؽ�Ʈ ��Ŀ���� �ٽ� Text_field�� ��ġ�Ѵ�.
					Text_field.setText(""); // �˻� �� �˻� ĭ�� ��ĭ���� �ʱ�ȭ �ȴ�.

				}

			}
		}
	}
}

class JPanelTest extends JFrame {

	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;
	public JPanel03 jpanel03 = null;

	public void change(String panelName) { // �г� 1���� 2�� ���� �� �缳��

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