package com.arjun.springpersistence.springdatajpa.helper;

import java.util.Date;

public class DateHelper {
	
    public static Date tomorrow() {
        return new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
    }

}
