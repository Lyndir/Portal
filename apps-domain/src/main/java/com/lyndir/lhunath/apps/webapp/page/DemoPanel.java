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
package com.lyndir.lhunath.apps.webapp.page;

import com.lyndir.lhunath.apps.model.AppVersion;
import com.lyndir.lhunath.apps.webapp.JavaScriptProvider;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.util.template.PackagedTextTemplate;


/**
 * <h2>{@link DemoPanel}<br> <sub>The interface panel that shows a video demonstration of the game.</sub></h2>
 *
 * <p> <i>May 31, 2009</i> </p>
 *
 * @author lhunath
 */
public class DemoPanel extends Panel implements JavaScriptProvider {

    private static final String JS_KEY_MOVIE_LINK = "movieLink";
    private static final String JS_KEY_PAGETRACK_CODE = "pageTrackCode";

    /**
     * @param id The Wicket ID of this panel.
     */
    public DemoPanel(String id) {

        super( id );

        add( new Label( "tagline", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {

                return AppVersion.getLatest().getTagLine();
            }
        } ) {

            @Override
            public boolean isVisible() {

                return AppVersion.getLatest().getTagLine() != null;
            }
        } );

        add( new Label( "description", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {

                return AppVersion.getLatest().getDescription();
            }
        } ) {

            @Override
            public boolean isVisible() {

                return AppVersion.getLatest().getDescription() != null;
            }
        }.setEscapeModelStrings( false ) );

        add( new WebMarkupContainer( "youtube" ) {

            @Override
            protected void onComponentTag(ComponentTag tag) {

                tag.put( "href", //
                         AppVersion.getLatest().getYouTubeLink() );
                tag.put( "onclick", //
                         AppVersion.getLatest().getYouTubePageTrackCode() );

                super.onComponentTag( tag );
            }

            @Override
            public boolean isVisible() {

                return AppVersion.getLatest().hasVideo();
            }
        } );
        add( new WebMarkupContainer( "mpeg" ) {

            @Override
            protected void onComponentTag(ComponentTag tag) {

                tag.put( "href", //
                         AppVersion.getLatest().getMP4Link() );
                tag.put( "onclick", //
                         AppVersion.getLatest().getMP4PageTrackCode() );

                super.onComponentTag( tag );
            }

            @Override
            public boolean isVisible() {

                return AppVersion.getLatest().hasVideo();
            }
        } );

        add( new WebMarkupContainer( "iphone-youtube" ) {

            @Override
            protected void onComponentTag(ComponentTag tag) {

                tag.put( "href", //
                         AppVersion.getLatest().getYouTubeLink() );
                tag.put( "onclick", //
                         AppVersion.getLatest().getYouTubePageTrackCode() );

                super.onComponentTag( tag );
            }

            @Override
            public boolean isVisible() {

                return AppVersion.getLatest().hasVideo();
            }
        } );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProvidedJavaScript() {

        if (!AppVersion.getLatest().hasVideo())
            return null;

        Map<String, Object> variablesMap = new HashMap<String, Object>();
        variablesMap.put( JS_KEY_MOVIE_LINK, AppVersion.getLatest().getFLVLink() );
        variablesMap.put( JS_KEY_PAGETRACK_CODE, AppVersion.getLatest().getFLVPageTrackCode() );

        return new PackagedTextTemplate( getClass(), "showMovie.js" ).asString( variablesMap );
    }
}
