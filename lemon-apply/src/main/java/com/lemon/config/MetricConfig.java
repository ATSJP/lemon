package com.lemon.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.lemon.metric.DruidDataSourceMetric;
import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Metric
 *
 * @author shijianpeng
 */
@Configuration
public class MetricConfig {

    /**
     * meterRegistryCustomizer
     *
     * @return MeterRegistryCustomizer<MeterRegistry>
     */
    @Bean(value = "meterRegistryCustomizer")
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer(
        @Value("${spring.application.name}") String applicationName) {
        return meterRegistry -> meterRegistry.config().commonTags("application", applicationName);
    }

    /**
     * Jvm Extras
     *
     * @return MeterBinder 指标绑定
     */
    @Bean
    public MeterBinder processMemoryMetrics() {
        return new ProcessMemoryMetrics();
    }

    /**
     * Jvm Extras
     *
     * @return MeterBinder 指标绑定
     */
    @Bean
    public MeterBinder processThreadMetrics() {
        return new ProcessThreadMetrics();
    }

    /**
     * druidDataSourceMetric
     *
     * @param dataSource 数据源
     * @return MeterBinder 指标绑定
     */
    @Bean
    public MeterBinder druidDataSourceMetric(DataSource dataSource) {
        return new DruidDataSourceMetric((DruidDataSource) dataSource);
    }

}
