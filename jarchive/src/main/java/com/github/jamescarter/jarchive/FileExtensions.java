package com.github.jamescarter.jarchive;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface FileExtensions {
	String[] values();
}

