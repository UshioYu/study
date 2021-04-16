import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

/**
 * @author: ushio
 * @description:
 **/
public class DemoParallelExecutionConfigurationStrategy implements ParallelExecutionConfigurationStrategy {
    @Override
    public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {
        //这里动态的添加configuration的配置
        int parallelism = 4;
        int corePoolSize = parallelism;
        int maxPoolSize = 256 + parallelism;
        int minimumRunnable = parallelism;
        return new DemoParallelExecutionConfiguration(parallelism, minimumRunnable, maxPoolSize, corePoolSize, 30);
    }
}
