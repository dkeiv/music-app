package app.local.song;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

//    @Value("${file-upload}")
//    private String fileUpload;

    Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    void save(Song song) {
        songRepository.save(song);
    }

    void remove(Long id) {
        songRepository.deleteById(id);
    }

    Song createSong(Song song){
        return songRepository.save(song);
    }

//    public Song updateSongRest(Long id, SongForm songForm) throws IOException {
//        Optional<Song> songOptional = songRepository.findById(id);
//        if (songOptional.isEmpty()) {
//            throw new RuntimeException("Song not found");
//        }
//
//        Song song = songOptional.get();
//        MultipartFile multipartFile = songForm.getMediaUrl();
//        String path = multipartFile.getOriginalFilename();
//
//        if (!path.isEmpty()) {
//            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + path));
//            song.setMediaUrl(path);
//        }
//
//        song.setTitle(songForm.getTitle());
//        song.setDescription(songForm.getDescription());
//
//        return songRepository.save(song);
//    }

//    public Song createSongRest(SongForm songForm) throws IOException {
//        Song song = new Song();
//        MultipartFile multipartFile = songForm.getMediaUrl();
//        String path = multipartFile.getOriginalFilename();
//
//        if (!path.isEmpty()) {
//            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + path));
//            song.setMediaUrl(path);
//        }
//
//        song.setTitle(songForm.getTitle());
//        song.setDescription(songForm.getDescription());
//
//        return songRepository.save(song);
//    }

//    public Song updateSong(SongForm songForm) {
//        Optional<Song> songOptional = findById(songForm.getId());
//        if (songOptional.isEmpty()) {
//            throw new RuntimeException("Song not found");
//        }
//
//        Song song = songOptional.get();
//        MultipartFile multipartFile = songForm.getMediaUrl();
//        String path = multipartFile.getOriginalFilename();
//
//        if (!path.isEmpty()) {
//            try {
//                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + path));
//                song.setMediaUrl(path);
//            } catch (IOException e) {
//                throw new RuntimeException("File upload error", e);
//            }
//        }
//
//        song.setTitle(songForm.getTitle());
//        song.setDescription(songForm.getDescription());
//
//        save(song);
//        return song;
//    }
//
//    public Song saveSong(SongForm songForm) {
//        MultipartFile multipartFile = songForm.getMediaUrl();
//        String path = multipartFile.getOriginalFilename();
//
//        if (!path.isEmpty()) {
//            try {
//                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + path));
//            } catch (IOException e) {
//                throw new RuntimeException("File upload error", e);
//            }
//        }
//
//        Song song = new Song(songForm.getId(), songForm.getTitle(), songForm.getDescription(), path, 0);
//        save(song);  // Save the new song to the database
//        return song;
//    }
}