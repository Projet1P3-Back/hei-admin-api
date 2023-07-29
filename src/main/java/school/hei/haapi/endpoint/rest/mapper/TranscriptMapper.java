package school.hei.haapi.endpoint.rest.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.haapi.endpoint.rest.model.Transcript;
import school.hei.haapi.model.validator.TranscriptValidator;
import school.hei.haapi.service.TranscriptService;
import school.hei.haapi.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TranscriptMapper {
    private final UserService userService;
    private final TranscriptService transcriptService;
    private final TranscriptValidator transcriptValidator;

    public Transcript toRest(school.hei.haapi.model.Transcript transcript) {
        return new Transcript()
                .id(transcript.getId())
                .creationDatetime(transcript.getCreationDatetime())
                .academicYear(transcript.getAcademicYear())
                .semester(transcript.getSemester())
                .isDefinitive(transcript.getIsDefinitive())
                .student(transcript.getStudent());

    }
}
