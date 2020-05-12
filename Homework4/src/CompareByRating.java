/**
* Homework 4 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * CompareByRating: Compares by rating in descending order, and if identical, by captions in ascending alphabetical order.
 */
public class CompareByRating extends PhotographComparator {

    /**
     * compares two photograph first by rating, and if same, by caption.
     * 
     * @return a positive integer if o1>o2, 0 if o1=o2, or a negetive integer if
     *         o1<o2.
     */
    @Override
    public int compare(Photograph o1, Photograph o2) {
        int result;
        result = compareRating(o1, o2);
        if (result == 0) {
            result = compareCaption(o1, o2);
        }
        return result;
    }

}