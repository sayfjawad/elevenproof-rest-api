package nl.multicode.elevenproof.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ElevenProofValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElevenProofConstraint {

  String message() default "BSN must be a 9 character numeric value";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}