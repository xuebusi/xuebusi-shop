# xuebusi-shop
学步思商城项目-SSM技术
# ssm-shop.
<h2>我们用了3台linux虚拟机来搭建该商城项目，主机名分别为：hadoop01、hadoop02和hadoop03。</h2>
<h3>该商城项目包含6个web工程，也就是6个war包</h3>：
<ul>
	<li>xuebusi-manager-web:商城后台;</li>
	<li>xuebusi-portal:商城前台;</li>
	<li>xuebusi-order:订单系统;</li>
	<li>xuebusi-rest:rest服务器;</li>
	<li>xuebusi-search:搜索服务器;</li>
	<li>xuebusi-sso:登录服务器;</li>
</ul>

<h3>该商城项目包含4个jar包：</h3>

<ul>
	<li>xuebusi-common: 通用工具类;</li>
	<li>xuebusi-manager-mapper: DAO类;</li>
	<li>xuebusi-manager-pojo: 实体类;</li>
	<li>xuebusi-manager-service: 业务类;</li>
</ul>


<h3>该商城项目包含2个pom工程：</h3>
（1）xuebusi-parent:这是一个pom工程，是所有工程的父工程;
（2）xuebusi-manager:这也是一个pom工程，是以下4个工程的父工程：

	xuebusi-manager-mapper;
	xuebusi-manager-pojo;
	xuebusi-manager-service;
	xuebusi-manager-web;

<h3>首先在3台服务器上分别部署一个zookeeper;</h3>

	zookeeper集群的启动命令：/root/apps/zookeeper/bin/zkServer.sh start
	zookeeper集群的停止命令：/root/apps/zookeeper/bin/zkServer.sh stop
	查看zookeeper服务器运行状态的命令：/root/apps/zookeeper/bin/zkServer.sh status


<h2>hadoop01服务器部署情况：</h2>
<h3>（1）搭建Nginx服务器（仅用于访问FTP图片服务器）;</h3>

	Nginx服务器部署目录：/usr/local/nginx;
	启动命令：/usr/local/nginx/sbin/nginx
	停止命令：/usr/local/nginx/sbin/nginx -s stop
	重启命令：/usr/local/nginx/sbin/nginx -s reload
	查看Nginx服务器是否启动：使用"netstat -ntlp"命令查看端口占用情况;
		
<h3>（2）搭建FTP图片服务器（结合Nginx使用），图片存放目录：/home/ftpuser/www/images/;</h3>

	在Nginx的"nginx.conf"文件中添加如下内容，将来自"http://localhost:8088/images/xxx.jpg"这种请求
	中的"http://localhost:8088/"这一部分url映射到"/home/ftpuser/www"目录：

	server {
		listen       8088;
		server_name  localhost;
		location / {
			root   /home/ftpuser/www;
			index  index.html index.htm;
		}
	}
		
图片访问方式：启动Nginx服务器，在浏览器中直接访问"http://localhost:8088/images/xxx.jpg"类似的地址就可以看到图片了;
		
