package com.findjb.findjob.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findjb.findjob.Request.CreateFreelancer;
import com.findjb.findjob.Request.UpdateFreelancer;
import com.findjb.findjob.Service.JPA.FreelancerService;

@RestController
@RequestMapping("/api/user")
public class FreelancerController {
    @Autowired
    private FreelancerService freelancerService;

    @PostMapping({ "/create/freelancer" })
    public ResponseEntity<Object> createNewFreelancer(@RequestBody CreateFreelancer freelancer) throws Exception {
        freelancerService.createNewFreelancer(freelancer);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("message", "create successfully");
        return new ResponseEntity<Object>(response, null, 200);
    }

    @PutMapping("/update/freelancer/{id}")
    public ResponseEntity<Object> updateFreelancer(@RequestBody UpdateFreelancer updateFreelancer,
            @PathVariable Long id) {
        freelancerService.updateFreelancer(updateFreelancer, id);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("message", "create successfully");
        return new ResponseEntity<Object>(response, null, 200);
    }

    @DeleteMapping("/delete/freelancer/{id}")
    public ResponseEntity<Object> deleteFreelancer(@PathVariable Long id) {
        freelancerService.deleteFreelancer(id);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("message", "create successfully");
        return new ResponseEntity<Object>(response, null, 200);
    }
}
