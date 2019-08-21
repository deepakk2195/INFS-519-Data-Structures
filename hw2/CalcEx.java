/*INFS-519 homework-2 Deepak Kanuri G01070295 10/12/2017*/
import java.util.*;
import java.lang.*;
import java.io.*;

class UnderflowException extends Exception/*Stack empty exception is thrown here*/
{
public UnderflowException(){}
public UnderflowException(String msg)
{
super(msg);
}
}


class Dstack/*Dynamic array stack implementation where two stacks are implemented: character stack for infix to postfix and float stack for postfix evaluation*/
{
  public char[] data;
  public float[] datai;
  String out=new String(""); 
  int size,top,sizei,topi; 
  public Dstack()/*constructor for initialising stack*/
  {
    data = new char[30];
	datai= new float[30];
    size = 0;
	sizei=0;
	top=-1;
	topi=-1;
  }
   
  public void ensureCapacity(int minCapacity)/*a dynamic array that thst checks size of array. If full new array with more size is created*/
  {
    int oldCapacity = data.length;
    if (minCapacity > oldCapacity)
    {
      int newCapacity = (oldCapacity * 2);
      if (newCapacity < minCapacity)
        newCapacity = minCapacity;
      data = Arrays.copyOf(data, newCapacity);
    }
  }
  public boolean isUnderflow()/*checking if the character stack is empty*/
  {
    if(top==-1)
		return true;
	else
		return false;
  }
  public boolean isUnderflowi()/*checking if the float stack is empty*/
  {
    if(topi==-1)
		return true;
	else
		return false;
  }
  public void push(char element)/*pushing character into character stack*/
  {
    ensureCapacity(size + 1);
    data[++top] = element;
	size++;
  }
  public void pushi(float element)/*pushing float value into float stack*/
  {
    ensureCapacity(sizei + 1);
    datai[++topi] = element;
	sizei++;
  }
  public char pop() throws UnderflowException/*popping character from character stack*/
  {
	  if(!isUnderflow())
	  {
		   char ch=data[top];
	       size--;top--;
		   return ch;
	  } 
	   else
		   throw new UnderflowException("Empty Stack");
  }
  public float popi() throws UnderflowException/*popping float value from float stack*/
  {
	  if(!isUnderflowi())
	  {
		   float ch=datai[topi];
	       sizei--;topi--;
		   return ch;
	  } 
	   else
		   throw new UnderflowException("Empty float Stack");
  }	   
  public boolean isop(char ch)/*checking if character is an operator*/
  {
	  boolean p;
	  if(ch=='+'||ch=='-'||ch=='*'||ch=='/')
		  p=true;
	  else
		  p=false;
	  
	  return p;
  }
  public int prec(char x)/*checking precedence value of operator */
  {
	  int k=99;
	  if(x=='+'||x=='-')
		  k=1;
	  else if(x=='*'||x=='/')
		  k=2;
	  else if(x=='(')
	      k=0;
	  return k;
  }	  
  public void intopost(String in) throws UnderflowException/*converting infix expression to postfix*/
  {
	  char ch;
	  out="";
	  for (int i = 0; i < in.length(); i++)
	  {
		  
       char c = in.charAt(i);
        if(c=='(')
          push(c);
        else if(c==')')
        {
			do
			{
				ch=pop();
				if(ch!='(')
					out+=ch;
			}while(ch!='(');	
        }
        else if(isop(c))
        {
			out+=" ";
			while((!isUnderflow())&&(prec(data[top])>=prec(c)))
			{
				ch=pop();
				out=out+" "+ch;
            }	
            push(c);			
        }
        else if(!Character.isSpace(c))
            out+=c;	
		
        		
	  } 
	  while(!isUnderflow())
		out=out+" "+pop();
	
	  System.out.println("\n the postfix expression is:"+out);
	  
	 posteval(out);  
  } 	
 
  public float perform(float x,float y,char ch)/*performs the required operation based on the given operand*/
  {
	  float res=99;
	  switch(ch)
	  {
		  case '+':res=(x+y);break;
		  case '-':res=(y-x);break;
		  case '*':res=(x*y);break;
		  case '/':res=(y/x);break;
	  }		  
	  return res;
  }	   
  public void posteval(String post) throws UnderflowException/*evaluating the postfix expression*/
  {
    float x,y,z;char ch,c;
	for (int i = 0; i < post.length(); i++)
	  {
       c = post.charAt(i);
	     
	   if(isop(c))
	   {   
		 x=popi();
		 y=popi();
		 z=perform(x,y,c);
		 pushi(z);
	   }
	   else if(Character.isDigit(c))
	   {
		   x=c-'0';
		   if(Character.isDigit(post.charAt(i+1)))
		   while(Character.isDigit(post.charAt(i+1)))
								{
									 ch=post.charAt(i+1);
								       y=(ch-'0');
									    x=x*10+y;
										i++;
								}			
		   pushi(x);
	   }  
	  }
	  z=popi();
	  System.out.println("the postfix result is:"+z); 
  } 
} 

class Node/*character linked list node*/ 
{
 public char data;
 public Node next;
 
}
class Nodei/*float linked list node*/ 
{
 public float dat;
 public Nodei next;
 
}
class Lstack/*linked list stack implementation where two stacks are implemented: character stack for infix to postfix and float stack for postfix evaluation*/
{
  private Node head;
  private Nodei headi;
  String out=new String("");
  public boolean isUnderflow() /*checking if the character stack is empty*/
  {
  return (head == null);
  }
  public boolean isUnderflowi() /*checking if the float stack is empty*/
  {
  return (headi == null);
  }
  
