/**
  * Created by iRobot on 20/May/2016.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import java.lang.IllegalArgumentException

/* Logging Package */
import org.apache.log4j.Logger
import org.apache.log4j.Level

object MyApp {
    def main(args : Array[String]) {
        try {
            println("*** PROGRAM STARTED ***")

            Logger.getLogger("org").setLevel(Level.OFF)
            Logger.getLogger("akka").setLevel(Level.OFF)

            val cfg = new SparkConf().setMaster("local[4]").setAppName("ApacheSparkRDDPersist")
            val sc = new SparkContext(cfg)

            // mark the starttime
            val startTime = System.currentTimeMillis()

            /*
            * The point here is to magnify the difference between persist() and not persist() by
            * choosing a file having a greater size AND/OR a list having a longer length.
            * */
            val interestedTerms = List("food", "money", "movement", "blog", "future", "life", "car",
                                        "movie", "eat", "secret")

            val txtRdd = sc.textFile("file:///D:\\Downloads\\Coursera-SwiftKey\\final\\en_US\\en_US.blogs.txt", 2)

            val txtRddLowerCase = txtRdd.map(x => x.toLowerCase)

            // Should persist or not persist?
            //txtRddLowerCase.persist()

            for( a <- 1 to 10) {
                for (term <- interestedTerms) {
                    println(f"Processing [$term%s]:")
                    txtRddLowerCase.filter(x => x.contains(term)).flatMap(x => x.split("\\s+"))
                            .map(x => (x, 1)).reduceByKey(_ + _).collect()
                }
            }

            println("Time Taken: " + (System.currentTimeMillis() - startTime) + " ms")
            println("*** PROGRAM SUCCEEDED ***")
        }
        catch   // this is the outer-most layer; so reasonably, we have "catch-all" exception.
            {
                case e: Exception => println("*** PROGRAM EXCEPTION: " + e.getMessage() + " ***");
            }
        finally
        {
            println("*** PROGRAM ENDED ***")
        }
    }
}
