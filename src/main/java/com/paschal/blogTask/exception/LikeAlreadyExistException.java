package com.paschal.blogTask.exception;


    public class LikeAlreadyExistException extends RuntimeException{
        public LikeAlreadyExistException(String message){
            super(message);
        }
    }
