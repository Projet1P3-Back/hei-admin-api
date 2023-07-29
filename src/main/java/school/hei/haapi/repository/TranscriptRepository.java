package school.hei.haapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.haapi.endpoint.rest.model.Student;
import school.hei.haapi.model.Course;
import school.hei.haapi.model.StudentCourse;
import school.hei.haapi.model.Transcript;
import school.hei.haapi.model.User;

import java.util.List;
@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, String> {
    Transcript getByStudentIdAndId(String studentId, String transcriptId);
    List<Transcript> findAllByStudentId(String studentId, Pageable pageable);
}
