package com.lovethefeel.method.application;

import com.lovethefeel.method.dto.SubwayRequest;
import com.lovethefeel.method.dto.SubwayResponse;

public interface SubwayService {
    SubwayResponse saveSubway(final SubwayRequest subwayRequest);
}
