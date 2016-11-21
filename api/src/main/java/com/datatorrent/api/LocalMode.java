/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.datatorrent.api;

import org.apache.apex.api.EmbeddedAppLauncher;
import org.apache.hadoop.conf.Configuration;

/**
 * Local mode execution for single application
 *
 * @deprecated
 * @since 0.3.2
 */
@Deprecated
public abstract class LocalMode<H extends EmbeddedAppLauncher.EmbeddedAppHandle> extends EmbeddedAppLauncher<H>
{

  /**
   * <p>
   * getDAG.</p>
   *
   * @return
   */
  public abstract DAG getDAG();

  /**
   * <p>
   * cloneDAG.</p>
   *
   * @return
   * @throws java.lang.Exception
   */
  public abstract DAG cloneDAG() throws Exception;

  /**
   * Build the logical plan through the given streaming application instance and/or from configuration.
   * <p>
   * The plan will be constructed through {@link StreamingApplication#populateDAG}. If configuration properties are
   * specified, they function as override, as would be the case when launching an application through CLI.
   * <p>
   * This method can also be used to construct the plan declaratively from configuration only, by passing null for the
   * application. In this case the configuration contains all operators and streams.
   * <p>
   *
   * @param app
   * @param conf
   * @return
   * @throws Exception
   * @since 0.3.5
   */
  public abstract DAG prepareDAG(StreamingApplication app, Configuration conf) throws Exception;

  /**
   * <p>
   * getController.</p>
   *
   * @return
   */
  public abstract Controller getController();

  public interface Controller
  {
    public void run();

    public void run(long runMillis);

    public void runAsync();

    public void shutdown();

    public void setHeartbeatMonitoringEnabled(boolean enabled);

  }

  /**
   * <p>
   * newInstance.</p>
   *
   * @return
   */
  public static LocalMode newInstance()
  {
    return loadService(LocalMode.class);
  }

}
