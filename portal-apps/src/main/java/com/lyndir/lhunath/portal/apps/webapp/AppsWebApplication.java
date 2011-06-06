package com.lyndir.lhunath.portal.apps.webapp;

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

import com.google.common.collect.ImmutableList;
import com.lyndir.lhunath.portal.apps.page.*;
import com.lyndir.lhunath.portal.webapp.PortalWebApplication;
import com.lyndir.lhunath.portal.webapp.model.PortalPageMeta;
import java.util.List;
import org.apache.wicket.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;


/**
 * <h2>{@link AppsWebApplication}<br> <sub>Base {@link WebApplication} for applications.</sub></h2>
 *
 * <p> <i>May 31, 2009</i> </p>
 *
 * @author lhunath
 */
public abstract class AppsWebApplication extends PortalWebApplication {

    @Override
    public List<PortalPageMeta> getPortalPages() {

        return ImmutableList.<PortalPageMeta>builder()
                            .add( new PortalPageMeta( DemoPage.class, "demo", "♕", Model.of( "Demo" ) ) )
                            .add( new PortalPageMeta( AboutPage.class, "about", "♔", Model.of( "About" ) ) )
                            .add( new PortalPageMeta( ArchivePage.class, "versions", "♘", Model.of( "Versions" ) ) )
                            .add( new PortalPageMeta( DevelopmentPage.class, "development", "♖", Model.of( "Development" ) ) )
                            .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session newSession(final Request request, final Response response) {

        return new AppsSession( request );
    }
}
