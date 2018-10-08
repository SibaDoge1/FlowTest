import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProductList {
	ArrayList<ProductRecord> List = new ArrayList<ProductRecord>(); //arrayList를 이용했다. 다른 클래스에서도 사용하기 위해 private를 걸지 않았다.
	private Scanner input; 
	
	public ProductList(FileInputStream stream) { //기본 생성자. 파일스트림을 받아와서 파싱한 뒤 List에 저장한다. 
			input = new Scanner(stream);
			while (input.hasNext()) {
				String line = input.nextLine();
				line = line.replaceAll("\\s", "");
				String[] token = line.split(":", 0);
				if(line.contains("//") == false && line != null){			
					set(-1, token, true);
				}
			}
	}
	
	public boolean set(int index, String[] token, boolean isMod){ //List에 데이터를 통째로 저장하기 위한 함수. index로 저장할 위치를 정하고, isMod를 통해  중간삽입인지 구별한다.
		try{
				skipCheck(token, isMod); 
				if(index == -1){
					List.add(new ProductRecord());
					List.get(List.size()-1).setRecord(token);
				}
				else if(index < List.size()){
					List.add(index, new ProductRecord());
					List.get(index).setRecord(token);
				}
				return false;
		}
		catch(skipException e) {
			JFrame error = new JFrame();
			error.add(new JLabel(e.getMessage()));
			error.setVisible(true);
			error.setSize(200, 100);
			error.setLocationRelativeTo(null);
			return true;
		}  
	}
	
	public void skipCheck(String[] token, boolean isMod) throws skipException { //예외요소를 확인하는 함수. 예외를 던진다. isMod를 통해 Mod의 목적이면 ID중복검사를 무시한다.
		for(int i = 0; i < token.length-1; i++){
			if(token[i] == ""){
				throw new skipException("Empty Line");
			}
		}
		if(!(token[1].matches("^[1-5]-[0-9]*$"))){
			throw new skipException("Wrong ID Value");
		}
		if(!token[3].matches("^[0-9]*$") || !token[4].matches("^[0-9]*$")){
			throw new skipException("Wrong Stock Value");
		}
		if(!token[2].matches("^[0-9]*$")){
			throw new skipException("Wrong Price Value");
		}
		if(!isMod){
			for(int i = 0; i < List.size(); i++){
				if(token[1].equals(List.get(i).get("id"))){
					throw new skipException("ID Conflict");
				}
			}
		}
	}
	
	class skipException extends RuntimeException{ //skip을 위해 따로만든 예외클래스
		private static final long serialVersionUID = 1L;
		skipException(String msg){
			super(msg);
		}
	}
}
