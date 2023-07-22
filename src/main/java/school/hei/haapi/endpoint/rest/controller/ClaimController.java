package school.hei.haapi.endpoint.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.haapi.model.Claim;
import school.hei.haapi.service.ClaimService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class ClaimController {
    private final ClaimService claimService;

    @Autowired
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/{sId}/transcripts/{tId}/versions/{vId}/claims")
    public ResponseEntity<List<Claim>> getClaimsByStudentTranscriptVersionIds(
            @PathVariable("sId") Long studentId,
            @PathVariable("tId") Long transcriptId,
            @PathVariable("vId") Long versionId
    ) {
        List<Claim> claims = claimService.getClaimsByStudentTranscriptVersionIds(studentId, transcriptId, versionId);
        if (claims.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(claims, HttpStatus.OK);
        }
    }

    @GetMapping("/{sId}/transcripts/{tId}/versions/{vId}/claims/{cId}")
    public ResponseEntity<Claim> getClaimByStudentTranscriptVersionClaimId(
            @PathVariable("sId") Long studentId,
            @PathVariable("tId") Long transcriptId,
            @PathVariable("vId") Long versionId,
            @PathVariable("cId") Long claimId
    ) {
        Optional<Claim> claim = claimService.getClaimByStudentTranscriptVersionClaimIds(studentId, transcriptId, versionId, claimId);
        return claim.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
