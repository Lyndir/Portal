/*
 *   Copyright 2010, Maarten Billemont
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lyndir.lhunath.portal.apps.model;

import static com.lyndir.lhunath.opal.system.util.ObjectUtils.ifNotNullElse;

import java.io.Serializable;


/**
 * <h2>{@link App}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> [description / usage]. </p>
 *
 * <p> <i>Feb 3, 2010</i> </p>
 *
 * @author lhunath
 */
public class App implements Serializable {

    private final String         author;
    private final String         friendlyName;
    private final String         internalName;
    private       String         sourceCodeName;
    private       String         issueTrackerName;
    private final SourceProvider sourceProvider;
    private final IssueTracker   issueTracker;
    private final String         googleAnalyticsID;

    /**
     * Create a new {@link App} instance.
     *
     * @param author            The name of the owner/author of the application.
     * @param friendlyName      The name of this application as it will appear in the user interface.
     * @param internalName      The name of this application for use in internal references.
     * @param sourceProvider    The instance that will provide the location of the source code for this application.
     * @param issueTracker      The instance that will provide the location of the issue tracker for this application.
     * @param googleAnalyticsID The web property ID of the Google Analytics profile under which user page access should be tracked.
     */
    public App(final String author, final String friendlyName, final String internalName, final SourceProvider sourceProvider,
               final IssueTracker issueTracker, final String googleAnalyticsID) {

        this.author = author;
        this.friendlyName = friendlyName;
        this.internalName = internalName;
        this.sourceProvider = sourceProvider;
        this.issueTracker = issueTracker;
        this.googleAnalyticsID = googleAnalyticsID;
    }

    /**
     * @return The author of this {@link App}.
     */
    public String getAuthor() {

        return author;
    }

    /**
     * @return The friendlyName of this {@link App}.
     */
    public String getFriendlyName() {

        return friendlyName;
    }

    /**
     * @return The internalName of this {@link App}.
     */
    public String getInternalName() {

        return internalName;
    }

    public String getSourceCodeName() {

        return ifNotNullElse( sourceCodeName, getInternalName() );
    }

    public void setSourceCodeName(final String sourceCodeName) {

        this.sourceCodeName = sourceCodeName;
    }

    public String getIssueTrackerName() {

        return ifNotNullElse( issueTrackerName, getInternalName() );
    }

    public void setIssueTrackerName(final String issueTrackerName) {

        this.issueTrackerName = issueTrackerName;
    }

    /**
     * @return The sourceProvider of this {@link App}.
     */
    public SourceProvider getSourceProvider() {

        return sourceProvider;
    }

    public IssueTracker getIssueTracker() {

        return issueTracker;
    }

    /**
     * @return The googleAnalyticsID of this {@link App}.
     */
    public String getGoogleAnalyticsID() {

        return googleAnalyticsID;
    }
}
