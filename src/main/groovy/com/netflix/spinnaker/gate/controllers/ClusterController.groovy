/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.gate.controllers

import com.netflix.spinnaker.gate.services.ClusterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult

@RequestMapping("/applications/{application}/clusters")
@RestController
class ClusterController {

  @Autowired
  ClusterService clusterService

  @RequestMapping(value = "/{account}", method = RequestMethod.GET)
  def getClusters(@PathVariable("application") String app, @PathVariable("account") String account) {
    DeferredResult<List> q = new DeferredResult<>()
    clusterService.getClusters(app, account).toList().subscribe({
      q.setResult(it)
    }, { Throwable t ->
      q.setErrorResult(t)
    })
    q
  }

  @RequestMapping(value = "/{account}/{clusterName}", method = RequestMethod.GET)
  def getClusters(@PathVariable("application") String app,
                  @PathVariable("account") String account,
                  @PathVariable("clusterName") String clusterName) {
    DeferredResult<List> q = new DeferredResult<>()
    clusterService.getCluster(app, account, clusterName).toList().subscribe({
      q.setResult(it)
    }, { Throwable t ->
      q.setErrorResult(t)
    })
    q
  }

  @RequestMapping(value = "/{account}/{clusterName}/tags", method = RequestMethod.GET)
  def getClusterTags(@PathVariable("clusterName") String clusterName) {
    DeferredResult<List> q = new DeferredResult<>()
    clusterService.getClusterTags(clusterName).subscribe({
      q.setResult(it)
    }, { Throwable t ->
      q.setErrorResult(t)
    })
    q
  }

  @RequestMapping(value = "/{account}/{clusterName}/{type}", method = RequestMethod.GET)
  def getClustersByType(@PathVariable("application") String app,
                        @PathVariable("account") String account,
                        @PathVariable("clusterName") String clusterName,
                        @PathVariable("type") String type) {
    DeferredResult<Map> q = new DeferredResult<>()
    clusterService.getClusterByType(app, account, clusterName, type).subscribe({
      q.setResult(it)
    }, { Throwable t ->
      q.setErrorResult(t)
    })
    q
  }

  @RequestMapping(value = "/{account}/{clusterName}/{type}/loadBalancers", method = RequestMethod.GET)
  def getClusterLoadBalancers(@PathVariable("application") String app,
                              @PathVariable("account") String account,
                              @PathVariable("clusterName") String clusterName,
                              @PathVariable("type") String type) {
    DeferredResult<Map> q = new DeferredResult<>()
    clusterService.getClusterByType(app, account, clusterName, type).subscribe({
      q.setResult(it)
    }, { Throwable t ->
      q.setErrorResult(t)
    })
    q
  }
}
