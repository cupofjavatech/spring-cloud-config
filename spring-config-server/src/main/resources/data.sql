INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'server.port', '8103');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.datasource.url', 'jdbc:h2:mem:flight');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.datasource.driverClassName', 'org.h2.Driver');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.datasource.username', 'sa');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.datasource.password', 'password');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.h2.console.path', '/h2-console');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.h2.console.enabled', 'true');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.h2.console.settings.trace', 'false');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.h2.console.settings.web-allow-others', 'false');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.jpa.database-platform', 'org.hibernate.dialect.H2Dialect');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.jpa.hibernate.ddl-auto', 'none');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.jpa.properties.hibernate.format-sql', 'true');

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, PROP_KEY, PROP_VAL) 
VALUES ('flightservice', 'dev', 'master', 'spring.sql.init.mode', 'always');