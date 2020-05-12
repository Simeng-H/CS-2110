import java.util.ArrayList;

/**
 * Homework 2 Simeng Hao, sh4aj Source: N/A
 */
/**
 * A Person who has a name, an id, and a list of photos
 */
public class Person {

    /** the name of the person, can be changed */
    private String name;

    /** the id of the person, cannot be change once set */
    private final int id;

    /** a list of photo the person has posted */
    private ArrayList<Photograph> photos;

    /**
     * @param name the intended name of the person
     * @param id   the id of the person
     */
    public Person(String name, int id) {
        this.name = name;
        this.id = id;
        this.photos = new ArrayList<Photograph>();
    }

    /**
     * @return the id of the person
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the person's list of photos
     */
    public ArrayList<Photograph> getPhotos() {
        return this.photos;
    }

    /**
     * @param newName the new intended name of the person
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * @param p a photograph to be added to the person's photo
     * @return true if p has been added to the person's photo, false if p is already inside the person's photos
     */
    public boolean takePhoto(Photograph p) {
        boolean added = false;
        if (this.hasPhoto(p) == false) {
            this.photos.add(p);
            added = true;
        }
        return added;
    }

    /**
     * determines whether the person's photo contains a specific photo
     * 
     * @param p the target photo
     * @return true if the person's photos contains a photo identical to p
     */
    public boolean hasPhoto(Photograph p) {
        boolean result = false;
        for (Photograph photo : this.getPhotos()) {
            if (p.equals(photo)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * removes a photo from a person's photos
     * 
     * @param p the target photo
     * @return true if p is successfully erased, false if p is not found in the person's photos
     */
    public boolean erasePhoto(Photograph p) {
        boolean erased = false;
        for (int i = 0; i < this.photos.size(); i++) {
            Photograph photo = this.photos.get(i);
            if (photo.equals(p)) {
                this.photos.remove(i);
                erased = true;
                break;
            }
        }
        return erased;
    }

    /**
     * @return the number of photos the person has
     */
    public int numPhotographs() {
        return this.photos.size();
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
        Person other = (Person) o;
        if (this.getId() == other.getId())
            return true;
        return false;
    }

    /**
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        String nameString = "name: " + this.getName() + "\n";
        String idString = "id: " + this.getId() + "\n";
        String photosString = "Photos: \n";
        for (Photograph photo : this.photos) {
            photosString += photo.toString() + "\n";
        }
        return nameString + idString + photosString;
    }

    /**
     * returns a list of photos found in both person's photos
     * 
     * @param a a person
     * @param b another person
     * @return the photos a and b have in common
     */
    public static ArrayList<Photograph> commonPhotos(Person a, Person b) {
        ArrayList<Photograph> photosFromA = a.getPhotos();
        // ArrayList<Photograph> photosFromB = b.getPhotos();
        ArrayList<Photograph> resultList = new ArrayList<Photograph>();
        for (Photograph photo : photosFromA) {
            if (b.hasPhoto(photo))
                resultList.add(photo);
        }
        return resultList;

    }

    /**
     * calculate the degree of similarity (0.0 - 1.0) between two persons, defined as (the number of common photos / the
     * number of the photos of the person who has less photos), or 0.0 if either person has no photo at all.
     * 
     * @param a
     * @param b
     * @return degree of similarity between a and b represented by a float
     */
    public static double similarity(Person a, Person b) {
        int photoCountA = a.numPhotographs();
        int photoCountB = b.numPhotographs();
        int commonPhotoCount = 0;
        int denominator = photoCountA < photoCountB ? photoCountA : photoCountB; // takes the smaller of either

        if (denominator == 0) //if either person has no photo at all, return 0
            return 0;

        for (Photograph photo : a.getPhotos()) {
            if (b.hasPhoto(photo))
                commonPhotoCount++;
        }
        return (double) commonPhotoCount / denominator;
    }

    /**
     * for main method testing
     * @param args
     */
    public static void main(String[] args) {
        Person testPersonA;
        Person testPersonB;
        Person testPersonC;
        Photograph photoA;
        Photograph photoB;
        Photograph photoC;
        Photograph photoACopy;

        // test .takePhoto()
        // Expected:
        // []
        // [Photograph with filename: 1 and caption: a]
        // false
        // [Photograph with filename: 1 and caption: a]
        testPersonA = new Person("a", 1);
        photoA = new Photograph("a", "1");
        System.out.println(testPersonA.getPhotos());
        testPersonA.takePhoto(photoA);
        System.out.println(testPersonA.getPhotos());
        System.out.println(testPersonA.takePhoto(photoA));
        System.out.println(testPersonA.getPhotos());
        System.out.println();

        // test .hasPhoto()
        // expected:
        // []
        // true
        // true
        // false
        testPersonA = new Person("a", 1);
        photoA = new Photograph("a", "1");
        photoACopy = new Photograph("a", "1");
        photoB = new Photograph("b", "2");
        System.out.println(testPersonA.getPhotos());
        testPersonA.takePhoto(photoA);
        System.out.println(testPersonA.hasPhoto(photoA));
        System.out.println(testPersonA.hasPhoto(photoACopy));
        System.out.println(testPersonA.hasPhoto(photoB));
        System.out.println();

        // test .erasePhoto()
        // Expected:
        // []
        // [Photograph with filename: 1 and caption: a]
        // false
        // true
        // []
        // true
        // []

        testPersonA = new Person("a", 1);
        photoA = new Photograph("a", "1");
        photoB = new Photograph("b", "2");
        photoACopy = new Photograph("a", "1");
        System.out.println(testPersonA.getPhotos());
        testPersonA.takePhoto(photoA);
        System.out.println(testPersonA.getPhotos());
        System.out.println(testPersonA.erasePhoto(photoB));
        System.out.println(testPersonA.erasePhoto(photoA));
        System.out.println(testPersonA.getPhotos());
        testPersonA.takePhoto(photoA);
        System.out.println(testPersonA.erasePhoto(photoACopy));
        System.out.println(testPersonA.getPhotos());
        System.out.println();

        // test .numPhotographs()
        // Expected:
        // 0
        // 1
        testPersonA = new Person("a", 1);
        photoA = new Photograph("a", "1");
        System.out.println(testPersonA.numPhotographs());
        testPersonA.takePhoto(photoA);
        System.out.println(testPersonA.numPhotographs());
        System.out.println();

        // test .equals()
        // Expected:
        // false
        // true
        // false
        // true
        testPersonA = new Person("a", 1);
        testPersonB = new Person("b", 2);
        testPersonC = new Person("c", 1);
        System.out.println(testPersonA.equals(testPersonB));
        System.out.println(testPersonA.equals(testPersonC));
        System.out.println(testPersonA.equals(null));
        System.out.println(testPersonA.equals(testPersonA));
        System.out.println();

        // test .toString()
        // Expedted:
        // name: a
        // id: 1
        // Photos:
        //
        // name: a
        // id: 1
        // Photos:
        // Photograph with filename: 1 and caption: a
        testPersonA = new Person("a", 1);
        photoA = new Photograph("a", "1");
        System.out.println(testPersonA);
        testPersonA.takePhoto(photoA);
        System.out.println(testPersonA);
        System.out.println();

        // test .commonPhotos()
        // Expected:
        // []
        // [Photograph with filename: 1 and caption: a]
        testPersonA = new Person("a", 1);
        testPersonB = new Person("b", 2);
        photoA = new Photograph("a", "1");
        photoB = new Photograph("b", "2");
        testPersonA.takePhoto(photoA);
        testPersonB.takePhoto(photoB);
        System.out.println(Person.commonPhotos(testPersonA, testPersonB));
        testPersonB.takePhoto(photoA);
        System.out.println(Person.commonPhotos(testPersonA, testPersonB));
        System.out.println();

        // test .similarity()
        // Expected:
        // 0.0
        // 1.0
        // 0.5
        testPersonA = new Person("a", 1);
        testPersonB = new Person("b", 2);
        photoA = new Photograph("a", "1");
        photoB = new Photograph("b", "2");
        photoC = new Photograph("c", "3");
        testPersonA.takePhoto(photoA);
        testPersonB.takePhoto(photoB);
        System.out.println(Person.similarity(testPersonA, testPersonB));
        testPersonB.takePhoto(photoA);
        System.out.println(Person.similarity(testPersonA, testPersonB));
        testPersonA.takePhoto(photoC);
        System.out.println(Person.similarity(testPersonA, testPersonB));
        System.out.println();

    }
}
