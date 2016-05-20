# Apache Spark RDD Persist
Benchmark the RDD with and without using persist().

In this limited experiment, we make use of command "persist()" to show that by fixing the RDD in memory at the right place, the iterative processing in Spark application can be sped up greatly. Even more, the resource usage in cluster in terms of network/harddisk is reduced. However, beware of memory-abuse issue.

Please see my article:

* https://thachtranerc.wordpress.com/2016/05/20/apache-sparks-rdd-persistence/

## Software Environment:

* Intellij IDEA 2016.1.1
* Scala v2.10.5
* Apache Spark v1.6.1
* Java JDK/JRE v1.8.0_77 64bit

## Reference:
1. [Spark Programming Guide](http://spark.apache.org/docs/latest/programming-guide.html)

2. [RDD.scala](https://github.com/apache/spark/blob/master/core/src/main/scala/org/apache/spark/rdd/RDD.scala)
