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

    // Constructor for create/update without image
    public ArtistRequest(String name, String artistProfile, String biography) {
        this.name = name;
        this.artistProfile = artistProfile;
        this.biography = biography;
    }

    // Constructor for create/update with image
    public ArtistRequest(String name, String artistProfile, String biography, String image) {
        this.name = name;
        this.artistProfile = artistProfile;
        this.biography = biography;
        this.image = image;
    }
}
