package dsm.hackaton._8.infrastructure.feign;

import dsm.hackaton._8.infrastructure.feign.exception.RequestFailToOtherServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static feign.FeignException.errorStatus;

@Slf4j
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() >= 400) throw RequestFailToOtherServerException.EXCEPTION;

        return errorStatus(methodKey, response);
    }
}
