package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolyProcess {
	public static void main(String[] args){
		Poly polynomial = new Poly();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String command = new String();
		while (true){
			//��ȡ�����, �������ظ���ȡ����
			try {  
			    command = br.readLine();         
			} catch (IOException e) {  
			    e.printStackTrace();  
			} 
			polynomial.Myexpression(command);
			
			}
		}
}
