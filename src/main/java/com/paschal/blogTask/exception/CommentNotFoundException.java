package com.paschal.blogTask.exception;

    public class CommentNotFoundException extends RuntimeException {
        public CommentNotFoundException(String message){
            super(message);
        }
    }

