package com.lyndir.lhunath.portal.webapp.model;

import java.io.Serializable;
import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;


/**
 * <h2>{@link Tab}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public class Tab implements Serializable {

    private final Class<? extends Page> page;
    private final String icon;
    private final IModel<String> name;

    public Tab(final Class<? extends Page> page, final String icon, final IModel<String> name) {
        this.page = page;
        this.icon = icon;
        this.name = name;
    }

    public Class<? extends Page> getPage() {

        return page;
    }

    public String getIcon() {

        return icon;
    }

    public IModel<String> getName() {

        return name;
    }
}
