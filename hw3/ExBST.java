/*Deepak Kanuri G010170295 assignment-3 INFS-519 11/2/2017*/
import java.util.*;
import java.lang.*;
import java.io.*;

class Node /*node containing data,height and left and right children.*/
{
        int key,height;
        Node left, right;
 
        public Node(int ele) 
		{
            key = ele;
            left = right = null;
			height=0;
        }
}
class Bst
{
	Node root;
	int tr=0;
 
    Bst() 
	{ 
        root = null; 
    }
	void insert(int key) {/*insert method that checks if the element to be inserted is present or not other wise calls the reccursive insert function*/
		if(!search(key))
       root = insertR(root, key);
        else  
		 System.out.println("element "+key+" already present");	
    }
     
    private int height(Node t )/*finding height of tree*/
     {
         return t == null ? -1 : t.height;
     }
   
     private int max(int lhs, int rhs)/*finding maximum of two elements*/
     {
         return lhs > rhs ? lhs : rhs;
     }
     
     private Node insertR(Node t,int x)/*reccursive insert method*/
     {
         if (t == null)
		 {
             t = new Node(x);
			 System.out.println(t.key+" inserted");
		 }	 
         else if (x < t.key)
         {
             t.left = insertR(t.left,x);
             if( height( t.left ) - height( t.right ) == 2 )/*checking if balancing is needed*/
                 if( x < t.left.key )
                     t = rotateWithLeftChild( t );/*balancing with left child in left part*/
                 else
                     t = doubleWithLeftChild( t );/*balancing with right child in left part using double rotation*/
         }
         else if( x > t.key )
         {
             t.right = insertR(t.right,x);
             if( height( t.right ) - height( t.left ) == 2 )/*checking if balancing is needed*/
                 if( x > t.right.key)
                     t = rotateWithRightChild( t );/*balancing with right child in right part*/
                 else
                     t = doubleWithRightChild( t );/*balancing with left child in right part using double rotation*/
         }
 
         t.height = max( height( t.left ), height( t.right ) ) + 1;/*updating height of nodes*/
         return t;
     }
       
     private Node rotateWithLeftChild(Node k2)/*balancing with left child in left part*/
     {
         Node k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         k2.height = max( height( k2.left ), height( k2.right ) ) + 1;/*updating height of nodes*/
         k1.height = max( height( k1.left ), k2.height ) + 1;/*updating height of nodes*/
         return k1;
     }
 

     private Node rotateWithRightChild(Node k1)/*balancing with right child in right part*/
     {
         Node k2 = k1.right;
         k1.right = k2.left;
         k2.left = k1;
         k1.height = max( height( k1.left ), height( k1.right ) ) + 1;/*updating height of nodes*/
         k2.height = max( height( k2.right ), k1.height ) + 1;/*updating height of nodes*/
         return k2;
     }
 
     private Node doubleWithLeftChild(Node k3)/*balancing with right child in left part using double rotation*/
     {
         k3.left = rotateWithRightChild( k3.left );
         return rotateWithLeftChild( k3 );
     }
           
     private Node doubleWithRightChild(Node k1)/*balancing with left child in right part using double rotation*/
     {
         k1.right = rotateWithLeftChild( k1.right );
         return rotateWithRightChild( k1 );
     }    
	
	public boolean search(int val)/*search method calling reccursive search method*/
    {
         return searchR(root, val);
    }
	private boolean searchR(Node r, int val)/*reccursive search method*/
    {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.key;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
                 found = true;
                 break;
             }
             found = searchR(r, val);
         }
         return found;
    }
	public void delete(int k)/*deleting specified element*/
     {
         if (root==null)
             System.out.println("Tree Empty");
         else if (search(k) == false)
             System.out.println("Sorry "+ k +" is not present");
         else
         {
             root = deleteR(root, k);/*calling reccursive delete method*/
             System.out.println(k+ " deleted from the tree");
         }
     }
	 
     private Node deleteR(Node root, int k)/*reccursive delete function*/
     {
         Node p, p2, n;
			if (root.key == k)/*if element found*/
         {
             Node lt, rt;
             lt = root.left;
             rt = root.right;
             if (lt == null && rt == null)/*if no childre return null*/
                 return null;
             else if (lt == null)/*return right child if present*/
             {
                 p = rt;
                 return p;
             }
             else if (rt == null)/*return left child if present*/
             {
                 p = lt;
                 return p;
             }
             else/*returning the  right child and the least element in the right child's left child is assigned with the left child of the removing element*/
             {
                 p2 = rt;
                 p = rt;
                 while (p.left != null)
                     p = p.left;
                 p.left=lt;
                 return p2;
             }
         }
         if (k < root.key)/*finding element in left part*/
         {
             n = deleteR(root.left, k);
             root.left=n;
         }
         else/*finding element in right part*/
         {
             n = deleteR(root.right, k);
             root.right=n;             
         }
         return root;
     }
	 
	 void prin()/*printing tree horizontally*/
	 {
		 System.out.println("");System.out.println("height of tree is: "+root.height);
		 prinR(root,0);
		 
	 }

	void prinR(Node root,int space)/*reccursive printing function*/
	{
		if(root==null)
			return;
		space+=5;
		prinR(root.right,space);
		System.out.println();
		for(int i=5;i<space;i++)
			System.out.print(" ");
		System.out.println(root.key);
		
		prinR(root.left,space);
	}		
	public int countNodes()/*counting number of nodes*/
     {
         return countNodesR(root);
		 
     }
     private int countNodesR(Node r)/*reccursive method of counting nodes*/
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodesR(r.left);
             l += countNodesR(r.right);
             return l;
         }
     }
	public void makeEmpty()/*making the tree empty*/
     {
		 System.out.println("end of processing line");
         root = null;
		 tr++;
     }
	
}

class ExBST
{
	public static void main(String args[]) throws Exception
	{
		Bst b=new Bst();int disp=0;
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String inputsText;
		while ((inputsText = br.readLine()) != null && inputsText.length()!=0)/*reading each line from input text file*/
		{	
	        int x=0,y=0;
	        if(!(inputsText.charAt(0)=='E'||inputsText.charAt(0)=='D'))
			{	
			  int i=2;
              while(i<inputsText.length()&&i<=5)
              {				  
                if (inputsText.charAt(i)>='0'&&inputsText.charAt(i)<='9') 
			    {   y=inputsText.charAt(i)-'0';
				    x=x*10+y;
			    }
				i++;
			  }	
            }			
			switch(inputsText.charAt(0))
			{
				case 'I':b.insert(x);/*inserting element*/
				         break;
				case 'F':if(b.search(x))/*searching element*/
					      System.out.println("element"+x+"found");
					     else  
						  System.out.println(-1);	  
                         break;
                case 'R':b.delete(x);/*deleting element*/
                         break;
                case 'D':b.prin();disp++;System.out.println("number of nodes is: "+b.countNodes()+"\n\n");/*displaying tree*/
                         break;
                case 'E':b.makeEmpty();/*emptying tree*/
				         System.out.println("\n\n Start of new tree");
                         break;				
			}	
		}
		System.out.println("end of processing line");
		b.tr++;
		System.out.println("\n\n Number of trees built:"+b.tr+" Number of trees displayed:"+disp);
	}	
}	