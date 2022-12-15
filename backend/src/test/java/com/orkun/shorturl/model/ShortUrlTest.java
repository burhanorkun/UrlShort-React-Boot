package com.orkun.shorturl.model;


import com.orkun.shorturl.models.ShortUrl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShortUrlTest {

    private static Validator validator;
    private ConstraintViolation<ShortUrl> violation;
    private Set<ConstraintViolation<ShortUrl>> constraintViolations;
    private ShortUrl shortUrl;
    @Before
    public void setUp(){
        shortUrl = new ShortUrl();
    }

    @After
    public void tearDown(){
    }

    @BeforeClass
    public static void oneTimeSetUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidShortUrl() {
        shortUrl.setLongUrl("http://www.google.com");
        shortUrl.setKey("test");
        assertEquals("http://www.google.com", shortUrl.getLongUrl());
        constraintViolations = validator.validate(shortUrl);
        assertTrue(constraintViolations.isEmpty());
    }


    @Test
    public void fullUrlCannotBeNull(){
        constraintViolations = validator.validate(shortUrl);
        assertEquals(2, constraintViolations.size());
        violation = constraintViolations.iterator().next();
        assertEquals("Key should not be blank", violation.getMessage());
        assertEquals("key", violation.getPropertyPath().toString());
    }

    @Test
    public void fullUrlCannotBeBlank() {
        shortUrl.setLongUrl("");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(2, constraintViolations.size());
        violation = constraintViolations.iterator().next();
        assertEquals("Full URL cannot be blank", violation.getMessage());
        assertEquals("longUrl", violation.getPropertyPath().toString());
    }

    @Test
    public void fullUrlMustHaveHttpPrefix() {
        shortUrl.setLongUrl("www.google.com");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(1, constraintViolations.size());
        violation = constraintViolations.iterator().next();
        assertEquals("must be a valid URL", violation.getMessage());
        assertEquals("longUrl", violation.getPropertyPath().toString());
    }

    @Test
    public void fullUrlCannotAllowXSSChars() {
        shortUrl.setLongUrl("www.g<script>oogle.com");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(1, constraintViolations.size());
        violation = constraintViolations.iterator().next();
        assertEquals("must be a valid URL", violation.getMessage());
        assertEquals("longUrl", violation.getPropertyPath().toString());
    }

    @Test
    public void fullUrlCannotAllowSQLInjectionChars() {
        shortUrl.setLongUrl("www.goo'gle.com");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(1, constraintViolations.size());
        violation = constraintViolations.iterator().next();
        assertEquals("must be a valid URL", violation.getMessage());
        assertEquals("longUrl", violation.getPropertyPath().toString());
    }

    @Test
    public void fullUrlStillValidWithoutWWW() {
        shortUrl.setLongUrl("http://google.com");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void fullUrlStillValidWithParameters() {
        shortUrl.setLongUrl("http://google.com?q=burhan");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void fullUrlMustHaveDomainPostfix() {
        shortUrl.setLongUrl("http://www.google");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void fullUrlCanHaveUncommonDomainPostfixBiz() {
        shortUrl.setLongUrl("http://orkun.biz");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void fullUrlCanHaveUncommonDomainPostfixInfo() {
        shortUrl.setLongUrl("http://orkun.info");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void fullUrlCanHaveUncommonDomainPostfixName() {
        shortUrl.setLongUrl("http://orkun.name");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void fullUrlCanHaveUncommonDomainPostfixLy() {
        shortUrl.setLongUrl("http://bit.ly");
        shortUrl.setKey("test");
        constraintViolations = validator.validate(shortUrl);
        assertEquals(0, constraintViolations.size());
    }

}
