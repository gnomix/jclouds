/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
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
package org.jclouds.cloudstack.internal;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.cloudstack.CloudStackAsyncClient;
import org.jclouds.cloudstack.CloudStackClient;
import org.jclouds.cloudstack.CloudStackContext;
import org.jclouds.cloudstack.CloudStackDomainAsyncClient;
import org.jclouds.cloudstack.CloudStackDomainClient;
import org.jclouds.cloudstack.CloudStackGlobalAsyncClient;
import org.jclouds.cloudstack.CloudStackGlobalClient;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.Utils;
import org.jclouds.compute.internal.ComputeServiceContextImpl;
import org.jclouds.domain.Credentials;
import org.jclouds.rest.RestContext;

/**
 * @author Adrian Cole
 */
@Singleton
public class CloudStackContextImpl extends ComputeServiceContextImpl<CloudStackClient, CloudStackAsyncClient> implements
      CloudStackContext {
   private final RestContext<CloudStackClient,CloudStackAsyncClient> providerSpecificContext;
   private final RestContext<CloudStackDomainClient, CloudStackDomainAsyncClient> domainContext;
   private final RestContext<CloudStackGlobalClient, CloudStackGlobalAsyncClient> globalContext;

   @Inject
   public CloudStackContextImpl(ComputeService computeService, Map<String, Credentials> credentialStore, Utils utils,
         RestContext<CloudStackClient,CloudStackAsyncClient> providerSpecificContext,
         RestContext<CloudStackDomainClient, CloudStackDomainAsyncClient> domainContext,
         RestContext<CloudStackGlobalClient, CloudStackGlobalAsyncClient> globalContext) {
      super(computeService, credentialStore, utils, providerSpecificContext);
      this.providerSpecificContext=providerSpecificContext;
      this.domainContext = domainContext;
      this.globalContext = globalContext;
   }
   
   @SuppressWarnings("unchecked")
   @Override
   public RestContext<CloudStackClient,CloudStackAsyncClient> getProviderSpecificContext() {
      return providerSpecificContext;
   }
   
   @Override
   public RestContext<CloudStackDomainClient, CloudStackDomainAsyncClient> getDomainContext() {
      return domainContext;
   }

   @Override
   public RestContext<CloudStackGlobalClient, CloudStackGlobalAsyncClient> getGlobalContext() {
      return globalContext;
   }
}
