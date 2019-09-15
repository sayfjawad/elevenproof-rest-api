package nl.multicode.bsn.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BsnValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BsnConstraint {
    String message() default "BSN must be a 9 character numeric value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}