// Copyright 2018 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.config.server.maintenance;

import com.yahoo.cloud.config.ConfigserverConfig;
import com.yahoo.component.AbstractComponent;
import com.yahoo.config.provision.SystemName;
import com.yahoo.vespa.config.server.ApplicationRepository;
import com.yahoo.vespa.config.server.session.FileDistributionFactory;
import com.yahoo.vespa.curator.Curator;

import java.time.Duration;

public class ConfigServerMaintenance extends AbstractComponent {

    private final TenantsMaintainer tenantsMaintainer;
    private final ZooKeeperDataMaintainer zooKeeperDataMaintainer;
    private final FileDistributionMaintainer fileDistributionMaintainer;

    @SuppressWarnings("unused") // instantiated by Dependency Injection
    public ConfigServerMaintenance(ConfigserverConfig configserverConfig,
                                   ApplicationRepository applicationRepository,
                                   Curator curator,
                                   FileDistributionFactory fileDistributionFactory) {
        DefaultTimes defaults = new DefaultTimes(configserverConfig);
        tenantsMaintainer = new TenantsMaintainer(applicationRepository, curator, defaults.tenantsMaintainerInterval);
        zooKeeperDataMaintainer = new ZooKeeperDataMaintainer(applicationRepository, curator, defaults.defaultInterval);
        fileDistributionMaintainer = new FileDistributionMaintainer(applicationRepository, curator, defaults.defaultInterval, configserverConfig);
    }

    @Override
    public void deconstruct() {
        tenantsMaintainer.deconstruct();
        zooKeeperDataMaintainer.deconstruct();
        fileDistributionMaintainer.deconstruct();
    }

    /*
     * Default values from config. If one of the values needs to be changed, add the value to
     * configserver-config.xml in the config server application directory and restart the config server
     */
    private static class DefaultTimes {

        private final Duration defaultInterval;
        private final Duration tenantsMaintainerInterval;

        DefaultTimes(ConfigserverConfig configserverConfig) {
            this.defaultInterval = Duration.ofMinutes(configserverConfig.maintainerIntervalMinutes());
            boolean isCd = configserverConfig.system().equals(SystemName.cd.name());
            // TODO: Want job control or feature flag to control when to run this, for now use a very
            // long interval to avoid running the maintainer except in CD
            this.tenantsMaintainerInterval = isCd
                    ? defaultInterval
                    : Duration.ofMinutes(configserverConfig.tenantsMaintainerIntervalMinutes());
        }
    }

}
