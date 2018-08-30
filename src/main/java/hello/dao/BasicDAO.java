package hello.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;

import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hello.entities.CalHistory;

@Repository
@Transactional
public class BasicDAO {
	@Autowired
	EntityManager entityManager;

	public void saveCalc(int a, int b, int sum) {
		
		entityManager.persist(new CalHistory(a, b, sum));
	}

	


	
}
