package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.system.domain.Organization;
import com.fulan.application.mapper.OrganizationMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.OrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 组织机构服务实现类
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@Service
@Transactional
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public boolean insertDetail(Organization organization) {
        Organization orgaExample = new Organization();
        orgaExample.setCode(organization.getCode());
        orgaExample.setEnabled(Boolean.TRUE);

        int num = organizationMapper.selectCount(new EntityWrapper<Organization>(orgaExample));
        if (num > 0) {
            throw new RuntimeException("Code has alredy existed! -- code: " + organization.getCode());
        }

        // 设置id，设置默认可用
        organization.setId(String.valueOf(idGenerator.generate()));
        organization.setEnabled(Boolean.TRUE);

        // 设置层级、rootId、parentId
        if (null == organization.getParentId() || "0".equals(organization.getParentId())) {
            organization.setLayer(0);
            organization.setRootId(new Long(0));
            organization.setParentId("0");
        } else {
            Organization orgaForParent = new Organization();
            orgaForParent.setId(organization.getParentId());
            orgaForParent.setEnabled(Boolean.TRUE);
            Organization parent = organizationMapper.selectOne(orgaForParent);

            organization.setLayer(parent.getLayer() + 1);
            if ("0".equals(parent.getParentId())) { // 若父对象的parentId为0，则父为root
                organization.setRootId(Long.valueOf(parent.getId()));
            } else { // 父对象parentId不为0，则父为非root，父rootId有真实值
                organization.setRootId(parent.getRootId());
            }
        }

        // 设置排序
        if (null == organization.getSort()) {
            Organization layerOrga = new Organization();
            layerOrga.setLayer(organization.getLayer());
            layerOrga.setRootId(organization.getRootId());
            layerOrga.setEnabled(Boolean.TRUE);

            int layerCount = organizationMapper.selectCount(new EntityWrapper<Organization>(layerOrga));
            organization.setSort(layerCount + 1);
        }
        return organizationMapper.insert(organization) == 1;
    }

    @Override
    public List<Organization> listChildren(long id, Boolean includeParent, Integer layer) {
        String idsStr = organizationMapper.getDictChildList(id, includeParent, layer, true);
        if (StringUtils.isNotEmpty(idsStr)) {
            String[] ids = idsStr.split(",");
            List<Long> idList = new ArrayList<Long>();
            for (String item : ids) {
                idList.add(new Long(item));
            }
            return this.selectBatchIds(idList);
        }
        return new ArrayList<Organization>();
    }

    @Override
    public boolean batchDelete(Long[] ids) {
        // 待删除id
        StringBuilder idsToDeleteSb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            idsToDeleteSb.append("," + organizationMapper.getDictChildList(ids[i], true, null, true));
        }
        String[] idsToDeleteStr = idsToDeleteSb.toString().split(",");

        // 去重
        Set<String> idSet = new HashSet<String>();
        for (String id : idsToDeleteStr) {
            if (StringUtils.isNotEmpty(id)) {
                idSet.add(id);
            }
        }

        List<Organization> orgaToDelete = new ArrayList<>();
        for (String id : idSet) {
            if (StringUtils.isEmpty(id)) {
                continue;
            }
            Organization organization = new Organization();
            organization.setEnabled(Boolean.FALSE);
            organization.setId(id);
            orgaToDelete.add(organization);
        }
        return this.updateBatchById(orgaToDelete);
    }
    
    @Override
	public boolean deleteAll() {
		return organizationMapper.deleteAll();
	}
    @Override
	public boolean insertFromDms(Organization organization) {
		return insert(organization);
	}
    @Override
    public List<Organization> listRootOrganization() {
        Organization organization = new Organization();
        organization.setEnabled(Boolean.TRUE);
        organization.setRootId(new Long(0));

        return organizationMapper.selectList(new EntityWrapper<Organization>(organization));
    }

    @Override
    public Organization findByCode(String code) {
        Organization organization = new Organization();
        organization.setCode(code);
        organization.setEnabled(Boolean.TRUE);

        return organizationMapper.selectOne(organization);
    }

    @Override
    public Page<Organization> listByPage(Long parentId, String code, String cnName, String enName, int pageNo, int pageSize, String pageSortFiled, String pageSortType) {
        // 组装page，页数、条数、排序字段、排序方式
        Page<Organization> page = new Page<Organization>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc(pageSortType.equals("asc") ? true : false);

        Organization organization = new Organization();
        organization.setParentId(String.valueOf(parentId));
        organization.setCode(code);
        organization.setEnabled(Boolean.TRUE);
        EntityWrapper<Organization> ew = new EntityWrapper<Organization>(organization);
        if (StringUtils.isNotEmpty(cnName)) {
            ew.like("cn_name", cnName);
        }
        if (StringUtils.isNotEmpty(enName)) {
            ew.like("en_name", enName);
        }

        return this.selectPage(page, ew);
    }

    @Override
    public boolean chedkHasChild(Long id) {
        Organization organization = new Organization();
        organization.setEnabled(Boolean.TRUE);
        organization.setParentId(String.valueOf(id));

        int num = organizationMapper.selectCount(new EntityWrapper<Organization>(organization));
        return num > 0;
    }

    @Override
    public boolean checkCode(String code) {
        Organization orgaExample = new Organization();
        orgaExample.setCode(code);
        orgaExample.setEnabled(Boolean.TRUE);

        int num = organizationMapper.selectCount(new EntityWrapper<Organization>(orgaExample));
        return num > 0;
    }
}
