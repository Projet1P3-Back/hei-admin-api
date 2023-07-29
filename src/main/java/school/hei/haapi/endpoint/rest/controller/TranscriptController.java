package school.hei.haapi.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.hei.haapi.endpoint.rest.mapper.TranscriptMapper;
import school.hei.haapi.endpoint.rest.model.Transcript;
import school.hei.haapi.model.BoundedPageSize;
import school.hei.haapi.model.PageFromOne;
import school.hei.haapi.service.TranscriptService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;

@RestController
@AllArgsConstructor
public class TranscriptController {
    private final TranscriptService transcriptService;
    private final TranscriptMapper transcriptMapper;
    @GetMapping(value = "/students/{studentId}/transcripts")
    public List<school.hei.haapi.endpoint.rest.model.Transcript> getTranscripts(
            @PathVariable String studentId,
            @RequestParam PageFromOne page, @RequestParam("page_size") BoundedPageSize pageSize

            ){
        return transcriptService.getTranscriptByStudentId(studentId, page, pageSize)
                .stream()
                .map(transcriptMapper::toRest).collect(toUnmodifiableList());
    }
    @GetMapping(value = "/students/{studentId}/transcripts/{transcriptId}")
    public Transcript getTranscriptId(@PathVariable String studentId,@PathVariable String transcriptId){
        return  transcriptMapper.toRest(transcriptService.getByStudentIdAndId(studentId, transcriptId));
    }
}
