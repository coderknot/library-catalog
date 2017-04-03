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

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM books WHERE id = :id;";
      Book book = con.createQuery(sql)
        .addColumnMapping("genre_id", "genreId")
        .addParameter("id", id)
        .executeAndFetchFirst(Book.class);
      return book;
    }
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

  @Override
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

  public void updateTitle(String title) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET title = :title WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("title", title)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateAuthor(String author) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET author = :author WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("author", author)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateGenre(int genreId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET genreId = :genreId WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("genreId", genreId)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM books WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
