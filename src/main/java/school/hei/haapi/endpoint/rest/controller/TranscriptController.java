package school.hei.haapi.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.hei.haapi.model.BoundedPageSize;
import school.hei.haapi.model.PageFromOne;
import school.hei.haapi.model.Transcript;
import school.hei.haapi.service.TranscriptService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TranscriptController {
    private final TranscriptService transcriptService;
    @GetMapping(value = "/students/{id}/transcripts")
    public List<Transcript> getTranscript(
            @RequestParam PageFromOne page, @RequestParam("page_size") BoundedPageSize pageSize,
            @RequestParam(value = "studentId", required = false, defaultValue = "") String studentId
            ){
        return transcriptService.getByStudentId(studentId, page, pageSize);
    }
    @GetMapping(value = "/students/{id}/transcripts/{id}")
    public  Transcript getTranscriptId(@PathVariable String id){
        return transcriptService.getTranscriptById(id);
    }
}
