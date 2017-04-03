import org.sql2o.*;
import java.util.List;

public class Book {

  private int id;
  private String title;
  private String author;
  private int genreId;

  public Book(String title, String author, int genreId) {
    this.title = title;
    this.author = author;
    this.genreId = genreId;
  }

  public int getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getAuthor() {
    return this.author;
  }

  public int getGenreId() {
    return this.genreId;
  }

  public static List<Book> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM books;";
      return con.createQuery(sql)
        .addColumnMapping("genre_id", "genreId")
        .executeAndFetch(Book.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO books (title, author, genre_id) VALUES (:title, :author, :genreId);";
      this.id = (int) con.createQuery(sql, true)
        .addColumnMapping("genre_id", "genreId")
        .addParameter("title", this.getTitle())
        .addParameter("author", this.getAuthor())
        .addParameter("genreId", this.getGenreId())
        .executeUpdate()
        .getKey();
    }
  }

  public boolean equals(Object otherBook) {
    if(!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getId() == newBook.getId() &&
        this.getTitle().equals(newBook.getTitle()) &&
        this.getAuthor().equals(newBook.getAuthor()) &&
        this.getGenreId() == newBook.getGenreId();
    }
  }
}
