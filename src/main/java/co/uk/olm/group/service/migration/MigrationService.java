package co.uk.olm.group.service.migration;

import io.quarkus.liquibase.LiquibaseFactory;
import liquibase.Liquibase;
import liquibase.changelog.ChangeSetStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MigrationService {

    private final LiquibaseFactory liquibaseFactory;

    @Inject
    public MigrationService(final LiquibaseFactory liquibaseFactory) {
        this.liquibaseFactory = liquibaseFactory;
    }

    public List<ChangeSetStatus> checkMigration() throws Exception {
        // Use the liquibase instance manually
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            // liquibase.dropAll();
            liquibase.validate();
            liquibase.update(liquibaseFactory.createContexts(), liquibaseFactory.createLabels());
            // Get the list of liquibase change set statuses
            return liquibase.getChangeSetStatuses(liquibaseFactory.createContexts(), liquibaseFactory.createLabels());
        }
    }
}
