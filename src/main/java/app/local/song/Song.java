package app.local.song;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Column(name = "mediaUrl", nullable = false)
    private String mediaUrl;

    @Column(name = "playCount", nullable = true)
    private int playCount =0;
}