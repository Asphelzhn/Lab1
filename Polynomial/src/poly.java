
import java.util.regex.*;

class Polynomial {
    private String polynomial;
    public Boolean expression(String s)
    {
    	if (check(arrytostring(s)) == true) {
    		this.polynomial = arrytostring(s);
    		System.out.println(s);
    		s=polynomial;
    		return true;
    	}
    	//���polynomial�л�û�ж���ʽ��ֱ�ӷ��أ����������
    	if (this.polynomial == null) return true;
    	
    	//����Ƿ�Ϊ������
    	if (s.matches("!simplify [a-zA-Z]+=\\d+")){
    		
    		this.simplify(s);
    	}
    	if (s.matches("!simplify")) {
    		this.simplify();
    		}
    	//����Ƿ�Ϊ������
    	if (s.matches("!d/d[a-zA-Z]+")) {
    		Pattern var = Pattern.compile("!d/d([a-zA-Z]+)");
    		Matcher matcherVar = var.matcher(s);
    		matcherVar.find();
    		derivation(matcherVar.group(1));
    	}
    	
    	return true;
    }
    private String arrytostring(String s)//@֧��ƥ��^����ʾa�Ķ��ٴη�
    {
    	Pattern polyPattern2 = Pattern.compile("([a-zA-Z]+)\\^([0-9])");
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
    		//System.out.println("�滻��"+s);
    	}
    	return s;
    }
    
    private Boolean check(String s)
    {
    	Pattern polyPattern = Pattern.compile("(([0-9]+|[a-zA-Z]+)(\\*|\\+))*([0-9]+|[a-zA-Z]+)");//@
    	Matcher poly = polyPattern.matcher(s);
    	return poly.matches();
    	
    }
    

    public boolean simplify()//���أ�û���������ֵ
    {
    	System.out.println(this.polynomial);
    	return true;
    }
    
    public boolean simplify(String s)
    {
		//��ȡvariable������
    	Pattern simp = Pattern.compile("!simplify ([a-zA-Z]+)=(\\d+)");
    	Matcher matchesimp = simp.matcher(s);
    	matchesimp.find();
		String variable = matchesimp.group(1);
		String value = matchesimp.group(2);
		
		//��polynomial�еĸ�variable,�滻��value
		Pattern varPattern = Pattern.compile(variable);
		Matcher findvar = varPattern.matcher(this.polynomial);
		if (findvar.find()) {
			//�����ҵ����ַ��������滻�������ӡ������Ϣ
			String newStr = new String();
			newStr = this.polynomial.replace(variable, value);
    		
			//�ж�newStr���Ƿ�����ĸ
			Pattern letter = Pattern.compile("[a-zA-Z]+");
			Matcher haveLetter = letter.matcher(newStr);
			
		Pattern somel = Pattern.compile("((\\d\\*)+[a-zA-Z]+)|([a-zA-Z]+(\\*\\d)+)");
		Matcher matchersomel = somel.matcher(newStr);//ƥ�䲿����ʽ����ĸ����
		
		Pattern some2 = Pattern.compile("(\\d\\*)+(\\d)\\+");
		Matcher matchersomel2 = some2.matcher(newStr);//ƥ�䲿����ʽ����ĸ����
			if (haveLetter.find()) {
				//����µ��ַ����л�����ĸ��ӡ�����������ʽ��ֵ
				
				if(matchersomel2.find())
				{
					multisome(newStr);
				}
				else if(matchersomel.find())//���������ʽ����ĸ����
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
    private void multisome(String equation)//�������ʽ�ﲿ����ʽ�ͼӺ�����
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
    			item3Value *= Integer.parseInt(matcherNum3.group(0));//���ַ�������Ϊ����
    		}
    		//System.out.println("matcherItem3.group(2)"+matcherItem3.group(2));
    		//item3Value=item3Value*Integer.parseInt(matcherItem3.group(2));
			newStr3=newStr3.replace(matcherItem3.group(0),item3Value+"+");
    	}
			System.out.println(newStr3);
    		
    }
    private void calculatesome(String equation)//�������ʽ�ﲿ����ʽ����ĸ����
    {
    	Pattern item2 = Pattern.compile("(\\d\\*)*[a-zA-Z]+|([a-zA-Z]+(\\*\\d)*)");
    	Matcher matcherItem2 = item2.matcher(equation);
    	String newStr2 = new String();
    	String vartemp=new String();
    	newStr2 = equation;
    	while (matcherItem2.find()) {
    		int item2Value = 1;
    		Pattern num = Pattern.compile("\\d");
    		Matcher matcherNum = num.matcher(matcherItem2.group(0));
    		
    		//System.out.println("group(0)="+matcherItem2.group(0)+"group(1)="+matcherItem2.group(1)+"groupcount="+matcherItem2.groupCount());
    		
    		Pattern var = Pattern.compile("([a-zA-Z]+)");
    		Matcher matchervar = var.matcher(matcherItem2.group(0));
    		if(matchervar.find());
    		vartemp=matchervar.group(0);
    		
    		
    		while (matcherNum.find()) {
    			item2Value *= Integer.parseInt(matcherNum.group(0));//���ַ�������Ϊ����
    		}
    		
			newStr2=newStr2.replace(matcherItem2.group(0),item2Value+"*"+vartemp);
    	}
			System.out.println(newStr2);
    		
    }
    private int calculate(String equation)//�������ʽ�����Լ�Ϊ����ʱ
    {
    	Pattern item = Pattern.compile("(\\d\\*)*\\d");
    	Matcher matcherItem = item.matcher(equation);
    	int sum = 0;
    	while (matcherItem.find()) {
    		int itemValue = 1;
    		Pattern num = Pattern.compile("\\d");
    		Matcher matcherNum = num.matcher(matcherItem.group(0));
    		while (matcherNum.find()) {
    			itemValue *= Integer.parseInt(matcherNum.group(0));//���ַ�������Ϊ����
    		}
    		sum += itemValue;
    	}
    	return sum;
    }
    
    public void derivation(String variable)
    {
    	Pattern judge = Pattern.compile(variable);
    	Matcher matcherjudge= judge.matcher(polynomial);
    	if(!matcherjudge.find())
    	{
    		System.out.println("Error, no variable");
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
    		int i = 0; //ͳ�Ƹñ������ֵĴ���
    		String newItem;
    		String replace ;
    		while (matcherVar.find()) ++i;
    		//�����µ�item
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
    	calculatesome(newStr);
    	}
    }
    
}
