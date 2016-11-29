/*
 * Generated file - Do not edit!
 */
package org.jacorb.notification.servant;

/**
 * MBean interface.
 */
public interface AbstractProxyPushSupplierMBean extends org.jacorb.notification.servant.AbstractProxySupplierMBean {

  void setRetryStrategy(java.lang.String factoryName) throws java.lang.ClassNotFoundException;

  java.lang.String getRetryStrategy() ;

  void resetErrorCounter() ;

  int getPushOperationCount() ;

  int getPushErrorCount() ;

  int getAveragePushDuration() ;

}
