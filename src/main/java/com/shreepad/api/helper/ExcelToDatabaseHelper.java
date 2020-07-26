package com.shreepad.api.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDatabaseHelper {

	public static void main(String args[]) {
		String jdbcURL = "jdbc:sqlite:shreepad_japmala_db";
		String excelFilePath = "C:\\Users\\pragati\\Desktop\\Shreepad Project data\\Shreejap_data_3.xlsx";

		int batchSize = 20;
		Connection connection = null;
		try {
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			connection = DriverManager.getConnection(jdbcURL);
			connection.setAutoCommit(false);

			String sqlCreate = "CREATE TABLE IF NOT EXISTS participant " + 
			"  (name           VARCHAR(10),"
			+ "  mobile_number  VARCHAR(20)," 
			+ "  jap         	 INTEGER,"
			+ "  strotram     	INTEGER,"
			+ "  location       VARCHAR(10),"
			+ "  PRIMARY KEY (name, mobile_number))";

			Statement stmt = connection.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Table created");
			String sql = "REPLACE INTO participant (name, mobile_number, jap,strotram,location) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			int count = 0;
			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						String name = nextCell.getStringCellValue();
						statement.setString(1, name);
						System.out.println("name Inserted");
						break;
					case 1:
						System.out.println("Cell Type: " + nextCell.getCellType());
						String  mobile_number = new BigDecimal( nextCell.getNumericCellValue()).toString() ;
						statement.setString(2, mobile_number);
						System.out.println("mobile_number Inserted" + mobile_number);					
						break;
					case 2:
						int jap = (int) nextCell.getNumericCellValue();
						statement.setInt(3, jap);
						System.out.println("jap Inserted");
						break;
					case 3:
						int strotram = (int) nextCell.getNumericCellValue();
						statement.setInt(4, strotram);
						System.out.println("strotram Inserted");
						break;
					case 4:
						String address = nextCell.getStringCellValue();
						statement.setString(5, address);
						System.out.println("address Inserted");
						break;
					}
				}
				statement.addBatch();
				if (count % batchSize == 0) {
					statement.executeBatch();
				}
			}
			workbook.close();

			// execute the remaining queries
			statement.executeBatch();
			/*
			 * 
			 * String sqlSelect =
			 * "SELECT name, mobile_number, jap, strotram,location FROM participant";
			 * ResultSet rs = stmt.executeQuery(sqlSelect); //STEP 5: Extract data from
			 * result set while(rs.next()){ //Retrieve by column name int name =
			 * rs.getInt("name"); String age =Integer.toString(rs.getInt("mobile_number"));
			 * int jap = rs.getInt("jap"); int strotram = rs.getInt("strotram"); String
			 * location = rs.getString("location");
			 * 
			 * //Display values System.out.print("name: " + name);
			 * System.out.print(", Age: " + age); System.out.print(", jap: " + jap);
			 * System.out.print(", strotram: " + strotram);
			 * System.out.println(", location: " + location); }
			 */

			connection.commit();
			connection.close();
		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}

	}
}
