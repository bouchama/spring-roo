package org.springframework.roo.addon.web.mvc.controller.finder;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.springframework.roo.model.JavaType;
import org.springframework.roo.shell.CliAvailabilityIndicator;
import org.springframework.roo.shell.CliCommand;
import org.springframework.roo.shell.CliOption;
import org.springframework.roo.shell.CommandMarker;

/**
 * Commands which provide finder functionality through Spring MVC controllers.
 * 
 * @author Stefan Schmidt
 * @since 1.2.0
 */
@Component(immediate = true)
@Service
public class WebFinderCommands implements CommandMarker {

	@Reference private WebFinderOperations operations;

	@CliAvailabilityIndicator({ "web mvc finder add", "web mvc finder all" }) 
	public boolean isCommandAvailable() {
		return operations.isCommandAvailable();
	}

	@CliCommand(value = "web mvc finder add", help = "Adds @RooWebFinder annotation to MVC controller type") 
	public void add(
			@CliOption(key = "backingType", mandatory = true, help = "The finder-enabled type") JavaType finderType,
			@CliOption(key = "class", mandatory = false, unspecifiedDefaultValue = "*", optionContext = "update,project", help = "The controller java type to apply this annotation to") JavaType controllerType) {		
		operations.annotateType(controllerType, finderType);
	}
	
	@CliCommand(value = "web mvc finder all", help = "Adds  @RooWebFinder annotation to existing MVC controllers") 
	public void all() {
		operations.annotateAll();
	}
}