import java.io.*;

public class PolynomialProcess {
	public static void main(String[] args){
		Polynomial polynomial = new Polynomial();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String command = new String();
		while (true){
			//��ȡ�����, �������ظ���ȡ����
			try {  
			    command = br.readLine();         
			} catch (IOException e) {  
			    e.printStackTrace();  
			} 
			polynomial.expression(command);
			
			}
		}
	}
// this is the main class for polynomial