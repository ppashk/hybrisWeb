package com.hybris.web.page;

public class Page {
    private String url;
    private boolean redirect;
    private boolean ajax;
    private String ajaxContent;

    public Page(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public Page(boolean ajax, String ajaxContent) {
        this.ajax = ajax;
        this.ajaxContent = ajaxContent;
    }

    public Page(String url) {
        this.url = url;
    }

    private Page() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public boolean isAjax() {
        return ajax;
    }

    public void setAjax(boolean ajax) {
        this.ajax = ajax;
    }

    public String getAjaxContent() {
        return ajaxContent;
    }

    public void setAjaxContent(String ajaxContent) {
        this.ajaxContent = ajaxContent;
    }
}
