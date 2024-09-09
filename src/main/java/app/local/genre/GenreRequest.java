package app.local.genre;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreRequest {
    private Long id;
    private String name;
}
