package Lab1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simplify {
	 private String willsim;
	 public String simplify()//���أ�û���������ֵ
	    {   	
	    	return this.willsim;
	    }
	 public String simplify(String s)
	    {
		 	
			//��ȡvariable������
	    	Pattern simp = Pattern.compile("!simplify ([a-zA-Z]+)=(\\d+)");
	    	Matcher matchesimp = simp.matcher(s);
	    	matchesimp.find();
			String variable = matchesimp.group(1);
			String value = matchesimp.group(2);
			
			//��polynomial�еĸ�variable,�滻��value
			Pattern varPattern = Pattern.compile(variable);
			Matcher findvar = varPattern.matcher(this.willsim);
			if (findvar.find()) {
				//�����ҵ����ַ��������滻�������ӡ������Ϣ
				String newStr = new String();
				newStr = this.willsim.replace(variable, value);
	    		
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
						return multisome(newStr);
					}
					else if(matchersomel.find())//���������ʽ����ĸ����
					{
						return calculatesome(newStr);
					}
					else return newStr;
//					
				} else {
					return calculate(newStr);
				}
			} else {
				return "Error, no variable";
			}
	    }
	 private String multisome(String equation)//�������ʽ�ﲿ����ʽ�ͼӺ�����
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
				return newStr3;
	    		
	    }

	   public String calculatesome(String equation)
	    {
	    	Pattern item = Pattern.compile("(\\d\\*)+\\d");
	    	Matcher matcherItem = item.matcher(equation);
	    	while (matcherItem.find()) {
	    		int itemValue = 1;
	    		Pattern num = Pattern.compile("\\d");
	    		Matcher matcherNum = num.matcher(matcherItem.group(0));
	    		while (matcherNum.find()) {
	    			itemValue *= Integer.parseInt(matcherNum.group(0));//���ַ�������Ϊ����
	    		}
	    		equation=equation.replace(matcherItem.group(0), ""+itemValue);
	    		matcherItem = item.matcher(equation);
	    	}
	    	System.out.println(equation);
	    	return equation;
	    }
	    private String calculate(String equation)//�������ʽ�����Լ�Ϊ����ʱ
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
	    	return ""+sum;
	    }
	public String getPolynomial() {
		return willsim;
	}
	public void setPolynomial(String polynomial) {
		this.willsim = polynomial;
	}
	 
}
