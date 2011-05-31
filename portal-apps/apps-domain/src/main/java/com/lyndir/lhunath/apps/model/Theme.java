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
 * <h2>{@link Theme}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> [description / usage]. </p>
 *
 * <p> <i>Feb 6, 2010</i> </p>
 *
 * @author lhunath
 */
public enum Theme {

    GRAY( "gray" ),
    BLUE( "blue" ),
    GREEN( "green" );

    private String internalName;

    /**
     * Create a new {@link Theme} instance.
     *
     * @param internalName The unique internal designation for the theme.
     */
    private Theme(String internalName) {

        setInternalName( internalName );
    }

    /**
     * @param internalName The internalName of this {@link Theme}.
     */
    public void setInternalName(String internalName) {

        this.internalName = internalName;
    }

    /**
     * @return The internalName of this {@link Theme}.
     */
    public String getInternalName() {

        return internalName;
    }
}
