package com.lyndir.lhunath.portal.webapp;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.Iterables;
import com.lyndir.lhunath.portal.webapp.model.PortalPageMeta;
import java.util.List;
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
public abstract class PortalWebApplication extends WebApplication {

    @Override
    protected void init() {

        for (final PortalPageMeta portalPage : getPortalPages())
            mount( new HybridUrlCodingStrategy( portalPage.getMountPoint(), portalPage.getPage() ) );
    }

    public abstract List<PortalPageMeta> getPortalPages();

    @Override
    public Class<? extends Page> getHomePage() {

        return checkNotNull( Iterables.getFirst( getPortalPages(), null ) ).getPage();
    }

    public static PortalWebApplication get() {

        return (PortalWebApplication) WebApplication.get();
    }
}
