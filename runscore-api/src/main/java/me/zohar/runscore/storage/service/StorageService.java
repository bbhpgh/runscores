package me.zohar.runscore.storage.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import me.zohar.runscore.common.exception.BizError;
import me.zohar.runscore.common.exception.BizException;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.dictconfig.ConfigHolder;
import me.zohar.runscore.storage.domain.Storage;
import me.zohar.runscore.storage.repo.StorageRepo;
import me.zohar.runscore.storage.vo.StorageVO;

@Service
public class StorageService {

	@Autowired
	private StorageRepo storageRepo;

	public StorageVO findById(String id) {
		return StorageVO.convertFor(storageRepo.getOne(id));
	}

	public Resource loadAsResource(String id) {
		try {
			String localStoragePath = ConfigHolder.getConfigValue("localStoragePath");
			Path file = Paths.get(localStoragePath).resolve(id);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String uploadGatheringCode(InputStream inputStream, Long fileSize, String fileType, String fileName) {
		if (!fileType.startsWith("image/")) {
			throw new BizException(BizError.只能上传图片类型的收款码);
		}
		String id = IdUtils.getId();
		try {
			String localStoragePath = ConfigHolder.getConfigValue("localStoragePath");
			Files.copy(inputStream, Paths.get(localStoragePath).resolve(id), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + id, e);
		}

		Storage storage = new Storage();
		storage.setId(id);
		storage.setFileName(fileName);
		storage.setFileType(fileType);
		storage.setFileSize(fileSize);
		storage.setUploadTime(new Date());
		storageRepo.save(storage);
		return id;
	}

}
