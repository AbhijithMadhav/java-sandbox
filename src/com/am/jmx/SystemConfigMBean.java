package com.am.jmx;

/**
 * Created by amadhav on 19/05/17.
 */

public interface SystemConfigMBean {

    void setThreadCount(int noOfThreads);
    int getThreadCount();

    void setSchemaName(String schemaName);
    public String getSchemaName();

    // any method starting with get and set are considered
    // as attributes getter and setter methods, so I am
    // using do* for operation.
    public String doConfig();
}
