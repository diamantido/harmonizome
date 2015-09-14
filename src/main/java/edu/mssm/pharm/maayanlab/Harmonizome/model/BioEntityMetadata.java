package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BioEntityMetadata {
	
	String name();
	
	String keyColumn();
	
	String jsp();
}
