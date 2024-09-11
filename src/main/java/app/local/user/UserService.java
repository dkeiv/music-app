package app.local.user;

import app.local.exception.NotFoundException;
import app.local.playlist.PlayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
        {
            return user;
        } else {
            throw new NotFoundException("not found");
        }
    }

    public void save(UserRequest request) {
        var user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .profilePicture(request.getProfilePicture())
                .bio(request.getBio())
                .role(request.getRole())
                .build();
        userRepository.save(user);
    }

    public void save(Long id, UserRequest request) throws NotFoundException {
            Optional<User> user = findById(id);
            var updatedUser = User.builder()
                    .id(user.get().getId())
                    .name(request.getName() == null ? user.get().getName() : request.getName())
                    .email(request.getEmail() == null ? user.get().getEmail() : request.getEmail())
                    .password(request.getPassword() == null ? user.get().getPassword() : request.getPassword())
                    .profilePicture(request.getProfilePicture() == null ? user.get().getProfilePicture() : request.getProfilePicture())
                    .bio(request.getBio() == null ? user.get().getBio() : request.getBio())
                    .role(request.getRole() == null ? user.get().getRole() : request.getRole())
                    .build();
            userRepository.save(updatedUser);
    }

    public void delete(Long id) throws NotFoundException {
        if(findById(id).isEmpty())
        {
            throw  new NotFoundException("not found");
        }
        userRepository.deleteById(id);
    }

}
