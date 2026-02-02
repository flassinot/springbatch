package com.example.exemplesspringbatch.config;

import com.example.exemplesspringbatch.model.Person;
import com.example.exemplesspringbatch.tasklet.CleanupTasklet;
import com.example.exemplesspringbatch.tasklet.PrepareTasklet;
import com.example.exemplesspringbatch.tasklet.ProcessTasklet;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@Profile("tasklet")
public class BatchConfigTasklet {

    @Bean
    public Job taskletJob(JobRepository jobRepository,
                          Step prepareStep,
                          Step processStep,
                          Step cleanupStep) {
        return new JobBuilder("taskletJob", jobRepository)
                .start(prepareStep)
                .next(processStep)
                .next(cleanupStep)
                .build();
    }

    @Bean
    public Step prepareStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            PrepareTasklet tasklet) {
        return new StepBuilder("prepareStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public Step processStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ProcessTasklet tasklet) {
        return new StepBuilder("processStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public Step cleanupStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            CleanupTasklet tasklet) {
        return new StepBuilder("cleanupStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}
