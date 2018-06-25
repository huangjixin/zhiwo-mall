package com.fulan.core.monitoring.cat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;

import com.fulan.core.monitoring.cat.LoadCatDefinition.CatDefinition;
import com.fulan.core.monitoring.cat.constant.Constant;
import com.fulan.core.monitoring.cat.monitor.CatGateWayForErrorMonitor;
import com.fulan.core.monitoring.cat.monitor.CatGateWayMonitor;

@Configuration
public class CatGateWayMonitorRegistry implements BeanDefinitionRegistryPostProcessor{
	
	private static Logger logger = LoggerFactory.getLogger(CatGateWayMonitorRegistry.class);
	private static CatDefinition catDefinition;
	
	static {
		catDefinition = LoadCatDefinition.getCatDefinition();
	}
	
	private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		// 不做处理
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
		if(catDefinition.isEnabledCat()){
			if(Constant.GATEWAY_YES.equals(catDefinition.getEnableGateWay())){
				try {
					doEnhanceForGateWay(beanDefinitionRegistry);
				} catch (Exception e) {
					logger.error("CatGateWayMonitorRegistry.postProcessBeanDefinitionRegistry", e);
				}
			}
		}
	}
	
	private void doEnhanceForGateWay(BeanDefinitionRegistry beanDefinitionRegistry){
		registerBean(beanDefinitionRegistry,null,CatGateWayMonitor.class);
		registerBean(beanDefinitionRegistry,null,CatGateWayForErrorMonitor.class);
	}
	
	private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass){
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);
        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
        abd.setScope(scopeMetadata.getScopeName());
        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));
        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }

}
