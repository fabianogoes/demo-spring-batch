# Demo Spring Batch
It's an example to Spring Batch Application   
!warning! Look to the branches

### Reference Documentation

* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/reference/htmlsingle/#howto-batch-applications)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Configuring and Running a Job](https://docs.spring.io/spring-batch/docs/current/reference/html/job.html)
* [Running Jobs from within a Web Container](https://docs.spring.io/spring-batch/docs/current/reference/html/index-single.html#runningJobsFromWebContainer)
* [Spring Batch + H2 Database Example](https://www.concretepage.com/spring-5/spring-batch-h2-database)
* [Spring Batch + Spring Boot Java Config Example](https://howtodoinjava.com/spring-batch/java-config-multiple-steps/)
* [Spring Batch Framework - Auto create Batch Table](https://stackoverflow.com/questions/33249942/spring-batch-framework-auto-create-batch-table)

### Run Postgres by Docker

```
sudo docker run -p 5432:5432 -d \
    --name springbatch_postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_DB=test \
    -v pgdata:/var/lib/postgresql/data \
    postgres
```


### Important links

* Show H2 Console at Browser: http://localhost:8080/h2-console
* Execute Job: http://localhost:8080/job

**Configuration H2 Console**

Saved Settings: `Generic H2 (Embedded)`   
Setting Name: `Generic H2 (Embedded)`   

Driver Class: `org.h2.Driver`   
JDBC URL: `jdbc:h2:mem:testdb`   
User Name: `sa`   
Password: `<Empty>`    



