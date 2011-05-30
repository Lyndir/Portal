package com.lyndir.lhunath.portal.webapp;

import com.lyndir.lhunath.portal.webapp.page.*;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.HybridUrlCodingStrategy;


/**
 * <h2>{@link PortalWebApplication}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public class PortalWebApplication extends WebApplication {

    @Override
    protected void init() {

        mount( new HybridUrlCodingStrategy( "/about", AboutPage.class ) );
        mount( new HybridUrlCodingStrategy( "/contact", ContactPage.class ) );
        mount( new HybridUrlCodingStrategy( "/creations", CreationsPage.class ) );
    }

    @Override
    public Class<? extends Page> getHomePage() {

        return AboutPage.class;
    }
}
