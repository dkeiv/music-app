package app.local.artist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArtistRequest {
    private Integer id;
    private String name;
    private String image;
    private String artistProfile;
    private String biography;
}
