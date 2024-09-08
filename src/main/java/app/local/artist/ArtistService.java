package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public void save(ArtistRequest request) {
        var artist = Artist.builder()
                .name(request.getName())
                .image(request.getImage())
                .artistProfile(request.getArtistProfile())
                .biography(request.getBiography())
                .build();
        artistRepository.save(artist);
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Optional<Artist> findById(Integer id) {
        return artistRepository.findById(id);
    }

    public boolean update(Integer id, ArtistRequest request) {
        return artistRepository.findById(id)
                .map(artist -> {
                    artist.setName(request.getName());
                    artist.setImage(request.getImage());
                    artist.setArtistProfile(request.getArtistProfile());
                    artist.setBiography(request.getBiography());
                    artistRepository.save(artist);
                    return true;
                }).orElse(false);
    }

    public boolean delete(Integer id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean existsById(Integer id) {
        return artistRepository.existsById(id);
    }

//    public Optional<List<Song>> findSongsByArtistId(Long id) {
//        return artistRepository.findById(id)
//                .map(artist -> artist.getSongs());
//    }
}
