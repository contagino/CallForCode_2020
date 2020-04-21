package com.cfc.contagino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cfc.contagino.entity.LocationEpidemic;
import com.cfc.contagino.entity.LocationEpidemicPk;

public interface LocationEpidemicRepository extends JpaRepository<LocationEpidemic, LocationEpidemicPk>{

}
