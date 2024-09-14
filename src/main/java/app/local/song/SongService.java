package app.local.song;

import app.local.artist.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    @Value("${file-upload}")
    private String fileUpload;

    @Value("${file-upload-img}")
    private String fileUploadImg;

    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

     public Song save(SongRequest request) throws IOException {
        Song song = createSongRest(request);
        songRepository.save(song);
        return song;
    }

    void remove(Long id) {
        Optional<Song> songOptional = findById(id);
        if (!songOptional.isPresent()) {
            //TODO: throw exception
        }
        songRepository.deleteById(id);
    }

    public Song updateSongRest(Long id, SongRequest songRequest) throws IOException {
        Optional<Song> songOptional = songRepository.findById(id);
        if (songOptional.isEmpty()) {
            throw new RuntimeException("Song not found");
        }

        Song song = songOptional.get();
        MultipartFile imageFile = songRequest.getAvatarUrl();
        String imagePath = imageFile.getOriginalFilename();

        if (!imagePath.isEmpty()) {
            FileCopyUtils.copy(imageFile.getBytes(), new File(fileUploadImg + imagePath));
            song.setAvatarUrl(imagePath);
        }

        song.setTitle(songRequest.getTitle());
        song.setDescription(songRequest.getDescription());

        return songRepository.save(song);
    }

    private Song createSongRest(SongRequest songRequest) throws IOException {
        Song song = new Song();
        MultipartFile multipartFile = songRequest.getMediaUrl();
        String path = multipartFile.getOriginalFilename();

        if (!path.isEmpty()) {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + path));
            song.setMediaUrl(path);
        }

        MultipartFile imageFile = songRequest.getAvatarUrl();
        String imagePath = imageFile.getOriginalFilename();

        if (!imagePath.isEmpty()) {
            FileCopyUtils.copy(imageFile.getBytes(), new File(fileUploadImg + imagePath));
            song.setAvatarUrl(imagePath);
        }

        song.setTitle(songRequest.getTitle());
        song.setDescription(songRequest.getDescription());

        return song;
    }

    public List<Song> getSongByUserId(Long id) {
        return songRepository.findSongByUserId(id);
    }

    public List<Song> findLikedSongsByUserId(Long userId) {
        return songRepository.findLikedSongsByUserId(userId);
    }

    public Page<Song> findSongByTitle(String title, Pageable pageable) {
        return songRepository.findSongByTitleContainingIgnoreCase(title, pageable);
    }

    public List<Song> findByArtist(Optional<Artist> featuredArtist) {
        return songRepository.findSongsByArtistId(featuredArtist.get().getId());
    }
}