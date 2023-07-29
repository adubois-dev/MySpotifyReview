package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "file_info")
public class FileInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @JdbcType(VarcharJdbcType.class)
  @JdbcTypeCode(java.sql.Types.VARCHAR)
  private UUID uuid;
  private String filename;
  private String path;
  private String url;
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name="user_uuid")
  private User user;

  public FileInfo(String filename, String url) {
    this.filename = filename;
    this.url = url;
  }
  public FileInfo(String filename, String url, User user) {
    this.filename = filename;
    this.url = url;
    this.user = user;
  }
}
