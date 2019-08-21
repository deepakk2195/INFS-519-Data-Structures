/*Deepak Kanuri G010170295 assignment-4 INFS-519 11/16/2017*/
import java.util.*;
import java.lang.*;
import java.io.*;
class Node/*String linked list node*/ 
{
 public String data;
 public Node next;
}

class hashh
{
	private Node head[]=new Node[20];double lf;int c=0;
	
	int h(char c)
	{
		return((int)c%20);
		
	}

    void insert(String dat,int x)
	{
		Node t=search(dat,x);
		if(t==null)
		{
			Node newNode = new Node();
			newNode.data = dat;
			newNode.next = head[x];
			head[x] = newNode;
			System.out.println("inserted "+dat);System.out.println("load factor is"+lf/20);
			lf++;
		}
		else
			System.out.println("This element already exists"+dat);
    }
	
	void delete(String dat,int x)
	{
		Node d=search(dat,x);Node t=head[x];
		if(d!=null/*&&t!=null*/)
		{
			if(d==t)
				head[x]=null;
			else
			{
				for(;t.next!=d;t=t.next);
				t.next=d.next;
				System.out.println("deleted "+dat);
				lf--;System.out.println("load factor is"+lf/20);
			}	
		}	
		else
			System.out.println("could not find element "+dat);
	}

    Node search(String dat,int x)	
	{
		Node t=head[x];
		for(;t!=null;t=t.next)
		{
			c++;
			if(t.data.equals(dat))
				break;
		}	
	
	return t;
	}
	void display()
	{
		int i;Node t;
		for(i=0;i<20;i++)
		{
			System.out.println("\n");
			System.out.print(i+"-->");
			for(t=head[i];t!=null;t=t.next)
				System.out.print(t.data+",  ");
		}	
		System.out.println("\n\nload factor is"+lf/20);
		System.out.println("the number of collisions is"+c);
	}	
}	

class ExHash
{
	public static void main(String args[]) throws Exception
	{
		hashh b=new hashh();int x;
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String inputsText;
		while ((inputsText = br.readLine()) != null && inputsText.length()!=0)/*reading each line from input text file*/
		{	
	        String sub=inputsText.substring(2,inputsText.length()); 
			switch(inputsText.charAt(0))
			{
				case 'I':x=b.h(sub.charAt(0));
				         b.insert(sub,x);/*inserting element*/
				         break;
				case 'F':x=b.h(sub.charAt(0));
						 if(b.search(sub,x)!=null)/*searching element*/
					      System.out.println("element found "+sub);
					     else  
						  System.out.println(-1+" not found element "+sub);	  
                         break;
                case 'R':x=b.h(sub.charAt(0));
						 b.delete(sub,x);/*deleting element*/
                         break;		
			}	
		}
		System.out.println(" \n\n");
		b.display();
	}
}