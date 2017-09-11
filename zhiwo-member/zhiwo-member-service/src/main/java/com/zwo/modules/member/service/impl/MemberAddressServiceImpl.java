/**
* 
*/
package com.zwo.modules.member.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.member.dao.MemberAddressMapper;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberAddressCriteria;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class MemberAddressServiceImpl extends BaseService<MemberAddress> implements IMemberAddressService {
	private static Logger logger = LoggerFactory.getLogger(MemberAddressServiceImpl.class);

	private static final String BASE_MESSAGE = "【MemberAddressServiceImpl服务类提供的基础操作增删改查等】";

	private static final String KEYLAST_LIST_ALL_BY_MEMBERID = "_key_list_all_by_memberid";
	
	private static final String KEYLAST_DEFAULT_MEMBER_ADDRESS = "_key_DefaultMemberAddress";
	
	@Autowired
	@Lazy(true)
	private MemberAddressMapper memberAddressMapper;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	@Override
	public Mapper<MemberAddress> getBaseMapper() {
		return memberAddressMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<MemberAddress> list) { // TODO
	 * Auto-generated method stub return 0; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#countByExample(java.lang.
	 * Object)
	 */
	/*
	 * @Override public int countByExample(Object example) { // TODO
	 * Auto-generated method stub return 0; }
	 */
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByExample(java.lang.
	 * Object)
	 */
	@Override
	@CacheEvict(value = "MemberAddress", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<MemberAddress> addresses  = this.selectByExample(example);
		// 逻辑操作
		int result = memberAddressMapper.deleteByExample(example);
		if(result!=0){
			for (MemberAddress memberAddress : addresses) {
				removeRedisKey(memberAddress.getMemberId()+KEYLAST_DEFAULT_MEMBER_ADDRESS);
				removeRedisKey(memberAddress.getMemberId()+KEYLAST_LIST_ALL_BY_MEMBERID);
			}
		}
		

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "MemberAddress", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		MemberAddressCriteria memberAddressCriteria = new MemberAddressCriteria();
		memberAddressCriteria.createCriteria().andIdIn(list);
		List<MemberAddress> addresses  = this.selectByExample(memberAddressCriteria);
		int result = memberAddressMapper.deleteByExample(memberAddressCriteria);
		if(result!=0){
			for (MemberAddress memberAddress : addresses) {
				removeRedisKey(memberAddress.getMemberId()+KEYLAST_DEFAULT_MEMBER_ADDRESS);
				removeRedisKey(memberAddress.getMemberId()+KEYLAST_LIST_ALL_BY_MEMBERID);
			}
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@CacheEvict(value = "MemberAddress", key = "#id+'_MemberAddress'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());
		MemberAddress address = this.selectByPrimaryKey(id);
		// 逻辑操作
		int result = this.memberAddressMapper.deleteByPrimaryKey(id);
		if(address.getMemberId()!=null){
			if(result !=0){
				removeRedisKey(address.getMemberId()+KEYLAST_DEFAULT_MEMBER_ADDRESS);
				removeRedisKey(address.getMemberId()+KEYLAST_LIST_ALL_BY_MEMBERID);
			}
		}
		
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insert(java.lang.Object)
	 */
	@Override
//	@CachePut(value = "MemberAddress", key = "#record.id+'_MemberAddress'")
	public int insert(MemberAddress record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insert(record);
		
		if(result !=0){
			removeRedisKey(record.getMemberId()+KEYLAST_DEFAULT_MEMBER_ADDRESS);
			removeRedisKey(record.getMemberId()+KEYLAST_LIST_ALL_BY_MEMBERID);
		}
		
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertSelective(java.lang.
	 * Object)
	 */

	@Override
//	@CachePut(value = "MemberAddress", key = "#record.id+'_MemberAddress'")
	public int insertSelective(MemberAddress record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		if(record.getMemberId()!=null){
			int count = countByMemberId(record.getMemberId());
			if(count == 0){
				record.setIsDefault("1");
			}
		}
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insertSelective(record);
		
		if(result !=0){
			removeRedisKey(record.getMemberId()+KEYLAST_DEFAULT_MEMBER_ADDRESS);
			removeRedisKey(record.getMemberId()+KEYLAST_LIST_ALL_BY_MEMBERID);
		}
		
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByExample(java.lang.
	 * Object)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MemberAddress> selectByExample(Object example) {
		return memberAddressMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_MemberAddress'", value = "MemberAddress")
	@Transactional(readOnly = true)
	public MemberAddress selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		MemberAddress memberAddress = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return memberAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "MemberAddress", allEntries = true)
	@Override
	public int updateByExampleSelective(MemberAddress record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByExampleSelective(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExample(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	@CacheEvict(value = "MemberAddress", allEntries = true)
	public int updateByExample(MemberAddress record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByExample(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKeySelective
	 * (java.lang.Object)
	 */
	@Override
	@CacheEvict(value = "MemberAddress", key = "#record.id+'_MemberAddress'")
	public int updateByPrimaryKeySelective(MemberAddress record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());

		// 逻辑操作
		int result = this.memberAddressMapper.updateByPrimaryKeySelective(record);
		
		if(result !=0){
			removeRedisKey(record.getMemberId()+KEYLAST_DEFAULT_MEMBER_ADDRESS);
			removeRedisKey(record.getMemberId()+KEYLAST_LIST_ALL_BY_MEMBERID);
		}
		
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKey(java.
	 * lang.Object)
	 */
	@Override
	@CacheEvict(value = "MemberAddress", key = "#record.id+'_MemberAddress'")
	public int updateByPrimaryKey(MemberAddress record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		
		// 逻辑操作
		int result = this.memberAddressMapper.updateByPrimaryKey(record);
		if(result !=0){
			removeRedisKey(record.getMemberId()+KEYLAST_DEFAULT_MEMBER_ADDRESS);
			removeRedisKey(record.getMemberId()+KEYLAST_LIST_ALL_BY_MEMBERID);
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPageInfo(java.lang.
	 * Object, com.github.pagehelper.PageInfo)
	 */
	@Transactional(readOnly = true)
	@Override
	public PageInfo<MemberAddress> selectByPageInfo(Object example, PageInfo<MemberAddress> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "MemberAddress", key = "#memberId+'_key_DefaultMemberAddress'")
	public MemberAddress selectDefaultAddressByMemberId(String memberId) {
		MemberAddressCriteria addressCriteria = new MemberAddressCriteria();
		addressCriteria.createCriteria().andMemberIdEqualTo(memberId).andIsDefaultEqualTo("1");
		List<MemberAddress> list = this.memberAddressMapper.selectByExample(addressCriteria);
		
		return list== null||list.isEmpty()?null:list.get(0);
	}
	
	@CacheEvict(value = "MemberAddress", key = "#memberId+'_key_DefaultMemberAddress'")
	public int deleteAddressByMemberId(String id,String memberId) {
		MemberAddressCriteria addressCriteria = new MemberAddressCriteria();
		addressCriteria.createCriteria().andIdEqualTo(id).andMemberIdEqualTo(memberId);
		int result = this.deleteByExample(addressCriteria);
		if(result != 0){
			removeRedisKey(memberId+KEYLAST_DEFAULT_MEMBER_ADDRESS);
		}
		
		return result;
	}


	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "MemberAddresses", key = "#memberId+'_key_list_all_by_memberid'")
	public List<MemberAddress> listAllByMemberId(String memberId) {
		MemberAddressCriteria criteria = new MemberAddressCriteria();
		criteria.createCriteria().andMemberIdEqualTo(memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员地址开始");
		List<MemberAddress> list = this.memberAddressMapper.selectByExample(criteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员地址结束，条目数为："+list.size());
		return list;
	}
	
	private int countByMemberId(String memberId){
		MemberAddressCriteria criteria = new MemberAddressCriteria();
		criteria.createCriteria().andMemberIdEqualTo(memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员地址数目开始");
		int result = this.memberAddressMapper.selectCountByExample(criteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员地址数目结束，条目数为："+result);
		return result;
	}
	/**
	 * 移除key.
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	private void removeRedisKey(String key){
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder
					.getBean("redisTemplate");
		}

		if (redisTemplate != null) {
			
			if (redisTemplate.hasKey(key)) {
				redisTemplate.delete(key);
			}
		}
	}

}
