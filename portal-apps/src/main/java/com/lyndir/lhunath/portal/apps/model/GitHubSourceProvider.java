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

import org.jetbrains.annotations.Nullable;


/**
 * <h2>{@link GitHubSourceProvider}<br> <sub>Provides sources from github.com.</sub></h2>
 *
 * <p> <i>Feb 3, 2010</i> </p>
 *
 * @author lhunath
 */
public class GitHubSourceProvider extends AbstractSourceProvider {

    /**
     * Create a new {@link GitHubSourceProvider} instance.
     */
    public GitHubSourceProvider() {

        this( null );
    }

    /**
     * Create a new {@link GitHubSourceProvider} instance.
     *
     * @param author The GitHub username of the owner of the project.
     */
    public GitHubSourceProvider(@Nullable final String author) {

        this( author, null );
    }

    /**
     * Create a new {@link GitHubSourceProvider} instance.
     *
     * @param author  The GitHub username of the owner of the project.
     * @param project The GitHub project for the application.
     */
    public GitHubSourceProvider(@Nullable final String author, @Nullable final String project) {

        super( author, project );
    }

    @Override
    public String getTitle() {

        return "GitHub";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHomepage() {

        return String.format( "http://wiki.github.com/%s/%s", //
                              author == null? app.getAuthor(): author, //
                              project == null? app.getFriendlyName(): project );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findSnapshotLinkFor(final AppVersion version) {

        return String.format( "http://github.com/%s/%s/zipball/%s", //
                              author == null? app.getAuthor(): author, //
                              project == null? app.getFriendlyName(): project, //
                              version.getInternalVersion() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findTreeLinkFor(final AppVersion version) {

        return String.format( "http://github.com/%s/%s/tree/%s", //
                              author == null? app.getAuthor(): author, //
                              project == null? app.getFriendlyName(): project, //
                              version.getInternalVersion() );
    }
}
