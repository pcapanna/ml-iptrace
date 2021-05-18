package com.ml.iptrace.application.port.out;

import com.ml.iptrace.domain.CountryCode;

import java.net.InetAddress;

public interface ResolveCountryCodeByIpPort {
    CountryCode resolveCountryCodeByIp(InetAddress inetAddress);
}
