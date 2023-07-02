package com.y2h.tinybox.admin.event.service;

public class NoContentException extends RuntimeException {
    public NoContentException() {}
    public NoContentException(String s) {
        super(s);
    }
}