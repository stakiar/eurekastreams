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
package org.eurekastreams.server.persistence.mappers.cache;

/**
 * Collection of Cache keys.
 *
 */
public final class CacheKeys
{
    /**
     * Hidden constructor.
     */
    private CacheKeys()
    {
        // no-op
    }

    /**
     * Cache key for buffered activities not yet added to cache or lists.
     */
    public static final String BUFFERED_ACTIVITIES = "BufferedActivites:";

    /**
     * Cache key for ordered comment ids by activity id.
     */
    public static final String COMMENT_IDS_BY_ACTIVITY_ID = "ActCmt:";

    /**
     * Key for CommentDTO by id.
     */
    public static final String COMMENT_BY_ID = "Cmt:";

    /**
     * Key for ActivityDTO by id.
     */
    public static final String ACTIVITY_BY_ID = "Act:";

    /**
     * Key for list of activity ids starred by a user.
     */
    public static final String STARRED_BY_PERSON_ID = "PerStar:";

    /**
     * Key for list of people who liked an activity.
     */
    public static final String LIKERS_BY_ACTIVITY_ID = "LikedByFor:";

    /**
     * Pointer key for person by opensocial id.
     */
    public static final String PERSON_BY_OPEN_SOCIAL_ID = "PerOS:";

    /**
     * Pointer key for person by account id.
     */
    public static final String PERSON_BY_ACCOUNT_ID = "PerAcct:";

    /**
     * Key for person by id.
     */
    public static final String PERSON_BY_ID = "Per:";

    /**
     * Pointer key for domain group by short name.
     */
    public static final String GROUP_BY_SHORT_NAME = "GrpShort:";

    /**
     * Key for domain group by id.
     */
    public static final String GROUP_BY_ID = "Grp:";

    /**
     * Pointer key for organization group by short name.
     */
    public static final String ORGANIZATION_BY_SHORT_NAME = "OrgShort:";

    /**
     * Key for organization by id.
     */
    public static final String ORGANIZATION_BY_ID = "Org:";

    /**
     * Key for followers of a person including said person.
     */
    public static final String FOLLOWERS_BY_PERSON = "Fwr:";

    /**
     * Key for followers of a person.
     */
    public static final String FOLLOWERS_BY_GROUP = "GrpFwr:";

    /**
     * Key for people a given user has followed.
     */
    public static final String PEOPLE_FOLLOWED_BY_PERSON = "PerFld:";

    /**
     * Key for groups a given user has followed.
     */
    public static final String GROUPS_FOLLOWED_BY_PERSON = "GrpFld:";

    /**
     * Key for coordinator ids by group id.
     */
    public static final String COORDINATOR_PERSON_IDS_BY_GROUP_ID = "GrpCoord:";

    /**
     * Key for org ids directly cooridinated by a person.
     */
    public static final String ORG_IDS_DIRECTLY_COORD_BY_PERSON = "OrgIdsCoordByPer:";

    /**
     * Key for list of activity ids of all users being followed by a person.
     */
    public static final String ACTIVITIES_BY_FOLLOWING = "CmpFwg:";

    /**
     * Key for the list of an Organization's parent ids recursively up the tree.
     */
    public static final String ORGANIZATION_PARENTS_RECURSIVE = "OrgParents:";

    /**
     * Key for the list of an Organization's direct children Organization ids.
     */
    public static final String ORGANIZATION_DIRECT_CHILDREN = "OrgChld:";

    /**
     * Key for the list of an Organization's children, recursively.
     */
    public static final String ORGANIZATION_RECURSIVE_CHILDREN = "OrgChldRec:";

    /**
     * Key for the OrganizationTreeDTO.
     */
    public static final String ORGANIZATION_TREE_DTO = "OrgTree";

    /**
     * Key for the list of an Organization's coordinators.
     */
    public static final String ORGANIZATION_COORDINATORS_BY_ORG_ID = "OrgCoord:";

    /**
     * Key for the list of an Organization's leaders.
     */
    public static final String ORGANIZATION_LEADERS_BY_ORG_ID = "OrgLead:";

    /**
     * Key for the set of ids of private groups that a user has view access to as a coordinator (org/group).
     */
    public static final String PRIVATE_GROUP_IDS_VIEWABLE_BY_PERSON_AS_COORDINATOR = "PrvGrpVw:";

    /**
     * Key for the count of unread notifications for a user.
     */
    public static final String UNREAD_APPLICATION_ALERT_COUNT_BY_USER = "UnreadAlerts:";

    /**
     * Key for system settings.
     */
    public static final String SYSTEM_SETTINGS = "SystemSettings";

    /**
     * Key for activity security information.
     */
    public static final String ACTIVITY_SECURITY_BY_ID = "ActSec:";

    /**
     * Activity liked by a person.
     */
    public static final String LIKED_BY_PERSON_ID = "PerLike:";

    /**
     * Popular hashag list by stream type and short name.
     */
    public static final String POPULAR_HASH_TAGS_BY_STREAM_TYPE_AND_SHORT_NAME = "PHT:";

    /**
     * Cache key for the everyone activity list.
     */
    public static final String EVERYONE_ACTIVITY_IDS = "ActAll";

    /**
     * Entity activity stream.
     */
    public static final String ENTITY_STREAM_BY_SCOPE_ID = "EntStr:";

    /**
     * Cache key prefix for all activity ids for an organization (recursive) by short name.
     */
    public static final String ACTIVITY_IDS_FOR_ORG_BY_SHORTNAME_RECURSIVE = "OrgActIds:";

}
