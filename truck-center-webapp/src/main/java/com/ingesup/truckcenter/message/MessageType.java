package com.ingesup.truckcenter.message;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
public enum MessageType {

    INFO, SUCCESS, WARNING, DANGER;

    private final String cssClass;

    private MessageType(){
        cssClass = name().toLowerCase();
    }

    public String getCssClass(){
        return cssClass;
    }
}
