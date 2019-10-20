package me.zohar.runscore.admin.storage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.util.ArrayUtil;
import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.storage.service.StorageService;
import me.zohar.runscore.storage.vo.StorageVO;

@Controller
@RequestMapping("/storage")
public class StorageController {

	@Autowired
	private StorageService storageService;

	@GetMapping("/fetch/{id:.+}")
	public ResponseEntity<Resource> fetch(@PathVariable String id) {
		StorageVO vo = storageService.findById(id);
		if (vo == null) {
			return ResponseEntity.notFound().build();
		}

		String fileType = vo.getFileType();
		MediaType mediaType = MediaType.parseMediaType(fileType);
		Resource file = storageService.loadAsResource(vo.getId());
		if (file == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().contentType(mediaType).body(file);
	}

	@PostMapping("/uploadPic")
	@ResponseBody
	public Result uploadPic(@RequestParam("file_data") MultipartFile[] files) throws IOException {
		if (ArrayUtil.isEmpty(files)) {
			return Result.fail("请选择要上传的图片");
		}
		List<String> storageIds = new ArrayList<>();
		for (MultipartFile file : files) {
			String filename = file.getOriginalFilename();
			String storageId = storageService.uploadGatheringCode(file.getInputStream(), file.getSize(),
					file.getContentType(), filename);
			storageIds.add(storageId);
		}
		return Result.success().setData(storageIds);
	}

}
