import org.sql2o.*;
import java.util.List;

public class Book extends Media {

  private String author;

  public Book(String title, String publisher, String author, int genre_id) {
    this.title = title;
    this.publisher = publisher;
    this.type_id = 1;
    this.author = author;
    this.genre_id = genre_id;
  }

  public String getAuthor() {
    return this.author;
  }

  @Override
  public boolean equals(Object otherBook) {
    if(!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle()) &&
             this.getPublisher().equals(newBook.getPublisher()) &&
             this.getAuthor().equals(newBook.getAuthor()) &&
             this.getTypeId() == newBook.getTypeId();
    }
  }

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM media WHERE id = :id;";
      Book book = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeAndFetchFirst(Book.class);
      return book;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO media (title, publisher, type_id, genre_id, author) VALUES (:title, :publisher, :type_id, :genre_id, :author)";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("title", this.title)
          .addParameter("publisher", this.publisher)
          .addParameter("type_id", this.type_id)
          .addParameter("genre_id", this.genre_id)
          .addParameter("author", this.author)
          .throwOnMappingFailure(false)
          .executeUpdate()
          .getKey();
    }
  }

  public static List<Book> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM media WHERE type_id = 1;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Book.class);
    }
  }

  public void updateAuthor(String author) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE media SET author = :author WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("author", author)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

}
