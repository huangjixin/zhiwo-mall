package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.system.domain.Dictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {
	
	void updateDicParentId(Dictionary dictionary);
	void updateDictionary(Dictionary dictionary);
	void insertDictionary(Dictionary dictionary);
	List<Dictionary> selectAllChild(Long id);
	
	/**
	 * 初始化字典Dictionary页面信息
	 * @return
	 */
	List<Dictionary> infoDictionary();
	
	
    /**
     * 获取以 id 为主键的子节点（循环层级包含孙节点）的id，以“,”分隔
     *
     * @param id                当前查找对象的父节点id
     * @param includeParent     是否包含父节点
     * @param layer             以父节点为 0 ，当前查找对象为 1，往下查找多少层
     * @param enabledSensitive  true：仅取可用的数据字典，false：不管可用不可用都取
     * @return
     */
    @Select("SELECT getDictChildList(#{id}, #{includeParent}, #{layer}, #{enabledSensitive})")
    String getDictChildList(@Param("id") Long id,
                            @Param("includeParent") Boolean includeParent,
                            @Param("layer") Integer layer,
                            @Param("enabledSensitive") Boolean enabledSensitive);
	List<Dictionary> selcetListDictionary(String code);
	
	
	List<Dictionary> selcetChildListDictionary(String code);
	
	
	
	Dictionary selectDictionaryById(Long id);
	
    
	List<Dictionary> findDictionaryByCode(String code);
	
	List<Dictionary> selectAllList(@Param("code") String code,@Param("cnName") String cnName,@Param("enName") String enName,@Param("id") Long id);

	List<Dictionary> findByPCodeAndValue(@Param("code") String code,@Param("value") String value);
}
