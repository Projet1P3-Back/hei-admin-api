package school.hei.haapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "\"transcript_version\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
public class TranscriptVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int ref;
    private Instant createDatetime;
    private String pdfLink;
    @ManyToOne
    @JoinColumn(name = "transcript_id",nullable = false)
    private Transcript transcript;
    @ManyToOne
    @JoinColumn(name = "editor_id",nullable = false)
    private User editor;
}