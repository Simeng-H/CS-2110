import java.util.ArrayList;
import java.util.HashSet;

/**
* Homework 4 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * A PhotoLibrary who has a name, an id, and a list of photos
 */
public class PhotoLibrary extends PhotographContainer {

    /** the id of the person, cannot be change once set */
    private final int id;

    /** a HashSet of the albums in the library */
    private HashSet<Album> albums;

    /**
     * @param name the intended name of the person
     * @param id   the id of the person
     */
    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
        this.albums = new HashSet<Album>();
    }

    /**
     * @return the id of the person
     */
    public int getId() {
        return this.id;
    }

    public HashSet<Album> getAlbums() {
        return this.albums;
    }

    /**
     * check if albums contains an album equal to a
     * 
     * @param a an album to look for
     * @return true if albums contains a
     */
    private boolean containsAlbum(Album a) {
        return this.albums.contains(a);
    }

    /**
     * adds a new album to albums
     * 
     * @param albumName the name of the new album
     * @return true if successfully added, false if an album with albumName already
     *         exists.
     */
    public boolean createAlbum(String albumName) {
        Album newAlbum = new Album(albumName);
        if (containsAlbum(newAlbum)) {
            return false;
        } else {
            this.albums.add(newAlbum);
            return true;
        }
    }

    /**
     * remove an album from albums
     * 
     * @param albumName the name of the album to be removed
     * @return true if the album has been removed, false if there isn't an album
     *         named albumName in albums
     */
    public boolean removeAlbum(String albumName) {
        Album toBeRemoved = new Album(albumName);
        return this.albums.remove(toBeRemoved);
    }

    /**
     * @param p         a photo to be added to the album named albunName
     * @param albumName the name of the album
     * @return true if p is successfully added, false if p isn't in the photo
     *         library or that it already exists in the album
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        boolean result = false;
        if (this.getPhotos().contains(p) == false) {
            return result;
        }
        Album targetAlbum = getAlbumByName(albumName);
        if (targetAlbum != null) {
            result = targetAlbum.addPhoto(p);
        }
        return result;
    }

    /**
     * @param p         a photo to be removed
     * @param albumName the name of the album from which p is to be removed
     * @return true if successfully removed, false if p isn't found in the album
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        boolean result = false;
        Album targetAlbum = getAlbumByName(albumName);
        if (targetAlbum != null) {
            result = targetAlbum.removePhoto(p);
        }
        return result;
    }

    /**
     * @param albumName the name of the intended album
     * @return an album in albums with albumName as its name, null if not found
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : this.albums) {
            if (a.equals(new Album(albumName))) {
                return a;
            }
        }
        return null;
    }

    /**
     * removes a photo from the PhotoLibrary and from all albums containing it
     * 
     * @param p the targetAlbum photo
     * @return true if p is successfully erased, false if p is not found in the
     *         person's photos
     */
    @Override
    public boolean removePhoto(Photograph p) {
        boolean result = super.removePhoto(p);
        if (result == true) {
            for (Album a : this.albums) {
                a.removePhoto(p);
            }
        }
        return result;
    }

    /**
     * returns true if both persons has the same ID
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        PhotoLibrary other = (PhotoLibrary) o;
        if (this.getId() == other.getId()) {
            return true;
        }
        return false;
    }

    /**
     * @return a string representation of the PhotoLibrary
     */
    @Override
    public String toString() {
        String nameString = "name: " + this.getName() + "\n";
        String idString = "id: " + this.getId() + "\n";

        String photosString = "Photos: \n";
        for (Photograph photo : this.photos) {
            photosString += photo.toString() + "\n";
        }

        String albumString = "Albums: \n";
        for (Album a : this.albums) {
            albumString += a.toString() + "\n";
        }

        return nameString + idString + photosString + albumString;
    }

    /**
     * returns a list of photos found in both person's photos
     * 
     * @param a a person
     * @param b another person
     * @return the photos a and b have in common
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> photosFromA = a.getPhotos();
        ArrayList<Photograph> resultList = new ArrayList<Photograph>();
        for (Photograph photo : photosFromA) {
            if (b.hasPhoto(photo)) {
                resultList.add(photo);
            }
        }
        return resultList;

    }

    /**
     * calculate the degree of similarity (0.0 - 1.0) between two persons, defined
     * as (the number of common photos / the number of the photos of the person who
     * has less photos), or 0.0 if either person has no photo at all.
     * 
     * @param a
     * @param b
     * @return degree of similarity between a and b represented by a float
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        int photoCountA = a.numPhotographs();
        int photoCountB = b.numPhotographs();
        int commonPhotoCount = 0;
        int denominator = photoCountA < photoCountB ? photoCountA : photoCountB; // takes the smaller of either

        if (denominator == 0) // if either person has no photo at all, return 0
        {
            return 0;
        }

        for (Photograph photo : a.getPhotos()) {
            if (b.hasPhoto(photo)) {
                commonPhotoCount++;
            }
        }
        return (double) commonPhotoCount / denominator;
    }

    /**
     * for main method testing
     * 
     * @param args
     */
    public static void main(String[] args) {
        // PhotoLibrary testPersonA;
        // PhotoLibrary testPersonB;
        // PhotoLibrary testPersonC;
        // Photograph photoA;
        // Photograph photoB;
        // Photograph photoC;
        // Photograph photoACopy;

        // // test .takePhoto()
        // // Expected:
        // // []
        // // [Photograph with filename: 1 and caption: a]
        // // false
        // // [Photograph with filename: 1 and caption: a]
        // testPersonA = new PhotoLibrary("a", 1);
        // photoA = new Photograph("a", "1");
        // System.out.println(testPersonA.getPhotos());
        // testPersonA.takePhoto(photoA);
        // System.out.println(testPersonA.getPhotos());
        // System.out.println(testPersonA.takePhoto(photoA));
        // System.out.println(testPersonA.getPhotos());
        // System.out.println();

        // // test .hasPhoto()
        // // expected:
        // // []
        // // true
        // // true
        // // false
        // testPersonA = new PhotoLibrary("a", 1);
        // photoA = new Photograph("a", "1");
        // photoACopy = new Photograph("a", "1");
        // photoB = new Photograph("b", "2");
        // System.out.println(testPersonA.getPhotos());
        // testPersonA.takePhoto(photoA);
        // System.out.println(testPersonA.hasPhoto(photoA));
        // System.out.println(testPersonA.hasPhoto(photoACopy));
        // System.out.println(testPersonA.hasPhoto(photoB));
        // System.out.println();

        // // test .erasePhoto()
        // // Expected:
        // // []
        // // [Photograph with filename: 1 and caption: a]
        // // false
        // // true
        // // []
        // // true
        // // []

        // testPersonA = new PhotoLibrary("a", 1);
        // photoA = new Photograph("a", "1");
        // photoB = new Photograph("b", "2");
        // photoACopy = new Photograph("a", "1");
        // System.out.println(testPersonA.getPhotos());
        // testPersonA.takePhoto(photoA);
        // System.out.println(testPersonA.getPhotos());
        // System.out.println(testPersonA.erasePhoto(photoB));
        // System.out.println(testPersonA.erasePhoto(photoA));
        // System.out.println(testPersonA.getPhotos());
        // testPersonA.takePhoto(photoA);
        // System.out.println(testPersonA.erasePhoto(photoACopy));
        // System.out.println(testPersonA.getPhotos());
        // System.out.println();

        // // test .numPhotographs()
        // // Expected:
        // // 0
        // // 1
        // testPersonA = new PhotoLibrary("a", 1);
        // photoA = new Photograph("a", "1");
        // System.out.println(testPersonA.numPhotographs());
        // testPersonA.takePhoto(photoA);
        // System.out.println(testPersonA.numPhotographs());
        // System.out.println();

        // // test .equals()
        // // Expected:
        // // false
        // // true
        // // false
        // // true
        // testPersonA = new PhotoLibrary("a", 1);
        // testPersonB = new PhotoLibrary("b", 2);
        // testPersonC = new PhotoLibrary("c", 1);
        // System.out.println(testPersonA.equals(testPersonB));
        // System.out.println(testPersonA.equals(testPersonC));
        // System.out.println(testPersonA.equals(null));
        // System.out.println(testPersonA.equals(testPersonA));
        // System.out.println();

        // // test .toString()
        // // Expedted:
        // // name: a
        // // id: 1
        // // Photos:
        // //
        // // name: a
        // // id: 1
        // // Photos:
        // // Photograph with filename: 1 and caption: a
        // testPersonA = new PhotoLibrary("a", 1);
        // photoA = new Photograph("a", "1");
        // System.out.println(testPersonA);
        // testPersonA.takePhoto(photoA);
        // System.out.println(testPersonA);
        // System.out.println();

        // // test .commonPhotos()
        // // Expected:
        // // []
        // // [Photograph with filename: 1 and caption: a]
        // testPersonA = new PhotoLibrary("a", 1);
        // testPersonB = new PhotoLibrary("b", 2);
        // photoA = new Photograph("a", "1");
        // photoB = new Photograph("b", "2");
        // testPersonA.takePhoto(photoA);
        // testPersonB.takePhoto(photoB);
        // System.out.println(PhotoLibrary.commonPhotos(testPersonA, testPersonB));
        // testPersonB.takePhoto(photoA);
        // System.out.println(PhotoLibrary.commonPhotos(testPersonA, testPersonB));
        // System.out.println();

        // // test .similarity()
        // // Expected:
        // // 0.0
        // // 1.0
        // // 0.5
        // testPersonA = new PhotoLibrary("a", 1);
        // testPersonB = new PhotoLibrary("b", 2);
        // photoA = new Photograph("a", "1");
        // photoB = new Photograph("b", "2");
        // photoC = new Photograph("c", "3");
        // testPersonA.takePhoto(photoA);
        // testPersonB.takePhoto(photoB);
        // System.out.println(PhotoLibrary.similarity(testPersonA, testPersonB));
        // testPersonB.takePhoto(photoA);
        // System.out.println(PhotoLibrary.similarity(testPersonA, testPersonB));
        // testPersonA.takePhoto(photoC);
        // System.out.println(PhotoLibrary.similarity(testPersonA, testPersonB));
        // System.out.println();
    }
}