<h3>（3）搭建搜索服务器</h3>

	Solr集群:4个Solr实例，4个tomcat实例，端口号分别为8080、8081、8082和8083;
	Solr集群部署目录：/root/apps/xuebusi/solrcloud/;

	-rwxr-xr-x. 1 root root  212 Dec 21 03:30 shutdown.sh
	drwxr-xr-x. 4 root root 4096 Dec 21 03:41 solrhome01
	drwxr-xr-x. 4 root root 4096 Dec 21 03:41 solrhome02
	drwxr-xr-x. 4 root root 4096 Dec 21 03:41 solrhome03
	drwxr-xr-x. 4 root root 4096 Dec 21 03:41 solrhome04
	-rwxr-xr-x. 1 root root  208 Dec 21 03:04 startAll.sh
	drwxr-xr-x. 9 root root 4096 Dec 21 02:33 tomcat01
	drwxr-xr-x. 9 root root 4096 Dec 21 02:33 tomcat02
	drwxr-xr-x. 9 root root 4096 Dec 21 02:33 tomcat03
	drwxr-xr-x. 9 root root 4096 Dec 21 02:33 tomcat04

	启动solr集群的命令：
	/root/apps/xuebusi/solrcloud/tomcat01/bin/startup.sh
	/root/apps/xuebusi/solrcloud/tomcat02/bin/startup.sh
	/root/apps/xuebusi/solrcloud/tomcat03/bin/startup.sh
	/root/apps/xuebusi/solrcloud/tomcat04/bin/startup.sh

	停止solr集群的命令：
	/root/apps/xuebusi/solrcloud/tomcat01/bin/shutdown.sh
	/root/apps/xuebusi/solrcloud/tomcat02/bin/shutdown.sh
	/root/apps/xuebusi/solrcloud/tomcat03/bin/shutdown.sh
	/root/apps/xuebusi/solrcloud/tomcat04/bin/shutdown.sh

	Solr服务器web访问地址：
	http://hadoop01:8080/solr
	http://hadoop01:8081/solr
	http://hadoop01:8082/solr
	http://hadoop01:8083/solr

	将数据库中的所有商品信息导入到solr索引库的URL：http://hadoop02:8081/search/manager/importall
		
<h3>（4）前台系统服务器（一个tomcat实例，端口号：8808），所在位置：/root/apps/xuebusi/tomcat-portal-8808;</h3>

	前台服务器访问地址：http://hadoop01:8808/
	前台服务器的启动命令：/root/apps/xuebusi/tomcat-portal-8808/bin/startup.sh
	前台服务器的停止命令：/root/apps/xuebusi/tomcat-portal-8808/bin/shutdown.sh 
	
	
