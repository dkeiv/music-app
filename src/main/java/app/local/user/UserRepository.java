package app.local.user;

import app.local.playlist.PlayList;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findById(Long id);

    void save(User user);

    void deleteById(Long id);

    Optional<User> findByEmail(String email);

}
