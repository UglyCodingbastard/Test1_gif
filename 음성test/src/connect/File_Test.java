//package connect;
//
//import java.io.*;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class File_Test {
//	 public static void main(String[] args){
//		 int count = -1;
//	        try{
//	            //���� ��ü ����
//	            File file = new File("C:/Users/nyj/Desktop/���������� �ڷ�/������Ʈ AI/recent_record.txt");
//	            //�Է� ��Ʈ�� ����
//	            FileReader filereader = new FileReader(file);
//	            //�Է� ���� ����
//	            BufferedReader bufReader = new BufferedReader(filereader);
//	            String line = "";
//	           // System.out.println(bufReader.readLine().length());
////	            for( int i=0; i<bufReader.readLine().length(); i++) {
////	            	line=bufReader.readLine();
////	            	System.out.println(line);
////	            }
//	            while((line = bufReader.readLine()) != null){
//	                count ++;
//	                if(count==2) {	                	
//	                	System.out.println(line);
//	                	break;
//	                }
//	                
//	            }
//	            //.readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.            
//	            bufReader.close();
//	        }catch (FileNotFoundException e) {
//	            // TODO: handle exception
//	        }catch(IOException e){
//	            System.out.println(e);
//	        }
//	    }
//}
//
//
//
//
////BufferedWriter bw = null;
////
////try {
////	bw = new BufferedWriter(
////			new FileWriter("C:/Users/nyj/Desktop/���������� �ڷ�/������Ʈ AI/recent_record.txt", true));
////} catch (IOException e3) {
////	// TODO Auto-generated catch block
////	e3.printStackTrace();
////}
////PrintWriter pw = new PrintWriter(bw, true);
////
////
////try {
////	File text = new File("C:/Users/nyj/Desktop/���������� �ڷ�/������Ʈ AI/recent_record.txt");
////	FileReader textRead = new FileReader(text);
////	BufferedReader bfReader = new BufferedReader(textRead);
////	String line = "";
////	List<String> lineArray = new ArrayList<>();
////	while ( (line = bfReader.readLine()) != null ) {
////		// System.out.println(line); ���
////		// ����Ʈ �迭�� line �������پ� �߰�.
////		lineArray.add(line);
////	}
////	for(int i =0; i<lineArray.size(); i++) {
////		arr=lineArray.get(i).split(","); //���̵�, �̸� �˻�� �迭�� �����ϰ� �����ڷ� ,����
////		ar.add(Text_field.getText()+"/"); //arraylist�� �˻��� ���� �ְ� �����ڸ� /f�� �� 
////		if(JPanel03.in_id.equals(arr[0])) {
////			pw.write(lineArray.get(i)+ar.get(0));
////			System.out.println(arr[0]+arr[1]+arr[2]);
////			
////			//pw.write(Text_field.getText() + "/");
////			// pw.write(JPanel03.in_name + "/�˻���/\n");
////			//System.out.println(Text_field.getText());
////			pw.close();
////		}
////	}
////}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////public static void main(String[] args){
////	
////	
////	String[] arr= new String[3];
////	//String ar="�˻��� �߰�";
////	try {
////		File text = new File("C:/Users/nyj/Desktop/���������� �ڷ�/������Ʈ AI/recent_record.txt");
////		FileReader textRead = new FileReader(text);
////		BufferedReader bfReader = new BufferedReader(textRead);
////		String line = "";
////		List<String> lineArray = new ArrayList<String>();
////		// null �� �ƴҶ����� �ݺ��Ѵ�.
////		while ( (line = bfReader.readLine()) != null ) {
////			// System.out.println(line); ���
////			// ����Ʈ �迭�� line �������پ� �߰�.
////			lineArray.add(line);
////		}
////		for(int i=0; i<lineArray.size(); i++) {//lineArray�� ������ ��, �ؽ�Ʈ ���Ͽ� �ִ� �� ����ŭ
//////			if(ui.) {
//////				
//////			}
////			arr=lineArray.get(i).split("/");				
////			System.out.println(arr[0]+arr[1]);
////			//System.out.println(lineArray.get(i).split("/"));
////		}
////	} catch (FileNotFoundException e2) {
////		// ���� ���ų� �̸� �ȸ����� ����ɸ�.
////		e2.printStackTrace();
////		System.out.println("������ ���������ʽ��ϴ�. ��θ� Ȯ�����ּ���");
////	} catch (IOException e3) {
////		e3.printStackTrace();
////	}
////	
////}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//
////try {
////// ����Ʈ ������ �����б�
////String filePath = "C:/Users/nyj/Desktop/���������� �ڷ�/������Ʈ AI/recent_record.txt"; // ��� ����
////FileInputStream fileStream = null; // ���� ��Ʈ��
////
////fileStream = new FileInputStream(filePath);// ���� ��Ʈ�� ����
////// ���� ����
////byte[] readBuffer = new byte[fileStream.available()];
////while (fileStream.read(readBuffer) != -1) {
////}
////System.out.println(new String(readBuffer)); // ���
////
////fileStream.close(); // ��Ʈ�� �ݱ�
////} catch (Exception e) {
////e.getStackTrace();
////}
//
//// text ���� �о��.
//// �̽������� ���� �������� (\)
//// �׳� ���� ���������µ� try ~ catch ������ ó���ؼ� ������ �κ�
//// ������־�� ������ �����.
