package com.psy.demo.config;

import com.psy.demo.ext.CustomThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
public class ThreadPoolConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Set the custom ThreadFactory
        executor.setThreadFactory(new CustomThreadFactory("MyExecutor"));

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("MyExecutor-");  // This can be used in addition to the custom ThreadFactory

        executor.initialize();
        return executor;
    }

}
