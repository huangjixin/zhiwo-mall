--机器信息
10.186.120.44
mysql/%TGBnji9

--连接mysql：
mysql -uroot -p

--输入密码：Aa!Dyl0AXo10
CSpa2s_acmp


--创建用户acmp,密码acmp：
GRANT ALL PRIVILEGES ON *.* TO 'acmp'@'%' IDENTIFIED BY 'acmp' WITH GRANT OPTION;

--执行：
flush privileges;

--查询用户：
SELECT DISTINCT CONCAT('User: ''',user,'''@''',host,''';') AS query FROM mysql.user;
 
--查看数据库端口号： 
cat /etc/my.cnf | grep port


数据库连接信息
10.186.120.44:16063:ACMPDB
acmp/acmp
