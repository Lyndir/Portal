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
package com.lyndir.lhunath.portal.apps.webapp.page;

import com.lyndir.lhunath.portal.apps.model.AppVersion;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.LoadableDetachableModel;


/**
 * <h2>{@link DevelopmentPage}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> [description / usage]. </p>
 *
 * <p> <i>May 31, 2009</i> </p>
 *
 * @author lhunath
 */
public class DevelopmentPage extends AppsPage {

    @Override
    protected void onInitialize() {

        super.onInitialize();

        add(
                new ExternalLink(
                        "issueTracking", new LoadableDetachableModel<String>() {
                    @Override
                    protected String load() {

                        return AppVersion.getLatest().getApp().getIssueTracker().getHomepage();
                    }
                }, new LoadableDetachableModel<String>() {
                    @Override
                    protected String load() {

                        return AppVersion.getLatest().getApp().getIssueTracker().getTitle();
                    }
                }
                ) );
        add(
                new ExternalLink(
                        "sourceCode", new LoadableDetachableModel<String>() {
                    @Override
                    protected String load() {

                        return AppVersion.getLatest().getApp().getSourceProvider().getHomepage();
                    }
                }, new LoadableDetachableModel<String>() {
                    @Override
                    protected String load() {

                        return AppVersion.getLatest().getApp().getSourceProvider().getTitle();
                    }
                }
                ) );
    }
}
