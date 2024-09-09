package app.local.song;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Entity
public class Song {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Column(name = "mediaUrl", nullable = false)
    private String mediaUrl;

    @Column(name = "playCount", nullable = false)
    private int playCount;

    public Song() {}

    public Song(Long id, String title, String description, String mediaUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mediaUrl = mediaUrl;
    }

    public Song(Long id, String title, String description ) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Song(Long id, String title, String description, String mediaUrl, int playCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mediaUrl = mediaUrl;
        this.playCount = playCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public void incrementPlayCount() {
        this.playCount++;
    }
}