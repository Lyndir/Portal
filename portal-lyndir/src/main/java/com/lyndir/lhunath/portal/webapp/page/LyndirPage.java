package com.lyndir.lhunath.portal.webapp.page;

import com.google.common.collect.ImmutableList;
import com.lyndir.lhunath.portal.webapp.model.Tab;
import org.apache.wicket.model.Model;


/**
 * <i>06 01, 2011</i>
 *
 * @author lhunath
 */
public abstract class LyndirPage extends PortalPage {

    @Override
    protected String getGoogleAnalyticsID() {

        return "UA-90535-8";
    }

    @Override
    protected ImmutableList<Tab> getMenu() {

        return ImmutableList.of(
                new Tab( AboutPage.class, "♔", Model.of( "About" ) ), //
                new Tab( ContactPage.class, "♗", Model.of( "Contact" ) ), //
                new Tab( CreationsPage.class, "♖", Model.of( "Creations" ) ) //
        );
    }
}
