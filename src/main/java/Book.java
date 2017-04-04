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

  public static Book find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM media WHERE id = :id;";
      Book book = con.createQuery(sql)
        .addColumnMapping("genre_id", "genre_id")
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeAndFetchFirst(Book.class);
      return book;
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
