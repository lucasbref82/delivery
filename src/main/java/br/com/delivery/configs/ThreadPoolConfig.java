package br.com.delivery.configs;

import br.com.delivery.enums.ThreadPools;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = ThreadPools.EXECUTOR_TAREFAS_PADRAO_POOL_THREAD)
    public Executor executorTarefasPadraoPoolThread() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("padrao-");
        executor.initialize();
        return executor;
    }


}
