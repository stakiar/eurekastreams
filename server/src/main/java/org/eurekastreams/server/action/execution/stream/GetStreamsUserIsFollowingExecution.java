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
package org.eurekastreams.server.action.execution.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eurekastreams.commons.actions.ExecutionStrategy;
import org.eurekastreams.commons.actions.context.PrincipalActionContext;
import org.eurekastreams.commons.exceptions.ExecutionException;
import org.eurekastreams.server.action.request.stream.GetStreamsUserIsFollowingRequest;
import org.eurekastreams.server.domain.PagedSet;
import org.eurekastreams.server.domain.dto.StreamDTO;
import org.eurekastreams.server.persistence.mappers.DomainMapper;
import org.eurekastreams.server.search.modelview.DomainGroupModelView;
import org.eurekastreams.server.search.modelview.PersonModelView;

/**
 * Return list of StreamsDTOs representing streams a user is following.
 * 
 */
public class GetStreamsUserIsFollowingExecution implements ExecutionStrategy<PrincipalActionContext>
{
    /**
     * Mapper to get person ids for all persons current user is following.
     */
    private DomainMapper<Long, List<Long>> personIdsUserIsFollowingMapper;

    /**
     * Mapper to get group ids for all persons current user is following.
     */
    private DomainMapper<Long, List<Long>> groupIdsUserIsFollowingMapper;

    /**
     * Mapper to get Person model views.
     */
    private DomainMapper<List<Long>, List<PersonModelView>> personModelViewsMapper;

    /**
     * Mapper to get group model views.
     */
    private DomainMapper<List<Long>, List<DomainGroupModelView>> groupModelViewsMapper;

    /**
     * Comparator for StreamDTOs by display name.
     */
    public static final Comparator<StreamDTO> STREAMDTO_DISPLAYNAME_COMPARATOR = new Comparator<StreamDTO>()
    {
        @Override
        public int compare(final StreamDTO a, final StreamDTO b)
        {
            return a.getDisplayName().compareToIgnoreCase(b.getDisplayName());

        }
    };

    /**
     * Constructor.
     * 
     * @param inPersonIdsUserIsFollowingMapper
     *            Mapper to get person ids for all persons current user is following.
     * @param inGroupIdsUserIsFollowingMapper
     *            Mapper to get group ids for all persons current user is following.
     * @param inPersonModelViewsMapper
     *            Mapper to get Person model views.
     * @param inGroupModelViewsMapper
     *            Mapper to get group model views.
     */
    public GetStreamsUserIsFollowingExecution(final DomainMapper<Long, List<Long>> inPersonIdsUserIsFollowingMapper,
            final DomainMapper<Long, List<Long>> inGroupIdsUserIsFollowingMapper,
            final DomainMapper<List<Long>, List<PersonModelView>> inPersonModelViewsMapper,
            final DomainMapper<List<Long>, List<DomainGroupModelView>> inGroupModelViewsMapper)
    {
        personIdsUserIsFollowingMapper = inPersonIdsUserIsFollowingMapper;
        groupIdsUserIsFollowingMapper = inGroupIdsUserIsFollowingMapper;
        personModelViewsMapper = inPersonModelViewsMapper;
        groupModelViewsMapper = inGroupModelViewsMapper;
    }

    @Override
    public PagedSet<StreamDTO> execute(final PrincipalActionContext inActionContext) throws ExecutionException
    {
        long currentUserId = inActionContext.getPrincipal().getId();
        GetStreamsUserIsFollowingRequest request = (GetStreamsUserIsFollowingRequest) inActionContext.getParams();

        ArrayList<StreamDTO> results = new ArrayList<StreamDTO>();
        PagedSet<StreamDTO> pagedResults = new PagedSet<StreamDTO>();

        // get person/group ModelViews, which implement StreamDTO, and add to results;
        results.addAll(personModelViewsMapper.execute(personIdsUserIsFollowingMapper.execute(currentUserId)));
        results.addAll(groupModelViewsMapper.execute(groupIdsUserIsFollowingMapper.execute(currentUserId)));

        // if no results, short-circuit here.
        if (results.isEmpty())
        {
            return pagedResults;
        }

        // sort results;
        Collections.sort(results, STREAMDTO_DISPLAYNAME_COMPARATOR);

        // set up PagedSet result and return.
        int total = results.size();
        int startIndex = request.getStartIndex();
        int endIndex = request.getEndIndex() > results.size() ? results.size() : request.getEndIndex() + 1;

        pagedResults.setFromIndex(startIndex);
        pagedResults.setToIndex(endIndex);
        pagedResults.setTotal(total);
        pagedResults.setPagedSet(new ArrayList<StreamDTO>(results.subList(startIndex, endIndex)));
        return pagedResults;
    }
}
