package com.fulan.application.service;

import com.fulan.api.information.vo.InformationContentVO;
import com.fulan.api.information.vo.InformationVO;
import com.fulan.application.util.domain.Response;

import java.util.List;

/**
 * Created by fsl on 2018/4/8.
 */
public interface InformationService {

    public Response<List<InformationVO>> queryInformationList(Integer informationType);

    Response<String> activitySignUp(Long activityId);

    Response<InformationContentVO> queryDetailById(Long informationId, Integer infoNewsType);

    // Response<List<String>> queryDetailById(String informationId, String infoNewsType);
}
