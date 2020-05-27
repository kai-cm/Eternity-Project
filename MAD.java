import java.util.ArrayList;
import java.util.Scanner;

public class MAD 
{
    public static void MADfunction()
    {
        
        ArrayList<Integer> userEntries = new ArrayList<Integer>();
        System.out.println("Enter each entries belonging to the data set, simply press f when finished");
        Scanner scanner = new Scanner(System.in);
        String userEntry = new String();

        while(!userEntry.equals("f"))
        {
            System.out.print("> ");
            userEntry = scanner.nextLine();
            if(EntryIsInteger(userEntry))
            {
                userEntries.add(Integer.parseInt(userEntry));
            }
            else
            {
                if(!userEntry.equals("f"))
                    System.out.println("Please enter a numerical value, or f to finish");
            }
        }
        // Step 1: Calculate the mean.
        int sumOfEntries = 0;
        for (int entry : userEntries) {
            sumOfEntries += entry;
        }
        int mean = sumOfEntries / userEntries.size();

        // Step 2: Calculate the distance between each data point and the mean.
        for (int i = 0; i < userEntries.size(); i++) 
        {
            int distanceFromMean = Math.abs(userEntries.get(i) - mean);
            userEntries.set(i, distanceFromMean);
        }

        // Step 3: Add the distances together.
        int addedDistances = 0;
        for (int entry : userEntries) {
            addedDistances += entry;
        }

        // Step 4: Divide the sum by the number of data points.
        float mad = (float)addedDistances / userEntries.size();

        System.out.println("The Mean absolute deviation is: " + mad);
    }

    private static boolean EntryIsInteger(String userEntry)
    {
        try 
        {
            Integer.parseInt(userEntry);
            return true;
        } 
        catch (NumberFormatException nfe) 
        {
            return false;
        }
    }
}
