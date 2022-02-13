package com.lovethefeel.method.application;

import com.lovethefeel.method.domain.Subway;
import com.lovethefeel.method.domain.SubwayRepository;
import com.lovethefeel.method.dto.SubwayRequest;
import com.lovethefeel.method.dto.SubwayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubwayServiceImpl implements SubwayService {
    private SubwayRepository subwayRepository;

    @Autowired
    public void init(final SubwayRepository subwayRepository) {
        this.subwayRepository = subwayRepository;
    }

    public SubwayResponse saveSubway(final SubwayRequest subwayRequest) {
        final Subway saveSubway = subwayRepository.save(subwayRequest.toEntity());
        return SubwayResponse.from(saveSubway);
    }
}
