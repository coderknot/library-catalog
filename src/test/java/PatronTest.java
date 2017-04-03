import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Patron_instantiatesCorrectly_true() {
    Patron testPatron = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    assertTrue(testPatron instanceof Patron);
  }

  @Test
  public void getId_getsPatronId_id() {
    Patron testPatron = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    testPatron.save();
    assertTrue(testPatron.getId() > 0);
  }

  @Test
  public void getName_getsPatronName_name() {
    Patron testPatron = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    assertEquals("Chris", testPatron.getName());
  }

  @Test
  public void getPhone_getsPatronPhone_phone() {
    Patron testPatron = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    assertEquals("(555) 555-5555", testPatron.getPhone());
  }

  @Test
  public void getEmail_getsPatronEmail_email() {
    Patron testPatron = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    assertEquals("test@gmail.com", testPatron.getEmail());
  }

  @Test
  public void getBookCount_getsPatronBookCount_bookCount() {
    Patron testPatron = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    assertEquals(0, testPatron.getBookCount());
  }

  @Test
  public void equals_PatronObjectsAreTheSame_true() {
    Patron testPatron1 = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    testPatron1.save();
    Patron testPatron2 = testPatron1;
    assertTrue(testPatron1.equals(testPatron2));
  }

  @Test
  public void all_returnsAllSavedPatrons_true() {
    Patron testPatron1 = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    testPatron1.save();
    Patron testPatron2 = new Patron("Grace", "(555) 555-5556", "test2@gmail.com");
    testPatron2.save();
    assertTrue(Patron.all().get(0).equals(testPatron1));
    assertTrue(Patron.all().get(1).equals(testPatron2));
  }

  @Test
  public void save_returnsTrueIfPatronSaved_true() {
    Patron testPatron = new Patron("Chris", "(555) 555-5555", "test@gmail.com");
    testPatron.save();
    assertTrue(Patron.all().get(0).equals(testPatron));
  }
}
