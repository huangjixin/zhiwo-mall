package com.fulan.application.service;

import java.util.List;

import com.fulan.api.personnel.domain.ErTag;
import com.fulan.application.util.domain.Response;

public interface ErTagService {

	
	Response<String> addErTag(ErTag erTag);

	Response<String> removeErTag(Long personnelId, Long tagId);
	
	List<ErTag> findBypersonnelId(Integer personnelId);
	
	
	Response<String> removeErTagbypersonnelId(Long personnelId);
}
