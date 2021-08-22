package co.uk.olm.group.resource.migration;

import co.uk.olm.group.service.migration.MigrationService;
import liquibase.change.CheckSum;
import liquibase.changelog.ChangeSetStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Path("/migration")
@ApplicationScoped
public class MigrationResource {

    private final MigrationService migrationService;

    @Inject
    public MigrationResource(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @GET
    @Path("/status")
    @Produces("application/json")
    public Set<SimpleChangeSetStatus> migrationStatus() throws Exception {
        final Set<SimpleChangeSetStatus> result = new HashSet<>();
        for (final ChangeSetStatus changeSet : migrationService.checkMigration()) {
            result.add(new SimpleChangeSetStatus(changeSet));
        }
        return result;
    }

    public static class SimpleChangeSetStatus {
        public String description;
        public String comments;
        public boolean willRun;
        public Date dateLastExecuted;
        public boolean previouslyRan;
        public CheckSum currentCheckSum;
        public CheckSum storedCheckSum;

        public SimpleChangeSetStatus(final ChangeSetStatus changeSet) {
            this.description = changeSet.getDescription();
            this.comments = changeSet.getComments();
            this.willRun = changeSet.getWillRun();
            this.dateLastExecuted = changeSet.getDateLastExecuted();
            this.previouslyRan = changeSet.getPreviouslyRan();
            this.currentCheckSum = changeSet.getCurrentCheckSum();
            this.storedCheckSum = changeSet.getStoredCheckSum();
        }
    }
}
