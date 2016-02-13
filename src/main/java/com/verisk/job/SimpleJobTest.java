/**
 * 
 */
package com.verisk.job;

import org.springframework.stereotype.Service;

/**
 * @author Naresh
 *
 */
@Service("simpleJobTest")
public class SimpleJobTest {

	protected void myTask() {
    	System.out.println("This is my task");
    }
}
