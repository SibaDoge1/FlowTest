import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProductList {
	ArrayList<ProductRecord> List = new ArrayList<ProductRecord>(); //arrayList�� �̿��ߴ�. �ٸ� Ŭ���������� ����ϱ� ���� private�� ���� �ʾҴ�.
	private Scanner input; 
	
	public ProductList(FileInputStream stream) { //�⺻ ������. ���Ͻ�Ʈ���� �޾ƿͼ� �Ľ��� �� List�� �����Ѵ�. 
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
	
	public boolean set(int index, String[] token, boolean isMod){ //List�� �����͸� ��°�� �����ϱ� ���� �Լ�. index�� ������ ��ġ�� ���ϰ�, isMod�� ����  �߰��������� �����Ѵ�.
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
	
	public void skipCheck(String[] token, boolean isMod) throws skipException { //���ܿ�Ҹ� Ȯ���ϴ� �Լ�. ���ܸ� ������. isMod�� ���� Mod�� �����̸� ID�ߺ��˻縦 �����Ѵ�.
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
	
	class skipException extends RuntimeException{ //skip�� ���� ���θ��� ����Ŭ����
		private static final long serialVersionUID = 1L;
		skipException(String msg){
			super(msg);
		}
	}
}
