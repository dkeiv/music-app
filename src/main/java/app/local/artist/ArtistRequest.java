package app.local.artist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtistRequest {
    private String name;
    private String artistProfile;
    private String biography;
    private String image;

    public ArtistRequest(String name, String artistProfile, String biography) {
        this.name = name;
        this.artistProfile = artistProfile;
        this.biography = biography;
    }

    public ArtistRequest(String name, String artistProfile, String biography, String image) {
        this.name = name;
        this.artistProfile = artistProfile;
        this.biography = biography;
        this.image = image;
    }
}
