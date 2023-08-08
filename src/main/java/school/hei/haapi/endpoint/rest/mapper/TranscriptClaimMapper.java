package school.hei.haapi.endpoint.rest.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.haapi.endpoint.rest.model.StudentTranscriptClaim;
import school.hei.haapi.endpoint.rest.validator.CreateTranscriptClaimValidator;
import school.hei.haapi.model.TranscriptClaim;
import school.hei.haapi.model.TranscriptVersion;
import school.hei.haapi.repository.TranscriptClaimRepository;
import school.hei.haapi.service.TranscriptVersionService;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;


@Component
@AllArgsConstructor
public class TranscriptClaimMapper {
    private final TranscriptVersionService transcriptVersionService;
    private final TranscriptClaimRepository transcriptClaimRepository;
    private final CreateTranscriptClaimValidator transcriptClaimValidator;

    public StudentTranscriptClaim toRest(TranscriptClaim transcriptClaim) {
        return new StudentTranscriptClaim()
                .id(transcriptClaim.getId())
                .transcriptId(transcriptClaim.getTranscriptVersion().getTranscript().getId())
                .transcriptVersionId(transcriptClaim.getTranscriptVersion().getId())
                .creationDatetime(transcriptClaim.getCreationDatetime())
                .closedDatetime(transcriptClaim.getClosedDatetime())
                .reason(transcriptClaim.getReason())
                .status(StudentTranscriptClaim.StatusEnum.fromValue(transcriptClaim.getClaimStatus().toString()));
    }

    public TranscriptClaim toDomain(StudentTranscriptClaim studentTranscriptClaim, String studentId, String transcriptId, String versionId, String claimId) {
        transcriptClaimValidator.acceptPathAndBodyCorrelation(studentTranscriptClaim, transcriptId, versionId, claimId);
        transcriptClaimValidator.accept(studentTranscriptClaim);

        TranscriptVersion transcriptVersion = transcriptVersionService.getTranscriptVersion(studentId, transcriptId, versionId);
        Optional<TranscriptClaim> claim = transcriptClaimRepository
                .findByTranscriptVersionTranscriptStudentIdAndTranscriptVersionTranscriptIdAndTranscriptVersionIdAndId(studentId, transcriptId, versionId, claimId);

        if (claim.isEmpty()) {
            return TranscriptClaim.builder()
                    .id(studentTranscriptClaim.getId())
                    .reason(studentTranscriptClaim.getReason())
                    .claimStatus(TranscriptClaim.ClaimStatus.OPEN)
                    .creationDatetime(null)
                    .closedDatetime(null)
                    .transcriptVersion(transcriptVersion)
                    .build();
        }

        Instant closedDatetime = null;
        if (claim.get().getClaimStatus().equals(TranscriptClaim.ClaimStatus.OPEN)
                && Objects.equals(studentTranscriptClaim.getStatus(), StudentTranscriptClaim.StatusEnum.CLOSE)) {
            closedDatetime = Instant.now();
        }
        return TranscriptClaim.builder()
                .id(studentTranscriptClaim.getId())
                .reason(studentTranscriptClaim.getReason())
                .claimStatus(TranscriptClaim.ClaimStatus.valueOf(studentTranscriptClaim.getStatus().toString()))
                .creationDatetime(claim.get().getCreationDatetime())
                .closedDatetime(closedDatetime)
                .transcriptVersion(transcriptVersion)
                .build();
    }
}
