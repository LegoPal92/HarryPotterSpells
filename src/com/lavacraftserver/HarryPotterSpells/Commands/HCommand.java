package com.lavacraftserver.HarryPotterSpells.Commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HCommand {
	
	String name() default "";
	String description() default "";
	String aliases() default "";
	String permission() default "";
	String permissionDefault() default "OP";
	String usage() default "";
	
}
