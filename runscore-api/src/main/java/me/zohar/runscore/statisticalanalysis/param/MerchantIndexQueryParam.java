package me.zohar.runscore.statisticalanalysis.param;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MerchantIndexQueryParam {

	@NotBlank
	private String merchantId;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;

}
