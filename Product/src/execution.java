import java.io.*;

public class execution {
	
	public static void main(String[] args) throws IOException {
		File file = new File("productlist.txt");	//������ �޾ƿ´�.
		new listWindow(file); // �߽��� �Ǵ� Ŭ������ ���۽�Ų��.
	}

}
