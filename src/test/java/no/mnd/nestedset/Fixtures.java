package no.mnd.nestedset;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class Fixtures {

    private static EmbeddedDatabase embeddedDatabase;

    public DataSource getDataSource() {
        if (embeddedDatabase == null) {
            embeddedDatabase = createDb();
        }
        return embeddedDatabase;
    }

    private EmbeddedDatabase createDb() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return  builder.setType(EmbeddedDatabaseType.H2)
                .addScript("create-db.sql")
                .build();
    }

}
