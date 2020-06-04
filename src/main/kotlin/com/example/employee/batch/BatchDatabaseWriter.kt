package com.example.employee.batch

import com.example.employee.model.Profile
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import javax.sql.DataSource

class BatchDatabaseWriter(dataSource: DataSource) : JdbcBatchItemWriter<Profile>() {

    companion object {
        const val SQL_WRITER = "INSERT INTO profile (id, emp_code, emp_name, profile_name) VALUES (:id, :empCode, :empName, :profileName)"
    }

    init {
        super.itemSqlParameterSourceProvider = BeanPropertyItemSqlParameterSourceProvider()
        super.sql = SQL_WRITER
        super.setDataSource(dataSource)
    }

}