package app.local.playlist;

import app.local.song.Song;
import app.local.song.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayListService {

    @Autowired
    private final PlayListRepository playlistRepository;
    private final SongRepository songRepository;

    @Value("${file-upload-img}")
    private String fileUpload;

    // Phương thức chung để lấy PlayList với xử lý ngoại lệ
    private PlayList findPlayListById(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found with id: " + playlistId));
    }

    public void save(PlayListRequest request, MultipartFile file) {
        String imageFileName = saveFile(file);
        var playlist = PlayList.builder()
                .id(request.getId())
                .name(request.getName())
                .image(imageFileName)
                .views(request.getViews())
                .build();
        playlistRepository.save(playlist);
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

    public Page<PlayList> findAll(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    public PlayList getPlayList(Long playlistId) {
        return findPlayListById(playlistId); // sử dụng phương thức chung
    }

    @Transactional
    public boolean updatePlayList(Long playlistId, PlayListRequest request, MultipartFile file) {
        return playlistRepository.findById(playlistId)
                .map(playList -> {
                    playList.setName(request.getName());
                    if (file != null && !file.isEmpty()) {
                        playList.setImage(saveFile(file));
                    }
                    playList.setViews(request.getViews());
                    playlistRepository.save(playList);
                    return true;
                }).orElse(false);
    }

    public void deletePlayList(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    public PlayList increaseViews(Long playlistId) {
        PlayList playlist = findPlayListById(playlistId); // sử dụng phương thức chung
        playlist.setViews(playlist.getViews() + 1);
        return playlistRepository.save(playlist);
    }

    public PlayList addSongToPlaylist(Long playlistId, Long songId) {
        PlayList playlist = findPlayListById(playlistId); // sử dụng phương thức chung

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));

        playlist.getSongs().add(song);
        return playlistRepository.save(playlist);
    }

    public List<Song> getSongsInPlaylist(Long playlistId) {
        PlayList playlist = findPlayListById(playlistId); // sử dụng phương thức chung
        return playlist.getSongs();
    }

    public PlayList removeSongFromPlaylist(Long playlistId, Long songId) {
        PlayList playlist = findPlayListById(playlistId);

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));
        if (playlist.getSongs().contains(song)) {
            playlist.getSongs().remove(song);
        } else {
            throw new RuntimeException("Song is not in the playlist");
        }

        return playlistRepository.save(playlist);
    }

    public Page<PlayList> getPlayListsByUserId(Long userId, Pageable pageable) {
        return playlistRepository.findPlayListByUserId(userId, pageable);
    }

    public List<PlayList> getPlayListsByUserId(Long userId) {
        return playlistRepository.findPlayListByUserId(userId);
    }

    public Page<PlayList> findPlayListsByName(String name, Pageable pageable) {
        return playlistRepository.findPlayListByNameContainingIgnoreCase(name, pageable);
    }

}
