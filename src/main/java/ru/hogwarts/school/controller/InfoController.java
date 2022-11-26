package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    public static final Logger LOG = LoggerFactory.getLogger(InfoController.class);

    @Value("${server.port}")
    private int port;

    @GetMapping("/port")
    public int getPort() {
        return port;
    }

    @GetMapping
    public int getSum() {
        long time = System.currentTimeMillis();

        Stream.iterate(1, a -> a + 1).limit(1_000_000)
//                .parallel()
                .reduce(0, Integer::sum);
        time = System.currentTimeMillis() - time;
        LOG.debug("time = {}", time);
        return (int) time;
    }
}
