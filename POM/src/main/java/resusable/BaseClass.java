package resusable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class BaseClass {
	
		public static Logger logger = LogManager.getLogger(BaseClass.class);
		
		
		@Test
	
		public void test1() {
			
			
			logger.error("Hello");
			
			logger.info("hello");
			
			
		

		}



}
