package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;

class poly {
    private String polynomial;
    private int read=0;
    public String expression(String s)
    {
    	if (check(arrytostring(s)) == true) {
    		this.polynomial = arrytostring(s);
    		System.out.println(polynomial);
    		s=polynomial;
    		read=1;
    		return s;
    		
    	}
    	//如果polynomial中还没有多项式，直接返回，不分析命令。
//    	if (this.polynomial == null)
//    	{
//    		return "还没有表达式";
//    		
//    	}
    	
    	//检测是否为简化命令
    	if (s.matches("!simplify [a-zA-Z]+=\\d+")){
    		read=1;
    		return "简化";
    		
    	}
    	if (s.matches("!simplify")) {
    		read=1;
    		return "简化";
    		
    		}
    	//检测是否为求导命令
    	if (s.matches("!d/d[a-zA-Z]+")) {
    		Pattern var = Pattern.compile("!d/d([a-zA-Z]+)");
    		Matcher matcherVar = var.matcher(s);
    		matcherVar.find();
    		read=1;
    		return "求导";
    		
    	}
    	if(read==0){
    		return "非法输入";
    	}
    	return "Yes";
    }
    public Boolean Myexpression(String s)
    {
    	if (check(arrytostring(s)) == true) {
    		this.polynomial = arrytostring(s);
    		System.out.println(polynomial);
    		s=polynomial;
    		read=1;
    		return true;
    	}
    	//如果polynomial中还没有多项式，直接返回，不分析命令。
    	if (this.polynomial == null)
    	{
    		System.out.println("还没有表达式");
    		return true;
    	}
    	
    	//检测是否为简化命令
    	if (s.matches("!simplify [a-zA-Z]+=\\d+")){
    		read=1;
    		System.out.println("简化");
    		this.simplify(s);
    	}
    	if (s.matches("!simplify")) {
    		read=1;
    		System.out.println("简化");
    		this.simplify();
    		}
    	//检测是否为求导命令
    	if (s.matches("!d/d[a-zA-Z]+")) {
    		Pattern var = Pattern.compile("!d/d([a-zA-Z]+)");
    		Matcher matcherVar = var.matcher(s);
    		matcherVar.find();
    		read=1;
    		System.out.println("求导");
    		derivation(matcherVar.group(1));
    	}
    	if(read==0){
    		System.out.println("非法输入");
    	}
    	return true;
    }
    private String arrytostring(String s)//@支持匹配^来表示a的多少次方
    {	
    	while(s.contains(" "))
    	{
    	s=s.replace(" ", "");
    	}
    	while(s.contains("	"))
    	{
    		s=s.replace("	", "");
    	}
    	Pattern polyPattern2 = Pattern.compile("([a-zA-Z]+)\\^([0-9]+)");
    	Matcher poly2 = polyPattern2.matcher(s);
    	int count=0;
    	String varcount=new String();
    	if(poly2.find())
    	{
    		count=Integer.parseInt(poly2.group(2));
    		//System.out.println("group0"+poly2.group(0)+"group1"+poly2.group(1)+"group2"+poly2.group(2));
    		for(int i=0;i<count-1;i++)
    		{
    			varcount=varcount+poly2.group(1)+"*";
    		}
    		varcount+=poly2.group(1);
    		s=s.replace(poly2.group(0),varcount);
    		//System.out.println("替换后："+s);
    	}
    	return s;
    }
    
    private Boolean check(String s)
    {
    	Pattern polyPattern = Pattern.compile("(([0-9]+|[a-zA-Z]+)(\\*|\\+))*([0-9]+|[a-zA-Z]+)");//
    	Matcher poly = polyPattern.matcher(s);
    	return poly.matches();
    	
    }
    

    public boolean simplify()//重载，没有输入变量值
    {
    	System.out.println(this.polynomial);
    	return true;
    }
    
