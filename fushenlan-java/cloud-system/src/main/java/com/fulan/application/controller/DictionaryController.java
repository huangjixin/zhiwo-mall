package com.fulan.application.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.system.domain.Dictionary;
import com.fulan.application.orm.page.PageUtil;
import com.fulan.application.service.DictionaryService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 数据字典前端控制器
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@Api(tags = "DictionaryApi", description = "数据字典接口")
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private static final Logger LOG = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 新增数据字典
     *
     * @param dictionary
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "新增一个字典数据", response = Response.class)
    @PostMapping("/insert")
    public Boolean insert(
            @ApiParam(
                    name = "dictionary",
                    value = "不能为空字段：</br>code</br>cnName</br>value</br>systemFlag</br>" +
                            "选传参数：</br>parentId:当为顶层节点时不传或传0</br>sort:同层级排序"
            )
            @RequestBody Dictionary dictionary) {
        try {
            return dictionaryService.insertDetail(dictionary);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 删除单个数据字典
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "删除单个数据字典（会同步循环删除该节点下所有子节点）", response = Response.class)
    @PostMapping("/delete")
    public Boolean delete(@RequestParam("id") Long id) {
        try {
            return dictionaryService.batchDelete(new Long[]{id});
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 批量删除数据字典
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "批量删除数据字典（会同步循环删除节点下所有子节点）", response = Response.class)
    @PostMapping("/batch/delete")
    public Boolean deleteBacth(
            @ApiParam(name = "id", value = "要删除的数据字典的id，可传入多个id") @RequestParam("id") Long[] id
    ) {
        try {
            return dictionaryService.batchDelete(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 更新数据字典
     *
     * @param dictionary
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "根据id更新数据字典", response = Response.class)
    @PostMapping(value = "/update")
    public Boolean update(@ApiParam("id不可不传") @RequestBody Dictionary dictionary) {
        try {
            return dictionaryService.updateById(dictionary);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 根据id查找单个字典
     *
     * @param id 字典Id
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "根据id查找单个字典", response = Response.class)
    @ApiImplicitParams({
     	  @ApiImplicitParam(name="id",value="字典id",dataType="long", paramType = "query",example="1")})
    @GetMapping(value = "/find")
    public Dictionary findById(@RequestParam("id") Long id) {
        try {
            return dictionaryService.selectById(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }

    }

    /**
     * 根据code查询数据字典
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "根据code查询数据字典", response = Response.class)
    @GetMapping(value = "/code/find")
    public Dictionary findByCode(@RequestParam("code") String code) {
        try {
            return dictionaryService.findByCode(code);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 根据code查询数据字典
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "根据code查询数据字典", response = Response.class)
    @GetMapping(value = "/code/findByCode")
    public Response<Dictionary> selectByCode(@RequestParam("code") String code) {
        try {
            Response<Dictionary>  resp = new Response<Dictionary> (Response.SUCCESS,"查询数据字典成功");
        	resp.setData( dictionaryService.findByCode(code));
            return resp;
        } catch (Exception e) {
        	return new Response<Dictionary> (Response.ERROR,"查询数据字典失败");
        }
    }

    /**
     * 根据codes查询数据字典
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "根据codes查询数据字典", response = Response.class)
    @PostMapping(value = "/code/findByCodes")
    public Response<Map<String,List<Dictionary>>> findByCodes(@RequestParam("codes") String codes) {
        try {
            return dictionaryService.findByCodes(codes);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 分页查询数据字典
     *
     * @param parentId      父字典Id  （精确匹配）
     * @param code          字典Code （精确匹配）
     * @param cnName        字典中文名（模糊匹配）
     * @param enName        字典英文名（模糊匹配）
     * @param pageNo        要跳转的页数
     * @param pageSize      每页条数
     * @param pageSortFiled 排序字段名
     * @param pageSortType  顺序：asc,倒叙：desc
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "分页查询字典", response = Response.class)
    @GetMapping(value = "/list")
    public PageInfo<Dictionary> listByPage(
            @ApiParam(name = "parentId", value = "父字典Id, 不传则默认查询所有根节点", required = false) @RequestParam(name = "parentId", defaultValue = "0") Long parentId,
            @ApiParam(name = "code", value = "字典code", required = false) @RequestParam(name = "code", required = false) String code,
            @ApiParam(name = "cnName", value = "字典中文名（模糊匹配）", required = false) @RequestParam(name = "cnName", required = false) String cnName,
            @ApiParam(name = "enName", value = "字典英文名（模糊匹配）", required = false) @RequestParam(name = "enName", required = false) String enName,

            @ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "sort") String pageSortFiled,
            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType
    ) {
        try {
            return PageUtil.versa(dictionaryService.listByPage(parentId, code, cnName, enName, pageNo, pageSize, pageSortFiled, pageSortType));
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 查询所有顶层数据字典
     *
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "查询所有顶层数据字典", response = Response.class)
    @GetMapping(value = "/root/list")
    public List<Dictionary> listRootDictionary() {
        try {
            return dictionaryService.listRootDictionary();
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 查询当前数据字典下的子节点
     *
     * @param id            要查找的对象的父节点
     * @param includeParent 是否包含父节点（id对应的自身节点），true：包含，false：不包含，默认false
     * @param layer         以id对应对象为第0层，查询多少层子节点，默认1层
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "查询当前数据字典下的子节点", response = Response.class)
    @GetMapping(value = "/child/list")
    public List<Dictionary> listChildDictionary(
            @ApiParam("要查找的对象的父节点") @RequestParam(name = "id", defaultValue = "0") Long id,
            @ApiParam("是否包含父节点（id对应的自身节点），</br>true：包含，</br>false：不包含，默认false") @RequestParam(name = "includeParent", defaultValue = "false") Boolean includeParent,
            @ApiParam("以id对应对象为第0层，查询多少层子节点，默认1层") @RequestParam(name = "layer", defaultValue = "1") Integer layer
    ) {
        try {
            return dictionaryService.listChildren(id, includeParent, layer);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }

    }


    /**
     * 检查该字典下是否有子数据
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "查询当前字典下是否有子数据字典 true:有；false:没有", response = Response.class)
    @GetMapping(value = "/child/check")
    public Boolean chedkHasChild(@RequestParam("id") Long id) {
        try {
            return dictionaryService.chedkHasChild(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 检查是否已经存在该code
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "检查code是否已经存在", response = Response.class)
    @GetMapping(value = "/code/check")
    public Boolean checkCode(@RequestParam("code") String code) {
        try {
            return dictionaryService.checkCode(code);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }

    /**
     * 根据code和查询数据字典
     *
     * @param code value
     * @return
     */
    @ApiOperation(value = "数据字典", notes = "根据codes查询数据字典", response = Response.class)
    @PostMapping(value = "/code/findByPCodeAndValue")
    public List<Dictionary> findByPCodeAndValue(@RequestParam("code") String code,@RequestParam("ip") String value) {
        try {
            return dictionaryService.findByPCodeAndValue(code,value);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }


}

