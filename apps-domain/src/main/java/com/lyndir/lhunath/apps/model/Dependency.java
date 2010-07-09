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
 * <h2>{@treeLink Dependency}<br> <sub>Defines a project dependency and the location to obtain it.</sub></h2>
 *
 * <p> <i>Mar 11, 2010</i> </p>
 *
 * @author lhunath
 */
public class Dependency implements Serializable {

    private String name;
    private String treeLink;
    private String snapshotLink;

    /**
     * Create a new {@treeLink Dependency} instance.
     *
     * @param name         The string used to name the dependency to the user.
     * @param treeLink     A URL that will be used to link the user to a place where he can view the dependency.
     * @param snapshotLink A URL that will be used to link the user to a place where he can obtain the dependency.
     */
    public Dependency(String name, String treeLink, String snapshotLink) {

        setName( name );
        setTreeLink( treeLink );
        setSnapshotLink( snapshotLink );
    }

    /**
     * @return The name of this {@treeLink Dependency}.
     */
    public String getName() {

        return name;
    }

    /**
     * @param name The name of this {@treeLink Dependency}.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return The treeLink of this {@treeLink Dependency}.
     */
    public String getTreeLink() {

        return treeLink;
    }

    /**
     * @param treeLink The treeLink of this {@treeLink Dependency}.
     */
    public void setTreeLink(String treeLink) {

        this.treeLink = treeLink;
    }

    /**
     * @param snapshotLink The snapshotLink of this {@link Dependency}.
     */
    public void setSnapshotLink(String snapshotLink) {

        this.snapshotLink = snapshotLink;
    }

    /**
     * @return The snapshotLink of this {@link Dependency}.
     */
    public String getSnapshotLink() {

        return snapshotLink;
    }
}
