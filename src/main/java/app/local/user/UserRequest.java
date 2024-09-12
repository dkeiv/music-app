package app.local.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserRequest {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String profilePicture;
    private String bio;
//    private Role role;
    private Set<UserRole> roles;
}
