import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
* Homework 3 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/

public class TestPhotoLibrary {

    @Test
    public void testGetPhotosNormalCase() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-11", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-11-11", 3);
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        testPhotoLibrary.addPhoto(photo3);
        ArrayList<Photograph> expectedPhotos = new ArrayList<Photograph>();
        expectedPhotos.add(photo2);
        expectedPhotos.add(photo3);
        assertEquals(expectedPhotos, testPhotoLibrary.getPhotos(2));
    }

    /** rating >5, expect Null */
    @Test
    public void testGetPhotosInvalidRating() {
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        assertNull(testPhotoLibrary.getPhotos(10));
    }

    @Test
    public void testGetPhotosInMonthNormalCase() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-12-11", 3);
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        testPhotoLibrary.addPhoto(photo3);
        ArrayList<Photograph> expectedPhotos = new ArrayList<Photograph>();
        expectedPhotos.add(photo1);
        expectedPhotos.add(photo2);
        assertEquals(testPhotoLibrary.getPhotosInMonth(11, 1111), expectedPhotos);
    }

    /** test for a month corresponding to no photos, expect empty ArrayList */
    @Test
    public void testGetPhotosInMonthEmptyMonth() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-12-11", 3);
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        testPhotoLibrary.addPhoto(photo3);
        ArrayList<Photograph> expectedPhotos = new ArrayList<Photograph>();
        assertEquals(testPhotoLibrary.getPhotosInMonth(10, 1111), expectedPhotos);
    }

    @Test
    public void testGetPhotosBetweenNormalCase() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-12-11", 3);
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        testPhotoLibrary.addPhoto(photo3);
        ArrayList<Photograph> expectedPhotos = new ArrayList<Photograph>();
        expectedPhotos.add(photo1);
        expectedPhotos.add(photo2);
        expectedPhotos.add(photo3);
        assertEquals(testPhotoLibrary.getPhotosBetween("1111-11-11", "1111-12-11"), expectedPhotos);
    }

    /** test for dates in incorrect format, expect null */
    @Test
    public void testGetPhotosBetweenInvalidDates() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-12-11", 3);
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        testPhotoLibrary.addPhoto(photo3);
        assertNull(testPhotoLibrary.getPhotosBetween("a", "b"));
    }

    @Test
    public void testErasePhotoNormalCase() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-12-11", 3);
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        testPhotoLibrary.addPhoto(photo3);

        // create an album for testing
        testPhotoLibrary.createAlbum("testAlbum");
        testPhotoLibrary.addPhotoToAlbum(photo1, "testAlbum");

        testPhotoLibrary.removePhoto(photo1);
        ArrayList<Photograph> expectedPhotos = new ArrayList<Photograph>();
        expectedPhotos.add(photo2);
        expectedPhotos.add(photo3);
        assertEquals(testPhotoLibrary.getPhotos(), expectedPhotos);

        // test that the photo is deleted from albums
        ArrayList<Photograph> expectedAlbumPhotos = new ArrayList<Photograph>();
        Album testAlbum = null;
        for (Album member : testPhotoLibrary.getAlbums()) {
            if (member.equals(new Album("testAlbum"))) {
                testAlbum = member;
            }
        }
        assertEquals(testAlbum.getPhotos(), expectedAlbumPhotos);
    }

    /**
     * removing a photo that's not in the PhotoLibrary, expect to return false and
     * not to change the PhotoLibrary
     */
    @Test
    public void testErasePhotoPhotoDoesNotExist() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-12-11", 3);
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        assertFalse(testPhotoLibrary.removePhoto(photo3));

        ArrayList<Photograph> expectedPhotos = new ArrayList<Photograph>();
        expectedPhotos.add(photo1);
        expectedPhotos.add(photo2);
        assertEquals(testPhotoLibrary.getPhotos(), expectedPhotos);

    }

    @Test
    public void testSimilarityNormalCase() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        Photograph photo3 = new Photograph("c", "c", "1111-12-11", 3);
        PhotoLibrary testPhotoLibrary1 = new PhotoLibrary("a", 1);
        PhotoLibrary testPhotoLibrary2 = new PhotoLibrary("b", 2);
        testPhotoLibrary1.addPhoto(photo1);
        testPhotoLibrary1.addPhoto(photo2);
        testPhotoLibrary2.addPhoto(photo2);
        testPhotoLibrary2.addPhoto(photo3);
        double similarity = PhotoLibrary.similarity(testPhotoLibrary1, testPhotoLibrary2);
        assertEquals(1 / 2.0, similarity, 0.01);
    }

    /**
     * test for similarity comparison involving an empty PhotoLibrary, expect 0.0
     */
    @Test
    public void testSimilarityEmptyLibrary() {
        Photograph photo1 = new Photograph("a", "a", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "b", "1111-11-12", 2);
        PhotoLibrary testPhotoLibrary1 = new PhotoLibrary("a", 1);
        PhotoLibrary testPhotoLibrary2 = new PhotoLibrary("b", 2);
        testPhotoLibrary1.addPhoto(photo1);
        testPhotoLibrary1.addPhoto(photo2);
        double similarity = PhotoLibrary.similarity(testPhotoLibrary1, testPhotoLibrary2);
        assertEquals(0, similarity, 0.01);
    }

}
