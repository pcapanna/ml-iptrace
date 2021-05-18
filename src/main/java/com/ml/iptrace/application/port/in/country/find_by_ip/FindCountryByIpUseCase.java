package com.ml.iptrace.application.port.in.country.find_by_ip;

import com.ml.iptrace.domain.Country;

import java.net.InetAddress;

public interface FindCountryByIpUseCase {

    Country find(InetAddress inetAddress);

}
