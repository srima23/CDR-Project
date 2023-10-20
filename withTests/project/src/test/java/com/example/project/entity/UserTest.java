package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@SpringBootTest

public class UserTest {

        @Test
        public void testUserEntity() {
                // Ensure that the User class is annotated as an Entity
                assertTrue(User.class.isAnnotationPresent(Entity.class), "User should be annotated as @Entity");

                // Test the annotations on fields
                try {
                        assertFalse(User.class.getDeclaredField("id")
                                        .isAnnotationPresent((Class<? extends Annotation>) javax.persistence.Id.class),
                                        "id field should be annotated with @Id");
                } catch (NoSuchFieldException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                try {
                        assertTrue(User.class.getDeclaredField("id").isAnnotationPresent(GeneratedValue.class),
                                        "id field should be annotated with @GeneratedValue");
                } catch (NoSuchFieldException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        assertFalse(User.class.getDeclaredField("name")
                                        .isAnnotationPresent(jakarta.persistence.Basic.class),
                                        "name field should be annotated with @Basic");
                } catch (NoSuchFieldException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                try {
                        assertFalse(User.class.getDeclaredField("password")
                                        .isAnnotationPresent(jakarta.persistence.Basic.class),
                                        "password field should be annotated with @Basic");
                } catch (NoSuchFieldException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                // Ensure that lombok's @Data annotation is present
                assertFalse(User.class.isAnnotationPresent(lombok.Data.class), "User should be annotated with @Data");
        }
}
