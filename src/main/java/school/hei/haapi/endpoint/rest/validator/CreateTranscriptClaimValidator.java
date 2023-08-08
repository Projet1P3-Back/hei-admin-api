package school.hei.haapi.endpoint.rest.validator;

import org.springframework.stereotype.Component;
import school.hei.haapi.endpoint.rest.model.StudentTranscriptClaim;
import school.hei.haapi.model.exception.BadRequestException;

import java.util.Objects;
import java.util.function.Consumer;
@Component
public class CreateTranscriptClaimValidator implements Consumer<StudentTranscriptClaim> {
    @Override public void accept(StudentTranscriptClaim studentTranscriptClaim) {
        if (studentTranscriptClaim.getReason() == null && studentTranscriptClaim.getReason().isEmpty()) {
            throw new BadRequestException("Reason is mandatory");
        }
        if (studentTranscriptClaim.getId() == null && studentTranscriptClaim.getId().isEmpty()) {
            throw new BadRequestException("Id is mandatory");
        }
        if (studentTranscriptClaim.getTranscriptId() == null && studentTranscriptClaim.getTranscriptId().isEmpty()) {
            throw new BadRequestException("Transcript Id is mandatory");
        }
        if (studentTranscriptClaim.getTranscriptVersionId() == null && studentTranscriptClaim.getTranscriptVersionId().isEmpty()) {
            throw new BadRequestException("Transcript Version Id is mandatory");
        }
    }

    public void acceptPathAndBodyCorrelation(StudentTranscriptClaim studentTranscriptClaim, String transcriptId, String versionId, String claimId) {
        if (!Objects.equals(studentTranscriptClaim.getId(), claimId)) {
            throw new BadRequestException("Id in request body: " + studentTranscriptClaim.getId() + " is different from id in path variable: " + claimId);
        }
        if (!Objects.equals(studentTranscriptClaim.getTranscriptId(), transcriptId)) {
            throw new BadRequestException("transcriptId in request body: " + studentTranscriptClaim.getTranscriptId() + " is different from transcript_id in path variable: " + transcriptId);
        }
        if (!Objects.equals(studentTranscriptClaim.getTranscriptVersionId(), versionId)) {
            throw new BadRequestException("versionId in request body: " + studentTranscriptClaim.getTranscriptVersionId() + " is different from version_id in path variable: " + versionId);
        }
    }
}
