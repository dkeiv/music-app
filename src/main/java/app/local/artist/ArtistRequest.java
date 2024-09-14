package app.local.artist;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class ArtistRequest {
    private String name;
    private String artistProfile;
    private String biography;
    private MultipartFile image;

    public ArtistRequest(String name, String artistProfile, String biography) {
        this.name = name;
        this.artistProfile = artistProfile;
        this.biography = biography;
    }

    public ArtistRequest(String name, String artistProfile, String biography, MultipartFile image) {
        this.name = name;
        this.artistProfile = artistProfile;
        this.biography = biography;
        this.image = image;
    }
}
