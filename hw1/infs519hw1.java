/*INFS-519   homework-1  Deepak Kanuri  G01070295    09/28/2017*/
import java.util.*;
import java.io.*;
class Sort/*This is a class that is invoked by the Main class. This class invokes the 3 sorting algorithms.*/
{
 Sort(int[] inarr,int flag) throws IOException
 {
  int[] inarr1=Arrays.copyOf(inarr,inarr.length);
  int[] inarr2=Arrays.copyOf(inarr,inarr.length);
  

System.out.println("\nSTART OF QUICK SORT");
  Qs quick=new Qs(inarr1,flag);
  /*for(int i:inarr1)
            System.out.print(i+" ");*/
  System.out.println("END OF QUICK SORT");
  

  System.out.println("\nSTART OF INSERTION SORT");
  Is insert=new Is(inarr2,flag);
  /*for(int i:inarr2)
            System.out.print(i+" ");*/
  System.out.println("END OF INSERTION SORT"); 

  
  System.out.println("\nSTART OF BUBBLE SORT");
  Bs bubble=new Bs(inarr,flag);
  /*for(int i:inarr)
            System.out.print(i+" ");*/
  System.out.println("END OF BUBBLE SORT");      
 }
}
class Qs/*this class sorts the given array using quick sort algorithm.*/
{
  private int[] array;
  private int length;
  long start,end,duration;
  Qs(int[] arr,int flag) throws IOException/*Constructor to store the array and to time the sorting algorithm.*/
  {
   this.array=arr;
   length=arr.length;
   start=System.nanoTime();
   quick(0,length-1);
   end=System.nanoTime();
   duration=end-start;System.out.println("duration="+duration);
   if(flag==1)/*given command line arguement file is sorted and the output is stored in another text file in accordance to the sorting algorithm*/
   {
     BufferedWriter outputWriter=new BufferedWriter(new FileWriter("Qsort.txt"));
    for (int i = 0; i < array.length; i++) 
    {
     outputWriter.write(Integer.toString(array[i]));
     outputWriter.newLine();
    }
  outputWriter.flush();  
  outputWriter.close();
   }
  }
  private void quick(int l,int h)
  {
        int i = l;
        int j = h;
        int pivot = array[l+(h-l)/2];
        //System.out.println("\n\npivot="+pivot);
        while (i <= j) 
        {
            while (array[i]<pivot)
                i++;
            while (array[j]>pivot)
                j--;
            if (i<=j) 
            {
                swap(i,j);
                i++;
                j--;
            }
        }
        if(l<j)
            quick(l,j);
        if(i<h)
            quick(i,h);
  }
  private void swap(int i,int j)
  {
   int t=array[i];
       array[i]=array[j];
       array[j]=t;
  } 
}
class Bs/*this class sorts the given array using bubble sort algorithm.*/
{
  private int[] array;
  private int length;long f=0;
  long start,end,duration;
  Bs(int[] arr,int flag) throws IOException/*Constructor to store the array and to time the sorting algorithm.*/
  {
   this.array=arr;
   length=arr.length;
   start=System.nanoTime();
   bubble(); 
   end=System.nanoTime();
   duration=end-start;System.out.println("duration="+duration);
   if(flag==1)/*given command line arguement file is sorted and the output is stored in another text file in accordance to the sorting algorithm*/
   {
     BufferedWriter outputWriter=new BufferedWriter(new FileWriter("Bsort.txt"));
    for (int i = 0; i < array.length; i++) 
    {
     outputWriter.write(Integer.toString(array[i]));
     outputWriter.newLine();
    }
  outputWriter.flush();  
  outputWriter.close();
   }
  }
  private void bubble()
  {
   long inv=0;
   for(int i=0;i<length-1;i++)
    for(int j=i+1;j<length;j++)
     if(array[i]>array[j])
     {
      int temp=array[i];
          array[i]=array[j];
          array[j]=temp;
     inv=inv+1;
     }
   System.out.println("inversions="+inv);
  }
}
class  Is/*this class sorts the given array using insertion sort algorithm. The code is taken from professor's slides*/
{
  private int[] array;
  private int length;
  long start,end,duration;
  Is(int[] arr,int flag) throws IOException/*Constructor to store the array and to time the sorting algorithm.*/
  {
   this.array=arr;
   length=arr.length;
   start=System.nanoTime();
   insert(); 
   end=System.nanoTime();
   duration=end-start;System.out.println("duration="+duration);
   if(flag==1)/*given command line arguement file is sorted and the output is stored in another text file in accordance to the sorting algorithm*/
   {
     BufferedWriter outputWriter=new BufferedWriter(new FileWriter("Isort.txt"));
    for (int i = 0; i < array.length; i++) 
    {
     outputWriter.write(Integer.toString(array[i]));
     outputWriter.newLine();
    }
  outputWriter.flush();  
  outputWriter.close();
   }
  }
  private void insert()
  {
   int j;long inv=0;
   for (int i = 0; i < array.length; i++) 
   {
   int tmp=array[i];
   //j=i;
   for (j=i;j>0&&(tmp<array[j-1]); j--)
    {array[j] = array[j-1];inv++;}
   array[j] = tmp;
   }
   System.out.println("Inversions:"+inv);
  }
}


class infs519hw1/*This is the main class that reads the input files and invokes the sort algorithm. The duration of the sorts are done in the sorting code itself. There is scope to give an integer text file as command line arguements and sort it in the 3 sorting algorithms.*/
{
 public static void main(String args[]) throws IOException
 {
   int flag=0,ctr=0;
   /*int[] k={10,3,6,8,222,55,4909,54,100000,6576,345678,5,909,456,987,123};
   Sort s=new Sort(k);*/
   Scanner scanner = new Scanner(new File("A1-100-integers.txt"));
   int[] first= new int[100];
   int i = 0;
   while(scanner.hasNextInt())
    first[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 100-integers file");
   Sort s= new Sort(first,flag);
   
   scanner = new Scanner(new File("A1-1000-almost-sorted.txt"));
   int[] second= new int[1000];
   i = 0;
   while(scanner.hasNextInt())
    second[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 1000-almost-sorted file");
   s=new Sort(second,flag);

   scanner = new Scanner(new File("A1-1000-integers.txt"));
   int[] third= new int[1000];
   i = 0;
   while(scanner.hasNextInt())
    third[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 1000-integers file");
   s=new Sort(third,flag);

   scanner = new Scanner(new File("A1-10000-integers.txt"));
   int[] fourth= new int[10000];
   i = 0;
   while(scanner.hasNextInt())
    fourth[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 10000-integers file");
   s=new Sort(fourth,flag);

   scanner = new Scanner(new File("A1-100000-integers.txt"));
   int[] fifth= new int[100000];
   i = 0;
   while(scanner.hasNextInt())
    fifth[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 100000-integers file");
   s=new Sort(fifth,flag);

  if(args.length>0)/*reading the command line arguements and it can sort any size of integer file.*/
  {
   i = 0;flag=1;
   File file = new File(args[0]);
   byte[] bytes = new byte[(int) file.length()];
   FileInputStream fis = new FileInputStream(file);
   fis.read(bytes);
   fis.close();
   String[] valueStr = new String(bytes).trim().split("\\s+");
   int[] last = new int[valueStr.length];
   for ( i = 0; i < valueStr.length; i++) 
    last[i] = Integer.parseInt(valueStr[i]);
   System.out.println("\nStart of command line file");
   s=new Sort(last,flag);
  }
 }
}