package br.com.delivery.configs;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SchedulersConfig {

    private final ThreadPoolConfig threadPoolConfig;

    public Scheduler defaultScheduler() {
        return Schedulers.from(threadPoolConfig.executorTarefasPadraoPoolThread());
    }
}
