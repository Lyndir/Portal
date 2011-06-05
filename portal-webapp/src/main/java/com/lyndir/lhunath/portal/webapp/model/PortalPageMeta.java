package com.lyndir.lhunath.portal.webapp.model;

import com.lyndir.lhunath.portal.webapp.page.PortalPage;
import java.io.Serializable;
import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;


/**
 * <h2>{@link PortalPageMeta}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public class PortalPageMeta implements Serializable {

    private final Class<? extends Page> page;
    private final String mountPoint;
    private final String icon;
    private final IModel<String> name;

    public PortalPageMeta(final Class<? extends PortalPage> page, final String mountPoint, final String icon,
                          final IModel<String> name) {
        this.page = page;
        this.mountPoint = mountPoint;
        this.icon = icon;
        this.name = name;
    }

    public Class<? extends Page> getPage() {

        return page;
    }

    public String getMountPoint() {

        return mountPoint;
    }

    public String getIcon() {

        return icon;
    }

    public IModel<String> getName() {

        return name;
    }
}
