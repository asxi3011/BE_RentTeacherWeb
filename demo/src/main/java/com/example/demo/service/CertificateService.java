package com.example.demo.service;


import com.example.demo.dto.request.CertificateRequest;
import com.example.demo.dto.response.CertificateResponse;
import com.example.demo.entity.Certificate;
import com.example.demo.entity.Teacher;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.CertificateMapper;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.TeacherRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CertificateService {

    CertificateRepository repository;
    CertificateMapper mapper;
    TeacherRepository teacherRepository;

    public CertificateResponse create(CertificateRequest request){
        var context = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = teacherRepository.findByUsername(context.getName()).orElseThrow(()->new AppException(ErrorApp.USER_NOT_FOUND));
        Certificate certificate = mapper.toCertificate(request);
        certificate.setTeacher(teacher);
        repository.save(certificate);
        return mapper.toResponse(certificate);
    }

    public CertificateResponse getInfo(Integer id){
        Certificate certificate = repository.findById(id).orElseThrow(()->new AppException(ErrorApp.USER_NOT_FOUND));
        return mapper.toResponse(certificate);
    }
}
