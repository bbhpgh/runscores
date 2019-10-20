package me.zohar.runscore.storage.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.storage.domain.Storage;

@Data
public class StorageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 文件大小
	 */
	private Long fileSize;

	private String url;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date uploadTime;

	/**
	 * 关联的业务
	 */
	private String associateBiz;

	/**
	 * 关联id
	 */
	private String associateId;

	public static StorageVO convertFor(Storage storage) {
		if (storage == null) {
			return null;
		}
		StorageVO vo = new StorageVO();
		BeanUtils.copyProperties(storage, vo);
		return vo;
	}

}
