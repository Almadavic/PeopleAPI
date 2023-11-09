package br.com.almada.people.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_logs")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "event", nullable = false)
    private String event;

    @Column(name = "event_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime eventTime;

    public Log(@NonNull String event) {
        this.event = event;
    }

}
