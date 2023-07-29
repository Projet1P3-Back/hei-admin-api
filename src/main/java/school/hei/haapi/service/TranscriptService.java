package school.hei.haapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.BoundedPageSize;
import school.hei.haapi.model.PageFromOne;
import school.hei.haapi.model.Transcript;
import school.hei.haapi.repository.TranscriptRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@AllArgsConstructor

public class TranscriptService {

    private  final TranscriptRepository transcriptRepository;

@Transactional
    public List<Transcript> saveAll(List<Transcript> transcripts){
    return transcriptRepository.saveAll(transcripts);
}
    public List<Transcript> getTranscriptByStudentId(String studentId, PageFromOne page, BoundedPageSize pageSize){

        Pageable pageable = PageRequest.of(
                page.getValue() - 1,
                pageSize.getValue(),
                Sort.by(DESC, "creationDatetime"));
        return transcriptRepository.findAllByStudentId(studentId,pageable);
    }
    public  Transcript getByStudentIdAndId(String studentId,String transcriptId){
        return  transcriptRepository.getByStudentIdAndId(studentId,transcriptId);
    }


}