    public boolean simplify(String s)
    {
		//获取variable和数字
    	Pattern simp = Pattern.compile("!simplify ([a-zA-Z]+)=(\\d+)");
    	Matcher matchesimp = simp.matcher(s);
    	matchesimp.find();
		String variable = matchesimp.group(1);
		String value = matchesimp.group(2);
		
		//将polynomial中的该variable,替换成value
		Pattern varPattern = Pattern.compile(variable);
		Matcher findvar = varPattern.matcher(this.polynomial);
		if (findvar.find()) {
			//若查找到该字符，进行替换，否则打印错误信息
			String newStr = new String();
			newStr = this.polynomial.replace(variable, value);
    		
			//判断newStr中是否还有字母
			Pattern letter = Pattern.compile("[a-zA-Z]+");
			Matcher haveLetter = letter.matcher(newStr);
			
		Pattern somel = Pattern.compile("((\\d\\*)+[a-zA-Z]+)|([a-zA-Z]+(\\*\\d)+)");
		Matcher matchersomel = somel.matcher(newStr);//匹配部分算式和字母相连
		
		Pattern some2 = Pattern.compile("(\\d\\*)+(\\d)\\+");
		Matcher matchersomel2 = some2.matcher(newStr);//匹配部分算式和字母相连
			if (haveLetter.find()) {
				//如果新的字符串中还有字母打印，否则计算算式的值
				
				if(matchersomel2.find())
				{
					multisome(newStr);
				}
				else if(matchersomel.find())//如果部分算式和字母相连
				{
					calculatesome(newStr);
				}
				else System.out.println(newStr);
//				
			} else {
				System.out.println(calculate(newStr));
			}
			return true;
		} else {
			System.out.println("Error, no variable");
			return false;
		}
    }
    private void multisome(String equation)//如果多项式里部分算式和加号相连
    {
    	Pattern item3 = Pattern.compile("(\\d\\*)+(\\d)\\+");
		Matcher matcherItem3 = item3.matcher(equation);
		String newStr3 = new String();
    	newStr3 = equation;
    	while (matcherItem3.find()) {
    		int item3Value = 1;
    		Pattern num3 = Pattern.compile("\\d");
    		Matcher matcherNum3 = num3.matcher(matcherItem3.group(0));
    		//System.out.println("group(0)="+matcherItem3.group(0)+"group(1)="+matcherItem3.group(1)+"groupcount="+matcherItem3.groupCount());
    		while (matcherNum3.find()) {
    			item3Value *= Integer.parseInt(matcherNum3.group(0));//将字符串解析为整数
    		}
    		//System.out.println("matcherItem3.group(2)"+matcherItem3.group(2));
    		//item3Value=item3Value*Integer.parseInt(matcherItem3.group(2));
			newStr3=newStr3.replace(matcherItem3.group(0),item3Value+"+");
    	}
			System.out.println(newStr3);
    		
    }
//    private void calculatesome(String equation)//如果多项式里部分算式和字母相连
//    {
//    	Pattern item2 = Pattern.compile("(\\d\\*)*[a-zA-Z]+|([a-zA-Z]+(\\*\\d)*)");
//    	Matcher matcherItem2 = item2.matcher(equation);
//    	String newStr2 = new String();
//    	String vartemp=new String();
//    	newStr2 = equation;
//    	while (matcherItem2.find()) {
//    		int item2Value = 1;
//    		Pattern num = Pattern.compile("\\d");
//    		Matcher matcherNum = num.matcher(matcherItem2.group(0));
//    		
//    		//System.out.println("group(0)="+matcherItem2.group(0)+"group(1)="+matcherItem2.group(1)+"groupcount="+matcherItem2.groupCount());
//    		
//    		Pattern var = Pattern.compile("([a-zA-Z]+)");
//    		Matcher matchervar = var.matcher(matcherItem2.group(0));
//    		if(matchervar.find());
//    		vartemp=matchervar.group(0);
//    		
//    		
//    		while (matcherNum.find()) {
//    			item2Value *= Integer.parseInt(matcherNum.group(0));//将字符串解析为整数
//    		}
//    		
//			newStr2=newStr2.replace(matcherItem2.group(0),item2Value+"*"+vartemp);
//    	}
//			System.out.println(newStr2);
//    		
//    }
    private String calculatesome(String equation)
    {
    	Pattern item = Pattern.compile("(\\d\\*)+\\d");
    	Matcher matcherItem = item.matcher(equation);
    	while (matcherItem.find()) {
    		int itemValue = 1;
    		Pattern num = Pattern.compile("\\d");
    		Matcher matcherNum = num.matcher(matcherItem.group(0));
    		while (matcherNum.find()) {
    			itemValue *= Integer.parseInt(matcherNum.group(0));//将字符串解析为整数
    		}
    		equation=equation.replace(matcherItem.group(0), ""+itemValue);
    		matcherItem = item.matcher(equation);
    	}
    	System.out.println(equation);
    	return equation;
    }
    private int calculate(String equation)//如果多项式都可以简化为数字时
    {
    	Pattern item = Pattern.compile("(\\d\\*)*\\d");
    	Matcher matcherItem = item.matcher(equation);
    	int sum = 0;
    	while (matcherItem.find()) {
    		int itemValue = 1;
    		Pattern num = Pattern.compile("\\d");
    		Matcher matcherNum = num.matcher(matcherItem.group(0));
    		while (matcherNum.find()) {
    			itemValue *= Integer.parseInt(matcherNum.group(0));//将字符串解析为整数
    		}
    		sum += itemValue;
    	}
    	return sum;
    }
    
    public String derivation(String variable)
    {
    	polynomial="x*x+2*3*4*x*y+x+x*x*num";
//    	polynomial=arrytostring(polynomial);
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
    	return calculatesome(newStr);
    	}
    }
    public static void main(String[] args){
		poly polynomial = new poly();
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
