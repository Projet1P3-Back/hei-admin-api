package school.hei.haapi.endpoint.rest.validator;

import org.springframework.stereotype.Component;
import school.hei.haapi.model.exception.BadRequestException;
import school.hei.haapi.service.utils.ByteTypeValidator;

import java.util.function.Consumer;

@Component
public class CreateTranscriptVersionValidator implements Consumer<byte[]> {

    @Override
    public void accept(byte[] file) {
        if (file == null){
            throw new BadRequestException("pdf file required");
        }
        ByteTypeValidator.isValid(file,"application","pdf");
    }
}
