package project.apicapstone.jobrunr;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.server.BackgroundJobServer;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.utils.mapper.JsonMapper;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AppConfig {
    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }
    //    @Bean
//    @DependsOn("storageProvider")
//    public JobScheduler jobScheduler(StorageProvider storageProvider, ApplicationContext applicationContext) {
//        return JobRunr.configure().useStorageProvider(storageProvider)
//                .useJobActivator(applicationContext::getBean)
//                .useDefaultBackgroundJobServer()
//                .useDashboard()
//                .useJmxExtensions()
//                .initialize();
//    }


}
