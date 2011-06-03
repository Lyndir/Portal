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
import com.lyndir.lhunath.portal.apps.model.Dependency;
import java.text.DateFormat;
import java.util.*;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;


/**
 * <h2>{@link ArchivePage}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> [description / usage]. </p>
 *
 * <p> <i>May 31, 2009</i> </p>
 *
 * @author lhunath
 */
public class ArchivePage extends AppsPage {

    public ArchivePage() {

        List<AppVersion> versions = new LinkedList<AppVersion>( AppVersion.getAll() );
        Collections.reverse( versions );

        ListView<AppVersion> entries = new ListView<AppVersion>( "entries", versions ) {

            @Override
            protected void populateItem(final ListItem<AppVersion> entryItem) {

                final AppVersion version = entryItem.getModelObject();

                entryItem.add( new Label( "version", version.getFriendlyVersion() ) );
                entryItem.add( new Label( "date", DateFormat.getDateInstance( DateFormat.LONG, getLocale() ).format(
                        version.getCompletionDate() ) ) );

                entryItem.add( new WebMarkupContainer( "screenshot" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put( "src", //
                                 version.getScreenShotLink() );

                        super.onComponentTag( tag );
                    }
                } );
                entryItem.add( new WebMarkupContainer( "youtube" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put( "href", //
                                 version.getYouTubeLink() );
                        tag.put( "onclick", //
                                 version.getYouTubePageTrackCode() );

                        super.onComponentTag( tag );
                    }

                    @Override
                    public boolean isVisible() {

                        return version.hasVideo();
                    }
                } );
                entryItem.add( new WebMarkupContainer( "flash" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put( "href", //
                                 version.getFLVLink() );
                        tag.put( "onclick", //
                                 version.getFLVPageTrackCode() );

                        super.onComponentTag( tag );
                    }

                    @Override
                    public boolean isVisible() {

                        return version.hasVideo();
                    }
                } );
                entryItem.add( new WebMarkupContainer( "mpeg" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put( "href", //
                                 version.getMP4Link() );
                        tag.put( "onclick", //
                                 version.getMP4PageTrackCode() );

                        super.onComponentTag( tag );
                    }

                    @Override
                    public boolean isVisible() {

                        return version.hasVideo();
                    }
                } );

                entryItem.add( new ListView<String>( "changes", version.getChanges() ) {

                    @Override
                    protected void populateItem(final ListItem<String> changesItem) {

                        changesItem.add( new Label( "description", changesItem.getModelObject() ) );
                    }
                } );

                WebMarkupContainer source = new WebMarkupContainer( "source" );
                source.add( new WebMarkupContainer( "snapshotLink" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put( "href", version.getApp().getSourceProvider().findSnapshotLinkFor( version ) );

                        super.onComponentTag( tag );
                    }
                } );
                source.add( new WebMarkupContainer( "treeLink" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put( "href", version.getApp().getSourceProvider().findTreeLinkFor( version ) );

                        super.onComponentTag( tag );
                    }
                } );
                entryItem.add( source );

                entryItem.add( new ListView<Dependency>( "dependencies", version.getDependencies() ) {

                    @Override
                    protected void populateItem(final ListItem<Dependency> item) {

                        Dependency dependency = item.getModelObject();

                        WebMarkupContainer snapshotLink = new WebMarkupContainer( "snapshotLink" ) {

                            @Override
                            protected void onComponentTag(final ComponentTag tag) {

                                tag.put( "href", //
                                         String.format( "http://github.com/lhunath/Cocos2D-iPhone/zipball/Gorillas-%s",
                                                        //
                                                        version.getInternalVersion() ) );

                                super.onComponentTag( tag );
                            }
                        };
                        WebMarkupContainer githubLink = new WebMarkupContainer( "treeLink" ) {

                            @Override
                            protected void onComponentTag(final ComponentTag tag) {

                                tag.put( "href", //
                                         String.format( "http://github.com/lhunath/Cocos2D-iPhone/tree/Gorillas-%s", //
                                                        version.getInternalVersion() ) );

                                super.onComponentTag( tag );
                            }
                        };

                        item.add( new Label( "name", dependency.getName() ) );
                        item.add( snapshotLink );
                        item.add( githubLink );
                    }

                    @Override
                    public boolean isVisible() {

                        return !getModelObject().isEmpty();
                    }
                } );
            }
        };

        add( entries );
    }
}
