package Lab1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Derivation {
	private String polynomial;
	public String derivation(String variable)
    {
		polynomial="x*x+2*3*4*x*y+x+x*x*num";
//   	polynomial=arrytostring(polynomial);
    	Pattern judge = Pattern.compile(variable);
    	Matcher matcherjudge= judge.matcher(polynomial);
    	if(!matcherjudge.find())
    	{
    		return "Error, no variable";
    	}
    	else
    	{
//    	Pattern item = Pattern.compile("(.*)\\+");
//    	Matcher matcherItem = item.matcher(polynomial);
    	String newStr = new String();
    	String[]a=polynomial.split("\\+");
    	int j=0;
    	while (j<a.length) {
    		Pattern var = Pattern.compile(variable);
    		String oldItem =a[j];
    		//System.out.println("matchreItem group(1)"+matcherItem.group(1));
    		Matcher matcherVar = var.matcher(oldItem);
    		//System.out.println("group(0)"+matcherVar.group(0));
    		int i = 0; //统计该变量出现的次数
    		String newItem;
    		String replace ;
    		while (matcherVar.find()) ++i;
    		//构建新的item
    		if(i==0)
    		{
    			newItem="";
    		}
    		else if(i==1)
    		{
    			newItem=oldItem.replaceFirst("\\*"+variable,"");
    		}
    		else {
    			replace = oldItem.replaceFirst("\\*"+variable, "");
    			newItem =  i + "*" + replace;
    		}
    		
    		if(i==0)
    		{
    			newStr+=newItem;
    		}
    		else newStr += newItem + "+";
    		j++;
    	}
    	newStr = newStr.substring(0, newStr.length() - 1);
    	return new Simplify().calculatesome(newStr);
    	}
    }
	public String getPolynomial() {
		return polynomial;
	}
	public void setPolynomial(String polynomial) {
		this.polynomial = polynomial;
	}
	
}
