package com.lyndir.lhunath.portal.apps.page;

import com.lyndir.lhunath.portal.apps.model.AppVersion;
import com.lyndir.lhunath.portal.apps.webapp.AppsSession;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.*;
import org.jetbrains.annotations.NotNull;


/**
 * <i>06 01, 2011</i>
 *
 * @author lhunath
 */
public abstract class AppsPage extends com.lyndir.lhunath.portal.webapp.page.PortalPage {

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
                } );
    }

    @Override
    protected String getGoogleAnalyticsID() {

        return AppVersion.getLatest().getApp().getGoogleAnalyticsID();
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

    @NotNull
    @Override
    protected String loadFooter() {

        return "";
    }

    @Override
    protected IModel<String> getPageImage() {

        return Model.of();
    }
}
