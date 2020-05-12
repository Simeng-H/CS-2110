import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
* Homework 4 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * HW4Test <br>
 * Test for: <br>
 * .removePhoto() in PhotoLibrary <br>
 * .compareTo() in Photograph <br>
 * .compare() in CompareByCaption <br>
 * .compare() in CompareByRating <br>
 */
public class HW4Test {

    @Test
    public void testRemovePhotoNormalCase() {
        Photograph photo1 = new Photograph("1", "a");
        Photograph photo2 = new Photograph("2", "b");
        Photograph photo3 = new Photograph("3", "c");
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);

        testPhotoLibrary.createAlbum("testAlbum1");
        testPhotoLibrary.createAlbum("testAlbum2");
        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        testPhotoLibrary.addPhoto(photo3);
        testPhotoLibrary.addPhotoToAlbum(photo2, "testAlbum1");
        testPhotoLibrary.addPhotoToAlbum(photo2, "testAlbum2");
        testPhotoLibrary.addPhotoToAlbum(photo3, "testAlbum2");

        testPhotoLibrary.removePhoto(photo1);
        testPhotoLibrary.removePhoto(photo2);

        // test that photo1 and photo2 has been removed, but not photo3
        ArrayList<Photograph> expectedPhotos = new ArrayList<Photograph>();
        expectedPhotos.add(photo3);
        assertEquals(testPhotoLibrary.getPhotos(), expectedPhotos);

        // test that the photo2 is deleted from all albums
        for (Album member : testPhotoLibrary.getAlbums()) {
            assertFalse(member.hasPhoto(photo2));
        }

        // test that photo3 remains in the album
        boolean albumHasPhoto3 = false;
        for (Album member : testPhotoLibrary.getAlbums()) {
            if (member.hasPhoto(photo3)) {
                albumHasPhoto3 = true;
                break;
            }
        }
        assertTrue(albumHasPhoto3);

    }

    @Test
    public void testRemovePhotoWhenPhotoNotInLibrary() {
        Photograph photo1 = new Photograph("1", "a");
        Photograph photo2 = new Photograph("2", "b");
        Photograph photo3 = new Photograph("3", "c");
        PhotoLibrary testPhotoLibrary = new PhotoLibrary("a", 1);

        testPhotoLibrary.addPhoto(photo1);
        testPhotoLibrary.addPhoto(photo2);
        assertFalse(testPhotoLibrary.removePhoto(photo3));
    }

    @Test
    public void testPhotographCompareToDiffDate() {
        Photograph photo1 = new Photograph("a", "Caption2", "1111-11-11", 1);
        Photograph photo2 = new Photograph("a", "Caption1", "2222-22-22", 2);
        int comparisonResult = photo1.compareTo(photo2);
        assertTrue(comparisonResult < 0);
    }

    @Test
    public void testPhotographCompareToSameDate() {
        Photograph photo1 = new Photograph("a", "Caption1", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "Caption2", "1111-11-11", 2);
        int comparisonResult = photo1.compareTo(photo2);
        assertTrue(comparisonResult < 0);
    }

    @Test
    public void testCompareByCaptionSameCaption() {
        Photograph photo1 = new Photograph("a", "Caption", "1111-11-11", 2);
        Photograph photo2 = new Photograph("b", "Caption", "1111-11-11", 1);
        CompareByCaption comparator = new CompareByCaption();
        int result = comparator.compare(photo1, photo2);
        assertTrue(result < 0);
    }

    @Test
    public void testCompareByCaptionDiffCaption() {
        Photograph photo1 = new Photograph("a", "CaptionA", "1111-11-11", 2);
        Photograph photo2 = new Photograph("b", "CaptionB", "1111-11-11", 1);
        CompareByCaption comparator = new CompareByCaption();
        int result = comparator.compare(photo1, photo2);
        assertTrue(result < 0);
    }

    @Test
    public void testCompareByRatingSameRating() {
        Photograph photo1 = new Photograph("a", "Caption1", "1111-11-11", 1);
        Photograph photo2 = new Photograph("b", "Caption2", "1111-11-11", 1);
        CompareByRating comparator = new CompareByRating();
        int result = comparator.compare(photo1, photo2);
        assertTrue(result < 0);
    }

    @Test
    public void testCompareByRatingDiffRating() {
        Photograph photo1 = new Photograph("a", "Caption1", "1111-11-11", 2);
        Photograph photo2 = new Photograph("b", "Caption2", "1111-11-11", 1);
        CompareByRating comparator = new CompareByRating();
        int result = comparator.compare(photo1, photo2);
        assertTrue(result < 0);
    }

}