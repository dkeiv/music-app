package app.local.playlist;

import app.local.song.Song;
import app.local.song.SongRepository;
import app.local.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayListService {

    private final PlayListRepository playlistRepository;
    private final SongRepository songRepository;

    // Phương thức chung để lấy PlayList với xử lý ngoại lệ
    private PlayList findPlayListById(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found with id: " + playlistId));
    }

    public void save(PlayListRequest request) {
        var playlist = PlayList.builder()
                .id(request.getId())
                .name(request.getName())
                .views(request.getViews())
                .build();
        playlistRepository.save(playlist);
    }

    public Page<PlayList> findAll(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    public PlayList getPlayList(Long playlistId) {
        return findPlayListById(playlistId); // sử dụng phương thức chung
    }

    public void updatePlayList(Long playlistId, PlayListRequest request) {
        PlayList playlist = findPlayListById(playlistId);
        var updatedPlayList = PlayList.builder()
                .id(playlist.getId())
                .name(request.getName() == null ? playlist.getName() : request.getName())
                .views(request.getViews())
                .build();
        playlistRepository.save(updatedPlayList);
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

    public List<PlayList> getPlayListsByUserId(Long userId) {
        return playlistRepository.findPlayListByUserId(userId);
    }

    public Page<PlayList> findPlayListsByName(String name, Pageable pageable) {
        return playlistRepository.findPlayListByNameContainingIgnoreCase(name, pageable);
    }

}
