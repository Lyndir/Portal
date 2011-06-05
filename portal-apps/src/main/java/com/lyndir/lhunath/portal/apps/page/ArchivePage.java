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
package com.lyndir.lhunath.portal.apps.page;

import com.lyndir.lhunath.portal.apps.model.AppVersion;
import com.lyndir.lhunath.portal.apps.model.Dependency;
import java.text.DateFormat;
import java.util.*;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;


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

    @Override
    protected void onInitialize() {

        super.onInitialize();

        add(
                new ListView<AppVersion>(
                        "entries", new LoadableDetachableModel<List<? extends AppVersion>>() {
                    @Override
                    protected List<? extends AppVersion> load() {

                        List<AppVersion> versions = new LinkedList<AppVersion>( AppVersion.getAll() );
                        Collections.reverse( versions );

                        return versions;
                    }
                } ) {

                    @Override
                    protected void populateItem(final ListItem<AppVersion> entryItem) {

                        final AppVersion version = entryItem.getModelObject();

                        entryItem.add( new Label( "version", version.getFriendlyVersion() ) );
                        entryItem.add(
                                new Label(
                                        "date", DateFormat.getDateInstance( DateFormat.LONG, getLocale() ).format(
                                        version.getCompletionDate() ) ) );

                        entryItem.add( new ContextImage( "screenshot", version.getScreenShotLink() ) );
                        entryItem.add(
                                new ExternalLink( "youtube", version.getYouTubeLink() ) {

                                    @Override
                                    protected void onComponentTag(final ComponentTag tag) {

                                        tag.put( "onclick", version.getYouTubePageTrackCode() );

                                        super.onComponentTag( tag );
                                    }

                                    @Override
                                    protected void onConfigure() {

                                        super.onConfigure();

                                        setVisible( version.hasVideo() );
                                    }
                                } );
                        entryItem.add(
                                new ExternalLink( "flash", version.getFLVLink() ) {

                                    @Override
                                    protected void onComponentTag(final ComponentTag tag) {

                                        tag.put( "onclick", version.getFLVPageTrackCode() );

                                        super.onComponentTag( tag );
                                    }

                                    @Override
                                    protected void onConfigure() {

                                        super.onConfigure();

                                        setVisible( version.hasVideo() );
                                    }
                                } );
                        entryItem.add(
                                new ExternalLink( "mpeg", version.getMP4Link() ) {

                                    @Override
                                    protected void onComponentTag(final ComponentTag tag) {

                                        tag.put( "onclick", version.getMP4PageTrackCode() );

                                        super.onComponentTag( tag );
                                    }

                                    @Override
                                    protected void onConfigure() {

                                        super.onConfigure();

                                        setVisible( version.hasVideo() );
                                    }
                                } );

                        entryItem.add(
                                new ListView<String>( "changes", version.getChanges() ) {

                                    @Override
                                    protected void populateItem(final ListItem<String> changesItem) {

                                        changesItem.add( new Label( "description", changesItem.getModelObject() ) );
                                    }
                                } );
                        entryItem.add(
                                new WebMarkupContainer( "source" ) {
                                    @Override
                                    protected void onInitialize() {

                                        super.onInitialize();

                                        add(
                                                new ExternalLink(
                                                        "treeLink", version.getApp().getSourceProvider().findTreeLinkFor(
                                                        version ) ) {

                                                    @Override
                                                    protected void onInitialize() {

                                                        super.onInitialize();

                                                        add(
                                                                new ExternalLink(
                                                                        "snapshotLink", version.getApp()
                                                                                               .getSourceProvider()
                                                                                               .findSnapshotLinkFor( version ) ) );
                                                    }
                                                } );
                                    }
                                } );
                        entryItem.add(
                                new ListView<Dependency>( "dependencies", version.getDependencies() ) {

                                    @Override
                                    protected void populateItem(final ListItem<Dependency> item) {

                                        item.add(
                                                new ExternalLink( "treeLink", item.getModelObject().getTreeLink() ) {

                                                    @Override
                                                    protected void onInitialize() {

                                                        super.onInitialize();

                                                        add( new Label( "name", item.getModelObject().getName() ) );
                                                        add( new ExternalLink( "snapshotLink", item.getModelObject().getSnapshotLink() ) );
                                                    }
                                                } );
                                    }

                                    @Override
                                    protected void onConfigure() {

                                        super.onConfigure();

                                        setVisible( !getModelObject().isEmpty() );
                                    }
                                } );
                    }
                } );
    }
}
