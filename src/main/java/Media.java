import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Media {
  public int id;
  public String title;
  public String publisher;
  public int type_id;
  public int genre_id;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getPublisher() {
    return publisher;
  }

  public int getTypeId() {
    return type_id;
  }

  public int getGenreId() {
    return this.genre_id;
  }

  @Override
  public boolean equals(Object otherMedia) {
    if(!(otherMedia instanceof Media)) {
      return false;
    } else {
      Media newMedia = (Media) otherMedia;
      return this.getTitle().equals(newMedia.getTitle()) &&
             this.getPublisher().equals(newMedia.getPublisher()) &&
             this.getAuthor().equals(newMedia.getAuthor()) &&
             this.getTypeId() == newMedia.getTypeId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO media (title, publisher, type_id, genre_id) VALUES (:title, :publisher, :type_id, :genre_id)";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("title", this.title)
          .addParameter("publisher", this.title)
          .addParameter("type_id", this.type_id)
          .addParameter("genre_id", this.genre_id)
          .throwOnMappingFailure(false)
          .executeUpdate()
          .getKey();
    }
  }

  public void updateTitle(String title) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE media SET title = :title WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("title", title)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  public void updateGenre(int genreId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE media SET genre_id = :genre_id WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("genre_id", genre_id)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM media WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
