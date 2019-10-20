package me.zohar.runscore.dictconfig.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.dictconfig.domain.DictItem;

public interface DictItemRepo extends JpaRepository<DictItem, String>, JpaSpecificationExecutor<DictItem> {

	DictItem findByDictTypeDictTypeCodeAndDictItemCode(String dictTypeCode, String dictItemCode);

	List<DictItem> findByDictTypeDictTypeCodeOrderByOrderNo(String dictTypeCode);
	
	List<DictItem> findByDictTypeIdOrderByOrderNo(String dictTypeId);

}
