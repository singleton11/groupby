package com.gbconnect.groupby;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupByApplicationTests {
    @Test
    public void tokenChecking() {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey("[B@1d9af731").parseClaimsJws("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZXRyYXllcjExQGdtYWlsLmNvbSIsInJvbGUiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV19.z5Wfl8Y7JRW5gzbabJcX18slPw53bvU-lSKfulSO-LmAuCYZq2TOMRyZxca84DqfFKFGjQi3Paux-6ViCkj_Kw");
            System.out.println(jws.getBody().getSubject());
            System.out.println("Correct");
        } catch (IncorrectClaimException e) {
            System.out.println("Incorrect");
        }
    }
}
