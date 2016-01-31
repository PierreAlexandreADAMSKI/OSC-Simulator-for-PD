package app.service;

/**
 * app.service Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public enum Tag {
	POSITION_SOURCE,
	SAMPLE_ID;

	private String parse(Tag tag){
		return (tag == POSITION_SOURCE) ? "/position" : (tag == SAMPLE_ID) ? "/sample" : null;
	}
}
