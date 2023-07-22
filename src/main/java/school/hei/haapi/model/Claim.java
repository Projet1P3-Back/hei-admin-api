package school.hei.haapi.model;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Claim implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private LocalDate creation_datetime;

    private String reason;

    private Enum status;

    private LocalDate closed_dateTime;


}
