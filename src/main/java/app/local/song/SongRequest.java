package app.local.song;

import org.springframework.web.multipart.MultipartFile;

public class SongRequest {
    private Long id;

    private String title;

    private String description;

    private MultipartFile mediaUrl;

    private MultipartFile avatarUrl;

    public SongRequest() {
    }

    public SongRequest(Long id, String title, String description, MultipartFile mediaUrl, MultipartFile avatarUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mediaUrl = mediaUrl;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(MultipartFile avatarUrl) {
        this.avatarUrl = avatarUrl;
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