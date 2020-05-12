/**
* Homework 5 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * CompareByCaption: Compares by captions in ascending alphabetical order, and if identical, by rating in descending order
 */
public class CompareByCaption extends PhotographComparator {
    /**
     * compares two photograph first by caption, and if same, by rating.
     * 
     * @return a positive integer if o1>o2, 0 if o1=o2, <br>
     *         or a negetive integer if o1<o2.
     */
    @Override
    public int compare(Photograph o1, Photograph o2) {
        int result;
        result = compareCaption(o1, o2);
        if (result == 0) {
            result = compareRating(o1, o2);
        }
        return result;
    }

}