/*
 * Copyright (c) 2011 Lockheed Martin Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eurekastreams.server.domain;

import static org.junit.Assert.assertEquals;

import org.eurekastreams.server.domain.stream.StreamScope.ScopeType;
import org.junit.Test;

/**
 * Tests DomainConversionUtility.
 */
public class DomainConversionUtilityTest
{
    /**
     * Tests conversion from scope type to entity type.
     */
    @Test
    public void testConvertToEntityTypeFromScopeType()
    {
        assertEquals(EntityType.NOTSET, DomainConversionUtility.convertToEntityType(ScopeType.ALL));
        assertEquals(EntityType.PERSON, DomainConversionUtility.convertToEntityType(ScopeType.PERSON));
        assertEquals(EntityType.ORGANIZATION, DomainConversionUtility.convertToEntityType(ScopeType.ORGANIZATION));
        assertEquals(EntityType.GROUP, DomainConversionUtility.convertToEntityType(ScopeType.GROUP));
        assertEquals(EntityType.ORGANIZATION,
                DomainConversionUtility.convertToEntityType(ScopeType.PERSONS_PARENT_ORGANIZATION));
        assertEquals(EntityType.NOTSET,
                DomainConversionUtility.convertToEntityType(ScopeType.PERSONS_FOLLOWED_STREAMS));
        assertEquals(EntityType.NOTSET, DomainConversionUtility.convertToEntityType(ScopeType.STARRED));
        assertEquals(EntityType.RESOURCE, DomainConversionUtility.convertToEntityType(ScopeType.RESOURCE));
    }
}