<h2>hadoop02服务器部署情况：</h2>
<h3>（1）搭建订单服务器、rest服务器、搜索服务器和单点登录服务器这4台服务器;</h3>

	这4台服务器也就是4个tomcat服务器：
	服务器部署目录：
	/root/apps/xuebusi/tomcat-service;
	[root@hadoop02 tomcat-service]# ll
	drwxr-xr-x. 9 root root   4096 Dec 27 03:26 tomcat-order-8083
	drwxr-xr-x. 9 root root   4096 Dec 27 03:22 tomcat-rest-8080
	drwxr-xr-x. 9 root root   4096 Dec 27 03:24 tomcat-search-8081
	drwxr-xr-x. 9 root root   4096 Dec 27 03:26 tomcat-sso-8082

	其中tomcat-order-8083是订单服务器;
	其中tomcat-rest-8080是rest服务器;
	其中tomcat-search-8081是搜索服务器;
	其中tomcat-sso-8082是单点登录服务器;

	启动这4台服务器是命令：
	tomcat-service/tomcat-order-8083/bin/startup.sh
	tomcat-service/tomcat-rest-8080/bin/startup.sh
	tomcat-service/tomcat-search-8081/bin/startup.sh
	tomcat-service/tomcat-sso-8082/bin/startup.sh

	停止这4台服务器的命令：
	tomcat-service/tomcat-order-8083/bin/shutdown.sh
	tomcat-service/tomcat-rest-8080/bin/shutdown.sh
	tomcat-service/tomcat-search-8081/bin/shutdown.sh
	tomcat-service/tomcat-sso-8082/bin/shutdown.sh

	测试rest服务器方法，在浏览器中访问：http://hadoop02:8080/rest/content/list/89
	在浏览器如果能看到json格式的数据，说明rest服务器运行正常;
	也可以在linux使用如下curl命令来测试：
	[root@hadoop01 ~]# curl http://hadoop02:8080/rest/content/list/89
	如返回如下json格式数据，说明服务器运行正常：
	{
		"status": 200,
		"msg": "OK",
		"data": [
			{
				"id": 39,
				"categoryId": 89,
				"title": "测试广告焦点图标题",
				"subTitle": "测试广告焦点图子标题",
				"titleDesc": "测试广告焦点图内容描述",
				"url": "",
				"pic": "http://192.168.71.11:8088/images/2016/12/21/1482315880465748.jpg",
				"pic2": "",
				"created": 1482373817000,
				"updated": 1482373817000,
				"content": null
			},
			{
				"id": 44,
				"categoryId": 89,
				"title": "鬼地方个梵蒂冈",
				"subTitle": "个梵蒂冈",
				"titleDesc": "滚动",
				"url": "",
				"pic": "http://192.168.71.11:8088/images/2016/12/21/1482321879828360.jpg",
				"pic2": "",
				"created": 1482379491000,
				"updated": 1482379491000,
				"content": null
			},
			{
				"id": 45,
				"categoryId": 89,
				"title": "的个梵蒂冈",
				"subTitle": "滚动个",
				"titleDesc": "放到滚动",
				"url": "",
				"pic": "http://192.168.71.11:8088/images/2016/12/21/1482321986850136.jpg",
				"pic2": "",
				"created": 1482379597000,
				"updated": 1482379597000,
				"content": null
			},
			{
				"id": 46,
				"categoryId": 89,
				"title": "第三方",
				"subTitle": "发给是",
				"titleDesc": "发给放到",
				"url": "",
				"pic": "http://192.168.71.11:8088/images/2016/12/21/1482322027636359.jpg",
				"pic2": "",
				"created": 1482379637000,
				"updated": 1482379637000,
				"content": null
			},
			{
				"id": 47,
				"categoryId": 89,
				"title": "他跟他",
				"subTitle": "特",
				"titleDesc": "try",
				"url": "",
				"pic": "http://192.168.71.11:8088/images/2016/12/21/1482322084631426.jpg",
				"pic2": "",
				"created": 1482379694000,
				"updated": 1482379694000,
				"content": null
			},
			{
				"id": 48,
				"categoryId": 89,
				"title": "水电费",
				"subTitle": "第三方",
				"titleDesc": "fds个",
				"url": "",
				"pic": "http://192.168.71.11:8088/images/2016/12/21/1482322132572655.jpg",
				"pic2": "",
				"created": 1482379742000,
				"updated": 1482379742000,
				"content": null
			},
			{
				"id": 49,
				"categoryId": 89,
				"title": "gffg",
				"subTitle": "fff",
				"titleDesc": "fff",
				"url": "",
				"pic": "http://192.168.71.11:8088/images/2016/12/27/1482859962251237.jpg",
				"pic2": "",
				"created": 1482859967000,
				"updated": 1482859967000,
				"content": null
			}
		]
	}		
	测试搜索服务器的方法，在浏览器中访问：http://hadoop02:8081/search/query?q=手机
	如果在浏览器中能看到json格式的数据，说明搜索服务器运行正常;

	测试登录服务器的方法，在浏览器上访问登录页面：http://hadoop02:8082/page/login
	或者访问注册页面：http://hadoop02:8082/page/register

