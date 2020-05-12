/**
* Homework 4 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * Photograph
 */
public class Photograph implements Comparable<Photograph> {

    /** the caption of the photo */
    private String caption;

    /** the filename of the photo */
    private final String filename;

    /** the date on which the photo was taken, in the format YYYY-MM-DD */
    private final String dateTaken;

    /** the rating of a photo, strictly from 0 to 5 */
    private int rating;

    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        this.dateTaken = "9999-99-99";
        this.rating = 0;
    }

    /**
     * @param filename  the filename of the photo
     * @param caption   the caption of the photo
     * @param dateTaken a date in format "YYYY-MM-DD"
     * @param rating    a rating from 0 to 5 inclusively
     */
    public Photograph(String filename, String caption, String dateTaken, int rating) {
        this.caption = caption;
        this.filename = filename;
        this.dateTaken = dateTaken;
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return (this.caption + "---" + this.filename).hashCode();
    }

    public String getCaption() {
        return this.caption;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getDateTaken() {
        return this.dateTaken;
    }

    public int getRating() {
        return this.rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        if (0 <= rating && rating <= 5) {
            this.rating = rating;
        }
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return true if the captions and filenames of both photos are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Photograph otherPhotograph = (Photograph) obj;
        if (this.getCaption() == otherPhotograph.getCaption() && this.getFilename() == otherPhotograph.getFilename()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Photograph with filename: " + this.getFilename() + " and caption: " + this.getCaption();
    }

    /**
     * compares to photograph by dateTaken, and if taken on the same day, by caption
     */
    @Override
    public int compareTo(Photograph other) {
        int result;
        result = this.compareDateTakenTo(other);
        if (result == 0) { // if this and other taken on the same day
            result = this.captionBiggerThan(other);
        }
        return result;
    }

    /**
     * compares the dateTaken of this and other
     * 
     * @return a negative int is this is taken earlier than other (smaller date), 0
     *         if equal, and a positive int if this is taken later than other.
     */
    private int compareDateTakenTo(Photograph other) {
        int result;
        String thisDate = this.getDateTaken();
        String otherDate = other.getDateTaken();
        result = thisDate.compareTo(otherDate);
        return result;
    }

    /**
     * compares the caption of this and other using String.compareTo()
     */
    private int captionBiggerThan(Photograph other) {
        return this.getCaption().compareTo(other.getCaption());
    }

    /**
     * for main method testing
     * 
     * @param args
     */
    public static void main(String[] args) {

        // String caption1 = "Hello";
        // String filename1 = "Alpha";
        // Photograph testPhotograph1 = new Photograph(caption1, filename1);
        // String caption2 = "Hello";
        // String filename2 = "Alpha";
        // Photograph testPhotograph2 = new Photograph(caption2, filename2);

        // System.out.println(testPhotograph1);
        // System.out.println(testPhotograph1.equals(testPhotograph1));
        // System.out.println(testPhotograph1.equals(testPhotograph2));

        // Photograph testPhotograph3 = new Photograph("", "");
        // System.out.println(testPhotograph3);
        // System.out.println(testPhotograph1.equals(testPhotograph3));

    }

}
