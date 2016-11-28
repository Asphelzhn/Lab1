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
    	//����Ƿ�Ϊ������
    	if (s.matches("!simplify [a-zA-Z]+=\\d+")){
    		read=1;
    		return "��";
    		
    	}
    	if (s.matches("!simplify")) {
    		read=1;
    		return "��";
    		
    		}
    	//����Ƿ�Ϊ������
    	if (s.matches("!d/d[a-zA-Z]+")) {
    		Pattern var = Pattern.compile("!d/d([a-zA-Z]+)");
    		Matcher matcherVar = var.matcher(s);
    		matcherVar.find();
    		read=1;
    		return "��";
    		
    	}
    	if(read==0){
    		return "�Ƿ�����";
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
    	//���polynomial�л�û�ж���ʽ��ֱ�ӷ��أ����������
    	if (this.polynomial == null)
    	{
    		System.out.println("��û�б��ʽ");
    		return true;
    	}
    	
    	//����Ƿ�Ϊ������
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
    	//����Ƿ�Ϊ������
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
    		System.out.println("�Ƿ�����");
    	}
    	return true;
    }
    public String Standardization(String s)//�Ա��ʽ���й淶����ɾ���ո�֧��ƥ��^����ʾa�Ķ��ٴη�
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
    		//System.out.println("�滻��"+s);
    	}
    	return s;
    }
    
    public Boolean check(String s)//�ж��Ǳ��ʽ��������
    {
    	Pattern polyPattern = Pattern.compile("(([0-9]+|[a-zA-Z]+)(\\*|\\+))*([0-9]+|[a-zA-Z]+)");//
    	Matcher poly = polyPattern.matcher(s);
    	return poly.matches();
    }
    public void printstr(String willprint)//����ַ���
    {
    	System.out.println(willprint);
    }
}
