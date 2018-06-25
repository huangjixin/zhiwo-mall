package com.fulan.application.service;

import java.util.List;

import com.fulan.api.plan.domain.TagTarget;
import com.fulan.application.util.domain.Response;

public interface TagTargetService {

	Response<String> addTagTarget(TagTarget tagtarget);

	List<TagTarget> findAllPublicClassTag(Integer type);

}
