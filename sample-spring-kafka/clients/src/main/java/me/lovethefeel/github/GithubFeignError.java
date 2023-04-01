package me.lovethefeel.github;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GithubFeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("test call");
        return null;
    }
}
