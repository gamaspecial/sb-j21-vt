package vt.service;

import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VirtualThreadsService {
    public void sleep() {
        try {
            log.info("{} sleeping...", Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");//適当
        } finally {
            log.info("{} exit", Thread.currentThread().getName());
        }
    }

    // Platform Threads
    // http://localhost:8080/platform/{count}
    public void execPlatformThread(int count) {
        IntStream.range(0, count)
        .forEach(e -> Thread.ofPlatform().start(() -> sleep()));
    }

    // Virtual Threads
    // http://localhost:8080/virtual/{count}
    public void execVirtualThread(int count) {
        IntStream.range(0, count)
        .forEach(e -> Thread.ofVirtual().start(() -> sleep()));
    }

    @Async
    public void sleepAsync() {
        try {
            log.info(" async sleeping......");
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");//適当
        }

    }

    public void task(int count) {
        for (int i = 0; i < count; i++) {
            log.info("task-{}, {}", i, Math.random());
        }
    }
}
