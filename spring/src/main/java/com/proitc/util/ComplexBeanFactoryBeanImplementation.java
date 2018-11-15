package com.proitc.util;

import com.proitc.bean.ComplexBean;
import org.springframework.beans.factory.FactoryBean;

public class ComplexBeanFactoryBeanImplementation implements FactoryBean<ComplexBean> {

  public ComplexBean getObject() throws Exception {
    ComplexBean bean = new ComplexBean();
    //Do Some Complex instantion here
    return bean;
  }

  public Class<?> getObjectType() {
    return ComplexBean.class;
  }

  public boolean isSingleton() {
    return false;
  }

}
