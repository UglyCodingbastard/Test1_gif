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
	Container pane = getContentPane();// ����Ʈ�� �˾Ƴ���
	
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
		win.setLocationRelativeTo(null); // âȭ���� ���Ϳ� ��������
	}

}

//@SuppressWarnings("serial")
class JPanel01 extends JPanel { // ���� Ȩ ȭ��

	private JPasswordField passwordField;
	private static JPanelTest win;
	static JButton btn = new JButton("�α���");
	static JTextField id = new JTextField("���̵�");
	static JPasswordField pwd = new JPasswordField("��й�ȣ");
	
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

		JCheckBox check = new JCheckBox("�α��� ���� ����", false);
		check.setSize(115, 20);
		check.setLocation(120, 233);
		add(check);

		JButton btn2 = new JButton("ȸ������");
		btn2.setBorder(bevel);
		btn2.setBorderPainted(false); // �׵θ� ����
		btn2.setContentAreaFilled(false); // �������

		btn2.setSize(70, 20);
		btn2.setLocation(310, 233);
		add(btn2);

		btn.addActionListener(new Login());
		btn2.addActionListener(new Membership());
	}
   
	static class Login implements ActionListener { // �α��� ��ư ������ ��, ��ư Ű ������ �г� 3��(�α��� �� ȭ��) ȣ��

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

			if ((JButton) obj3 == btn) { // �α��� ��ư ������ ��

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
						JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
						win.change("panel03");

					} else {
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

	JTextField input_id = new JTextField(10);
	JPasswordField input_pwd = new JPasswordField(10);
	JTextField input_email = new JTextField(30); // �̸���
	JTextField input_name = new JTextField(20); // ����� �̸�
	JPasswordField input_pwd_re = new JPasswordField(10);

	JButton over = new JButton();
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
		add(over);

		btn3 = new JButton("ȸ������ �Ϸ�");
		btn3.setSize(260, 35);
		btn3.setLocation(120, 321);
		btn3.setBorder(bevel);
		btn3.setBackground(Color.green);
		add(btn3);

		jButton2 = new JButton("�α���");
		jButton2.setSize(70, 35);
		jButton2.setLocation(217, 370);
		jButton2.setBorderPainted(false); // �׵θ� ����
		jButton2.setContentAreaFilled(false); // �������
		add(jButton2);

		jButton2.addActionListener(new Home()); // ��� ��ư ���� �� Ȩ ȭ������ �̵�
		btn3.addActionListener(new Ok()); // ȸ������ �Ϸ� ��ư ������ ȸ������ �Ϸ� ��� ������ ���� ���ο� â�� �ϳ��߸鼭 Ȩ���� �̵�
		over.addActionListener(new Ok()); // �ߺ�Ȯ�� ��ư ������
	}

	class Home implements ActionListener { // ��ư Ű ������ �г� 1��(�α��� ȭ��) ȣ��
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}

	class Ok implements ActionListener { // ��ư Ű ������ 'ȸ������ �Ϸ�' ������ �Բ� â�� ���Ӱ� �ϳ� �� �� ȸ������ �Ϸ�

		@Override
		public void actionPerformed(ActionEvent e) {

			DTO dto = new DTO(); // ȸ������
			DAO dao = new DAO();
			Object obj = e.getSource();
			String sql = null;

			if ((JButton) obj == btn3) { // ȸ������ �Ϸ� ������ ��
				dto.setid(input_id.getText());
				dto.setpwd(input_pwd.getText());
				dto.setpwd_re(input_pwd_re.getText());
				dto.setemail(input_email.getText());
				dto.setname(input_name.getText());

				if (input_pwd.getText().equals(input_pwd_re.getText())) {
					if (dto.id.equals("") || dto.pwd.equals("") || dto.email.equals("") || dto.name.equals("")) {
						JOptionPane.showMessageDialog(null, "�Էµ��� ���� ������ �ֽ��ϴ�.");
					} else {
						JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�"); // ȸ������ �˶� â�� ��Ÿ��
						win.change("panel01");
						try {
							DAO.create(dto); // dto�� DAO�� �Ѱ��ش�.
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
		jButton1 = new JButton("�����ν� ����");
		jButton1.setSize(300, 80);
		jButton1.setLocation(100, 300);
		add(jButton1);

		ch = new Choice(); // ����� ����Ʈ
		ch.addItem("����� ����Ʈ");
		ch.addItem("www.naver.com");
		ch.addItem("www.google.com");
		ch.addItem("www.daum.net");
		ch.setBounds(20, 20, 109, 35);

		Log_DTO ldto2 = new Log_DTO();
		String user_name=ldto2.getlog_name();
		
		System.out.println(user_name);

		JLabel label_user = new JLabel(user_name); // �α����� ����� id����ϰ� �ؾߵ�(id �ִ� ���̸� ���ص־ߵ�
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

		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1); // TextArea�� �׵θ����� ���� ���� �α��� 3px�� �����մϴ�.
		Border emptyBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3); // �ؽ�Ʈ�� TextArea ��� ���̿� ������ �α� ���ؼ�
		// emptyBorder�� �����մϴ�.
		pane.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder)); // TextArea�� lineBorder(�����׵θ�),
		// emptyBorder(����)�� ������ ���� ��輱��
		// �����մϴ�.
		add(pane);

		ImageIcon normalicon = new ImageIcon("icon_images/������.jpg");
		jButton3 = new JButton("", normalicon); // �α׾ƿ�
		jButton3.setSize(30, 30);
		jButton3.setLocation(410, 70);
		add(jButton3);

		jButton2.addActionListener(new Home()); // �α׾ƿ� ��ư ������ �α��� â ���
		jButton3.addActionListener(new search()); // �˻� ��ư ������ �Ʒ��� ���
		Text_field.addActionListener(new search()); // Enter ������ �� �Ʒ��� ���
	}

	class Home implements ActionListener { // ��ư Ű ������ �г� 1��(�α��� ȭ��) ȣ��
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}

	class search implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Text_field || e.getSource() == jButton3) { // Enter or �˻� ��ư ���� �� �˻� ���
				if (Text_field.getText().equals("")) {
					Text_area.append("�˻�� �Է����ּ���." + newline);
					Text_field.requestFocus();
				} else {
					String str = Text_field.getText(); // ������ ��ư ������ �ؽ�Ʈ �ʵ��� ���� �ҷ���
					Text_area.append(str + newline); // �ؽ�Ʈarea�� �ؽ�Ʈ �ʵ��� ���� + '\n'�� ���ļ� ���ͱ�� ����
					// Text_field.selectAll(); //�˻� �� �˻� �� �ؽ�Ʈ�� ��ü �巡�� �ȴ�.
					Text_field.requestFocus(); // �˻� ��ư ���� �� Ŀ���� �ٽ� Text_field�� ��ġ�Ѵ�.
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