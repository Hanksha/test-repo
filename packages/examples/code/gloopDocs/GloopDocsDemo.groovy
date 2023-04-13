package gloopDocs

import io.toro.gloop.annotation.GloopComment
import io.toro.gloop.annotation.GloopHide
import io.toro.gloop.annotation.GloopObjectParameter
import io.toro.gloop.annotation.GloopParameter
import io.toro.gloop.object.property.GloopModel
import io.toro.gloop.object.property.GloopObject


class GloopDocsDemo {

	// The @GloopComment annotation is used to specify service-level comments visible to the Gloop UI.
	@GloopComment("I am a comment. I tell Gloop what this Groovy service does, which is to print 'hello' to the console")
	public static void sayHello() {
		println 'hello'
	}

	// The @GloopParameter annotation is used to document method parameters and return values,
	// for display so it can be displayed in the Gloop UI. Use the annotation on a method to document the return value,
	// and on parameters to document parameters.
	//
	// @GloopParameter's name parameter can be used to specify the name of an input or output property. For example:
	// @GloopParameter( name = "radius" ), will show a parameter with the name radius, regardless if the method
	// parameter has another name.
	//
	// Meanwhile, @GloopParameter's value parameter is used to describe an input (parameter) or output (return)
	// property. For example:
	// @GloopParameter( name = "radius", value = "The radius of the circle" )
	//
	// The @GloopParameter also has a allowNull parameter, which is used to indicate whether or not a property
	// may be allowed a null value. For example:
	// @GloopParameter( name = "radius", value = "The radius of the circle", allowNull = false )
	//
	// If you'd like to set a default value for a property, you may use @GloopParameter's defaultValue parameter.
	// For example:
	// @GloopParameter( name = "radius", value = "The radius of the circle", allowNull = false, defaultValue = '0' )
	@GloopComment('Yet another service that returns a greeting')
	@GloopParameter( name = "customOutputName", value = 'String message based on the input' )
	public static String greet1( @GloopParameter( 'Put any string value you can think of' ) String anyString,
								 @GloopParameter( defaultValue = 'John' ) String defaultValue ) {
		return "Greetings to: ${anyString?:defaultValue}"
	}

	// @GloopParameter also has a choices parameter, which tells Gloop that only a specific set of values are
	// valid for the annotated parameter. For example:
	// @GloopParameter( choices = ['Option 1', 'Option 2'] )
	//
	// With this annotation, an input property can only have 'Option 1' or 'Option 2' for its value.
	public static String greet2( @GloopParameter( choices = ['Hi', 'Hello'] ) String greeting ) {
		return "${greeting}!"
	}

	// If you've set choices for your input property, but would like to accept other values as well, then
	// set @GloopParameter's allowOtherValues parameter to true. For example:
	// @GloopParameter( choices = ['Option 1', 'Option 2'], allowOtherValues = true )
	public static String greet3( @GloopParameter( choices = ['Hi', 'Hello'], allowOtherValues = true ) String greeting ) {
		return "${greeting}!"
	}

	// The @GloopHide annotation can be used on a method to hide it from Gloop.
	// You can comment or uncomment this method's @GloopHide annotation to make hiddenMethod() appear or disappear from
	// the Martini Navigator.
	@GloopHide
	public static void hiddenMethod() {
		println 'I\'m supposed to be hidden!'
	}

	// The @GloopObjectParameter annotation is used to describe the structure of a GloopModel parameter or return
	// value. Use this annotation on a method to describe the returned model's structure, or use it on a parameter to
	// describe the parameter's structure.
	@GloopObjectParameter( 'output{\n  outputModel:The output model{\n    output\n  }\n}')
	public static GloopModel gloopObjectMethod( @GloopObjectParameter( value = 'inputModel:The input Model{\ninput::The input\n}' ) GloopModel inputModel ) {
		GloopModel output = (GloopModel) GloopObject.fromGloopDoc('output{\n  outputModel:The output model{\n    output\n  }\n}')
		output.outputModel.output = inputModel.input
		return output
	}

}
