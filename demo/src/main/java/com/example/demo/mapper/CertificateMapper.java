package com.example.demo.mapper;

import com.example.demo.dto.request.CertificateRequest;
import com.example.demo.dto.response.CertificateResponse;
import com.example.demo.entity.Certificate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    Certificate toCertificate(CertificateRequest request);

    CertificateResponse toResponse(Certificate certificate);
}
