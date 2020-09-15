## 启动docker
```$xslt
 docker-compose up -d
```


## 进入docker容器,登录数据库并创建用户名
```$xslt
➜  mysql-5.7-docker git:(master) ✗ docker exec -it mysql_5.7 bash
bash-4.2# mysql -uroot -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 3
Server version: 5.7.31 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> create user 'admin'@'%' identified by 'admin123';
Query OK, 0 rows affected (0.03 sec)

mysql> grant all privileges on *.* to 'admin'@'%' identified by 'admin123';
Query OK, 0 rows affected, 1 warning (0.00 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.01 sec)

mysql> exit
Bye
bash-4.2# exit
exit

# 进入到用户自己的终端登录数据库
➜  mysql-5.7-docker git:(master) ✗  mysql -h127.0.0.1  -uadmin -P3337 -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 16
Server version: 5.7.31 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.03 sec)

mysql>
```