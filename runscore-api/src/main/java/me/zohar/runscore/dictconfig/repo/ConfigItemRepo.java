package me.zohar.runscore.dictconfig.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.dictconfig.domain.ConfigItem;

public interface ConfigItemRepo extends JpaRepository<ConfigItem, String>, JpaSpecificationExecutor<ConfigItem> {

	ConfigItem findByConfigCode(String configCode);

}
