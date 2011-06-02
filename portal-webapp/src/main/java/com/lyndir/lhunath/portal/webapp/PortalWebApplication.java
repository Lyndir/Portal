package com.lyndir.lhunath.portal.webapp;

import java.util.Map;
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

        for (final Map.Entry<String, Class<? extends Page>> mountPoint : getMountPoints().entrySet())
            mount( new HybridUrlCodingStrategy( mountPoint.getKey(), mountPoint.getValue() ) );
    }

    protected abstract Map<String, Class<? extends Page>> getMountPoints();
}
