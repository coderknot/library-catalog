import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Book_instatiatesCorrectly_true() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    assertTrue(testBook instanceof Book);
  }

  @Test
  public void getId_getsBookId_id() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    testBook.save();
    assertTrue(testBook.getId() > 0);
  }

  @Test
  public void getTitle_getsBookTitle_title() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    assertEquals("Firestarter", testBook.getTitle());
  }

  @Test
  public void getAuthor_getsBookTitle_author() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    assertEquals("Stephen King", testBook.getAuthor());
  }

  @Test
  public void getGenreId_getsBookGenreId() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    assertEquals(1, testBook.getGenreId());
  }

  @Test
  public void equals_BookObjectsAreTheSame_true() {
    Book testBook1 = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    testBook1.save();
    Book testBook2 = testBook1;
    assertTrue(testBook1.equals(testBook2));
  }

  @Test
  public void all_returnsAllSavedBooks_true() {
    Book testBook1 = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    testBook1.save();
    Book testBook2 = new Book("The Stand", "Double Day", "Stephen King", 1);
    testBook2.save();
    assertTrue(Book.all().get(0).equals(testBook1));
    assertTrue(Book.all().get(1).equals(testBook2));
  }

  @Test
  public void save_returnsTrueIfBookSaved_true() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    testBook.save();
    assertTrue(Book.all().get(0).equals(testBook));
  }

  @Test
  public void find_returnsBookWithSameId_Book() {
    Book testBook1 = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    testBook1.save();
    Book testBook2 = new Book("The Stand", "Double Day", "Stephen King", 1);
    testBook2.save();
    assertEquals(testBook2, Book.find(testBook2.getId()));
  }

  @Test
  public void updateTitle_updatesBookTitle_true() {
    Book testBook = new Book("Firestater", "Viking Press", "Stephen King", 1);
    testBook.save();
    testBook.updateTitle("Firestarter");
    assertEquals("Firestarter", Book.find(testBook.getId()).getTitle());
  }

  @Test
  public void updateAuthor_updatesBookAuthor_true() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stefen King", 1);
    testBook.save();
    testBook.updateAuthor("Stephen King");
    assertEquals("Stephen King", Book.find(testBook.getId()).getAuthor());
  }

  @Test
  public void delete_deletesBook_true() {
    Book testBook = new Book("Firestarter", "Viking Press", "Stephen King", 1);
    testBook.save();
    int testBookId = testBook.getId();
    testBook.delete();
    assertEquals(null, Book.find(testBookId));
  }
}
