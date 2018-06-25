package com.fulan.api.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.system.domain.Dictionary;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 数据字典服务类
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@FeignClient(name = "system")
public interface DictionaryService {

    @PostMapping("/dictionary/insert")
    Boolean insert(@RequestBody Dictionary dictionary);
    
    @PostMapping("/manage/dictionary/insert")
    Response<String> insertDictionary(@RequestBody Dictionary dictionary);
    
    @PostMapping("/manage/dictionary/insertdic")
    Response<Dictionary> insertDic(@RequestBody Dictionary dictionary);

    @PostMapping("/dictionary/delete")
    Boolean delete(@RequestParam("id") Long id);
    
    @PostMapping("/manage/dictionary/delete")
    Response<String> deleteDictionary(@RequestParam("id") Long id);
    
    @GetMapping("/manage/dictionary/dele")
    Response<String> deleteDiction(@RequestParam("id") Long id);
    
    
    @PostMapping("/dictionary/batch/delete")
    Boolean deleteBacth(Long[] id);
    
    @PostMapping("/manage/dictionary/batch/delete")
    Response<String> deletes(@RequestParam("id") Long[] ids);
    
    
    @PostMapping(value = "/dictionary/update")
    Boolean update(@RequestBody Dictionary dictionary);
    
    @PostMapping(value = "/manage/dictionary/updateInfo")
    Response<Dictionary> updateDictionary(@RequestBody Dictionary dictionary);

    @GetMapping(value = "/dictionary/find")
    Dictionary findById(@RequestParam("id") Long id);
    
    @GetMapping(value = "/manage/dictionary/find")
    Response<Dictionary> findDictionaryById(@RequestParam("id") Long id);

    @GetMapping(value = "/dictionary/code/find")
    Dictionary findByCode(@RequestParam("code") String code);
    
    @PostMapping(value = "/dictionary/code/findByCodes")
    Response<Map<String,List<Dictionary>>> findByCodes(@RequestParam("codes") String codes);
    
    @GetMapping(value = "/manage/dictionary/code/find")
    Response<Dictionary> findByCodeDictionary(@RequestParam("code") String code);

    @GetMapping(value = "/dictionary/list")
    PageInfo<Dictionary> listByPage(
            @RequestParam(name = "parentId", defaultValue = "0") Long parentId,
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "cnName", required = false) String cnName,
            @RequestParam(name = "enName", required = false) String enName,

            @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "pageSortFiled", defaultValue = "sort") String pageSortFiled,
            @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType
    );

    @GetMapping(value = "/dictionary/root/list")
    List<Dictionary> listRootDictionary();

    @GetMapping(value = "/dictionary/child/list")
    public List<Dictionary> listChildDictionary(@RequestParam(name = "id", defaultValue = "0") Long id,
                                                @RequestParam(name = "includeParent", defaultValue = "false") Boolean includeParent,
                                                @RequestParam(name = "layer", defaultValue = "1") Integer layer);
  
    
    @GetMapping(value = "/manage/dictionary/child/list")
    public List<Dictionary> listChildDic(@RequestParam(name = "id", defaultValue = "0") Long id,
                                                @RequestParam(name = "includeParent", defaultValue = "false") Boolean includeParent,
                                                @RequestParam(name = "layer", defaultValue = "1") Integer layer);
    
    
    @GetMapping(value = "/manage/dictionary/selectAll")
    List<Dictionary> selectAll();
    
    //新增或者是修改一条数据
    @PostMapping(value="/manage/dictionary/editOrInsert1")
    Response<Dictionary> updateOrInsert(@RequestBody Dictionary dictionary);

    

    //初始化Dictionaary字典数据
    @GetMapping(value = "/manage/dictionary/infoDictary")
	List infoDictionary();
    

    
    //查询某节点的子节点
    @GetMapping(value="/manage/dictionary/selectAllChild")
    List<Dictionary> selectAllChild(@RequestParam("id") Long id);
    
    

    /**
     * 根据字典code查询字典集合
     * @param code
     * @return
     */
    @GetMapping(value="/dictionary/selcetListDictionary")
	Dictionary selcetListDictionary(@RequestParam("code") String code);

    
    /**
     * 根据code值查询字典子节点对象集合
     * @param code
     * @return
     */
    @GetMapping(value="/dictionary/selcetChildListDictionary")
	List<Dictionary> selcetChildListDictionary(@RequestParam("code") String code);

    //根据id查询字典对象
    @GetMapping(value="/manage/dictionary/selectDictionaryById")
	Dictionary selectDictionaryById(@RequestParam("id") Long id);

    @PostMapping(value = "/dictionary/code/findByPCodeAndValue")
    List<Dictionary> findByPCodeAndValue(@RequestParam("code") String code,@RequestParam("ip") String value);
}
