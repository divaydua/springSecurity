package com.example.duadivay.springSecurityTest.service;

import com.example.duadivay.springSecurityTest.dto.DemoDto;
import com.example.duadivay.springSecurityTest.entities.DemoEntity;
import com.example.duadivay.springSecurityTest.entities.UserEntity;
import com.example.duadivay.springSecurityTest.repo.DemoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {

    private final DemoRepo demoRepo;
    private final ModelMapper modelMapper;


    public DemoDto createNewDemo(DemoDto demoDto) {
        DemoEntity demo = modelMapper.map(demoDto,DemoEntity.class);

        DemoEntity savedDemo = demoRepo.save(demo);

        return modelMapper.map(savedDemo,DemoDto.class);
    }

    public List<DemoDto> getAllDemo() {
        return demoRepo.findAll()
                .stream()
                .map(demoEntity ->
                        modelMapper.map(demoEntity,DemoDto.class))
                .collect(Collectors.toList());
    }


    public DemoDto getDemoById(Long id) throws Exception {

        //here if you want then we can get the authentication from the spring security context holder
        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal(); // this principal contains the user details only because in filter we use the 'user' object directly

        log.info("user {}",user); //logging our logged in user here

        DemoEntity demo = demoRepo.findById(id)
                .orElseThrow(()->new Exception("Demo id not found: "+id));
        return modelMapper.map(demo,DemoDto.class);
    }
}