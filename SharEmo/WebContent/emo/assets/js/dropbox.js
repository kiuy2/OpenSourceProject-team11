	    $(document).ready(function(){  
	        $(".nav-mid-item>a").hover(function() {
                $(this).css("color", "grey");
                $(this).parent().find(".nav-mid-item-drop").fadeIn('normal').show(); 
                $(this).parent().find(".nav-mid-item-drop li").hover(function() {
                    $(this).css("background-color", "#e6e6e6");

                    $(this).mouseleave(function() { 
                        $(this).css("background-color", "white");
                    });                    
                });

                $(this).parent().mouseleave(function() { 
                    $(this).find(">a").css("color", "white");
                    $(this).find(".nav-mid-item-drop").fadeOut('fast'); 
                });
                
            });  
	    }); 