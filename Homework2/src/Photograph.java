
/**
 * Photograph
 */
public class Photograph {

    /** the caption of the photo */
    private final String caption;

    /** the filename of the photo */
    private final String filename;

    /**
     * @param caption  the caption of the photo
     * @param filename the filename of the photo
     */
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getFilename() {
        return this.filename;
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
            if (this.getCaption() == otherPhotograph.getCaption() && this.getFilename() == otherPhotograph.getFilename()) {
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
     * @param args
     */
    public static void main(String[] args) {

        String caption1 = "Hello";
        String filename1 = "Alpha";
        Photograph testPhotograph1 = new Photograph(caption1, filename1);
        String caption2 = "Hello";
        String filename2 = "Alpha";
        Photograph testPhotograph2 = new Photograph(caption2, filename2);

        System.out.println(testPhotograph1);
        System.out.println(testPhotograph1.equals(testPhotograph1));
        System.out.println(testPhotograph1.equals(testPhotograph2));

        Photograph testPhotograph3 = new Photograph("", "");
        System.out.println(testPhotograph3);
        System.out.println(testPhotograph1.equals(testPhotograph3));

    }
}
