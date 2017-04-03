import org.sql2o.*;
import java.util.List;

public class Patron {

  private int id;
  private String name;
  private String phone;
  private String email;
  private int bookCount;

  public Patron(String name, String phone, String email) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.bookCount = 0;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getEmail() {
    return this.email;
  }

  public int getBookCount() {
    return this.bookCount;
  }

  public static List<Patron> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patrons;";
      return con.createQuery(sql)
        .addColumnMapping("book_count", "bookCount")
        .executeAndFetch(Patron.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patrons (name, phone, email, book_count) VALUES (:name, :phone, :email, :bookCount);";
      this.id = (int) con.createQuery(sql, true)
        .addColumnMapping("book_count", "bookCount")
        .addParameter("name", this.getName())
        .addParameter("phone", this.getPhone())
        .addParameter("email", this.getEmail())
        .addParameter("bookCount", this.getBookCount())
        .executeUpdate()
        .getKey();
    }
  }

  public boolean equals(Object otherPatron) {
    if(!(otherPatron instanceof Patron)) {
      return false;
    } else {
      Patron newPatron = (Patron) otherPatron;
      return this.getId() == newPatron.getId() &&
        this.getName().equals(newPatron.getName()) &&
        this.getPhone().equals(newPatron.getPhone()) &&
        this.getEmail().equals(newPatron.getEmail()) &&
        this.getBookCount() == newPatron.getBookCount();
    }
  }
}
