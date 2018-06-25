package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.application.util.domain.Response;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典服务类
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 插入一条数据
     *
     * @param dictionary
     * @return
     */
   boolean insertDetail(Dictionary dictionary);

    /**
     * 批量删除，删除包括所有子节点
     *
     * @param ids
     * @return
     */
    boolean batchDelete(Long[] ids);

    /**
     * 根据code查询
     *
     * @param code
     * @return
     */
    Dictionary findByCode(String code);

    /**
     * 根据codes查询
     *
     * @param code
     * @return
     */
    Response<Map<String,List<Dictionary>>> findByCodes(String codes);
    
    /**
     * 列出所有根节点
     *
     * @return
     */
    List<Dictionary> listRootDictionary();

    /**
     * 分页，条件查询
     *
     * @param parentId
     * @param code
     * @param cnName
     * @param enName
     * @param pageNo
     * @param pageSize
     * @param pageSortFiled
     * @param pageSortType
     * @return
     */
    Page<Dictionary> listByPage(Long parentId, String code, String cnName, String enName, int pageNo, int pageSize, String pageSortFiled, String pageSortType);

    /**
     * 获取以 id 为主键的子节点（循环层级包含孙节点）的id，以“,”分隔
     *
     * @param id            当前查找对象的父节点id
     * @param includeParent 是否包含父节点
     * @param layer         以父节点为 0 ，当前查找对象为 1，往下查找多少层
     * @return
     */
    List<Dictionary> listChildren(long id, Boolean includeParent, Integer layer);

    /**
     * 检查是否有子节点
     *
     * @param id
     * @return
     */
    boolean chedkHasChild(Long id);

    /**
     * 查询code是否已经存在
     *
     * @param code
     * @return
     */
    boolean checkCode(String code);

    
    List<Dictionary> selectAll();
    
    Dictionary updateInfo(Dictionary dictionary);
    
  
    /**
     * 插入或者更新一条数据
     * @param dictionary
     * @return
     */
    Response<Dictionary> editOrInsert(Dictionary dictionary);
    /**
     * 详细的插入一条数据
     * @param dictionary
     * @return
     */
    /**
     * 逻辑删除一条数据
     * @param dictionary
     */
    void updateDicParid(Dictionary dictionary);
    
    /**
     * 更新一条数据
     * @param dictionary
     */
    Response<Dictionary> update(Dictionary dictionary);
    
    //新增一条数据
   Response<Dictionary> insertDict(Dictionary dictionary);
    
   //查询出所有的子节点
   List<Dictionary> selectByparentId(Long Id);

    /**
     * 初始化字典Dictionary页面信息
     * @return
     */
	List<Dictionary> infoDictionary();

	/**
	 * 根据字典code查询字典集合
	 * @param id
	 * @return
	 */
	List<Dictionary> selcetListDictionary(String id);

	 /**
     * 根据code值查询字典子节点对象集合
     * @param code
     * @return
     */
	List<Dictionary> selcetChildListDictionary(String code);

	
	//根据id查询字典对象
	Dictionary selectDictionaryById(Long id);

    /**
     * 根据code值查询字典子节点对象集合
     * @param code
     *
     * @return
     */
    List<Dictionary> findByPCodeAndValue(String code,String value);

}
