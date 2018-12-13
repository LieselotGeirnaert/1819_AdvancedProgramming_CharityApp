package com.char1.api.repositories;

import com.char1.api.entity.Charity;
import com.char1.api.repository.CharityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharityRepositoryTests {

    @Autowired
    private CharityRepository charityRepository;

    @Test
    public void saveTest() {
        Charity charity = new Charity("oxfam test", "oxfam lets go", "");
        charityRepository.save(charity);
        Assert.assertNotNull(charityRepository.findByName("oxfam test"));
    }

    @Test
    public void deleteTest() {
        charityRepository.delete(charityRepository.findByName("oxfam test"));
        Assert.assertNull(charityRepository.findByName("oxfam test"));
    }
}
