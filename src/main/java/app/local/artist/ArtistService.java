package app.local.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Value("${file-upload-img}")
    private String fileUpload;

    @Transactional
    public void save(ArtistRequest artistRequest, MultipartFile file) {
        String imageFileName = saveFile(file);
        Artist artist = Artist.builder()
                .name(artistRequest.getName())
                .image(imageFileName)
                .artistProfile(artistRequest.getArtistProfile())
                .biography(artistRequest.getBiography())
                .build();
        artistRepository.save(artist);
    }


    public Page<Artist> findAll(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    @Transactional
    public boolean update(Long id, ArtistRequest artistRequest, MultipartFile file) {
        return artistRepository.findById(id)
                .map(artist -> {
                    artist.setName(artistRequest.getName());
                    if (file != null && !file.isEmpty()) {
                        artist.setImage(saveFile(file));
                    }
                    artist.setArtistProfile(artistRequest.getArtistProfile());
                    artist.setBiography(artistRequest.getBiography());
                    artistRepository.save(artist);
                    return true;
                }).orElse(false);
    }

    public boolean delete(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean existsById(Long id) {
        return artistRepository.existsById(id);
    }

    private String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            Path path = Paths.get(fileUpload + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
