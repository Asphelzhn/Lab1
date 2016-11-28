package Lab1;
import java.util.regex.*;

public class Poly {
    private String polynomial;
    private int read=0;
    public String expression(String s)
    {
    	if (check(Standardization(s)) == true) {
    		this.polynomial = Standardization(s);
    		System.out.println(polynomial);
    		s=polynomial;
    		read=1;
    		return s;
    		
    	}
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
    	if (check(Standardization(s)) == true) {
    		this.polynomial = Standardization(s);
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
    		Simplify sim=new Simplify();
    		sim.setPolynomial(polynomial);
    		printstr(sim.simplify(s));
    	}
    	if (s.matches("!simplify")) {
    		read=1;
    		Simplify sim=new Simplify();
    		printstr(sim.simplify());
    		}
    	//检测是否为求导命令
    	if (s.matches("!d/d[a-zA-Z]+")) {
    		Pattern var = Pattern.compile("!d/d([a-zA-Z]+)");
    		Matcher matcherVar = var.matcher(s);
    		matcherVar.find();
    		read=1;
    		Derivation der=new Derivation();
    		der.setPolynomial(polynomial);
    		printstr(der.derivation(matcherVar.group(1)));
    	}
    	if(read==0){
    		System.out.println("非法输入");
    	}
    	return true;
    }
    public String Standardization(String s)//对表达式进行规范化，删除空格，支持匹配^来表示a的多少次方
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
    
    public Boolean check(String s)//判断是表达式还是命令
    {
    	Pattern polyPattern = Pattern.compile("(([0-9]+|[a-zA-Z]+)(\\*|\\+))*([0-9]+|[a-zA-Z]+)");//
    	Matcher poly = polyPattern.matcher(s);
    	return poly.matches();
    }
    public void printstr(String willprint)//输出字符串
    {
    	System.out.println(willprint);
    }
}
