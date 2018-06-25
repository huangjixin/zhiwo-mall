package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.application.mapper.DictionaryMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.DictionaryService;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@Service
@Transactional
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private DictionaryMapper dictionaryMapper;



    @Override
    public Dictionary findByCode(String code) {
        Dictionary dictExample = new Dictionary();
        dictExample.setCode(code);
        dictExample.setEnabled(Boolean.TRUE);

        return dictionaryMapper.selectOne(dictExample);
    }
    
    @Override
	public Response<Map<String,List<Dictionary>>> findByCodes(String codes) {
    	Response<Map<String,List<Dictionary>>> response= new Response<Map<String,List<Dictionary>>>(Response.SUCCESS,"根据codes查找数据字典成功");
    	//解析codes(,分隔)
    	String array_codes[] = codes.split(",");
    	Map<String,List<Dictionary>> dicts = new HashMap<String,List<Dictionary>>();
    	for(int i=0;i<array_codes.length;i++){
    		List<Dictionary> dictList = dictionaryMapper.findDictionaryByCode(array_codes[i]);
    		dicts.put(array_codes[i], dictList);
    	}
    	response.setData(dicts);
    	return response;
	}

    @Override
    public List<Dictionary> listRootDictionary() {
        Dictionary dictionary = new Dictionary();
        dictionary.setRootId(new Long(0));
        dictionary.setEnabled(Boolean.TRUE);
        return dictionaryMapper.selectList(new EntityWrapper<Dictionary>(dictionary));
    }

    @Override
    public Page<Dictionary> listByPage(Long parentId, String code, String cnName, String enName, int pageNo, int pageSize, String pageSortFiled, String pageSortType) {
        // 组装page，页数、条数、排序字段、排序方式
        Page<Dictionary> page = new Page<Dictionary>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc((null == pageSortType || "desc".equals(pageSortType)) ? false : true);

        Dictionary dictionary = new Dictionary();
        dictionary.setParentId(parentId);
        dictionary.setCode(code);
        dictionary.setEnabled(Boolean.TRUE);
        EntityWrapper<Dictionary> ew = new EntityWrapper<>(dictionary);
        if (StringUtils.isNotEmpty(cnName)) {
            ew.like("cn_name", cnName);
        }
        if (StringUtils.isNotEmpty(enName)) {
            ew.like("en_name", enName);
        }

        return this.selectPage(page, ew);
    }



    @Override
    public List<Dictionary> listChildren(long id, Boolean includeParent, Integer layer) {
        String idsStr = dictionaryMapper.getDictChildList(id, includeParent, layer, true);
        if (StringUtils.isNotEmpty(idsStr)) {
            String[] ids = idsStr.split(",");
            List<Long> idList = new ArrayList<Long>();
            for (String item : ids) {
                idList.add(new Long(item));
            }
            return this.selectBatchIds(idList);
        }
        return new ArrayList<Dictionary>();
    }

    @Override
    public boolean batchDelete(Long[] ids) {
        // 待删除id
        StringBuilder idsToDeleteSb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            idsToDeleteSb.append("," + dictionaryMapper.getDictChildList(ids[i], true, null, true));
        }
        String[] idsToDeleteStr = idsToDeleteSb.toString().split(",");

        // 去重
        Set<String> idSet = new HashSet<String>();
        for (String id : idsToDeleteStr) {
            if (StringUtils.isNotEmpty(id)) {
                idSet.add(id);
            }
        }

        List<Dictionary> dictToDelete = new ArrayList<>();
        for (String id : idSet) {
            if (StringUtils.isEmpty(id)) {
                continue;
            }
            Dictionary dictionary = new Dictionary();
            dictionary.setId(new Long(id));
            dictionary.setEnabled(Boolean.FALSE);
            dictToDelete.add(dictionary);
        }
        return this.updateBatchById(dictToDelete);
    }

    @Override
    public boolean chedkHasChild(Long id) {
        Dictionary dictExample = new Dictionary();
        dictExample.setParentId(id);
        dictExample.setEnabled(Boolean.TRUE);

        int num = this.selectCount(new EntityWrapper<Dictionary>(dictExample));
        return num > 0;
    }

    @Override
    public boolean checkCode(String code) {
        Dictionary dictExample = new Dictionary();
        dictExample.setCode(code);
        dictExample.setEnabled(Boolean.TRUE);

        int num = this.selectCount(new EntityWrapper<Dictionary>(dictExample));
        return num > 0;
    }
    
	@Override
	public List<Dictionary> selectAll() {
		Dictionary dictionary=new Dictionary();
		dictionary.setEnabled(Boolean.TRUE);
		Map<String,Object> columnMap=new HashMap<>();
		columnMap.put("enabled",dictionary.getEnabled());
		return dictionaryMapper.selectByMap(columnMap);
	}
	
	

	@Override
	@Transactional
	public Dictionary updateInfo(Dictionary dictionary) {
		
		
		dictionaryMapper.updateById(dictionary);
		
		return dictionary;
	}
