package com.Spring_Security_Back.shared.Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class ServiceFilters {
    private  Date startDate;
    private  Date endDate;
    private  String stateFilter;
    private  String clientNameFilter;
    private  String serviceTypeFilter;
    private  String plateFilter;
    private  String companyFilter;

    public ServiceFilters(Date startDate, Date endDate, String stateFilter, String clientNameFilter, String serviceTypeFilter, String plateFilter, String companyFilter) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.stateFilter = stateFilter;
        this.clientNameFilter = clientNameFilter;
        this.serviceTypeFilter = serviceTypeFilter;
        this.plateFilter = plateFilter;
        this.companyFilter = companyFilter;
    }
}
