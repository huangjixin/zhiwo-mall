package com.fulan.application.mapper;

import com.fulan.api.information.domain.Activity;
import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.domain.News;
import com.fulan.api.information.domain.NewsViewDetails;
import com.fulan.api.information.vo.InformationContentVO;
import com.fulan.api.information.vo.InformationVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fsl on 2018/4/8.
 */
@Service

public interface InformationMapper {
    List<InformationVO> queryInformationListByInformationType(@Param("informationType") Integer informationType,
                                                              @Param("accountId") String accountId,
                                                              @Param("deptId") String deptId,
                                                              @Param("status") Integer status,
                                                              @Param("companyId")String companyId,
                                                              @Param("branchCompanyId")String branchCompanyId
                                                              );

    //List<News> queryInformationListByStatus(@Param("informationType") String informationType, @Param("status") Integer status);

    Integer selectActivitySignUp(Long activityId);

    Long queryHasSignUp(@Param("accountId") String accountId, @Param("activityId")Long activityId);

    Integer queryReadCount(@Param("informationId")Long informationId,@Param("type")Integer type);

    Integer insertSignUp(ApplyDetail applyDetail);

    Integer queryActivityCount(Long activityId);

    Integer queryHasSignUpPerson(Long activityId);

    InformationContentVO queryInfoContent(Long informationId);

    InformationContentVO queryNewsContent(Long informationId);

    void insertReadCount(NewsViewDetails newsV);

    Long queryHasActivity(Long activityId);
}
