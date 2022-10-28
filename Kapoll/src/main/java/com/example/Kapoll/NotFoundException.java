package com.example.Kapoll;

public class NotFoundException extends RuntimeException{

        NotFoundException(Long id, String object) {
            super("Could not find "+ object+ " with id: " + id);
        }

    }
