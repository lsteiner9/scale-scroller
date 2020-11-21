package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(
    indices = {
        @Index(value = {"oauth_key"}, unique = true),
        @Index(value = {"username"}, unique = true)
    }
)
public class Player {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "player_id")
  private long id;

  @ColumnInfo(name = "oauth_key")
  private String oauthKey;

  @NonNull
  private String username;

  @ColumnInfo(name = "highest_learn_level")
  private int highestLearnLevel;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  @NonNull
  public String getUsername() {
    return username;
  }

  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  public int getHighestLearnLevel() {
    return highestLearnLevel;
  }

  public void setHighestLearnLevel(int highestLearnLevel) {
    this.highestLearnLevel = highestLearnLevel;
  }
}
