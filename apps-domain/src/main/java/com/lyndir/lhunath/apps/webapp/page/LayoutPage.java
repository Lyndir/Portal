package com.lyndir.lhunath.apps.webapp.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.StringHeaderContributor;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.template.JavaScriptTemplate;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

import com.google.common.collect.ImmutableList;
import com.lyndir.lhunath.apps.model.AppVersion;
import com.lyndir.lhunath.apps.webapp.AppsSession;
import com.lyndir.lhunath.apps.webapp.JavaScriptProvider;


public class LayoutPage extends WebPage {

    private static final long serialVersionUID = 1L;

    List<ITab>                headTabsList;


    List<ITab> getTabs() {

        if (headTabsList == null)
            headTabsList = buildTabs();

        return headTabsList;
    }

    /**
     * Override me to add your own tabs.
     * 
     * @return A list of {@link ITab}s that will be used for navigation at the top of the page.
     */
    protected List<ITab> buildTabs() {

        ImmutableList.Builder<ITab> tabListBuilder = new ImmutableList.Builder<ITab>();
        tabListBuilder.add( new AbstractTab( new Model<String>( "Demo" ) ) {

            @Override
            public Panel getPanel(String wicketId) {

                return new DemoPanel( wicketId );
            }
        } );
        tabListBuilder.add( new AbstractTab( new Model<String>( "About" ) ) {

            @Override
            public Panel getPanel(String wicketId) {

                return new AboutPanel( wicketId );
            }
        } );
        tabListBuilder.add( new AbstractTab( new Model<String>( "Versions" ) ) {

            @Override
            public Panel getPanel(String wicketId) {

                return new ArchivePanel( wicketId );
            }
        } );
        tabListBuilder.add( new AbstractTab( new Model<String>( "Development" ) ) {

            @Override
            public Panel getPanel(String wicketId) {

                throw new RestartResponseException( DevelopmentPage.class );
            }
        } );

        return tabListBuilder.build();
    }


    int                selectedTabIndex;
    HeaderContributor  selectedTheme;
    WebMarkupContainer headTabsContainer;


    /**
     * @param pageTitle
     *            The contents of the <code>title</code> tag.
     * @param headTabsList
     *            A list of tabs to put in the header.
     */
    public LayoutPage() {

        // Page STYLE.
        add( new ExternalLink( "style", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {

                return AppsSession.get().getStyleURL();
            }
        } ) {

            @Override
            public boolean isVisible() {

                return AppsSession.get().getStyleURL() != null;
            }
        } );

        // Page TITLE.
        Label pageTitle = new Label( "pageTitle", getPageTitle() );

        // Page TABS.
        headTabsContainer = new WebMarkupContainer( "headTabsContainer" );
        headTabsContainer.add( new ListView<ITab>( "headTabs", getTabs() ) {

            @Override
            protected void populateItem(ListItem<ITab> item) {

                final ITab headTab = item.getModelObject();

                Link<String> link = new AjaxFallbackLink<String>( "link" ) {

                    @Override
                    public void onClick(AjaxRequestTarget target) {

                        // TAB click.
                        selectedTabIndex = getTabs().indexOf( headTab );
                        Panel contentPanel = headTab.getPanel( "contentPanel" );
                        contentPanel.setOutputMarkupId( true );

                        // OnShowJavaScript
                        String panelJs = null;
                        if (contentPanel instanceof JavaScriptProvider)
                            panelJs = ((JavaScriptProvider) contentPanel).getProvidedJavaScript();
                        Map<String, Object> trackPanelVariables = new HashMap<String, Object>();
                        trackPanelVariables.put( "googleAnalyticsID", //
                                AppVersion.getLatest().getApp().getGoogleAnalyticsID() );
                        trackPanelVariables.put( "pageView", //
                                contentPanel.getClass().getSimpleName() );
                        final String trackPanelJs = new JavaScriptTemplate( new PackagedTextTemplate( LayoutPage.class,
                                "trackPage.js" ) ).asString( trackPanelVariables );

                        LayoutPage.this.addOrReplace( contentPanel );

                        if (target != null) {
                            // AJAX Support
                            target.addComponent( contentPanel );
                            target.addComponent( headTabsContainer );

                            if (panelJs != null)
                                target.appendJavascript( panelJs );
                            target.appendJavascript( "Shadowbox.setup();" );
                            target.appendJavascript( trackPanelJs );

                        } else {
                            // No AJAX Support
                            final String jsTemplate = panelJs;

                            add( new StringHeaderContributor( new JavaScriptTemplate( new TextTemplate() {

                                @Override
                                public TextTemplate interpolate(Map<String, Object> variables) {

                                    return this;
                                }

                                @Override
                                public String getString() {

                                    return jsTemplate + "\n\n" + trackPanelJs;
                                }
                            } ).asString() ) );
                        }
                    }
                };

                item.add( link );
                link.add( new Label( "title", headTab.getTitle() ) );
            }

            @Override
            protected ListItem<ITab> newItem(final int index) {

                return new ListItem<ITab>( index, getListItemModel( getModel(), index ) ) {

                    @Override
                    protected void onComponentTag(ComponentTag tag) {

                        super.onComponentTag( tag );
                        if (index == selectedTabIndex)
                            tag.put( "class", "active" );
                    }
                };
            }
        } );
        headTabsContainer.setOutputMarkupId( true );

        // Page content.
        add( new ExternalLink( "appstore", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {

                return AppVersion.getLatest().getAppStoreLink();
            }
        } ) );
        Panel contentPanel = getDefaultPanel( "contentPanel" );
        contentPanel.setOutputMarkupId( true );

        // Page TRACKING.
        Map<String, Object> trackPageVariables = new HashMap<String, Object>();
        trackPageVariables.put( "googleAnalyticsID", //
                AppVersion.getLatest().getApp().getGoogleAnalyticsID() );
        trackPageVariables.put( "pageView", //
                contentPanel.getClass().getSimpleName() );
        add( new StringHeaderContributor( new JavaScriptTemplate( new PackagedTextTemplate( LayoutPage.class,
                "trackPage.js" ) ).asString( trackPageVariables ) ) );

        // OnShowJavaScript
        String js = null;
        if (contentPanel instanceof JavaScriptProvider)
            js = ((JavaScriptProvider) contentPanel).getProvidedJavaScript();
        final String jsTemplate = js;
        add( new StringHeaderContributor( new JavaScriptTemplate( new TextTemplate() {

            @Override
            public TextTemplate interpolate(Map<String, Object> variables) {

                return this;
            }

            @Override
            public String getString() {

                return jsTemplate;
            }
        } ).asString() ) );

        add( pageTitle, headTabsContainer, contentPanel );
    }

    /**
     * @param wicketId
     *            The wicket ID that the panel should have.
     * @return The {@link Panel} to show as the content before any tabs have been selected.
     */
    protected Panel getDefaultPanel(String wicketId) {

        return getTabs().get( 0 ).getPanel( wicketId );
    }

    /**
     * @return The title string that describes this page.
     */
    protected String getPageTitle() {

        return AppVersion.getLatest().getApp().getFriendlyName();
    }
}
