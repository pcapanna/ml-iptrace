package com.ml.iptrace.application.in_port.ip.trace;


import java.net.InetAddress;

public interface TraceIpUseCase {

    TraceIpResponse trace(InetAddress inetAddress);

}