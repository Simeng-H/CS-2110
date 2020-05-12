import java.util.ArrayList;

/**
 * Album
 */
public class Album {
    /** the name of the album */
    private String name;

    /** the photos in the album */
    private ArrayList<Photograph> photos;

    public Album(String albumName) {
        this.name = albumName;
        this.photos = new ArrayList<Photograph>();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Photograph> getPhotos() {
        return this.photos;
    }

    /**
     * adds a photo to the album
     * 
     * @param p a photograph to be added to the album
     * @return true if p has been added to the album, false if p is already inside the album
     */
    public boolean addPhoto(Photograph p) {
        boolean added = false;
        if(p!=null){
            if(!this.photos.contains(p))
                added = this.photos.add(p);
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
     * @return true if p is successfully erased, false if p is not found in the albums
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
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Album other = (Album) o;
        if (this.getName() == other.getName())
            return true;
        return false;
    }

    public String toString() {
        String nameString = "Name:" + this.getName();
        String photoString = this.getPhotos().toString();
        return nameString + photoString;
    }
}
