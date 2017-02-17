import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class USTaxData {

	public static void main(String[] args) {
		String inputFile = "G:/CSFall2016/BigData/Assignments/Assgn4/12taxData.csv";
		
		SparkConf config = new SparkConf().setAppName("USTaxData").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(config);
		
    
		JavaRDD<String> usTaxData = jsc.textFile(inputFile, 1);
		String firstRow =usTaxData.first();
		JavaRDD<String> data = usTaxData.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                return !s.equalsIgnoreCase(firstRow);
            }
        });	
		

		JavaPairRDD<String,Double> rdd = data.mapToPair(
		  new PairFunction<String, String,Double>() {
		      public Tuple2 call(String record) throws Exception {

		         String[] cols = record.split(",");
		         return new Tuple2(cols[1]+cols[3],Double.parseDouble(cols[4]));
		      }
		});

		JavaPairRDD<String, Double> enumerate = rdd.reduceByKey(
				  new Function2<Double, Double, Double>() {
				    public Double call(Double rec1, Double rec2) {
				      return rec1 + rec2;
				    }
				  });
		JavaPairRDD<String, Double> organized = enumerate.sortByKey();
		organized.saveAsTextFile("C:/Users/Rajesh/Desktop/USTaxOut");
		jsc.stop();
	}

}

