package com.order.perf.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Properties {

    @Getter
    @Component
    public static class Aes256Configs {

        @Value("${aes256.key}")
        private String KEY;

    }

}

