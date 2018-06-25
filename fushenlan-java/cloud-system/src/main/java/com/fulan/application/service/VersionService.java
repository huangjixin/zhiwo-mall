package com.fulan.application.service;

import com.fulan.api.system.domain.Version;
import com.fulan.application.util.domain.Response;

public interface VersionService {

	Response<Boolean> insert(Version version);

	Boolean delete(Long id);

	Response<Boolean> updateById(Version version);

	Response<Version> findById(Long id);

	Response<String> theNewstVersion(int type);

}
