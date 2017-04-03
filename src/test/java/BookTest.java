import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Book_instatiatesCorrectly_true() {
    Book testBook = new Book("Firestarter", "Stephen King", 1);
    assertTrue(testBook instanceof Book);
  }

  @Test
  public void getId_getsBookId_id() {
    Book testBook = new Book("Firestarter", "Stephen King", 1);
    testBook.save();
    assertTrue(testBook.getId() > 0);
  }

  @Test
  public void getTitle_getsBookTitle_title() {
    Book testBook = new Book("Firestarter", "Stephen King", 1);
    assertEquals("Firestarter", testBook.getTitle());
  }

  @Test
  public void getAuthor_getsBookTitle_author() {
    Book testBook = new Book("Firestarter", "Stephen King", 1);
    assertEquals("Stephen King", testBook.getAuthor());
  }

  @Test
  public void getGenreId_getsBookGenreId() {
    Book testBook = new Book("Firestarter", "Stephen King", 1);
    assertEquals(1, testBook.getGenreId());
  }

  @Test
  public void equals_BookObjectsAreTheSame_true() {
    Book testBook1 = new Book("Firestarter", "Stephen King", 1);
    testBook1.save();
    Book testBook2 = testBook1;
    assertTrue(testBook1.equals(testBook2));
  }

  @Test
  public void all_returnsAllSavedBooks_true() {
    Book testBook1 = new Book("Firestarter", "Stephen King", 1);
    testBook1.save();
    Book testBook2 = new Book("The Stand", "Stephen King", 1);
    testBook2.save();
    assertTrue(Book.all().get(0).equals(testBook1));
    assertTrue(Book.all().get(1).equals(testBook2));
  }

  @Test
  public void save_returnsTrueIfBookSaved_true() {
    Book testBook = new Book("Firestarter", "Stephen King", 1);
    testBook.save();
    assertTrue(Book.all().get(0).equals(testBook));
  }
}
