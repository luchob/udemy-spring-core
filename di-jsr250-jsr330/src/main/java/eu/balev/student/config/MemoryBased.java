package eu.balev.student.config;


import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.inject.Qualifier;
import java.lang.annotation.Retention;

@Qualifier
@Retention(RUNTIME)
public @interface MemoryBased {

}
