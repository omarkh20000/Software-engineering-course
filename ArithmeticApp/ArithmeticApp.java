import java.util.Scanner;

public class ArithmeticApp {

	public static void main(String[] args) {
		System.out.format("Please enter expression: \n");
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		
		s=s.replaceAll("\\s+","");	// Removing all white spaces.
		String temp=s;
		s=Solve(s);
		
		int dotIndex=s.indexOf('.');	// Cutting to five digits after decimal.
		if(dotIndex>0)
		{
			s=s+"00000";
			int i=dotIndex+5;
			while(i>dotIndex && s.charAt(i)=='0')
				i--;
			if(i==dotIndex)
				i--;
			s=s.substring(0,i+1);
			
		}
			
		System.out.format("\nThe value of expression "+temp+ " is: = " + s);		
		input.close();
	}
	
	
	public static String Solve(String s)
	{
		/* A recursive method which takes an arithmetic expression as a string and solve it.
		 								*/
		
		int length=s.length();
		
		int i=0,j=0;	// This section of the code detects the first brackets that needs to be solved and recalls the method after solving it.
		while(i<length)
		{
			if(s.charAt(i)=='(')
			{
				
					j=i;
					while(j<length)	
					{
						if(s.charAt(j)==')')
							break;		
						j++;
					}
					break;
			}
			i++;			
		}
		if(i<length && s.charAt(i)=='(') {
			s=Solve(s.substring(0,i)+Solve(s.substring(i+1,j))+s.substring(j+1, length));
		return s;	
		}
			
		
		
		i=0;	// This section of the code detects the first multiplication or division operation and solve it.
		int left=0,right=0;
		double answer=0;
		while(i<length)
		{
			if(s.charAt(i)=='*' || s.charAt(i)=='/')
			{
				left=i-1;
				right=i+2;
				while(left>=0 && (s.charAt(left)!='*' && s.charAt(left)!='/' && s.charAt(left)!='+' && s.charAt(left)!='-'))
					left--;
				while(right<length && (s.charAt(right)!='*' && s.charAt(right)!='/' && s.charAt(right)!='+' && s.charAt(right)!='-'))
					right++;	
				break;
			}		
			i++;
		}	
		
		if(i<length && s.charAt(i)=='*')
			answer=Double.valueOf(s.substring(left+1,i))*Double.valueOf(s.substring(i+1,right));
		if(i<length && s.charAt(i)=='/')
			answer=Double.valueOf(s.substring(left+1,i))/Double.valueOf(s.substring(i+1,right));
		if(i<length && (s.charAt(i)=='*' || s.charAt(i)=='/'))
			{
			s=Solve(s.substring(0,left+1)   + String.valueOf(answer) + s.substring(right,length));
			return s;
			}
			
	
		i=1;
		left=0;
		right=0;
		answer=0;
		while(i<length-1)	// This section of the code detects the first addition or subtraction operation and solve it.
		{
			if((s.charAt(i)=='+' || s.charAt(i)=='-'))
			{
				left=i-1;
				right=i+2;
				while(left>0 && ((s.charAt(left)!='*' && s.charAt(left)!='/' && s.charAt(left)!='+' && s.charAt(left)!='-')
						|| (s.charAt(left-1)!='*' && s.charAt(left-1)!='/' && s.charAt(left-1)!='+' && s.charAt(left-1)!='-')))
					left--;
				while(right<length && (s.charAt(right)!='*' && s.charAt(right)!='/' && s.charAt(right)!='+' && s.charAt(right)!='-'))
					right++;	
				break;
			}		
			i++;
		}			
		
		if(i<length && s.charAt(i)=='+')
			answer=Double.valueOf(s.substring(left,i))+Double.valueOf(s.substring(i+1,right));

		if(i<length &&s.charAt(i)=='-')
			answer=Double.valueOf(s.substring(left,i))-Double.valueOf(s.substring(i+1,right));

		if(i<length && (s.charAt(i)=='+' || s.charAt(i)=='-'))
		{
			s=Solve(s.substring(0,left)   + String.valueOf(answer) + s.substring(right,length));
			return s;
		}
	
		return s;
		
	}	
	}
