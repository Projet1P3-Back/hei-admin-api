package school.hei.haapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.haapi.model.TranscriptVersion;

import java.util.List;

@Repository
public interface TranscriptVersionRepository extends JpaRepository<TranscriptVersion,String> {
    List<TranscriptVersion> findAllByTranscriptId(String transcriptId,Pageable pageable);
    TranscriptVersion findFirstByTranscriptIdOrderByCreationDatetimeDesc(String transcriptId);

    List<TranscriptVersion> getAllByEditorIdAndTranscriptId(String sId, String tId, Pageable pageable);

    TranscriptVersion getTranscriptVersionByEditorIdAndTranscriptIdAndId(String sId, String tId, String vId);
}
