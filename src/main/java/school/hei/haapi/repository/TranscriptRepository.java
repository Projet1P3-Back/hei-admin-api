package school.hei.haapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.haapi.model.Transcript;

import java.util.List;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, String> {
    List<Transcript> findAllByStudentId(String studentId);

    Transcript getByStudentIdAndId(String studentId, String id);
}
