package school.hei.haapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.Claim;
import school.hei.haapi.repository.ClaimRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    @Autowired
    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public List<Claim> getClaimsByStudentTranscriptVersionIds(Long studentId, Long transcriptId, Long versionId) {
        return claimRepository.findByStudentIdAndTranscriptIdAndVersionId(studentId, transcriptId, versionId);
    }

    public Optional<Claim> getClaimByStudentTranscriptVersionClaimIds(Long studentId, Long transcriptId, Long versionId, Long claimId) {
        return claimRepository.findByStudentIdAndTranscriptIdAndVersionIdAndId(studentId, transcriptId, versionId, claimId);
    }
}
