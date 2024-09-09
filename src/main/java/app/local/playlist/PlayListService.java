package app.local.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {
    @Autowired
    private PlayListRepository playlistRepository;

    public void save(PlayList playlist) {}

    public PlayList createPlayList(PlayListRequest request) {
        PlayList playlist = new PlayList();
        playlist.setName(request.getName());
        return playlistRepository.save(playlist);
    }

    public Page<PlayList> getPlayLists(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    public PlayList getPlayList(Long playlistId) {
        return playlistRepository.findById(playlistId).orElseThrow(()-> new RuntimeException("No playlist found with id: " + playlistId));
    }

    public PlayList updatePlayList(Long playlistId ,PlayListRequest request) {
        PlayList playlist = getPlayList(playlistId);
        playlist.setName(request.getName());
        return playlistRepository.save(playlist);
    }

    public void deletePlayList(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }
}
