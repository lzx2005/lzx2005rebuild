package com.lzx2005.exception;

/**
 * Created by Administrator on 2016/6/20.
 */
public class BlogException extends RuntimeException {
    public BlogException() {
        super();
    }

    public BlogException(String message) {
        super(message);
    }
}