<h2>hadoop03服务器部署情况：</h2>
<h3>（1）搭建redis集群，共6个redis实例，端口分别是7001~7006;</h3>

	redis集群部署目录：/root/apps/xuebusi/redis-cluster;
	redis启动命令：
	cd /root/apps/xuebusi/redis-cluster/redis01
	./redis-server redis.conf
	cd ../
	cd /root/apps/xuebusi/redis-cluster/redis02
	./redis-server redis.conf
	cd ../
	cd /root/apps/xuebusi/redis-cluster/redis03
	./redis-server redis.conf
	cd ../
	cd /root/apps/xuebusi/redis-cluster/redis04
	./redis-server redis.conf
	cd ../
	cd /root/apps/xuebusi/redis-cluster/redis05
	./redis-server redis.conf
	cd ../
	cd /root/apps/xuebusi/redis-cluster/redis06
	./redis-server redis.conf

	redis集群停止命令：
	/root/apps/xuebusi/redis-cluster/redis01/redis-cli -p 7001 shutdown
	/root/apps/xuebusi/redis-cluster/redis01/redis-cli -p 7002 shutdown
	/root/apps/xuebusi/redis-cluster/redis01/redis-cli -p 7003 shutdown
	/root/apps/xuebusi/redis-cluster/redis01/redis-cli -p 7004 shutdown
	/root/apps/xuebusi/redis-cluster/redis01/redis-cli -p 7005 shutdown
	/root/apps/xuebusi/redis-cluster/redis01/redis-cli -p 7006 shutdown

	查看redis集群启动状态的命令："ps aux|grep redis";
	[root@hadoop03 redis-cluster]# ps aux|grep redis
	root       2771  0.0  0.3 137440  7384 ?        Ssl  10:56   0:00 ./redis-server *:7001 [cluster]
	root       2775  2.0  0.3 137440  7376 ?        Ssl  10:56   0:00 ./redis-server *:7002 [cluster]
	root       2779  2.3  0.3 137440  7360 ?        Ssl  10:56   0:00 ./redis-server *:7003 [cluster]
	root       2783  0.0  0.3 137440  7368 ?        Ssl  10:56   0:00 ./redis-server *:7004 [cluster]
	root       2787  0.0  0.3 137440  7356 ?        Ssl  10:56   0:00 ./redis-server *:7005 [cluster]
	root       2791  2.3  0.3 137440  7344 ?        Ssl  10:56   0:00 ./redis-server *:7006 [cluster]
	root       2798  0.0  0.0 103308   848 pts/0    S+   10:56   0:00 grep redis
			
<h3>（2）搭建后台服务器，tomcat部署目录为：/root/apps/xuebusi/tomcat-manager-8080;</h3>

	启动后台服务器的命令：/root/apps/xuebusi/tomcat-manager-8080/bin/startup.sh 
	停止后台服务器的命令：/root/apps/xuebusi/tomcat-manager-8080/bin/shutdown.sh 
	后台服务器访问地址：http://hadoop03:8080/

<h2>数据库服务器是MySQL，部署在hadoop03这台服务器上;</h2>

	查看服务器上是否安装了MySQL的命令：rpm -qa | grep mysql
	[root@hadoop03 ~]# rpm -qa | grep mysql
	mysql-5.1.73-5.el6_6.x86_64
	mysql-libs-5.1.73-5.el6_6.x86_64
	mysql-server-5.1.73-5.el6_6.x86_64
	mysql-community-release-el6-5.noarch
	
	启动MySQL数据库服务器的命令：service mysqld start
	停止MySQL数据库服务器的命令：service mysqld stop
	重启MySQL数据库服务器的命令：service mysqld restart
	查看MySQL是否启动，使用" netstat -ntlp"命令查看端口占用情况;
	

<h2>项目的部署启动流程：</h2>

<ul>
	<li>启动MySQL数据库服务器（数据库部署在hadoop03服务器）;</li>
	<li>启动zookeeper集群（3台服务器上的zookeeper都要启动）;</li>
	<li>启动Nginx服务器（部署在hadoop01服务器上）;</li>
	<li>启动redis集群（部署在hadoop03服务器上）;</li>
	<li>启动solr集群（部署在hadoop01服务器上）;</li>
	<li>启动订单服务器、rest服务器、搜索服务器和登录服务器（这4台服务器都部署在hadoop02服务器上）;</li>
	<li>启动后台服务器（部署在hadoop03服务器上）;</li>
	<li>启动前台服务器（部署在hadoop01服务器上）;</li>
</ul>

<h1>注意事项：</h1>
<h2>由于后台系统（xuebusi-manager）和MySQL数据库同一台机器上，所以后台系统连接数据库的URL是"localhost:3306"，而其他的系统都是在hadoop01和hadoop02机器上，所以它们连接数据库的URL都是"hadoop03:3306"</h2>
