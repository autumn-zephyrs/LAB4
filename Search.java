import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Search{

	static int bubbleSwapCounter = 0;
	static int bubbleComparisonCounter = 0;

	static int mergeMoveCounter = 0;
	static int mergeComparisonCounter = 0;

	public static ArrayList<String> parseData(String fileName){
		ArrayList<String> words = new ArrayList<String>();
		try
		{
		    BufferedReader br = new BufferedReader(new FileReader(fileName));
		    String line;
		    while ((line = br.readLine()) != null)
			{	
			if (words.size() < 1000)
				{
					line = line.replaceAll("[^ A-Za-z]+","");
					String[] lineList = line.split(" ");
					for (String s : lineList) 
					{
						if (s.length() > 3)
						{
							words.add(s);
						}
					}
				}		
			}
			br.close();
			return words;
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", "data.txt");
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<String> bubbleSort(ArrayList<String> A)
	{
		int n = A.size(); //Amount of words in the array
		bubbleSwapCounter = 0;
		bubbleComparisonCounter = 0;

		for(int i=0; i<n-1; i++)
		{
			for(int j=0; j<n-1; j++)
			{	
				int comparison = A.get(j+1).compareTo(A.get(j)); // Check if one string is alphabetically prior to another
				bubbleComparisonCounter++; // increment number of comparisons
				if (comparison < 0) //If no, swap
				{
					String temp = A.get(j);
					A.set(j, A.get(j+1));
					A.set(j+1, temp);
					bubbleSwapCounter++; // increment number of swaps
				}
			}
		}
		return A;
	}

	public static ArrayList<String> mergeSort(ArrayList<String> A)
	{

		int n = A.size();
		int i = 0;

		ArrayList<String> left = new ArrayList<String>();
		ArrayList<String> right = new ArrayList<String>();

		if (n <= 1)
		{
			return A;
		}
		for(String s : A)
		{	
			if (i < (n/2))
			{
				left.add(s);
			}
			else 
			{
				right.add(s);
			}
			i++;
		}

		// use recursions on both lists
		left = mergeSort(left);
		right = mergeSort(right);

		// Return merged list
		return merge(right,left);
	}

	public static ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right)
	{
		ArrayList<String> result = new ArrayList<String>();
		while (!(left.isEmpty()) && !(right.isEmpty()))
		{
			mergeComparisonCounter++;
			if ((right.get(0).compareTo(left.get(0))) < 0)
			{
				mergeMoveCounter++;
				result.add(right.get(0));
				right.remove(right.get(0));
			}
			else
			{
				mergeMoveCounter++;
				result.add(left.get(0));
				left.remove(left.get(0));
			}
		}

		while (!(left.isEmpty()))
		{
			mergeMoveCounter++;
			result.add(left.get(0));
			left.remove(left.get(0));
		}

		while (!(left.isEmpty()))
		{
			mergeMoveCounter++;
			result.add(right.get(0));
			right.remove(right.get(0));
		}
		return result;
	}

	public static void main(String[] args) {
		Search program = new Search();
		ArrayList<String> wordsList = program.parseData("data.txt");
		ArrayList<String> bubbleList = program.bubbleSort(wordsList);
		ArrayList<String> mergeList = program.mergeSort(wordsList);

		for (String s : bubbleList)
		{
			System.out.print(s + " ");
		}
		System.out.println();
		System.out.println("Number of comparisons: " + program.bubbleComparisonCounter);
		System.out.println("Number of swaps: " + program.bubbleSwapCounter);
		System.out.println();

		for (String s : mergeList)
		{
			System.out.print(s + " ");
		}
		System.out.println();
		System.out.println("Number of comparisons: " + program.mergeComparisonCounter);
		System.out.println("Number of move: " + program.mergeMoveCounter);
		System.out.println();
	}		  
}

