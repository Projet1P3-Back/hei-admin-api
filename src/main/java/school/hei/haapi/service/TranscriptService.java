package school.hei.haapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.BoundedPageSize;
import school.hei.haapi.model.PageFromOne;
import school.hei.haapi.model.Transcript;
import school.hei.haapi.model.exception.NotFoundException;
import school.hei.haapi.repository.TranscriptRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@AllArgsConstructor
public class TranscriptService {
    private TranscriptRepository transcriptRepository;

    public Transcript getByIdAndStudent(String id,String studentId) {
        return transcriptRepository.getByIdAndStudentId(id,studentId).orElseThrow(()-> new NotFoundException("Transcript with id "+id+" not found for student: "+studentId));
    }

    public List<Transcript> getAllByStudentId(String studentId, PageFromOne page, BoundedPageSize pageSize) {
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(), Sort.by(DESC, "creationDatetime"));
        return transcriptRepository.findAllByStudentId(studentId, pageable);
    }
}
