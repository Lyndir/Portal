package com.lyndir.lhunath.portal.webapp;

import com.google.common.collect.ImmutableList;
import com.lyndir.lhunath.opal.system.logging.exception.InternalInconsistencyException;
import com.lyndir.lhunath.opal.system.util.ObjectUtils;
import com.lyndir.lhunath.portal.webapp.model.PortalPageMeta;
import com.lyndir.lhunath.portal.webapp.model.StripItem;
import com.lyndir.lhunath.portal.webapp.page.*;
import java.util.List;
import org.apache.wicket.model.Model;


/**
 * <h2>{@link PortalWebApplication}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public class LyndirPortalWebApplication extends PortalWebApplication {

    @Override
    public StripItem getActiveItem() {

        for (final StripItem stripItem : getStripItems().getObject())
            if (ObjectUtils.isEqual( stripItem.getLink(), "http://www.lyndir.com" ))
                return stripItem;

        throw new InternalInconsistencyException( "Active strip not found." );
    }

    @Override
    public List<PortalPageMeta> getPortalPages() {

        return ImmutableList.<PortalPageMeta>builder()
                            .add( new PortalPageMeta( AboutPage.class, "about", "♔", Model.of( "About" ) ) )
                            .add( new PortalPageMeta( ContactPage.class, "contact", "♗", Model.of( "Contact" ) ) )
                            .add( new PortalPageMeta( CreationsPage.class, "creations", "♖", Model.of( "Creations" ) ) )
                            .build();
    }
}
