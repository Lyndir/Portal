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
package com.lyndir.lhunath.portal.apps.webapp;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;


/**
 * <h2>{@link AppsSession}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> [description / usage]. </p>
 *
 * <p> <i>Feb 9, 2010</i> </p>
 *
 * @author lhunath
 */
public class AppsSession extends WebSession {

    String styleURL;

    /**
     * @param request The request that initiates this session.
     */
    public AppsSession(final Request request) {

        super( request );
    }

    public static AppsSession get() {

        return (AppsSession) Session.get();
    }

    /**
     * @return The styleURL of this {@link AppsSession}.
     */
    public String getStyleURL() {

        return styleURL;
    }

    /**
     * @param styleURL The styleURL of this {@link AppsSession}.
     *
     * @return <code>this</code>
     */
    public AppsSession setStyleURL(final String styleURL) {

        this.styleURL = styleURL;

        return this;
    }
}
