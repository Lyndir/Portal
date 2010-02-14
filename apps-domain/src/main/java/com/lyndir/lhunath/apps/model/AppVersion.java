/*
 *   Copyright 2009, Maarten Billemont
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * <h2>{@link AppVersion}<br>
 * <sub>[in short] (TODO).</sub></h2>
 * 
 * <p>
 * [description / usage].
 * </p>
 * 
 * <p>
 * <i>Jun 2, 2009</i>
 * </p>
 * 
 * @author lhunath
 */
public class AppVersion implements Serializable, Comparable<AppVersion> {

    private static final SortedMap<String, AppVersion> versions = new TreeMap<String, AppVersion>();

    private App                                        app;
    private String                                     internalVersion;
    private String                                     friendlyVersion;
    private String                                     tagLine;
    private String                                     description;
    private List<String>                               dependencies;
    private Date                                       completionDate;
    private String                                     youTubeID;
    private List<String>                               changes;


    public static List<AppVersion> getAll() {

        return Collections.unmodifiableList( new ArrayList<AppVersion>( versions.values() ) );
    }

    public static AppVersion getLatest() {

        return versions.get( versions.lastKey() );
    }

    public static void register(AppVersion version) {

        versions.put( version.getInternalVersion(), version );
    }

    public AppVersion(App app, String internalVersion, String friendlyVersion, String tagLine, String description,
                      List<String> dependencies, Date completionDate, String youTubeID, String... changes) {

        this.app = app;
        this.internalVersion = internalVersion;
        this.friendlyVersion = friendlyVersion;
        this.tagLine = tagLine;
        this.description = description;
        this.dependencies = dependencies;
        this.completionDate = completionDate;
        this.youTubeID = youTubeID;
        this.changes = new LinkedList<String>( Arrays.asList( changes ) );
    }

    /**
     * @return The URI at which this version's screenshot can be found.
     */
    public String getScreenShotLink() {

        return String.format( "images/game/%s-%s.png", //
                getApp().getInternalName(), getInternalVersion() );
    }

    /**
     * @return <code>true</code> if this version has a demonstration video available.
     */
    public boolean hasVideo() {

        return getYouTubeID() != null;
    }

    /**
     * @return The URI at which this version's YouTube video can be found.
     */
    public String getYouTubeLink() {

        return String.format( "http://www.youtube.com/watch?v=%s", //
                getYouTubeID() );
    }

    /**
     * @return The JS code to trigger a page tracker hit for the YouTube movie of this version.
     */
    public String getYouTubePageTrackCode() {

        return String.format( "pageTracker._trackPageview('/movie/%s-%s.yt');", //
                getApp().getInternalName(), getInternalVersion() );
    }

    /**
     * @return The URI at which this version's FLV video can be found.
     */
    public String getFLVLink() {

        return String.format( "movies/%s-%s.flv", //
                getApp().getInternalName(), getInternalVersion() );
    }

    /**
     * @return The JS code to trigger a page tracker hit for the FLV movie of this version.
     */
    public String getFLVPageTrackCode() {

        return String.format( "pageTracker._trackPageview('/movie/%s-%s.flv');", //
                getApp().getInternalName(), getInternalVersion() );
    }

    /**
     * @return The URI at which this version's MP4 video can be found.
     */
    public String getMP4Link() {

        return String.format( "movies/%s-%s.mp4", //
                getApp().getInternalName(), getInternalVersion() );
    }

    /**
     * @return The JS code to trigger a page tracker hit for the MP4 movie of this version.
     */
    public String getMP4PageTrackCode() {

        return String.format( "pageTracker._trackPageview('/movie/%s-%s.mp4');", //
                getApp().getInternalName(), getInternalVersion() );
    }

    /**
     * @return The application of this {@link AppVersion}.
     */
    public App getApp() {

        return app;
    }

    /**
     * @return The detailed version string.
     */
    public String getInternalVersion() {

        return internalVersion;
    }

    /**
     * @return The user-friendly version string.
     */
    public String getFriendlyVersion() {

        return friendlyVersion;
    }

    /**
     * @param tagLine
     *            The tagLine of this {@link AppVersion}.
     */
    public void setTagLine(String tagLine) {

        this.tagLine = tagLine;
    }

    /**
     * @return The tagLine of this {@link AppVersion}.
     */
    public String getTagLine() {

        return tagLine;
    }

    /**
     * @param description
     *            The description of this {@link AppVersion}.
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * @return The description of this {@link AppVersion}.
     */
    public String getDescription() {

        return description;
    }

    /**
     * @return The appStoreLink of this {@link AppVersion}.
     */
    public String getAppStoreLink() {

        return String.format( "http://itunes.com/apps/%s/%s", //
                getApp().getAuthor().toLowerCase(), getApp().getFriendlyName().toLowerCase() );
    }

    /**
     * @return A map that maps the names of the external dependencies of this version to their snapshot archives.
     */
    public List<String> getDependencies() {

        return dependencies;
    }

    /**
     * @return The date when this version was completed and committed into the code repository.
     */
    public Date getCompletionDate() {

        return completionDate;
    }

    /**
     * @return The youTubeID that references a YouTube movie that demos this version
     */
    public String getYouTubeID() {

        return youTubeID;
    }

    /**
     * @return The list of changes introduced by this version since the last.
     */
    public List<String> getChanges() {

        return changes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return String.format( "{%s - %s, [yt: %s]}", friendlyVersion, internalVersion, youTubeID );
    }

    /**
     * Sorts by {@link #getInternalVersion()}.
     * 
     * {@inheritDoc}
     */
    public int compareTo(AppVersion o) {

        return getInternalVersion().compareTo( o.getInternalVersion() );
    }
}
