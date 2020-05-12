import java.util.Comparator;
/**
* Homework 4 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * PhotographComparator
 */
public abstract class PhotographComparator implements Comparator<Photograph> {

    /**
     * compares the caption of two photographs using String.compareTo() method, <br>
     * so as to achieve ascending alphabetical order.
     */
    protected int compareCaption(Photograph o1, Photograph o2) {
        String caption1 = o1.getCaption();
        String caption2 = o2.getCaption();
        int result = caption1.compareTo(caption2);
        return result;
    }

    /**
     * compares the ratings of two photograph
     * 
     * @return 1 if o1's rating is lower than o2's rating, 0 if both are equal, and
     *         -1 is o1's rating is higher than o2's rating
     */
    protected int compareRating(Photograph o1, Photograph o2) {
        int result = 0;
        int rating1 = o1.getRating();
        int rating2 = o2.getRating();

        // result = rating2-rating1;

        if (rating1 < rating2) {
            result = 1;
        } else if (rating1 == rating2) {
            result = 0;
        } else if (rating1 > rating2) {
            result = -1;
        }
        return result;
    }

}