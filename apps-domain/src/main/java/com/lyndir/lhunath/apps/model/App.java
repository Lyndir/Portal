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
package com.lyndir.lhunath.apps.model;

import java.io.Serializable;


/**
 * <h2>{@link App}<br>
 * <sub>[in short] (TODO).</sub></h2>
 *
 * <p>
 * [description / usage].
 * </p>
 *
 * <p>
 * <i>Feb 3, 2010</i>
 * </p>
 *
 * @author lhunath
 */
public class App implements Serializable {

    private String author;
    private String friendlyName;
    private String internalName;
    private SourceProvider sourceProvider;
    private String googleAnalyticsID;


    /**
     * Create a new {@link App} instance.
     *
     * @param author            The name of the owner/author of the application.
     * @param friendlyName      The name of this application as it will appear in the user interface.
     * @param internalName      The name of this application for use in internal references.
     * @param sourceProvider    The instance that will provide the location of the source code for this application.
     * @param googleAnalyticsID The web property ID of the Google Analytics profile under which user page access should be tracked.
     */
    public App(String author, String friendlyName, String internalName, SourceProvider sourceProvider,
               String googleAnalyticsID) {

        setAuthor( author );
        setFriendlyName( friendlyName );
        setInternalName( internalName );
        setSourceProvider( sourceProvider );
        sourceProvider.setApp( this );
        setGoogleAnalyticsID( googleAnalyticsID );
    }

    /**
     * @param author The author of this {@link App}.
     */
    public void setAuthor(String author) {

        this.author = author;
    }

    /**
     * @return The author of this {@link App}.
     */
    public String getAuthor() {

        return author;
    }

    /**
     * @param friendlyName The friendlyName of this {@link App}.
     */
    public void setFriendlyName(String friendlyName) {

        this.friendlyName = friendlyName;
    }

    /**
     * @return The friendlyName of this {@link App}.
     */
    public String getFriendlyName() {

        return friendlyName;
    }

    /**
     * @param internalName The internalName of this {@link App}.
     */
    public void setInternalName(String internalName) {

        this.internalName = internalName;
    }

    /**
     * @return The internalName of this {@link App}.
     */
    public String getInternalName() {

        return internalName;
    }

    /**
     * @param sourceProvider The sourceProvider of this {@link App}.
     */
    public void setSourceProvider(SourceProvider sourceProvider) {

        this.sourceProvider = sourceProvider;
    }

    /**
     * @return The sourceProvider of this {@link App}.
     */
    public SourceProvider getSourceProvider() {

        return sourceProvider;
    }

    /**
     * @param googleAnalyticsID The googleAnalyticsID of this {@link App}.
     */
    public void setGoogleAnalyticsID(String googleAnalyticsID) {

        this.googleAnalyticsID = googleAnalyticsID;
    }

    /**
     * @return The googleAnalyticsID of this {@link App}.
     */
    public String getGoogleAnalyticsID() {

        return googleAnalyticsID;
    }

}
