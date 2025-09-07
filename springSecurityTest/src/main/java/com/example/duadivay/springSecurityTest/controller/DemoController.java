package com.example.duadivay.springSecurityTest.controller;

import com.example.duadivay.springSecurityTest.dto.DemoDto;
import com.example.duadivay.springSecurityTest.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/endpoint")
public class DemoController {
    private final DemoService demoService;

    @PostMapping
    public ResponseEntity<DemoDto> createNewDemo(@RequestBody DemoDto demoDto){
        return ResponseEntity.ok(demoService.createNewDemo(demoDto));
    }

    @GetMapping
    public ResponseEntity<List<DemoDto>> getAllDemo(){
        return ResponseEntity.ok(demoService.getAllDemo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemoDto> getDemoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(demoService.getDemoById(id));
    }
}
