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
package com.lyndir.lhunath.apps.listener;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.inject.*;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.lyndir.lhunath.apps.webapp.AppsWebApplication;
import java.util.Collection;
import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;


/**
 * <h2>{@link AppsListener}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>Jan 11, 2010</i> </p>
 *
 * @author lhunath
 */
public class AppsListener extends GuiceServletContextListener {

    private static final String PATH_WICKET = "/*";
    public static Injector injector;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Injector getInjector() {

        return injector = Guice.createInjector( Stage.PRODUCTION, //
                                                new ImmutableList.Builder<Module>().add( getWicketModule() )
                                                        .addAll( getApplicationModules() ).build() );
    }

    /**
     * Used to configure Wicket initialization.
     *
     * @return A {@link WicketServletModule}.
     */
    protected WicketServletModule getWicketModule() {

        return new WicketServletModule( AppsWebApplication.class );
    }

    /**
     * Used to configure your application's Guice set-up.
     *
     * @return Some Guice {@link Module}s.
     */
    protected Collection<? extends Module> getApplicationModules() {

        return ImmutableList.of();
    }

    /**
     * <h2>{@link WicketServletModule}<br> <sub>Basic servlet {@link Module} that adds Wicket to the servlet context.</sub></h2>
     *
     * <p> <i>Feb 4, 2010</i> </p>
     *
     * @author lhunath
     */
    protected static class WicketServletModule extends ServletModule {

        private Class<? extends AppsWebApplication> application;

        public WicketServletModule(Class<? extends AppsWebApplication> application) {

            this.application = application;
        }

        @Override
        protected void configureServlets() {

            ImmutableMap.Builder<String, String> paramBuilder = new ImmutableMap.Builder<String, String>();

            // Tell Wicket what URL pattern it'll be bound on.
            paramBuilder.put( WicketFilter.FILTER_MAPPING_PARAM, //
                              PATH_WICKET );
            // Tell Wicket what URL pattern it'll be bound on.
            paramBuilder.put( WicketFilter.APP_FACT_PARAM, //
                              ContextParamWebApplicationFactory.class.getCanonicalName() );
            // Wicket Application.
            paramBuilder.put( ContextParamWebApplicationFactory.APP_CLASS_PARAM, //
                              application.getCanonicalName() );
            paramBuilder.put( Application.CONFIGURATION, //
                              Application.DEPLOYMENT );

            // Bind the filter to the servlet context.
            filter( PATH_WICKET ).through( WicketFilter.class, paramBuilder.build() );
            bind( WicketFilter.class ).in( Scopes.SINGLETON );
        }
    }
}
