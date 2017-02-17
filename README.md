# Tax-Returns-filed-Using-Spark
Compute total number of tax returns filed in each state in USA for 2012 year

## How to clone the Repository
	git clone git@github.com:rajesh612/Tax-Returns-filed-Using-Spark.git

## Tools and Softwares Required
#### Spark-2.0.2
#### JDK 1.8
#### Eclipse IDE

## Required Jar files
#### scala-library-2.11.8.jar
#### spark-core_2.11-2.0.1.jar
#### spark-sql_2.11-2.0.1.jar
#### hadoop-core-1.2.1.jar

## Steps to run usData jar using spark
#### change directory to spark_home , place the usData jar file in spark_home folder and run the following commands
	spark-submit --class USTaxData usData.jar (input path)G:/fakePath/12taxData.csv 
	(output path) C:/Desktop/USTaxOut 


