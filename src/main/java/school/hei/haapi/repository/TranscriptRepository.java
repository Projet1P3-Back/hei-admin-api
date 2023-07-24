package school.hei.haapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.haapi.endpoint.rest.model.Student;
import school.hei.haapi.model.Course;
import school.hei.haapi.model.StudentCourse;
import school.hei.haapi.model.Transcript;
import school.hei.haapi.model.User;

import java.util.List;

public interface TranscriptRepository extends JpaRepository<Transcript, String> {
   // List<Transcript> getStudentIdTranscripts(String studentId, Pageable pageable);
}
