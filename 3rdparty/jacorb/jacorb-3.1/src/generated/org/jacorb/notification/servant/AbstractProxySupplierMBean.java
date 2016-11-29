/*
 * Generated file - Do not edit!
 */
package org.jacorb.notification.servant;

/**
 * MBean interface.
 */
public interface AbstractProxySupplierMBean extends org.jacorb.notification.servant.AbstractProxyMBean {

  int getPendingMessagesCount() ;

  java.lang.String getOrderPolicy() ;

  java.lang.String getDiscardPolicy() ;

  int getMaxEventsPerConsumer() ;

  void setMaxEventsPerConsumer(int max) ;

  int getNumberOfDiscardedMessages() ;

  void clearPendingMessageQueue() ;

}
