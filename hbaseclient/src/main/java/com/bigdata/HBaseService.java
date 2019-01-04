package com.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
//import org.apache.hadoop.hbase.client.
import java.io.IOException;


public class HBaseService {

    static Configuration conf = null;
    static Connection conn = null;
    static {
        try{
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conf.set("hbase.zookeeper.quorum", "172.32.0.191,172.32.0.195,172.32.0.190");
            conf.set("zookeeper.znode.parent","/hbase-unsecure");
            conn = ConnectionFactory.createConnection(conf);

        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public static void creatTable(String tableName, String[] familyNames) {
        try {
            Admin admin = conn.getAdmin();
            if (admin.tableExists(TableName.valueOf(tableName))) {
                System.out.println("table Exists!");
                System.exit(0);
            } else {
                TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
                for (String familyName : familyNames) {
                    tableDescriptor.setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familyName)).build());
                }
                admin.createTable(tableDescriptor.build());
                System.out.println("create table Success!");
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

}
