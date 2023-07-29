package school.hei.haapi.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import school.hei.haapi.endpoint.rest.model.CourseStatus;
import school.hei.haapi.repository.types.PostgresEnumType;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "\"transcript\"")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "pgsql_enum", typeClass = PostgresEnumType.class)
public class Transcript implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User student;
    //enum
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    private Semester semester;
    private  String academicYear;
    //Boolean
    private Boolean isDefinitive;
    //locale date
    //instante date precie
    @CreationTimestamp
    @Getter(AccessLevel.NONE)
    private Instant creationDatetime;
    public enum Semester {
        s1, s2, s3, s4
    }
    public Instant getCreationDatetime() {
        return creationDatetime.truncatedTo(ChronoUnit.MILLIS);
    }
}
