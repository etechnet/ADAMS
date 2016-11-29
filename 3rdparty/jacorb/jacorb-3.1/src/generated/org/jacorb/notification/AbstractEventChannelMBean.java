/*
 * Generated file - Do not edit!
 */
package org.jacorb.notification;

/**
 * MBean interface.
 */
public interface AbstractEventChannelMBean {

   /**
    * destroy this Channel, all created Admins and all Proxies.
    */
  void destroy() ;

  int getMaxNumberOfSuppliers() ;

  void setMaxNumberOfSuppliers(int max) ;

  int getMaxNumberOfConsumers() ;

  void setMaxNumberOfConsumers(int max) ;

  int getID() ;

}
