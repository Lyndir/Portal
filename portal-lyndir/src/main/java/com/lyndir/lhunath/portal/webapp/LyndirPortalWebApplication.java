package com.lyndir.lhunath.portal.webapp;

import com.google.common.collect.ImmutableMap;
import com.lyndir.lhunath.portal.webapp.page.*;
import java.util.Map;
import org.apache.wicket.Page;


/**
 * <h2>{@link PortalWebApplication}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public class LyndirPortalWebApplication extends PortalWebApplication {

    @Override
    protected Map<String, Class<? extends Page>> getMountPoints() {

        return ImmutableMap.<String, Class<? extends Page>>builder()
                           .put( "/about", AboutPage.class )
                           .put( "/contact", ContactPage.class )
                           .put( "/creations", CreationsPage.class )
                           .build();
    }

    @Override
    public Class<? extends Page> getHomePage() {

        return AboutPage.class;
    }
}
