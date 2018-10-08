public class ProductRecord {
	private String name;
	private String id;
	private String category;
	private int price;
	private int currentStock;
	private int safeStock;
	private String memo;
	
	public ProductRecord() {
	}
	
	public void setRecord(String[] token){ // record에 상품데이터들을 저장한다.
		name = token[0];
		switch ((int)token[1].charAt(0)-'0'){ // id의 첫번째 숫자를 비교해 카테고리를 지정한다.
			case 1 : category = "Food"; break;
			case 2 : category = "Office"; break;
			case 3 : category = "Misc"; break;
			case 4 : category = "Health"; break;
			case 5 : category = "Clothing"; break;
		}
		id = token[1];
		price = Integer.parseInt(token[2]);
		currentStock = Integer.parseInt(token[3]);
		safeStock = Integer.parseInt(token[4]);		
		if(token.length >=6){ // 메모의 유무를 판별하고 저장한다.
			memo = token[5];
		}
		else{
			memo = "";
		}
	}
	
	public Object get(String a){ //인자로 받는 스트링을 항목과 비교해서 일치하는 항목의 데이터를 리턴한다.
		if (a.equals("price")){
			return price;
		}
		if (a.equals("currentStock")){
			return currentStock;
		}
		if (a.equals("safeStock")){
			return safeStock;
		}
		if (a.equals("name")){
			return name;
		}
		if (a.equals("id")){
			return id;
		}
		if (a.equals("category")){
			return category;
		}
		if (a.equals("memo")){
			return memo;
		}
		else{
			return null;
		}
	}
	
	public String[] getAll(){ //record의 모든 데이터를 저장하고 스트링배열로 리턴한다.
		String[] token = new String[7];
		token[0] = name;
		token[1] = id;
		token[2] = category;
		token[3] = String.valueOf(price);
		token[4] = String.valueOf(currentStock);
		token[5] = String.valueOf(safeStock);
		token[6] = String.valueOf(memo);
		return token;
	}

}
