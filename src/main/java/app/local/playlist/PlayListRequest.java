package app.local.playlist;


import lombok.Data;

@Data
public class PlayListRequest {
    private Long id;
    private String name;
    private String image;
    private int views;

    public PlayListRequest(String name, int views) {
        this.name = name;
        this.views = views;
    }
}
