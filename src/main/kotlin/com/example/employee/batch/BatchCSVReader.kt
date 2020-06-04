package com.example.employee.batch

import com.example.employee.model.Employee
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.core.io.ClassPathResource

class BatchCSVReader :FlatFileItemReader<Employee>() {

    companion object {
        val CSV_NAMES = arrayOf("id", "firstName", "lastName")
        const val CSV_DELIMITER = ";"
        const val CSV_FILE = "employee.csv"
    }

    init {
        //Set input file location
        super.setResource(ClassPathResource(CSV_FILE))

        //Set number of lines to skips. Use it if file has header rows.
        super.setLinesToSkip(1)

        //Configure how each line will be parsed and mapped to different values
        super.setLineMapper(object : DefaultLineMapper<Employee>() {
            init {
                setLineTokenizer(object : DelimitedLineTokenizer() {
                    init {
                        // Set CSV's columns
                        setNames(*CSV_NAMES)
                        // Set CSV delimiter columns
                        setDelimiter(CSV_DELIMITER)
                    }
                })
                //Set values in Employee class
                setFieldSetMapper(object : BeanWrapperFieldSetMapper<Employee>() {
                    init {
                        setTargetType(Employee::class.java)
                    }
                })
            }
        })
    }

}