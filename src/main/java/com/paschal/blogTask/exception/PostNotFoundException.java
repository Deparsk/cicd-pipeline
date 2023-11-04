package com.paschal.blogTask.exception;


    public class PostNotFoundException extends RuntimeException {
        public PostNotFoundException(String message){
            super(message);
        }
    }
