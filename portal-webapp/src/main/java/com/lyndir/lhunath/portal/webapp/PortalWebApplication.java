package com.lyndir.lhunath.portal.webapp;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.lyndir.lhunath.portal.webapp.error.*;
import com.lyndir.lhunath.portal.webapp.listener.PortalGuiceContext;
import com.lyndir.lhunath.portal.webapp.model.PortalPageMeta;
import com.lyndir.lhunath.portal.webapp.model.StripItem;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.guice.InjectionFlagCachingGuiceComponentInjector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.HybridUrlCodingStrategy;
import org.apache.wicket.settings.IExceptionSettings;


/**
 * <h2>{@link PortalWebApplication}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public abstract class PortalWebApplication extends WebApplication {

    private final IModel<List<? extends StripItem>> stripItems = Model.ofList(
            ImmutableList.of(
                    new StripItem(
                            Model.of( "Lyndir" ), "images/icon.lyndir.png", "images/logo.png", "http://www.lyndir.com",
                            Model.of( "Modern design and development" ), null ), //
                    new StripItem(
                            Model.of( "Gorillas" ), "images/icon.gorillas.png", "images/logo.png", "http://gorillas.lyndir.com",
                            Model.of( "Gorillas is a resurrection of the classic QBasic game shipped with MS-DOS 5." ), null ), //
                    new StripItem(
                            Model.of( "DeBlock" ), "images/icon.deblock.png", "images/logo.png", "http://deblock.lyndir.com", //
                            Model.of(
                                    "DeBlock is a block destruction game in the style of Bejeweled or Destruct-o-Block, "
                                    + "but unique altogether." ), null ), //
                    new StripItem(
                            Model.of( "Opal" ), "images/icon.opal.png", "images/logo.png", "http://opal.lyndir.com",
                            Model.of( "Collection of Java convenience libraries." ), null ), //
                    new StripItem(
                            Model.of( "iLibs" ), "images/icon.ilibs.png", "images/logo.png", "http://ilibs.lyndir.com",
                            Model.of( "Collection of iOS convenience libraries." ), null ) ) );

    @Override
    protected void init() {

        for (final PortalPageMeta portalPage : getPortalPages())
            mount( new HybridUrlCodingStrategy( portalPage.getMountPoint(), portalPage.getPage() ) );

        getApplicationSettings().setPageExpiredErrorPage( PageExpiredErrorPage.class );
        getApplicationSettings().setAccessDeniedPage( AccessDeniedErrorPage.class );
        getApplicationSettings().setInternalErrorPage( InternalErrorPage.class );

        getExceptionSettings().setUnexpectedExceptionDisplay( IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE );

        addComponentInstantiationListener( new InjectionFlagCachingGuiceComponentInjector( this, PortalGuiceContext.get() ) );
    }

    public abstract List<PortalPageMeta> getPortalPages();

    public IModel<List<? extends StripItem>> getStripItems() {

        return stripItems;
    }

    public abstract StripItem getActiveItem();

    @Override
    public Class<? extends Page> getHomePage() {

        return checkNotNull( Iterables.getFirst( getPortalPages(), null ) ).getPage();
    }

    public static PortalWebApplication get() {

        return (PortalWebApplication) WebApplication.get();
    }
}
