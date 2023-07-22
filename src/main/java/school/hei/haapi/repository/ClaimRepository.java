package school.hei.haapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.haapi.model.Claim;

import java.util.List;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, String> {
    List<Claim> findByStudentIdAndTranscriptIdAndVersionId(Long studentId, Long transcriptId, Long versionId);
    Optional<Claim> findByStudentIdAndTranscriptIdAndVersionIdAndId(Long studentId, Long transcriptId, Long versionId, Long claimId);
}
