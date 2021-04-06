package com.lemon.metric;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import com.alibaba.druid.pool.DruidDataSource;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.lang.NonNull;

/**
 * Druid Metric
 *
 * @author shijianpeng
 */
public class DruidDataSourceMetric implements MeterBinder {

	private DruidDataSource druidDataSource;

	public DruidDataSourceMetric(DruidDataSource druidDataSource) {
		this.druidDataSource = druidDataSource;
	}

	@Override
	public void bindTo(@NonNull MeterRegistry meterRegistry) {
		Gauge.builder("jdbc.connections.active", druidDataSource::getActiveCount).register(meterRegistry);
		Gauge.builder("jdbc.connections.max", druidDataSource::getMaxActive).register(meterRegistry);
		Gauge.builder("jdbc.connections.min", druidDataSource::getMinIdle).register(meterRegistry);
		Gauge.builder("jdbc.druid.initialSize", druidDataSource::getInitialSize).register(meterRegistry);
		Gauge.builder("jdbc.druid.maxActive", druidDataSource::getMaxActive).register(meterRegistry);
		Gauge.builder("jdbc.druid.minIdle", druidDataSource::getMinIdle).register(meterRegistry);
		Gauge.builder("jdbc.druid.maxIdle", druidDataSource::getMaxIdle).register(meterRegistry);
		Gauge.builder("jdbc.druid.maxWait", druidDataSource::getMaxWait).register(meterRegistry);
		Gauge.builder("jdbc.druid.notFullTimeoutRetry", druidDataSource::getNotFullTimeoutRetryCount)
				.register(meterRegistry);
		Gauge.builder("jdbc.druid.execute", druidDataSource::getExecuteCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.executeQuery", druidDataSource::getExecuteQueryCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.executeUpdate", druidDataSource::getExecuteUpdateCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.executeBatch", druidDataSource::getExecuteBatchCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.create", druidDataSource::getCreateCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.destroy", druidDataSource::getDestroyCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.pooling", druidDataSource::getPoolingCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.active", druidDataSource::getActiveCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.discard", druidDataSource::getDiscardCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.notEmptyWaitThread", druidDataSource::getNotEmptyWaitThreadCount)
				.register(meterRegistry);
		Gauge.builder("jdbc.druid.notEmptyWaitThreadPeak", druidDataSource::getNotEmptyWaitThreadPeak)
				.register(meterRegistry);
		Gauge.builder("jdbc.druid.recycleError", druidDataSource::getRecycleErrorCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.connect", druidDataSource::getConnectCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.close", druidDataSource::getCloseCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.connectError", druidDataSource::getConnectErrorCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.recycle", druidDataSource::getRecycleCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.removeAbandoned", druidDataSource::getRemoveAbandonedCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.notEmptyWait", druidDataSource::getNotEmptyWaitCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.notEmptySignal", druidDataSource::getNotEmptySignalCount).register(meterRegistry);
		Gauge.builder("jdbc.druid.notEmptyWait", druidDataSource::getNotEmptyWaitNanos).baseUnit("nanos")
				.register(meterRegistry);
		Gauge.builder("jdbc.druid.activePeak", druidDataSource::getActivePeak).register(meterRegistry);
		Gauge.builder("jdbc.druid.poolingPeak", druidDataSource::getPoolingPeak).register(meterRegistry);
	}

}
