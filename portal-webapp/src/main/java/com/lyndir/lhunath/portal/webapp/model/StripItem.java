package com.lyndir.lhunath.portal.webapp.model;

import java.io.Serializable;
import org.apache.wicket.model.Model;


/**
 * <i>05 29, 2011</i>
 *
 * @author lhunath
 */
public class StripItem implements Serializable {

    private final Model<String> name;
    private final String        icon;
    private final String        logo;
    private final String        link;
    private final Model<String> description;
    private final Model<String> footer;

    public StripItem(final Model<String> name, final String icon, final String logo, final String link, final Model<String> description,
                     final Model<String> footer) {

        this.name = name;
        this.icon = icon;
        this.logo = logo;
        this.link = link;
        this.description = description;
        this.footer = footer;
    }

    public Model<String> getName() {

        return name;
    }

    public String getIcon() {

        return icon;
    }

    public String getLogo() {

        return logo;
    }

    public String getLink() {

        return link;
    }

    public Model<String> getDescription() {

        return description;
    }

    public Model<String> getFooter() {

        return footer;
    }
}
