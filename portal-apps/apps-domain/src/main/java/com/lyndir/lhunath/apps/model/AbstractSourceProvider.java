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

/**
 * <h2>{@link AbstractSourceProvider}<br> <sub>Basic source provider implementation.</sub></h2>
 *
 * <p> Convenience implementation which provides author, project and app data. </p>
 *
 * <p> <i>Feb 3, 2010</i> </p>
 *
 * @author lhunath
 */
public abstract class AbstractSourceProvider implements SourceProvider {

    protected App app;
    protected String author;
    protected String project;

    /**
     * Create a new {@link GitHubSourceProvider} instance.
     *
     * @param author  The GitHub username of the owner of the project.
     * @param project The GitHub project for the application.
     */
    protected AbstractSourceProvider(String author, String project) {

        setAuthor( author );
        setProject( project );
    }

    /**
     * @param app The application of this {@link GitHubSourceProvider}.
     */
    @Override
    public void setApp(App app) {

        this.app = app;
    }

    /**
     * @return The application of this {@link GitHubSourceProvider}.
     */
    public App getApp() {

        return app;
    }

    /**
     * @return The author of this {@link GitHubSourceProvider}.
     */
    public String getAuthor() {

        return author;
    }

    /**
     * @param author The author of this {@link GitHubSourceProvider}.
     */
    public void setAuthor(String author) {

        this.author = author;
    }

    /**
     * @param project The project of this {@link GitHubSourceProvider}.
     */
    public void setProject(String project) {

        this.project = project;
    }

    /**
     * @return The project of this {@link GitHubSourceProvider}.
     */
    public String getProject() {

        return project;
    }
}
