### Problem Statement

In this Assignment 3, we will

    1. Read the header.  
    2. Identify the Data type of each field 
   
  ******************************************************************************* 
    
    
   ******************************************************************************** 
    Object obj;
		String[] dataTypes = null;
		String[] dataTypeElements =null;
		
		while((dataType = csvRead.readLine())!=null) {
        	dataTypes = dataType.split(",");
        	dataTypeElements = new String[dataTypes.length];
        	 System.out.println(" data in file" + dataType);
        	 for(int i=0;i<dataTypes.length;i++) {
        		 
        			 try {
        				obj = Integer.parseInt(dataTypes[i]);
        				if(obj instanceof Integer)
        					dataTypeElements[i] = obj.getClass().getName();
        			 }catch(NumberFormatException e) {
        				 try{
        					 obj = Double.parseDouble(headerList[i]);
        					 if(obj instanceof Double) {
        						 dataTypeElements[i] = obj.getClass().getName();
        					 }
        					 
        				 }catch(Exception ex) {
        					 
        					 obj = headerList[i];
        					 if(obj instanceof String) {
        						 dataTypeElements[i] = obj.getClass().getName();
        					 }
        				 }
        			 }
        			 finally {
        				 
        			 }
        			 
        		 
        	 }
         }
		csvRead.close();
		DataTypeDefinitions typeDef = new DataTypeDefinitions(dataTypeElements);
		return typeDef;
	}
	
	
	
	
	
	