package com.lyndir.lhunath.portal.webapp.page;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


/**
 * <h2>{@link ContactPage}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public class ContactPage extends LyndirPage {

    @Override
    public IModel<String> getPageTitle() {

        return new Model<String>( "Contact" );
    }
}
