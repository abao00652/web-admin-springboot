package com.cuii.error;

import lombok.Data;

@Data
public class ServerException extends RuntimeException{

    private String msg;

    public ServerException(String message) {
        super(message);
        this.msg = message;
    }
}
