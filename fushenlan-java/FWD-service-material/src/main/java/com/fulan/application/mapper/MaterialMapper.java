package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.material.vo.MaterialDTO;
import com.fulan.api.material.vo.MaterialVO;
import com.fulan.api.material.vo.MaterialVoCMS;

@Mapper
public interface MaterialMapper extends BaseMapper<Material>{
    int deleteByPrimaryKey(Long id);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
    
    List<Material> materialManageSearch(Page<Material> page, @Param("keyWord") String keyWord,
                                        @Param("type") String type,
                                        @Param("submitter") String submitter,
                                        @Param("pubType") String pubType,
                                        @Param("id") String id,
                                        @Param("groupId") String groupId,
                                        @Param("tagId") String tagId,
                                        @Param("uploadTimeBegin") String uploadTimeBegin,
                                        @Param("uploadTimeEnd") String uploadTimeEnd,
                                        @Param("loginUser") String loginUser,
                                        @Param("pageNo") int pageNo,
                                        @Param("pageSize") int pageSize);
    int materialManageSearchCount( @Param("keyWord") String keyWord,
						            @Param("type") String type,
						            @Param("submitter") String submitter,
						            @Param("pubType") String pubType,
						            @Param("id") String id,
						            @Param("groupId") String groupId,
						            @Param("tagId") String tagId,
						            @Param("uploadTimeBegin") String uploadTimeBegin,
						            @Param("uploadTimeEnd") String uploadTimeEnd,
						            @Param("loginUser") String loginUser
						            );

	int deleteRelation(List<String> asList);

	int share(Map<String,Long> parms);

	int deleteShar(String materialId);

	int selectMaterAndCourseRe(String id);
	
	MaterialCourseVo  checkMaterialInfo(String id);
	
	MaterialDTO selectForId(Long id);

    void deleteBatch(Long id);

    Integer updateById(Material material);


    List<MaterialDTO> findMaterialByPage(Page<MaterialDTO> page, @Param("materialVO") MaterialVO materialVO, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

	List<String> getAllShared(String materialId);
	
	/**
	 * 关联中间表查询资料库
	 * @param courseId 课程ID
	 * @return
	 */
    List<Material> listMaterialByCourseId(@Param("courseId")Long courseId);
    
    List<MaterialShare> listMaterialShareByMaterialId(@Param("materialIds") String materialIds);

    int getMaterialCount(Map<String, Object> paramMap);
    
    /**
     * 资料列表查询
     * @param paramMap
     * @param page
     * @return
     */
    List<MaterialVoCMS> listMaterialByPage(Page<MaterialVoCMS> page,Map<String, Object> paramMap);

}
