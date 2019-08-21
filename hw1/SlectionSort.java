class SelectionSort
{
     SelectionSort(int a[])
    {
        int n = a.length;
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min = i;
            for (int j = i+1; j < n; j++)
                if (a[j] < a[min])
                    min = j;
 
            // Swap the found minimum element with the first
            // element
            int temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
    }
 
    // Prints the array
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
 
    // Driver code to test above
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(new File("A1-100-integers.txt"));
   int[] first= new int[100];
   int i = 0;
   while(scanner.hasNextInt())
    first[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 100-integers file");
   SelectionSort s= new Sort(first);
   
   scanner = new Scanner(new File("A1-1000-almost-sorted.txt"));
   int[] second= new int[1000];
   i = 0;
   while(scanner.hasNextInt())
    second[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 1000-almost-sorted file");
   s=new Sort(second);

   scanner = new Scanner(new File("A1-1000-integers.txt"));
   int[] third= new int[1000];
   i = 0;
   while(scanner.hasNextInt())
    third[i++] = scanner.nextInt();
   System.out.println("\n\nStart of 1000-integers file");
   s=new Sort(third);

    }
} 