package net.srook.common.healthcheck.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;

import reactor.core.publisher.Mono;

public class HealthService implements ReactiveHealthIndicator {
    @Override
    public Mono<Health> health() {
        return checkDownstreamServiceHealth()
                .onErrorResume(ex -> Mono.just(new Health.Builder().down().build()));
    }

    private Mono<Health> checkDownstreamServiceHealth() {
        return Mono.just(new Health.Builder().up().build());
    }
}
