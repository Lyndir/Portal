package com.lyndir.lhunath.portal.apps.webapp.page;

import com.google.common.collect.ImmutableList;
import com.lyndir.lhunath.portal.apps.model.AppVersion;
import com.lyndir.lhunath.portal.apps.webapp.AppsSession;
import com.lyndir.lhunath.portal.webapp.model.Tab;
import com.lyndir.lhunath.portal.webapp.page.PortalPage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.*;
import org.jetbrains.annotations.Nullable;


/**
 * <i>06 01, 2011</i>
 *
 * @author lhunath
 */
public abstract class AppsPage extends PortalPage {

    @Override
    protected void onInitialize() {

        super.onInitialize();

        add(
                new ExternalLink(
                        "style", new AbstractReadOnlyModel<String>() {

                    @Override
                    public String getObject() {

                        return AppsSession.get().getStyleURL();
                    }
                } ) {

                    @Override
                    public boolean isVisible() {

                        return AppsSession.get().getStyleURL() != null;
                    }
                } );
        add(
                new ExternalLink(
                        "appstore", new AbstractReadOnlyModel<String>() {

                    @Override
                    public String getObject() {

                        return AppVersion.getLatest().getAppStoreLink();
                    }
                } ) {
                    @Override
                    protected void onConfigure() {

                        super.onConfigure();

                        setVisible( getDefaultModelObject() != null );
                    }
                });
    }

    @Override
    protected String getGoogleAnalyticsID() {

        return AppVersion.getLatest().getApp().getGoogleAnalyticsID();
    }

    @Override
    protected ImmutableList<Tab> getMenu() {

        return ImmutableList.of(
                new Tab( DemoPage.class, "♕", Model.of( "Demo" ) ), //
                new Tab( AboutPage.class, "♔", Model.of( "About" ) ), //
                new Tab( ArchivePage.class, "♘", Model.of( "Versions" ) ), //
                new Tab( DevelopmentPage.class, "♖", Model.of( "Development" ) ) //
        );
    }

    @Override
    protected IModel<String> getPageTitle() {

        return new LoadableDetachableModel<String>() {
            @Override
            protected String load() {

                return AppVersion.getLatest().getApp().getFriendlyName();
            }
        };
    }

    @Nullable
    @Override
    protected String loadFooter() {

        return null;
    }

    @Override
    protected IModel<String> getPageImage() {

        return Model.of();
    }
}
