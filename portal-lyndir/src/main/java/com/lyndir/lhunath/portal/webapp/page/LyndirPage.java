package com.lyndir.lhunath.portal.webapp.page;

import org.jetbrains.annotations.NotNull;


/**
 * <i>06 01, 2011</i>
 *
 * @author lhunath
 */
public abstract class LyndirPage extends PortalPage {

    @NotNull
    @Override
    protected String getGoogleAnalyticsID() {

        return "UA-90535-12";
    }
}
