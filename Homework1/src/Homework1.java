/**
 * Homework1
 */
public class Homework1 {

    /**
     * Takes a string and returns its first or last character as another string
     * 
     * @param str   an input string
     * @param front whether to return the first or the character
     * @return the first character of str if front in true, or the last character of
     *         str if front is false
     */
    public String theEnd(String str, boolean front) {

        String resultStr;
        char resultChar;

        int lenInput = str.length();
        char[] inputChars = str.toCharArray();
        resultChar = front ? inputChars[0] : inputChars[lenInput - 1]; // -1 to account for 0-based index
        resultStr = Character.toString(resultChar);
        return resultStr;
    }

    /**
     * takes an array (length>3) of ints and calculated its centered average: the
     * mean of the array when 1 largest and 1 smallest values are removed.
     * 
     * @param nums an array of ints used for the calculation
     * @return the centered average of nums
     */
    public int centeredAverage(int[] nums) {
        int lenInput = nums.length;
        int effectiveCount = lenInput - 2; // since we've removed two values in calculation of the average
        int result;

        int min = nums[0], max = nums[0], sum = 0, effectiveSum = 0;
        for (int item : nums) {
            if (min < item) {
                min = item;
            }
            if (max > item) {
                max = item;
            }
            sum += item;
        }
        effectiveSum = sum - min - max;
        result = effectiveSum / effectiveCount;
        return result;
    }

    /**
     * returns the filling of a sandwhich
     * 
     * @param str an input string
     * @return a string of whatever between the first and last occurence of "bread"
     *         in str, or an empty string if there aren't two occurences
     */
    public String getSandwich(String str) {
        String bread = "bread";
        final int LENGTH_OF_BREAD = bread.length();

        int firstBread = str.indexOf(bread);
        int lastBread = str.lastIndexOf(bread);
        String fillings = "";

        if (firstBread != -1 && lastBread != firstBread) {
            int afterFirstBread = firstBread + LENGTH_OF_BREAD;
            fillings = str.substring(afterFirstBread, lastBread);
        }
        return fillings;
    }

    /**
     * round an int to nearest multiple of 10
     * 
     * @param input an int to be rounded
     * @return input rounded to the nearest multiple of 10
     */
    private int roundTo10(int input) {
        int unitDigit = input % 10;
        int inputWithoutUnitDigit = input - unitDigit;
        int result = unitDigit >= 5 ? inputWithoutUnitDigit + 10 : inputWithoutUnitDigit;
        return result;
    }

    /**
     * 
     * @param a an int
     * @param b an int
     * @param c an int
     * @return the sum of a,b,c each rounded to the nearest multiple of 10.
     */
    public int roundSum(int a, int b, int c) {
        return roundTo10(a) + roundTo10(b) + roundTo10(c);
    }

    /**
     * 
     * @param nums    the array from which pattern is looked for
     * @param pattern an ordered pattern of numbers
     * @return true if pattern appears in nums at least once, false otherwise
     */
    private boolean arrayContainsPattern(int[] nums, int[] pattern) {
        boolean result;
        boolean startedMatching;
        boolean matchingComplete = false;
        int nextIndex = 0;
        int lookingFor;
        for (int i = 0; i < nums.length; i++) {

            int current = nums[i];
            lookingFor = pattern[nextIndex];
            startedMatching = (nextIndex > 0);

            if (current == lookingFor) {
                nextIndex++;
            } else {
                nextIndex = 0;
                if (startedMatching) {
                    i--;
                }
            }

            if (nextIndex == pattern.length) {
                matchingComplete = true;
                break;
            }
        }
        result = matchingComplete;
        return result;
    }

    /**
     * 
     * @param nums
     * @return true if the pattern {1,2,3} appears in nums
     */
    public boolean array123(int[] nums) {
        return arrayContainsPattern(nums, new int[] { 1, 2, 3 });
    }

    /**
     * determines if the cigar party has the right number of cigars
     * 
     * @param cigars    how many cigars there are
     * @param isWeekend a boolean
     * @return true if 40<=cigars<=60 on weekdays, or 40<=cigars on weekends, false
     *         otherwise
     */
    public boolean cigarParty(int cigars, boolean isWeekend) {
        boolean result = cigars >= 40;
        if (isWeekend == false) {
            result = result && cigars <= 60;
        }
        return result;
    }

    /**
     * determine whether and what type of ticket is issued for car at a certain
     * speed. no ticket : speed <= 60; small ticket: 60 < speed <= 80; big ticket:
     * 80 < speed
     * 
     * @param speed      an int representing the speed of the car
     * @param isBirthday a bool, all speed limits are raised by 5 if true
     * @return type of ticket(int) issued for car at speed, 0 for no ticket, 1 for a
     *         small ticket, 2 for a big ticket.
     */
    public int caughtSpeeding(int speed, boolean isBirthday) {
        int[] speedLimits = new int[] { 60, 80 };
        int ticketType = 0;
        if (isBirthday) {
            for (int i = 0; i < speedLimits.length; i++) {
                speedLimits[i] += 5;
            }
        }
        for (int item : speedLimits) {
            if (speed > item) {
                ticketType += 1; // upgrade ticket for more serious offense
            }
        }
        return ticketType;
    }

    /**
     * 
     * @param a
     * @param b
     * @return the smaller value between a and b
     */
    private int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * 
     * @param small number of small bars available (1 kilo each)
     * @param big   number of big bars available (5 kilo each)
     * @param goal  intended kilos of chocolate to be made from small and big bars
     * @return number of small bars to use
     */
    public int makeChocolate(int small, int big, int goal) {
        int result;
        int bigBarsUsed = min(big, goal / 5);
        int smallBarsUsed = goal - bigBarsUsed * 5;
        if (smallBarsUsed > small) {
            result = -1;
        } else {
            result = smallBarsUsed;
        }
        return result;
    }

    /**
     * 
     * @param nums  the original array
     * @param count the number of even ints to be taken from nums
     * @return the first "count" numbers of even ints from nums
     */
    public int[] copyEvens(int[] nums, int count) {
        int[] result = new int[count];
        int collectedCount = 0;
        for (int item : nums) {
            if (item % 2 == 0) {
                result[collectedCount] = item;
                collectedCount++;
            }
            if (collectedCount == count) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Homework1 Test = new Homework1();
        int[] pattern = new int[] { 2, 23, 1 };
        int[] a = new int[] { 1, 2, 23, 1, 23, 4, 2, 3, 2, 1, 2, 2, 3, 1, 2, 3 };
        boolean b = Test.arrayContainsPattern(a, pattern);
        System.out.println(b);
    }
}