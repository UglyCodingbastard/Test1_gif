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
//	            //파일 객체 생성
//	            File file = new File("C:/Users/nyj/Desktop/포토폴리오 자료/프로젝트 AI/recent_record.txt");
//	            //입력 스트림 생성
//	            FileReader filereader = new FileReader(file);
//	            //입력 버퍼 생성
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
//	            //.readLine()은 끝에 개행문자를 읽지 않는다.            
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
////			new FileWriter("C:/Users/nyj/Desktop/포토폴리오 자료/프로젝트 AI/recent_record.txt", true));
////} catch (IOException e3) {
////	// TODO Auto-generated catch block
////	e3.printStackTrace();
////}
////PrintWriter pw = new PrintWriter(bw, true);
////
////
////try {
////	File text = new File("C:/Users/nyj/Desktop/포토폴리오 자료/프로젝트 AI/recent_record.txt");
////	FileReader textRead = new FileReader(text);
////	BufferedReader bfReader = new BufferedReader(textRead);
////	String line = "";
////	List<String> lineArray = new ArrayList<>();
////	while ( (line = bfReader.readLine()) != null ) {
////		// System.out.println(line); 출력
////		// 리스트 배열에 line 한줄한줄씩 추가.
////		lineArray.add(line);
////	}
////	for(int i =0; i<lineArray.size(); i++) {
////		arr=lineArray.get(i).split(","); //아이디, 이름 검색어를 배열에 저장하고 구분자로 ,를줌
////		ar.add(Text_field.getText()+"/"); //arraylist에 검색어 값을 넣고 구분자를 /f로 줌 
////		if(JPanel03.in_id.equals(arr[0])) {
////			pw.write(lineArray.get(i)+ar.get(0));
////			System.out.println(arr[0]+arr[1]+arr[2]);
////			
////			//pw.write(Text_field.getText() + "/");
////			// pw.write(JPanel03.in_name + "/검색어/\n");
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
////	//String ar="검색어 추가";
////	try {
////		File text = new File("C:/Users/nyj/Desktop/포토폴리오 자료/프로젝트 AI/recent_record.txt");
////		FileReader textRead = new FileReader(text);
////		BufferedReader bfReader = new BufferedReader(textRead);
////		String line = "";
////		List<String> lineArray = new ArrayList<String>();
////		// null 이 아닐때까지 반복한다.
////		while ( (line = bfReader.readLine()) != null ) {
////			// System.out.println(line); 출력
////			// 리스트 배열에 line 한줄한줄씩 추가.
////			lineArray.add(line);
////		}
////		for(int i=0; i<lineArray.size(); i++) {//lineArray의 사이즈 즉, 텍스트 파일에 있는 줄 수만큼
//////			if(ui.) {
//////				
//////			}
////			arr=lineArray.get(i).split("/");				
////			System.out.println(arr[0]+arr[1]);
////			//System.out.println(lineArray.get(i).split("/"));
////		}
////	} catch (FileNotFoundException e2) {
////		// 파일 없거나 이름 안맞으면 여기걸림.
////		e2.printStackTrace();
////		System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
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
////// 바이트 단위로 파일읽기
////String filePath = "C:/Users/nyj/Desktop/포토폴리오 자료/프로젝트 AI/recent_record.txt"; // 대상 파일
////FileInputStream fileStream = null; // 파일 스트림
////
////fileStream = new FileInputStream(filePath);// 파일 스트림 생성
////// 버퍼 선언
////byte[] readBuffer = new byte[fileStream.available()];
////while (fileStream.read(readBuffer) != -1) {
////}
////System.out.println(new String(readBuffer)); // 출력
////
////fileStream.close(); // 스트림 닫기
////} catch (Exception e) {
////e.getStackTrace();
////}
//
//// text 파일 읽어보자.
//// 이스케이프 문자 역슬래쉬 (\)
//// 그냥 쓰면 에러가나는데 try ~ catch 문으로 처리해서 오류쪽 부분
//// 명시해주어야 에러가 사라짐.
