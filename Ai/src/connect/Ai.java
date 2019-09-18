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
	Container pane = getContentPane();// ����Ʈ�� �˾Ƴ���

	public static void main(String[] args) {

		JPanelTest win = new JPanelTest();

		win.setTitle("frame test");
		win.jpanel01 = new JPanel01(win);
		win.jpanel02 = new JPanel02(win);
		win.jpanel03 = new JPanel03(win);

		win.jpanel01.setBackground(new Color(244, 244, 244)); // jpanel01 �� ����
		win.jpanel02.setBackground(new Color(244, 244, 244)); // jpanel02 �� ����
		//win.jpanel03.setBackground(new Color(244, 244, 244)); // jpanel03 �� ����

		win.add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		win.setSize(500, 450);
		// win.setBackground(Color.black);

		win.setVisible(true);
		win.setLocationRelativeTo(null); // âȭ���� ���Ϳ� ��������
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // ���� Ȩ ȭ��
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

		JTextField trash = new JTextField(); // ù ��Ŀ�� ���ֱ� ���� trash �ؽ�Ʈ �ʵ�
		trash.setBounds(0, 0, 0, 0);
		add(trash);

		ImageIcon img = new ImageIcon("icon_images/�ΰ�.png");
		JLabel imageLabel = new JLabel(img);
		imageLabel.setBounds(120, 60, 255, 35);
		add(imageLabel);
		id = new JTextField("���̵�");
		id.setForeground(new Color(150, 150, 150));
		id.setBounds(120, 116, 260, 35);
		id.addFocusListener(new myFocusListener_id());
		// id.setBorder(b2);
		// id.setFocusable(false);
		// id.setColumns(5);
		add(id);

		password = new JPasswordField("��й�ȣ");
		// password.setForeground(Color.RED);
		password.setBounds(120, 165, 260, 35);
		password.setForeground(new Color(150, 150, 150));
		password.addFocusListener(new myFocusListener_password());
		// password.setBorder(b2);
		add(password);

		// ImageIcon normalicon = new ImageIcon("icon_images/�α���.png");
		JButton btn = new JButton("�α���");
		btn.setBackground(new Color(102, 204, 102));
		btn.setBorder(bevel);
//		btn.setOpaque(false);	//��� ���ְ� �׵θ��ϰ� Ŭ���� ȿ��
//		btn.setBorderPainted(false);	//�׵θ� ����
//		btn.setContentAreaFilled(false);	//�������

		btn.setSize(260, 47);
		btn.setLocation(120, 214);
		btn.setFocusPainted(false);
		add(btn);

		JCheckBox check = new JCheckBox("�α��� ���� ����", false);
		check.setSize(115, 20);
		check.setLocation(120, 273);
		check.setBackground(new Color(244, 244, 244));
		check.setFocusPainted(false); // ��ư ��Ŀ�� ����
		add(check);

		// ImageIcon normalicon1 = new ImageIcon("icon_images/ȸ������.png");
		JButton btn2 = new JButton("ȸ������");
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // �׵θ� ����
		btn2.setContentAreaFilled(false); // �������
		btn2.setFocusPainted(false); // ��ư ��Ŀ�� ����

		btn2.setSize(70, 20);
		btn2.setLocation(310, 273);
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
			if (password.getText().equals("��й�ȣ")) { // �ؽ�Ʈ �ʵ忡 "��й�ȣ"������ ������� ��ĭ���� �ʱ�ȭ
				password.setForeground(new Color(0, 0, 0));
				password.setText("");
			}
		}

		public void focusLost(FocusEvent e) { // ��Ŀ���� �Ҿ���� ��
			if (password.getText().equals("")) {
				password.setForeground(new Color(150, 150, 150));
				password.setText("��й�ȣ");
			}
		}
	}

	class Login implements ActionListener { // ��ư Ű ������ �г� 3��(�α��� �� ȭ��) ȣ��
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel03");
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
		// this.input_pwd = input_pwd;
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
		// btn3.setBackground(Color.green);
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

	class Over implements ActionListener {
		int result = 3; // ���̵� �ߺ� üũ

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
//			DTO dto = new DTO(); // ȸ������
//			DAO dao = new DAO();
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
						if (input_id.getText().equals("")) { // ������ ��
							result = 1;
						} else if (rs.getString("id").equals(input_id.getText())) { // ���̵� �ߺ��϶�
							result = 0;
							break;
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
			} else if (result == 2) {
				login = true;
				// System.out.println(login);
				JOptionPane.showMessageDialog(null, "��� ������ ���̵� �Դϴ�.");
			}
			// login++;
			// System.out.println("login:"+login);
			System.out.println(login);
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
						if (login == true) {
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
						} else {
							// System.out.println(login);
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

		ImageIcon play = new ImageIcon("icon_images/�ʷ�����3.jpg");
		jButton1 = new JButton(play);
		// ImageIcon stop = new ImageIcon("icon_images/����������.png");
		// jButton4 = new JButton(play);
		// jButton1.setBorder(b2);
		jButton1.setSize(300, 100);
		jButton1.setLocation(100, 300);
		jButton1.setBackground(new Color(244, 244, 244));

		// jButton1.setOpaque(false); //��� ���ְ� �׵θ��ϰ� Ŭ���� ȿ��
		// jButton1.setContentAreaFilled(false); //�������
		// jButton1.setFocusPainted(false); //��ư ��Ŀ�� ����
		// jButton1.setBorderPainted(false); // �׵θ� ����
		// jButton1.setBorder(bevel); //��ü�� ȿ��

//		btn.setBorder(bevel);
//		btn.setOpaque(false);	//��� ���ְ� �׵θ��ϰ� Ŭ���� ȿ��
//		btn.setBorderPainted(false);	//�׵θ� ����
//		btn.setContentAreaFilled(false);	//�������

		add(jButton1);

		ch1 = new Choice(); // ����� ����Ʈ
		ch1.addItem("����� ����Ʈ");
		ch1.addItem("www.naver.com");
		ch1.addItem("www.google.com");
		ch1.addItem("www.daum.net");
		ch1.setBounds(20, 20, 109, 35);
		add(ch1);

		ch2 = new Choice(); // �ֱ� �˻���
		ch2.addItem("�ֱ� �˻���");
		ch2.setBounds(159, 20, 109, 35);
		add(ch2);

//		textField = new JTextField("����� ����Ʈ"); // ����� ����Ʈ
//		textField.setBounds(20, 99, 109, 35);
//		add(textField);

		JLabel label_user = new JLabel("������̸�"); // �α����� ����� id����ϰ� �ؾߵ�(id �ִ� ���̸� ���ص־ߵ�
		label_user.setBounds(300, 8, 60, 50);
		add(label_user);

		jButton2 = new JButton("�α׾ƿ�"); // �α׾ƿ�
		jButton2.setSize(82, 25);
		jButton2.setLocation(386, 20);
		// jButton2.setBackground(new Color(102, 204, 102));
		// jButton2.setBackground(Color.GRAY);
		jButton2.setContentAreaFilled(false); // �������
		add(jButton2);

		Text_field.setBounds(100, 70, 300, 30); // �˻� â
		// Text_field.setBorder(b2);
		add(Text_field);
		JScrollPane pane = new JScrollPane(Text_area); // JTextArea area = new JTextArea("�˻���"); //�˻���
		// Text_area.setBackground(Color.DARK_GRAY); //���� ���� ����
		// Text_area.setFont(new Font("����ü",Font.BOLD,13)); //��Ʈ ����
		// Text_area.setForeground(Color.WHITE); //���ڻ� ����
		// Text_area.setBackground(new Color(122, 224, 142));

		pane.setSize(300, 180);
		pane.setLocation(100, 105);

		Text_area.setEditable(false); // JTextArea �ȿ� �ؽ�Ʈ �Է� ����
		Text_area.setLineWrap(true); // �� �ѱ�� ��� �ѱ�

		Border lineBorder = BorderFactory.createLineBorder(new Color(102, 204, 102), 3); // TextArea�� �׵θ����� ���� ����
																							// �α���
																							// 3px�� �����մϴ�.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // �ؽ�Ʈ�� TextArea ��� ���̿� ������ �α� ���ؼ�
																			// emptyBorder�� �����մϴ�.
		// pane.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		// // TextArea�� lineBorder(�����׵θ�),
		// emptyBorder(����)�� ������ ����
		// ��輱��
		// �����մϴ�.
		add(pane);

		jButton4 = new JButton("Clear");
		jButton4.setSize(70, 30);
		jButton4.setLocation(400, 260);
		jButton4.setFocusPainted(false); // ��Ŀ�� ����
		jButton4.setBorderPainted(false); // �׵θ� ����
		jButton4.setContentAreaFilled(false); // �������
		add(jButton4);

		// ImageIcon normalicon = new ImageIcon("icon_images/������2.jpg");
		jButton3 = new JButton("�˻�");
		jButton3.setSize(58, 30);
		jButton3.setLocation(410, 70);
		jButton3.setBorder(b2);
		jButton3.setFocusPainted(false); // ��ư ��Ŀ�� ����
		// jButton3.setContentAreaFilled(false); // �������
		jButton3.setBorderPainted(false); // �׵θ� ����
		jButton3.setBackground(new Color(102, 204, 102));
		// jButton3.setBorder(bevel);
		add(jButton3);

		jButton1.addActionListener(new Play()); // ��ư Ŭ�� ���� ����
		jButton2.addActionListener(new Home()); // �α׾ƿ� ��ư ������ �α��� â ���
		jButton3.addActionListener(new search()); // �˻� ��ư ������ �Ʒ��� ���
		Text_field.addActionListener(new search()); // Enter ������ �� �Ʒ��� ���
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
				// System.out.println("����");
				// Text_area.setT
				Text_area.append("What can I help you with?" + newline);
				// jButton1.setImageIcon("icon_images/����������.png");
				// jButton1.ch();
				// ImageIcon play = new ImageIcon("icon_images/����������.png");
			}
		}
	}

	class Home implements ActionListener { // ��ư Ű ������ �г� 1��(�α��� ȭ��) ȣ��
		@Override
		public void actionPerformed(ActionEvent e) {
			Text_area.setText("");
			win.change("panel01");
		}
	}

	class search implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Text_field || e.getSource() == jButton3) { // Enter or �˻� ��ư ���� �� �˻� ���
				if (Text_field.getText().equals("")) {
					// Text_area.setHorizontalAlignment(Text_area.);
					// Text_area.setAlignment(Component.CENTER);
					Text_area.append("�˻�� �Է����ּ���." + newline);
					// Text_area.setForeground(Color.red); //���ڻ� ����
					Text_field.requestFocus();
				} else {
					String str = Text_field.getText(); // ������ ��ư ������ �ؽ�Ʈ �ʵ��� ���� �ҷ���
					Text_area.append(str + newline); // �ؽ�Ʈarea�� �ؽ�Ʈ �ʵ��� ���� + '\n'�� ���ļ� ���ͱ�� ����
					// Text_field.selectAll(); //�˻� �� �˻� �� �ؽ�Ʈ�� ��ü �巡�� �ȴ�.
					Text_field.requestFocus(); // �˻� ��ư ���� �� �ؽ�Ʈ ��Ŀ���� �ٽ� Text_field�� ��ġ�Ѵ�.
					// Text_area.setCaretPosition(Text_area.getDocument().getLength());
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