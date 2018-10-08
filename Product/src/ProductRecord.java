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
	
	public void setRecord(String[] token){ // record�� ��ǰ�����͵��� �����Ѵ�.
		name = token[0];
		switch ((int)token[1].charAt(0)-'0'){ // id�� ù��° ���ڸ� ���� ī�װ��� �����Ѵ�.
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
		if(token.length >=6){ // �޸��� ������ �Ǻ��ϰ� �����Ѵ�.
			memo = token[5];
		}
		else{
			memo = "";
		}
	}
	
	public Object get(String a){ //���ڷ� �޴� ��Ʈ���� �׸�� ���ؼ� ��ġ�ϴ� �׸��� �����͸� �����Ѵ�.
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
	
	public String[] getAll(){ //record�� ��� �����͸� �����ϰ� ��Ʈ���迭�� �����Ѵ�.
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
