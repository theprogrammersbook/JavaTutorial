package com.muthyatechnology.customannotations.usage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.validation.constraints.NotNull;

import com.muthyatechnology.constraint.annotations.IssueInfo;


public class AnnotationTest {

    public static void main(String[] args) {
        Class<IssueInfoTest> issueInfoTestObject = IssueInfoTest.class;
        if (issueInfoTestObject.isAnnotationPresent(IssueInfo.class)) {

            Annotation annotation = (Annotation) issueInfoTestObject.getAnnotation(IssueInfo.class);
            IssueInfo testerInfo = (IssueInfo) annotation;
            System.out.println("In Classs============>");
            System.out.printf("%nType: %s", testerInfo.type());
            System.out.printf("%nReporter: %s", testerInfo.reporter());
            System.out.printf("%nCreated On: %s%n%n",
                    testerInfo.created());
        }
        for(Field  fields : issueInfoTestObject.getFields()){
          	 Annotation annotation = (Annotation) fields.getAnnotation(IssueInfo.class);
               IssueInfo testerInfo = (IssueInfo) annotation;
               System.out.println("In Fields============>");
               if(testerInfo!=null){
                   System.out.printf("%nType: %s", testerInfo.type());
                   System.out.printf("%nReporter: %s", testerInfo.reporter());
                   System.out.printf("%nCreated On: %s%n%n",
                           testerInfo.created());
               }
          }
        for(Field  fields : issueInfoTestObject.getFields()){
         	 Annotation annotation = (Annotation) fields.getAnnotation(NotNull.class);
         	NotNull testerInfo = (NotNull) annotation;
              System.out.println("In NOT NULL============>");
              if(testerInfo!=null){
            	  
            	  System.out.printf("%nType: %s", testerInfo.message());
              }
         }
        for(Method  method : issueInfoTestObject.getMethods()){
        	 Annotation annotation = (Annotation) method.getAnnotation(IssueInfo.class);
             IssueInfo testerInfo = (IssueInfo) annotation;
             if(testerInfo!=null){
            	 System.out.println("In Method============>");
                 System.out.printf("%nType: %s", testerInfo.type());
                 System.out.printf("%nReporter: %s", testerInfo.reporter());
                 System.out.printf("%nCreated On: %s%n%n",
                         testerInfo.created());
             }
             
        }
       
    }
}
