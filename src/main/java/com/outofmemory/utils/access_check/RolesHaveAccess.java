package com.outofmemory.utils.access_check;

import com.outofmemory.entity.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RolesHaveAccess {
    User.Role[] restrict();
}
