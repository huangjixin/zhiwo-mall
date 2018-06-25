package com.fulan.application.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.system.domain.Organization;
import com.fulan.application.service.OrganizationService;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@Api(tags = "OrganizationApi", description = "组织机构接口")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    /**
     * 新增组织机构
     *
     * @param organization
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "新增一个组织机构", response = Response.class)
    @PostMapping("/insert")
    public Response insert(
            @ApiParam(
                    name = "organization",
                    value = "不能为空字段：</br>code</br>cnName</br>companyId</br>" +
                            "选传参数：</br>parentId:当为顶层节点时不传或传0</br>sort:同层级排序"
            )
            @RequestBody Organization organization) {
        try {
            boolean flag = organizationService.insertDetail(organization);
            if (flag) {
                return new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            }
            return new Response(Response.ERROR, Response.ERROR_MESSAGE);
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 删除一个组织机构
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "删除一个组织机构（会同步循环删除该节点下所有子节点）", response = Response.class)
    @PostMapping("/delete")
    public Response delete(@RequestParam("id") Long id) {
        try {
            boolean flag = organizationService.batchDelete(new Long[]{id});
            if (flag) {
                return new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            }
            return new Response(Response.ERROR, Response.ERROR_MESSAGE);
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "批量删除数（会同步循环删除节点下所有子节点）", response = Response.class)
    @PostMapping("/batch/delete")
    public Response deleteBacth(
            @ApiParam(name = "ids", value = "要删除的id，可传入多个id") @RequestParam("ids") Long[] ids
    ) {
        try {
            boolean flag = organizationService.batchDelete(ids);
            if (flag) {
                return new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            }
            return new Response(Response.ERROR, Response.ERROR_MESSAGE);
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }
    }
    
    @ApiOperation(value = "清空组织", notes = "清空组织机构", response = Response.class)
    @PostMapping("/deleteAll")
    public Response<Boolean> deleteAll(){
    	try {
            Response<Boolean> resp = new Response<Boolean>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            Boolean returnFlag = organizationService.deleteAll();
            resp.setData(returnFlag);
            resp.setCode(Response.SUCCESS);
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<>(Response.ERROR,e.getLocalizedMessage());
        }
    }

    @ApiOperation(value = "插入组织机构数据", notes = "dms数据插入", response = Response.class)
    @PostMapping("/insertFromDms")
    public Response<Boolean> insertFromDms(@RequestBody Organization organization){
    	try {
            Response<Boolean> resp = new Response<Boolean>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            boolean returnFlag = organizationService.insert(organization);
            resp.setCode(Response.SUCCESS);
            resp.setData(returnFlag);
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<>(Response.ERROR,e.getLocalizedMessage());
        }
    }
    
    /**
     * 更新组织机构
     *
     * @param organization
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "根据id更新组织机构", response = Response.class)
    @PostMapping(value = "/update")
    public Response update(@ApiParam("id不可不传") @RequestBody Organization organization) {
        try {
            boolean flag = organizationService.updateById(organization);
            if (flag) {
                return new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            }
            return new Response(Response.ERROR, Response.ERROR_MESSAGE);
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 查找单个组织机构
     *
     * @param id Id
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "根据id查找单个组织机构", response = Response.class)
    @GetMapping(value = "/find")
    public Response<Organization> findById(@RequestParam("id") String id) {
        try {
            Response<Organization> resp = new Response<Organization>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            resp.setData(organizationService.selectById(id));
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 查找单个组织机构
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "根据id查找单个组织机构", response = Response.class)
    @GetMapping(value = "/code/find")
    public Response<Organization> findByCode(@RequestParam("code") String code) {
        try {
            Response<Organization> resp = new Response<Organization>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            resp.setData(organizationService.findByCode(code));
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 分页查询组织机构
     *
     * @param parentId      父id（精确匹配）
     * @param code          code（精确匹配）
     * @param cnName        中文名（模糊匹配）
     * @param enName        英文名（模糊匹配）
     * @param pageNo        要跳转的页数
     * @param pageSize      每页条数
     * @param pageSortFiled 排序字段名
     * @param pageSortType  顺序：asc,倒叙：desc
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "分页查询组织机构", response = Response.class)
    @GetMapping(value = "/list")
    public Response<Page<Organization>> listByPage(
            @ApiParam(name = "parentId", value = "父Id, 不传则默认查询所有根节点") @RequestParam(name = "parentId", defaultValue = "0") Long parentId,
            @ApiParam(name = "code", value = "code", required = false) @RequestParam(name = "code", required = false) String code,
            @ApiParam(name = "cnName", value = "中文名（模糊匹配）", required = false) @RequestParam(name = "cnName", required = false) String cnName,
            @ApiParam(name = "enName", value = "英文名（模糊匹配）", required = false) @RequestParam(name = "enName", required = false) String enName,

            @ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "sort") String pageSortFiled,
            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType
    ) {
        try {
            Response<Page<Organization>> resp = new Response<Page<Organization>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            resp.setData(organizationService.listByPage(parentId, code, cnName, enName, pageNo, pageSize, pageSortFiled, pageSortType));
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 查询所有顶层组织机构
     *
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "查询所有顶层组织机构", response = Response.class)
    @GetMapping(value = "/root/list")
    public Response<List<Organization>> listRootOrganization() {
        try {
            Response<List<Organization>> resp = new Response<List<Organization>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            resp.setData(organizationService.listRootOrganization());
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 查询当前组织机构下的子节点
     *
     * @param id            要查找的对象的父节点
     * @param includeParent 是否包含父节点（id对应的自身节点），true：包含，false：不包含，默认false
     * @param layer         以id对应对象为第0层，查询多少层子节点，默认1层
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "查询当前组织机构下的子节点", response = Response.class)
    @GetMapping(value = "/child/list")
    public Response<List<Organization>> listChildOrganization(
            @ApiParam("要查找的对象的父节点") @RequestParam(name = "id", defaultValue = "0") Long id,
            @ApiParam("是否包含父节点（id对应的自身节点），</br>true：包含，</br>false：不包含，默认false") @RequestParam(name = "includeParent", defaultValue = "false") Boolean includeParent,
            @ApiParam("以id对应对象为第0层，查询多少层子节点，默认1层") @RequestParam(name = "layer", defaultValue = "1") Integer layer
    ) {
        try {
            Response<List<Organization>> resp = new Response<List<Organization>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            resp.setData(organizationService.listChildren(id, includeParent, layer));
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 检查该组织下是否有子数据
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "查询当前组织机构下是否有子组织机构 true:有；false:没有", response = Response.class)
    @GetMapping(value = "/child/check")
    public Response<Boolean> chedkHasChild(@RequestParam("id") Long id) {
        try {
            Response<Boolean> resp = new Response<Boolean>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            resp.setData(organizationService.chedkHasChild(id));
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response(Response.ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 检查是否已经存在该code
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "组织机构", notes = "检查code是否已经存在", response = Response.class)
    @GetMapping(value = "/code/check")
    public Response<Boolean> checkCode(@RequestParam("code") String code) {
        try {
            Response<Boolean> resp = new Response<Boolean>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
            resp.setData(organizationService.checkCode(code));
            return resp;
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }
    }

}