//	@Override
//	public Dictionary findById(Long id) {
//		 Dictionary dictExample = new Dictionary();
//	        dictExample.setId(id);
//	        dictExample.setEnabled(Boolean.TRUE);
//	        return dictionaryMapper.selectOne(dictExample);
//	}



	@Override
	public Response<Dictionary> editOrInsert(Dictionary dictionary) {
		//查找是否有这个数据，如果有就更改，若没有则添加
		Long id=dictionary.getId();
		if(id==null){//没有则添加
			if(StringUtils.isNotEmpty(dictionary.getCode())){
				Map<String,Object> columnMap = new HashMap<>();
				columnMap.put("code", dictionary.getCode());
				columnMap.put("enabled", Boolean.TRUE);
				List<Dictionary> list = this.selectByMap(columnMap);
				if(CollectionUtils.isNotEmpty(list)){
					Response<Dictionary> res = new Response<>(Response.ERROR,"code不可重复");
					return res;
				}
			}
			if(StringUtils.isNotEmpty(dictionary.getCnName())){
				Map<String,Object> columnMap = new HashMap<>();
				columnMap.put("cn_name", dictionary.getCnName());
				columnMap.put("enabled", Boolean.TRUE);
				List<Dictionary> list = this.selectByMap(columnMap);
				if(CollectionUtils.isNotEmpty(list)){
					Response<Dictionary> res = new Response<>(Response.ERROR,"中文名称不可重复");
					return res;
				}
			}
			if(StringUtils.isNotEmpty(dictionary.getEnName())){
				Map<String,Object> columnMap = new HashMap<>();
				columnMap.put("en_name", dictionary.getEnName());
				columnMap.put("enabled", Boolean.TRUE);
				List<Dictionary> list = this.selectByMap(columnMap);
				if(CollectionUtils.isNotEmpty(list)){
					Response<Dictionary> res = new Response<>(Response.ERROR,"英文名称不可重复");
					return res;
				}
			}
			Response<Dictionary> res=insertDict(dictionary);
			return res;
		}else{
			String code =dictionary.getCode();
			String cnName= dictionary.getCnName();
			String enName= dictionary.getEnName();
			if(StringUtils.isNotEmpty(code)){
				List<Dictionary> list = dictionaryMapper.selectAllList(code, null, null,id);
				if(list.size()>0){
					Response<Dictionary> res = new Response<>(Response.ERROR,"code不可重复");
					return res;
				}
			}
			if(StringUtils.isNotEmpty(cnName)){
				List<Dictionary> list = dictionaryMapper.selectAllList(null, cnName, null,id);
				if(list.size()>0){
					Response<Dictionary> res = new Response<>(Response.ERROR,"中文名称不可重复");
					return res;
				}
			}
			if(StringUtils.isNotEmpty(enName)){
				List<Dictionary> list = dictionaryMapper.selectAllList(null, null, enName,id);
				if(list.size()>0){
					Response<Dictionary> res = new Response<>(Response.ERROR,"英文名称不可重复");
					return res;
				}
			}
			Response<Dictionary> res=update(dictionary);
			return res;
		}
		
		
		
		
	}

	@Override
	@Transactional
	public void updateDicParid(Dictionary dictionary) {
		dictionaryMapper.updateDicParentId(dictionary);
		
	}

	@Override
	@Transactional
	public Response<Dictionary> update(Dictionary dictionary) {
		Date date=new Date();
	    dictionary.setUpdateTime(date);
		dictionaryMapper.updateDictionary(dictionary);
		/*List<Dictionary> list = dictionaryMapper.selcetListDictionary(dictionary.getCode());
		Dictionary dic = list.get(0);
		String idl =""+dic.getId();
		String id = ""+dictionary.getId();
		if((!idl.equals("")) && (!idl.equals(id))){
			Response<Dictionary> res=new Response<Dictionary>(Response.ERROR,"已存在相同code，更新失败"); 
			return res;
		}*/
		Response<Dictionary> res=new Response<Dictionary>(Response.SUCCESS,"更新成功"); 
		res.setData(dictionary);
		return res;
	}
	//插入一条数据(新增)
	@Override
	@Transactional
   public Response<Dictionary> insertDict(Dictionary dictionary){
		 Dictionary dictExample = new Dictionary();
	        dictExample.setCode(dictionary.getCode());
	        dictExample.setEnabled(Boolean.TRUE);
	        dictExample.setParentId(dictionary.getParentId());//有相同的父节点和code则不能插入
	        int num = dictionaryMapper.selectCount(new EntityWrapper<Dictionary>(dictExample));
	        if (num > 0) {
	            Response res= new Response<Dictionary>(Response.ERROR,"存在相同的字典,添加失败");
	            res.setData(dictionary);
	            return res;
	        }

	        // 设置id、设置默认可用
	        dictionary.setId(idGenerator.generate());
	        dictionary.setEnabled(Boolean.TRUE);
	        if(dictionary.getCode()==null){
	        	dictionary.setCode("11");
	        }
	        if(dictionary.getCnName()==null){
	        	dictionary.setCnName("12");
	        }
	        if(dictionary.getValue()==null){
	        	dictionary.setValue("13");
	        }
	        if(dictionary.getEnabled()==null){
	        	dictionary.setEnabled(Boolean.TRUE);
	        }
	        if(dictionary.getSystemFlag()==null){
	        	dictionary.setSystemFlag(1);
	        }
	        if(dictionary.getCreateById()==null){
	        	dictionary.setCreateById(520L);
	        }
	        if(dictionary.getCreateByName()==null){
	        	dictionary.setCreateByName("上帝");
	        }
	        // 设置layer、rootId、parentId
	        if (null == dictionary.getParentId() || dictionary.getParentId() == 0) {
	            dictionary.setLayer(0);
	            dictionary.setRootId(new Long(0));
	            dictionary.setParentId(new Long(0));
	        } else {
	            Dictionary dictForParent = new Dictionary();
	            dictForParent.setId(dictionary.getParentId());
	            dictForParent.setEnabled(Boolean.TRUE);
	            Dictionary parent = dictionaryMapper.selectOne(dictForParent);

	            dictionary.setLayer(parent.getLayer() + 1);
	            if (parent.getParentId() == 0) { // 若父对象的parentId为0，则父为root
	                dictionary.setRootId(parent.getId());
	            } else { // 父对象parentId不为0，则父为非root，父rootId有真实值
	                dictionary.setRootId(parent.getRootId());
	            }
	        }

	        // 设置排序
	        if (null == dictionary.getSort()) {
	            Dictionary layerDict = new Dictionary();
	            layerDict.setLayer(dictionary.getLayer());
	            layerDict.setRootId(dictionary.getRootId());
	            layerDict.setEnabled(Boolean.TRUE);

	            int layerCount = dictionaryMapper.selectCount(new EntityWrapper<Dictionary>(layerDict));
	            dictionary.setSort(layerCount + 1);
	        }
	        
	        Date date=new Date();
	       
	       dictionary.setCreateTime(date);

          dictionaryMapper.insertDictionary(dictionary);
          Response<Dictionary> response= new Response<Dictionary>(Response.SUCCESS,"新增字典成功");
          response.setData(dictionary);
          return response;
	
}


	/**
	 * 初始化字典Dictionary页面信息
	 */
	@Override
	public List<Dictionary> infoDictionary() {
		
		
		return dictionaryMapper.infoDictionary();
	}

	@Override
	public List<Dictionary> selectByparentId(Long id) {
	return	dictionaryMapper.selectAllChild(id);
	}

	@Override
	public boolean insertDetail(Dictionary dictionary) {
		Dictionary dictExample = new Dictionary();
        dictExample.setCode(dictionary.getCode());
        dictExample.setEnabled(Boolean.TRUE);
        int num = dictionaryMapper.selectCount(new EntityWrapper<Dictionary>(dictExample));
        if (num > 0) {
          
        }


        // 设置id、设置默认可用
        dictionary.setId(idGenerator.generate());
        dictionary.setEnabled(Boolean.TRUE);

        // 设置layer、rootId、parentId
        if (null == dictionary.getParentId() || dictionary.getParentId() == 0) {
            dictionary.setLayer(0);
            dictionary.setRootId(new Long(0));
            dictionary.setParentId(new Long(0));
        } else {
            Dictionary dictForParent = new Dictionary();
            dictForParent.setId(dictionary.getParentId());
            dictForParent.setEnabled(Boolean.TRUE);
            Dictionary parent = dictionaryMapper.selectOne(dictForParent);

            dictionary.setLayer(parent.getLayer() + 1);
            if (parent.getParentId() == 0) { // 若父对象的parentId为0，则父为root
                dictionary.setRootId(parent.getId());
            } else { // 父对象parentId不为0，则父为非root，父rootId有真实值
                dictionary.setRootId(parent.getRootId());
            }
        }

        // 设置排序
        if (null == dictionary.getSort()) {
            Dictionary layerDict = new Dictionary();
            layerDict.setLayer(dictionary.getLayer());
            layerDict.setRootId(dictionary.getRootId());
            layerDict.setEnabled(Boolean.TRUE);

            int layerCount = dictionaryMapper.selectCount(new EntityWrapper<Dictionary>(layerDict));
            dictionary.setSort(layerCount + 1);
        }
        
      
     return 1== dictionaryMapper.insert(dictionary);
     
	}


	@Override
	public List<Dictionary> selcetListDictionary(String code) {
		List<Dictionary> list=dictionaryMapper.selcetListDictionary(code);
		return list;
	}

	@Override
	public List<Dictionary> selcetChildListDictionary(String code) {
		List<Dictionary> list=dictionaryMapper.selcetChildListDictionary(code);
		return list;
	}

	@Override
	public Dictionary selectDictionaryById(Long id) {
		
		return dictionaryMapper.selectDictionaryById(id);
	}

	@Override
	public List<Dictionary> findByPCodeAndValue(String code, String value) {
		return dictionaryMapper.findByPCodeAndValue(code,value);
	}

}
