import java.awt.*;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.*;

public class listWindow extends JFrame  { //메인 창으로, 프로그램 구동의 기틀이 되는 클래스이다.
	private JButton jbtAdd = new JButton("Add");
	private JButton jbtDel = new JButton("Delete");
	private JButton jbtMod = new JButton("Modify");
	private JButton jbtSave = new JButton("SaveFile");
	private JPanel panel = new JPanel(new GridLayout(4, 1));
	private String [] name = {"제품명", "제품ID", "카테고리", "가격", "재고수", "최소재고량", "기타 메모"};  //table을 만들기 위한 항목
	private String [][] contents = {
	};
	private DefaultTableModel model = new DefaultTableModel(contents, name); //DefaultTableModel을 사용
	private JTable table = new JTable(model);
	private JScrollPane Spane = new JScrollPane(table);
	private String [] token = new String[6]; // 데이터를 전달하기 위한 목적의 변수
	private ProductList pList; //프로그램상에 만들어지는 상품 리스트이다.
	private File file;
	private BufferedOutputStream out; //메모리상의 안전과 속도를 위해 Buffered를 사용
	private recordWindow recordWin;
	private int i;
	
	public listWindow(File _file) throws IOException{ //파일을 받는 생성자, 구분을 쉽게하기 위해 2개의 생성자를 두었다.
		this();
		file = _file;
		if(!file.exists()){ // 파일이 없으면 직접 만들고 기본적인 내용을 입력해둔다.
			out = new BufferedOutputStream(new FileOutputStream(file));
            String data = "// Product List \r\n// Format:: Product Name:Product ID:Value:Current Stock:Safe Stock Level \r\n// Category Number Food:1 , Office:2 , Misc.:3 , Health:4, Clothing:5";
            out.write(data.getBytes());
            out.flush();
            out.close();
		}
		FileInputStream in = new FileInputStream(file); //인풋스트림에 파일을 입력한다.
		pList = new ProductList(in); // List생성
		for(int i = 0; i < pList.List.size(); i++){ // table에 List의 데이터를 입력한다.
			model.addRow(pList.List.get(i).getAll());
		}
		in.close();
	}
	
	public listWindow(){ // gui를 설정하는 생성자, 구분을 쉽게하기 위해 2개의 생성자를 두었다.
		panel.add(jbtAdd);
		panel.add(jbtDel);
		panel.add(jbtMod);
		panel.add(jbtSave);
		this.setTitle("Product List");
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.EAST);
		this.add(Spane, BorderLayout.CENTER);
		jbtAdd.addActionListener(new clickAction());
		jbtDel.addActionListener(new clickAction());
		jbtMod.addActionListener(new clickAction());
		jbtSave.addActionListener(new clickAction());
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void refreshTable(){ //table을 새로고침 하기 위한 함수
		model.setRowCount(0);
		for(int i = 0; i < pList.List.size(); i++){
			model.addRow(pList.List.get(i).getAll());
		}
		model.fireTableDataChanged();
	}
	
	class clickAction implements ActionListener{ //모든 창에서 나타나는 모든 버튼에 쓰이는 액션리스너이다. 모든 버튼의 액션이 프로그램 전반을 건드리기 때문에 중심이 되는 클래스에 몰아넣었다.
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbtAdd){ //Add버튼이 눌릴 때
				recordWin = new recordWindow("Add", this); //Add를 위한 목적의 창을 띄운다.
			}
			else if (e.getSource() == jbtDel){//Delete버튼이 눌릴 때
				if(table.getSelectedRow() != -1){ // 선택된 행을 삭제한다.
					pList.List.remove(table.getSelectedRow());
					refreshTable();
				}
			}
			else if (e.getSource() == jbtMod){//Modify버튼이 눌릴 때
				i = table.getSelectedRow();
				token =pList.List.get(i).getAll();
				recordWin = new recordWindow("Modify", this, token); //선택된 행의 데이터를 전달하고, Mod를 위한 목적의 창을 띄운다.
				
			}
			else if (e.getSource() == jbtSave){//SaveFile 버튼이 눌릴 때
				try { //기본적인 안내문을 먼저 출력하고, List의 모든 데이터를 체계적으로 출력한다.
					out = new BufferedOutputStream(new FileOutputStream(file));
					String data = "// Product List \r\n// Format:: Product Name:Product ID:Value:Current Stock:Safe Stock Level \r\n// Category Number Food:1 , Office:2 , Misc.:3 , Health:4, Clothing:5 \r\n";
					out.write(data.getBytes());
					out.flush();
					for(int i = 0; i < pList.List.size(); i++ ){	//List의 데이터를 받아 일정한 형태로 출력
						String[] token = pList.List.get(i).getAll(); 
						String s = token[0]+":"+token[1]+":"+token[3]+":"+token[4]+":"+token[5]+":"+token[6]+"\r\n";
						out.write(s.getBytes());
						out.flush();
					} 
					out.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			else if (recordWin.getName().equals("Add")){ //띄운 Add창에서 Done 버튼이 눌릴 때
				if(!pList.set(-1, recordWin.getText(), false)){ recordWin.dispose(); }
				refreshTable();
			}
			else if (recordWin.getName().equals("Modify")){ //띄운 Mod창에서 Done 버튼이 눌릴 때
				if(!pList.set(i, recordWin.getText(), true)){ 
					recordWin.dispose(); 
					pList.List.remove(i+1); 
				}
				refreshTable();
			}
		}
	}
	

}
