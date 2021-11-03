import java.util.*;

public class SumOfTwoNumbers
{
  public static void main(String[] args) throws Exception
  {
    try
    {
      if (args.length <= 0)
        throw new Exception();
    }
    catch (Exception Error)
    {
      System.out.println("Incorrect format, include target number");
      System.exit(0);
    }

    int[] array = {1, 43, 91923, 3, 0, 4, 6, 8, 1, 15, 21};
    int target = Integer.parseInt(args[0]);

    int findSumOfTwoNumbers(array, target);
    findSumOfTwoNumbersMemo(array, target);

  }

  public static int[] findSumOfTwoNumbers(int[] array, int target)
  {
    for (int i = 0; i < array.length; i++)
      for (int j = array.length - 1; j >= 0; j--)
        if ((array[i] + array[j]) == target)
          return new int[] {i, j};

    return new int[] {};
  }

  public static int[] findSumOfTwoNumbersMemo(int[] array, int target)
  {
    HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    for (int i = 0; i < array.length; i++)
    {
      int complement = target - array[i];
      if (hashMap.containsKey(complement))
        return new int[] { hashMap.get(complement), i };
      else
        hashMap.put(array[i], i);
    }

    return new int[] {};
  }
}
