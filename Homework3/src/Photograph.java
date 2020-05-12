/**
 * Homework 3 Simeng Hao, sh4aj Source: N/A
 */
/**
 * Photograph
 */
public class Photograph {

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
        this.rating = 1;
    }

    /**
     * @param caption  the caption of the photo
     * @param filename the filename of the photo
     * @param dateTaken a date in format "YYYY-MM-DD"
     * @param rating a rating from 0 to 5 inclusively
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
        if (0 <= rating && rating <= 5)
            this.rating = rating;
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
        boolean result = false;
        while (true) {
            if (obj == null)
                break;
            if (this.getClass() != obj.getClass())
                break;
            Photograph otherPhotograph = (Photograph) obj;
            if (this.getCaption() == otherPhotograph.getCaption()
                    && this.getFilename() == otherPhotograph.getFilename()) {
                result = true;
            }
            break;
        }
        return result;
    }

    public String toString() {
        return "Photograph with filename: " + this.getFilename() + " and caption: " + this.getCaption();
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
