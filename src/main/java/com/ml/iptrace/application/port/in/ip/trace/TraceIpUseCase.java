package com.ml.iptrace.application.port.in.ip.trace;


import java.net.InetAddress;

public interface TraceIpUseCase {

    TraceIpResponse trace(InetAddress inetAddress);

}