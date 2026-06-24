package com.uttam.helper;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String string) {
        super(string);
    }

    public ResourceNotFoundException() {
        super("User Not Found");
    }


}
