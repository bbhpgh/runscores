package me.zohar.runscore.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.storage.domain.Storage;

public interface StorageRepo extends JpaRepository<Storage, String>, JpaSpecificationExecutor<Storage> {

}
