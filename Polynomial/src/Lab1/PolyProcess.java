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
			//读取并检测, 若出错，重复读取操作
			try {  
			    command = br.readLine();         
			} catch (IOException e) {  
			    e.printStackTrace();  
			} 
			polynomial.Myexpression(command);
			
			}
		}
}
