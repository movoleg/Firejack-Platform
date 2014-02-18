package net.firejack.platform.service.process.broker;

import net.firejack.platform.api.process.domain.*;
import net.firejack.platform.api.process.domain.Process;
import net.firejack.platform.core.broker.ListBroker;
import net.firejack.platform.core.domain.NamedValues;
import net.firejack.platform.core.exception.BusinessFunctionException;
import net.firejack.platform.core.model.registry.process.CaseModel;
import net.firejack.platform.core.request.ServiceRequest;
import net.firejack.platform.core.store.process.ICaseStore;
import net.firejack.platform.web.security.model.context.ContextManager;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class encapsulates the functionality of finding the cases by user actor
 */
@TrackDetails
@Component("findCaseBelongingToUserActorBroker")
public class FindCaseBelongingToUserActorBroker extends ListBroker<CaseModel, Case, NamedValues> {

    @Autowired
    @Qualifier("caseStore")
    private ICaseStore caseStore;

    @Autowired
    private TaskCaseProcessor taskCaseProcessor;

    /**
     * Invokes data access layer on order to find the cases
     * @param namedValuesServiceRequest service request containing lookup prefix and paging info
     * @return list of found cases
     * @throws BusinessFunctionException
     */
    @Override
    @SuppressWarnings("unchecked")
    protected List<CaseModel> getModelList(ServiceRequest<NamedValues> namedValuesServiceRequest) throws BusinessFunctionException {
        Long userId = ContextManager.getUserInfoProvider().getId();
        Integer offset = (Integer) namedValuesServiceRequest.getData().get("offset");
        Integer limit = (Integer) namedValuesServiceRequest.getData().get("limit");
        String sortColumn = CaseSearchTermVO.processSortColumn((String) namedValuesServiceRequest.getData().get("sort"));
        String sortDirection = (String) namedValuesServiceRequest.getData().get("dir");
        String lookupPrefix = (String) namedValuesServiceRequest.getData().get("lookupPrefix");
        //Boolean active = (Boolean) namedValuesServiceRequest.getData().get("active");
        return caseStore.findBelongingToUserActor(userId, lookupPrefix, getFilter(), offset, limit, sortColumn, sortDirection);
    }

    /**
     * Gets total count of found cases
     * @param namedValuesServiceRequest service request containing lookup prefix
     * @return number of found cases
     * @throws BusinessFunctionException
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Integer getTotal(ServiceRequest<NamedValues> namedValuesServiceRequest) throws BusinessFunctionException {
        Long userId = ContextManager.getUserInfoProvider().getId();
        String lookupPrefix = (String) namedValuesServiceRequest.getData().get("lookupPrefix");
        return (int)caseStore.countBelongingToUserActor(userId, lookupPrefix, getFilter());
    }

    /**
     * Converts list of case entities to the list of case data transfer objects
     * @param entities list of case entities to be converted
     * @return list of case data transfer objects
     */
    @Override
    protected List<Case> convertToDTOs(List<CaseModel> entities) {
        List<Case> cases = new ArrayList<Case>();
        Map<String, List<Process>> processesMap = new HashMap<String, List<Process>>();
        for (CaseModel entity : entities) {
            Case processCase = factory.convertTo(Case.class, entity);
            processCase.setUserCanPerform(true); // case is found by it's active task belonging to user's actor - always can perform
            cases.add(processCase);
            taskCaseProcessor.registerCaseProcess(processCase, processesMap);
        }
        taskCaseProcessor.initializeCaseStrategy(processesMap);
        return cases;
    }

}