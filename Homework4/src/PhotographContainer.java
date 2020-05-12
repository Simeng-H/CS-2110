import java.util.ArrayList;

/**
* Homework 4 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * PhotographContainer
 */
public abstract class PhotographContainer {

    /** the name of the container */
    protected String name;

    /** the photographs in the container */
    protected ArrayList<Photograph> photos;

    public PhotographContainer(String name) {
        this.name = name;
        this.photos = new ArrayList<Photograph>();
    }

    /**
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param newName the new intended name of the person
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * @return the person's list of photos
     */
    public ArrayList<Photograph> getPhotos() {
        return this.photos;
    }

    /**
     * adds a photo to the album
     * 
     * @param p a photograph to be added to the album
     * @return true if p has been added to the album, false if p is already inside
     *         the album
     */
    public boolean addPhoto(Photograph p) {
        boolean added = false;
        if (p != null) {
            if (!this.photos.contains(p)) {
                added = this.photos.add(p);
            }
        }
        return added;
    }

    /**
     * determines whether the album contains a specific photo
     * 
     * @param p the target photo
     * @return true if the album contains a photo identical to p
     */
    public boolean hasPhoto(Photograph p) {
        return this.photos.contains(p);
    }

    /**
     * removes a photo from the album
     * 
     * @param p the target photo
     * @return true if p is successfully erased, false if p is not found in the
     *         albums
     */
    public boolean removePhoto(Photograph p) {
        return this.photos.remove(p);
    }

    /**
     * @return the number of photos in the album
     */
    public int numPhotographs() {
        return this.photos.size();
    }

    /**
     * @return true if both persons has the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        PhotographContainer other = (PhotographContainer) o;
        if (this.getName() == other.getName()) {
            return true;
        }
        return false;
    }

    public String toString() {
        String nameString = "Name:" + this.getName();
        String photoString = this.getPhotos().toString();
        return nameString + photoString;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * @param minimumRating
     * @return an arraylist of photos with a rating greater than or equal to
     *         minimumRating
     */
    public ArrayList<Photograph> getPhotos(int minimumRating) {
        ArrayList<Photograph> selectedPhotos = new ArrayList<Photograph>();
        if (!isValidRating(minimumRating)) {
            return null;
        }
        for (Photograph member : this.photos) {
            if (member.getRating() >= minimumRating) {
                selectedPhotos.add(member);
            }
        }
        return selectedPhotos;
    }

    /**
     * checks for validity of rating
     * 
     * @param rating
     * @return true if 0 <= rating <= 5, false otherwise
     */
    private boolean isValidRating(int rating) {
        return (rating >= 0) && (rating <= 5);
    }

    /**
     * @param year a 4-digit number representing a year
     * @return all photos taken with that year
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> selectedPhotos = new ArrayList<Photograph>();
        if (year < 1000 || year > 9999) // year must be 4-digit
        {
            return null;
        }
        for (Photograph member : this.photos) {
            if (getYearTaken(member) == year) {
                selectedPhotos.add(member);
            }
        }
        return selectedPhotos;
    }

    /**
     * @param p a Photograph
     * @return a 4-digit int representing the year in which p was taken
     */
    private int getYearTaken(Photograph p) {
        String dateYearString = p.getDateTaken().split("-")[0]; // the first 4 digit of date
        int dateYearInt = Integer.valueOf(dateYearString);
        return dateYearInt;
    }

    /**
     * @param month a number representing a month, strictly between 1 to 12
     * @param year  a 4-digit number representing a year
     * @return all photos taken with the specified year and month
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> selectedPhotos = new ArrayList<Photograph>();
        if (year < 1000 || year > 9999) // year must be 4-digit
        {
            return null;
        }
        if (month < 1 || month > 12) {
            return null;
        }
        for (Photograph member : this.photos) {
            if (getYearTaken(member) == year && getMonthTaken(member) == month) {
                selectedPhotos.add(member);
            }
        }
        return selectedPhotos;
    }

    /**
     * @param p a Photograph
     * @return an int (between 1-12) representing the month in which p was taken
     */
    private int getMonthTaken(Photograph p) {
        String dateMonthString = p.getDateTaken().split("-")[1]; // the first 4 digit of date
        int dateMonthInt = Integer.valueOf(dateMonthString);
        return dateMonthInt;
    }

    /**
     * @param beginDate a date in format "YYYY-MM-DD"
     * @param endDate   a date in format "YYYY-MM-DD"
     * @return all photos taken between beginDate and endDate, inclusively
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        if (!dateIsValid(beginDate)) {
            return null;
        }
        if (!dateIsValid(endDate)) {
            return null;
        }
        int beginDateInt = dateToInt(beginDate);
        int endDateInt = dateToInt(endDate);

        if (beginDateInt > endDateInt) {
            return null;
        }

        ArrayList<Photograph> selectedPhotos = new ArrayList<Photograph>();
        for (Photograph member : this.getPhotos()) {
            int memberDateInt = dateToInt(member.getDateTaken());
            if (beginDateInt <= memberDateInt && memberDateInt <= endDateInt) {
                selectedPhotos.add(member);
            }
        }
        return selectedPhotos;
    }

    /**
     * @param date a String representing a date in format YYYY-MM-DD
     * @return a int corresponding to the given date, in format YYYYMMDD
     */
    private int dateToInt(String date) {
        String[] parsedDate = date.split("-");
        int yearInt = Integer.valueOf(parsedDate[0]);
        int monthInt = Integer.valueOf(parsedDate[1]);
        int dayInt = Integer.valueOf(parsedDate[2]);
        return yearInt * 10000 + monthInt * 100 + dayInt;
    }

    /**
     * check if date is in correct format
     * 
     * @param date a string representing a date
     * @return true if date is in format YYYY-MM-DD, false otherwise
     */
    private boolean dateIsValid(String date) {

        // check for correct number of segments
        String[] parsedDate = date.split("-");
        if (parsedDate.length != 3) {
            return false;
        }

        // check for correct number of digits in each part
        String yearString = parsedDate[0];
        String monthString = parsedDate[1];
        String dayString = parsedDate[2];
        if (yearString.length() != 4) {
            return false;
        }
        if (monthString.length() != 2) {
            return false;
        }
        if (dayString.length() != 2) {
            return false;
        }

        // check for valid values
        int yearInt = Integer.valueOf(yearString);
        int monthInt = Integer.valueOf(monthString);
        int dayInt = Integer.valueOf(dayString);
        if (yearInt < 1000) {
            return false;
        }
        if (monthInt < 1 || monthInt > 12) {
            return false;
        }
        if (dayInt < 1 || dayInt > 31) {
            return false;
        }

        return true;
    }

}
