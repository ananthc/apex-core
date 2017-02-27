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
package org.apache.apex.common.util;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceStability;

import com.datatorrent.api.StorageAgent;

/**
 * Storage agent which can take checkpoints asynchronously.
 * An AsyncStorageAgent enables quick checkpoints by taking local snapshot of an operator
 * and unblocking the operator to process more data, while storage engine is pushing local snapshot to
 * the distributed or globally accessible location for recovery.
 */
@InterfaceStability.Evolving
public interface AsyncStorageAgent extends StorageAgent
{
  /**
   * Make checkpoint for given windowID final. i.e after this method returns,
   * the checkpoint is accessible for recovery.
   *
   * @param operatorId
   * @param windowId
   * @throws IOException
   */
  public void finalize(int operatorId, long windowId) throws IOException;

  /**
   * Check if StorageAgent is configured to take synchronous checkpoints.
   *
   * @return true if StorageAgent is configured to take synchronous checkpoints.
   * @return false otherwise.
   */
  public boolean isSyncCheckpoint();

}
