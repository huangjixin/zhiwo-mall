package com.fulan.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulan.api.system.domain.Attachment;
import com.fulan.application.mapper.AttachmentMapper;
import com.fulan.application.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	AttachmentMapper attachmentMapper;
	
	@Override
	public int save(List<Attachment> uploadList) {
		// TODO Auto-generated method stub
		int i=0;
		if(uploadList!=null && uploadList.size()>0){
			for(Attachment upload :uploadList){
				attachmentMapper.insert(upload);
				i++;
			}
		}
		return i;
	}

	@Override
	public Boolean insert(Attachment attachment) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		if(attachment.getHostId()!=null && attachment.getCategory() != null){
			attachmentMapper.updateIsDeleteByHostIdAndCategory(attachment);
    	}
		int ins=attachmentMapper.insert(attachment);
		if(ins>0){
			flag=true;
		}
		return flag;
	}

	@Override
	public Boolean updateById(Attachment attachment) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		if(attachment.getHostId()!=null && attachment.getCategory() != null){
			attachmentMapper.updateIsDeleteByHostIdAndCategory(attachment);
    	}
		int ins=attachmentMapper.updateById(attachment);
		if(ins>0){
			flag=true;
		}
		return flag;
	}
	
	
	
	@Override
	public Boolean updateByattachmentId(Attachment attachment) {
		
		Boolean flag=false;
		
		int ins=attachmentMapper.updateById(attachment);
		if(ins>0){
			flag=true;
		}
		return flag;
	}

	@Override
	public Boolean updateIsDeleteById(Long id) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		//int ins=attachmentMapper.deleteById(id);
		int ins=attachmentMapper.updateIsDeleteById(id);
		if(ins>0){
			flag=true;
		}
		return flag;
	}

	@Override
	public Attachment selectById(Long id) {
		
		return attachmentMapper.selectByIdAndIsDelete(id);
	}

	@Override
	public List<Attachment> findFileByTableAndHostId(Integer category, Long hostId) {
		
		return attachmentMapper.findByTableAndHostId(category,hostId);
	}

	@Override
	public List<Attachment> selectByhostId(String hostId) {
		
		return attachmentMapper.selectByhostId(hostId);
	}

	
	
	
	
}