  public void push(char data) /*pushing character into character stack*/
  {
  Node newNode = new Node();
  newNode.data = data;
  newNode.next = head;
  head = newNode;
  }
  public void pushi(float data) /*pushing float value into float stack*/
  {
  Nodei newNode = new Nodei();
  newNode.dat = data;
  newNode.next = headi;
  headi = newNode;
  }
  public char pop() throws UnderflowException/*popping character from character stack*/
  {
	  char ch;
	  if(!isUnderflow())
	  {  
           ch=head.data;
		   head = head.next;
		   return ch;
	  }	
	   else
		   throw new UnderflowException("Empty Stack");
  }
  public float popi() throws UnderflowException/*popping float value from float stack*/
  {
	  float ch;
	  if(!isUnderflowi())
	  {  
           ch=headi.dat;
		   headi = headi.next;
		   return ch;
	  }	
	   else
		   throw new UnderflowException("Empty float Stack");
  }
 
  public boolean isop(char ch)/*checking if character is an operator*/
  {
	  boolean p;
	  if(ch=='+'||ch=='-'||ch=='*'||ch=='/')
		  p=true;
	  else
		  p=false;
	  
	  return p;
  }
  public int prec(char x)/*checking precedence value of operator */
  {
	  int k=99;
	  if(x=='+'||x=='-')
		  k=1;
	  else if(x=='*'||x=='/')
		  k=2;
	  else if(x=='(')
	      k=0;
	  return k;
  }	  
  public void intopost(String in) throws UnderflowException/*converting infix expression to postfix*/
  {
	  char ch;
	  out="";
	  for (int i = 0; i < in.length(); i++)
	  {
       char c = in.charAt(i);
        if(c=='(')
          push(c);
        else if(c==')')
        {
			do
			{
				 ch=pop();
				if(ch!='(')
					out+=ch;
			}while(ch!='(');	
        }
        else if(isop(c))
        {
			out+="  ";
			while((!isUnderflow())&&(prec(head.data)>=prec(c)))
			{
				ch=pop();
				out=out+" "+ch;
            }	
			push(c);
        }
        else if(!Character.isSpace(c))
            out+=c;	
        		
	  } 
	  while(!isUnderflow())
		  out=out+" "+pop();
	  
	  System.out.println("\n the postfix expression is:"+out);
	  
	 posteval(out); 
  } 	
 public float perform(float x,float y,char ch)/*performs the required operation based on the given operand*/
  {
	  float res=99;
	  switch(ch)
	  {
		  case '+':res=(x+y);break;
		  case '-':res=(y-x);break;
		  case '*':res=(x*y);break;
		  case '/':res=(y/x);break;
	  }		  
	  return res;
  }	   
  public void posteval(String post) throws UnderflowException/*evaluating the postfix expression*/
  {
    float x,y,z;char ch,c;
	for (int i = 0; i < post.length(); i++)
	  {
       c = post.charAt(i);
	     
	   if(isop(c))
	   {   
		 x=popi();
		 y=popi();
		 z=perform(x,y,c);
		 pushi(z);
	   }
	   else if(Character.isDigit(c))
	   {
		   x=c-'0';
		   if(Character.isDigit(post.charAt(i+1)))
		   while(Character.isDigit(post.charAt(i+1)))
								{
									 ch=post.charAt(i+1);
								       y=(ch-'0');
									    x=x*10+y;
										i++;
								}			
		   pushi(x);
	   }  
	  }
	  z=popi();
	  System.out.println("\nthe postfix result is:"+z);
  } 
}	

class Errorscheck/*class to check if the given infix expression is valid or not*/
{
	public boolean ch(String in)
	{
		int i=0;
		char c = in.charAt(i);
		while(Character.isSpace(c))
		{
				i++;
				c = in.charAt(i);
		}	
		if(!Character.isDigit(c)&&c!='(')
		{
			System.out.println("\nThe infix expression should start and end either with digit or braces ");
			return false;
		}	
		int ctr=0;
		for (int k = 0; k < in.length(); k++)
		{
			c = in.charAt(k);
			
			if(!Character.isDigit(c)&&c!='+'&&c!='-'&&c!='*'&&c!='/'&&c!='('&&c!=')'&&!Character.isSpace(c))
			{
				System.out.println("\nThe infix expression contains an invalid character");
				return false;
			}	
			if(c=='(')
				ctr++;
			if(c==')')
				ctr--;
		}
        if(ctr!=0)
		{	
	       System.out.println("\nbraces mismatch");
           return false;
        }
	    return true;
	}	
}
class CalcEx
{
 public static void main(String args[]) throws Exception/*opening and processing the input file twice for linked list and dynamic array stack*/
 {
	 Lstack l=new Lstack();
	 Dstack d=new Dstack();
	 /*String inputsText="3*     (6 - 11)";
	 l.intopost(inputsText);*/
	 Errorscheck check=new Errorscheck();
	 BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String inputsText;
		System.out.println("\n\nSTART OF DYNAMIC ARRAY STACK IMPLEMENTATION\n");
        while ((inputsText = br.readLine()) != null)
		{
			if(!inputsText.isEmpty())
			{	
		      System.out.println("\n\n the infixfix expression is:"+inputsText);
			if(check.ch(inputsText))
			  d.intopost(inputsText);
		    }
		}
		br = new BufferedReader(new FileReader(args[0]));
        System.out.println("\n\nSTART OF LINKED LIST STACK IMPLEMENTATION\n");
        while ((inputsText = br.readLine()) != null)
		{	
	        if(!inputsText.isEmpty())
			{	
		     System.out.println("\n\n the infixfix expression is:"+inputsText);
			if(check.ch(inputsText))
			l.intopost(inputsText);
		    }
		}
 }	
}