/*
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
package org.jclouds.vcloud.director.v1_5.features.admin;

import static org.testng.Assert.assertEquals;

import org.jclouds.vcloud.director.v1_5.domain.Checks;
import org.jclouds.vcloud.director.v1_5.domain.Reference;
import org.jclouds.vcloud.director.v1_5.domain.RoleReferences;
import org.jclouds.vcloud.director.v1_5.domain.query.QueryResultAdminUserRecord;
import org.jclouds.vcloud.director.v1_5.domain.query.QueryResultAdminVdcRecord;
import org.jclouds.vcloud.director.v1_5.domain.query.QueryResultRecordType;
import org.jclouds.vcloud.director.v1_5.domain.query.QueryResultRecords;
import org.jclouds.vcloud.director.v1_5.domain.query.QueryResultRightRecord;
import org.jclouds.vcloud.director.v1_5.domain.query.QueryResultRoleRecord;
import org.jclouds.vcloud.director.v1_5.domain.query.QueryResultStrandedUserRecord;
import org.jclouds.vcloud.director.v1_5.features.admin.AdminQueryClient;
import org.jclouds.vcloud.director.v1_5.internal.BaseVCloudDirectorClientLiveTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
* Tests live behavior of {@link AdminQueryClient}.
* 
* @author Aled Sage
*/
@Test(groups = { "live", "admin", "query" }, singleThreaded = true, testName = "AdminQueryClientLiveTest")
public class AdminQueryClientLiveTest extends BaseVCloudDirectorClientLiveTest {

   /*
    * Convenience references to API clients.
    */

   private AdminQueryClient queryClient;

   @Override
   @BeforeClass(alwaysRun = true)
   public void setupRequiredClients() {
      queryClient = adminContext.getApi().getQueryClient();
   }

   @Test(description = "GET /admin/groups/query")
   public void testQueryAllGroups() {
      // TODO Ensure there will be at least one record, for asserting result
      QueryResultRecords resultRecords = queryClient.groupsQueryAll();
      
      for (QueryResultRecordType record : resultRecords.getRecords()) {
         Checks.checkQueryResultRecord(record);
      }
   }
   
   @Test(description = "GET /admin/orgs/query")
   public void testQueryAllOrgs() {
      // TODO Ensure there will be at least one record, for asserting result
      QueryResultRecords resultRecords = queryClient.orgsQueryAll();
      
      for (QueryResultRecordType record : resultRecords.getRecords()) {
         Checks.checkQueryResultRecord(record);
      }
   }
   
   @Test(description = "GET /admin/rights/query")
   public void testQueryAllRights() {
      // TODO Ensure there will be at least one record, for asserting result
      QueryResultRecords resultRecords = queryClient.rightsQueryAll();
      
      for (QueryResultRecordType record : resultRecords.getRecords()) {
         Checks.checkQueryResultRecord(record);
         assertEquals(record.getClass(), QueryResultRightRecord.class, "incorrect record type admin query");
      }
   }
   
   @Test(description = "GET /admin/roles/query")
   public void testQueryAllRoles() {
      // TODO Ensure there will be at least one record, for asserting result
      QueryResultRecords resultRecords = queryClient.rolesQueryAll();
      
      for (QueryResultRecordType record : resultRecords.getRecords()) {
         Checks.checkQueryResultRecord(record);
         assertEquals(record.getClass(), QueryResultRoleRecord.class, "incorrect record type admin query");
      }
      
      RoleReferences roleRefs = queryClient.roleReferencesQueryAll();
      
      for (Reference ref : roleRefs.getReferences()) {
         Checks.checkReferenceType(ref);
      }
   }
   
   @Test(description = "GET /admin/strandedUsers/query")
   public void testQueryAllStrandedUsers() {
      // TODO Ensure there will be at least one record, for asserting result
      QueryResultRecords resultRecords = queryClient.strandedUsersQueryAll();
      
      for (QueryResultRecordType record : resultRecords.getRecords()) {
         Checks.checkQueryResultRecord(record);
         assertEquals(record.getClass(), QueryResultStrandedUserRecord.class, "incorrect record type admin query");
      }
   }
   
   @Test(description = "GET /admin/users/query")
   public void testQueryAllUsers() {
      // TODO Ensure there will be at least one record, for asserting result
      QueryResultRecords resultRecords = queryClient.usersQueryAll();
      
      for (QueryResultRecordType record : resultRecords.getRecords()) {
         Checks.checkQueryResultRecord(record);
         assertEquals(record.getClass(), QueryResultAdminUserRecord.class, "incorrect record type admin query");
      }
   }
   
   @Test(description = "GET /admin/vdcs/query")
   public void testQueryAllVdc() {
      // TODO Ensure there will be at least one record, for asserting result
      QueryResultRecords resultRecords = queryClient.vdcsQueryAll();
      
      for (QueryResultRecordType record : resultRecords.getRecords()) {
         Checks.checkQueryResultRecord(record);
         assertEquals(record.getClass(), QueryResultAdminVdcRecord.class, "incorrect record type admin query");
      }
   }
}
