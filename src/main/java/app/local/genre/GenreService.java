package app.local.genre;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre save(GenreRequest request) {
        var genre = Genre.builder()
                .id(request.getId())
                .name(request.getName())
                .build();
        genreRepository.save(genre);
        return genre;
    }

    public Genre getGenreById(Long id) {
        return genreRepository.getReferenceById(id);
    }

    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }
}
