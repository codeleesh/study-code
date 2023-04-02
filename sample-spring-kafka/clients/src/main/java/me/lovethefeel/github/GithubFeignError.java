package me.lovethefeel.github;

import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

@Slf4j
public class GithubFeignError implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.value() == (response.status())) {
            throw new RetryableException(response.status(), response.reason(), Request.HttpMethod.GET, null, response.request());
        }
        return new IllegalStateException(format("%s 요청이 성공하지 못했습니다. - status: %s, headers: %s", methodKey, response.status(), response.headers()));
    }
}
