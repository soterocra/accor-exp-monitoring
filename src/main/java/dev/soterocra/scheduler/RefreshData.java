package dev.soterocra.scheduler;

import dev.soterocra.usecase.UpdateTableUseCase;
import io.quarkus.scheduler.Scheduled;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RefreshData {

    private static final Logger LOG = Logger.getLogger(RefreshData.class.getSimpleName());
    @Inject
    UpdateTableUseCase updateTableUseCase;

    @Scheduled(every = "60s")
    void searchData() {
        LOG.info("Atualizando tabela da ALL");
        updateTableUseCase.refresh();
        LOG.info("Fim da atualização automática");
    }

}
