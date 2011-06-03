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

import com.google.common.collect.ImmutableMap;
import com.lyndir.lhunath.portal.apps.webapp.error.*;
import com.lyndir.lhunath.portal.apps.webapp.page.*;
import com.lyndir.lhunath.portal.webapp.PortalWebApplication;
import com.lyndir.lhunath.portal.webapp.listener.PortalGuiceContext;
import java.util.Map;
import org.apache.wicket.*;
import org.apache.wicket.guice.InjectionFlagCachingGuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IExceptionSettings;


/**
 * <h2>{@link AppsWebApplication}<br> <sub>Base {@link WebApplication} for applications.</sub></h2>
 *
 * <p> <i>May 31, 2009</i> </p>
 *
 * @author lhunath
 */
public class AppsWebApplication extends PortalWebApplication {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init() {

        super.init();

        getApplicationSettings().setPageExpiredErrorPage( PageExpiredErrorPage.class );
        getApplicationSettings().setAccessDeniedPage( AccessDeniedErrorPage.class );
        getApplicationSettings().setInternalErrorPage( InternalErrorPage.class );

        getExceptionSettings().setUnexpectedExceptionDisplay( IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE );

        addComponentInstantiationListener( new InjectionFlagCachingGuiceComponentInjector( this, PortalGuiceContext.get() ) );
    }

    @Override
    protected Map<String, Class<? extends Page>> getMountPoints() {

        return ImmutableMap.<String, Class<? extends Page>>builder()
                           .put( "demo", DemoPage.class )
                           .put( "about", AboutPage.class )
                           .put( "archive", ArchivePage.class )
                           .put( "development", DevelopmentPage.class )
                           .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Page> getHomePage() {

        return DemoPage.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session newSession(final Request request, final Response response) {

        return new AppsSession( request );
    }
}
