package school.hei.haapi.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.haapi.endpoint.rest.mapper.TranscriptClaimMapper;
import school.hei.haapi.endpoint.rest.model.StudentTranscriptClaim;
import school.hei.haapi.endpoint.rest.model.StudentTranscriptVersion;
import school.hei.haapi.model.BoundedPageSize;
import school.hei.haapi.model.PageFromOne;
import school.hei.haapi.model.TranscriptClaim;
import school.hei.haapi.service.TranscriptClaimService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TranscriptClaimController {
    private final TranscriptClaimMapper transcriptClaimMapper;
    private final TranscriptClaimService transcriptClaimService;
    @GetMapping("/students/{student_id}/transcripts/{transcript_id}/versions/{version_id}/claims")
    public List<StudentTranscriptClaim> getClaims(
            @PathVariable("student_id") String studentId,
            @PathVariable("transcript_id") String transcriptId,
            @PathVariable("version_id") String versionId,
            @RequestParam PageFromOne page,
            @RequestParam("page_size") BoundedPageSize pageSize
    ){
        return transcriptClaimService.getAllByVersionId(studentId,transcriptId,versionId,page,pageSize).stream()
                .map(transcriptClaimMapper::toRest)
                .collect(Collectors.toUnmodifiableList());
    }
    @GetMapping("/students/{student_id}/transcripts/{transcript_id}/versions/{version_id}/claims/{claim_id}")
    public StudentTranscriptClaim getClaimsById(
            @PathVariable("student_id") String studentId,
            @PathVariable("transcript_id") String transcriptId,
            @PathVariable("version_id")String versionId,
            @PathVariable("claim_id")String claimId
    )
    {
        return transcriptClaimMapper.toRest(transcriptClaimService.findByVersionIdAndClaimId(studentId,transcriptId,versionId,claimId));
    }
    @PutMapping("/students/{student_id}/transcripts/{transcript_id}/versions/{version_id}/claims/{claim_id}")
    public StudentTranscriptClaim CreateOrUpdateTranscriptVersionClaim(
            @PathVariable("student_id") String studentId,
            @PathVariable("transcript_id") String transcriptId,
            @PathVariable("version_id") String versionId,
            @PathVariable("claim_id") String claimId,
            @RequestBody StudentTranscriptClaim studentTranscriptClaim
    ){

        //TODO: validator pathVariable and body attribute (compare)
        return transcriptClaimMapper.toRest(transcriptClaimService.save(transcriptClaimMapper.toDomain(studentTranscriptClaim,studentId,transcriptId,versionId,claimId)));
    }
}