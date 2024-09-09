package app.local.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String profilePicture;
    private String bio;
    private Role role;
}
