package demo.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReportingApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReportingApplication.class, args);

		// clear the report file
		Writer.clearFile("report.txt");
		String qSeparator= "=".repeat(60)+"\n";

		// creates an obj. for each SQL query
		String q1Desc= "Count of cars produced by each manufacturer: \n";
		String q1Sql = "SELECT DIVISION AS `MANUFACTURER`, COUNT(ID) AS `# CARS` FROM CAR GROUP BY(DIVISION);";
		String [] q1Columns = {"MANUFACTURER", "# CARS"};

		Query q1 = new Query(q1Desc, q1Sql, q1Columns);

		String q2Desc= "Number of manufacturers per country: \n";
		String q2Sql = "SELECT COUNTRY, COUNT(ID) AS `# MANUFACTURERS` FROM MANUFACTURER GROUP BY (COUNTRY);";
		String [] q2Columns = {"COUNTRY", "# MANUFACTURERS"};

		Query q2 = new Query(q2Desc, q2Sql, q2Columns);

		String q3Desc= "What country produced the most fuel efficient Car: \n";
		String q3Sql = "SELECT MANUFACTURER.COUNTRY, CAR.CARLINE AS `CAR`, CAR.`COMB FE` FROM CAR "
				+ "INNER JOIN MANUFACTURER ON CAR.DIVISION = MANUFACTURER.NAME "
				+ "WHERE  `COMB FE`=(SELECT MAX(`COMB FE`) FROM CAR);";
		String [] q3Columns = {"COUNTRY", "CAR", "COMB FE"};

		Query q3 = new Query(q3Desc, q3Sql, q3Columns);

		String q4Desc= "Count of cars produced by each Manufacturer by Cylinder: \n";
		String q4Sql = "SELECT DIVISION AS MANUFACTURER, `# CYL` AS `# CYLINDERS`, COUNT(CARLINE) AS `# CARS` FROM CAR "
				+ "GROUP BY (DIVISION, `# CYL`);";
		String [] q4Columns = {"MANUFACTURER", "# CYLINDERS", "# CARS"};

		Query q4 = new Query(q4Desc, q4Sql, q4Columns);

		String q5Desc= "Rank the count of cars by MPG in ranges (1-15, 15-20, 21-25, 26+) : \n";
		String q5Sql = "SELECT T.`MPG RANGE`, COUNT(*) AS `# CARS`"
				+ "FROM (SELECT CASE"
				+ " WHEN `COMB FE` between 1 and 15 then  '1-15'"
				+ " WHEN `COMB FE` between 15 and 20 then '15-20'"
				+ " WHEN `COMB FE` between 21 and 25 then '21-25'"
				+ " ELSE '26+'"
				+ " END AS `MPG RANGE`"
				+ " FROM CAR) T"
				+ " GROUP BY T.`MPG RANGE`"
				+ " ORDER BY `# CARS` DESC;";
		String [] q5Columns = {"MPG RANGE", "# CARS"};

		Query q5 = new Query(q5Desc, q5Sql, q5Columns);

		String q6Desc= "Rank cars by best performing Combined MPG: \n";
		String q6Sql = "SELECT DIVISION AS BRAND, CARLINE AS CAR, `COMB FE` AS `COMB FE (MPG)` FROM CAR "
				+ "ORDER BY `COMB FE` DESC;";
		String [] q6Columns = {"BRAND", "CAR", "COMB FE (MPG)"};

		Query q6 = new Query(q6Desc, q6Sql, q6Columns);


		Query [] arrQueries = {q1, q2, q3, q4, q5, q6};

		//execute all queries
		for(int i =0; i<arrQueries.length; i++){
			Writer.writeToFile(arrQueries[i].getDescription());
			Writer.writeToFile(qSeparator);
			JdbcQuerry.executeQuerry(arrQueries[i].getSql(), arrQueries[i].getColumns());
			Writer.writeToFile(qSeparator);
		}


	}


}

