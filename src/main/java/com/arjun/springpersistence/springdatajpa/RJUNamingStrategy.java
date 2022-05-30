package com.arjun.springpersistence.springdatajpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class RJUNamingStrategy extends PhysicalNamingStrategyStandardImpl {
	
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		
		return new Identifier("AR_" + name.getText(), name.isQuoted());
	}

}
