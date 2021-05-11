package com.ml.iptrace.infrastructure.in_adapter.web.trace_ip;

import com.ml.iptrace.application.in_port.ip.trace.TraceIpResponse;
import com.ml.iptrace.application.in_port.ip.trace.TraceIpService;
import com.ml.iptrace.infrastructure.WebAdapter;
import com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.dto.TraceIpWebRequestDTO;
import com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.dto.TraceIpWebResponseDTO;
import com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.exception.UnknownIpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@WebAdapter
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TraceIpController {

    private final TraceIpService traceIpService;

    @PostMapping(path = "/trace")
    @ResponseStatus(HttpStatus.OK)
    TraceIpWebResponseDTO trace(@RequestBody TraceIpWebRequestDTO traceIpWebRequestDTO) throws UnknownIpException {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(traceIpWebRequestDTO.getIp());
        } catch (UnknownHostException unknownHostException) {
            throw new UnknownIpException();
        }
        TraceIpResponse traceIpResponse = traceIpService.trace(inetAddress);
        return new TraceIpWebResponseDTO(traceIpWebRequestDTO.getIp(), traceIpResponse);
    }


}
