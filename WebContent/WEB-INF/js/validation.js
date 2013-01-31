jQuery(function($) {
	
	$(document).tooltip({
		show: { effect: "blind", duration: 300 },
		hide: { effect: "explode", duration: 800 },
		 position: {
			 my: "center bottom-20",
			 at: "center top",
			 using: function( position, feedback ) {
			 $( this ).css( position );
			 $( "<div>" )
			 .addClass( "arrow" )
			 .addClass( feedback.vertical )
			 .addClass( feedback.horizontal )
			 .appendTo( this );
			 }
		}
	});
	
	$.validator.setDefaults({
		meta : "validate",
		submitHandler : function() {
				form.submit();
			},
		showErrors : function(map, list) {
			var focussed = document.activeElement;
			if (focussed && $(focussed).is("input, textarea")) {
				$(this.currentForm).tooltip("close", {
					currentTarget : focussed
				}, true);
			}
			//this.currentElements.removeAttr("title").removeClass(
			//		"ui-state-highlight");
			this.currentElements.removeAttr("title");
			$.each(list, function(index, error) {
				//$(error.element).attr("title", error.message).addClass(
				//		"ui-state-highlight");
				$(error.element).attr("title", error.message);
			});
			if (focussed && $(focussed).is("input, textarea")) {
				$(this.currentForm).tooltip("open", {
					target : focussed
				});
			}
		}
	});
});
