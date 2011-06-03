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

/**
 * <h2>{@link CodaSetSourceProvider}<br> <sub>Provides sources from codaset.com.</sub></h2>
 *
 * <p> <i>Feb 3, 2010</i> </p>
 *
 * @author lhunath
 */
public class CodaSetSourceProvider extends AbstractSourceProvider {

    /**
     * Create a new {@link CodaSetSourceProvider} instance.
     */
    public CodaSetSourceProvider() {

        this( null );
    }

    /**
     * Create a new {@link CodaSetSourceProvider} instance.
     *
     * @param author The CodaSet username of the owner of the project.
     */
    public CodaSetSourceProvider(final String author) {

        this( author, null );
    }

    /**
     * Create a new {@link CodaSetSourceProvider} instance.
     *
     * @param author  The CodaSet username of the owner of the project.
     * @param project The CodaSet project for the application.
     */
    public CodaSetSourceProvider(final String author, final String project) {

        super( author, project );
    }

    @Override
    public String getTitle() {

        return "Codaset";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHomepage() {

        return String.format( "http://codaset.com/%s/%s", //
                              author == null? app.getAuthor(): author, //
                              project == null? app.getInternalName(): project );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findSnapshotLinkFor(final AppVersion version) {

        return String.format( "http://codaset.com/%s/%s/source/%s/download", //
                              author == null? app.getAuthor(): author, //
                              project == null? app.getInternalName(): project, //
                              version.getInternalVersion() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findTreeLinkFor(final AppVersion version) {

        return String.format( "http://codaset.com/%s/%s/source/%s/tree", //
                              author == null? app.getAuthor(): author, //
                              project == null? app.getInternalName(): project, //
                              version.getInternalVersion() );
    }
}