import java.io.*;

public class execution {
	
	public static void main(String[] args) throws IOException {
		File file = new File("productlist.txt");	//파일을 받아온다.
		new listWindow(file); // 중심이 되는 클래스를 동작시킨다.
	}

}
