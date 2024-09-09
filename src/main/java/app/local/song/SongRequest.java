package app.local.song;

import org.springframework.web.multipart.MultipartFile;

public class SongRequest {
    private Long id;

    private String title;

    private String description;

    private MultipartFile mediaUrl;

    public SongRequest() {
    }

    public SongRequest(Long id, String title, String description, MultipartFile mediaUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mediaUrl = mediaUrl;
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

    public MultipartFile getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(MultipartFile mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}