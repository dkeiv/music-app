package app.local.playlist;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayListService {
    @Autowired
    private final PlayListRepository playlistRepository;

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
        return playlistRepository.findById(playlistId).orElseThrow(()-> new RuntimeException("No playlist found with id: " + playlistId));
    }

    public void updatePlayList(Long playlistId ,PlayListRequest request) {
        Optional<PlayList> playlistOptional = playlistRepository.findById(playlistId);
        var updatedPlayList = PlayList.builder()
                .id(playlistOptional.get().getId())
                .name(request.getName() == null ? playlistOptional.get().getName() : request.getName())
                .views(request.getViews())
                .build();
        playlistRepository.save(updatedPlayList);
    }

    public void deletePlayList(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    public PlayList increaseViews(Long id){
        PlayList playlist = getPlayList(id);
        if (playlist != null){
            playlist.setViews(playlist.getViews() + 1);
            return playlistRepository.save(playlist);
        }
        return null;
    }
}
