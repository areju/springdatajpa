package com.arjun.springpersistence.springdatajpa.model.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class JpIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
	    String prefix = "jp";
	    Connection connection = session.connection();
	    
	    try {
	        Statement statement=connection.createStatement();

	        ResultSet rs=statement.executeQuery("select count(id) as Id from ITEM");

	        if(rs.next())
	        {
	            int id=rs.getInt(1)+101;
	            String generatedId = new StringBuilder().append(prefix).append(id).toString();
	            return generatedId;
	        }
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    };	
	    
	    return null;

	 }
	

}
