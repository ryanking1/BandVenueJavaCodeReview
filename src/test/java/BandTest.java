import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
   public void all_emptyAtFirst() {
     assertEquals(Band.all().size(), 0);
   }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Band firstBand = new Band("Band 1");
    Band secondBand = new Band("Band 1");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Band myBand = new Band("Band 1");
    myBand.save();
    assertTrue(Band.all().get(0).equals(myBand));
  }

  @Test
  public void find_findBandInDatabase_true() {
    Band myBand = new Band("Band 1");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void updatesBandName() {
    Band myBand = new Band("Band 1");
    myBand.save();
    myBand.update("Band 2");
    Band savedBand = Band.find(myBand.getId());
    assertTrue(savedBand.getName().equals("Band 2"));
  }

  @Test
  public void getVenues_returnsAllVenues_ArrayList() {
    Band myBand = new Band("Band 1");
    myBand.save();

    Venue myVenue = new Venue("Venue 1");
    myVenue.save();

    myBand.addVenue(myVenue);
    List savedVenues = myBand.getVenues();
    assertEquals(savedVenues.size(), 1);
  }
}
