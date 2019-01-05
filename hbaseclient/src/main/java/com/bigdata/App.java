package com.bigdata;

import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.Configuration;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        BasicConfigurator.configure();
        HBaseService hBaseService = new HBaseService();
        String tableName = "user";
        String [] familys = {"self", "others"};
        hBaseService.creatTable(tableName, familys);
//        System.out.println("hello,world");

    }

}
