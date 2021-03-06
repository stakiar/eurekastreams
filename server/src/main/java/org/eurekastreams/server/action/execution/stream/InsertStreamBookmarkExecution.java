/*
 * Copyright (c) 2010 Lockheed Martin Corporation
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
package org.eurekastreams.server.action.execution.stream;

import java.io.Serializable;
import java.util.List;

import org.eurekastreams.commons.actions.ExecutionStrategy;
import org.eurekastreams.commons.actions.context.PrincipalActionContext;
import org.eurekastreams.commons.exceptions.ExecutionException;
import org.eurekastreams.server.domain.Person;
import org.eurekastreams.server.domain.stream.StreamScope;
import org.eurekastreams.server.persistence.mappers.FindByIdMapper;
import org.eurekastreams.server.persistence.mappers.requests.FindByIdRequest;

/**
 * Insert a stream bookmark.
 */
public class InsertStreamBookmarkExecution implements ExecutionStrategy<PrincipalActionContext>
{
    /**
     * Mapper used to retrieve and save the page that holds the streams.
     */
    private final FindByIdMapper<Person> personMapper;

    /**
     * Mapper used to retrieve and save the page that holds the streams.
     */
    private final FindByIdMapper<StreamScope> scopeMapper;

    /**
     * Constructor.
     * 
     * @param inPersonMapper
     *            the person mapper.
     * @param inScopeMapper
     *            the scope mapper.
     */
    public InsertStreamBookmarkExecution(final FindByIdMapper<Person> inPersonMapper,
            final FindByIdMapper<StreamScope> inScopeMapper)
    {
        personMapper = inPersonMapper;
        scopeMapper = inScopeMapper;
    }

    /**
     * Execute the action.
     * 
     * @param inActionContext
     *            the action context.
     * @return the parameter.
     * @throws ExecutionException
     *             on execution failure.
     */
    public Serializable execute(final PrincipalActionContext inActionContext) throws ExecutionException
    {
        // TODO: bookmarks should really be promoted to it's own entity (e.g. followers) to make this (and deletes)
        // more efficient.
        Long ssIdToInsert = (Long) inActionContext.getParams();

        Person person = personMapper.execute(new FindByIdRequest("Person", inActionContext.getPrincipal().getId()));
        List<StreamScope> bookmarks = person.getBookmarks();

        StreamScope scope = null;

        // if already bookmarked, just assign return scope here.
        for (StreamScope ss : bookmarks)
        {
            if (ss.getId() == ssIdToInsert)
            {
                scope = ss;
                break;
            }
        }

        // if hasn't been bookmarked, bookmark it.
        if (scope == null)
        {
            scope = scopeMapper.execute(new FindByIdRequest("StreamScope", ssIdToInsert));
            bookmarks.add(scope);
            personMapper.flush();
        }

        return scope;
    }
}
