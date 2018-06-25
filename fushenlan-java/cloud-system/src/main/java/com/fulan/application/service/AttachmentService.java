package com.fulan.application.service;

import java.util.List;

import com.fulan.api.system.domain.Attachment;



public interface AttachmentService {

	int save(List<Attachment> uploadList);

	Boolean insert(Attachment attachment);

	Boolean updateById(Attachment attachment);

	Boolean updateByattachmentId(Attachment attachment);
	
	Boolean updateIsDeleteById(Long id);

	Attachment selectById(Long id);

	List<Attachment> findFileByTableAndHostId(Integer category, Long hostId);
	
	
	List<Attachment> selectByhostId(String hostId);
	
	
	

}
