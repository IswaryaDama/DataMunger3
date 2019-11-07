package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	
	String fileName;
	BufferedReader csvRead = null;
	Header headers = null;
	String dataType;
	String[] headerList;

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
            this.fileName = fileName;
            csvRead = new BufferedReader(new FileReader(fileName));
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {
		
		// read the first line
		csvRead.mark(1);
		String firstLine = csvRead.readLine();
		System.out.println("headrers list " + Arrays.toString(firstLine.split(",")));
		headerList = firstLine.split(",");
		csvRead.reset();
		System.out.println("header size" + headerList.length);
		 headers = new Header(headerList);

		// populate the header object with the String array containing the header names
		return headers;
	
		
		
	}
	

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		Object obj;
		int i = 0;
		String type = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String headerRow = br.readLine();
		String headerArgs[] = headerRow.split(",");
		String secondRow = br.readLine();
		secondRow += " ,";
		String[] dataElements = secondRow.split(",");
		String[] dataTypes = new String[headerArgs.length];
		if (dataElements != null) {
			for (i = 0; i < dataElements.length; i++) {

				try {
					obj = Integer.parseInt(dataElements[i]);
					if (obj instanceof Integer)

						type = obj.getClass().getName();
					dataTypes[i] = type;
				} catch (NumberFormatException e) {

					try {
						obj = Double.parseDouble(dataElements[i]);
						if (obj instanceof Double) {
							type = obj.getClass().getName();
							dataTypes[i] = type;
						}
					} catch (Exception e2) {
						obj = dataElements[i];
						if (obj instanceof String)
							type = obj.getClass().getName();
						dataTypes[i] = type;
					}
				}
			}

		}

		br.close();   
		
		DataTypeDefinitions types = new DataTypeDefinitions(dataTypes);
		return types;
	}	
}
