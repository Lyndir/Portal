package com.lyndir.lhunath.apps.webapp;

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

import com.lyndir.lhunath.apps.listener.AppsListener;
import com.lyndir.lhunath.apps.webapp.error.AccessDeniedErrorPage;
import com.lyndir.lhunath.apps.webapp.error.InternalErrorPage;
import com.lyndir.lhunath.apps.webapp.error.PageExpiredErrorPage;
import com.lyndir.lhunath.apps.webapp.page.LayoutPage;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.guice.InjectionFlagCachingGuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.HybridUrlCodingStrategy;
import org.apache.wicket.settings.IExceptionSettings;


/**
 * <h2>{@link AppsWebApplication}<br> <sub>Base {@link WebApplication} for applications.</sub></h2>
 *
 * <p> <i>May 31, 2009</i> </p>
 *
 * @author lhunath
 */
public class AppsWebApplication extends WebApplication {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init() {

        getApplicationSettings().setPageExpiredErrorPage( PageExpiredErrorPage.class );
        getApplicationSettings().setAccessDeniedPage( AccessDeniedErrorPage.class );
        getApplicationSettings().setInternalErrorPage( InternalErrorPage.class );

        getExceptionSettings().setUnexpectedExceptionDisplay( IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE );

        addComponentInstantiationListener( //
                                           new InjectionFlagCachingGuiceComponentInjector( this, AppsListener.injector ) );

        mount( new HybridUrlCodingStrategy( "/about", LayoutPage.class ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Page> getHomePage() {

        return LayoutPage.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session newSession(Request request, Response response) {

        return new AppsSession( request );
    }
}