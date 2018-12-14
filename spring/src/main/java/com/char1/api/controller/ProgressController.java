package com.char1.api.controller;


import com.char1.api.entity.Progress;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.ProgressRepository;
import com.char1.api.repository.UserChallengeRepository;
import java.util.List;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgressController {
    @Autowired
    ProgressRepository progressRepository;
    
    @Autowired
    UserChallengeRepository userChallengeRepository;

    @GetMapping(value = "/progress")
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }
    
    @GetMapping(value = "/progress/{idChallenge}")
    public List<Progress> getAllProgressByUserChallenge(@PathVariable String idChallenge) {
       return progressRepository.findAllProgressByUserChallenge(userChallengeRepository.findById(NumberUtils.toInt(idChallenge)));
   }
}
