import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class recordWindow extends JFrame {
	private String name;
	private JButton jbtDone = new JButton("Done");
	private JTextField jtfName = new JTextField();
	private JTextField jtfId = new JTextField();
	private JTextField jtfCate = new JTextField();
	private JTextField jtfPrice = new JTextField();
	private JTextField jtfStock = new JTextField();
	private JTextField jtfMin = new JTextField();
	private JTextField jtfMemo = new JTextField();
	private JPanel panel = new JPanel(new GridLayout(2, 7));
	private String [] token = new String [7]; //텍스트필드의 데이터를 받아 전달하기위한 변수
	
	public recordWindow(String s, ActionListener Action){ //기본 생성자. 제목과 버튼액션리스너를 인자로 받는다.
		name = s;
		panel.add(new JLabel("제품명"));
		panel.add(new JLabel("제품ID"));
		panel.add(new JLabel("카테고리"));
		panel.add(new JLabel("가격"));
		panel.add(new JLabel("재고수"));
		panel.add(new JLabel("최소재고량"));
		panel.add(new JLabel("기타 메모"));
		panel.add(jtfName);
		panel.add(jtfId);
		panel.add(jtfCate);
		panel.add(jtfPrice);
		panel.add(jtfStock);
		panel.add(jtfMin);
		panel.add(jtfMemo);
		this.setTitle(s);
		this.setLayout(new BorderLayout());
		this.add(jbtDone, BorderLayout.EAST);
		this.add(panel, BorderLayout.CENTER);
		jbtDone.addActionListener(Action);
		this.setSize(800, 100);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public recordWindow(String s, ActionListener Action, String[] token){ //스트링배열을 추가로 받는 생성자, 받은 배열을 텍스트필드에 처음부터 출력시킨다
		this(s, Action);
		jtfName.setText(token[0]);
		jtfId.setText(token[1]);
		jtfCate.setText(token[2]);
		jtfPrice.setText(token[3]);
		jtfStock.setText(token[4]);
		jtfMin.setText(token[5]);
		jtfMemo.setText(token[6]);
	}
	
	public String getName(){ //창의 이름을 리턴한다.
		return name;
	}
	
	public String[] getText(){ // 텍스트필드에 있는 데이터를 저장하여 스트링배열형태로 리턴한다
		String [] token = new String [6];
		token[0] = jtfName.getText();
		token[1] = jtfId.getText();
		token[2] = jtfPrice.getText();
		token[3] = jtfStock.getText();
		token[4] = jtfMin.getText();
		token[5] = jtfMemo.getText();
		return token;
	}
	
}
